package com.urlshortener.rest.impl;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShortUrlGenerator {
	private static Long id = 1L;

	public static String next() {
		String value = id.toString();
		id++;
		return value;
	}
}
