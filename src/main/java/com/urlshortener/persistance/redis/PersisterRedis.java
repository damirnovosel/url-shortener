package com.urlshortener.persistance.redis;

import java.util.Arrays;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urlshortener.persistance.api.CompleteUrl;
import com.urlshortener.persistance.api.Persister;

import io.quarkus.redis.client.RedisClient;

//@ApplicationScoped
public class PersisterRedis implements Persister {

	@Inject
	ObjectMapper mapper;

	@Inject
	private RedisClient redisClient;

	@Inject
	Logger logger;

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void invalidateAll() {
		// TODO Auto-generated method stub
	}

	@Override
	public void persist(String shortUrl, CompleteUrl completeUrl) {
		try {
			String value = mapper.writeValueAsString(completeUrl);
			redisClient.set(Arrays.asList(shortUrl, value));
		} catch (JsonProcessingException e) {
			// TODO handle this better
			e.printStackTrace();
		}
	}

	@Override
	public String find(String shortUrl) {
		String value = redisClient.get(shortUrl).toString();
		try {
			CompleteUrl completUrl = mapper.readValue(value, CompleteUrl.class);
			return completUrl.getUrl();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<String> getUrls() {
		// TODO Auto-generated method stub
		return null;
	}

}
