package com.ingesup.cocktail.service;

import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.rest.RestTemplateFactory;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public class CocktailServiceImpl implements CocktailService {

	private static final String COCKTAILS_URL = "http://alexandreplaitant.ddns.net/android/cocktail.php";

	@Override
	public List<Cocktail> findAll() throws Exception {

		ResponseEntity<Cocktail[]> cocktails =
				RestTemplateFactory.getRestTemplate().getForEntity(COCKTAILS_URL, Cocktail[].class);
		if (cocktails != null) {
			return Arrays.asList(cocktails.getBody());
		} else {
			return new ArrayList<Cocktail>();
		}
	}

	@Override
	public List<Cocktail> findFavourites() {
		return null;
	}
}
