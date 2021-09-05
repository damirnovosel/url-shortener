package com.urlshortener.rest.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.urlshortener.persistance.Persister;

@ApplicationScoped
public class UrlShortenerService {

	@Inject
	Persister persister;

	@Inject
	ShortUrlGenerator generator;

	@ConfigProperty(name = "serviceUrl")
	String baseUrl;

	public String shortenUrl(String longUrl) {
		String shortUrl = ShortUrlGenerator.next();
		persister.persist(shortUrl, longUrl);
		return shortUrl;
	}

	public String expandUrl(String shortUrl) {
		return persister.find(shortUrl);
	}

}
