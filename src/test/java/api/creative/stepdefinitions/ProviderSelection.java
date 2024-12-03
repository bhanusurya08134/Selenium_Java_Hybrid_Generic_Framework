package api.creative.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import com.aventstack.extentreports.gherkin.model.Scenario;

import api.creative.testcomponents.APIBaseClass;

//import com.creative.TestComponents.UpdateXlFile;
//import com.creative.payloads.ActivateLicense;
//import com.creative.utilities.ExcelReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
//import safefleetLM.testcomponents.TestSetup;
//import safefleetLM.testcomponents.UpdateXlFile;

public class ProviderSelection extends APIBaseClass {

	

	public Map<String, String> testData = null;
	public static Response response = null;
	public static String resString = null;
	public static String testcaseID = null;
///
	public static String expSchemeMethodName=null;
	public static String Schemeid;
	
	public static String expUserEmailAddress=null;
	public static String Userid;
	
	public static String expHttpApiMethodName=null;
	public static String ApiMethodid;
	
	public static String expDepartmentName=null;
	public static String DeptEmployeeName1=null;
	public static String DeptEmployeeName2=null;
	public static String Departmentid;
	public static String DeptEmpid1;
	public static String DeptEmpid2;
	
	public static String expAuthName=null;         
	public static String Authid=null;

	public static String expSourceName=null;
	public static String SourceDestName1=null;
	public static String SourceDestName2=null;
	public static String Sourceid;
	public static String SourceDestid1;
	public static String SourceDestid2;

	public static String expBasicAuthName=null;
	public static String BasicAuthid;
	
	public static String expBearerAuthName=null;
	public static String BearerAuthid;
	
	public static String expCustomAuthName=null;
	public static String CustomAuthid;
	
	public static String expEmployeeName=null;
	public static String Employeeid;
	
	public static String expDestinationUrl=null;
	public static String Destinationid;
	
	
	

	public ProviderSelection() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	///////////////////////////////Scheme

