package com.urlshortener.persistance.mem;

import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

import com.urlshortener.persistance.api.ShortUrlGenerator;

@ApplicationScoped
public class ShortUrlGeneratorMem implements ShortUrlGenerator {
	private static AtomicLong id = new AtomicLong(1L);

	@Override
	public String next() {
		Long value = id.addAndGet(1L);
		return value.toString();
	}

	// alternative generator:
	// owner + url
	// produces non-predictable urls, uniqueness needs to be checked
}