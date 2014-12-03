package com.ingesup.cocktail.repository;

import com.ingesup.cocktail.metier.Cocktail;

import java.util.List;

/**
 * Created by lopes_f on 11/30/2014.
 * <florian.lopes@outlook.com>
 */
public interface CocktailRepository {

	public List<Cocktail> getFavouritesCocktails();

	public void addCocktailToFavourite(int cocktailId);

	public void removeCocktailFromFavourite(int cocktailId);

	public List<Cocktail> recupererCocktails();

	public long insertionCocktail(Cocktail unCocktail);

	public void supprimerCocktail(Cocktail unCocktail);

	public void supprimerCocktail(String nomCocktail);

	public int countCocktail(String nom);

	public void addAll(List<Cocktail> cocktails);
}