package com.ingesup.cocktail.service;

import android.content.Context;

/**
 * Created by lopes_f on 12/3/2014.
 * <florian.lopes@outlook.com>
 */
public class CocktailServiceFactory {

	private static CocktailService cocktailService;

	public static CocktailService instance(Context context) {
		if (cocktailService == null) {
			cocktailService = new CocktailServiceImpl();
		}
		cocktailService.setContext(context);

		return cocktailService;
	}
}