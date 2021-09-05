package com.urlshortener.rest.impl.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;

import com.urlshortener.persistance.Persister;
import com.urlshortener.rest.impl.UrlShortenerService;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class UrlShortenerServiceStressTest {

	@Inject
	Logger logger;

	@Inject
	Persister persister;

	@Inject
	UrlShortenerService testedService;

	private final int numThreads = 20;

	private final int numLoops = 200;

	@Test
	void test_00_shouldSaveManyLinks() throws InterruptedException {
		persister.invalidateAll();
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < numThreads; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					// Generate 100 short URIs
					for (int i = 0; i < numLoops; i++) {
						testedService.shortenUrl("http://localhost:8080/hello", "owner");
					}
				}
			});
			threads.add(t);
		}

		long start = System.currentTimeMillis();
		logger.info("Starting {} threads with {} API calls each", numThreads, numLoops);
		for (Thread t : threads) {
			t.start();
		}

		logger.info("Waiting for threads to finish");
		for (Thread t : threads) {
			t.join();
		}
		long end = System.currentTimeMillis();
		logger.info("Processing of {} links took {} ms", numThreads * numLoops, end - start);
		logger.info("Average links/sec: {}", numThreads * numLoops * 1000. / (end - start));
		Assertions.assertEquals(numThreads * numLoops, persister.size());
	}

	@Test
	void test_01_shouldGetManyLinks() throws InterruptedException {
		// using links from previous test
		Set<String> keys = persister.getUrls();

		logger.info("Starting reading {} ", keys.size());
		long start = System.currentTimeMillis();
		for (String key : keys) {
			testedService.expandUrl(key);
		}
		long end = System.currentTimeMillis();

		logger.info("Processing of {} links took {} ms", keys.size(), end - start);
		logger.info("Average links/sec: {}", keys.size() * 1000. / (end - start));
	}
}
