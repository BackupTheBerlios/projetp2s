package P2S.UI.View.JDialog;

import P2S.Control.Bundle.*;
import P2S.Control.*;

import javax.swing.*;
import java.util.*;

/**
 * Boite de dialogue contenant les preferences du logiciel
 * @author Fabien Bouyssou
 */
public class JDialogPreferences extends javax.swing.JDialog {
    
    /**
     * Cree une nouvelle instance
     * @param parent Frame Parent
     * @param modal Modal
     */
    public JDialogPreferences(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        // initialise le texte
        initTexte();
        
        P2S.P2S.ControllerLocale.addLocaleListener(new LocaleListener(){
            public void localeChanged() {
                initTexte();
            } 
        });
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        Titre = new javax.swing.JLabel();
        Langue = new javax.swing.JLabel();
        ComboBoxLangue = new javax.swing.JComboBox();
        BoutonOk = new javax.swing.JButton();
        BoutonCancel = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Titre.setFont(new java.awt.Font("MS Sans Serif", 1, 14));
        Titre.setText("PREFERENCES");
        getContentPane().add(Titre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        Langue.setFont(new java.awt.Font("MS Sans Serif", 0, 12));
        Langue.setText("Langue :");
        getContentPane().add(Langue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        ComboBoxLangue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { Bundle.getText("JDialogPreferences_ComboBoxLangue_Francais"), Bundle.getText("JDialogPreferences_ComboBoxLangue_Anglais") }));
        getContentPane().add(ComboBoxLangue, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 80, -1));

        BoutonOk.setText("jButton1");
        BoutonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonOkActionPerformed(evt);
            }
        });

        getContentPane().add(BoutonOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        BoutonCancel.setText("jButton2");
        BoutonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonCancelActionPerformed(evt);
            }
        });

        getContentPane().add(BoutonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-230)/2, (screenSize.height-224)/2, 230, 224);
    }//GEN-END:initComponents

    private void BoutonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_BoutonCancelActionPerformed

    private void initTexte()
    {
        this.setTitle(Bundle.getText("JDialogPreferences_TitreFenetre"));
        
        Titre.setText(Bundle.getText("JDialogPreferences_JLabel_Titre"));
        Langue.setText(Bundle.getText("JDialogPreferences_JLabel_Langue"));
        
        if(Locale.getDefault().toString().compareTo("fr") == 0)
            this.ComboBoxLangue.setSelectedItem(Bundle.getText("JDialogPreferences_ComboBoxLangue_Francais"));
        else if(Locale.getDefault().toString().compareTo("en") == 0)
            this.ComboBoxLangue.setSelectedItem(Bundle.getText("JDialogPreferences_ComboBoxLangue_Anglais"));
        
        this.BoutonOk.setText(Bundle.getText("JDialogPreferences_JButton_Ok"));
        this.BoutonCancel.setText(Bundle.getText("JDialogPreferences_JButton_Annuler"));
    }
    
    private void BoutonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonOkActionPerformed
        // On regarde la langue choisie et on change la Locale
        if(this.ComboBoxLangue.getSelectedItem().toString().compareTo(Bundle.getText("JDialogPreferences_ComboBoxLangue_Francais")) == 0)
        {
            Bundle.setCurrentLocale(Locale.FRENCH);
            P2S.P2S.ControllerLocale.fireLocaleChanged(); // genere un evenement changement de langue
        }
        else if(this.ComboBoxLangue.getSelectedItem().toString().compareTo(Bundle.getText("JDialogPreferences_ComboBoxLangue_Anglais")) == 0)
        {
            Bundle.setCurrentLocale(Locale.ENGLISH);
            P2S.P2S.ControllerLocale.fireLocaleChanged(); // genere un evenement changement de langue
        }
        
        this.dispose();
    }//GEN-LAST:event_BoutonOkActionPerformed
    
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonCancel;
    private javax.swing.JButton BoutonOk;
    private javax.swing.JComboBox ComboBoxLangue;
    private javax.swing.JLabel Langue;
    private javax.swing.JLabel Titre;
    // End of variables declaration//GEN-END:variables
    
}
