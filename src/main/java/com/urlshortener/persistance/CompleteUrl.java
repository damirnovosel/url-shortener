package com.urlshortener.persistance;

import java.time.Duration;
import java.time.Instant;

public class CompleteUrl {

	String url;

	Instant lastAccessTime;

	Duration validity;

	String ownerId;

	public CompleteUrl(String longUrl, Long retentionPeriodHours, String owner) {
		url = longUrl;
		lastAccessTime = Instant.now();
		validity = Duration.ofHours(retentionPeriodHours);
		ownerId = owner;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Instant getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Instant lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Duration getValidity() {
		return validity;
	}

	public void setValidity(Duration validity) {
		this.validity = validity;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
