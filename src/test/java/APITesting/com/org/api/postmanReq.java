/**
 * 
 */
package APITesting.com.org.api;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import static org.hamcrest.Matchers.*;

/**
 * @author Alok
 *
 */
public class postmanReq {

	@Test(enabled = true)
	public void getResp() {

		Response resp = RestAssured.given().get("https://postman-echo.com/GET");

		System.out.println(resp.asString());

	}

	@Test(priority = 1, enabled = false)
	public void testStatusCode() {
		RestAssured.given().get("https://postman-echo.com/GET").then().statusCode(200);
	}

	@Test(priority = 2, enabled = false)
	public void testBody() {
		RestAssured.get("https://postman-echo.com/GET").then().assertThat().body("headers.host",
				equalTo("postman-echo.com"));
	}

	@Test(priority = 3)
	public void testHeader() {
		RestAssured.get("https://postman-echo.com/GET").then().header("Content-Encoding", "gzip");

		Response resp = RestAssured.get("https://postman-echo.com/GET");
		resp.getBody();
		System.out.println(resp);
		System.out.println(resp.getHeaders());
		System.out.println(resp.getBody());
		System.out.println(resp.getContentType());

	}
}
