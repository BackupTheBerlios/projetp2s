/*
 * Seuil.java
 *
 * Created on 21 mars 2005, 15:40
 */

package P2S.Model;

/**
 *
 * @author Guillaume
 */
public class SeuilsFixes {
    
    //Les seuils généraux
    private Seuil totalChargesProjet;
    private Seuil tachesTermineesProjet;
    private Seuil dureeMoyenneTache;
    private Seuil nombreParticipants;
    private Seuil avancementProjet;
    
    //Les seuils par itération
    private Seuil totalChargesIteration;
    private Seuil tacheTermineesIteration;
    private Seuil dureeMoyenneIteration;
    private Seuil nombreParticipantIteration;
    private Seuil chargeMoyenne;
    private Seuil nombreTacheParticipant;
    
    /** Creates a new instance of Seuil */
    public SeuilsFixes() {
        
    }

    public Seuil getTotalChargesProjet() {
        return totalChargesProjet;
    }

    public void setTotalChargesProjet(Seuil totalChargesProjet) {
        this.totalChargesProjet = totalChargesProjet;
    }

    public Seuil getTachesTermineesProjet() {
        return tachesTermineesProjet;
    }

    public void setTachesTermineesProjet(Seuil tachesTermineesProjet) {
        this.tachesTermineesProjet = tachesTermineesProjet;
    }

    public Seuil getDureeMoyenneTache() {
        return dureeMoyenneTache;
    }

    public void setDureeMoyenneTache(Seuil dureeMoyenneTache) {
        this.dureeMoyenneTache = dureeMoyenneTache;
    }

    public Seuil getNombreParticipants() {
        return nombreParticipants;
    }

    public void setNombreParticipants(Seuil nombreParticipants) {
        this.nombreParticipants = nombreParticipants;
    }

    public Seuil getAvancementProjet() {
        return avancementProjet;
    }

    public void setAvancementProjet(Seuil avancementProjet) {
        this.avancementProjet = avancementProjet;
    }

    public Seuil getTotalChargesIteration() {
        return totalChargesIteration;
    }

    public void setTotalChargesIteration(Seuil totalChargesIteration) {
        this.totalChargesIteration = totalChargesIteration;
    }

    public Seuil getTacheTermineesIteration() {
        return tacheTermineesIteration;
    }

    public void setTacheTermineesIteration(Seuil tacheTermineesIteration) {
        this.tacheTermineesIteration = tacheTermineesIteration;
    }
    
    public Seuil getNombreParticipantIteration() {
        return nombreParticipantIteration;
    }

    public void setNombreParticipantIteration(Seuil nombreParticipantIteration) {
        this.nombreParticipantIteration = nombreParticipantIteration;
    }

    public Seuil getChargeMoyenne() {
        return chargeMoyenne;
    }

    public void setChargeMoyenne(Seuil chargeMoyenne) {
        this.chargeMoyenne = chargeMoyenne;
    }

    public Seuil getNombreTacheParticipant() {
        return nombreTacheParticipant;
    }

    public void setNombreTacheParticipant(Seuil nombreTacheParticipant) {
        this.nombreTacheParticipant = nombreTacheParticipant;
    }

    public Seuil getDureeMoyenneIteration() {
        return dureeMoyenneIteration;
    }

    public void setDureeMoyenneIteration(Seuil dureeMoyenneIteration) {
        this.dureeMoyenneIteration = dureeMoyenneIteration;
    }
    
    
    
}
