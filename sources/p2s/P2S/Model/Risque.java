/*
 * Risque.java
 *
 * Created on 7 février 2005, 23:59
 */

package P2S.Model;

/**
 *
 * @author Fabien
 */
public class Risque {
    
    //ATTRIBUTS
    
    private String nom;
    private String description;
    private int priorite;
    private int impact;
    private int etat;
    
    //CONSTRUCTEUR
    
    //Constructeur sans parametres
    public Risque(){}
    
    //Constructeur complet
    public Risque(String _nom, String _description, int _priorite, int _impact, int _etat) {
        this.setNom(_nom);
        this.setDescription(_description);
        this.setPriorite(_priorite);
        this.setImpact(_impact);
        this.setEtat(_etat);
    }

    //GETTEURS ET SETTEURS
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }    
}
