package com.urlshortener.persistance.redis;

import javax.inject.Inject;

import com.urlshortener.persistance.api.ShortUrlGenerator;

import io.quarkus.redis.client.RedisClient;

//@ApplicationScoped
public class ShortUrlGeneratorRedis implements ShortUrlGenerator {

	@Inject
	private RedisClient redisClient;

	@Override
	public String next() {
		return redisClient.incrby("counter", "1").toString();
	}
}
