/*
 * Role.java
 *
 * Created on 8 février 2005, 01:28
 */

package P2S.Model;

/**
 *
 * @author LAFFARGUE
 */
public class Role {
    private String designation;
    private String description;
    //CONSTRUCTEURS
    
    //Constructeur sans parametre
    public Role() {
    }
    
    //Constructeur complet
    public Role(String _designation,String _description){
        this.description = _description;
        this.designation = _designation;
    }

    //GETTEURS ET SETTEURS
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
