/*
 * Probleme.java
 *
 * Created on 8 mars 2005, 10:17
 */

package P2S.Model;

import java.util.Date;

/**
 *
 * @author Conde Mike K
 */
public class Probleme {

    
    private String nom ;
    private String cause ;
    private Date debut ;
    private Date fin ;
    
    /** Creates a new instance of Probleme */
    public Probleme (String nom, String cause, Date debut, Date fin) {
	this.nom = nom ;
	this.cause = cause ;
	this.debut = debut ;
	this.fin = fin ;
    }
    
    public String getNom() {
	return nom ;
    }
    
    public void setNom(String nom) {
	this.nom = nom ;
    }
    
    public String getCause() {
	return cause ;
    }
    
    public void setCause(String cause) {
	this.cause = cause ;
    }
    
    public Date getDebut() {
	return debut ;
    }
    
    public void setDebut(Date debut) {
	this.debut = debut ;
    }
    
    public Date getFin() {
	return fin ;
    }
    
    public void setFin(Date fin) {
	this.fin = fin ;
    }
    
}
