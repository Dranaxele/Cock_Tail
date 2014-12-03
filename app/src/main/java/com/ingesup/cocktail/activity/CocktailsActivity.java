package com.ingesup.cocktail.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;
import com.ingesup.cocktail.AppConstants;
import com.ingesup.cocktail.R;
import com.ingesup.cocktail.adapter.CocktailAdapter;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.repository.SQLLite;
import com.ingesup.cocktail.service.CocktailServiceFactory;
import com.ingesup.cocktail.task.AsyncTaskCallback;
import com.ingesup.cocktail.task.CocktailsAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class CocktailsActivity extends ActionBarActivity implements AsyncTaskCallback<List<Cocktail>> {

    private ListView cocktailsListView;
    private ProgressDialog progressDialog;

    private List<Cocktail> cocktails = new ArrayList<Cocktail>();

    private CocktailAdapter cocktailsAdapter;

    private Context context;

    public static SQLLite monSqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        setContentView(R.layout.activity_cocktails);

//        Log.d("CK_SQLite", " MainActivity : MainActivity onCreate : Etat de la connexion � la base de donn�e : " + monSqlLite.state() + ".");

        initView();

        try {
            if (CocktailServiceFactory.instance(this).dbFindAll().size() < 1) {
				new CocktailsAsyncTask(this).execute("");
			}
        } catch (Exception e) {
            Log.d("CK_SQLite", "Failed to retrieve cocktails");

            progressDialog.hide();

            Toast.makeText(this, R.string.failed_to_retrieve_cocktails, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (monSqlLite != null) {
            monSqlLite.close();
            monSqlLite = null;
        }
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
                bundle.putInt(AppConstants.COCKTAIL_ID_BUNDLE_PARAM, cocktails.get(position).getId());

                startActivity(showCocktailIntent, bundle);
            }
        });

        this.cocktailsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemPosition = position;

                if (CocktailServiceFactory.instance(context).findFavourites().contains(cocktails.get(position))) {
                    Toast.makeText(context, R.string.cocktail_already_exists_in_fav, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle(R.string.confirm_title_add_to_favourite)
                            .setMessage(R.string.confirm_title_add_to_favourite_question)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CocktailServiceFactory.instance(context).addCocktailToFavourite(itemPosition);
                                    Toast.makeText(context, R.string.cocktail_added_to_fav, Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                }

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

        if (result.size() > 0) {
            CocktailServiceFactory.instance(this).addAll(result);
        }

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