package com.urlshortener.rest.impl.test.util;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class WiremockHelloWorld implements QuarkusTestResourceLifecycleManager {

	private WireMockServer wireMockServer;

	public static final String message = "Hello RESTEasy";

	@Override
	public Map<String, String> start() {
		wireMockServer = new WireMockServer();
		wireMockServer.start();
		stubFor(get(urlEqualTo("/hello"))
				.willReturn(aResponse().withHeader("Content-Type", MediaType.TEXT_PLAIN).withBody(message)));
		return Collections.singletonMap("com.urlshortener.rest.impl.test.util.WiremockHelloWorld/mp-rest/url",
				wireMockServer.baseUrl());
	}

	@Override
	public void stop() {
		if (null != wireMockServer) {
			wireMockServer.stop();
		}
	}
}
