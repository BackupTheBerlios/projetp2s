package P2S.Model;

import java.util.*;

/**
 *
 * @author  Fabien - Nicolas
 */
public class Projet {
    
    //ATTRIBUTS
    
    private int id;
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private Vector listeMesures;
    private Vector listeIt;
    private Vector listeMembres;
    private Vector listeArtefacts;
    
    
    //CONSTRUCTEURS
    
    /** Creates a new instance of Projet */
    
    public Projet(String nom, Vector listeMesures) {
        this.id = 1;
        this.nom = nom;
        this.description = new String("");
        this.dateDebut = new Date();
        this.dateFin = new Date();
        this.listeMesures = listeMesures;
        this.listeIt = new Vector();
        this.listeMembres = new Vector();
        this.listeArtefacts = new Vector();
    }
    
    public Projet(int idProjet, String nom, String description) {
        this.id = idProjet;
        this.nom = nom;
        this.description = description;
        this.dateDebut = new Date();
        this.dateFin = new Date();
        this.listeMesures = new Vector();
        this.listeIt = new Vector();
        this.listeMembres = new Vector();
        this.listeArtefacts = new Vector();
    }
    
    public Projet(int idProjet,String nom, String description, Vector listeMesure) {
        this.id = idProjet;
        this.nom = nom;
        this.description = description;
        this.dateDebut = new Date();
        this.dateFin = new Date();
        this.listeMesures = new Vector(listeMesure);
        this.listeIt = new Vector();
        this.listeMembres = new Vector();
        this.listeArtefacts = new Vector();
    }
    
    public Projet(int idProjet,String nom, String description, Date dateDebut, Date dateFin, Vector listeMesure, Vector listeIt, Vector listeMembres, Vector listeArtefacts) {
        this.id = idProjet;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeMesures = listeMesure;
        this.listeIt = listeIt;
        this.listeMembres = listeMembres;
        this.listeArtefacts = listeArtefacts;
    }
    
    public Projet(String nom, String description, Date dateDebut, Date dateFin, Vector listeMesure) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeMesures = listeMesure;
        this.listeIt = new Vector();
        this.listeMembres = new Vector();
        this.listeArtefacts = new Vector();
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
    
    public int getId() {
        return id;
    }
    
    public void setId(int idProjet) {
        this.id = idProjet;
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
    
    public Vector getListeArtefacts() {
        return listeArtefacts;
    }
    
    public void setListeArtefacts(Vector listeArtefacts) {
        this.listeArtefacts = listeArtefacts;
    }
}
