package com.ingesup.cocktail.technique;

import android.util.Log;

import com.ingesup.cocktail.metier.Cocktail;

import java.util.ArrayList;

/**
 * Created by alexandre on 15/10/2014.
 */

@SuppressWarnings("serial")
public class Cocktails extends ArrayList<Cocktail> {
    // CONSTRUCTEUR :
    public Cocktails() {
        super();
        Log.d("CK_action", "Cocktail : constructor : instanciation de la liste des gite.");
    }

    public boolean addByObject(Cocktail unCocktail) {
        try{
            this.add(unCocktail);
            Log.d("CK_action", "Cocktail : addCocktail : add a la liste des cocktails ok.");
            return true;
        } catch (Exception e){
            Log.d("CK_error", "Cocktail : addCocktail : Erreur de creation du gite dans addCocktail().");
            return false;
        }
    }
    public boolean addByData(	String nom,
                                 String couleur,
                                 String alcool,
                                 String base,
                                 String ingredient,
                                 String description,
                                 String nomPhoto) {
        try{
            // on cree un cocktail :
            Cocktail returnCocktail = new Cocktail();
            Log.d("CK_action", "Cocktail : addCocktail : instanciation d'un Cocktail ok.");

            // on set un gite :
            returnCocktail.setNom(nom);
            Log.d("CK_action", "Cocktail : addCocktail : setNom ok.");
            returnCocktail.setCouleur(couleur);
            Log.d("CK_action", "Cocktail : addCocktail: setCouleur ok.");
            returnCocktail.setAlcool(alcool);
            Log.d("CK_action", "Cocktail : addCocktail : setAlcool ok.");
            returnCocktail.setBase(base);
            Log.d("CK_action", "Cocktail : addCocktail : setBase ok.");
            returnCocktail.setIngrédients(ingredient);
            Log.d("CK_action", "Cocktail : addCocktail : setIngrédients ok.");
            returnCocktail.setDescription(description);
            Log.d("CK_action", "Cocktail : addCocktail : setDescription ok.");
            returnCocktail.setNomPhoto(nomPhoto);
            Log.d("CK_action", "Cocktail : addCocktail : setNomPhoto ok.");


            // on add le gite a la liste :
            this.addByObject(returnCocktail);
            Log.d("CK_action", "Cocktail : addCocktail : add a la liste des Cocktail ok.");

            return true;
        } catch (Exception e){
            Log.d("CK_action", "Cocktail : addCocktail : Erreur de creation du Cocktail dans addCocktail().");
            return false;
        }
    }

    public Cocktail getCocktailByName(int theId){
        return this.get(theId);
    }
}
