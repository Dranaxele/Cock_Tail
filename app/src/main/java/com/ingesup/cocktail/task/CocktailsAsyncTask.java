package com.ingesup.cocktail.task;

import android.os.AsyncTask;
import android.util.Log;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.service.CocktailService;
import com.ingesup.cocktail.service.CocktailServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public class CocktailsAsyncTask extends AsyncTask<String, Integer, List<Cocktail>> {

	private static final String TAG = "CocktailService";

	private CocktailService cocktailService = new CocktailServiceImpl();

	private AsyncTaskCallback<List<Cocktail>> asyncTaskCallback;

	public CocktailsAsyncTask(AsyncTaskCallback<List<Cocktail>> asyncTaskCallback) {
		this.asyncTaskCallback = asyncTaskCallback;
	}

	@Override
	protected List<Cocktail> doInBackground(String... params) {
		try {
			return this.cocktailService.findAll();
		} catch (Exception e) {
			Log.e(TAG, "Error while retrieving cocktails " + e.getMessage());
			return new ArrayList<Cocktail>();
		}
	}

	@Override
	protected void onPostExecute(List<Cocktail> cocktails) {
		asyncTaskCallback.deliverResult(cocktails);
	}
}
