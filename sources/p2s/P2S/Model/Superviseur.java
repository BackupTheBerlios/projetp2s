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
    
    //ATTRIBUTS
    
    private Vector listeProjets;
    
    //CONSTRUCTEURS
    
    //Constructeur avec le login uniquement
    public Superviseur(String login) {
        super(login);
        this.setListeProjets(new Vector());
    }
    
    public Superviseur(String login, Vector listeProjets) {
        super(login);
        this.setListeProjets(new Vector(listeProjets));
    }
    
    public Superviseur(String login, String nom, String prenom) {
        super(login,nom,prenom);
        this.setListeProjets(new Vector());
    }
    
    //Constructeur complet
    public Superviseur(String login, String nom, String prenom, Vector listeProjets) {
        super(login,nom,prenom);
        this.setListeProjets(new Vector(listeProjets));
    }
    
    //METHODES
    
    public int nbProjets() {
        return this.getListeProjets().size();
    }
    
    public Projet getProjet(int position) {
        return (Projet)this.getListeProjets().get(position);
    }
    
    public void ajouterProjet(Projet projet) {
        this.getListeProjets().add(projet);
    }
    
    //SETTEURS ET GETTEURS
    
    public Vector getListeProjets() {
        return listeProjets;
    }
    
    public void setListeProjets(Vector listeProjets) {
        this.listeProjets = listeProjets;
    }
    
}
