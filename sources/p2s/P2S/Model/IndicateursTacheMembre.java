/*
 * IndicateursTacheMembre.java
 *
 * Created on 23 février 2005, 17:56
 */

package P2S.Model;

/**
 *
 * @author Guillaume
 */
public class IndicateursTacheMembre {
    
    private String nom;
    private String nomProjet;
    private int charges;
    
    /** Creates a new instance of IndicateursTacheMembre */
    public IndicateursTacheMembre() {
    }
    
    public IndicateursTacheMembre(String _nom, String _nomProjet, int _charges) {
        nom = _nom;
        nomProjet = _nomProjet;
        charges = _charges;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }
    
}
