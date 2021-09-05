package com.urlshortener.persistance;

import java.time.Duration;
import java.time.Instant;

public class CompleteUrl {

	String url;

	Instant lastAccessTime;

	Duration validity;

	public CompleteUrl(String longUrl, Long retentionPeriodHours) {
		url = longUrl;
		lastAccessTime = Instant.now();
		validity = Duration.ofHours(retentionPeriodHours);
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
}
