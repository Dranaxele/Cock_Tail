package com.ingesup.cocktail.metier;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by alexandre on 15/10/2014.
 */
public class Cocktail implements Parcelable {
    private int id;
    private String nom;
    private String couleur;
    private String alcool;
    private String base;
    private String ingredient;
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
        this.ingredient = desIngredient;
        this.description = uneDescription;
        this.nomPhoto = unNomPhoto;
    }

    public Cocktail(Cursor cursor) {
        this.id = cursor.getInt(1);
        this.nom = cursor.getString(2);
        this.couleur = cursor.getString(3);
        // TODO finish binding
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

    public String getIngredient(){
        return this.ingredient;
    }
    public void setIngredient(String desIngredients){
        this.ingredient = desIngredients;
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

    public static ArrayList<Cocktail> getPost(){
        return listeCocktail;
    }

    public Cocktail id(final int id) {
        this.id = id;
        return this;
    }

    public Cocktail nom(final String nom) {
        this.nom = nom;
        return this;
    }

    public Cocktail couleur(final String couleur) {
        this.couleur = couleur;
        return this;
    }

    public Cocktail alcool(final String alcool) {
        this.alcool = alcool;
        return this;
    }

    public Cocktail base(final String base) {
        this.base = base;
        return this;
    }

    public Cocktail ingredient(final String ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    public Cocktail description(final String description) {
        this.description = description;
        return this;
    }

    public Cocktail nomPhoto(final String nomPhoto) {
        this.nomPhoto = nomPhoto;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nom);
        dest.writeString(this.couleur);
        dest.writeString(this.alcool);
        dest.writeString(this.base);
        dest.writeString(this.ingredient);
        dest.writeString(this.description);
        dest.writeString(this.nomPhoto);
    }

    public static Cocktail fromParcel(Parcel cocktailParcel) {
        return new Cocktail()
                .id(cocktailParcel.readInt())
                .nom(cocktailParcel.readString())
                .couleur(cocktailParcel.readString())
                .alcool(cocktailParcel.readString())
                .base(cocktailParcel.readString())
                .ingredient(cocktailParcel.readString())
                .description(cocktailParcel.readString())
                .nomPhoto(cocktailParcel.readString());
    }

    public static final Parcelable.Creator<Cocktail> CREATOR = new Creator<Cocktail>() {

        @Override
        public Cocktail createFromParcel(Parcel source) {
            return Cocktail.fromParcel(source);
        }

        @Override
        public Cocktail[] newArray(int size) {
            return new Cocktail[size];
        }
    };
}
