package APITesting.com.org.apis;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;

public class WeatherGetRequests {
	
	


	//simple get request for getting weather request by city name
	//@Test
	public void Test_01() {
		
		Response resp = when().get("http://api.openweathermap.org/data/2.5/weather?q=NewYork&appid=a9fa5473da8dd88a448c438b233cbade"); 
		
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);

	}
	
	//@Test
	public void Test_02() {
		
		Response resp = when().get("http://api.openweathermap.org/data/2.5/weather?q=NewYork&appid=a9fa5473da8dd88a448c438b233cbad"); 
		
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 401);

	}
	
	
	//How to use parameters with rest assured
	//@Test
	public void Test_03() {
		
		Response resp = given().
				param("q", "London").
				param("appid", "0df422b4baf987e0f3bc5ffa4e4451fa").
				when().get("http://api.openweathermap.org/data/2.5/weather"); 
		
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);

		if(resp.getStatusCode()==200) {
			System.out.println("Weather API is working properly");
		}
		else {
			System.out.println("Weather API is not working properly");
		}
	}
	
	
	//Assert our test case in rest assured API
	//@Test
	public void Test_04() {
		
		given().
				param("q", "London").
				param("appid", "0df422b4baf987e0f3bc5ffa4e4451fa").
				when().get("http://api.openweathermap.org/data/2.5/weather").
				then().
				assertThat().statusCode(201);
		
		
	}
	
	// Retrieve the response as string
	//@Test
	public void Test_05() {
		
		Response resp = given().
				param("q", "London").
				param("appid", "0df422b4baf987e0f3bc5ffa4e4451fa").
				when().get("http://api.openweathermap.org/data/2.5/weather");
		
		System.out.println(resp.asString());
	}

	
	//Extract using  JSON Path
	//@Test
	public void Test_06() {
		
		String WeatherReport = given().
				param("id", "2172797").
				param("appid", "0df422b4baf987e0f3bc5ffa4e4451fa").
				when().get("http://api.openweathermap.org/data/2.5/weather").
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");
		
		
			System.out.println("Weather Report :  "+ WeatherReport);		
			
	}
	
	
	
	//Actual testing code type
	//@Test
	public void Test_07() {
		
		Response resp = given().
				param("id", "2172797").
				param("appid", "0df422b4baf987e0f3bc5ffa4e4451fa").
				when().get("http://api.openweathermap.org/data/2.5/weather");
		
		String actualWeatherReport = resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");
		
		String expectedWeatherReport = null;
		
		if(actualWeatherReport.equalsIgnoreCase(expectedWeatherReport)){
			System.out.println("TestCase is passed");		
		}
		
		else
			System.out.println("TestCase is failed");		
			
	}
	
	//Transfering api response to another api
	@Test
	public void Test_08() {
		
		Response resp = given().
				param("id", "2172797").
				param("appid", "0df422b4baf987e0f3bc5ffa4e4451fa").
				when().get("http://api.openweathermap.org/data/2.5/weather");
		
		String reportById = resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");
				
			System.out.println("Weather Report by Id :  "+ reportById);	
		
			
		String lat = String.valueOf(resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("coord.lat"));	
		
			System.out.println("Latitude is :  " + lat);

		
		String lon = String.valueOf(resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("coord.lon"));	
		
			System.out.println("Longtitude is :  " + lon);

		Response reportByCoordinates = given().
				parameter("lon", lon).
				parameter("lat", lat).
				param("appid", "0df422b4baf987e0f3bc5ffa4e4451fa").
				when().
				get("http://api.openweathermap.org/data/2.5/weather").
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");
		
		
			System.out.println("Weather Report by Coordinates :  "+ reportByCoordinates);
			
			
			Assert.assertEquals(reportById, reportByCoordinates);
			
	}


}
