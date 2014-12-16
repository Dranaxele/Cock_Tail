package com.ingesup.cocktail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ingesup.cocktail.R;
import com.ingesup.cocktail.adapter.CocktailFavorisAdapter;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.task.AsyncTaskCallback;
import com.ingesup.cocktail.task.CocktailsAsyncTask;
import com.ingesup.cocktail.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 19/11/2014.
 */
public class CocktailsActivityFavoris extends ActionBarActivity implements AsyncTaskCallback<List<Cocktail>> {

    private ListView cocktailsFavorisListView;

    private List<Cocktail> cocktailsFavoris = new ArrayList<Cocktail>();

    private CocktailFavorisAdapter cocktailsFavorisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails_favoris);

        initView();

        // TODO handle errors
        new CocktailsAsyncTask(this).execute("");
    }

    private void initView() {
        this.cocktailsFavorisListView = (ListView) findViewById(R.id.cocktailsFavorisListView);

        this.cocktailsFavorisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showCocktailIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(AppConstants.COCKTAIL_ID_BUNDLE_PARAM, cocktailsFavoris.get(position));

                startActivity(showCocktailIntent, bundle);
            }
        });
    }

    @Override
    public void deliverResult(List<Cocktail> result) {
        this.cocktailsFavoris = result;

        if (this.cocktailsFavorisAdapter == null) {
            this.cocktailsFavorisAdapter = new CocktailFavorisAdapter(this, this.cocktailsFavoris);
            this.cocktailsFavorisListView.setAdapter(this.cocktailsFavorisAdapter);
        }

        this.cocktailsFavorisAdapter.notifyDataSetChanged();

    }
}
