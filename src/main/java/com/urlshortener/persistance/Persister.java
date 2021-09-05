package com.urlshortener.persistance;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class Persister {

	Map<String, CompleteUrl> urls = new ConcurrentHashMap<>();

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

	@Scheduled(every = "60s")
	private void invalidateUrls() {
		Instant now = Instant.now();
		Iterator<Entry<String, CompleteUrl>> it = urls.entrySet().iterator();
		while (it.hasNext()) {
			CompleteUrl cUrl = it.next().getValue();
			Duration dur = Duration.between(cUrl.getLastAccessTime(), now);
			if (dur.getSeconds() > cUrl.getValidity().getSeconds()) {
				it.remove(); // expired
			}
		}
	}
}
