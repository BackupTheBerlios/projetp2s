/*
 * JPanelInfoGenProjet.java
 *
 * Created on 26 janvier 2005, 01:15
 */

package P2S.UI.View.JPanel;

import P2S.Control.Bundle.Bundle;
import P2S.Model.IndicateursProjet;
import P2S.Model.Projet;
import P2S.Model.Seuil;
import P2S.UI.Graphic2D.GrapheIndicateursProjet;
import P2S.UI.View.JDialog.JDialogAlerte;
import java.text.DateFormat;
import java.util.Locale;
import java.awt.Color;

import java.text.NumberFormat;



/**
 * JPanel affichant les informations generales d'un projet
 * @author Kassem
 */
public class JPanelInfoGenProjet extends javax.swing.JPanel {
    
    GrapheIndicateursProjet  graphe ;
    
    /**
     * Creates new form JPanelInfoGenProjet
     * @param proj projet a afficher
     */
    
    public JPanelInfoGenProjet(Projet proj) {
        
        initComponents();
        graphe = new GrapheIndicateursProjet(proj) ;
        add(new GrapheIndicateursProjet(proj), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));
        
        
        // Initialisation du texte des labels
        initText();
        
        // On affiche les infos du projet
        // this.LabelNomProjet.setText(proj.getNom());
        this.textNomProjet.setText(proj.getNom());
        this.textNomProjet.setBackground(new Color(255,255,255));
        this.textNomProjet.setEditable(false);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
        //this.LabelDateDebut.setText(dateFormat.format(proj.getDateDebut()));
        
        if(proj.getDateDebut() != null)
            this.textDateDebut.setText(dateFormat.format(proj.getDateDebut()));
        else
            this.textDateDebut.setText("N/C");
        
        this.textDateDebut.setBackground(new Color(255,255,255));
        this.textDateDebut.setEditable(false);
        //this.LabelDateFin.setText(dateFormat.format(proj.getDateFin()));
        if(proj.getDateFin() != null)
            this.textDateFin.setText(dateFormat.format(proj.getDateFin()));
        else
            this.textDateFin.setText("N/C");
        this.textDateFin.setBackground(new Color(255,255,255));
        this.textDateFin.setEditable(false);
        
        if(proj.getDescription() != null)
            this.TextAreaDescription.setText(proj.getDescription());
        this.TextAreaDescription.setEditable(false);
        
