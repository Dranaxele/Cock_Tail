package com.ingesup.cocktail.metier;

import java.util.ArrayList;

/**
 * Created by alexandre on 15/10/2014.
 */
public class Cocktail {
    private int id;
    private String nom;
    private String couleur;
    private String alcool;
    private String base;
    private String ingredients;
    private String description;
    private String nomPhoto;
    private static ArrayList<Cocktail> listeCocktail = new ArrayList<Cocktail>();

    //Constructeurs
    public Cocktail(){
        super();
    }

    public Cocktail(int unId, String unNom, String uneCouleur, String unAlcool, String uneBase, String desIngredient, String uneDescription, String unNomPhoto){
        super();
        this.id = unId;
        this.nom = unNom;
        this.couleur= uneCouleur;
        this.alcool = unAlcool;
        this.base = uneBase;
        this.ingredients = desIngredient;
        this.description = uneDescription;
        this.nomPhoto = unNomPhoto;
    }

    //Getter et Setter
    public int getId() {
        return this.id;
    }
    public void setId(int unId) {
        this.id = unId;
    }

    public String getNom() {
        return this.nom;
    }
    public void setNom(String unNom) {
        this.nom = unNom;
    }

    public String getCouleur(){
        return this.couleur;
    }
    public void setCouleur(String uneCouleur){
        this.couleur = uneCouleur;
    }

    public String getAlcool(){
        return this.alcool;
    }
    public void setAlcool(String unAlcool){
        this.alcool = unAlcool;
    }

    public String getBase(){
        return this.base;
    }
    public void setBase(String uneBase){
        this.base= uneBase;
    }

    public String getIngredients(){
        return this.ingredients;
    }
    public void setIngredients(String desIngredients){
        this.ingredients = desIngredients;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String uneDescription){
        this.description = uneDescription;
    }

    public String getNomPhoto(){
        return this.nomPhoto;
    }
    public void setNomPhoto(String unNomPhoto){
        this.nomPhoto = unNomPhoto;
    }

    public void addPost(Cocktail theCocktail){
        listeCocktail.add(theCocktail);
    }

    public ArrayList<Cocktail> getPost(){
        return listeCocktail;
    }
}
