/*
 * Artefact.java
 *
 * Created on 6 février 2005, 21:14
 */

package P2S.Model;

import java.util.Vector;

/**
 *
 * @author LAFFARGUE
 */
public class Artefact {
    
    //ATTRIBUTS
    
    private String nom;
    private String description;
    private boolean livrable;
    private String etat;
    private Membre responsable;
    private Vector listeTachesEntrees;
    private Vector listeTachesSorties;
    
    //CONSTRUCTEURS
    
    //Constructeur sans parametre
    public Artefact(){}
    
    //Constructeur sans listes
    public Artefact(String nom,String description,boolean livrable,String etat,Membre responsable){
        this.setNom(nom);
        this.setDescription(description);
        this.setLivrable(livrable);
        this.setEtat(etat);
        this.setResponsable(responsable);
        this.listeTachesEntrees = new Vector();
        this.listeTachesSorties = new Vector();
    }
    
    //Constructeur complet
    public Artefact(String nom,String description,boolean livrable,String etat,Membre responsable,Vector _listeTachesEntrees,Vector _listeTachesSorties) {
        this.setNom(nom);
        this.setDescription(description);
        this.setLivrable(livrable);
        this.setEtat(etat);
        this.setResponsable(responsable);
        this.setListeTachesEntrees(_listeTachesEntrees);
        this.setListeTachesSorties(_listeTachesSorties);
    }
    
    //GETTEURS ET SETTEURS
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isLivrable() {
        return livrable;
    }
    
    public void setLivrable(boolean livrable) {
        this.livrable = livrable;
    }
    
    public String getEtat() {
        return etat;
    }
    
    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public Membre getResponsable() {
        return responsable;
    }
    
    public void setResponsable(Membre responsable) {
        this.responsable = responsable;
    }
    
    public Vector getListeTachesEntrees() {
        return listeTachesEntrees;
    }
    
    public void setListeTachesEntrees(Vector listeTachesEntrees) {
        this.listeTachesEntrees = listeTachesEntrees;
    }
    
    public Vector getListeTachesSorties() {
        return listeTachesSorties;
    }
    
    public void setListeTachesSorties(Vector listeTachesSorties) {
        this.listeTachesSorties = listeTachesSorties;
    }
    
}
