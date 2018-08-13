/**
 * 
 */
package APITesting.com.org.api;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

/**
 * @author Alok
 *
 */
public class localReq {

	@Test

	public void test1() {

	//	Response resp = when().get("http://localhost:3000/posts");

		Response resp = given().when().get("http://localhost:3000/posts");

		System.out.println(resp.getStatusCode());
		System.out.println(resp.asString());
		Assert.assertEquals(resp.getStatusCode(), 200);
		
		

	}

	public void test_update() {
		
		
		Response resp= given().body(body)
		
	}
}
