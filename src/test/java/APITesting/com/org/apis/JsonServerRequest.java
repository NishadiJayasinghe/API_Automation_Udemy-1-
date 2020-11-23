package APITesting.com.org.apis;

import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.classes.Info;
import APITesting.com.org.classes.Posts;
import APITesting.com.org.classes._posts;
import APITesting.com.org.classes.AdvancedExample._Info;
import APITesting.com.org.classes.AdvancedExample._Posts;
import static org.hamcrest.Matchers.lessThan;

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
	
	
	//GET /posts/3
	
		//@Test
		public void Test_02() {
			
			Response resp = given().
					when().
					get("http://localhost:3000/posts/03");
		
			System.out.println(resp.asString());
		
		}
	
	
	//POST /posts
	
	//@Test
	public void Test_03() {
	
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
	
	//@Test
	public void Test_04() {
		
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
	
	
	//PUT /posts/03
	
	//@Test
	public void Test_05() {
	
		Posts posts = new Posts();
		posts.setId("03");
		posts.setAuthor("Jayasinghe");
		posts.setTitle("PUT request by object");
		
		Response resp = given().
				when().
				contentType(ContentType.JSON).
				body(posts).
				put("http://localhost:3000/posts/03");
		
		System.out.println("Response of PUT Request : " + resp.asString() );
		
	}
	
	
	//PATCH /posts/1
	
	//@Test
	public void Test_06() {
		
		Response resp = given().
				body("{ \"author\" : \"Erangika\" } ").
				when().
				contentType(ContentType.JSON).
				patch("http://localhost:3000/posts/03");
		
		System.out.println("Patch Updated response : " + resp.asString());
		
	}
	
	//DELETE /posts/03
	
	//@Test
	public void Test_07() {
		
		Response resp = given().
		when().
		delete("http://localhost:3000/posts/03");
	
		System.out.println("Delete request : " + resp.asString());
	
	}
	
	//Complex post request
	//POST /posts
	
	//@Test
	public void Test_08(){
		
		Info info = new Info();
		info.setEmail("jayanghjej@gmail.com");
		info.setPhone("12345566");
		info.setAddress("Sri Lanka");
		
		
		_posts posts = new _posts();
		posts.setId("02");
		posts.setTitle("complex posts");
		posts.setAuthor("vibhavi");
		posts.setInfo(info);
		
		Response resp = given().
		when().
		contentType(ContentType.JSON).
		body(posts).
		post("http://localhost:3000/posts");
		
		System.out.println("Complex post request response : " + resp.asString() );
	}
	
	//Complex POST request
	//POST /posts
	
	//@Test
	public void Test_09() {
		
		_Info info1 = new _Info();
		info1.setEmail("Test email 1");
		info1.setPhone("Test phone 1");
		info1.setAddress("Test address 1");
		
		
		_Info info2 = new _Info();
		info2.setEmail("Test email 2");
		info2.setPhone("Test phone 2");
		info2.setAddress("Test address 2");

		_Posts posts = new _Posts();
		posts.setId("2");
		posts.setTitle("title");
		posts.setAuthor("Author");
		posts.setInfo(new _Info[] {info1, info2});

		Response resp = given().
				when().
				contentType(ContentType.JSON).
				body(posts).
				post("http://localhost:3000/posts");
		
		System.out.println("Respnse with cpmplext array request : " + resp.asString());
		
		
	}
	
	
	//Get request calculate response time
	//GET /posts
	
	@Test
	public void Test_10() {
		
		/*Response resp = given().
				when().
				get("http://localhost:3000/posts");
	
		long time = resp.
				then().
				extract().
				time();
		
		System.out.println("Response time is : " + time);
		*/
		
		given().
		when().
		get("http://localhost:3000/posts").
		then().
		and().
		time(lessThan(90L));
			
	
	}
	
	
	
}
