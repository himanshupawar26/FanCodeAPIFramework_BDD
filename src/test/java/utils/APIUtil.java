package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class APIUtil {
	
	
	// Static block to set the base URI once when the class is loaded
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    public static Response getUsers(String endpoint) {
        return RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response getTodos(String endpoint) {
        return RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
