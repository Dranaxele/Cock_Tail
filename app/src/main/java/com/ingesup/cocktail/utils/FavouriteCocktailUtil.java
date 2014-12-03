package com.ingesup.cocktail.utils;

import android.content.Context;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.repository.CocktailRepositoryFactory;
import com.ingesup.cocktail.service.CocktailService;
import com.ingesup.cocktail.service.CocktailServiceFactory;
import com.ingesup.cocktail.service.CocktailServiceImpl;

import java.util.List;

/**
 * Created by lopes_f on 11/20/2014.
 * <florian.lopes@outlook.com>
 */
public class FavouriteCocktailUtil {

	private static List<Cocktail> favouritesCocktails;

	private static CocktailService cocktailService;

/*	private static CocktailService getCocktailService(Context context) {
		return CocktailServiceFactory.instance(context);
	}

	public static List<Cocktail> getFavouritesCocktails(Context context) {
		return CocktailRepositoryFactory.instance(context).getFavouritesCocktails();
	}

	public static void addFavouriteCocktail(int cocktailId) {
		getCocktailService().addCocktailToFavourite(cocktailId);
	}*/

	public static void removeFavouriteCocktail(int cocktailId) {
//		getCocktailService().findFavourites()
//		getFavouritesCocktails().remove(cocktailId);
	}
}
