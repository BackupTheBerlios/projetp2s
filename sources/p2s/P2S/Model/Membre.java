/*
 * Membres.java
 *
 * Created on 13 janvier 2005, 16:46
 */

package P2S.Model;

/**
 *
 * @author Cox
 */
public class Membre {
    
    //ATTRIBUTS
    
    private int idMembre;
    private String nom;
    private String prenom;
    private String login;
    private String adresse;
    private String telephone;
    private String email;
    
    //CONSTRUCTEURS
    
    public Membre() {}

    public Membre(int _idMembre,String _nom,String _prenom,String _login,String _adresse,String _telephone,String _email) {
        idMembre = _idMembre;
        nom = _nom;
        prenom = _prenom;
        login = _login;
        adresse = _adresse;
        telephone = _telephone;
        email = _email;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    
}
