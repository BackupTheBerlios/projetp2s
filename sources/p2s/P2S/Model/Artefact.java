/*
 * Artefact.java
 *
 * Created on 6 février 2005, 21:14
 */

package P2S.Model;

/**
 *
 * @author Cox
 */
public class Artefact {
    
    private int id;
    private String nom;
    private String description;
    private boolean livrable;
    private String etat;
    
    /** Creates a new instance of Artefact */
    public Artefact( int id, String nom,String description,boolean livrable,String etat) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.livrable = livrable;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isLivrable() {
        return livrable;
    }

    public void setLivrable(boolean livrable) {
        this.livrable = livrable;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
