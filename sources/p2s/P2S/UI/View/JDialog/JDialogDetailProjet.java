/*
 * JPanelInfoGenProjet.java
 *
 * Created on 26 janvier 2005, 01:15
 */

package P2S.UI.View.JDialog;

import P2S.Control.Bundle.Bundle;
import P2S.Model.IndicateursProjet;
import P2S.Model.Projet;
import P2S.Model.Seuil;
import P2S.UI.Graphic2D.GrapheIndicateursProjet;
import java.text.DateFormat;
import java.util.Locale;
import java.awt.Color;

import java.text.NumberFormat;
import javax.swing.JLabel;



/**
 * JPanel affichant les informations generales d'un projet
 * @author Kassem
 */
public class JDialogDetailProjet extends javax.swing.JDialog {
    
    GrapheIndicateursProjet  graphe ;
    
    /**
     * Creates new form JPanelInfoGenProjet
     * @param proj projet a afficher
     */
    
    public JDialogDetailProjet(java.awt.Frame parent, boolean modal, Projet proj) {
        super(parent, modal);
        
        initComponents();
        graphe = new GrapheIndicateursProjet(proj) ;
        getContentPane().add(new GrapheIndicateursProjet(proj), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));
        
        
        // Initialisation du texte des labels
        initText();
        
        // On affiche les infos du projet
        // this.LabelNomProjet.setText(proj.getNom());
        this.setTitle(proj.getNom());
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
        
        //Affichage des indicateurs généraux aux projets
        IndicateursProjet ind = proj.getIndicateursProjet();
        //this.LabelIndAvancementProjet.setText(new Float(ind.getAvancementProjet()).toString()+" %");
        //this.LabelIndDureeMoyenneTache.setText(new Integer(ind.getDureeMoyenneTache()).toString());
        //this.LabelIndNombreParticipants.setText(new Integer(ind.getNombreParticipants()).toString());
        //this.LabelIndTachesTerminees.setText(new Integer(ind.getTachesTerminees()).toString());
        //this.LabelIndTotalCharges.setText(new Integer(ind.getTotalCharges()).toString());
        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        
        if(ind != null){
            
            
            
            
            
            
            
            this.textIndDureeMoyenneTache.setBackground(new Color(255,255,255));
            if(new Float(ind.getDureeMoyenneTache()) != null){
                this.textIndDureeMoyenneTache.setText(new Integer(ind.getDureeMoyenneTache()).toString());
                if(Seuil.estHorsIntervalle(new Float(ind.getDureeMoyenneTache()),new Float(proj.getSeuilFixes().getDureeMoyenneTache().getSeuilMin().toString()), new Float(proj.getSeuilFixes().getDureeMoyenneTache().getSeuilMax().toString()))){
                    JLabel jIconDureeMoyenne = new JLabel("");
                    this.textIndDureeMoyenneTache.setBackground(new Color(240,200,100));
                    jIconDureeMoyenne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/warning.gif")));
                    add(jIconDureeMoyenne, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, -1));
                }
            }
            
