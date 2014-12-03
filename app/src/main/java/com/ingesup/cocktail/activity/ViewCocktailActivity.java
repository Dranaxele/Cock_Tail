package com.ingesup.cocktail.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.ingesup.cocktail.AppConstants;
import com.ingesup.cocktail.R;
import com.ingesup.cocktail.metier.Cocktail;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public class ViewCocktailActivity extends Activity {

	private Cocktail cocktail;

	private TextView cocktailNameTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final int cocktailId = getIntent().getIntExtra(AppConstants.COCKTAIL_ID_BUNDLE_PARAM, 0);


		initView();
	}

	private void initView() {
		setContentView(R.layout.activiti_view_cocktail);

		// TODO init TextView to show cocktail name, desc, ...
	}
}