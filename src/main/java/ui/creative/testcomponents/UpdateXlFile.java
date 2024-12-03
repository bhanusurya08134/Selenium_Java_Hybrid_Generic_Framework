package ui.creative.testcomponents;
///**
// * 
// */
//package DemoWebShop.testcomponents;
//
//import DemoWebShop.testcomponents.ReadXL;
//
//
///**
// * @author RaveendR
// *
// */
//public class UpdateXlFile {
//
//	/**
//	 * @param args
//	 * @return 
//	 */
//	public static void writeDetailsToXl(String fromFile,String toFile,String sheetFrom, String sheetTo, String testCaseFrom,
//			String testCaseTo,String dataFrom,String dataTo)
//	{
//
//		String testDataFromSplit[] = dataFrom.split(","); 		
//		int numberOfDataFrom = dataFrom.split(",").length;			
//		int numberOfDataTo = dataTo.split(",").length;  		
//		String dataToWrite="";		
//		String testDataToSplit[] = dataTo.split(",");	
//		
//		
//		/**Sheet from which values to be fetched As per parmeter system will fetch the details and write***/             
//		int rowFromFile, colsFromFile;   
//		int rowToFile, colsToFile; 		
//		rowFromFile = ReadXL.getRow(fromFile, sheetFrom, testCaseFrom);		
//		colsFromFile = ReadXL.XLCol(fromFile, sheetFrom, 0);		
//		rowToFile = ReadXL.getRow(toFile, sheetTo, testCaseTo);         
//		colsToFile = ReadXL.XLCol(toFile, sheetTo, 0);
//
//		System.out.println("rowFromFile:::"+rowFromFile);
//		System.out.println("colsFromFile:::"+colsFromFile);
//		System.out.println("rowToFile:::"+rowToFile);
//		System.out.println("colsToFile::"+colsToFile);		
//		System.out.println("numberOfDataFrom::"+numberOfDataFrom);
//		System.out.println("numberOfDataFrom::"+numberOfDataTo);	
//		
//				
//		for(int m=0;m<numberOfDataFrom;m++){			
//			System.out.println("m:::"+m);
//			Outer:			
//			for (int i = 0; i <= rowFromFile; i++) {				
//				System.out.println("i:::"+i);
//				for (int j = 0; j < colsFromFile; j++) {					
//					System.out.println("j:::"+j);
//					if (ReadXL.XLCellValue(fromFile, sheetFrom, i, j).equals(testDataFromSplit[m])) {						
//						System.out.println("Inside If:::"+rowFromFile);						
//						dataToWrite = ReadXL.XLCellValue(fromFile, sheetFrom, rowFromFile, testDataFromSplit[m]);  
//						ReadXL.writeCell(toFile, sheetTo, dataToWrite, rowToFile, ReadXL.getCol(toFile, sheetTo,testDataToSplit[m]));
//						break Outer;                            
//					}
//				}			
//			}
//
//		}
//
//	}
//	public static void writeToXl(String path,String sheetName,String columnName,String data,String testCase)
//	{
//
//		int row = ReadXL.getRow(path, sheetName, testCase);	
//		int cols = ReadXL.getCol(path, sheetName, columnName);	
//		
//		ReadXL.writeCell(path, sheetName, data, row, cols);
//
//	}
//
//
//
//
//}
