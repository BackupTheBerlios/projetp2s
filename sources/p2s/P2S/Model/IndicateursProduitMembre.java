/*
 * IndicateursProduitMembre.java
 *
 * Created on 23 février 2005, 18:25
 */

package P2S.Model;

/**
 *
 * @author Guillaume
 */
public class IndicateursProduitMembre {
    
    private String nom;
    private int etat;
    
    /** Creates a new instance of IndicateursProduitMembre */
    public IndicateursProduitMembre() {
    }
    
    public IndicateursProduitMembre(String _nom, int _etat) {
        nom = _nom;
        etat = _etat;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getEtat() {
        return etat;
    }
    
    public void setEtat(int etat) {
        this.etat = etat;
    }
    
}
