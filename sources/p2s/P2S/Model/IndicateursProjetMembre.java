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
    private float charges;
    private float tempsTravail;
    
    /** Creates a new instance of IndicateursProjetMembre */
    public IndicateursProjetMembre() {
    }

    public IndicateursProjetMembre(String _nom, float _charges, float _tempsTravail) {
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

    public float getCharges() {
        return charges;
    }

    public void setCharges(float charges) {
        this.charges = charges;
    }

    public float getTempsTravail() {
        return tempsTravail;
    }

    public void setTempsTravail(float tempsTravail) {
        this.tempsTravail = tempsTravail;
    }
    
}
