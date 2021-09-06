package com.urlshortener.persistance.api;

import java.util.Set;

public interface Persister {

	public int size();

	public void invalidateAll();

	public void persist(String shortUrl, CompleteUrl completeUrl);

	public String find(String shortUrl);

	public Set<String> getUrls();

}
