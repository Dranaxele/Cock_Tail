package com.ingesup.cocktail.repository;

/**
 * Created by Fergal on 05/11/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.ingesup.cocktail.metier.Cocktail;

import java.util.ArrayList;
import java.util.List;

public class SQLLite {

	private static final String FIND_ALL_COCKTAILS_SQL = "SELECT * FROM cocktail";
	private static final String FIND_FAVOURITES_COCKTAILS_SQL = "SELECT * FROM cocktail WHERE cocktail_favourite = 1";
	private static final String ADD_COCKTAIL_TO_FAVOURITES_SQL = "UPDATE cocktail SET cocktail_favourite = 1 WHERE cocktail_id = ?";
	private static final String REMOVE_COCKTAIL_FROM_FAVOURITE_SQL = "UPDATE cocktail SET cocktail_favourite = 0 WHERE cocktail_id = ?";

	// ATTRIBUTS :
	SQLLiteGestion BDDGestionnaire;
	SQLLiteUsage BDDUsage;
	Context context;
	SQLiteDatabase db;


	// CONSTRUCTEUR :
	public SQLLite(Context context) {
		// on definis le contexte :
		this.context = context;
		// on instancie la BDD (ajout ou modif) :
		BDDGestionnaire = new SQLLiteGestion(context);
		BDDUsage = new SQLLiteUsage(context);
	}


	// GESTION DE LA BASE :
	public final class SQLLiteGestion extends SQLiteOpenHelper {
		// ATTRIBUTS :
		Context context;

		// creation de l'objet de gestion de la BDD :
		public SQLLiteGestion(Context context) {
			// base de donnee "cocktails" :
			super(context, "cocktail", null, 1);
			Log.d("GDL_action", "SQLLite : SQLLiteGestion constructeur : Creation de la base de donnees 'cocktails'.");
			this.context = context;
		}

		// on creer la base de donnees si elle n'existe pas :
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE IF NOT EXISTS cocktail (id INTEGER PRIMARY KEY," +
							   "nom TEXT NOT NULL UNIQUE ON CONFLICT REPLACE," +
							   "couleur TEXT NOT NULL," +
							   "presence_alcool TEXT NOT NULL," +
							   "base Text NOT NULL," +
							   "ingredients TEXT NOT NULL," +
							   "description TEXT NOT NULL," +
							   "nom_photo TEXT NOT NULL)");

			Log.d("GDL_action", "SQLLite : SQLLiteGestion onCreate : Creation de la table dans la base de donnees.");
		}

		// on la met a jour si besoin :
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d("GDL_action", "SQLLite : SQLLiteGestion onUpdate : Mise a jour du contenu de la base de donnees.");
		}
	}

	// UTILISATION DE LA BASE :
	public final class SQLLiteUsage implements CocktailRepository {
		// ATTRIBUTS :
		Context context;

		// creation de l'objet de gestion de la BDD :
		public SQLLiteUsage(Context context) {
			this.context = context;
			Log.d("GDL_action", "SQLLite : SQLLiteUsage constructeur : Creation du composant d'usage de la base de donnees 'cocktails'.");
		}

		public void actionMettreAJourTableCocktail() {
			db.execSQL("DROP cocktail");
			db.execSQL("CREATE TABLE IF NOT EXISTS cocktail (id INTEGER PRIMARY KEY," +
							   "nom TEXT NOT NULL UNIQUE ON CONFLICT REPLACE," +
							   "couleur TEXT NOT NULL," +
							   "presence_alcool TEXT NOT NULL," +
							   "base Text NOT NULL," +
							   "ingredients TEXT NOT NULL," +
							   "description TEXT NOT NULL," +
							   "nom_photo TEXT NOT NULL)");
			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : actionMettreAJourTableCocktail : mise a jour de la table cocktail ok.");
		}

		public void actionViderTableCocktail() {
			db.execSQL("DELETE FROM cocktail");
			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : actionViderTableCocktail : vidage de la table cocktail ok.");
		}

		public long insertionCocktail(Cocktail unCocktail) {
			// on ajoute le cocktail dans la bdd :
			try {
				long result = 0;

				// on compte le nombre de cocktail portant le nom :
				ContentValues values = new ContentValues();
				values.put("id", ((Integer) unCocktail.getId()));
				values.put("nom", ((String) unCocktail.getNom()));
				values.put("couleur", ((String) unCocktail.getCouleur()));
				values.put("presence_alcool", ((String) unCocktail.getAlcool()));
				values.put("base", ((String) unCocktail.getBase()));
				values.put("ingredients", ((String) unCocktail.getIngredient()));
				values.put("description", ((String) unCocktail.getDescription()));
				values.put("nom_photo", ((String) unCocktail.getNomPhoto()));

				result = db.insert("cocktail", null, values);

				if (result > 0) {
					Log.d("GDL_action", "SQLLite --> SQLLiteUsage : insertionCocktail : insertion du cocktail '" + unCocktail.getNom() + "' ok (" + result + ").");
					//Effects.Toast("Cocktail '" + unCocktail.getNom() + "' insere.");
				}

				return result;
			}
			catch (Exception e) {
				Log.d("GDL_error", "SQLLite --> SQLLiteUsage : insertionCocktail : erreur lors de l'insertion du cocktails '" + unCocktail.getNom() + "' : " + e + ".");
				// Effects.Toast("Erreur d'inserction du cocktail '" + unCocktail.getNom() + "'.");
				return -9;
			}
		}

		public void supprimerCocktail(Cocktail unCocktail) {
			// on supprime le cocktails :
			db.execSQL("DELETE FROM cocktail WHERE nom = '" + unCocktail.getNom() + "'");
			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : supprimerCocktail : suppression du cocktail '" + unCocktail.getNom() + "' ok.");
		}

		public void supprimerCocktail(String nomCocktail) {
			// on supprime le cocktails :
			db.execSQL("DELETE FROM cocktail WHERE nom = '" + nomCocktail + "'");
			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : supprimerCocktail : suppression du cocktail '" + nomCocktail + "' ok.");
		}

		public int countCocktail(String nom) {
			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : recupererCocktail : Recuperation d'un cocktail dans la BDD SQLLite avec le nom : " + nom + ".");
			Cursor mCount = db.rawQuery("SELECT count(*) FROM cocktail WHERE nom=?", new String[]{nom});

			return mCount.getCount();
		}

		public Cocktail recupererCocktail(String nom) {
			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : recupererCocktail : Recuperation d'un cocktail dans la BDD SQLLite avec le nom : " + nom + ".");

			Cursor cursor = db.rawQuery("SELECT * FROM cocktail WHERE nom=?", new String[]{nom});

			Cocktail unCocktail = new Cocktail(cursor.getInt(1),
											   cursor.getString(2),
											   cursor.getString(3),
											   cursor.getString(4),
											   cursor.getString(5),
											   cursor.getString(6),
											   cursor.getString(7),
											   cursor.getString(8)
			);

			return unCocktail;
		}

		public List<Cocktail> getFavouritesCocktails() {
			List<Cocktail> favouritesCocktails = new ArrayList<Cocktail>();

			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : retrieving favourites cocktails ...");

			Cursor cursor = db.rawQuery(FIND_FAVOURITES_COCKTAILS_SQL, null);

			if (cursor.moveToFirst()) {
				do {
					// TODO
					Cocktail favouriteCocktail = new Cocktail(cursor.getInt(1),
													   cursor.getString(2),
													   cursor.getString(3),
													   cursor.getString(4),
													   cursor.getString(5),
													   cursor.getString(6),
													   cursor.getString(7),
													   cursor.getString(8)
					);

					favouritesCocktails.add(favouriteCocktail);
				}
				while (cursor.moveToNext());
			}

			return favouritesCocktails;
		}

		@Override
		public void addCocktailToFavourite(int cocktailId) {
			db.rawQuery(ADD_COCKTAIL_TO_FAVOURITES_SQL, new String[] {String.valueOf(cocktailId)});
		}

		@Override
		public void removeCocktailFromFavourite(int cocktailId) {
			db.rawQuery(REMOVE_COCKTAIL_FROM_FAVOURITE_SQL, new String[] {String.valueOf(cocktailId)});
		}

		public List<Cocktail> recupererCocktails() {
			List<Cocktail> cocktails = new ArrayList<Cocktail>();

			Log.d("GDL_action", "SQLLite --> SQLLiteUsage : recupererCocktail : Recuperation de la listes des cocktails dans la BDD SQLLite.");
			Cursor cursor = db.rawQuery(FIND_ALL_COCKTAILS_SQL, null);

			if (cursor.moveToFirst()) {
				do {
					Cocktail cocktail = new Cocktail(cursor.getInt(1),
													   cursor.getString(2),
													   cursor.getString(3),
													   cursor.getString(4),
													   cursor.getString(5),
													   cursor.getString(6),
													   cursor.getString(7),
													   cursor.getString(8)
					);

					cocktails.add(cocktail);
				}
				while (cursor.moveToNext());
			}

			return cocktails;
		}
	}

	// OUVERTURE DE LA BASE :
	public SQLLite open() {
		// on recupere l'acces a la BDD :
		db = BDDGestionnaire.getWritableDatabase();
		return this;
	}

	// ETAT DE LA CONNEXIOJN SQL :
	public boolean state() {
		return db.isOpen();
	}

	// FERMETURE DE LA BASE :
	public void close() {
		// on termeine et ferme l'acces a la BDD :
		db.close();
	}

	// GETTERS & SETTERS :
	public SQLLiteUsage getBDDUsage() {
		return BDDUsage;
	}
}