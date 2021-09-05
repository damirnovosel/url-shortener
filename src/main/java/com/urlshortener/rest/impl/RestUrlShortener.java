package com.urlshortener.rest.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.urlshortener.rest.api.UrlShortener;

@RequestScoped
public class RestUrlShortener implements UrlShortener {

	@Inject
	Logger logger;

	@Inject
	UrlShortenerService shortenerService;

	@Override
	public Response shortenUrl(String longUrl) {
		try {
			URI uri = new URI(longUrl);
		} catch (URISyntaxException e) {
			return Response.status(Status.BAD_REQUEST).entity("Incorrect URL: " + longUrl).build();
		}
		return Response.status(Status.OK).entity(shortenerService.shortenUrl(longUrl)).build();
	}

	@Override
	public Response expandUrl(@PathParam("shortUrl") String shortUrl) {
		logger.info("Expanding: {}", shortUrl);
		String longUrl = shortenerService.expandUrl(shortUrl);
		if (null != longUrl) {
			try {
				URI uri = new URI(longUrl);
				logger.info("Sending redirect to: {}", uri);
				return Response.temporaryRedirect(uri).build();
			} catch (URISyntaxException e) {
				// fall through
			}
		}
		return Response.status(Status.NOT_FOUND).entity(shortUrl).build();
	}
}
