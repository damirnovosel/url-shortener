package com.urlshortener.rest.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;

import com.urlshortener.rest.api.UrlShortener;

@ApplicationScoped
public class RestUrlShortener implements UrlShortener {

	@Inject
	Logger logger;

	@Inject
	UrlShortenerService shortenerService;

	@Inject
	JsonWebToken jwt;

	@Override
	public Response shortenUrl(String longUrl) {
		String owner = getOwner();
		try {
			URI uri = new URI(longUrl);
		} catch (URISyntaxException e) {
			return Response.status(Status.BAD_REQUEST).entity("Incorrect URL: " + longUrl).build();
		}
		return Response.status(Status.OK).entity(shortenerService.shortenUrl(longUrl, owner)).build();
	}

	@Override
	public Response expandUrl(@PathParam("shortUrl") String shortUrl) {
		// logger.info("Expanding: {}", shortUrl);
		String longUrl = shortenerService.expandUrl(shortUrl);
		if (null != longUrl) {
			try {
				URI uri = new URI(longUrl);
				// logger.info("Sending redirect to: {}", uri);
				return Response.temporaryRedirect(uri).build();
			} catch (URISyntaxException e) {
				// fall through
			}
		}
		return Response.status(Status.NOT_FOUND).entity(shortUrl).build();
	}

	private String getOwner() {
		if (null != jwt) {
			return jwt.getName();
		} else {
			return "n/a";
		}
	}
}
