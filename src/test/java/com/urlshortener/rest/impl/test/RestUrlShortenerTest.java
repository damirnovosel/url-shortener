package com.urlshortener.rest.impl.test;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.urlshortener.rest.impl.GreetingResource;
import com.urlshortener.rest.impl.test.util.WiremockHelloWorld;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@QuarkusTest
@QuarkusTestResource(WiremockHelloWorld.class)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RestUrlShortenerTest {

	@Test
	void shouldReturn404OnUnknownShortUrl() {
		RestAssured.given().when().get("/unknown-short").then().statusCode(Status.NOT_FOUND.getStatusCode());
	}

	@Test
	void shouldReturn400OnIncorrectLongUrl() {
		RestAssured.given().when().body("%%&&$$").post("/shorten").then()
				.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void shouldReturnDifferentShortUrlForSameLongUrl() {
		String longUrl1 = "url/to/shorten/1";
		// shorten same URL twice
		Response response1 = RestAssured.given().when().body(longUrl1).post("/shorten").andReturn();
		Response response2 = RestAssured.given().when().body(longUrl1).post("/shorten").andReturn();

		Assertions.assertEquals(Status.OK.getStatusCode(), response1.getStatusCode());
		Assertions.assertEquals(Status.OK.getStatusCode(), response2.getStatusCode());

		String short1 = response1.getBody().asString();
		String short2 = response2.getBody().asString();

		// should be shortened to different values
		Assertions.assertNotEquals(short1, short2);
	}

	@Test
	void shouldCorrectlyRedirectExistingShortenedUrl() {
		String longUrl2 = "http://localhost:8080/hello";
		// get shortened URL
		Response response1 = RestAssured.given().when().body(longUrl2).post("/shorten").andReturn();
		Assertions.assertEquals(Status.OK.getStatusCode(), response1.getStatusCode());
		String short1 = response1.getBody().asString();

		// expand it and check response
		Response response2 = RestAssured.given().when().pathParam("shortUrl", short1).get("/{shortUrl}").andReturn();
		Assertions.assertEquals(GreetingResource.message, response2.getBody().asString());
		Assertions.assertEquals(Status.OK.getStatusCode(), response2.getStatusCode());
	}
}
