package APITesting.com.org.apis;

import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.classes.Posts;

import static com.jayway.restassured.RestAssured.*;

public class JsonServerRequest {
	
	//GET /Posts
	
	//@Test
	public void Test_01() {
	
	Response resp = given().
	when().
	get("http://localhost:3000/posts");
	
	System.out.println("Get response is  " + resp.asString());
		
	}
	
	
	//POST /posts
	
	//@Test
	public void Test_02() {
	
	Response resp = given().
	body(" {\"id\" : \"02\" , "
			+ "\"title\" : \"Dummy title\" , "
			+ "\"author\" : \"nishadi\"} ").
	when().
	contentType(ContentType.JSON).
	post("http://localhost:3000/posts");
	
	System.out.println("Post response is : " + resp.asString() );
	
	}
	
	
	//POST /posts    ---using post objects
	
	@Test
	public void Test_03() {
		
		Posts posts = new Posts();
		
		posts.setId("03");
		posts.setTitle("Posts request by object");
		posts.setAuthor("Jayasinghe");
		
		Response resp = given().
		when().
		contentType(ContentType.JSON).
		body(posts).
		post("http://localhost:3000/posts");
		
		System.out.println("Response " + resp.asString());
		
	}

}
