/*
 * Membres.java
 *
 * Created on 13 janvier 2005, 16:46
 */

package P2S.Model;

import java.util.Vector;

/**
 *
 * @author LAFFARGUE
 */
public class Membre {
    
    //ATTRIBUTS
    
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private Vector listeRoles;
    private Vector listeTaches;
    private Vector listeArtefacts;
    
    //CONSTRUCTEURS
    
    //Constructeur sans parametre
    public Membre() {}
    
    //Constructeur sans liste
    public Membre(String _nom,String _prenom,String _adresse,String _telephone,String _email) {
        this.nom = _nom;
        this.prenom = _prenom;
        this.adresse = _adresse;
        this.telephone = _telephone;
        this.email = _email;
        this.listeRoles = new Vector();
        this.listeTaches = new Vector();
        this.listeArtefacts = new Vector();
    }
    
    //Constructeur complet
    public Membre(String _nom,String _prenom,String _adresse,String _telephone,String _email,Vector _listeRoles,Vector _listeTaches,Vector _listeArtefacts) {
        this.nom = _nom;
        this.prenom = _prenom;
        this.adresse = _adresse;
        this.telephone = _telephone;
        this.email = _email;
        this.setListeRoles(_listeRoles);
        this.listeTaches = listeTaches;
        this.listeArtefacts = _listeArtefacts;
    }
    
    
    //GETTEURS ET SETTEURS
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Vector getListeTaches() {
        return listeTaches;
    }
    
    public void setListeTaches(Vector listeTaches) {
        this.listeTaches = listeTaches;
    }
    
    public Vector getListeArtefacts() {
        return listeArtefacts;
    }
    
    public void setListeArtefacts(Vector listeArtefacts) {
        this.listeArtefacts = listeArtefacts;
    }
    
    public Vector getListeRoles() {
        return listeRoles;
    }
    
    public void setListeRoles(Vector listeRoles) {
        this.listeRoles = listeRoles;
    }
    
}
