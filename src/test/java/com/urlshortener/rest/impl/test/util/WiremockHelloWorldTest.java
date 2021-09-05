package com.urlshortener.rest.impl.test.util;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@QuarkusTest
@QuarkusTestResource(WiremockHelloWorld.class)
public class WiremockHelloWorldTest {

	@Test
	public void testHelperResource() {
		Response response = RestAssured.given().when().get("http://localhost:8080/hello").andReturn();
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatusCode());
		Assertions.assertEquals(WiremockHelloWorld.message, response.getBody().asString());
	}
}
