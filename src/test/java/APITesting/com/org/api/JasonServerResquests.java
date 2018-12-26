package APITesting.com.org.api;
import org.testng.annotations.Test;

import APITesting.com.org.classes.Info;
import APITesting.com.org.classes.Posts;
import APITesting.com.org.classes._Posts;
import APITesting.com.org.classes.advancedExample._Info;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

import static com.jayway.restassured.RestAssured.*;


public class JasonServerResquests {

	//GET    /posts

	//@Test
	public void test_01(){
		Response resp = given().
				when().
				get("http://localhost:3000/posts");


		System.out.println("Get response is : " + resp.asString());
	}




	//POST   /posts
	//@Test
	public void test_02(){

		Response resp=	given().
				body("  {\"id\":\"2\","
						+ " \"titile\":\"dummyTitle\","
						+ " \"author\":\"Vaibhav\" }  ").
						when().
						contentType(ContentType.JSON).
						post("http://localhost:3000/posts");

		System.out.println(resp.asString());
	}


	//POST   /posts
	//@Test
	public void test_03(){

		Posts posts = new Posts();
		posts.setId(3);
		posts.setTitle("posts request by object");
		posts.setAuthor("Vaibhav");

		Response resp = given().
				when().
				contentType(ContentType.JSON).
				body(posts).
				post("http://localhost:3000/posts");


		System.out.println("response : " + resp.asString());




	}

	
	//GET    /posts/1
	//@Test
	public void test_04(){
		Response resp = given().
				when().
				get("http://localhost:3000/posts/3");


		System.out.println(resp.asString());
	}

	//PUT    /posts/1
	//@Test
	public void test_05(){
		Posts posts = new Posts();
		posts.setId(3);
		posts.setAuthor("updated Author name");
		posts.setTitle("updated Title name");
		
		Response resp = given().
		when().
		contentType(ContentType.JSON).
		body(posts).
		put("http://localhost:3000/posts/3");
		
		System.out.println("Put API response : " + resp.asString());
	}


	//PATCH  /posts/1
	//@Test
	public void test_06(){
		Response resp = given().
		body("{ \"title\":\"updated by PUT request\" }").
		when().
		contentType(ContentType.JSON).
		patch("http://localhost:3000/posts/3");
		
		System.out.println("PATCH request : "+ resp.asString());
	}

	
	//DELETE /posts/1
	//@Test
	public void test_07(){
		Response resp = given().
		when().
		delete("http://localhost:3000/posts/3");
		
		System.out.println("Deleting response : "+ resp.asString());
	}
	
	//Complex Posts
	// POST   /posts
	//@Test
	public void test_08(){
		Info info = new Info();
		info.setEmail("info@appium-selenium.com");
		info.setPhone("1111111");
		info.setAddress("India");
		
		
		
		_Posts posts = new _Posts();
		posts.setAuthor("Author");
		posts.setId("10");
		posts.setTitle("title");
		posts.setInfo(info);
		
		
		Response resp = given().
		when().
		contentType(ContentType.JSON).
		body(posts).
		post("http://localhost:3000/posts");
		
		System.out.println("Response : "+ resp.asString());
		
	}
	
	
	//Complex post
	//POST   /posts
    //@Test
    public void test_09(){
    	_Info info1 = new _Info();
    	info1.setEmail("test email 1");
    	info1.setPhone("test phone 1");
    	info1.setAddress("test address 1");
    	
    	
    	_Info info2 = new _Info();
    	info2.setEmail("test email 2");
    	info2.setPhone("test phone 2");
    	info2.setAddress("test address 2");
    	
    	Posts posts = new Posts();
    	posts.setId(100);
    	posts.setTitle("title");
    	posts.setAuthor("author");
    	//posts.setInfo(new _Info[]{info1, info2});
    	
    	
    	Response resp = given().
    	when().
    	contentType(ContentType.JSON).
    	body(posts).
    	post("http://localhost:3000/posts");
    	
    	
    	System.out.println("Response : "+ resp.asString());
    	
    	 	
    	
    }
    
    
    //Get Request calculate Response time
    //GET   /posts
    @Test
    public void test_10(){
    	Response resp = given().
    	when().
    	get("http://localhost:3000/posts");
    	
    	Long time = resp.
    	then().
    	extract().
    	time();
    	
    	System.out.println("Response time is : "+ time);
    	
    	
    	
    	
    	given().
    	when().
    	get("http://localhost:3000/posts").
    	then().
    	and().
    	time(lessThan(800L));
    	
    	
    	
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

























}
