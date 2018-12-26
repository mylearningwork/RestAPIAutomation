/**
 * 
 */
package APITesting.com.org.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

/**
 * @author Alok
 *
 */
public class GoogleAPI {

	String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670,151.1957&radius=500&types=food&name=cruise&key=AIzaSyA3ku1r7Sbw_j-bnmtIcE12G6ZzZe5wWtQ";

	String url1 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

	@Test

	public void validateGoogleResp() {

		Map<String, String> params = new HashMap<String, String>();

		params.put("location", "-33.8670,151.1957");
		params.put("types", "food");
		params.put("radius", "500");
		params.put("name", "cruise");
		params.put("key", "AIzaSyA3ku1r7Sbw_j-bnmtIcE12G6ZzZe5wWtQ");

		/*
		 * Response resp = RestAssured.given().queryParam("location",
		 * "-33.8670,151.1957").queryParam("radius", 500) .queryParam("types",
		 * "food").queryParam("name", "cruise") .queryParam("key",
		 * "AIzaSyA3ku1r7Sbw_j-bnmtIcE12G6ZzZe5wWtQ").get(url1);
		 */
		// .get(url).then().statusCode(200);
		Response resp1 = RestAssured.given().log().everything().queryParameters(params).get(url1);

		// System.out.println(resp1.getBody().asString());

		// System.out.println(resp1.getBody().asString().toUpperCase());

		// System.out.println(resp1.asString());
		// System.out.println(RestAssured.given().queryParameters(params).get(url1).thenReturn().contentType());
		// resp1.then().statusCode(200).contentType(ContentType.JSON).assertThat().header(headerName,
		// expectedValue)

		// System.out.println(resp1.asString());
		// System.out.println(resp1.getContentType());
		// System.out.println(resp1.body().asString());
		// System.out.println(resp.getStatusCode());

		// System.out.println(resp1);

		// System.out.println(resp1.getHeaders());

		// resp1.then().assertThat().header("Pragma", "no-cach8e");
		// resp1.then().statusCode(200).and().header("Pragma",
		// "no-cache").and().contentType(ContentType.JSON).assertThat().statusCode(200);

		// System.out.println(resp1.then().assertThat().statusCode(200));

		// System.out.println(resp1.getStatusCode());

		// Assert.assertTrue(resp1.getStatusCode() == 200);
		//https://github.com/rest-assured/rest-assured/wiki/Usage
		
		System.out.println("response time is .." + resp1.getTime());
		System.out.println("...............");
		System.out.println(resp1.getHeaders());
		System.out.println("...............");
		System.out.println(resp1.getStatusLine());

		Assert.assertTrue(resp1.body().asString().contains("html_attributions"));

		//resp1.body().prettyPrint();
		// resp1.then().b
		System.out.println(resp1.jsonPath().get("status").toString());
		System.out.println("++++++++++++++++++++");
		System.out.println(resp1.then().extract().jsonPath().get("status").toString());
	}

}
