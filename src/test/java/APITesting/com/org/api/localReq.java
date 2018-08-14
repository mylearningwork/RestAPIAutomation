/**
 * 
 */
package APITesting.com.org.api;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.classes.Posts;

/**
 * @author Alok
 *
 */
public class localReq {

	@Test(enabled = false)

	public void readData_Get() {

		// Response resp = when().get("http://localhost:3000/posts");

		Response resp = given().when().get("http://localhost:3000/posts");

		System.out.println(resp.getStatusCode());
		System.out.println(resp.asString());
		Assert.assertEquals(resp.getStatusCode(), 200);

	}

	/**
	 * @author Alok
	 * @Desscription This method will update JSON db with new record. no duplicated will be allowed.
	 */

	@Test(enabled = false)
	public void test_update_Post() {

		Posts post = new Posts();

		post.setId(16);
		post.setTitle("RAM");
		post.setAuthor("God");

		given().body(post).contentType(ContentType.JSON).post("http://localhost:3000/posts");

		Response resp = given().get("http://localhost:3000/posts/11");

		System.out.println(resp.getStatusCode());
		System.out.println(resp.asString());

	}
/***
 * @author Alok
 * @Description This method will update an existing record based on id.
 */
	@Test(enabled = false)

	public void test_PUT_Req() {

		Posts post = new Posts();

		post.setId(11);
		post.setTitle("RAM");
		post.setAuthor("God updated");

		given().body(post).contentType(ContentType.JSON).put("http://localhost:3000/posts/11");

		Response resp = given().get("http://localhost:3000/posts/11");

		System.out.println(resp.getStatusCode());
		System.out.println(resp.asString());

	}
	
	@Test
	public void updateByPATCHReq() {
		
		given().body("{\"title\":\"updated by patch Req by alok\"}").when().contentType(ContentType.JSON).patch("http://localhost:3000/posts/11");
		
		Response resp = given().get("http://localhost:3000/posts/");

		System.out.println(resp.getStatusCode());
		System.out.println(resp.asString());
		
	}
}
