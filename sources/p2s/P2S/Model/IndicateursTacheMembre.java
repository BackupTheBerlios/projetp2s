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
    private int tempsPasse;
    
    /** Creates a new instance of IndicateursTacheMembre */
    public IndicateursTacheMembre() {
    }
    
    public IndicateursTacheMembre(String _nom, int _tempsPasse) {
        nom = _nom;
        tempsPasse = _tempsPasse;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(int tempsPasse) {
        this.tempsPasse = tempsPasse;
    }
    
}
