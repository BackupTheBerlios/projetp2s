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
    private IndicateursProjet indicateursProjet;
    private Vector listeMesures;
    private Vector listeIt;
    private Vector listeMembres;
    private Vector listeRisques;
    private Vector listeProblemes;
    
    
    //CONSTRUCTEURS
    
    //Constructeur seulement avec le nom
    public Projet(String nom, Vector listeMesures) {        
        this.nom = nom;
        this.description = new String("");
        this.dateDebut = new Date();
        this.dateFin = new Date();
        this.listeMesures = new Vector();
        this.listeIt = new Vector();
        this.listeMembres = new Vector();        
        this.listeRisques = new Vector();
	this.listeProblemes = new Vector() ;
        this.indicateursProjet = new IndicateursProjet();
    }
    
    //Constructeur sans listes
    public Projet(String nom, String description, Date dateDebut, Date dateFin) {        
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeMesures = new Vector();
        this.listeIt = new Vector();
        this.listeMembres = new Vector();        
        this.listeRisques = new Vector();
	this.listeProblemes = new Vector() ;
        this.indicateursProjet = new IndicateursProjet();
    }   
    
    //Constructeur avec indicateur
    public Projet(String nom, String description, Date dateDebut, Date dateFin, IndicateursProjet indicateursProjet) {        
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeMesures = new Vector();
        this.listeIt = new Vector();
        this.listeMembres = new Vector();        
        this.listeRisques = new Vector();
	this.listeProblemes = new Vector() ;
        this.indicateursProjet = indicateursProjet;
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
	this.listeProblemes = new Vector() ;
        this.indicateursProjet = new IndicateursProjet();
    }
       
    
    //METHODES
    
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
    
    public Vector getListeProblemes() {
        return listeProblemes;
    }

    public void setListeProblemes(Vector listeProblemes) {
        this.listeProblemes = listeProblemes;
    }
    
    public IndicateursProjet getIndicateursProjet(){
        return indicateursProjet;
    }
    
    public void setIndicateursProjet(IndicateursProjet indicateursProjet){
        this.indicateursProjet = indicateursProjet;
    }
    
}
