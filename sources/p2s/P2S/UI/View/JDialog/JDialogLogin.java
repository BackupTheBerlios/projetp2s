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
        java.awt.GridBagConstraints gridBagConstraints;

        JLabelLogin = new javax.swing.JLabel();
        JLabelMDP = new javax.swing.JLabel();
        JTextFieldLogin = new javax.swing.JTextField();
        JButtonOK = new javax.swing.JButton();
        JButtonAnnuler = new javax.swing.JButton();
        JPasswordFieldMDP = new javax.swing.JPasswordField();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        JLabelLogin.setText("Login :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        getContentPane().add(JLabelLogin, gridBagConstraints);

        JLabelMDP.setText("Mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        getContentPane().add(JLabelMDP, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 0, 47);
        getContentPane().add(JTextFieldLogin, gridBagConstraints);

        JButtonOK.setText("OK");
        JButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOKActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 8, 16, 0);
        getContentPane().add(JButtonOK, gridBagConstraints);

        JButtonAnnuler.setText("Annuler");
        JButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonAnnulerActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 53, 16, 0);
        getContentPane().add(JButtonAnnuler, gridBagConstraints);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.ipady = -2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 0, 47);
        getContentPane().add(JPasswordFieldMDP, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-305)/2, (screenSize.height-153)/2, 305, 153);
    }//GEN-END:initComponents

    private void formWindowClosed (java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0) ;
    }//GEN-LAST:event_formWindowClosed

    private void JPasswordFieldMDPFocusLost (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JPasswordFieldMDPFocusLost
        JButtonOK.setSelected(false) ;
    }//GEN-LAST:event_JPasswordFieldMDPFocusLost

    private void JPasswordFieldMDPFocusGained (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JPasswordFieldMDPFocusGained
        JButtonOK.setSelected(true) ;
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
        
        try{
            ParserXMLPreferences parserPref = new ParserXMLPreferences(P2S.P2S.readFile("P2S/preferences.xml"));
            // Envoie du login et du password a la servlet "LoginServlet" pour identifier l'utilisateur
            URL url = new URL("http://"+parserPref.lireAdresseServeur()+":"+parserPref.lirePortServeur()+"/p2sserver/LoginServlet?login="+this.JTextFieldLogin.getText()+"&password="+Md5.getEncodedPassword(this.JPasswordFieldMDP.getText()));
            
            // Buffer qui va recuperer la reponse de la servlet
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    url.openStream()));
	    
            //Recuperation du fluxXml envoye par la Servlet : LoginServlet contenant toutes les donnees de l'utilisateur
            String fluxXml = new String("");
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                fluxXml += inputLine;
                System.out.println("FLUX : " + fluxXml);
            
            if(fluxXml.compareTo("") != 0) {
                ParserXMLLog parser = new ParserXMLLog(fluxXml);
                                
                if(parser.lireFonction().compareTo("sup") == 0) {
                    ((JFrameP2S)this.getParent()).utilisateur = new Superviseur(this.JTextFieldLogin.getText(), Md5.getEncodedPassword(this.JPasswordFieldMDP.getText()), parser.lireProjets());
                } else if(parser.lireFonction().compareTo("dir") == 0) {
                    ((JFrameP2S)this.getParent()).utilisateur = new Directeur(this.JTextFieldLogin.getText(),parser.lireMembres());
                }
            }
            in.close();
        } catch(MalformedURLException e1){
            e1.printStackTrace();
        } catch(IOException e2){
            e2.printStackTrace();
        }
        this.dispose();
    }//GEN-LAST:event_JButtonOKActionPerformed
    
    private void initText(){
        this.setTitle(Bundle.getText("JDialogLogin_TitreFenetre"));
        
        JLabelLogin.setText(Bundle.getText("JDialogLogin_JLabel_Login"));
        JLabelMDP.setText(Bundle.getText("JDialogLogin_JLabel_MDP"));
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
