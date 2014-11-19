package com.ingesup.cocktail.service;

import com.ingesup.cocktail.metier.Cocktail;

import java.util.List;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public interface CocktailService {

	public List<Cocktail> findAll() throws Exception;
}