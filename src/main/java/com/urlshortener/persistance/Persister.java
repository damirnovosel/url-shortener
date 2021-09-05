package com.urlshortener.persistance;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Persister {

	Map<String, String> urls = new HashMap<>();

	public void persist(String shortUrl, String longUrl) {
		urls.put(shortUrl, longUrl);
	}

	public String find(String shortUrl) {
		return urls.get(shortUrl);
	}

}