	@Given("I hit Scheme POST with name as {string}")
	public void i_hit_scheme_post_with_name_as(String name) {
		try {
			test = logInfo.get().createNode("I hit Scheme POST with name as "+name);

			expSchemeMethodName = name;
			String payload = "{\n"
					+ "  \"schemeName\": \""+name+"\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-16T07:33:59.978Z\",\n"
					+ "  \"statusId\": 0\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("Scheme/InsertScheme").then().extract().response();

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
	@Then("I validate the response for Scheme POST")
	public void i_validate_the_response_for_scheme_post() {
		try {
			test = logInfo.get().createNode("I validate the response for Scheme POST ");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			//System.out.println("ID= "+js.getString("id"));
			Schemeid=js.getString("id");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Scheme GetALL")
	public void i_hit_scheme_get_all() {
		try {
			test = logInfo.get().createNode("I hit Scheme GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Scheme/GetAllSchemes").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit Scheme GetALL successfully", "I hit Scheme GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for Scheme GetALL")
	public void i_validate_the_response_for_scheme_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for Scheme GetALL ");

			JsonPath js = new JsonPath(resString);

			reportPayload(response.asPrettyString());
			
			if(js.getString("schemeName").contains(expSchemeMethodName) && js.getString("id").contains(Schemeid)) {
				reportInfo("'"+expSchemeMethodName+"' Present in GetAll response");
				reportInfo("Scheme id '"+Schemeid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("Scheme Name", expSchemeMethodName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Scheme GetID")
	public void i_hit_scheme_get_id() {
		try {
			test = logInfo.get().createNode("I hit  Scheme GetID ");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Scheme/GetScheme/"+Schemeid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit Scheme GetID successfully", "I hit Scheme GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for Scheme GetID")
	public void i_validate_the_response_for_scheme_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for Scheme GetID ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("schemeName").equalsIgnoreCase("["+expSchemeMethodName+"]")) {
				validateField("Scheme Name", "["+expSchemeMethodName+"]",js.getString("schemeName"));
				validateField("Scheme id", "["+Schemeid+"]",js.getString("id"));
			}
			else
				reportFail("Scheme Name", expSchemeMethodName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Given("I hit Scheme PUT")
	public void i_hit_scheme_put() {
		try {
			test = logInfo.get().createNode("I hit Scheme PUT");

			String updateSchemeName = expSchemeMethodName+"_updated";

			expSchemeMethodName = updateSchemeName;

			String payload = "{\n"
					+ "  \"id\": "+Schemeid+",\n"
					+ "  \"schemeName\": \""+updateSchemeName+"\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-16T07:33:59.978Z\",\n"
					+ "  \"statusId\": 0\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("Scheme/UpdateScheme").then().extract().response();

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

	@Then("I validate the response for Scheme PUT")
	public void i_validate_the_response_for_scheme_put() {
		try {
			test = logInfo.get().createNode("I validate the response for Scheme PUT ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the Scheme Delete")
	public void i_hit_the_scheme_delete() {
		try {
			test = logInfo.get().createNode("I hit the Scheme Delete ");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Scheme/DeleteScheme/"+Schemeid+"").then().extract().response();

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

	@Then("I validate the response for Scheme GetALL after delete")
	public void i_validate_the_response_for_scheme_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Scheme GetALL after delete ");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("schemeName").contains(expSchemeMethodName)) {
				validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
				reportFail("Status", "Scheme '"+expSchemeMethodName+"' should be deleted", "Scheme '"+expSchemeMethodName+"' not deleted");
			}
			else {
				reportPass("Scheme Name", expSchemeMethodName, "");
				reportInfo("Scheme Name '"+expSchemeMethodName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for Scheme GetID after delete")
	public void i_validate_the_response_for_scheme_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Scheme GetID after delete ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("schemeName").equalsIgnoreCase("["+expSchemeMethodName+"]")) {
				validateField("Scheme Name", "["+expSchemeMethodName+"]",js.getString("schemeName"));
				reportFail("Status", "Scheme '"+expSchemeMethodName+"' should be deleted", "Scheme '"+expSchemeMethodName+"' not deleted");
			}
			else {
				reportPass("Scheme Name", "[]",js.getString("schemeName"));
				reportInfo("Scheme Name '"+expSchemeMethodName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	
	///////////////////////////////User-------------------------------------------------------

	
	@Given("I hit User POST with name as {string}")
	public void i_hit_user_post_with_name_as(String EmailAddress) {
		try {
			test = logInfo.get().createNode("I hit User POST with name as "+EmailAddress);

			expUserEmailAddress = EmailAddress;
			String payload = "{\n"
					+ "  \"emailAddress\": \""+EmailAddress+"\",\n"
					+ "  \"userSource\": \"extl\",\n"
					+ "  \"firstName\": \"R1\",\n"
					+ "  \"lastName\": \"R2\",\n"
					+ "  \"createdDate\": \"2023-02-16T07:39:24.702Z\"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("User/InsertUser").then().extract().response();

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
	@Then("I validate the response for User POST")
	public void i_validate_the_response_for_user_post() {
		try {
			test = logInfo.get().createNode("I validate the response for User POST");

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

	@When("I hit User GetALL")
	public void i_hit_user_get_all() {
		try {
			test = logInfo.get().createNode("I hit User GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("User/GetAllUsers").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit User GetALL successfully", "I hit User GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for User GetALL")
	public void i_validate_the_response_for_user_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for User GetALL");

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

	@When("I hit User GetID")
	public void i_hit_user_get_id() {
		try {
			test = logInfo.get().createNode("I hit User GetID");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("User/GetUser/"+Userid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit User GetID successfully", "I hit User GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for User GetID")
	public void i_validate_the_response_for_user_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for User GetID");

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

	@Given("I hit User PUT")
	public void i_hit_user_put() {
		try {
			test = logInfo.get().createNode("I hit User PUT");

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
					.put("User/UpdateUser").then().extract().response();

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

	@Then("I validate the response for User PUT")
	public void i_validate_the_response_for_user_put() {
		try {
			test = logInfo.get().createNode("I validate the response for User PUT");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("User Emailaddress", expUserEmailAddress,js.getString("emailAddress"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the User Delete")
	public void i_hit_the_user_delete() {
		try {
			test = logInfo.get().createNode("I hit the User Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("User/DeleteUser/"+Userid+"").then().extract().response();

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

	@Then("I validate the response for User GetALL after delete")
	public void i_validate_the_response_for_user_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for User GetALL after delete");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("emailAddress").contains(expUserEmailAddress)) {
				validateField("User Emailaddress", expUserEmailAddress,js.getString("emailAddress"));
				reportFail("Status", "User '"+expUserEmailAddress+"' should be deleted", "User '"+expUserEmailAddress+"' not deleted");
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

	@Then("I validate the response for User GetID after delete")
	public void i_validate_the_response_for_user_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for User GetID after delete");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("emailAddress").equalsIgnoreCase("["+expUserEmailAddress+"]")) {
				validateField("User Emailaddress", "["+expUserEmailAddress+"]",js.getString("emailAddress"));
				reportFail("Status", "User '"+expUserEmailAddress+"' should be deleted", "User '"+expUserEmailAddress+"' not deleted");
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


	///////////////////////////////HttpApiMethod---------------------------------------------------


	@Given("I hit HttpApiMethod POST with name as {string}")
	public void i_hit_http_api_method_post_with_name_as(String name) {
		try {

			test = logInfo.get().createNode("I hit HttpApiMethod POST with name as "+name);
			expHttpApiMethodName = name;
			String payload = "{\n"
					+ "\n"
					+ "  \"methodName\": \""+name+"\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-16T07:05:05.335Z\",\n"
					+ "  \"statusId\": 0\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("HttpApiMethod/InsertHttpApiMethod").then().extract().response();

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

	@Then("I verify the status code as {string}")
	public void i_verify_the_status_code_as(String string) {
		try {

			test = logInfo.get().createNode("I verify the status code as "+string);
			reportInfo("Status Code "+response.statusCode());
			validateField("Status Code", "200", String.valueOf(response.statusCode()));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}
	@Then("I validate the response for HttpApiMethod POST")
	public void i_validate_the_response_for_http_api_method_post() {
		try {

			test = logInfo.get().createNode("I validate the response for HttpApiMethod POST");
			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("Method Name", expHttpApiMethodName,js.getString("methodName"));
			ApiMethodid=js.getString("id");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@When("I hit HttpApiMethod GetALL")
	public void i_hit_http_api_method_get_all() {
		try {

			test = logInfo.get().createNode("I hit HttpApiMethod GetALL");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("HttpApiMethod/GetAllHttpApiMethods").then().extract().response();

			if(response!=null)
				reportPass("Status", "I hit HttpApiMethod GetALL successfully", "I hit HttpApiMethod GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();	
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}
	@Then("I validate the response for HttpApiMethod GetALL")
	public void i_validate_the_response_for_http_api_method_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for HttpApiMethod GetALL");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("methodName").contains(expHttpApiMethodName) && js.getString("id").contains(ApiMethodid)) {
				reportInfo("HttpApiMethod name '"+expHttpApiMethodName+"' Present in GetAll response");
				reportInfo("HttpApiMethod id '"+ApiMethodid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("ApiMethod Name", expHttpApiMethodName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit HttpApiMethod GetID")
	public void i_hit_http_api_method_get_id() {
		try {
			test = logInfo.get().createNode("I hit HttpApiMethod GetID");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("HttpApiMethod/GetHttpApiMethod/"+ApiMethodid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit HttpApiMethod GetID successfully", "I hit HttpApiMethod GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	
	@Then("I validate the response for HttpApiMethod GetID")
	public void i_validate_the_response_for_http_api_method_get_id() {
		try {

			test = logInfo.get().createNode("I validate the response for HttpApiMethod GetID");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("methodName").equalsIgnoreCase("["+expHttpApiMethodName+"]")) {
				validateField("HttpApiMethod Name", "["+expHttpApiMethodName+"]",js.getString("methodName"));
				validateField("HttpApiMethod id", "["+ApiMethodid+"]",js.getString("id"));
			}
			else
				reportFail("HttpApiMethod Name", expHttpApiMethodName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Given("I hit HttpApiMethod PUT")
	public void i_hit_http_api_method_put() {
		try {

			test = logInfo.get().createNode("I hit HttpApiMethod PUT");

			String updateHttpApiMethodName = expHttpApiMethodName+"_updated";

			expHttpApiMethodName = updateHttpApiMethodName;

			String payload = "{\n"
					+ "  \"id\": "+ApiMethodid+",\n"
					+ "  \"methodName\": \""+updateHttpApiMethodName+"\",\n"
					+ "  \"createdBy\": 0,\n"
					+ "  \"createdOn\": \"2023-02-16T07:05:42.840Z\",\n"
					+ "  \"statusId\": 0\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("HttpApiMethod/UpdateHttpApiMethod").then().extract().response();

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

	@Then("I validate the response for HttpApiMethod PUT")
	public void i_validate_the_response_for_http_api_method_put() {
		try {
			test = logInfo.get().createNode("I validate the response for HttpApiMethod PUT");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("HttpApiMethod Name", expHttpApiMethodName,js.getString("methodName"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}


	@When("I hit the HttpApiMethod Delete")
	public void i_hit_the_http_api_method_delete() {
		try {
			test = logInfo.get().createNode("I hit the HttpApiMethod Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("HttpApiMethod/DeleteHttpApiMethod/"+ApiMethodid+"").then().extract().response();

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
	@Then("I validate the response for HttpApiMethod GetALL after delete")
	public void i_validate_the_response_for_http_api_method_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for HttpApiMethod GetALL after delete");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("methodName").contains(expHttpApiMethodName)) {
				validateField("HttpApiMethod Name", expHttpApiMethodName,js.getString("methodName"));
				reportFail("Status", "HttpApiMethod '"+expHttpApiMethodName+"' should be deleted", "HttpApiMethod '"+expHttpApiMethodName+"' not deleted");
			}
			else {
				reportPass("HttpApiMethod Name", expHttpApiMethodName, "");
				reportInfo("HttpApiMethod Name '"+expHttpApiMethodName+"' deleted succesfully");
			}	
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for HttpApiMethod GetID after delete")
	public void i_validate_the_response_for_http_api_method_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for HttpApiMethod GetID after delete");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("methodName").equalsIgnoreCase("["+expHttpApiMethodName+"]")) {
				validateField("HttpApiMethod Name", "["+expHttpApiMethodName+"]",js.getString("methodName"));
				reportFail("Status", "HttpApiMethod '"+expHttpApiMethodName+"' should be deleted", "HttpApiMethod '"+expHttpApiMethodName+"' not deleted");
			}
			else {
				reportPass("HttpApiMethod Name", "[]",js.getString("methodName"));
				reportInfo("HttpApiMethod Name '"+expHttpApiMethodName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}


	///////////////////////////////Department------------------------------------------------------
	

	@Given("I hit Department POST with name as {string}")
	public void i_hit_department_post_with_name_as(String name) {
		try {

			test = logInfo.get().createNode("I hit Department POST with name as "+name);
			
			expDepartmentName = "Admin";

			DeptEmployeeName1=name;

			String payload = "{\n"
					+ "  \"departmentName\": \"Admin\",\n"
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
					.post("Department/InsertDepartment").then().extract().response();

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

	@Then("I validate the response for Department POST")
	public void i_validate_the_response_for_department_post() {
		try {

			test = logInfo.get().createNode("I validate the response for Department POST");
			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("Department Name", expDepartmentName,js.getString("departmentName"));
			validateField("Employee Name", DeptEmployeeName1,js.getString("employee[0].employeeName"));

			Departmentid=js.getString("id");
			DeptEmpid1=js.getString("employee[0].id");

			//	System.out.println("Dept id ="+js.getString("id")+"Emp id "+js.getString("employee[0].id"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@When("I hit Department GetALL")
	public void i_hit_department_get_all() {
		try {

			test = logInfo.get().createNode("I hit Department GetALL");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Department/GetAllDepartments").then().extract().response();

			if(response!=null)
				reportPass("Status", "I hit Department GetALL successfully", "I hit Department GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();	
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}
	@Then("I validate the response for Department GetALL")
	public void i_validate_the_response_for_department_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for Department GetALL");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("departmentName").contains(expDepartmentName) && js.getString("id").contains(Departmentid)) {
				reportInfo("Department name '"+expDepartmentName+"' Present in GetAll response");
				reportInfo("Department id '"+Departmentid+"' Present in GetAll response");
				reportInfo("Employee name '"+DeptEmployeeName1+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("Department Name", expDepartmentName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Department GetID")
	public void i_hit_department_get_id() {
		try {
			test = logInfo.get().createNode("I hit Department GetID");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Department/GetDepartment/"+Departmentid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit Department GetID successfully", "I hit Department GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for Department GetID")
	public void i_validate_the_response_for_department_get_id() {
		try {

			test = logInfo.get().createNode("I validate the response for Department GetID");

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

	@Given("I hit Department PUT")
	public void i_hit_department_put() {
		try {

			test = logInfo.get().createNode("I hit Department PUT");

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
					.put("Department/UpdateDepartment").then().extract().response();

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

	@Then("I validate the response for Department PUT")
	public void i_validate_the_response_for_department_put() {
		try {
			test = logInfo.get().createNode("I validate the response for Department PUT");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("Department name", expDepartmentName,js.getString("departmentName"));
			validateField("Employee name", DeptEmployeeName2,js.getString("employee[0].employeeName"));

			DeptEmpid2=js.getString("employee[0].id");

			System.out.println(Departmentid+"   "+DeptEmpid1+"   "+DeptEmpid2);
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	//department delete

	@When("I hit the created Employee Delete")
	public void i_hit_the_created_employee_delete() {
		try {
			test = logInfo.get().createNode("I hit the Employee Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Employee/DeleteEmployee/"+DeptEmpid1+"").then().extract().response();

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

	@Then("I hit the updated Employee Delete")
	public void i_hit_the_updated_employee_delete() {
		try {
			test = logInfo.get().createNode("I hit the updated Employee Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Employee/DeleteEmployee/"+DeptEmpid2+"").then().extract().response();

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


	@When("I hit the Department Delete")
	public void i_hit_the_department_delete() {
		try {
			test = logInfo.get().createNode("I hit the Department Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Department/DeleteDepartment/"+Departmentid+"").then().extract().response();

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
	@Then("I validate the response for Department GetALL after delete")
	public void i_validate_the_response_for_department_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Department GetALL after delete");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("id").contains(Departmentid)){//used id because Department name and Employee name is not unique
				validateField("Department Name", expDepartmentName,js.getString("departmentName"));
				reportFail("Status", "Department '"+expDepartmentName+"' should be deleted", "Department '"+expDepartmentName+"' not deleted");
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

	@Then("I validate the response for Department GetID after delete")
	public void i_validate_the_response_for_department_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Department GetID after delete");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("departmentName").equalsIgnoreCase("["+expDepartmentName+"]")) {
				validateField("Department Name ", "[]",js.getString("departmentName"));
				reportFail("Status", "Department '"+expDepartmentName+"' should be deleted", "Department '"+expDepartmentName+"' not deleted");
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



	///////////////////////////////Auth---------------------------------------------------



	@Given("I hit Auth POST with name as {string}")
	public void i_hit_auth_post_with_name_as(String name) {
		try {
			test = logInfo.get().createNode("I hit Auth POST with name as "+name);
			expAuthName = name;
			String payload = "{\n"
					+ "  \"authName\": \""+expAuthName+"\",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:07:47.442Z\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"userId\": "+Userid+",\n"
					+ "  \"statusId\": 0 \n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("Auth/InsertAuth").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload); 
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}
	}

	@Then("I validate the response for Auth POST")
	public void i_validate_the_response_for_auth_post() {
		try {
			test = logInfo.get().createNode("I validate the response for Auth POST");
			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("AuthName", expAuthName,js.getString("authName"));
			Authid=js.getString("id");

		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@When("I hit Auth GetALL")
	public void i_hit_auth_get_all() {
		try {
			test = logInfo.get().createNode("I hit Auth GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().get("Auth/GetAllAuths").then().extract().response();
			resString =response.asPrettyString();
			
			if(response!=null)
				reportPass("Status", "I hit Auth GetALL successfully", "I hit Auth GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@Then("I validate the response for Auth GetALL")
	public void i_validate_the_response_for_auth_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for Auth GetALL");
			
			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);
			
			if(js.getString("authName").contains(expAuthName) && js.getString("id").contains(Authid)) {
				reportInfo("AuthName '"+expAuthName+"' Present in GetAll response");
				reportInfo("Auth id '"+Authid+"' Present in GetAll response");

			}
		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@When("I hit Auth GetID")
	public void i_hit_auth_get_id() {
		try {
			test = logInfo.get().createNode("I hit Auth GetID");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().get("Auth/GetAuth/"+Authid+"").then().extract().response();
			JsonPath js = new JsonPath(resString);
			resString =response.asPrettyString();
			
		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit Auth GetID successfully", "I hit Auth GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@Then("I validate the response for Auth GetID")
	public void i_validate_the_response_for_auth_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for Auth GetID");
			JsonPath js = new JsonPath(resString);
			reportPayload(response.asPrettyString());
			
			validateField("Authname", "["+expAuthName+"]",js.getString("authName"));
			
			validateField("Auth id", "["+Authid+"]",js.getString("id"));
			
		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}


	}

	@When("I hit Auth PUT")
	public void i_hit_auth_put() {
		try {
			test = logInfo.get().createNode("I hit Auth PUT");
			
			expAuthName = expAuthName+"_Update";

			String payload = "{\n"
					+ "  \"id\": "+Authid+",\n"
					+ "  \"authName\": \""+expAuthName+"\",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:07:47.442Z\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"userId\": "+Userid+",\n"
					+ "  \"statusId\": 0 \n"
					+ "  \n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("Auth/UpdateAuth").then().extract().response();

			resString =response.asPrettyString();

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@Then("I validate the response for Auth PUT")
	public void i_validate_the_response_for_auth_put() {
		try {
			test = logInfo.get().createNode("I validate the response for Auth PUT");
			JsonPath js = new JsonPath(resString);
			resString =response.asPrettyString();
			reportPayload(resString);
			validateField("UpdateAuthName", expAuthName,js.getString("authName"));
		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@When("I hit the Auth Delete")
	public void i_hit_the_auth_delete() {
		try {
			test = logInfo.get().createNode("I hit the Auth Delete");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().delete("Auth/DeleteAuth/"+Authid+"").then().extract().response();
			reportPayload(response.asPrettyString());
			if(response.asPrettyString().equalsIgnoreCase("true"))
				validateField("Status", "true", response.asPrettyString());
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@Then("I validate the response for Auth GetALL after delete")
	public void i_validate_the_response_for_auth_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Auth GetALL after delete");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().get("Auth/GetAllAuths").then().extract().response();
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("authName").contains(expAuthName)) {
				validateField("authName", expAuthName,js.getString("authName"));
				reportFail("Status", "Auth '"+expAuthName+"' should be deleted", "Auth '"+expAuthName+"' not deleted");
			}
			else {
				reportPass("authName", expAuthName, "");
				reportInfo("authName '"+expAuthName+"' deleted Successfully");
			}

		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}

	}

	@Then("I validate the response for Auth GetID after delete")
	public void i_validate_the_response_for_auth_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Auth GetID after delete");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().get("Auth/GetAuth/"+Authid+"").then().extract().response();
			JsonPath js = new JsonPath(resString);

			if(js.getString("authName").contains(expAuthName)) {
				validateField("authName", expAuthName,js.getString("authName"));
				reportFail("Status", "Auth '"+expAuthName+"' should be deleted", "Auth '"+expAuthName+"' not deleted");
			}
			else {
				reportPass("AuthName", "[]", js.getString("authName"));
				reportInfo("AuthName '"+expAuthName+"' deleted Successfully");}

		}
		catch (Exception e) {
			testStepHandle("FAIL",test,e);
		}
	}


	///////////////////////////////Source----------------------------------------------------------------------------------
	
	
	@Given("I hit Source POST with name as {string}")
	public void i_hit_source_post_with_name_as(String name) {
		try {

			test = logInfo.get().createNode("I hit Source POST with name as "+name);
			
			expSourceName = name;

			String payload = "{\n"
					+ "  \"sourceName\": \""+expSourceName+"\",\n"
					+ "  \"endpointName\": \"xxxx\",\n"
					+ "  \"sourcePayload\": \"xxx\",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:35:38.284Z\",\n"
					+ "  \"userId\": "+Userid+",\n"
					+ "  \"statusId\": 0,\n"
					+ "  \"destination\": [\n"
					+ "    {\n"
					+ "      \"destinationURL\": \"http://www.fb.com\",\n"
					+ "      \"destinationPayload\": \"xxxx\",\n"
					+ "      \"modifiedBy\": 1,\n"
					+ "      \"modifiedOn\": \"2023-02-16T07:35:38.284Z\",\n"
					+ "      \"authId\": "+Authid+",\n"
					+ "      \"sourceId\": 2,\n"
					+ "      \"statusId\": 0,\n"
					+ "      \"methodId\": 1,\n"
					+ "      \"destinationPayloadMapping\": \"xxx\",\n"
					+ "      \"urlParameter\": \"xx\"\n"
					+ "  }]\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("Source/InsertSource").then().extract().response();

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

	@Then("I validate the response for Source POST")
	public void i_validate_the_response_for_source_post() {
		try {

			test = logInfo.get().createNode("I validate the response for Source POST");
			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("sourceName", expSourceName,js.getString("sourceName"));
		//	validateField("Employee Name", DeptEmployeeName1,js.getString("employee[0].employeeName"));

			Sourceid=js.getString("id");
			SourceDestid1=js.getString("destination[0].id");

			//	System.out.println("Dept id ="+js.getString("id")+"Emp id "+js.getString("employee[0].id"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@When("I hit Source GetALL")
	public void i_hit_source_get_all() {
		try {

			test = logInfo.get().createNode("I hit Source GetALL");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Source/GetAllSources").then().extract().response();

			if(response!=null)
				reportPass("Status", "I hit Source GetALL successfully", "I hit Source GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();	
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}
	@Then("I validate the response for Source GetALL")
	public void i_validate_the_response_for_source_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for Source GetALL");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("sourceName").contains(expSourceName) && js.getString("id").contains(Sourceid)) {
				reportInfo("Source name '"+expSourceName+"' Present in GetAll response");
				reportInfo("Source id '"+Sourceid+"' Present in GetAll response");
			//	reportInfo("Employee name '"+DeptEmployeeName1+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("Source Name", expSourceName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Source GetID")
	public void i_hit_source_get_id() {
		try {
			test = logInfo.get().createNode("I hit Source GetID");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Source/GetSource/"+Sourceid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			
			if(response!=null)
				reportPass("Status", "I hit Source GetID successfully", "I hit Source GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for Source GetID")
	public void i_validate_the_response_for_source_get_id() {
		try {

			test = logInfo.get().createNode("I validate the response for Source GetID");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("sourceName").equalsIgnoreCase("["+expSourceName+"]") && js.getString("id").contains(Sourceid)) {
				validateField("Source name", "["+expSourceName+"]",js.getString("sourceName"));
				validateField("Source id", "["+Sourceid+"]",js.getString("id"));
			}
			else
				reportFail("Source name", expSourceName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Given("I hit Source PUT")
	public void i_hit_source_put() {
		try {

			test = logInfo.get().createNode("I hit Source PUT");

			String updatedSourcename = expSourceName+"_updated";

			expSourceName = updatedSourcename;

			String payload = "{\n"
					+ "  \"id\": "+Sourceid+",\n"
					+ "  \"sourceName\": \""+updatedSourcename+"\",\n"
					+ "  \"endpointName\": \"vvvvv\",\n"
					+ "  \"sourcePayload\": \"bbbbb\",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:35:38.284Z\",\n"
					+ "  \"userId\": "+Userid+",\n"
					+ "  \"statusId\": 0,\n"
					+ "  \"destination\": [\n"
					+ "    {\n"
					+ "      \"destinationURL\": \"http://www.fb.com\",\n"
					+ "      \"destinationPayload\": \"ccccc\",\n"
					+ "      \"modifiedBy\": 1,\n"
					+ "      \"modifiedOn\": \"2023-02-16T07:35:38.284Z\",\n"
					+ "      \"authId\": "+Authid+",\n"
					+ "      \"sourceId\": 2,\n"
					+ "      \"statusId\": 0,\n"
					+ "      \"methodId\": 1,\n"
					+ "      \"destinationPayloadMapping\": \"vvvv\",\n"
					+ "      \"urlParameter\": \"xzzzz\"\n"
					+ "  }]\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("Source/UpdateSource").then().extract().response();

			resString =response.asPrettyString();

		//	expDepartmentName="Purchase";

			if(response!=null)
				reportPayload(payload);
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for Source PUT")
	public void i_validate_the_response_for_source_put() {
		try {
			test = logInfo.get().createNode("I validate the response for Source PUT");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("Source name", expSourceName,js.getString("sourceName"));
		//	validateField("Employee name", DeptEmployeeName2,js.getString("employee[0].employeeName"));

			SourceDestid2=js.getString("destination[0].id");

			System.out.println(Sourceid+"   "+SourceDestid1+"   "+SourceDestid2);
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	//source delete

	@When("I hit the created Destination Delete")
	public void i_hit_the_created_Destination_delete() {
		try {
			test = logInfo.get().createNode("I hit the Destination Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Destination/DeleteDestination/"+SourceDestid1+"").then().extract().response();

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

	@Then("I hit the updated Destination Delete")
	public void i_hit_the_updated_Destination_delete() {
		try {
			test = logInfo.get().createNode("I hit the updated Destination Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Destination/DeleteDestination/"+SourceDestid2+"").then().extract().response();

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


	@When("I hit the Source Delete")
	public void i_hit_the_source_delete() {
		try {
			test = logInfo.get().createNode("I hit the Source Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Source/DeleteSource/"+Sourceid+"").then().extract().response();

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
	
	@Then("I validate the response for Source GetALL after delete")
	public void i_validate_the_response_for_source_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Source GetALL after delete");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("sourceName").contains(expSourceName) && js.getString("id").contains(Sourceid)) {
				validateField("Source Name", expSourceName,js.getString("sourceName"));
				reportFail("Status", "Source '"+expSourceName+"' should be deleted", "Source '"+expSourceName+"' not deleted");
			}
			else {
				reportPass("Source Name", expSourceName, "");
				reportInfo("Source Name '"+expSourceName+"' deleted succesfully");
			}	
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for Source GetID after delete")
	public void i_validate_the_response_for_source_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Source GetID after delete");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("sourceName").equalsIgnoreCase("["+expSourceName+"]")) {
				validateField("Source Name ", "["+expSourceName+"]",js.getString("sourceName"));
				reportFail("Status", "Source '"+expSourceName+"' should be deleted", "Source '"+expSourceName+"' not deleted");
			}
			else {
				reportPass("Source Name ", "[]",js.getString("sourceName"));
				reportInfo("Source Name '"+expSourceName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	
	
	///////////////////////////////BasicAuth-----------------------------------------------------------



	@Given("I hit BasicAuth POST with name as {string}")
	public void i_hit_basicAuth_post_with_name_as(String name) {
		try {
			test = logInfo.get().createNode("I hit BasicAuth POST with name as "+name);

			expBasicAuthName = name;
			
			String payload = "{\n"
					+ " \n"
					+ "  \"username\": \""+expBasicAuthName+"\",\n"
					+ "  \"password\": \"zzzzzzzzzzz\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:14:39.813Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"statusId\": 0\n"
					+ " \n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("BasicAuth/InsertBasicAuth").then().extract().response();

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
	@Then("I validate the response for BasicAuth POST")
	public void i_validate_the_response_for_basicAuth_post() {
		try {
			test = logInfo.get().createNode("I validate the response for BasicAuth POST ");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("BasicAuth username", expBasicAuthName,js.getString("username"));
			//System.out.println("ID= "+js.getString("id"));
			BasicAuthid=js.getString("id");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit BasicAuth GetALL")
	public void i_hit_basicAuth_get_all() {
		try {
			test = logInfo.get().createNode("I hit BasicAuth GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("BasicAuth/GetAllBasicAuths").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit BasicAuth GetALL successfully", "I hit BasicAuth GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for BasicAuth GetALL")
	public void i_validate_the_response_for_basicAuth_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for BasicAuth GetALL ");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("username").contains(expBasicAuthName) && js.getString("id").contains(BasicAuthid)) {
				reportInfo("'"+expBasicAuthName+"' Present in GetAll response");
				reportInfo("BasicAuth id '"+BasicAuthid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("BasicAuth userName", expBasicAuthName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit BasicAuth GetID")
	public void i_hit_basicAuth_get_id() {
		try {
			test = logInfo.get().createNode("I hit BasicAuth GetID ");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("BasicAuth/GetBasicAuth/"+BasicAuthid+"").then().extract().response();

			//  reportPayload(response.asPrettyString());

			if(response!=null)
				reportPass("Status", "I hit BasicAuth GetID successfully", "I hit BasicAuth GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	
	@Then("I validate the response for BasicAuth GetID")
	public void i_validate_the_response_for_basicAuth_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for BasicAuth GetID ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("username").equalsIgnoreCase("["+expBasicAuthName+"]")) {
				validateField("BasicAuth userName", "["+expBasicAuthName+"]",js.getString("username"));
				validateField("BasicAuth id", "["+BasicAuthid+"]",js.getString("id"));
			}
			else
				reportFail("BasicAuth userName", expBasicAuthName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Given("I hit BasicAuth PUT")
	public void i_hit_basicAuth_put() {
		try {
			test = logInfo.get().createNode("I hit BasicAuth PUT");

			String updateBasicAuthName = expBasicAuthName+"_updated";

			expBasicAuthName = updateBasicAuthName;

			String payload = "{\n"
					+ "  \"id\": "+BasicAuthid+",\n"
					+ "  \"username\": \""+updateBasicAuthName+"\",\n"
					+ "  \"password\": \"ppppppp\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:14:39.813Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"statusId\": 0\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("BasicAuth/UpdateBasicAuth").then().extract().response();

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

	@Then("I validate the response for BasicAuth PUT")
	public void i_validate_the_response_for_basicAuth_put() {
		try {
			test = logInfo.get().createNode("I validate the response for BasicAuth PUT ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("BasicAuth userName", expBasicAuthName,js.getString("username"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the BasicAuth Delete")
	public void i_hit_the_basicAuth_delete() {
		try {
			test = logInfo.get().createNode("I hit the BasicAuth Delete ");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("BasicAuth/DeleteBasicAuth/"+BasicAuthid+"").then().extract().response();

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

	@Then("I validate the response for BasicAuth GetALL after delete")
	public void i_validate_the_response_for_basicAuth_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for BasicAuth GetALL after delete ");
			JsonPath js = new JsonPath(resString);

			reportPayload(response.asPrettyString());
			
			if(js.getString("username").contains(expBasicAuthName)) {
				validateField("BasicAuth userName", expBasicAuthName,js.getString("username"));
				reportFail("Status", "BasicAuth '"+expBasicAuthName+"' should be deleted", "BasicAuth '"+expBasicAuthName+"' not deleted");
			}
			else {
				reportPass("BasicAuth userName", expBasicAuthName, "");
				reportInfo("BasicAuth userName '"+expBasicAuthName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for BasicAuth GetID after delete")
	public void i_validate_the_response_for_basicAuth_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for BasicAuth GetID after delete ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("username").equalsIgnoreCase("["+expBasicAuthName+"]")) {
				validateField("BasicAuth userName", "["+expBasicAuthName+"]",js.getString("username"));
				reportFail("Status", "BasicAuth '"+expBasicAuthName+"' should be deleted", "BasicAuth '"+expBasicAuthName+"' not deleted");
			}
			else {
				reportPass("BasicAuth userName", "[]",js.getString("username"));
				reportInfo("BasicAuth userName '"+expBasicAuthName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	
	
       ///////////////////////////////BearerAuth-----------------------------------------------------------
	
	
	@Given("I hit BearerAuth POST with token name as {string}")
	public void i_hit_bearerAuth_post_with_name_as(String tokenName) {
		try {
			test = logInfo.get().createNode("I hit BearerAuth POST with name as "+tokenName);

			expBearerAuthName = tokenName;
			
			String payload = "{\n"
					+ "    \n"
					+ "  \"token\": \""+expBearerAuthName+"\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:11:19.909Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"statusId\": 0\n"
					+ "  \n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("BearerAuth/InsertBearerAuth").then().extract().response();

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
	@Then("I validate the response for BearerAuth POST")
	public void i_validate_the_response_for_bearerAuth_post() {
		try {
			test = logInfo.get().createNode("I validate the response for BearerAuth POST ");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("BearerAuth token", expBearerAuthName,js.getString("token"));
			//System.out.println("ID= "+js.getString("id"));
			BearerAuthid=js.getString("id");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit BearerAuth GetALL")
	public void i_hit_bearerAuth_get_all() {
		try {
			test = logInfo.get().createNode("I hit BearerAuth GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("BearerAuth/GetAllBearerAuths").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit BearerAuth GetALL successfully", "I hit BearerAuth GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for BearerAuth GetALL")
	public void i_validate_the_response_for_bearerAuth_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for BearerAuth GetALL ");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("token").contains(expBearerAuthName) && js.getString("id").contains(BearerAuthid)) {
				reportInfo("'"+expBearerAuthName+"' Present in GetAll response");
				reportInfo("BearerAuth id '"+BearerAuthid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("BearerAuth token", expBearerAuthName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit BearerAuth GetID")
	public void i_hit_bearerAuth_get_id() {
		try {
			test = logInfo.get().createNode("I hit BearerAuth GetID ");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("BearerAuth/GetBearerAuth/"+BearerAuthid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit BearerAuth GetID successfully", "I hit BearerAuth GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for BearerAuth GetID")
	public void i_validate_the_response_for_bearerAuth_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for BearerAuth GetID ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("token").equalsIgnoreCase("["+expBearerAuthName+"]")) {
				validateField("BearerAuth token", "["+expBearerAuthName+"]",js.getString("token"));
				validateField("BearerAuth id", "["+BearerAuthid+"]",js.getString("id"));
			}
			else
				reportFail("BearerAuth token", expBearerAuthName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Given("I hit BearerAuth PUT")
	public void i_hit_bearerAuth_put() {
		try {
			test = logInfo.get().createNode("I hit BearerAuth PUT");

			String updateBearerToken = expBearerAuthName+"_updated";

			expBearerAuthName = updateBearerToken;

			String payload = "{\n"
					+ "  \"id\": "+BearerAuthid+",\n"
					+ "  \"token\": \""+expBearerAuthName+"\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:13:05.407Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"statusId\": 0\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("BearerAuth/UpdateBearerAuth").then().extract().response();

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

	@Then("I validate the response for BearerAuth PUT")
	public void i_validate_the_response_for_bearerAuth_put() {
		try {
			test = logInfo.get().createNode("I validate the response for BearerAuth PUT ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("BearerAuth token", expBearerAuthName,js.getString("token"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the BearerAuth Delete")
	public void i_hit_the_bearerAuth_delete() {
		try {
			test = logInfo.get().createNode("I hit the BearerAuth Delete ");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("BearerAuth/DeleteBearerAuth/"+BearerAuthid+"").then().extract().response();

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

	@Then("I validate the response for BearerAuth GetALL after delete")
	public void i_validate_the_response_for_bearerAuth_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for BearerAuth GetALL after delete ");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("token").contains(expBearerAuthName)) {
				validateField("BearerAuth token", expBearerAuthName,js.getString("token"));
				reportFail("Status", "BearerAuth '"+expBearerAuthName+"' should be deleted", "BearerAuth '"+expBearerAuthName+"' not deleted");
			}
			else {
				reportPass("BearerAuth token", expBearerAuthName, "");
				reportInfo("BearerAuth token '"+expBearerAuthName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for BearerAuth GetID after delete")
	public void i_validate_the_response_for_bearerAuth_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for BearerAuth GetID after delete ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("token").equalsIgnoreCase("["+expBearerAuthName+"]")) {
				validateField("BearerAuth token", "["+expBearerAuthName+"]",js.getString("token"));
				reportFail("Status", "BearerAuth '"+expBearerAuthName+"' should be deleted", "BearerAuth '"+expBearerAuthName+"' not deleted");
			}
			else {
				reportPass("BearerAuth token", "[]",js.getString("token"));
				reportInfo("BearerAuth token '"+expBearerAuthName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	
	
	///////////////////////////////CustomAuth-----------------------------------------------------------------------------------------
	


	@Given("I hit CustomAuth POST with name as {string}")
	public void i_hit_customAuth_post_with_name_as(String name) {
		try {
			test = logInfo.get().createNode("I hit CustomAuth POST with name as "+name);

			expCustomAuthName= name;
			
			String payload = "{\n"
					+ "  \"headers\": \""+expCustomAuthName+"\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:20:05.109Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"statusId\": 0\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("CustomAuth/InsertCustomAuth").then().extract().response();

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
	@Then("I validate the response for CustomAuth POST")
	public void i_validate_the_response_for_customAuth_post() {
		try {
			test = logInfo.get().createNode("I validate the response for CustomAuth POST ");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("CustomAuth headers", expCustomAuthName,js.getString("headers"));
			//System.out.println("ID= "+js.getString("id"));
			CustomAuthid=js.getString("id");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit CustomAuth GetALL")
	public void i_hit_customAuth_get_all() {
		try {
			test = logInfo.get().createNode("I hit CustomAuth GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("CustomAuth/GetAllCustomAuths").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit CustomAuth GetALL successfully", "I hit CustomAuth GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for CustomAuth GetALL")
	public void i_validate_the_response_for_customAuth_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for CustomAuth GetALL ");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("headers").contains(expCustomAuthName) && js.getString("id").contains(CustomAuthid)) {
				reportInfo("'"+expCustomAuthName+"' Present in GetAll response");
				reportInfo("CustomAuth id '"+CustomAuthid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("CustomAuth headers", expCustomAuthName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit CustomAuth GetID")
	public void i_hit_customAuth_get_id() {
		try {
			test = logInfo.get().createNode("I hit CustomAuth GetID ");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("CustomAuth/GetCustomAuth/"+CustomAuthid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit CustomAuth GetID successfully", "I hit CustomAuth GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for CustomAuth GetID")
	public void i_validate_the_response_for_customAuth_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for CustomAuth GetID ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("headers").equalsIgnoreCase("["+expCustomAuthName+"]")) {
				validateField("CustomAuth headers", "["+expCustomAuthName+"]",js.getString("headers"));
				validateField("CustomAuth id", "["+CustomAuthid+"]",js.getString("id"));
			}
			else
				reportFail("CustomAuth headers", expCustomAuthName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Given("I hit CustomAuth PUT")
	public void i_hit_customAuth_put() {
		try {
			test = logInfo.get().createNode("I hit CustomAuth PUT");

			String updateBearerToken = expCustomAuthName+"_updated";

			expCustomAuthName = updateBearerToken;

			String payload = "{\n"
					+ "  \"id\": "+CustomAuthid+",\n"
					+ "  \"headers\": \""+expCustomAuthName+"\",\n"
					+ "  \"schemeId\": "+Schemeid+",\n"
					+ "  \"modifiedBy\": 1,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:20:05.109Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"statusId\": 0\n"
					+ "\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("CustomAuth/UpdateCustomAuth").then().extract().response();

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

	@Then("I validate the response for CustomAuth PUT")
	public void i_validate_the_response_for_customAuth_put() {
		try {
			test = logInfo.get().createNode("I validate the response for CustomAuth PUT ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("CustomAuth headers", expCustomAuthName,js.getString("headers"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the CustomAuth Delete")
	public void i_hit_the_customAuth_delete() {
		try {
			test = logInfo.get().createNode("I hit the CustomAuth Delete ");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("CustomAuth/DeleteCustomAuth/"+CustomAuthid+"").then().extract().response();

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

	@Then("I validate the response for CustomAuth GetALL after delete")
	public void i_validate_the_response_for_customAuth_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for CustomAuth GetALL after delete ");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("headers").contains(expCustomAuthName)) {
				validateField("CustomAuth headers", expCustomAuthName,js.getString("headers"));
				reportFail("Status", "CustomAuth '"+expCustomAuthName+"' should be deleted", "CustomAuth '"+expCustomAuthName+"' not deleted");
			}
			else {
				reportPass("CustomAuth headers", expCustomAuthName, "");
				reportInfo("CustomAuth headers '"+expCustomAuthName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for CustomAuth GetID after delete")
	public void i_validate_the_response_for_customAuth_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for CustomAuth GetID after delete ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("headers").equalsIgnoreCase("["+expCustomAuthName+"]")) {
				validateField("CustomAuth headers", "["+expCustomAuthName+"]",js.getString("headers"));
				reportFail("Status", "CustomAuth '"+expCustomAuthName+"' should be deleted", "CustomAuth '"+expCustomAuthName+"' not deleted");
			}
			else {
				reportPass("CustomAuth headers", "[]",js.getString("headers"));
				reportInfo("CustomAuth headers '"+expCustomAuthName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}



	///////////////////////////////Employee-------------------------------------------------------------------------


	@Given("I hit Employee POST with name as {string}")
	public void i_hit_employee_post_with_name_as(String name) {
		try {
			test = logInfo.get().createNode("I hit Employee POST with name as "+name);

			expEmployeeName= name;
			
			String payload = "{\n"
					+ "  \"employeeName\": \""+expEmployeeName+"\",\n"
					+ "  \"gender\": \"Male\",\n"
					+ "  \"city\": \"Mumbai\",\n"
					+ "  \"designation\": \"TL\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-16T07:32:20.520Z\",\n"
					+ "  \"departmentId\": "+Departmentid+"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("Employee/InsertEmployee").then().extract().response();

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
	@Then("I validate the response for Employee POST")
	public void i_validate_the_response_for_employee_post() {
		try {
			test = logInfo.get().createNode("I validate the response for Employee POST ");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("employeeName", expEmployeeName,js.getString("employeeName"));
			//System.out.println("ID= "+js.getString("id"));
			Employeeid=js.getString("id");
			
			//Employee delete method exist so i need to update employee id
		//	DeptEmpid1=Employeeid;
			
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Employee GetALL")
	public void i_hit_employee_get_all() {
		try {
			test = logInfo.get().createNode("I hit Employee GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Employee/GetAllEmployees").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit Employee GetALL successfully", "I hit Employee GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for Employee GetALL")
	public void i_validate_the_response_for_employee_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for Employee GetALL ");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("employeeName").contains(expEmployeeName) && js.getString("id").contains(Employeeid)) {
				reportInfo("'"+expEmployeeName+"' Present in GetAll response");
				reportInfo("Employee id '"+Employeeid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("employeeName", expEmployeeName, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Employee GetID")
	public void i_hit_employee_get_id() {
		try {
			test = logInfo.get().createNode("I hit Employee GetID ");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Employee/GetEmployee/"+Employeeid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit Employee GetID successfully", "I hit Employee GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");


			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for Employee GetID")
	public void i_validate_the_response_for_employee_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for Employee GetID ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("employeeName").equalsIgnoreCase("["+expEmployeeName+"]")) {
				validateField("EmployeeName", "["+expEmployeeName+"]",js.getString("employeeName"));
				validateField("Employee id", "["+Employeeid+"]",js.getString("id"));
			}
			else
				reportFail("employeeName", expEmployeeName, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Given("I hit Employee PUT")
	public void i_hit_employee_put() {
		try {
			test = logInfo.get().createNode("I hit Employee PUT");

			String updatedEmployeeName = expEmployeeName+"_updated";

			expEmployeeName = updatedEmployeeName;

			String payload = "{\n"
					+ "  \"id\": "+Employeeid+",\n"
					+ "  \"employeeName\": \""+expEmployeeName+"\",\n"
					+ "  \"gender\": \"Male\",\n"
					+ "  \"city\": \"Mumbai\",\n"
					+ "  \"designation\": \"TL\",\n"
					+ "  \"createdBy\": 1,\n"
					+ "  \"createdOn\": \"2023-02-16T07:32:20.520Z\",\n"
					+ "  \"departmentId\": "+Departmentid+"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("Employee/UpdateEmployee").then().extract().response();

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

	@Then("I validate the response for Employee PUT")
	public void i_validate_the_response_for_employee_put() {
		try {
			test = logInfo.get().createNode("I validate the response for Employee PUT ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("employeeName", expEmployeeName,js.getString("employeeName"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the Employee Delete")
	public void i_hit_the_employee_delete() {
		try {
			test = logInfo.get().createNode("I hit the Employee Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Employee/DeleteEmployee/"+Employeeid+"").then().extract().response();

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

	@Then("I validate the response for Employee GetALL after delete")
	public void i_validate_the_response_for_employee_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Employee GetALL after delete ");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("employeeName").contains(expEmployeeName)) {
				validateField("employeeName", expEmployeeName,js.getString("employeeName"));
				reportFail("Status", "Employee '"+expEmployeeName+"' should be deleted", "Employee '"+expEmployeeName+"' not deleted");
			}
			else {
				reportPass("employeeName", expEmployeeName, "");
				reportInfo("employeeName '"+expEmployeeName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for Employee GetID after delete")
	public void i_validate_the_response_for_employee_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Employee GetID after delete ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("employeeName").equalsIgnoreCase("["+expEmployeeName+"]")) {
				validateField("employeeName", "["+expEmployeeName+"]",js.getString("employeeName"));
				reportFail("Status", "Employee '"+expEmployeeName+"' should be deleted", "Employee '"+expEmployeeName+"' not deleted");
			}
			else {
				reportPass("employeeName", "[]",js.getString("employeeName"));
				reportInfo("employeeName '"+expEmployeeName+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}


	
	
	///////////////////////////////Destination-------------------------------------------------------------------------------
	

	@Given("I hit Destination POST with name as {string}")
	public void i_hit_Destination_post_with_name_as(String url) {
		try {
			test = logInfo.get().createNode("I hit Destination POST with name as "+url);

			expDestinationUrl= url;
			
			String payload = "{\n"
					+ "  \"destinationURL\": \""+expDestinationUrl+"\",\n"
					+ "  \"destinationPayload\": \"string\",\n"
					+ "  \"modifiedBy\": 0,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:27:40.805Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"sourceId\": "+Sourceid+",\n"
					+ "  \"statusId\": 0,\n"
					+ "  \"methodId\": 1,\n"
					+ "  \"destinationPayloadMapping\": \"string\",\n"
					+ "  \"urlParameter\": \"string\"\n"
					+ "}"; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.post("Destination/InsertDestination").then().extract().response();

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
	@Then("I validate the response for Destination POST")
	public void i_validate_the_response_for_Destination_post() {
		try {
			test = logInfo.get().createNode("I validate the response for Destination POST ");

			reportPayload(response.asPrettyString());
			JsonPath js = new JsonPath(resString);

			validateField("destinationURL", expDestinationUrl,js.getString("destinationURL"));
			//System.out.println("ID= "+js.getString("id"));
			Destinationid=js.getString("id");
			
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Destination GetALL")
	public void i_hit_Destination_get_all() {
		try {
			test = logInfo.get().createNode("I hit Destination GetALL");
			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Destination/GetAllDestinations").then().extract().response();
			
			if(response!=null)
				reportPass("Status", "I hit Destination GetALL successfully", "I hit Destination GetALL successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");
			
		//	reportPayload(response.asPrettyString());

			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@Then("I validate the response for Destination GetALL")
	public void i_validate_the_response_for_Destination_get_all() {
		try {
			test = logInfo.get().createNode("I validate the response for Destination GetALL ");

			reportPayload(response.asPrettyString());
			
			JsonPath js = new JsonPath(resString);

			if(js.getString("destinationURL").contains(expDestinationUrl) && js.getString("id").contains(Destinationid)) {
				reportInfo("'"+expDestinationUrl+"' Present in GetAll response");
				reportInfo("Destination id '"+Destinationid+"' Present in GetAll response");
				//validateField("Scheme Name", expSchemeMethodName,js.getString("schemeName"));
			}
			else
				reportFail("destinationURL", expDestinationUrl, "");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit Destination GetID")
	public void i_hit_Destination_get_id() {
		try {
			test = logInfo.get().createNode("I hit Destination GetID ");

			response = given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					get("Destination/GetDestination/"+Destinationid+"").then().extract().response();

		//	reportPayload(response.asPrettyString());
			
			if(response!=null)
				reportPass("Status", "I hit Destination GetID successfully", "I hit Destination GetID successfully");
			else
				reportFail("Status", "Request Not sent", "Request Not sent");


			resString =response.asPrettyString();
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Then("I validate the response for Destination GetID")
	public void i_validate_the_response_for_Destination_get_id() {
		try {
			test = logInfo.get().createNode("I validate the response for Destination GetID ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("destinationURL").equalsIgnoreCase("["+expDestinationUrl+"]")) {
				validateField("destinationURL", "["+expDestinationUrl+"]",js.getString("destinationURL"));
				validateField("Destination id", "["+Destinationid+"]",js.getString("id"));
			}
			else
				reportFail("destinationURL", expDestinationUrl, "not found");
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}
	@Given("I hit Destination PUT")
	public void i_hit_Destination_put() {
		try {
			test = logInfo.get().createNode("I hit Destination PUT");

			String updatedDestinationURL ="http://www.rediffmail.com";

			expDestinationUrl = updatedDestinationURL;

			String payload = "{\n"
					+ "  \"id\": "+Destinationid+",\n"
					+ "  \"destinationURL\": \""+expDestinationUrl+"\",\n"
					+ "  \"destinationPayload\": \"xxxx\",\n"
					+ "  \"modifiedBy\": 0,\n"
					+ "  \"modifiedOn\": \"2023-02-16T07:27:40.805Z\",\n"
					+ "  \"authId\": "+Authid+",\n"
					+ "  \"sourceId\": "+Sourceid+",\n"
					+ "  \"statusId\": 0,\n"
					+ "  \"methodId\": 1,\n"
					+ "  \"destinationPayloadMapping\": \"xxxx\",\n"
					+ "  \"urlParameter\": \"xxxx\"\n"
					+ "}\n"
					+ ""; 

			response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
					.put("Destination/UpdateDestination").then().extract().response();

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

	@Then("I validate the response for Destination PUT")
	public void i_validate_the_response_for_Destination_put() {
		try {
			test = logInfo.get().createNode("I validate the response for Destination PUT ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			validateField("destinationURL", expDestinationUrl,js.getString("destinationURL"));
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}

	@When("I hit the Destination Delete")
	public void i_hit_the_Destination_delete() {
		try {
			test = logInfo.get().createNode("I hit the Destination Delete");

			response=given().relaxedHTTPSValidation().baseUri(baseURI).and().when().
					delete("Destination/DeleteDestination/"+Destinationid+"").then().extract().response();

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

	@Then("I validate the response for Destination GetALL after delete")
	public void i_validate_the_response_for_Destination_get_all_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Destination GetALL after delete ");
			JsonPath js = new JsonPath(resString);
			
			reportPayload(response.asPrettyString());

			if(js.getString("id").contains(Destinationid)){///Destination url not unique so i use id to confirm
				validateField("destinationURL", expDestinationUrl,js.getString("destinationURL"));
				reportFail("Status", "Destination '"+expDestinationUrl+"' should be deleted", "Destination '"+expDestinationUrl+"' not deleted");
			}
			else {
				reportPass("destinationURL", expDestinationUrl, "");
				reportInfo("destinationURL '"+expDestinationUrl+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}

	}

	@Then("I validate the response for Destination GetID after delete")
	public void i_validate_the_response_for_Destination_get_id_after_delete() {
		try {
			test = logInfo.get().createNode("I validate the response for Destination GetID after delete ");

			reportPayload(response.asPrettyString());

			JsonPath js = new JsonPath(resString);

			if(js.getString("destinationURL").equalsIgnoreCase("["+expDestinationUrl+"]")) {
				validateField("destinationURL", "["+expDestinationUrl+"]",js.getString("destinationURL"));
				reportFail("Status", "Destination '"+expDestinationUrl+"' should be deleted", "Destination '"+expDestinationUrl+"' not deleted");
			}
			else {
				reportPass("destinationURL", "[]",js.getString("destinationURL"));
				reportInfo("destinationURL '"+expDestinationUrl+"' deleted succesfully");
			}
		}
		catch (Exception e) {
			testStepHandle("FAIL", test, e);
		}
	}


	
////------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////ApiMethod

	/*	@Given("I hit ApiMethod POST with name as {string}")
	public void i_hit_api_method_post_with_name_as(String name) {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit ApiMethod POST with name as "+name);
		expAPIMethodName = name;
		String payload = "{\n"
				+ "\n"
				+ "  \"methodName\": \"TestMethod_Ravi\",\n"
				+ "  \"createdBy\": 1,\n"
				+ "  \"createdOn\": \"2023-02-16T07:05:05.335Z\",\n"
				+ "  \"statusId\": 0\n"
				+ "}"; 

		response = given().relaxedHTTPSValidation().contentType(ContentType.JSON).baseUri(baseURI).and().when().body(payload)
				.post("ApiMethod/InsertApiMethod").then().extract().response();

		resString =response.asPrettyString();

		if(response!=null)
			reportPayload(payload);
		else
			reportFail("Status", "Request Not sent", "Request Not sent");

	}
	@Then("I verify the status code as {string}")
	public void i_verify_the_status_code_as(String string) {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I verify the status code as "+string);
		reportInfo("Status Code "+response.statusCode());
		validateField("Status Code", "200", String.valueOf(response.statusCode()));

	}
	@Then("I validate the response for ApiMethod POST")
	public void i_validate_the_response_for_api_method_post() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I validate the response for ApiMethod POST");
		reportPayload(response.asPrettyString());
		JsonPath js = new JsonPath(resString);

		validateField("Method Name", expAPIMethodName,js.getString("methodName"));

	}
	@When("I hit ApiMethod GetALL")
	public void i_hit_api_method_get_all() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit ApiMethod GetALL");
	}
	@Then("I validate the response for ApiMethod GetALL")
	public void i_validate_the_response_for_api_method_get_all() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I validate the response for ApiMethod GetALL");
	}
	@When("I hit ApiMethod GetID")
	public void i_hit_api_method_get_id() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit  ApiMethod GetID");
	}
	@Then("I validate the response for ApiMethod GetID")
	public void i_validate_the_response_for_api_method_get_id() {
		// Write code here that turns the phrase above into concrete actions

		test = logInfo.get().createNode("I validate the response for ApiMethod GetID");

	}
	@Given("I hit ApiMethod PUT")
	public void i_hit_api_method_put() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit ApiMethod PUT");
	}
	@Then("I validate the response for ApiMethod PUT")
	public void i_validate_the_response_for_api_method_put() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I validate the response for ApiMethod PUT");
	}

	@When("I hit the ApiMethod Delete")
	public void i_hit_the_api_method_delete() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I hit the ApiMethod Delete");

	}


	@Then("I validate the response for ApiMethod GetALL after delete")
	public void i_validate_the_response_for_api_method_get_all_after_delete() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I validate the response for ApiMethod GetALL after delete");

	}
	@Then("I validate the response for ApiMethod GetID after delete")
	public void i_validate_the_response_for_api_method_get_id_after_delete() {
		// Write code here that turns the phrase above into concrete actions
		test = logInfo.get().createNode("I validate the response for ApiMethod GetID after delete");

	}*/

}