        //Affichage des indicateurs g�n�raux aux projets
        IndicateursProjet ind = proj.getIndicateursProjet();
        //this.LabelIndAvancementProjet.setText(new Float(ind.getAvancementProjet()).toString()+" %");
        //this.LabelIndDureeMoyenneTache.setText(new Integer(ind.getDureeMoyenneTache()).toString());
        //this.LabelIndNombreParticipants.setText(new Integer(ind.getNombreParticipants()).toString());
        //this.LabelIndTachesTerminees.setText(new Integer(ind.getTachesTerminees()).toString());
        //this.LabelIndTotalCharges.setText(new Integer(ind.getTotalCharges()).toString());
        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        
        if(ind != null){
            if(new Float(ind.getAvancementProjet()) != null)
                this.textIndAvancementProjet.setText(nf.format(new Float(ind.getAvancementProjet())).toString()+" %");
            this.textIndAvancementProjet.setBackground(new Color(255,255,255));
            
            if(new Float(ind.getDureeMoyenneTache()) != null)
                this.textIndDureeMoyenneTache.setText(new Integer(ind.getDureeMoyenneTache()).toString());
            this.textIndDureeMoyenneTache.setBackground(new Color(255,255,255));
            
            if(new Float(ind.getNombreParticipants()) != null)
                this.textIndNombreParticipants.setText(new Integer(ind.getNombreParticipants()).toString());
            this.textIndNombreParticipants.setBackground(new Color(255,255,255));
            
            if(new Float(ind.getTachesTerminees()) != null)
                this.textIndTachesTerminees.setText(new Integer(ind.getTachesTerminees()).toString());
            this.textIndTachesTerminees.setBackground(new Color(255,255,255));
            
            if(new Float(ind.getTotalCharges()) != null)
                this.textIndTotalCharges.setText(new Integer(ind.getTotalCharges()).toString());
            
            //if(Seuil.estHorsIntervalle(new Integer(ind.getTotalCharges()),new Integer(proj.getSeuilFixes().getTotalChargesProjet().getSeuilMin().toString()), new Integer(proj.getSeuilFixes().getTotalChargesProjet().getSeuilMax().toString())))
            //    this.textIndTotalCharges.setForeground(new Color(151,151,12));
            
            this.textIndTotalCharges.setBackground(new Color(255,255,255));
        }else{
            this.textIndAvancementProjet.setText("0");
            this.textIndDureeMoyenneTache.setText("0");
            this.textIndNombreParticipants.setText("0");
            this.textIndTachesTerminees.setText("0");
            this.textIndTotalCharges.setText("0");  
        }
            
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
        TextAreaDescription = new javax.swing.JTextArea();
        textNomProjet = new javax.swing.JTextField();
        textDateDebut = new javax.swing.JTextField();
        textDateFin = new javax.swing.JTextField();
        textIndTotalCharges = new javax.swing.JTextField();
        textIndTachesTerminees = new javax.swing.JTextField();
        textIndDureeMoyenneTache = new javax.swing.JTextField();
        textIndNombreParticipants = new javax.swing.JTextField();
        textIndAvancementProjet = new javax.swing.JTextField();
        textStaticIndTotalCharges = new javax.swing.JTextField();
        textStaticIndTachesTerminees = new javax.swing.JTextField();
        textStaticIndDureeMoyenneTache = new javax.swing.JTextField();
        textStaticIndNombreParticipants = new javax.swing.JTextField();
        textStaticIndAvancementProjet = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JButtonAlerte.setText("jButton1");
        JButtonAlerte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonAlerteActionPerformed(evt);
            }
        });

        add(JButtonAlerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        LabelStaticNomProjet.setText("Nom du projet : ");
        add(LabelStaticNomProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 77, -1));

        LabelStaticDateDebut.setText("Date de d\u00e9but : ");
        add(LabelStaticDateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        LabelStaticDateFin.setText("Date de fin : ");
        add(LabelStaticDateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 77, -1));

        LabelStaticDescription.setText("Description : ");
        add(LabelStaticDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 77, -1));

        LabelStaticIndicateursProjet.setText("Indicateurs sur le projet :");
        add(LabelStaticIndicateursProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        TextAreaDescription.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(TextAreaDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 340, 90));

        textNomProjet.setEditable(false);
        textNomProjet.setText("nomProjet");
        textNomProjet.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textNomProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 340, -1));

        textDateDebut.setEditable(false);
        textDateDebut.setText("dateDebut");
        textDateDebut.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textDateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 340, -1));

        textDateFin.setEditable(false);
        textDateFin.setText("dateFin");
        textDateFin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textDateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 340, -1));

        textIndTotalCharges.setEditable(false);
        textIndTotalCharges.setText("totalCharges");
        textIndTotalCharges.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndTotalCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 60, -1));

        textIndTachesTerminees.setEditable(false);
        textIndTachesTerminees.setText("tachesTerminees");
        textIndTachesTerminees.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 60, -1));

        textIndDureeMoyenneTache.setEditable(false);
        textIndDureeMoyenneTache.setText("dureeMoyenneTache");
        textIndDureeMoyenneTache.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndDureeMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 60, -1));

        textIndNombreParticipants.setEditable(false);
        textIndNombreParticipants.setText("nombreParticipants");
        textIndNombreParticipants.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 60, -1));

        textIndAvancementProjet.setEditable(false);
        textIndAvancementProjet.setText("avancemantProjet");
        textIndAvancementProjet.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndAvancementProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 60, -1));

        textStaticIndTotalCharges.setEditable(false);
        textStaticIndTotalCharges.setText("Total des charges :");
        textStaticIndTotalCharges.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textStaticIndTotalCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 270, -1));

        textStaticIndTachesTerminees.setEditable(false);
        textStaticIndTachesTerminees.setText("Nombre de t\u00e2ches termin\u00e9es :");
        textStaticIndTachesTerminees.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textStaticIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 270, -1));

        textStaticIndDureeMoyenneTache.setEditable(false);
        textStaticIndDureeMoyenneTache.setText("Dur\u00e9e moyenne des t\u00e2ches :");
        textStaticIndDureeMoyenneTache.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textStaticIndDureeMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 270, -1));

        textStaticIndNombreParticipants.setEditable(false);
        textStaticIndNombreParticipants.setText("Nombre de participants :");
        textStaticIndNombreParticipants.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textStaticIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 270, -1));

        textStaticIndAvancementProjet.setEditable(false);
        textStaticIndAvancementProjet.setText("Avancement du projet :");
        textStaticIndAvancementProjet.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textStaticIndAvancementProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 270, -1));

    }//GEN-END:initComponents
    
    private void JButtonAlerteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonAlerteActionPerformed
        new JDialogAlerte((java.awt.Frame)this.getRootPane().getParent(),true).show();
    }//GEN-LAST:event_JButtonAlerteActionPerformed
    
    private void initText() {
        this.LabelStaticNomProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_NomProjet"));
        this.LabelStaticDateDebut.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_DateDebut"));
        this.LabelStaticDateFin.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_DateFin"));
        this.LabelStaticDescription.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_Description"));
        this.LabelStaticIndicateursProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurProjet"));
        
        this.JButtonAlerte.setText(Bundle.getText("JPanelInfoGenProjet_JButton_Alerte"));
        /*this.JButtonAlerte.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    JDialogMail mail = new JDialogMail(this, true);
                }
        });*/
        /*this.LabelIndAvancementProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurAvancement"));
        this.LabelIndDureeMoyenneTache.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurMoyenneTache"));
        this.LabelIndNombreParticipants.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurParticipant"));
        this.LabelIndTachesTerminees.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurTachesTerminees"));
        this.LabelIndTotalCharges.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurTotalCharges"));*/
        
        this.textStaticIndAvancementProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurAvancement"));
        this.textStaticIndAvancementProjet.setBackground(new Color(255,255,255));
        this.textStaticIndDureeMoyenneTache.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurMoyenneTache"));
        this.textStaticIndDureeMoyenneTache.setBackground(new Color(255,255,255));
        this.textStaticIndNombreParticipants.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurParticipant"));
        this.textStaticIndNombreParticipants.setBackground(new Color(255,255,255));
        this.textStaticIndTachesTerminees.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurTachesTerminees"));
        this.textStaticIndTachesTerminees.setBackground(new Color(255,255,255));
        this.textStaticIndTotalCharges.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurTotalCharges"));
        this.textStaticIndTotalCharges.setBackground(new Color(255,255,255));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonAlerte;
    private javax.swing.JLabel LabelStaticDateDebut;
    private javax.swing.JLabel LabelStaticDateFin;
    private javax.swing.JLabel LabelStaticDescription;
    private javax.swing.JLabel LabelStaticIndicateursProjet;
    private javax.swing.JLabel LabelStaticNomProjet;
    private javax.swing.JTextArea TextAreaDescription;
    private javax.swing.JTextField textDateDebut;
    private javax.swing.JTextField textDateFin;
    private javax.swing.JTextField textIndAvancementProjet;
    private javax.swing.JTextField textIndDureeMoyenneTache;
    private javax.swing.JTextField textIndNombreParticipants;
    private javax.swing.JTextField textIndTachesTerminees;
    private javax.swing.JTextField textIndTotalCharges;
    private javax.swing.JTextField textNomProjet;
    private javax.swing.JTextField textStaticIndAvancementProjet;
    private javax.swing.JTextField textStaticIndDureeMoyenneTache;
    private javax.swing.JTextField textStaticIndNombreParticipants;
    private javax.swing.JTextField textStaticIndTachesTerminees;
    private javax.swing.JTextField textStaticIndTotalCharges;
    // End of variables declaration//GEN-END:variables
    
}
