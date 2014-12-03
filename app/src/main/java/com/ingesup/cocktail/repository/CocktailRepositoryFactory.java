package com.ingesup.cocktail.repository;

import android.content.Context;

/**
 * Created by lopes_f on 11/30/2014.
 * <florian.lopes@outlook.com>
 */
public class CocktailRepositoryFactory {

	private static CocktailRepository cocktailRepository;

	public static CocktailRepository instance(Context context) {
		if (cocktailRepository == null) {
			cocktailRepository = new SQLLite(context).getBDDUsage();
		}

		return cocktailRepository;
	}
}
