/*
 * Directeur.java
 *
 * Created on 13 janvier 2005, 16:42
 */

package P2S.Model;

import java.util.*;

/**
 *
 * @author Cox
 */
public class Directeur extends Utilisateur{
    
    private Vector listeMembres;
    
    /** Creates a new instance of Directeur */
    
    public Directeur(String login) {
        super(login);
    }
    
    public Directeur(String login, Vector listeMembres) {
        super(login);
        this.listeMembres = new Vector(listeMembres);
    }
    
    public Directeur(String login, String nom, String prenom) {
        super(login,nom,prenom);
    }
    
    public Directeur(String nom, String prenom, String login, Vector listeMembres) {
        super(login,nom,prenom);
        
        this.listeMembres = new Vector(listeMembres);
    }
    
    public int nbMembres() {
        return this.listeMembres.size();
    }
    
    public Membre getMembre(int position) {
        return (Membre)this.listeMembres.get(position);
    }
    
    public void ajouterMembre(Membre membre) {
        this.listeMembres.add(membre);
    }
}
