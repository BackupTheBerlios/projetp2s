package P2S.Model;

import java.util.*;

/**
 *
 * @author  Fabien - Nicolas
 */
public class Projet {
    
    //ATTRIBUTS
    
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private Vector listeMesures;
    private Vector listeIt;
    private Vector listeMembres;
    private Vector listeArtefacts;
    private Vector listeRisques;
    
    
    //CONSTRUCTEURS
    
    /** Creates a new instance of Projet */
    
    public Projet(String nom, Vector listeMesures) {        
        this.nom = nom;
        this.description = new String("");
        this.dateDebut = new Date();
        this.dateFin = new Date();
        this.listeMesures = listeMesures;
        this.listeIt = new Vector();
        this.listeMembres = new Vector();        
        this.listeRisques = new Vector();
    }
    
    public Projet(String nom, String description) {        
        this.nom = nom;
        this.description = description;
        this.dateDebut = new Date();
        this.dateFin = new Date();
        this.listeMesures = new Vector();
        this.listeIt = new Vector();
        this.listeMembres = new Vector();        
        this.listeRisques = new Vector();
    }
    
    public Projet(String nom, String description, Vector listeMesure) {        
        this.nom = nom;
        this.description = description;
        this.dateDebut = new Date();
        this.dateFin = new Date();
        this.listeMesures = new Vector(listeMesure);
        this.listeIt = new Vector();
        this.listeMembres = new Vector();        
        this.listeRisques = new Vector();
    }
    
    public Projet(String nom, String description, Date dateDebut, Date dateFin, Vector listeMesure, Vector listeIt, Vector listeMembres) {        
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeMesures = new Vector(listeMesure);
        this.listeIt = new Vector(listeIt);
        this.listeMembres = new Vector(listeMembres);    
        this.listeRisques = new Vector();
    }
    
    public Projet(String nom, String description, Date dateDebut, Date dateFin, Vector listeMesure) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeMesures = new Vector(listeMesure);
        this.listeIt = new Vector();
        this.listeMembres = new Vector();        
        this.listeRisques = new Vector();
    }
        
    
    public void ajouterMesure(Mesure mesure) {
        this.listeMesures.add(mesure);
    }
    
    
    //GETTEURS ET SETTEURS
    
    /**
     * Getter for property _dateDebut.
     * @return Value of property _dateDebut.
     */
    public java.util.Date getDateDebut() {
        return dateDebut;
    }
    
    /**
     * Setter for property _dateDebut.
     * @param _dateDebut New value of property _dateDebut.
     */
    public void setDateDebut(java.util.Date _dateDebut) {
        this.dateDebut = _dateDebut;
    }
    
    /**
     * Getter for property _nom.
     * @return Value of property _nom.
     */
    public java.lang.String getNom() {
        return nom;
    }
    
    /**
     * Setter for property _nom.
     * @param _nom New value of property _nom.
     */
    public void setNom(java.lang.String _nom) {
        this.nom = _nom;
    }
    
    /**
     * Getter for property _dateFin.
     * @return Value of property _dateFin.
     */
    public java.util.Date getDateFin() {
        return dateFin;
    }
    
    /**
     * Setter for property _dateFin.
     * @param _dateFin New value of property _dateFin.
     */
    public void setDateFin(java.util.Date _dateFin) {
        this.dateFin = _dateFin;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
   
    public Vector getListeIt() {
        return listeIt;
    }
    
    public void setListeIt(Vector listeIt) {
        this.listeIt = listeIt;
    }
    
    public Vector getListeMembres() {
        return listeMembres;
    }
    
    public void setListeMembres(Vector listeMembres) {
        this.listeMembres = listeMembres;
    }  

    public Vector getListeRisques() {
        return listeRisques;
    }

    public void setListeRisques(Vector listeRisques) {
        this.listeRisques = listeRisques;
    }
}
