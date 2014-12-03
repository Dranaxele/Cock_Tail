package com.ingesup.cocktail.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ingesup.cocktail.AppConstants;
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
	private Button removeFromFavouriteButton;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		final int cocktailId = getIntent().getIntExtra(AppConstants.COCKTAIL_ID_BUNDLE_PARAM, 0);

		try {
			cocktail = CocktailServiceFactory.instance(this).findById(cocktailId);

			initView();
		} catch (Exception e) {
			Toast.makeText(this, R.string.cocktail_not_found, Toast.LENGTH_LONG);

			finish();
		}
	}

	private void initView() {
		setContentView(R.layout.activiti_view_cocktail);

		// TODO init TextView to show cocktail name, desc, ...

		this.removeFromFavouriteButton = (Button) findViewById(R.id.removeFromFavouriteButton);
		this.removeFromFavouriteButton.setText(R.string.removeFromFavourite);
		this.removeFromFavouriteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				CocktailServiceFactory.instance(context).removeCocktailFromFavourite(cocktail.getId());
			}
		});
	}
}