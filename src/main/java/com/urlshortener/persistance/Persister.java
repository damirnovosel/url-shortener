package com.urlshortener.persistance;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class Persister {

	@Inject
	Logger logger;

	Map<String, CompleteUrl> urls = new ConcurrentHashMap<>(10000);

	public int size() {
		return urls.size();
	}

	public void invalidateAll() {
		urls.clear();
	}

	public void persist(String shortUrl, CompleteUrl completeUrl) {
		urls.put(shortUrl, completeUrl);
	}

	public String find(String shortUrl) {
		CompleteUrl url = urls.get(shortUrl);
		if (null != url) {
			return url.getUrl();
		} else {
			return null;
		}
	}

	public Set<String> getUrls() {
		return urls.keySet();
	}

	@Scheduled(every = "60s")
	public void invalidateUrls() {
		Instant now = Instant.now();
		Iterator<Entry<String, CompleteUrl>> it = urls.entrySet().iterator();
		while (it.hasNext()) {
			CompleteUrl cUrl = it.next().getValue();
			if (Duration.between(cUrl.getLastAccessTime(), now).getSeconds() > cUrl.getValidity().getSeconds()) {
				it.remove(); // expired
			}
		}
	}
}
