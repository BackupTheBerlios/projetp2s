/*
 * Membres.java
 *
 * Created on 13 janvier 2005, 16:46
 */

package P2S.Model;

import java.util.Vector;

/**
 *
 * @author Cox
 */
public class Membre {
    
    //ATTRIBUTS
    
    private int idMembre;
    private String nom;
    private String prenom;    
    private String adresse;
    private String telephone;
    private String email;
    private Vector listeTaches;
    private Vector listeArtefacts;
    
    //CONSTRUCTEURS
    
    public Membre() {}

    public Membre(int _idMembre,String _nom,String _prenom,String _adresse,String _telephone,String _email,Vector _listeTaches,Vector _listeArtefacts) {
        idMembre = _idMembre;
        nom = _nom;
        prenom = _prenom;        
        adresse = _adresse;
        telephone = _telephone;
        email = _email;
        setListeTaches(_listeTaches);
        listeArtefacts = _listeArtefacts;
    }
    
    
    //GETTEURS ET SETTEURS
    
    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

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
    
}
