/*
 * Superviseur.java
 *
 * Created on 9 janvier 2005, 23:46
 */

package P2S.Model;

import java.util.*;

/**
 *
 * @author Fabien
 */
public class Superviseur extends Utilisateur{
    
    private Vector listeProjets;
    
    /** Creates a new instance of Superviseur */
    
    public Superviseur(String login) {
        super(login);        
    }
    
    public Superviseur(String login, Vector listeProjets) {
        super(login);
        this.listeProjets = new Vector(listeProjets);
    }
    
    public Superviseur(String login, String nom, String prenom) {
        super(login,nom,prenom);        
    }
    
    public Superviseur(String login, String nom, String prenom, Vector listeProjets) {
        super(login,nom,prenom);        
        this.listeProjets = new Vector(listeProjets);        
    }
                   
    public int nbProjets()
    {
        return this.listeProjets.size();
    }
    
    public Projet getProjet(int position)
    {
        return (Projet)this.listeProjets.get(position);
    }
    
    public void ajouterProjet(Projet projet)
    {
        this.listeProjets.add(projet);
    }
    
}
