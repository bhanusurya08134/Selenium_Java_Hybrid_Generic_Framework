package ui.creative.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Worksheet;

public class ExcelFileReader extends BaseTest {
	public ExcelFileReader() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static FileInputStream fis;
	private static Workbook wb;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;

	public static int XLCol(String path, String sheet, int row) {
		int xlCol;
		try {
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			xlCol = wb.getSheet(sheet).getRow(row).getLastCellNum();
			fis.close();
		} catch (Exception e) {

			return -1;
		}
		return xlCol;
	}

	public static int XLRow(String path, String sheet) {
		int xlRow;
		try {
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			xlRow = wb.getSheet(sheet).getLastRowNum();
			fis.close();
		} catch (Exception e) {

			return -1;
		}
		return xlRow;
	}

	public static int getCol(String path, String sheet, String search) {
		String cellValue;
		int col = -1;
		int noOfCol = XLCol(path, sheet, 0);
		try {
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			for (int i = 0; i <= 0; i++) {
				for (int j = 0; j < noOfCol; j++) {
					try {
						cellValue = wb.getSheet(sheet).getRow(i).getCell(j).getStringCellValue();
						if (cellValue.equals(search)) {
							col = j;
							break;
						} else {
							continue;
						}
					} catch (Exception e) {
						continue;
					}
				}
			}
		} catch (Exception e) {
			return -1;
		}
		return col;
	}

	public static int getRow(String path, String sheet, String search) {
		String cellValue;
		int row = -1;
		int noOfrows = XLRow(path, sheet);
		try {
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);

			for (int i = 1; i <= noOfrows; i++) {
				try {
					cellValue = wb.getSheet(sheet).getRow(i).getCell(0).getStringCellValue();
					if (cellValue.equals(search)) {
						row = i;
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					// add log statement here
				}
			}
		} catch (Exception e) {
			// add log statement here
		}

		return row;
	}

	public String getTestData(String path, String sheet, String rowName, String colName) {

		String cellValue;
		int cell = getCol(path, sheet, colName);
		int row = getRow(path, sheet, rowName);

		try {
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			cellValue = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();

			fis.close();
		} catch (Exception e) {

			return cellValue = " ";

		}
		return cellValue;
	}

