/*
 * Tache.java
 *
 * Created on 27 janvier 2005, 23:26
 */

package P2S.Model;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Fabien
 */
public class Tache {
    
    protected String nom;
    protected String description;
    protected String etat;
    protected int chargePrevue;
    protected int tempsPasse;
    protected int resteAPasser;
    protected Date dateDebutPrevue;
    protected Date dateDebutReelle;
    protected Date dateFinPrevue;
    protected Date dateFinReelle;
    protected Membre realisateur;
    protected Vector listeArtefactsEntrees;
    protected Vector listeArtefactsSorties;
    
    
    //CONSTRUCTEURS
    
    
    //Constructeur sans parametres
    public Tache() {
    }
    
    //Constructeur sans listes et sans realisateur (cf Constructeur pour la classe tacheCollaborative)
    public Tache(String _nom, String _description, String _etat,int _chargePrevue, int _tempsPasse, int _resteAPasser, Date _dateDebutPrevue, Date _dateDebutReelle,Date _dateFinPrevue,Date _dateFinReelle) {
        this.setNom(_nom);
        this.setDescription(_description);
        this.setEtat(_etat);
        this.setChargePrevue(_chargePrevue);
        this.setResteAPasser(_resteAPasser);
        this.setDateDebutPrevue(_dateDebutPrevue);
        this.setDateDebutReelle(_dateDebutReelle);
        this.setDateFinPrevue(_dateFinPrevue);
        this.setDateFinReelle(_dateFinReelle);
        this.setTempsPasse(_tempsPasse);
        this.realisateur = new Membre();
        this.listeArtefactsEntrees = new Vector();
        this.listeArtefactsSorties = new Vector();
    }
    
    //Constructeur sans listes
    public Tache(String _nom, String _description, String _etat,int _chargePrevue, int _tempsPasse, int _resteAPasser, Date _dateDebutPrevue, Date _dateDebutReelle,Date _dateFinPrevue,Date _dateFinReelle,Membre _realisateur) {
        this.setNom(_nom);
        this.setDescription(_description);
        this.setEtat(_etat);
        this.setChargePrevue(_chargePrevue);
        this.setResteAPasser(_resteAPasser);
        this.setDateDebutPrevue(_dateDebutPrevue);
        this.setDateDebutReelle(_dateDebutReelle);
        this.setDateFinPrevue(_dateFinPrevue);
        this.setDateFinReelle(_dateFinReelle);
        this.setTempsPasse(_tempsPasse);
        this.setRealisateur(_realisateur);
        this.listeArtefactsEntrees = new Vector();
        this.listeArtefactsSorties = new Vector();
    }
    
    //Constructeur complet
    public Tache(String _nom, String _description, String _etat,int _chargePrevue, int _tempsPasse, int _resteAPasser, Date _dateDebutPrevue, Date _dateDebutReelle,Date _dateFinPrevue,Date _dateFinReelle,Membre _realisateur,Vector _listeArtefactsEntrees,Vector _listeArtefactsSorties ) {
        this.setNom(_nom);
        this.setDescription(_description);
        this.setEtat(_etat);
        this.setChargePrevue(_chargePrevue);
        this.setResteAPasser(_resteAPasser);
        this.setDateDebutPrevue(_dateDebutPrevue);
        this.setDateDebutReelle(_dateDebutReelle);
        this.setDateFinPrevue(_dateFinPrevue);
        this.setDateFinReelle(_dateFinReelle);
        this.setTempsPasse(_tempsPasse);
        this.setRealisateur(_realisateur);
        this.setListeArtefactsEntrees(_listeArtefactsEntrees);
        this.setListeArtefactsSorties(_listeArtefactsSorties);
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
    
    public String getEtat() {
        return etat;
    }
    
    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public int getChargePrevue() {
        return chargePrevue;
    }
    
    public void setChargePrevue(int chargePrevue) {
        this.chargePrevue = chargePrevue;
    }
    
    public int getTempsPasse() {
        return tempsPasse;
    }
    
    public void setTempsPasse(int tempsPasse) {
        this.tempsPasse = tempsPasse;
    }
    
    public int getResteAPasser() {
        return resteAPasser;
    }
    
    public void setResteAPasser(int resteAPasser) {
        this.resteAPasser = resteAPasser;
    }
    
    public Date getDateDebutPrevue() {
        return dateDebutPrevue;
    }
    
    public void setDateDebutPrevue(Date dateDebutPrevue) {
        this.dateDebutPrevue = dateDebutPrevue;
    }
    
    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }
    
    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }
    
    public Date getDateFinPrevue() {
        return dateFinPrevue;
    }
    
    public void setDateFinPrevue(Date dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }
    
    public Date getDateFinReelle() {
        return dateFinReelle;
    }
    
    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }
    
    public Membre getRealisateur() {
        return realisateur;
    }
    
    public void setRealisateur(Membre realisateur) {
        this.realisateur = realisateur;
    }
    
    public Vector getListeArtefactsEntrees() {
        return listeArtefactsEntrees;
    }
    
    public void setListeArtefactsEntrees(Vector listeArtefactsEntrees) {
        this.listeArtefactsEntrees = listeArtefactsEntrees;
    }
    
    public Vector getListeArtefactsSorties() {
        return listeArtefactsSorties;
    }
    
    public void setListeArtefactsSorties(Vector listeArtefactsSorties) {
        this.listeArtefactsSorties = listeArtefactsSorties;
    }
    
}
