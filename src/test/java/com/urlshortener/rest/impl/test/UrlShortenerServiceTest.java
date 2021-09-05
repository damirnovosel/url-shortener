package com.urlshortener.rest.impl.test;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.urlshortener.rest.impl.UrlShortenerService;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UrlShortenerServiceTest {

	@Inject
	UrlShortenerService testedService;

	String longUrl = "url/to/shorten";

	@Test
	void shouldReturnDifferentShortUrlsForSameLongUrls() {
		String short1 = testedService.shortenUrl(longUrl, "owner");
		String short2 = testedService.shortenUrl(longUrl, "owner");
		Assertions.assertNotEquals(short1, short2);
	}

	@Test
	void shouldExpandUrl() {
		String short1 = testedService.shortenUrl(longUrl, "owner");
		Assertions.assertEquals(longUrl, testedService.expandUrl(short1));
	}

}
