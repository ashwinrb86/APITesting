import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethods;

public class LibAddBook {
	
	@Test(dataProvider="BooksData")
	public static void AddBook(String isbn, String aisle) {
		
		
		RestAssured.baseURI ="http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json").body(Payload.AddbookPayload(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String ID=js.get("ID");
		System.out.println(ID);
		
		given().log().all().header("Content-Type", "application/json").body(Payload.DeletebookPayload(ID)).
		when().delete("/Library/DeleteBook.php").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted")).extract().response();
		
	}
//	
//	@Test
//	public static void DeletePlace(String ID) {
//		
//		;
//		
//	}
	
	
	
	@DataProvider(name="BooksData")
	
	
	public Object[][] getData() {
		
		return new Object[][] {{"aaaa","111"}};
		//,{"bbbb","222"},{"cccc","333"}
	}

}
