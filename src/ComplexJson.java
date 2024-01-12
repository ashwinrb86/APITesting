/*



1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount

*/

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;


public class ComplexJson {
	
	public static void main(String[] args) {
		
		
//		Print No of courses returned by API
		JsonPath js = new JsonPath(Payload.CoursesJson());
		int noOfCourses=js.getInt("courses.size()");
		System.out.println(noOfCourses);
		
//		Print Purchase Amount		
		String purchaseAmount=js.get("dashboard.purchaseAmount").toString();
		System.out.println(purchaseAmount);
		
//		Print Title of the first course
		String firstTitle=js.get("courses[0].title");
		System.out.println(firstTitle);
		
//		Print All course titles and their respective Prices
		
		for(int i=0;i<noOfCourses;i++) 
		{
			String title=js.get("courses["+i+"].title");
			System.out.println(title);
		}
		
//		Print no of copies sold by RPA Course
		
		for(int i=0;i<noOfCourses;i++) 
		{
			String title1=js.get("courses["+i+"].title");
			if (title1.equalsIgnoreCase("RPA")) 
			{
				String copies=js.get("courses["+i+"].copies").toString();
				System.out.println(copies);
			}
		}
		
		
		
	}

}
