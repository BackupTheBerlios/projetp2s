/*
 * ChefDeProjet.java
 *
 * Created on 2 mars 2005, 21:45
 */

package P2S.Model;
import java.util.Vector;
/**
 *
 * @author Cox
 */
public class ChefDeProjet extends Superviseur {
    
    
    //CONSTRUCTEURS
    public ChefDeProjet(String login) {
        super(login);
    }
    
    public ChefDeProjet(String login, String pass) {
        super(login, pass);
    }
    
    
    public ChefDeProjet(String login, Vector _projets) {
        super(login,_projets);
    }
    
    public ChefDeProjet(String login, String pass, Vector _projets) {
        super(login, pass, _projets);
    }
    
    public ChefDeProjet(String login, String nom, String prenom) {
        super(login,nom,prenom);
    }
    
    public ChefDeProjet(String login, String pass, String nom, String prenom) {
        super(login, pass, nom, prenom);
    }
   
    public ChefDeProjet(String login, String nom, String prenom, Vector _projets) {
        super(login,nom,prenom,_projets);
    }
      
    //Constructeur complet
    public ChefDeProjet(String login, String pass, String nom, String prenom, Vector _projets) {
        super(login, pass, nom, prenom, _projets);
    }

       
}
