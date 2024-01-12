
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;

public class Courses {
	
	@Test
	public static void coursesSum() {
		
//		Verify if Sum of all Course prices matches with Purchase Amount
		JsonPath js = new JsonPath(Payload.CoursesJson());
		int noOfCourses=js.getInt("courses.size()");
		int actualSum=0;
		
		for(int j=0;j<noOfCourses;j++) 
		{
			int prices1=js.getInt("courses["+j+"].price");
			int copies1=js.get("courses["+j+"].copies");
			System.out.println(prices1);
			System.out.println(copies1);
			actualSum=actualSum+(prices1*copies1);
			
			
		}
		
		System.out.println(actualSum);
		Assert.assertEquals(actualSum, js.getInt("dashboard.purchaseAmount"));
		
		
	}
	}


