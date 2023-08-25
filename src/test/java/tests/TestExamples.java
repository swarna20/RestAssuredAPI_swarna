package tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;

public class TestExamples {
	// @Test
	public void test_1() {
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200);
		System.out.println(response.getTime());

	}

	@Test
	public void login_success() {
		JSONObject request = new JSONObject();
		request.put("email", "eve.holt@reqres.in");
		request.put("password", "cityslicka");

		baseURI = "https://reqres.in";
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toString()).when()
				.post("/api/login").then().statusCode(200);
		System.out.print(">> Login is success");
	}

	@Test
	public void login_Failed_withoutPassword() {
		JSONObject request = new JSONObject();
		request.put("email", "eve.holt@reqres.in");
		request.put("password", "");

		baseURI = "https://reqres.in";
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toString()).when()
				.post("/api/login").then().statusCode(400);

	}
	
	@Test
	public void login_Failed_withoutEmail() {
		JSONObject request = new JSONObject();
		request.put("email", "");
		request.put("password", "cityslicka");

		baseURI = "https://reqres.in";
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toString()).when()
				.post("/api/login").then().statusCode(400);

	}

	@Test
	public void login_Failed_InvalidEmail() {
		JSONObject request = new JSONObject();
		request.put("email", "invalid.holt@reqres.in");
		request.put("password", "cityslicka");

		baseURI = "https://reqres.in";
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toString()).when()
				.post("/api/login").then().statusCode(400);

	}

	@Test
	public void login_Failed_NotExistsAccount() {
		JSONObject request = new JSONObject();
		request.put("email", "invalid@gmail.com");
		request.put("password", "invalid");

		baseURI = "https://reqres.in";
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toString()).when()
				.post("/api/login").then().statusCode(400);

	}
}
