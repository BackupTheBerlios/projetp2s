/*
 * IndicateursProjet.java
 *
 * Created on 9 février 2005, 21:18
 */

package P2S.Model;

/**
 *
 * @author LAFFARGUE
 */
public class IndicateursProjet {
    
    //ATTRIBUTS
    
    private int totalCharges; //Total des charges du projet
    private int tachesTerminees; //Nombre de taches terminees du projet
    private int dureeMoyenneTache; //Durée moyenne d'une tache sur le projet
    private int nombreParticipants; //Nombre de participants sur le projet
    private float avancementProjet; //Indicateur sur l'avancement du projet
    
    //CONSTRUCTEUR
    
    //Constructeur sans paramètres
    public IndicateursProjet() {
    }
    
    //Constructeur complet
    public IndicateursProjet(int _totalCharges,int _tachesTerminees,int _dureeMoyenneTache,int _nombreParticipants,float _avancementProjet) {
        this.totalCharges = _totalCharges;
        this.tachesTerminees = _tachesTerminees;
        this.dureeMoyenneTache = _dureeMoyenneTache;
        this.nombreParticipants = _nombreParticipants;
        this.avancementProjet = _avancementProjet;
    }
    
    //SETTEURS ET GETTEURS
    
    public int getTotalCharges() {
        return totalCharges;
    }
    
    public void setTotalCharges(int totalCharges) {
        this.totalCharges = totalCharges;
    }
    
    public int getTachesTerminees() {
        return tachesTerminees;
    }
    
    public void setTachesTerminees(int tachesTerminees) {
        this.tachesTerminees = tachesTerminees;
    }
    
    public int getDureeMoyenneTache() {
        return dureeMoyenneTache;
    }
    
    public void setDureeMoyenneTache(int dureeMoyenneTache) {
        this.dureeMoyenneTache = dureeMoyenneTache;
    }
    
    public int getNombreParticipants() {
        return nombreParticipants;
    }
    
    public void setNombreParticipants(int nombreParticipants) {
        this.nombreParticipants = nombreParticipants;
    }
    
    public float getAvancementProjet() {
        return avancementProjet;
    }
    
    public void setAvancementProjet(float avancementProjet) {
        this.avancementProjet = avancementProjet;
    }
    
}
