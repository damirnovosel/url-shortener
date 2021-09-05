package com.urlshortener.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

	public static final String message = "Hello RESTEasy";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return message;
	}
}