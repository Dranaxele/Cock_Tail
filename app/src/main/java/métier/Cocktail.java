package métier;

/**
 * Created by alexandre on 15/10/2014.
 */
public class Cocktail {
    private String nom;
    private String couleur;
    private Boolean alcool;
    private String base;
    private String ingrédients;
    private String description;
    private String nomPhoto;

    //Constructeurs
    public Cocktail(){
        super();
    }

    public Cocktail(String unNom, String uneCouleur, Boolean unAlcool, String uneBase, String desIngredient, String uneDescription, String unNomPhoto){
        super();
        this.nom = unNom;
        this.couleur= uneCouleur;
        this.alcool = unAlcool;
        this.base = uneBase;
        this.ingrédients = desIngredient;
        this.description = uneDescription;
        this.nomPhoto = unNomPhoto;
    }

    //Getter et Setter
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

    public Boolean getAlcool(){
        return this.alcool;
    }
    public void setAlcool(Boolean unAlcool){
        this.alcool = unAlcool;
    }

    public String getBase(){
        return this.base;
    }
    public void setBase(String uneBase){
        this.base= uneBase;
    }

    public String getIngrédients(){
        return this.ingrédients;
    }
    public void setIngrédients(String desIngrédients){
        this.ingrédients = desIngrédients;
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
}
