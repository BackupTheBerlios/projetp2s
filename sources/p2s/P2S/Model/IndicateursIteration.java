/*
 * IndicateursIteration.java
 *
 * Created on 9 f�vrier 2005, 21:26
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
    private float dureeMoyenneTaches;
    private int nombreParticipants;
    private float chargeMoyenneParticipants;
    private float nombreMoyenTachesParticipants;

    
    //CONSTRUCTEUR
    
    //Constructeur sans param�tres
    public IndicateursIteration() {
    }
    
    //Constructeur complet
    public IndicateursIteration(int _totalCharges,int _nombreTachesTerminees,float _dureeMoyenneTaches,int _nombreParticipants,float _chargeMoyenneParticipants,float _nombreMoyenTachesParticipants){
        this.setTotalCharges(_totalCharges);
        this.setNombreTachesTerminees(_nombreTachesTerminees);
        this.setDureeMoyenneTaches(_dureeMoyenneTaches);
        this.setNombreParticipants(_nombreParticipants);
        this.setChargeMoyenneParticipants(_chargeMoyenneParticipants);
        this.setNombreMoyenTachesParticipants(_nombreMoyenTachesParticipants);
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

    public float getDureeMoyenneTaches() {
        return dureeMoyenneTaches;
    }

    public void setDureeMoyenneTaches(float dureeMoyenneTaches) {
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
        this.setNombreMoyenTachesParticipants(nombreMoyenTachesParticipants);
    }

    public void setNombreMoyenTachesParticipants(float nombreMoyenTachesParticipants) {
        this.nombreMoyenTachesParticipants = nombreMoyenTachesParticipants;
    }
    
}
