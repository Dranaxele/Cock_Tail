package com.ingesup.cocktail.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ingesup.cocktail.utils.AppConstants;
import com.ingesup.cocktail.R;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.service.CocktailServiceFactory;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public class ViewCocktailActivity extends Activity {

	private Cocktail cocktail;

	private TextView cocktailNameTextView;
	private TextView cocktailIngredientsTextView;
	private TextView cocktailAlcoholTextView;
	private TextView cocktailDescriptionTextView;

	private Button removeFromFavouriteButton;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		final int cocktailId = getIntent().getExtras().getInt(AppConstants.COCKTAIL_ID_BUNDLE_PARAM, 0);

		try {
			cocktail = CocktailServiceFactory.instance(this).dbFindById(cocktailId);

			initView();
		} catch (Exception e) {
			Toast.makeText(this, R.string.cocktail_not_found, Toast.LENGTH_LONG);

			System.exit(0);
		}
	}

	private void initView() {
		setContentView(R.layout.activiti_view_cocktail);

		// TODO init TextView to show cocktail name, desc, ...
		this.cocktailNameTextView = (TextView) findViewById(R.id.cocktailNameTextView);
		this.cocktailNameTextView.setText(cocktail.getNom());

		this.cocktailAlcoholTextView = (TextView) findViewById(R.id.cocktailAlcoolTextView);
		this.cocktailAlcoholTextView.setText(cocktail.getAlcool());

		this.cocktailIngredientsTextView = (TextView) findViewById(R.id.cocktailIngredientTextView);
		this.cocktailIngredientsTextView.setText(cocktail.getIngredient());

		this.cocktailDescriptionTextView = (TextView) findViewById(R.id.cocktailDescriptionTextView);
		this.cocktailDescriptionTextView.setText(cocktail.getDescription());

		this.removeFromFavouriteButton = (Button) findViewById(R.id.removeFromFavouritesButton);

		if (CocktailServiceFactory.instance(context).findFavourites().contains(cocktail)) {
			this.removeFromFavouriteButton.setEnabled(true);
			this.removeFromFavouriteButton.setVisibility(View.VISIBLE);
		} else {
			this.removeFromFavouriteButton.setEnabled(false);
			this.removeFromFavouriteButton.setVisibility(View.INVISIBLE);
		}

		this.removeFromFavouriteButton.setText(R.string.removeFromFavourite);
		this.removeFromFavouriteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				CocktailServiceFactory.instance(context).removeCocktailFromFavourite(cocktail.getId());

				removeFromFavouriteButton.setEnabled(false);
				removeFromFavouriteButton.setVisibility(View.INVISIBLE);

				Toast.makeText(context, R.string.cocktail_removed_from_favourites, Toast.LENGTH_SHORT);
			}
		});
	}
}