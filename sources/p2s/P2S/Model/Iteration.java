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
    
    private int numero;
    private Date dateDebutPrevue;
    private Date dateDebutReelle;
    private Date dateFinPrevue;
    private Date dateFinReelle;
    private Vector listeTache;
    
    /** Creates a new instance of Iteration */
    public Iteration(int num, Date _dateDebutPrevue, Date _dateDebutReelle, Date _dateFinPrevue, Date _dateFinReelle, Vector _listeTache) {
        this.setNumero(num);
        this.setDateDebutPrevue(_dateDebutPrevue);
        this.setDateDebutReelle(_dateDebutReelle);
        this.setDateFinPrevue(_dateFinPrevue);
        this.setDateFinReelle(_dateFinReelle);
        this.setListeTache(new Vector(_listeTache));
    }

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
    
}
