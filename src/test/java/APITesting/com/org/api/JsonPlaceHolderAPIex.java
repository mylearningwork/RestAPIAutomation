/**
 * 
 */
package APITesting.com.org.api;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.meta.When;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given; // user can directly use given() if imported like this. 

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.get;

/**
 * @author Alok
 *
 */


@SuppressWarnings("unused")
public class JsonPlaceHolderAPIex {
	
	
/*	@BeforeClass
	public void setUp() throws Exception {
	    RestAssured.port = 8081;
	}*/
	
	

	@Test(enabled = false)
	public void photos() {

		Response resp = given().get("https://jsonplaceholder.typicode.com/photos");
		String respAsString = RestAssured.given().get("https://jsonplaceholder.typicode.com/photos").then().extract()
				.asString();
		// System.out.println(resp.asString());
		// System.out.println("Headers are"+resp.getHeaders());
		// System.out.println(resp.getHeader("Content-Type"));
		// System.out.println("Body is "+resp.getBody());
		// System.out.println("content type is :"+resp.getContentType());
		// resp.then().contentType(ContentType.JSON).header("Content-Encoding",
		// "gzip").statusCode(200);
		// resp.then().assertThat().statusCode(200).log().all();
		// .log().all();
		// System.out.println(resp);

		RestAssured.given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).assertThat().statusCode(200).cookie("__cfduid")
				.header("Content-Encoding", "gzip").log().ifValidationFails();// logs only if validation fails.
		// https://stackoverflow.com/questions/43699258/restassured-cannot-master-post-method

		// System.out.println(resp.getHeaders());

		System.out.println(resp.getHeader("Content-Encoding").equals("gzip"));

		// to print all headers one by one.
		Headers headers = resp.getHeaders();

		for (Header h : headers) {

			if (h.getName().equals("Pragma")) {
				System.out.println(h.getName() + "....... " + h.getValue());
				System.out.println(" header exists ");
			}
		}

	}

	@Test(enabled = false)

	public void getCookies() {

		Response resp = RestAssured.get("https://jsonplaceholder.typicode.com/photos");
		Map<String, String> cookies = resp.getCookies();
		cookies.toString();
		// System.out.println(cookies);
		// print all cookies
		for (Entry<String, String> c : cookies.entrySet()) {

			// System.out.println(c.getKey());
			// System.out.println(c.getValue());
		}
		// System.out.println(resp.getDetailedCookies());
		System.out.println(resp.getDetailedCookie("__cfduid"));
		System.out.println(resp.getDetailedCookie("__cfduid").hasExpiryDate());// cookie related methods to test them.
		System.out.println("Expiry date of cookie is : " + resp.getDetailedCookie("__cfduid").getExpiryDate()
				+ resp.getDetailedCookie("__cfduid").getDomain() + resp.getDetailedCookie("__cfduid").getName());

	}

	@Test(enabled = false)

	public void testQueryParam() {

		given().when().request();

	}

	// cdata? par n convert to xml . get set params in cdata req resp.
	@Test(enabled = false)
	public void logtypes() {

		/*
		 * Log all requests: given().log().all() Log all responses: `when`().log().all()
		 * Or just when validations fail: `when`().log().ifValidationFails()
		 */
		given().log().all().get("https://jsonplaceholder.typicode.com/photos");
		given().get("https://jsonplaceholder.typicode.com/photos").then().log().ifStatusCodeIsEqualTo(200);

	}

	@Test(enabled = false)
	public void testPathParam() {
		// https://www.youtube.com/watch?v=4haz0QRTYXw&index=6&list=PLEiBaBxmVLi-hoi61aX-2agQb8EXSCT5f
		String strUser = "users";
		int intI = 1;

		given().pathParam("strUser", strUser).pathParam("id", intI)
				.get("https://jsonplaceholder.typicode.com/{strUser}/{id}").then().log().all();

	}

	@Test(enabled=false)
	public void setCookie() {

		given().when().cookie("CookieName", "cookieAlok").get("http://www.webservicex.net/price/MSNUM");

	}
	
	@Test(enabled=true)
	public void testBodyParamsInResponse() {
		
		// with java 8 lambda expression.
		
		RestAssured.given().get("http://jsonplaceholder.typicode.com/photos/1");
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}