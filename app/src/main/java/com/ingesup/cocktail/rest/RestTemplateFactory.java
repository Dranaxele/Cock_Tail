package com.ingesup.cocktail.rest;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public class RestTemplateFactory {

	private static RestTemplate restTemplate;

	public static RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			initRestTemplate();
		}

		return restTemplate;
	}

	private static void initRestTemplate() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
}