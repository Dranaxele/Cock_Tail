package com.ingesup.cocktail.utils;

import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.service.CocktailService;
import com.ingesup.cocktail.service.CocktailServiceImpl;

import java.util.List;

/**
 * Created by lopes_f on 11/20/2014.
 * <florian.lopes@outlook.com>
 */
public class FavouriteCocktailUtil {

	private static List<Cocktail> favouritesCocktails;

	private static CocktailService cocktailService = new CocktailServiceImpl();

	public static List<Cocktail> getFavouritesCocktails() {
		if (favouritesCocktails == null) {
			favouritesCocktails = cocktailService.findFavourites();
		}

		return favouritesCocktails;
	}

	public static void addFavouriteCocktail(Cocktail cocktail) {
		getFavouritesCocktails().add(cocktail);
	}

	public static void removeFavouriteCocktail(int cocktailId) {
		getFavouritesCocktails().remove(cocktailId);
	}
}
