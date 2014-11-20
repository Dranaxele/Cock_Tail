package com.ingesup.cocktail.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ingesup.cocktail.AppConstants;
import com.ingesup.cocktail.R;
import com.ingesup.cocktail.SQLLite;
import com.ingesup.cocktail.adapter.CocktailAdapter;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.task.AsyncTaskCallback;
import com.ingesup.cocktail.task.CocktailsAsyncTask;
import com.ingesup.cocktail.utils.FavouriteCocktailUtil;

import java.util.ArrayList;
import java.util.List;

public class CocktailsActivity extends ActionBarActivity implements AsyncTaskCallback<List<Cocktail>> {

    private ListView cocktailsListView;
    private ProgressDialog progressDialog;

    private List<Cocktail> cocktails = new ArrayList<Cocktail>();

    private CocktailAdapter cocktailsAdapter;

    public static SQLLite monSqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);

        // SQLLite start :
        monSqlLite = new SQLLite(this);
        monSqlLite.open();
        Log.d("CK_SQLite", " MainActivity : MainActivity onCreate : Etat de la connexion � la base de donn�e : " + monSqlLite.state() + ".");
        // SQLLite end :
        monSqlLite.close();
        monSqlLite = null;

        initView();

        // TODO handle errors
        new CocktailsAsyncTask(this).execute("");
    }

    private void initView() {
        this.cocktailsListView = (ListView) findViewById(R.id.cocktailsListView);
        this.progressDialog = new ProgressDialog(this);

        progressDialog.setTitle(R.string.cocktails_dialog_loading);
        progressDialog.show();

        this.cocktailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showCocktailIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(AppConstants.COCKTAIL_BUNDLE_PARAM, cocktails.get(position));

                startActivity(showCocktailIntent, bundle);
            }
        });

        this.cocktailsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(AppConstants.COCKTAIL_BUNDLE_PARAM, cocktails.get(position));

                if (FavouriteCocktailUtil.getFavouritesCocktails().contains(cocktails.get(position))) {
                    // TODO if (favorites.contains(cocktails.get(position)) -> remove from fav ; else -> add to favs
                    // TODO dialog : add to favourites ? Yes / No
                } else {
                    FavouriteCocktailUtil.addFavouriteCocktail(cocktails.get(position));
                }
//                Toast.makeText(this, R.string.cocktail_added_to_fav, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_favoris:
                Intent intent = new Intent(CocktailsActivity.this, CocktailsActivityFavoris.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void deliverResult(List<Cocktail> result) {
        this.cocktails = result;

        if (this.cocktailsAdapter == null) {
            this.cocktailsAdapter = new CocktailAdapter(this, this.cocktails);
            this.cocktailsListView.setAdapter(this.cocktailsAdapter);
        }

        this.cocktailsAdapter.notifyDataSetChanged();

        this.progressDialog.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global, menu);
        return true;
    }
}
