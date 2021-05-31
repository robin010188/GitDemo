package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleplaceAPI;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuilder;
import resources.Utils;

public class StepDefination extends Utils {
	
	
	RequestSpecification reqbuilt;
	ResponseSpecification resp;
	Response response;
	TestDataBuilder data=new TestDataBuilder();
	 static String place_id;

	@Given("Add place payload {string} {string}  {string}")
	public void add_place_payload(String name, String address, String language) throws IOException {
	     
//	    System.out.println("given print");
		
		reqbuilt=given().spec(requestSpecification()).body(data.AddPlacePayload(name,address,language));
	    
	}

	@When("user calls {string} API with {string} HTTP request")
	public void user_calls_api_with_http_request(String resource, String method) {
	    
//		System.out.println("when print");
		
		ApiResources resourceAPI=ApiResources.valueOf(resource);
//		System.out.println(resourceAPI.getResource());
		String resourceValue=resourceAPI.getResource();
		System.out.println("Resource url fethed from API resource file for " + resource + " is: "+resourceValue);
		
		resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("post"))
		{
			response=reqbuilt.when().post(resourceValue);
		}
		
		else if(method.equalsIgnoreCase("get"))
		{
			response=reqbuilt.when().get(resourceValue);
		}
		
		
//		response=reqbuilt.when().post(resourcevalue)
//		.then().spec(resp).extract().response();

	}

	@Then("the API call got success with Status Code {int}")
	public void the_API_call_got_success_with_Status_Code(Integer int1) {

//		System.out.println("then print");
		System.out.println("Status code extracted is: " + response.getStatusCode());
		assertEquals(response.getStatusCode(),200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
//		System.out.println("and print");
		String respString=response.asString();
		JsonPath js=new JsonPath(respString);
		System.out.println("value of " + keyValue + " is: " + js.get(keyValue).toString());
//		assertEquals(js.get(keyValue).toString(),expectedValue);
		assertEquals(getJsonPath(response, keyValue),expectedValue);
//	    throw new cucumber.api.PendingException();
	}
	
	@Then("verify place_id created maps to {string} using {string} API")
	public void verify_place_id_created_maps_to_using_api(String expectedname, String resource) throws IOException {
	
		place_id=getJsonPath(response, "place_id");
		reqbuilt=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_api_with_http_request(resource, "GET");
		String actualname =getJsonPath(response,"name");
		assertEquals(actualname,expectedname);
		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		reqbuilt=given().spec(requestSpecification()).body(data.DeletePlacePayload(place_id));
	   
	
		
	}
	
}
