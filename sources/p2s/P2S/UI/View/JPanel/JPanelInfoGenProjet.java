/*
 * JPanelInfoGenProjet.java
 *
 * Created on 26 janvier 2005, 01:15
 */

package P2S.UI.View.JPanel;

import P2S.Control.Bundle.Bundle;
import P2S.Model.IndicateursProjet;
import P2S.Model.Iteration;
import P2S.Model.Projet;
import java.text.DateFormat;
import java.util.Locale;


/**
 * JPanel affichant les informations generales d'un projet
 * @author Fabien
 */
public class JPanelInfoGenProjet extends javax.swing.JPanel {
    
    /**
     * Creates new form JPanelInfoGenProjet
     * @param proj projet a afficher
     */
    public JPanelInfoGenProjet(Projet proj) {
        initComponents();
        
        // Initialisation du texte des labels
        initText();
        
        // On affiche les infos du projet
        this.LabelNomProjet.setText(proj.getNom());
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
        this.LabelDateDebut.setText(dateFormat.format(proj.getDateDebut()));
        this.LabelDateFin.setText(dateFormat.format(proj.getDateFin()));
        this.TextAreaDescription.setText(proj.getDescription());
        this.TextAreaDescription.setEditable(false);
        
        //Affichage des indicateurs g�n�raux aux projets
        IndicateursProjet ind = proj.getIndicateursProjet();
        this.LabelIndAvancementProjet.setText(new Float(ind.getAvancementProjet()).toString());
        this.LabelIndDureeMoyenneTache.setText(new Integer(ind.getDureeMoyenneTache()).toString());
        this.LabelIndNombreParticipants.setText(new Integer(ind.getNombreParticipants()).toString());
        this.LabelIndTachesTerminees.setText(new Integer(ind.getTachesTerminees()).toString());
        this.LabelIndTotalCharges.setText(new Integer(ind.getTotalCharges()).toString());
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        JButtonAlerte = new javax.swing.JButton();
        LabelStaticNomProjet = new javax.swing.JLabel();
        LabelStaticDateDebut = new javax.swing.JLabel();
        LabelStaticDateFin = new javax.swing.JLabel();
        LabelStaticDescription = new javax.swing.JLabel();
        LabelStaticIndicateursProjet = new javax.swing.JLabel();
        LabelNomProjet = new javax.swing.JLabel();
        LabelDateDebut = new javax.swing.JLabel();
        LabelDateFin = new javax.swing.JLabel();
        TextAreaDescription = new javax.swing.JTextArea();
        LabelIndTotalCharges = new javax.swing.JLabel();
        LabelIndTachesTerminees = new javax.swing.JLabel();
        LabelIndDureeMoyenneTache = new javax.swing.JLabel();
        LabelIndNombreParticipants = new javax.swing.JLabel();
        LabelIndAvancementProjet = new javax.swing.JLabel();
        LabelStaticIndTotalCharges = new javax.swing.JLabel();
        LabelStaticIndTachesTerminees = new javax.swing.JLabel();
        LabelStaticIndDureeMoyenneTache = new javax.swing.JLabel();
        LabelStaticIndNombreParticipants = new javax.swing.JLabel();
        LabelStaticIndAvancementProjet = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JButtonAlerte.setText("jButton1");
        add(JButtonAlerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        LabelStaticNomProjet.setText("Nom du projet : ");
        add(LabelStaticNomProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 77, -1));

        LabelStaticDateDebut.setText("Date de d\u00e9but : ");
        add(LabelStaticDateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        LabelStaticDateFin.setText("Date de fin : ");
        add(LabelStaticDateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 77, -1));

        LabelStaticDescription.setText("Description : ");
        add(LabelStaticDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 77, -1));

        LabelStaticIndicateursProjet.setText("Indicateurs sur le projet :");
        add(LabelStaticIndicateursProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        LabelNomProjet.setText("Nom projet");
        add(LabelNomProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 240, -1));

        LabelDateDebut.setText("Date debut");
        add(LabelDateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 270, -1));

        LabelDateFin.setText("Date fin");
        add(LabelDateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 240, -1));

        add(TextAreaDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 340, 90));

        LabelIndTotalCharges.setText("totalCharges");
        add(LabelIndTotalCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 80, -1));

        LabelIndTachesTerminees.setText("tachesTerminees");
        add(LabelIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 100, -1));

        LabelIndDureeMoyenneTache.setText("dureeMoyenneTache");
        add(LabelIndDureeMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 100, -1));

        LabelIndNombreParticipants.setText("nombreParticipants");
        add(LabelIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 120, -1));

        LabelIndAvancementProjet.setText("avancementProjet");
        add(LabelIndAvancementProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 100, -1));

        LabelStaticIndTotalCharges.setText("Total des charge :");
        add(LabelStaticIndTotalCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 170, -1));

        LabelStaticIndTachesTerminees.setText("Nombre de t\u00e2ches termin\u00e9es :");
        add(LabelStaticIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 160, -1));

        LabelStaticIndDureeMoyenneTache.setText("Dur\u00e9e moyenne des t\u00e2ches :");
        add(LabelStaticIndDureeMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 170, -1));

        LabelStaticIndNombreParticipants.setText("Nombre de participants :");
        add(LabelStaticIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 120, -1));

        LabelStaticIndAvancementProjet.setText("Avancement du projet :");
        add(LabelStaticIndAvancementProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 140, -1));

    }//GEN-END:initComponents
    
    private void initText() {
        this.LabelStaticNomProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_NomProjet"));
        this.LabelStaticDateDebut.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_DateDebut"));
        this.LabelStaticDateFin.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_DateFin"));
        this.LabelStaticDescription.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_Description"));
        this.LabelStaticIndicateursProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurProjet"));
        this.JButtonAlerte.setText(Bundle.getText("JPanelInfoGenProjet_JButton_Alerte"));
        this.LabelIndAvancementProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurAvancement"));
        this.LabelIndDureeMoyenneTache.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurMoyenneTache"));
        this.LabelIndNombreParticipants.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurParticipant"));
        this.LabelIndTachesTerminees.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurTachesTerminees"));
        this.LabelIndTotalCharges.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurTotalCharges"));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonAlerte;
    private javax.swing.JLabel LabelDateDebut;
    private javax.swing.JLabel LabelDateFin;
    private javax.swing.JLabel LabelIndAvancementProjet;
    private javax.swing.JLabel LabelIndDureeMoyenneTache;
    private javax.swing.JLabel LabelIndNombreParticipants;
    private javax.swing.JLabel LabelIndTachesTerminees;
    private javax.swing.JLabel LabelIndTotalCharges;
    private javax.swing.JLabel LabelNomProjet;
    private javax.swing.JLabel LabelStaticDateDebut;
    private javax.swing.JLabel LabelStaticDateFin;
    private javax.swing.JLabel LabelStaticDescription;
    private javax.swing.JLabel LabelStaticIndAvancementProjet;
    private javax.swing.JLabel LabelStaticIndDureeMoyenneTache;
    private javax.swing.JLabel LabelStaticIndNombreParticipants;
    private javax.swing.JLabel LabelStaticIndTachesTerminees;
    private javax.swing.JLabel LabelStaticIndTotalCharges;
    private javax.swing.JLabel LabelStaticIndicateursProjet;
    private javax.swing.JLabel LabelStaticNomProjet;
    private javax.swing.JTextArea TextAreaDescription;
    // End of variables declaration//GEN-END:variables
    
}
