/*
 * Directeur.java
 *
 * Created on 13 janvier 2005, 16:42
 */

package P2S.Model;

import java.util.*;

/**
 *
 * @author LAFFARGUE
 */
public class Directeur extends Utilisateur{
    
    //ATTRIBUTS
    
    private Vector listeMembres;
    
    
    //CONSTRUCTEURS
    
    public Directeur(String login) {
        super(login);
        listeMembres = new Vector();
    }
    
    public Directeur(String login, String pass) {
        super(login, pass);
        listeMembres = new Vector();
    }
    
    public Directeur(String login, Vector listeMembres) {
        super(login);
        this.listeMembres = new Vector(listeMembres);
    }
    
    public Directeur(String login, String nom, String prenom) {
        super(login,nom,prenom);
        listeMembres = new Vector();
    }
    
     public Directeur(String login,String pass, String nom, String prenom) {
        super(login,pass,nom,prenom);
        listeMembres = new Vector();
    }
    
    public Directeur(String nom, String prenom, String login, Vector listeMembres) {
        super(login,nom,prenom);
        
        this.listeMembres = new Vector(listeMembres);
    }
    
    public Directeur(String nom, String pass, String prenom, String login, Vector listeMembres) {
        super(login,pass,nom,prenom);
        
        this.listeMembres = new Vector(listeMembres);
    }
    
    //METHODES
    
    public int nbMembres() {
        return this.listeMembres.size();
    }
    
    //GETTEURS ET SETTEURS
    
    public Membre getMembre(int position) {
        return (Membre)this.listeMembres.get(position);
    }
    
    public void ajouterMembre(Membre membre) {
        this.listeMembres.add(membre);
    }
}
