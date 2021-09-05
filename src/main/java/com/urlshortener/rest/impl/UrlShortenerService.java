package com.urlshortener.rest.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.urlshortener.persistance.CompleteUrl;
import com.urlshortener.persistance.Persister;

@ApplicationScoped
public class UrlShortenerService {

	@Inject
	Persister persister;

	@Inject
	ShortUrlGenerator generator;

	@ConfigProperty(name = "baseUrl")
	String baseUrl;

	@ConfigProperty(name = "retentionPeriodHours")
	Long retentionPeriodHours;

	public String shortenUrl(String longUrl, String owner) {
		String shortUrl = ShortUrlGenerator.next();
		CompleteUrl cUrl = new CompleteUrl(longUrl, retentionPeriodHours, owner);
		persister.persist(shortUrl, cUrl);
		return shortUrl;
	}

	public String expandUrl(String shortUrl) {
		return persister.find(shortUrl);
	}

}
