
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethods;
import POJO.GetCourse;




public class OAuth {

	@Test
	public static void  auth() {
		
		String response = given().log().all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String accToken=js.get("access_token");
		System.out.println(accToken);
		
		
		GetCourse gc =given().log().all().header("Content-Type", "application/json").queryParam("access_token",accToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().extract().response().as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		
		//
		
		
		
	}
}
