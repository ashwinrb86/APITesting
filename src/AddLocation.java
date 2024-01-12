import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethods;


public class AddLocation {

	public static void main(String[] args) {
		
		
		//given - inputs
		//when - submit API http method and resource
		//then - validate result
		
		//Add Place
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		String response =  given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").body(Payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeid = js.get("place_id");
		System.out.println(placeid);
		
		//Update Place
		
		String newAddress="TCS Banglore Global Axis";
		
		given().queryParam("key","qaclick123").header("Content-Type", "application/json").body("{\n"
				+ "\"place_id\":\""+placeid+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("/maps/api/place/update/json/")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceResponse = given().queryParam("key","qaclick123").queryParam("place_id",placeid)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getPlaceResponse);
		
		
		
		JsonPath js1= ReusableMethods.rawToJson(getPlaceResponse);
		String address = js1.get("address");
		
		if (address.equalsIgnoreCase(newAddress))
		{
			System.out.println("Test Successful");
		}
		Assert.assertEquals(address, newAddress);
		
		
		// TODO Auto-generated method stub

	}

}
