package com.ingesup.cocktail.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ingesup.cocktail.AppConstants;
import com.ingesup.cocktail.R;
import com.ingesup.cocktail.adapter.CocktailAdapter;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.task.AsyncTaskCallback;
import com.ingesup.cocktail.task.CocktailsAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class CocktailsActivity extends ActionBarActivity implements AsyncTaskCallback<List<Cocktail>> {

    private ListView cocktailsListView;
    private ProgressDialog progressDialog;

    private List<Cocktail> cocktails = new ArrayList<Cocktail>();

    private CocktailAdapter cocktailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);

        initView();

        new CocktailsAsyncTask(this).execute("");
    }

    private void initView() {
        this.cocktailsListView = (ListView) findViewById(R.id.cocktailsListView);
        this.progressDialog = new ProgressDialog(this);

        progressDialog.setTitle(R.string.cocktails_dialog_loading);

        this.cocktailsAdapter = new CocktailAdapter(this, this.cocktails);
        this.cocktailsListView.setAdapter(this.cocktailsAdapter);

        this.cocktailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showCocktailIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(AppConstants.COCKTAIL_BUNDLE_PARAM, cocktails.get(position));

                startActivity(showCocktailIntent, bundle);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void deliverResult(List<Cocktail> result) {
        this.cocktails = result;

        this.progressDialog.hide();
    }
}
