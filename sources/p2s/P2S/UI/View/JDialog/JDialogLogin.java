package P2S.UI.View.JDialog;

import P2S.Control.Bundle.*;
import P2S.UI.View.*;
import P2S.Model.*;
import P2S.Inf.*;
import java.net.*;
import java.io.*;




/**
 * JDialog permettant ? un utilisateur de se connecter ? l'application en tant que directeur ou superviseur
 * @author Fabien
 */
public class JDialogLogin extends javax.swing.JDialog {
    
    private boolean OK;
    private boolean fermerApplication = true ; // sera verifie lors de la fermeture de la boite de dialogue
    
    /**
     * Creates new form JDialogLogin
     * @param parent frame parent de la jdialog
     * @param modal indique si la jdialog doit etre modal ou pas
     */
    public JDialogLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();        
        
        // Initialisation du texte
        initText();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        JLabelLogin = new javax.swing.JLabel();
        JLabelMDP = new javax.swing.JLabel();
        JTextFieldLogin = new javax.swing.JTextField();
        JButtonOK = new javax.swing.JButton();
        JButtonAnnuler = new javax.swing.JButton();
        JPasswordFieldMDP = new javax.swing.JPasswordField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        JLabelLogin.setText("Login :");
        getContentPane().add(JLabelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        JLabelMDP.setText("Mot de passe :");
        getContentPane().add(JLabelMDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        getContentPane().add(JTextFieldLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 130, -1));

        JButtonOK.setText("OK");
        JButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOKActionPerformed(evt);
            }
        });

        getContentPane().add(JButtonOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        JButtonAnnuler.setText("Annuler");
        JButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonAnnulerActionPerformed(evt);
            }
        });

        getContentPane().add(JButtonAnnuler, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        JPasswordFieldMDP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JPasswordFieldMDPFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                JPasswordFieldMDPFocusLost(evt);
            }
        });
        JPasswordFieldMDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JPasswordFieldMDPKeyPressed(evt);
            }
        });

        getContentPane().add(JPasswordFieldMDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 130, 20));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-280)/2, (screenSize.height-181)/2, 280, 181);
    }//GEN-END:initComponents

    private void formWindowClosed (java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        /*if (fermerApplication)
        {
           System.exit(0) ;
        }*/
    }//GEN-LAST:event_formWindowClosed

    private void JPasswordFieldMDPFocusLost (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JPasswordFieldMDPFocusLost
        JButtonOK.setSelected(false) ;
    }//GEN-LAST:event_JPasswordFieldMDPFocusLost

    private void JPasswordFieldMDPFocusGained (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JPasswordFieldMDPFocusGained
        //JButtonOK.setSelected(true) ;
    }//GEN-LAST:event_JPasswordFieldMDPFocusGained

    private void JPasswordFieldMDPKeyPressed (java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JPasswordFieldMDPKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            JButtonOK.doClick() ;
        }
            
    }//GEN-LAST:event_JPasswordFieldMDPKeyPressed
    
    private void JButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonAnnulerActionPerformed
        System.exit(0); // On quitte l'application
    }//GEN-LAST:event_JButtonAnnulerActionPerformed
    
    private void JButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOKActionPerformed
        fermerApplication = false ;
        try{     
            
            // Envoie du login et du password a la servlet "LoginServlet" pour identifier l'utilisateur
            URL url = new URL("http://"+P2S.P2S.Preferences.getProperty("host")+":"+P2S.P2S.Preferences.getProperty("port")+"/p2sserver/LoginServlet?login="+this.JTextFieldLogin.getText()+"&password="+Md5.getEncodedPassword(this.JPasswordFieldMDP.getText()));
            
            // Buffer qui va recuperer la reponse de la servlet
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    url.openStream()));
	    
            //Recuperation du fluxXml envoye par la Servlet : LoginServlet contenant toutes les donnees de l'utilisateur
            String fluxXml = new String("");
            String inputLine;
            
            while ((inputLine = in.readLine()) != null){
                fluxXml += inputLine;                
            }
            System.out.println("FLUX : " + fluxXml);
            
            
                            
            
            if(fluxXml.compareTo("") != 0) {
                ParserXMLLog parser = new ParserXMLLog(fluxXml);
                                
                if(parser.lireFonction().compareTo("sup") == 0) {
                    ((JFrameP2S)this.getParent()).utilisateur = new Superviseur(this.JTextFieldLogin.getText(), Md5.getEncodedPassword(this.JPasswordFieldMDP.getText()), parser.lireProjets(), parser.lireMessages());
                } else if(parser.lireFonction().compareTo("dir") == 0) {
                    ((JFrameP2S)this.getParent()).utilisateur = new Directeur(this.JTextFieldLogin.getText(),parser.lireMembres());
                }else if(parser.lireFonction().compareTo("cdp") == 0) {
                    ((JFrameP2S)this.getParent()).utilisateur = new ChefDeProjet(this.JTextFieldLogin.getText(),Md5.getEncodedPassword(this.JPasswordFieldMDP.getText()), parser.lireProjets(), parser.lireMessages());
                }
            }
            in.close();
            
        } catch(MalformedURLException e1){
	    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorURL"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
        } catch(IOException e2){
	    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ErrorConnexionServer"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch(IllegalArgumentException e3){
            e3.printStackTrace();
	    javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorARGS"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
            e3.printStackTrace();
        } 
        
        this.dispose();
    }//GEN-LAST:event_JButtonOKActionPerformed
    
    private void initText(){
        this.setTitle(Bundle.getText("JDialogLogin_TitreFenetre"));
        
        JLabelLogin.setText(Bundle.getText("JDialogLogin_JLabel_Login"));
        JLabelLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/tree_members.gif")));
        JLabelMDP.setText(Bundle.getText("JDialogLogin_JLabel_MDP"));
        JLabelMDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/login_cle.gif")));
        
        JButtonOK.setText(Bundle.getText("JDialogLogin_JButton_Ok"));
        JButtonAnnuler.setText(Bundle.getText("JDialogLogin_JButton_Annuler"));        
    }


    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonAnnuler;
    private javax.swing.JButton JButtonOK;
    private javax.swing.JLabel JLabelLogin;
    private javax.swing.JLabel JLabelMDP;
    private javax.swing.JPasswordField JPasswordFieldMDP;
    private javax.swing.JTextField JTextFieldLogin;
    // End of variables declaration//GEN-END:variables

}
