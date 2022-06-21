package SimP_Steps;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimP_PostRequest {
	RequestSpecification Request002;
	Response GetResponse;
	String SubPath = "";
	Logger Logger = LogManager.getLogger(SimP_PostRequest.class.getSimpleName());
	@io.cucumber.java.Before
	public void Configure() {
		PropertyConfigurator.configure("log4j.properties");
	}
	@Given("A base URL002 {string}")
	public void a_base_url002(String URL002) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("CLASSNAME = " + SimP_PostRequest.class.getName());
		Logger.info("Step 001 ==> URL = " + URL002);
		RestAssured.baseURI = URL002;
		Request002 = RestAssured.given();
	}
	@And("path002 is {string}")
	public void path002_is(String Path002) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 002 ==> PATH = " + Path002);
		SubPath = Path002;
	}
	@When("User sends a post request")
	public void user_sends_a_post_request() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 003 ==> Post request");
		GetResponse = Request002.post(SubPath);
	}
	@And("payload002 file is {string}")
	public void payload002_file_is(String JsonF) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 004 ==> JSON FILE");
		String proPath = System.getProperty("user.dir");
		String filePath = proPath + "/" + JsonF;
		System.out.println("File = " + filePath);
		File JsonF001 = new File(filePath);
		System.out.println("File exists = " + JsonF001.exists());
		Request002.body(JsonF);
	}
	@Then("prints response002")
	public void prints_response002() {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 005 ==> PRINT RESPONSE");
		GetResponse.then().log().body();
	}
	@Then("status code002 is equal to {int}")
	public void status_code002_is_equal_to(Integer SC002) {
		System.out.println("--------------------------------------------------------------");
		Logger.info("Step 006 ==> STATUS CODE");
		GetResponse.then().statusCode(SC002);
		Response Response001 = Request002.when().post(SubPath);
		Response001.then().statusCode(201);
		int StatusC = Response001.getStatusCode();
		String StatusL = Response001.getStatusLine();
		Logger.info("Response code = " + StatusC + " // " + StatusL);
		if (SC002.equals(StatusC)) {
			Logger.info("RESPONSE CODE PASSED!");
		} else {
			Logger.error("ERROR");
		}
	}
}
