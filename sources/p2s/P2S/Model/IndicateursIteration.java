/*
 * IndicateursIteration.java
 *
 * Created on 9 février 2005, 21:26
 */

package P2S.Model;

/**
 *
 * @author LAFFARGUE
 */
public class IndicateursIteration {
    
    //ATRRIBUTS
    
    private int totalCharges;
    private int nombreTachesTerminees;
    private int dureeMoyenneTaches;
    private int nombreParticipants;
    private float chargeMoyenneParticipants;
    private float nombreMoyenTachesParticipants;

    
    //CONSTRUCTEUR
    
    //Constructeur sans paramètres
    public IndicateursIteration() {
    }
    
    //Constructeur complet
    public IndicateursIteration(int _totalCharges,int _nombreTachesTerminees,int _dureeMoyenneTaches,int _nombreParticipants,float _chargeMoyenneParticipants,float _nombreMoyenTachesParticipants){
        this.totalCharges = _totalCharges;
        this.nombreTachesTerminees = _nombreTachesTerminees;
        this.dureeMoyenneTaches = _dureeMoyenneTaches;
        this.nombreParticipants = _nombreParticipants;
        this.chargeMoyenneParticipants = _chargeMoyenneParticipants;
        this.nombreMoyenTachesParticipants = _nombreMoyenTachesParticipants;
    }
            

    //GETTEURS ET SETTEURS
    
    public int getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(int totalCharges) {
        this.totalCharges = totalCharges;
    }

    public int getNombreTachesTerminees() {
        return nombreTachesTerminees;
    }

    public void setNombreTachesTerminees(int nombreTachesTerminees) {
        this.nombreTachesTerminees = nombreTachesTerminees;
    }

    public int getDureeMoyenneTaches() {
        return dureeMoyenneTaches;
    }

    public void setDureeMoyenneTaches(int dureeMoyenneTaches) {
        this.dureeMoyenneTaches = dureeMoyenneTaches;
    }

    public int getNombreParticipants() {
        return nombreParticipants;
    }

    public void setNombreParticipants(int nombreParticipants) {
        this.nombreParticipants = nombreParticipants;
    }

    public float getChargeMoyenneParticipants() {
        return chargeMoyenneParticipants;
    }

    public void setChargeMoyenneParticipants(float chargeMoyenneParticipants) {
        this.chargeMoyenneParticipants = chargeMoyenneParticipants;
    }

    public float getNombreMoyenTachesParticipants() {
        return nombreMoyenTachesParticipants;
    }

    public void setNombreMoyenTachesParticipants(int nombreMoyenTachesParticipants) {
        this.nombreMoyenTachesParticipants = nombreMoyenTachesParticipants;
    }
    
}
