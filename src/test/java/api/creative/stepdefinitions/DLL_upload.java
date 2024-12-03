package api.creative.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import com.aventstack.extentreports.gherkin.model.Scenario;

import api.creative.testcomponents.APIBaseClass;
import api.creative.testcomponents.ExcelFileReaderAPI;

//import DemoWebShop.testcomponents.UpdateXlFile;
//import com.creative.payloads.ActivateLicense;
//import DemoWebShop.testcomponents.ExcelReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class DLL_upload extends APIBaseClass {

	

	public Map<String, String> testData = null;
	public static Response response = null;
	public static String resString = null;
	public static String testcaseID = null;

	public static String expDepartmentName=null;
	public static String DeptEmployeeName1=null;
	public static String DeptEmployeeName2=null;
	public static String Departmentid;
	public static String DeptEmpid1;
	public static String DeptEmpid2;
	
	public static String EmployeeName=null;
	public static String Employeeid;
	
	public static String expUserEmailAddress=null;
	public static String Userid;
	
	

	public DLL_upload() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	///////////////////////////////Department
	

	@Given("I hit DepartmentService POST {string}")
	public void i_hit_departmentService_post_with_name_as(String tcid) {
		try {
			
			Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("DepartMentService", tcid);
			
			String name =testData.get("employeeName");
			
			test = logInfo.get().createNode("I hit DepartmentService POST with name as "+name);
		
			expDepartmentName = testData.get("expDepartmentName");

			DeptEmployeeName1=name;

			String payload = "{\n"
					+ "  \"departmentName\": \""+expDepartmentName+"\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-15T13:16:32.500Z\",\n"
					+ "  \"employee\": [\n"
					+ "    {\n"
					+ "      \"employeeName\": \""+name+"\",\n"
					+ "      \"gender\": \"Male\",\n"
					+ "      \"city\": \"Mumbai\",\n"
					+ "      \"designation\": \"TeamMember\",\n"
					+ "      \"createdBy\": 1,\n"
					+ "      \"createdOn\": \"2023-02-15T13:16:32.500Z\"\n"
					+ "    }\n"
					+ "  ]\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("DepartmentService/Post").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I verify the status code as  {string}")
	public void i_verify_the_status_code_a(String string) {
		try {
			Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("CommonPage", "TCID_CP");
			test = logInfo.get().createNode("I verify the status code as "+string);
			reportInfo("Status Code "+response.statusCode());
			validateField("Status Code", testData.get("StatusCode"), String.valueOf(response.statusCode()));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}
	
	@Then("I validate the response for DepartmentService POST")
	public void i_validate_the_response_for_departmentService_post() {
		try {

			test = logInfo.get().createNode("I validate the response for DepartmentService POST");
			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("Department Name", expDepartmentName,js.getString("departmentName"));
			validateField("Employee Name", DeptEmployeeName1,js.getString("employee[0].employeeName"));

			Departmentid=js.getString("id");
			DeptEmpid1=js.getString("employee[0].id");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@When("I hit DepartmentService GetALL")
	public void i_hit_departmentService_get_all() {
		try {

			test = logInfo.get().createNode("I hit DepartmentService GetALL");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("DepartmentService/GetAll").then().extract().response();

			if(response!=null)
				reportPass("Status", "I hit DepartmentService GetALL successfully", "I hit DepartmentService GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
			//reportPayload(response.asPrettyString());

			resString =response.asPrettyString();	
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}
	@Then("I validate the response for DepartmentService GetALL")
	public void i_validate_the_response_for_departmentService_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for DepartmentService GetALL");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("departmentName").contains(expDepartmentName) && js.getString("id").contains(Departmentid)) {
				reportInfo("Department name '"+expDepartmentName+"' Present in GetAll response");
				reportInfo("Department id '"+Departmentid+"' Present in GetAll response");
				reportInfo("Employee name '"+DeptEmployeeName1+"' Present in GetAll response");
			}
			else
				reportFail("Department Name", expDepartmentName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit DepartmentService GetID")
	public void i_hit_departmentService_get_id() {
		try {
			test = logInfo.get().createNode("I hit DepartmentService GetID");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("DepartmentService/Get/"+Departmentid+"").then().extract().response();

			if(response!=null)
				reportPass("Status", "I hit DepartmentService GetID successfully", "I hit DepartmentService GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for DepartmentService GetID")
	public void i_validate_the_response_for_departmentService_get_id() {
		try {

			test = logInfo.get().createNode("I validate the response for DepartmentService GetID");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("departmentName").equalsIgnoreCase("["+expDepartmentName+"]") && js.getString("id").contains(Departmentid)) {
				validateField("Department name", "["+expDepartmentName+"]",js.getString("departmentName"));
				validateField("Department id", "["+Departmentid+"]",js.getString("id"));
			}
			else
				reportFail("Department name", expDepartmentName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Given("I hit DepartmentService PUT")
	public void i_hit_departmentService_put() {
		try {

			test = logInfo.get().createNode("I hit DepartmentService PUT");

			String updateEmployeeName = DeptEmployeeName1+"_updated";

			DeptEmployeeName2 = updateEmployeeName;
			DeptEmployeeName1 = updateEmployeeName;
			
			expDepartmentName="Purchase";

			String payload = "{\n"
					+ "\"id\":"+Departmentid+",\n"
					+ "  \"departmentName\": \""+expDepartmentName+"\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-15T13:16:32.500Z\",\n"
					+ "  \"employee\": [\n"
					+ "    {\n"
					+ "      \"employeeName\": \""+updateEmployeeName+"\",\n"
					+ "      \"gender\": \"Male\",\n"
					+ "      \"city\": \"Mumbai\",\n"
					+ "      \"designation\": \"TeamMember\",\n"
					+ "      \"createdBy\": 1,\n"
					+ "      \"createdOn\": \"2023-02-15T13:16:32.500Z\"\n"
					+ "    }\n"
					+ "  ]\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("DepartmentService/Put").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for DepartmentService PUT")
	public void i_validate_the_response_for_departmentService_put() {
		try {
			test = logInfo.get().createNode("I validate the response for DepartmentService PUT");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("Department name", expDepartmentName,js.getString("departmentName"));
			validateField("Employee name", DeptEmployeeName2,js.getString("employee[0].employeeName"));

			DeptEmpid2=js.getString("employee[0].id");

		
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	//department delete

	@When("I hit the created EmployeeService Delete")
	public void i_hit_the_created_employeeService_delete() {
		try {
			test = logInfo.get().createNode("I hit the EmployeeService Delete");
			
			Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("CommonPage", "TCID_CP");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("EmployeeService/Delete?Id="+DeptEmpid1+"").then().extract().response();

			reportPayload(response.asPrettyString());

			if(response.asPrettyString().equalsIgnoreCase("true"))
				validateField("Status", testData.get("DeleteStatus"), response.asPrettyString());
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I hit the updated EmployeeService Delete")
	public void i_hit_the_updated_employeeService_delete() {
		try {
			test = logInfo.get().createNode("I hit the updated EmployeeService Delete");
			
			Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("CommonPage", "TCID_CP");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("EmployeeService/Delete?Id="+DeptEmpid2+"").then().extract().response();

			reportPayload(response.asPrettyString());

			if(response.asPrettyString().equalsIgnoreCase("true"))
				validateField("Status",  testData.get("DeleteStatus"), response.asPrettyString());
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}


	@When("I hit the DepartmentService Delete")
	public void i_hit_the_departmentService_delete() {
		try {
			test = logInfo.get().createNode("I hit the DepartmentService Delete");
			
			Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("CommonPage", "TCID_CP");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("DepartmentService/Delete?Id="+Departmentid+"").then().extract().response();

			reportPayload(response.asPrettyString());

			if(response.asPrettyString().equalsIgnoreCase("true"))
				validateField("Status",testData.get("DeleteStatus"), response.asPrettyString());
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for DepartmentService GetALL after delete")
	public void i_validate_the_response_for_departmentService_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for DepartmentService GetALL after delete");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("id").contains(Departmentid)){//used id because Department name and Employee name is not unique
				validateField("Department Name", expDepartmentName,js.getString("departmentName"));
				reportFail("Status", "DepartmentService '"+expDepartmentName+"' should be deleted", "DepartmentService '"+expDepartmentName+"' not deleted");
			}
			else {
				reportPass("Department Name", expDepartmentName, "");
				reportInfo("Department Name '"+expDepartmentName+"' deleted succesfully");
			}	
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for DepartmentService GetID after delete")
	public void i_validate_the_response_for_departmentService_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for DepartmentService GetID after delete");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("departmentName").equalsIgnoreCase("["+expDepartmentName+"]")) {
				validateField("Department Name ", "[]",js.getString("departmentName"));
				reportFail("Status", "DepartmentService '"+expDepartmentName+"' should be deleted", "Department service '"+expDepartmentName+"' not deleted");
			}
			else {
				reportPass("Department Name ", "[]",js.getString("departmentName"));
				reportInfo("Department Name '"+expDepartmentName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}



	///////////////////////////////Employee-------------------------------------------------------------------------


	@Given("I hit EmployeeService POST {string}")
	public void i_hit_employeeService_post_with_name_as(String tcid) {
		try {
			
			Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("EmployeeService", tcid);
		

			EmployeeName= testData.get("employeeName");
			
			test = logInfo.get().createNode("I hit EmployeeService POST with name as "+EmployeeName);
			
			String payload = "{\n"
					+ "  \"employeeName\": \""+EmployeeName+"\",\n"
					+ "  \"gender\": \"Male\",\n"
					+ "  \"city\": \"Mumbai\",\n"
					+ "  \"designation\": \"TL\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-16T07:32:20.520Z\",\n"
					+ "  \"departmentId\": "+Departmentid+"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("EmployeeService/Post").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for EmployeeService POST")
	public void i_validate_the_response_for_employeeService_post() {
		try {
			test = logInfo.get().createNode("I validate the response for EmployeeService POST ");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("employeeName", EmployeeName,js.getString("employeeName"));
			//System.out.println("ID= "+js.getString("id"));
			Employeeid=js.getString("id");
			
			//Employee delete method exist so i need to update employee id
		//	DeptEmpid1=Employeeid;
			
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit EmployeeService GetALL")
	public void i_hit_employeeService_get_all() {
		try {
			test = logInfo.get().createNode("I hit EmployeeService GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("EmployeeService/GetAll").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit EmployeeService GetALL successfully", "I hit EmployeeService GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
			//reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for EmployeeService GetALL")
	public void i_validate_the_response_for_employeeService_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for EmployeeService GetALL ");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("employeeName").contains(EmployeeName) && js.getString("id").contains(Employeeid)) {
				reportInfo("'"+EmployeeName+"' Present in GetAll response");
				reportInfo("Employee id '"+Employeeid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("employeeName", EmployeeName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit EmployeeService GetID")
	public void i_hit_employeeService_get_id() {
		try {
			test = logInfo.get().createNode("I hit EmployeeService GetID ");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("EmployeeService/Get/"+Employeeid+"").then().extract().response();

			//reportPayload(response.asPrettyString());
			if(response!=null)
				reportPass("Status", "I hit EmployeeService GetID successfully", "I hit EmployeeService GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for EmployeeService GetID")
	public void i_validate_the_response_for_employeeService_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for EmployeeService GetID ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("employeeName").equalsIgnoreCase("["+EmployeeName+"]")) {
				validateField("employeeName", "["+EmployeeName+"]",js.getString("employeeName"));
				validateField("Employee id", "["+Employeeid+"]",js.getString("id"));
			}
			else
				reportFail("employeeName", EmployeeName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Given("I hit EmployeeService PUT")
	public void i_hit_employeeService_put() {
		try {
			test = logInfo.get().createNode("I hit EmployeeService PUT");

			String updatedEmployeeName = EmployeeName+"_updated";

			EmployeeName = updatedEmployeeName;

			String payload = "{\n"
					+ "  \"id\": "+Employeeid+",\n"
					+ "  \"employeeName\": \""+EmployeeName+"\",\n"
					+ "  \"gender\": \"Male\",\n"
					+ "  \"city\": \"Mumbai\",\n"
					+ "  \"designation\": \"TL\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-16T07:32:20.520Z\",\n"
					+ "  \"departmentId\": "+Departmentid+"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("EmployeeService/Put").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for EmployeeService PUT")
	public void i_validate_the_response_for_employeeService_put() {
		try {
			test = logInfo.get().createNode("I validate the response for EmployeeService PUT ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("employeeName", EmployeeName,js.getString("employeeName"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the EmployeeService Delete")
	public void i_hit_the_employeeService_delete() {
		try {
			test = logInfo.get().createNode("I hit the EmployeeService Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("EmployeeService/Delete?Id="+Employeeid+"").then().extract().response();

			reportPayload(response.asPrettyString());

			if(response.asPrettyString().equalsIgnoreCase("true"))
				validateField("Status", "true", response.asPrettyString());
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for EmployeeService GetALL after delete")
	public void i_validate_the_response_for_employeeService_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for EmployeeService GetALL after delete ");
			JsonPath js = new JsonPath(resString);

			reportPayload(response.asPrettyString());
			
			if(js.getString("employeeName").contains(EmployeeName)) {
				validateField("employeeName", EmployeeName,js.getString("employeeName"));
				reportFail("Status", "EmployeeService '"+EmployeeName+"' should be deleted", "EmployeeService '"+EmployeeName+"' not deleted");
			}
			else {
				reportPass("employeeName", EmployeeName, "");
				reportInfo("employeeName '"+EmployeeName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for EmployeeService GetID after delete")
	public void i_validate_the_response_for_employeeService_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for EmployeeService GetID after delete ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("employeeName").equalsIgnoreCase("["+EmployeeName+"]")) {
				validateField("employeeName", "["+EmployeeName+"]",js.getString("employeeName"));
				reportFail("Status", "EmployeeService '"+EmployeeName+"' should be deleted", "EmployeeService '"+EmployeeName+"' not deleted");
			}
			else {
				reportPass("employeeName", "[]",js.getString("employeeName"));
				reportInfo("employeeName '"+EmployeeName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	
	

	///////////////////////////////User-------------------------------------------------------

	
	@Given("I hit UserService POST {string}")
	public void i_hit_userService_post_with_name_as(String tcid) {
		try {
			
			
			Map<String, String> testData = ExcelFileReaderAPI.getDataInMap("UserService", tcid);
			

			expUserEmailAddress = testData.get("EmailAddress");
			
			test = logInfo.get().createNode("I hit UserService POST with name as "+expUserEmailAddress);
			
			String payload = "{\n"
					+ "  \"emailAddress\": \""+expUserEmailAddress+"\",\n"
					+ "  \"userSource\": \"extl\",\n"
					+ "  \"firstName\": \"R1\",\n"
					+ "  \"lastName\": \"R2\",\n"
					+ "  \"createdDate\": \"2023-02-16T07:39:24.702Z\"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("UserService/Post").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}
	@Then("I validate the response for UserService POST")
	public void i_validate_the_response_for_userService_post() {
		try {
			test = logInfo.get().createNode("I validate the response for UserService POST");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			if(js.getString("emailAddress").equalsIgnoreCase(expUserEmailAddress))
				validateField("Scheme Name", expUserEmailAddress,js.getString("emailAddress"));
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			//System.out.println("ID= "+js.getString("id"));
			Userid=js.getString("id");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit UserService GetALL")
	public void i_hit_userService_get_all() {
		try {
			test = logInfo.get().createNode("I hit UserService GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("UserService/GetAll").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit UserService GetALL successfully", "I hit UserService GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for UserService GetALL")
	public void i_validate_the_response_for_userService_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for UserService GetALL");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("emailAddress").contains(expUserEmailAddress) && js.getString("id").contains(Userid)) {
				reportInfo("User '"+expUserEmailAddress+"' Present in GetAll response");
				reportInfo("User id '"+Userid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("User Name", expUserEmailAddress, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@When("I hit UserService GetID")
	public void i_hit_userService_get_id() {
		try {
			test = logInfo.get().createNode("I hit UserService GetID");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("UserService/Get/"+Userid+"").then().extract().response();

			// reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit UserService GetID successfully", "I hit UserService GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for UserService GetID")
	public void i_validate_the_response_for_userService_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for UserService GetID");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("emailAddress").equalsIgnoreCase("["+expUserEmailAddress+"]")) {
				validateField("User Emailaddress ", "["+expUserEmailAddress+"]",js.getString("emailAddress"));
				validateField("User id", "["+Userid+"]",js.getString("id"));
			}
			else
				reportFail("User", expUserEmailAddress, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Given("I hit UserService PUT")
	public void i_hit_userService_put() {
		try {
			test = logInfo.get().createNode("I hit UserService PUT");

			String updateUserEmail = "www.TestAutotest0081_updated.com";

			expUserEmailAddress = updateUserEmail;

			String payload = "{\n"
					+ "  \"id\": "+Userid+",\n"
					+ "  \"emailAddress\": \""+updateUserEmail+"\",\n"
					+ "  \"userSource\": \"extl\",\n"
					+ "  \"firstName\": \"R3\",\n"
					+ "  \"lastName\": \"R5\",\n"
					+ "  \"createdDate\": \"2023-02-16T07:39:24.702Z\"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("UserService/Put").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for UserService PUT")
	public void i_validate_the_response_for_userService_put() {
		try {
			test = logInfo.get().createNode("I validate the response for UserService PUT");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("User Emailaddress", expUserEmailAddress,js.getString("emailAddress"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the UserService Delete")
	public void i_hit_the_userService_delete() {
		try {
			test = logInfo.get().createNode("I hit the UserService Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("UserService/Delete?Id="+Userid+"").then().extract().response();

			reportPayload(response.asPrettyString());

			if(response.asPrettyString().equalsIgnoreCase("true"))
				validateField("Status", "true", response.asPrettyString());
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for UserService GetALL after delete")
	public void i_validate_the_response_for_userService_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for UserService GetALL after delete");
			JsonPath js = new JsonPath(resString);

			reportPayload(response.asPrettyString());
			
			if(js.getString("emailAddress").contains(expUserEmailAddress)) {
				validateField("User Emailaddress", expUserEmailAddress,js.getString("emailAddress"));
				reportFail("Status", "UserService '"+expUserEmailAddress+"' should be deleted", "UserService '"+expUserEmailAddress+"' not deleted");
			}
			else {
				reportPass("User Emailaddress", expUserEmailAddress, "");
				reportInfo("User Emailaddress '"+expUserEmailAddress+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for UserService GetID after delete")
	public void i_validate_the_response_for_userService_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for UserService GetID after delete");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("emailAddress").equalsIgnoreCase("["+expUserEmailAddress+"]")) {
				validateField("User Emailaddress", "["+expUserEmailAddress+"]",js.getString("emailAddress"));
				reportFail("Status", "UserService '"+expUserEmailAddress+"' should be deleted", "UserService '"+expUserEmailAddress+"' not deleted");
			}
			else {
				reportPass("User Emailaddress", "[]",js.getString("emailAddress"));

				//	reportPass("User Emailaddress", "["+expUseEmailAddress+"]",js.getString("emailAddress"));

				reportInfo("User Emailaddress '"+expUserEmailAddress+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

}