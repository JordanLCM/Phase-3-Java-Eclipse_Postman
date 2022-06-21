package SimP_Steps;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimP_PutRequest {
	RequestSpecification Request003;
	Response GetResponse;
	String SubPath = "";
	Logger Logger = LogManager.getLogger(SimP_PutRequest.class.getSimpleName());
	@io.cucumber.java.Before
	public void Configure() {
		PropertyConfigurator.configure("log4j.properties");
	}
	@Given("A base URL003 {string}")
	public void a_base_url003(String URL003) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("CLASSNAME = " + SimP_PutRequest.class.getName());
		Logger.info("Step 001 ==> URL = " + URL003);
		RestAssured.baseURI = URL003;
		Request003 = RestAssured.given();
	}
	@And("path003 is {string}")
	public void path003_is(String Path003) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 002 ==> PATH = " + Path003);
		SubPath = Path003;
	}
	@Then("User sends a PUT request")
	public void user_sends_a_put_request() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 003 ==> Put request");
		GetResponse = Request003.put(SubPath);
	}
	@And("payload003 file is {string}")
	public void payload003_file_is(String JsonF002) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 004 ==> JSON FILE");
		String proPath = System.getProperty("user.dir");
		String filePath = proPath + "/" + JsonF002;
		System.out.println("File = " + filePath);
		File JsonFX = new File(filePath);
		System.out.println("File exists = " + JsonFX.exists());
		Request003.body(JsonF002);
	}
	@Then("prints response003")
	public void prints_response003() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 005 ==> PRINT RESPONSE");
		GetResponse.then().log().body();
	}
	@Then("status code003 is equal to {int}")
	public void status_code003_is_equal_to(Integer SC003) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 006 ==> STATUS CODE");
		GetResponse.then().statusCode(SC003);
		Response Response001 = Request003.when().put(SubPath);
		Response001.then().statusCode(200);
		int StatusC = Response001.getStatusCode();
		String StatusL = Response001.getStatusLine();
		Logger.info("Response code = " + StatusC + " // " + StatusL);
		if (SC003.equals(StatusC)) {
			Logger.info("RESPONSE CODE PASSED!");
		} else {
			Logger.error("ERROR");
		}
	}

}
