package com.testPages;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class WheatherGetRequests {

	// Simple get request for getting wheather request by City name
	// Status Code : 200
	// @Test
	public void Test_01() {

		Response resp = when()
				.get("http://api.openweathermap.org/data/2.5/weather?q=London&appid=673c5650a20311041c26d61291b186ae");

		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);

	}

	// Status Code : 401
	// @Test
	public void Test_02() {

		Response resp = when()
				.get("http://api.openweathermap.org/data/2.5/weather?q=London&appid=673c5650a20311041c26d61291b186a");

		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 401);

	}

	// How to use parameters with rest assured
	// @Test
	public void Test_03() {

		Response resp = given().param("q", "London").param("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);

		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
		} else {
			System.out.println("API is not working fine");
		}

	}

	// Asset our testcase in Rest assured api
	// @Test
	public void Test_04() {

		given().param("q", "London").param("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather").then().assertThat().statusCode(200);

	}

	// @Test
	public void Test_05() {

		Response resp = given().param("q", "London").param("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		System.out.println(resp.asString());

	}

	// @Test
	public void Test_06() {

		Response resp = given().parameter("id", "2172797").param("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		Assert.assertEquals(resp.getStatusCode(), 200);

		System.out.println(resp.asString());

	}

	// @Test
	public void Test_07() {

		Response resp = given().parameter("zip", "201010,in").param("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		Assert.assertEquals(resp.getStatusCode(), 200);

		System.out.println(resp.asString());

	}

	@Test
	public void Test_08() {

		String weatherReport = given().parameter("id", "2172797").parameter("appid", "673c5650a20311041c26d61291b186ae")
				.when().get("http://api.openweathermap.org/data/2.5/weather").then().contentType(ContentType.JSON)
				.extract().path("weather[0].description");

		String resp2 = given().parameter("id", "2172797").parameter("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather").jsonPath().get("weather[0].description");

		System.out.println("wheather report : " + weatherReport);

		System.out.println("Weather report in another ways is : " + resp2);
	}

	// @Test
	public void Test_09() {

		Response resp = given().parameter("id", "2172797").parameter("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		String actualWeatherReport = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");

		String expectedWeathereport = null;

		if (actualWeatherReport.equalsIgnoreCase(expectedWeathereport)) {
			System.out.println("Testcase pass");
		} else
			System.out.println("Testcase fail");

	}

	// @Test
	public void test_10() {

		Response resp = given().parameter("id", "2172797").parameter("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		String reportbyID = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");

		System.out.println("weather description by ID : " + reportbyID);

		String lon = String.valueOf(resp.then().contentType(ContentType.JSON).extract().path("coord.lon"));

		System.out.println("longitude is : " + lon);

		String lat = String.valueOf(resp.then().contentType(ContentType.JSON).extract().path("coord.lat"));

		System.out.println("latitude is : " + lat);

		String reportbyCoordinates = given().parameter("lat", lat).parameter("lon", lon)
				.parameter("appid", "673c5650a20311041c26d61291b186ae").when()
				.get("http://api.openweathermap.org/data/2.5/weather").then().contentType(ContentType.JSON).extract()
				.path("weather[0].description");
		// https://stackoverflow.com/questions/9534602/what-is-the-difference-between-digest-and-basic-authentication
	//	given().authentication().basic("Alok", "Rai");
		System.out.println("report by coordinates : " + reportbyCoordinates);

		Assert.assertEquals(reportbyID, reportbyCoordinates);

	}
}
