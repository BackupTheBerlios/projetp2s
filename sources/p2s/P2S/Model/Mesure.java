package P2S.Model;

/**
 *
 * @author  Fabien
 */
public class Mesure {
    
    //ATTRIBUTS
    
    private String nom;
    private int type;
    private String valeur;
    private String description;
    
    //CONSTRUCTEURS
    
    //Constructeur sans parametres
    public Mesure(){}
    
    public Mesure(String _nom, int _type, String _valeur, String _description) {
        this.setNom(_nom);
        this.setType(_type);
        this.setValeur(_valeur);
        this.setDescription(_description);
    }

    //GETTEURS ET SETTEURS
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
