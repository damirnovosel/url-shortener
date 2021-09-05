package com.urlshortener.rest.impl;

import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShortUrlGenerator {
	private static AtomicLong id = new AtomicLong(1L);

	public static String next() {
		Long value = id.addAndGet(1L);
		return value.toString();
	}
}
