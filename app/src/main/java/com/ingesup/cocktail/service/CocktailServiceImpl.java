package com.ingesup.cocktail.service;

import android.content.Context;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.repository.CocktailRepositoryFactory;
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

	private Context context;

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
	public List<Cocktail> dbFindAll() throws Exception {
		return CocktailRepositoryFactory.instance(context).recupererCocktails();
	}

	@Override
	public List<Cocktail> findFavourites() {
		return CocktailRepositoryFactory.instance(context).getFavouritesCocktails();
	}

	@Override
	public Cocktail findById(int id) throws Exception {
		Cocktail cocktailToRetrieve = null;

		ResponseEntity<Cocktail[]> cocktails =
				RestTemplateFactory.getRestTemplate().getForEntity(COCKTAILS_URL, Cocktail[].class);
		if (cocktails != null) {
			for (Cocktail cocktail : cocktails.getBody()) {
				if (cocktail.getId() == id) {
					cocktailToRetrieve = cocktail;
					break;
				}
			}
		}

		return cocktailToRetrieve;
	}

	@Override
	public void addCocktailToFavourite(int cocktailId) {
		CocktailRepositoryFactory.instance(context).addCocktailToFavourite(cocktailId);
	}

	@Override
	public void removeCocktailFromFavourite(int cocktailId) {
		CocktailRepositoryFactory.instance(context).removeCocktailFromFavourite(cocktailId);
	}

	@Override
	public void addAll(List<Cocktail> cocktails) {
		CocktailRepositoryFactory.instance(context).addAll(cocktails);
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}
}