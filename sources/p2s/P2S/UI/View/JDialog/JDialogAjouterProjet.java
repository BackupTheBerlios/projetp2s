/*
 * JDialogAjouterProjet.java
 *
 * Created on 11 f�vrier 2005, 00:47
 */

package P2S.UI.View.JDialog;

import P2S.Control.Bundle.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import P2S.UI.View.*;


import javax.swing.ProgressMonitor;

/**
 *
 * @author  Fabien
 */
public class JDialogAjouterProjet extends javax.swing.JDialog {
    
    /** Creates new form JDialogAjouterProjet */
    public JDialogAjouterProjet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initTexte();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabelStaticURLProjet = new javax.swing.JLabel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jTextFieldURL = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("nb.output.selectionBackground"));
        setResizable(false);
        jLabelStaticURLProjet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/url.gif")));
        jLabelStaticURLProjet.setText("URL du fichier Projet : ");
        getContentPane().add(jLabelStaticURLProjet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jButtonOK.setText("jButton1");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        getContentPane().add(jButtonOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jButtonCancel.setText("jButton2");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        getContentPane().add(jButtonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jTextFieldURL.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldURLFocusGained(evt);
            }
        });
        jTextFieldURL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldURLKeyPressed(evt);
            }
        });

        getContentPane().add(jTextFieldURL, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 200, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-371)/2, (screenSize.height-154)/2, 371, 154);
    }//GEN-END:initComponents
    
    private void jTextFieldURLKeyPressed (java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldURLKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            jButtonOK.doClick() ;
        }
    }//GEN-LAST:event_jTextFieldURLKeyPressed
    
    private void jTextFieldURLFocusGained (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldURLFocusGained
        //jButtonOK.setSelected(true) ;
    }//GEN-LAST:event_jTextFieldURLFocusGained
    
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        String URLFichier = this.jTextFieldURL.getText();
        int flag = 1;
        if(URLFichier.compareTo("") == 0){
            javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ErrorEntreeVide"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
        } else{
            try{                
                URL url = new URL("http://"+P2S.P2S.Preferences.getProperty("host")+":"+P2S.P2S.Preferences.getProperty("port")+"/p2sserver/MAJBDServlet?login="+((JFrameP2S)this.getParent()).utilisateur.getLogin()+"&url="+URLFichier);
                
                // Buffer qui va recuperer la reponse de la servlet
                BufferedReader  in = new BufferedReader(
                        new InputStreamReader(
                        url.openStream()));
                
                //Recuperation de la reponse envoye par la Servlet
                flag = 2;
                String reponse = new String("");
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    reponse += inputLine;
                // Si le fichier est introuvable
                if(reponse.compareTo("0") == 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ErrorFichierNotFound"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
                }
                // Si une valeur qui doit etre non null est nulle dans le fichier
                else if(reponse.compareTo("1") == 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ErrorValeurNulle"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
                }
                in.close();
            } catch(MalformedURLException e1){
                javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionMalformedURL"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
            } catch(IOException e2){
                if(flag == 1)
                    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ErrorConnexionServer"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE);
                else
                    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorIO"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
            }
        }
        this.dispose();
    }//GEN-LAST:event_jButtonOKActionPerformed
    
    private void initTexte(){
        this.setTitle(Bundle.getText("JDialogAjouterProjet_TitreFenetre"));
        
        this.jButtonOK.setText(Bundle.getText("JDialogAjouterProjet_ButtonOK"));
        this.jButtonCancel.setText(Bundle.getText("JDialogAjouterProjet_ButtonCancel"));
        this.jLabelStaticURLProjet.setText(Bundle.getText("JDialogAjouterProjet_LabelStaticURLFichier"));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabelStaticURLProjet;
    private javax.swing.JTextField jTextFieldURL;
    // End of variables declaration//GEN-END:variables
    
}
