/*
 * Tache.java
 *
 * Created on 27 janvier 2005, 23:26
 */

package P2S.Model;

/**
 *
 * @author Fabien
 */
public class Tache {
    
    private String nom;
    private String description;
    private int tempsPasse;
    
    /** Creates a new instance of Tache */
    public Tache(String _nom, String _description, int _tempsPasse) {
        this.nom = _nom;
        this.description = _description;
        this.tempsPasse = _tempsPasse;
    }
    
}
