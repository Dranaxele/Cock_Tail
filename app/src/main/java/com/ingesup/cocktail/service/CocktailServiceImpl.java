package com.ingesup.cocktail.service;

import android.util.Log;
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

	private static final String TAG = "CocktailService";

	private static final String COCKTAILS_URL = "http://alexandreplaitant.ddns.net/android/cocktail.php";

	@Override
	public List<Cocktail> findAll() {

		try {
			ResponseEntity<Cocktail[]> cocktails =
					RestTemplateFactory.getRestTemplate().getForEntity(COCKTAILS_URL, Cocktail[].class);
			if (cocktails != null) {
				return Arrays.asList(cocktails.getBody());
			}
		}
		catch (Exception e) {
			Log.e(TAG, "Error while retrieving cocktails " + e.getMessage());
		}

		return new ArrayList<Cocktail>();
	}
}