            this.textIndNombreParticipants.setBackground(new Color(255,255,255));
            if(new Integer(ind.getNombreParticipants()) != null){
                this.textIndNombreParticipants.setText(new Integer(ind.getNombreParticipants()).toString());
                if(Seuil.estHorsIntervalle(new Integer(ind.getNombreParticipants()),new Integer(proj.getSeuilFixes().getNombreParticipants().getSeuilMin().toString()), new Integer(proj.getSeuilFixes().getNombreParticipants().getSeuilMax().toString()))){
                    this.textIndNombreParticipants.setBackground(new Color(240,200,100));
                    JLabel jLabel1 = new JLabel("");
                    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/warning.gif")));
                    add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));
                }
                
            }
            
            this.textIndTachesTerminees.setBackground(new Color(255,255,255));
            if(new Float(ind.getTachesTerminees()) != null){
                this.textIndTachesTerminees.setText(new Integer(ind.getTachesTerminees()).toString());
                if(Seuil.estHorsIntervalle(new Integer(ind.getTachesTerminees()),new Integer(proj.getSeuilFixes().getTachesTermineesProjet().getSeuilMin().toString()), new Integer(proj.getSeuilFixes().getTachesTermineesProjet().getSeuilMax().toString()))){
                    this.textIndTachesTerminees.setBackground(new Color(240,200,100));
                    JLabel jLabel2 = new JLabel("");
                    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/warning.gif")));
                    add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, -1, -1));
                }
            }
            
            this.textIndTotalCharges.setBackground(new Color(255,255,255));
            if(new Float(ind.getTotalCharges()) != null){
                this.textIndTotalCharges.setText(new Integer(ind.getTotalCharges()).toString());
                if(Seuil.estHorsIntervalle(new Integer(ind.getTotalCharges()),new Integer(proj.getSeuilFixes().getTotalChargesProjet().getSeuilMin().toString()), new Integer(proj.getSeuilFixes().getTotalChargesProjet().getSeuilMax().toString()))){
                    this.textIndTotalCharges.setBackground(new Color(240,200,100));
                    JLabel jIconTotalCharges = new JLabel("");
                    jIconTotalCharges.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/warning.gif")));
                    add(jIconTotalCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, -1, -1));
                }
            }
            
            
            
        }else{
            this.textIndDureeMoyenneTache.setText("0");
            this.textIndNombreParticipants.setText("0");
            this.textIndTachesTerminees.setText("0");
            this.textIndTotalCharges.setText("0");
        }
        pack();
        show();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        LabelStaticDateDebut = new javax.swing.JLabel();
        LabelStaticDateFin = new javax.swing.JLabel();
        LabelStaticDescription = new javax.swing.JLabel();
        LabelStaticIndicateursProjet = new javax.swing.JLabel();
        TextAreaDescription = new javax.swing.JTextArea();
        textDateDebut = new javax.swing.JTextField();
        textDateFin = new javax.swing.JTextField();
        textIndTotalCharges = new javax.swing.JTextField();
        textIndTachesTerminees = new javax.swing.JTextField();
        textIndDureeMoyenneTache = new javax.swing.JTextField();
        textIndNombreParticipants = new javax.swing.JTextField();
        textStaticIndTotalCharges = new javax.swing.JTextField();
        textStaticIndTachesTerminees = new javax.swing.JTextField();
        textStaticIndDureeMoyenneTache = new javax.swing.JTextField();
        textStaticIndNombreParticipants = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setResizable(false);
        LabelStaticDateDebut.setText("Date de d\u00e9but : ");
        getContentPane().add(LabelStaticDateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        LabelStaticDateFin.setText("Date de fin : ");
        getContentPane().add(LabelStaticDateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 77, -1));

        LabelStaticDescription.setText("Description : ");
        getContentPane().add(LabelStaticDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 77, -1));

        LabelStaticIndicateursProjet.setText("Indicateurs sur le projet :");
        getContentPane().add(LabelStaticIndicateursProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        TextAreaDescription.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(TextAreaDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 340, 90));

        textDateDebut.setEditable(false);
        textDateDebut.setText("dateDebut");
        textDateDebut.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textDateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 340, -1));

        textDateFin.setEditable(false);
        textDateFin.setText("dateFin");
        textDateFin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textDateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 340, -1));

        textIndTotalCharges.setEditable(false);
        textIndTotalCharges.setText("totalCharges");
        textIndTotalCharges.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndTotalCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 60, -1));

        textIndTachesTerminees.setEditable(false);
        textIndTachesTerminees.setText("tachesTerminees");
        textIndTachesTerminees.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 60, -1));

        textIndDureeMoyenneTache.setEditable(false);
        textIndDureeMoyenneTache.setText("dureeMoyenneTache");
        textIndDureeMoyenneTache.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndDureeMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 60, -1));

        textIndNombreParticipants.setEditable(false);
        textIndNombreParticipants.setText("nombreParticipants");
        textIndNombreParticipants.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 60, -1));

        textStaticIndTotalCharges.setEditable(false);
        textStaticIndTotalCharges.setText("Total des charges :");
        textStaticIndTotalCharges.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textStaticIndTotalCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 270, -1));

        textStaticIndTachesTerminees.setEditable(false);
        textStaticIndTachesTerminees.setText("Nombre de t\u00e2ches termin\u00e9es :");
        textStaticIndTachesTerminees.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textStaticIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 270, -1));

        textStaticIndDureeMoyenneTache.setEditable(false);
        textStaticIndDureeMoyenneTache.setText("Dur\u00e9e moyenne des t\u00e2ches :");
        textStaticIndDureeMoyenneTache.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textStaticIndDureeMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 270, -1));

        textStaticIndNombreParticipants.setEditable(false);
        textStaticIndNombreParticipants.setText("Nombre de participants :");
        textStaticIndNombreParticipants.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textStaticIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 270, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-584)/2, (screenSize.height-713)/2, 584, 713);
    }//GEN-END:initComponents
    
    private void initText() {
        //this.LabelStaticNomProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_NomProjet"));
        this.LabelStaticDateDebut.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_DateDebut"));
        this.LabelStaticDateFin.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_DateFin"));
        this.LabelStaticDescription.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_Description"));
        this.LabelStaticIndicateursProjet.setText(Bundle.getText("JPanelInfoGenProjet_JLabel_IndicateurProjet"));
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
    private javax.swing.JLabel LabelStaticDateDebut;
    private javax.swing.JLabel LabelStaticDateFin;
    private javax.swing.JLabel LabelStaticDescription;
    private javax.swing.JLabel LabelStaticIndicateursProjet;
    private javax.swing.JTextArea TextAreaDescription;
    private javax.swing.JTextField textDateDebut;
    private javax.swing.JTextField textDateFin;
    private javax.swing.JTextField textIndDureeMoyenneTache;
    private javax.swing.JTextField textIndNombreParticipants;
    private javax.swing.JTextField textIndTachesTerminees;
    private javax.swing.JTextField textIndTotalCharges;
    private javax.swing.JTextField textStaticIndDureeMoyenneTache;
    private javax.swing.JTextField textStaticIndNombreParticipants;
    private javax.swing.JTextField textStaticIndTachesTerminees;
    private javax.swing.JTextField textStaticIndTotalCharges;
    // End of variables declaration//GEN-END:variables
    
}
