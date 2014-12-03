package com.ingesup.cocktail.repository;

import android.content.Context;

/**
 * Created by lopes_f on 11/30/2014.
 * <florian.lopes@outlook.com>
 */
public class CocktailRepositoryFactory {

	private static CocktailRepository cocktailRepository;

	private static SQLLite sqlLite;

	public static CocktailRepository instance(Context context) {
		if (cocktailRepository == null) {
			if (sqlLite == null) {
				sqlLite = new SQLLite(context);
				sqlLite.open();
			}
			
			cocktailRepository = sqlLite.getBDDUsage();
		}

		return cocktailRepository;
	}

	public static void closeRepository() {
		if (sqlLite != null) {
			sqlLite.close();
		}
	}
}