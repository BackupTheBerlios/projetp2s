/*
 * Utilisateur.java
 *
 * Created on 13 janvier 2005, 16:03
 */

package P2S.Model;

/**
 *
 * @author Cox
 */
public class Utilisateur {
    
    //ATTRIBUTS
    
    protected String login;
    protected String nom;
    protected String prenom;
    
    //CONSTRUCTEURS
    
    public Utilisateur(String log) {
        this.login = log;        
    }
    
    public Utilisateur(String log, String nom, String prenom) {
        this.login = log;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    //GETTEURS ET SETTEURS
    
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String _login) {
        this.login = login;
    }
    
}
