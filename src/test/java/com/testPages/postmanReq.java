/**
 * 
 */
package com.testPages;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import utilities.TestBase;
import utilities.Utils;

import static org.hamcrest.Matchers.*;

public class postmanReq extends TestBase {

	// @Test(enabled = true)
	public void getResp() {

		Response resp = RestAssured.given().get("https://postman-echo.com/GET");
		System.out.println(resp.asString());

	}

	// https://stackoverflow.com/questions/52208765/rest-assured-how-to-pass-object-in-jsonobject-body
	@Test(priority = 1, enabled = true)
	public void testStatusCode() {
		
		RestAssured.given().get("https://postman-echo.com/GET").then().statusCode(200);
		Response resp = RestAssured.given().get("https://postman-echo.com/GET");
		Utils.assertIfEqual(Utils.getStatusCode(resp),200);
		//resp.then().assertThat().statusLine(isEmptyOrNullString());
		resp.getBody().prettyPeek();
		
		
	}

	@Test(priority = 2, enabled = false)
	public void testBody() {
		RestAssured.get("https://postman-echo.com/GET").then().assertThat().body("headers.host",
				equalTo("postman-echo.com"));
	}

	// @Test(priority = 3)
	public void testHeader() {
		RestAssured.get("https://postman-echo.com/GET").then().header("Content-Encoding", "gzip");

		Response resp = RestAssured.get("https://postman-echo.com/GET");
		resp.getBody();
		System.out.println(resp);
		System.out.println(resp.getHeaders());
		System.out.println(resp.getBody().jsonPath());
		System.out.println(resp.getContentType());

	}
}
