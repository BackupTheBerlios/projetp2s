/*
 * ChefDeProjet.java
 *
 * Created on 2 mars 2005, 21:45
 */

package P2S.Model;
import P2S.Model.Utilisateur;
/**
 *
 * @author Cox
 */
public class ChefDeProjet extends Utilisateur {
    
    //ATTRRIBUTS
    private Projet projet;
    
    //CONSTRUCTEURS
    public ChefDeProjet(String login) {
        super(login);
    }
    
    public ChefDeProjet(String login, String pass) {
        super(login, pass);
    }
    
    
    public ChefDeProjet(String login, Projet _projet) {
        super(login);
        setProjet(_projet);
    }
    
    public ChefDeProjet(String login, String pass, Projet _projet) {
        super(login, pass);
        setProjet(_projet);
    }
    
    public ChefDeProjet(String login, String nom, String prenom) {
        super(login,nom,prenom);
    }
    
    public ChefDeProjet(String login, String pass, String nom, String prenom) {
        super(login, pass, nom, prenom);
    }
    
    //Constructeur complet
    public ChefDeProjet(String login, String nom, String prenom, Projet _projet) {
        super(login,nom,prenom);
        setProjet(_projet);
    }
    
    public ChefDeProjet(String login, String pass, String nom, String prenom, Projet _projet) {
        super(login, pass, nom, prenom);
        setProjet(_projet);
    }

    
    //GETTEURS ET SETTEURS
    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    
    
}
