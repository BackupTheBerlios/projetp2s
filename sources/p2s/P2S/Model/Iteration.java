/*
 * Iteration.java
 *
 * Created on 27 janvier 2005, 23:16
 */

package P2S.Model;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Fabien
 */
public class Iteration {
    
    //ATTRIBUTS
    
    private int numero;
    private Date dateDebutPrevue;
    private Date dateDebutReelle;
    private Date dateFinPrevue;
    private Date dateFinReelle;
    private IndicateursIteration indicateursIteration;
    private Vector listeTache;
    
    //CONSTRUCTEURS
    
    //Constructeur sans parametres
    public Iteration(){}
    
    //Constructeur sans liste
    public Iteration(int _numero, Date _dateDebutPrevue, Date _dateDebutReelle, Date _dateFinPrevue, Date _dateFinReelle){
        this.setNumero(_numero);
        this.setDateDebutPrevue(_dateDebutPrevue);
        this.setDateDebutReelle(_dateDebutReelle);
        this.setDateFinPrevue(_dateFinPrevue);
        this.setDateFinReelle(_dateFinReelle);
        this.listeTache = new Vector();
        indicateursIteration = new IndicateursIteration();
    }
        
    //Constructeur sans liste avec indicateurs
    public Iteration(int _numero, Date _dateDebutPrevue, Date _dateDebutReelle, Date _dateFinPrevue, Date _dateFinReelle, IndicateursIteration _indicateursIteration){
        this.setNumero(_numero);
        this.setDateDebutPrevue(_dateDebutPrevue);
        this.setDateDebutReelle(_dateDebutReelle);
        this.setDateFinPrevue(_dateFinPrevue);
        this.setDateFinReelle(_dateFinReelle);
        this.listeTache = new Vector();
        this.indicateursIteration = _indicateursIteration;
    }
    
    //Constructeur complet
    public Iteration(int _numero, Date _dateDebutPrevue, Date _dateDebutReelle, Date _dateFinPrevue, Date _dateFinReelle, Vector _listeTache, IndicateursIteration _indicateursIteration) {
        this.setNumero(_numero);
        this.setDateDebutPrevue(_dateDebutPrevue);
        this.setDateDebutReelle(_dateDebutReelle);
        this.setDateFinPrevue(_dateFinPrevue);
        this.setDateFinReelle(_dateFinReelle);
        listeTache = _listeTache;
        indicateursIteration = _indicateursIteration;
    }
    
    //GETTEURS ET SETTEURS

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public Vector getListeTache() {
        return listeTache;
    }

    public void setListeTache(Vector listeTache) {
        this.listeTache = listeTache;
    }

    public Date getDateDebutPrevue() {
        return dateDebutPrevue;
    }

    public void setDateDebutPrevue(Date dateDebutPrevue) {
        this.dateDebutPrevue = dateDebutPrevue;
    }

    public Date getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(Date dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }
    
    public IndicateursIteration getIndicateursIteration(){
        return indicateursIteration;
    }
    
    public void setIndicateursIteration(IndicateursIteration indicateursIteration){
        this.indicateursIteration = indicateursIteration;
    }
    
}
