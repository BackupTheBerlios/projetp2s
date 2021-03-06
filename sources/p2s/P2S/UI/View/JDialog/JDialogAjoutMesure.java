package P2S.UI.View.JDialog;

import P2S.Control.Bundle.*;
import P2S.Model.*;
import P2S.UI.View.*;

/**
 *
 * @author  Fabien
 */
public class JDialogAjoutMesure extends javax.swing.JDialog {
    
    /** Creates new form JDialogAjoutMesure */
    public JDialogAjoutMesure(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.initText();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        JLabelNom = new javax.swing.JLabel();
        JLabelLimiteInf = new javax.swing.JLabel();
        JLabelDescription = new javax.swing.JLabel();
        JTextFieldNom = new javax.swing.JTextField();
        JTextFieldLimiteInf = new javax.swing.JTextField();
        JTextAreaDescription = new javax.swing.JTextArea();
        JButtonOk = new javax.swing.JButton();
        JButtonAnnuler = new javax.swing.JButton();
        JLabelLimiteSup = new javax.swing.JLabel();
        JTextFieldLimiteSup = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        JLabelNom.setText("Nom :");
        getContentPane().add(JLabelNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        JLabelLimiteInf.setText("Limite Inf\u00e9rieure :");
        getContentPane().add(JLabelLimiteInf, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        JLabelDescription.setText("Description :");
        getContentPane().add(JLabelDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        getContentPane().add(JTextFieldNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 180, -1));

        getContentPane().add(JTextFieldLimiteInf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 180, -1));

        getContentPane().add(JTextAreaDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 180, 110));

        JButtonOk.setText("OK");
        JButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOkActionPerformed(evt);
            }
        });

        getContentPane().add(JButtonOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        JButtonAnnuler.setText("Annuler");
        JButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonAnnulerActionPerformed(evt);
            }
        });

        getContentPane().add(JButtonAnnuler, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        JLabelLimiteSup.setText("Limite Sup\u00e9rieure :");
        getContentPane().add(JLabelLimiteSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        getContentPane().add(JTextFieldLimiteSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 180, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-315)/2, (screenSize.height-332)/2, 315, 332);
    }//GEN-END:initComponents

    public void initText()
    {
        this.setTitle(Bundle.getText("JDialogAjouterMesure_TitreFenetre"));
        
        JLabelNom.setText(Bundle.getText("JDialogAjouterMesure_JLabel_Nom"));
        JLabelLimiteInf.setText(Bundle.getText("JDialogAjouterMesure_JLabel_LimiteInf"));
        JLabelLimiteSup.setText(Bundle.getText("JDialogAjouterMesure_JLabel_LimiteSup"));
        JLabelDescription.setText(Bundle.getText("JDialogAjouterMesure_JLabel_Description"));
        JButtonOk.setText(Bundle.getText("JDialogAjouterMesure_JButton_Ok"));
        JButtonAnnuler.setText(Bundle.getText("JDialogAjouterMesure_JButton_Annuler"));        
    }
    
    
    private void JButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonAnnulerActionPerformed
        this.dispose();
    }//GEN-LAST:event_JButtonAnnulerActionPerformed

    private void JButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOkActionPerformed
        if((this.JTextFieldNom.getText().compareTo("") != 0) && (this.JTextFieldLimiteInf.getText().compareTo("") != 0) &&(this.JTextFieldLimiteSup.getText().compareTo("") != 0))
        {
           /* JFrameP2S.FenCreerProjet.ajouterMesure(new Mesure(JTextFieldNom.getText(),new Integer(JTextFieldLimiteInf.getText()).intValue(),new Integer(JTextFieldLimiteSup.getText()).intValue(),this.JTextAreaDescription.getText()));
            JFrameP2S.FenCreerProjet.rafarichirMesures();*/
        }
        
        this.dispose();
    }//GEN-LAST:event_JButtonOkActionPerformed
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonAnnuler;
    private javax.swing.JButton JButtonOk;
    private javax.swing.JLabel JLabelDescription;
    private javax.swing.JLabel JLabelLimiteInf;
    private javax.swing.JLabel JLabelLimiteSup;
    private javax.swing.JLabel JLabelNom;
    private javax.swing.JTextArea JTextAreaDescription;
    private javax.swing.JTextField JTextFieldLimiteInf;
    private javax.swing.JTextField JTextFieldLimiteSup;
    private javax.swing.JTextField JTextFieldNom;
    // End of variables declaration//GEN-END:variables
    
}