	public String getConfigDetails(String Path, String SheetName, String Key) throws Exception {
		HashMap<String, String> excelDataArray = null;
		ArrayList<String> Headers = null;
		try {

			FileInputStream ExcelFile = new FileInputStream(Path);

			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int numOfColumns = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
			int numOfRows = ExcelWSheet.getPhysicalNumberOfRows();

			excelDataArray = new HashMap<>();
			Headers = new ArrayList<>();

			for (int k = 2; k < numOfRows; k++) {
				excelDataArray.put(ExcelWSheet.getRow(k).getCell(0).getStringCellValue(),
						ExcelWSheet.getRow(k).getCell(1).getStringCellValue());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excelDataArray.get(Key);
	}

	public static Map<String, String> getDataInMap(String SheetName, String testcaseId) throws Exception {

		Map<String, String> TestDatainMap = new TreeMap<String, String>();

		// String testDataFile=TestDataPath;
		String query = null;

		if (SheetName.equals("Login"))
			query = String.format("SELECT * from %s where ENVIRONMENT = '%s'", SheetName, testcaseId);
		else
			query = String.format("SELECT * from %s where TCID = '%s'", SheetName, testcaseId);

		Fillo fillo = new Fillo();
		Connection conn = null;
		Recordset recodset = null;
		try {
			conn = fillo.getConnection(System.getProperty("user.dir") + TestDataPath);
			recodset = conn.executeQuery(query);
			while (recodset.next()) {
				for (String field : recodset.getFieldNames()) {
					TestDatainMap.put(field, recodset.getField(field));

				}
			}
		} catch (FilloException e) {
			e.printStackTrace();
			throw new Exception("Test Data can't found");
		}
		conn.close();

		return TestDatainMap;

	}

	public static ArrayList<String> verifyExcelHeaders(String fileName) throws Exception {

		// Rename sheet
		com.spire.xls.Workbook workbook = new com.spire.xls.Workbook();
		workbook.loadFromFile(System.getProperty("user.dir") + File.separator + "Downloads\\" + fileName);
		Worksheet worksheet = workbook.getWorksheets().get(0);
		worksheet.setName(fileName.split("-")[0]);
		workbook.saveToFile(System.getProperty("user.dir") + File.separator + "Downloads\\" + fileName);

		ArrayList<String> header;

		String query = String.format("SELECT * from %s", fileName.split("-")[0]);
		Fillo fillo = new Fillo();
		Connection conn = null;
		Recordset recodset = null;
		try {
			conn = fillo.getConnection(System.getProperty("user.dir") + File.separator + "Downloads\\" + fileName);
			recodset = conn.executeQuery(query);
			header = recodset.getFieldNames();
		} catch (FilloException e) {
			e.printStackTrace();
			throw new Exception("Test Data can't found");
		}
		conn.close();
		return header;

	}
	
	public static Map<String, String> getDataInMap(String SheetName, String fieldId, String testcaseId) throws Exception {
//---------------------
		Map<String, String> TestDatainMap1 = new TreeMap<String, String>();
		// String testDataFile=TestDataPath;
		String query = null;

		query = String.format("SELECT * from %s where TCID = '%s'", SheetName, fieldId);

		Fillo fillo = new Fillo();
		Connection conn = null;
		Recordset recodset = null;
		try {
			conn = fillo.getConnection(System.getProperty("user.dir") + TestDataPath);
			recodset = conn.executeQuery(query);
			while (recodset.next()) {
				for (String field : recodset.getFieldNames()) {
					TestDatainMap1.put(field, recodset.getField(field));

				}
			}

		}catch (FilloException e) {
			e.printStackTrace();
			throw new Exception("Test Data can't found");
		}
		conn.close();
//------------------------------

		Map<String, String> TestDatainMap2 = new TreeMap<String, String>();
		// String testDataFile=TestDataPath;
		String query2 = null;

		query2 = String.format("SELECT * from %s where TCID = '%s'", SheetName, testcaseId);

		Fillo fillo2 = new Fillo();
		Connection conn2 = null;
		Recordset recodset2 = null;
		try {
			conn2 = fillo2.getConnection(System.getProperty("user.dir") + TestDataPath);
			recodset2 = conn2.executeQuery(query2);
			while (recodset2.next()) {
				for (String field : recodset2.getFieldNames()) {
					TestDatainMap2.put(field, recodset2.getField(field));

				}
			}

		}catch (FilloException e) {
			e.printStackTrace();
			throw new Exception("Test Data can't found");
		}
		conn2.close();

//------------------------------------Patch the column and values--------------------------------------------------
		Map<String, String> TestDatainMap = new TreeMap<String, String>();

//		System.out.println(TestDatainMap1);
//		System.out.println(TestDatainMap2);
//		System.out.println(TestDatainMap2.size());
//		System.out.println(TestDatainMap1.get("1") +" : "+ TestDatainMap2.get("1"));

//		for(int i = 1; i<TestDatainMap2.size(); i++) {
////			System.out.println(TestDatainMap1.get(i+"")+ " : "+TestDatainMap2.get(i+""));
//			
//			if(!(TestDatainMap1.get("COLUMN_"+i+"")==null)) {
//				TestDatainMap.put(TestDatainMap1.get("COLUMN_"+i+""), TestDatainMap2.get("COLUMN_"+i+""));
//			}
//		}
		
		
		for (String field : TestDatainMap1.keySet()) {

			String parentValue = TestDatainMap1.get(field);
			String childValue = TestDatainMap2.get(field);

			if (!parentValue.isEmpty() && !childValue.isEmpty()) {
				TestDatainMap.put(parentValue, childValue);
			}
		}
		
		TestDatainMap.put("TCID", TestDatainMap2.get("TCID"));

//		System.out.println(TestDatainMap);

		return TestDatainMap;

	}
}
