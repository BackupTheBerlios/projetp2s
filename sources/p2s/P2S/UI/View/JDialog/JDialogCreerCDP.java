package P2S.UI.View.JDialog;

import P2S.Control.Bundle.*;
import P2S.Inf.Md5;
import java.net.*;
import java.io.*;
import javax.swing.*;


/**
 * jdialog qui permet a un directeur de creer un superviseur
 * @author Fabien
 */
public class JDialogCreerCDP extends javax.swing.JDialog {
    
    /**
     * Creates new form JDialogAjoutMesure
     * @param parent frame parent de la jdialog
     * @param modal indique si la jdialog est modal ou pas
     */
    public JDialogCreerCDP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        // Initialisation du texte des labels
        this.initText();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        JButtonOk = new javax.swing.JButton();
        JButtonAnnuler = new javax.swing.JButton();
        jLabelLogin = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jPasswordMDP = new javax.swing.JPasswordField();
        jTextFieldLogin = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Creer un superviseur de projet");
        JButtonOk.setText("OK");
        JButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOkActionPerformed(evt);
            }
        });

        getContentPane().add(JButtonOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        JButtonAnnuler.setText("Annuler");
        JButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonAnnulerActionPerformed(evt);
            }
        });

        getContentPane().add(JButtonAnnuler, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jLabelLogin.setText("Login : ");
        jLabelLogin.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabelPassword.setText("Mot de passe :");
        getContentPane().add(jLabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jPasswordMDP.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11));
        jPasswordMDP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordMDPFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordMDPFocusLost(evt);
            }
        });
        jPasswordMDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordMDPKeyPressed(evt);
            }
        });

        getContentPane().add(jPasswordMDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 130, -1));

        getContentPane().add(jTextFieldLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 130, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-266)/2, (screenSize.height-173)/2, 266, 173);
    }//GEN-END:initComponents

    private void jPasswordMDPKeyPressed (java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordMDPKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            JButtonOk.doClick() ;
        }
    }//GEN-LAST:event_jPasswordMDPKeyPressed

    private void jPasswordMDPFocusLost (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordMDPFocusLost
        //JButtonOk.setSelected(false) ;
    }//GEN-LAST:event_jPasswordMDPFocusLost

    private void jPasswordMDPFocusGained (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordMDPFocusGained
        //JButtonOk.setSelected(true) ;
    }//GEN-LAST:event_jPasswordMDPFocusGained
    
    private void initText() {
        this.setTitle(Bundle.getText("JDialogCreerCDP_TitreFenetre"));

        jLabelLogin.setText(Bundle.getText("JDialogCreerCDP_JLabel_Login"));
        jLabelPassword.setText(Bundle.getText("JDialogCreerCDP_JLabel_MDP"));
        JButtonOk.setText(Bundle.getText("JDialogCreerCDP_JButton_Ok"));
        JButtonAnnuler.setText(Bundle.getText("JDialogCreerCDP_JButton_Annuler"));
    }
    
    
    private void JButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonAnnulerActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_JButtonAnnulerActionPerformed
    
    private void JButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOkActionPerformed
        
        // verification si les champs sont vides
        if (jTextFieldLogin.getText().equals("") || jPasswordMDP.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, Bundle.getText("JDialogCreerCDP_ChampsVides"),Bundle.getText("JDialogCreerCDP_CreationNOkTitle") , JOptionPane.WARNING_MESSAGE);
            return ;
        }
        
        try{           
            // Envoie du login et du password a la servlet "CreerSuperviseurServlet" pour l'ajouter a la BD
            URL url = new URL("http://"+P2S.P2S.Preferences.getProperty("host")+":"+P2S.P2S.Preferences.getProperty("port")+"/p2sserver/CreerCDPServlet?login="+this.jTextFieldLogin.getText()+"&password="+Md5.getEncodedPassword(this.jPasswordMDP.getText()));
            System.out.println("TEST");
            // Buffer qui va recuperer la reponse de la servlet
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    url.openStream()));
            
            // On recupere la reponse
            String inputLine = in.readLine();
            if(inputLine.equalsIgnoreCase("nok")){ // Si la servlet repond que ce n'est pas Ok
                this.setVisible(false); // on cache la fenetre
                // On affiche un message d'erreur
                JOptionPane.showMessageDialog(this, Bundle.getText("JDialogCreerCDP_LoginExiste"),Bundle.getText("JDialogCreerCDP_CreationNOkTitle") , JOptionPane.WARNING_MESSAGE);
                // On remet a vide le login et le password
                this.jTextFieldLogin.setText("");
                this.jPasswordMDP.setText("");
                this.show(); // on reaffiche la jdialog
            }
            else{
               this.dispose(); // on ferme la fenetre
               // On avertit le directeur que le superviseur a ete cree
               JOptionPane.showMessageDialog(this, Bundle.getText("JDialogCreerCDP_SupCree"),Bundle.getText("JDialogCreerCDP_CreationOkTitle") , JOptionPane.INFORMATION_MESSAGE);                 
            }
            in.close();
        } catch(MalformedURLException e1){
	    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorURL"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
        } catch(IOException e2){
	    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorIO"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
        } catch(IllegalArgumentException e3){
	    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorARGS"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
        }
    }//GEN-LAST:event_JButtonOkActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonAnnuler;
    private javax.swing.JButton JButtonOk;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JPasswordField jPasswordMDP;
    private javax.swing.JTextField jTextFieldLogin;
    // End of variables declaration//GEN-END:variables
    
}
