package com.ingesup.cocktail.service;

import android.content.Context;
import com.ingesup.cocktail.metier.Cocktail;

import java.util.List;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public interface CocktailService {

	public List<Cocktail> findAll() throws Exception;

	public List<Cocktail> findFavourites();

	public Cocktail findById(int id) throws Exception;

	public void addCocktailToFavourite(int cocktailId);

	public void removeCocktailFromFavourite(int cocktailId);

	public void setContext(Context context);
}