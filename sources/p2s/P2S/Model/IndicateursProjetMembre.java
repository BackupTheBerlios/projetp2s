/*
 * IndicateursProjetMembre.java
 *
 * Created on 23 février 2005, 17:21
 */

package P2S.Model;

/**
 *
 * @author Guillaume
 */
public class IndicateursProjetMembre {
    
    private String nom;
    private int charges;
    private int tempsTravail;
    
    /** Creates a new instance of IndicateursProjetMembre */
    public IndicateursProjetMembre() {
    }

    public IndicateursProjetMembre(String _nom, int _charges, int _tempsTravail) {
        nom = _nom;
        charges = _charges;
        tempsTravail = _tempsTravail;
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

    public int getTempsTravail() {
        return tempsTravail;
    }

    public void setTempsTravail(int tempsTravail) {
        this.tempsTravail = tempsTravail;
    }
    
}
