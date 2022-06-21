package SimP_Steps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import SimP_Steps.SimP_GetRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

public class SimP_GetRequest {
	RequestSpecification Request;
	Response GetResponse;
	String SubPath = "";
	Logger Logger = LogManager.getLogger(SimP_GetRequest.class.getSimpleName());
	@io.cucumber.java.Before
	public void Configure() {
		PropertyConfigurator.configure("log4j.properties");
	}
	@Given("A base URL {string}")
	public void a_base_url(String URL001) {
		RestAssured.baseURI = URL001;
		Request = RestAssured.given();
		System.out.println("--------------------------------------------------------------");
		Logger.info("CLASSNAME = " + SimP_GetRequest.class.getSimpleName());
		Logger.info("Step 001 ==> URL = " + URL001);
	}
	@Given("path is {string}")
	public void path_is(String Path001) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 002 ==> PATH = " + Path001);
		SubPath = Path001;
	}
	@Then("User sends a GET request")
	public void user_sends_a_get_request() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 003 ==> GET REQUEST");
		GetResponse = Request.get(SubPath);
	}
	@Then("prints response")
	public void prints_response() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 004 ==> PRINT RESPONSE");
		GetResponse.then().log().body();
	}
	@Then("status code is equal to {int}")
	public void status_code_is_equal_to(Integer SC001) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 005 ==> STATUS CODE");
		Response Response001 = Request.when().get(SubPath);
		Response001.then().statusCode(200);
		int StatusC = Response001.getStatusCode();
		String StatusL = Response001.getStatusLine();
		Logger.info("Response code = " + StatusC + " // " + StatusL);
		if (SC001.equals(StatusC)) {
			Logger.info("RESPONSE CODE PASSED!");
		} else {
			Logger.error("ERROR!!! RECEIVED RESPONSE CODE IS = " + StatusC);
		}
	}
}