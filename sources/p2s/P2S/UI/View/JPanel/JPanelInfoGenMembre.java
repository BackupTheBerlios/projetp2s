/*
 * JPanelInfoGenMembre.java
 *
 * Created on 26 janvier 2005, 02:22
 */

package P2S.UI.View.JPanel;

import P2S.Control.Bundle.Bundle;
import P2S.Model.Membre;
import javax.swing.JLabel;

/**
 * JPanel contenant les informations generales sur un membres
 * @author Fabien
 */
public class JPanelInfoGenMembre extends javax.swing.JPanel {
    
    /**
     * Creates new form JPanelInfoGenMembre
     * @param membre membre a afficher
     */
    public JPanelInfoGenMembre(Membre membre) {
        initComponents();
        
        // Initialisation du texte des labels
        initText();
        
        // On affiche les infos du membre
        /*this.LabelNom.setText(membre.getNom());
        this.LabelPrenom.setText(membre.getPrenom());
        this.LabelAdresse.setText(membre.getAdresse());
        this.LabelTelephone.setText(membre.getTelephone());
        this.LabelEMail.setText(membre.getEmail());*/
        
        this.textNom.setText(membre.getNom());
        this.textPrenom.setText(membre.getPrenom());
        this.textAdresse.setText(membre.getAdresse());
        this.textTel.setText(membre.getTelephone());
        this.textMail.setText(membre.getEmail());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        LabelVide = new javax.swing.JLabel();
        textNom = new javax.swing.JTextField();
        textPrenom = new javax.swing.JTextField();
        textAdresse = new javax.swing.JTextField();
        textTel = new javax.swing.JTextField();
        textMail = new javax.swing.JTextField();
        labelStaticNom = new javax.swing.JLabel();
        labelStaticPrenom = new javax.swing.JLabel();
        labelStaticAdresse = new javax.swing.JLabel();
        labelStaticTel = new javax.swing.JLabel();
        labelStaticMail = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelVide.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        add(LabelVide, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 348));

        textNom.setBackground(new java.awt.Color(255, 255, 255));
        textNom.setEditable(false);
        textNom.setText("Nom");
        textNom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        textPrenom.setBackground(new java.awt.Color(255, 255, 255));
        textPrenom.setEditable(false);
        textPrenom.setText("Pr\u00e9nom");
        textPrenom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textPrenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 120, -1));

        textAdresse.setBackground(new java.awt.Color(255, 255, 255));
        textAdresse.setEditable(false);
        textAdresse.setText("Adresse");
        textAdresse.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textAdresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 120, -1));

        textTel.setBackground(new java.awt.Color(255, 255, 255));
        textTel.setEditable(false);
        textTel.setText("t\u00e9l\u00e9phonne");
        textTel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 120, -1));

        textMail.setBackground(new java.awt.Color(255, 255, 255));
        textMail.setEditable(false);
        textMail.setText("EMail");
        textMail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 120, -1));

        labelStaticNom.setText("jLabel1");
        add(labelStaticNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 70, -1));

        labelStaticPrenom.setText("jLabel2");
        add(labelStaticPrenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 70, -1));

        labelStaticAdresse.setText("jLabel3");
        add(labelStaticAdresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 70, -1));

        labelStaticTel.setText("jLabel4");
        add(labelStaticTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 70, -1));

        labelStaticMail.setText("jLabel5");
        add(labelStaticMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 70, -1));

    }//GEN-END:initComponents
             
    private void initText()
    {
        this.labelStaticNom.setText(Bundle.getText("JPanelInfoMembre_JLabel_Nom"));
        this.labelStaticPrenom.setText(Bundle.getText("JPanelInfoMembre_JLabel_Prenom"));
        this.labelStaticAdresse.setText(Bundle.getText("JPanelInfoMembre_JLabel_Adresse"));
        this.labelStaticTel.setText(Bundle.getText("JPanelInfoMembre_JLabel_Telephone"));
        this.labelStaticMail.setText(Bundle.getText("JPanelInfoMembre_JLabel_EMail"));
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelVide;
    private javax.swing.JLabel labelStaticAdresse;
    private javax.swing.JLabel labelStaticMail;
    private javax.swing.JLabel labelStaticNom;
    private javax.swing.JLabel labelStaticPrenom;
    private javax.swing.JLabel labelStaticTel;
    private javax.swing.JTextField textAdresse;
    private javax.swing.JTextField textMail;
    private javax.swing.JTextField textNom;
    private javax.swing.JTextField textPrenom;
    private javax.swing.JTextField textTel;
    // End of variables declaration//GEN-END:variables
    
}
