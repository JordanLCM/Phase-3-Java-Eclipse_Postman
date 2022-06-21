package SimP_Steps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimP_DeleteRequest {
	RequestSpecification Request;
	Response GetResponse;
	String SubPath = "";
	Logger Logger = LogManager.getLogger(SimP_DeleteRequest.class.getSimpleName());
	@io.cucumber.java.Before
	public void Configure() {
		PropertyConfigurator.configure("log4j.properties");
	}
	@Given("A base URL004 {string}")
	public void a_base_url004(String URL004) {
		RestAssured.baseURI = URL004;
		Request = RestAssured.given();
		System.out.println("--------------------------------------------------------------");
		Logger.info("CLASSNAME = " + SimP_DeleteRequest.class.getName());
		Logger.info("Step 001 ==> URL = " + URL004);
	}
	@Given("path004 is {string}")
	public void path004_is(String Path004) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 002 ==> PATH = " + Path004);
		SubPath = Path004;
	}
	@Then("User sends a DELETE request")
	public void user_sends_a_delete_request() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 003 ==> DELETE REQUEST");
		GetResponse = Request.delete(SubPath);
	}
	@Then("prints response004")
	public void prints_response004() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 004 ==> PRINT RESPONSE");
		GetResponse.then().log().body();
	}
	@Then("status code004 is equal to {int}")
	public void status_code004_is_equal_to(Integer SC004) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 005 ==> STATUS CODE");
		Response Response001 = Request.when().delete(SubPath);
		Response001.then().statusCode(204);
		int StatusC = GetResponse.statusCode();
		String StatusL = GetResponse.statusLine();
		if (SC004.equals(StatusC)) {
			Logger.info("Response code = " + StatusC + " // " + StatusL);
			Logger.info("DELETE REQUEST PASSED!!!");
		} else {
			Logger.error("ERROR!!! RECEIVED RESPONSE CODE IS = " + StatusC);
		}
	}
}
