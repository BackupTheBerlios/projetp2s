package P2S.UI.View;

import P2S.Control.Bundle.*;
import P2S.UI.View.JDialog.*;
import P2S.Control.*;
import P2S.Model.*;
import P2S.UI.Tree.*;
import P2S.UI.View.JPanel.JPanelTousLesProjets;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.event.*;


/**
 * JFrame representant la fenetre principale de P2S
 * @author Fabien
 */
public class JFrameP2S extends javax.swing.JFrame {
    
    /**
     * jdialog pour se logger au depart du programme
     */
    public JDialogLogin JDialogLog;
    /**
     * jdialog pour creer un projet
     */
    public static JDialogCreerProjet FenCreerProjet;
    
    /**
     * utilisateur en cours
     */
    public Utilisateur utilisateur;
    
    DefaultMutableTreeNode racine;
    
    /** Creates new form JFrameP2S */
    public JFrameP2S() {
        initComponents();
        
        // Initialisation des attributs
        this.utilisateur = null;
        
        // Fen?tre plein ?cran
        this.setState(Frame.NORMAL);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setLocation(0,0);
        this.setSize(dimension);
        
        // Look & Feel de l'application
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        // Affichage de la bo?te de dialogue pour se logger
        JDialogLogin JDialogLog = new JDialogLogin(this,true);
        JDialogLog.show();
        
        verifierLogin(); // Verification du login
        
        // Initialise le texte de l'application dans la Locale actuelle
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
        jTree1 = new javax.swing.JTree();
        PanelContenu = new javax.swing.JPanel();
        JMenuBar = new javax.swing.JMenuBar();
        JMenuFichier = new javax.swing.JMenu();
        JMenuItemQuitter = new javax.swing.JMenuItem();
        JMenuOutils = new javax.swing.JMenu();
        JMenuItemRafraichir = new javax.swing.JMenuItem();
        JMenuItemPreferences = new javax.swing.JMenuItem();
        JMenuAide = new javax.swing.JMenu();
        JMenuItemAProposDe = new javax.swing.JMenuItem();

        setTitle("P2S");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jTree1.setPreferredSize(new java.awt.Dimension(150, 64));
        getContentPane().add(jTree1, java.awt.BorderLayout.WEST);

        PanelContenu.setLayout(new java.awt.BorderLayout());

        getContentPane().add(PanelContenu, java.awt.BorderLayout.CENTER);

        JMenuFichier.setBackground(new java.awt.Color(236, 233, 216));
        JMenuFichier.setText("Fichier");
        JMenuItemQuitter.setText("Quitter");
        JMenuItemQuitter.setBackground(Color.WHITE);
        JMenuItemQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemQuitterActionPerformed(evt);
            }
        });

        JMenuFichier.add(JMenuItemQuitter);

        JMenuBar.add(JMenuFichier);

        JMenuOutils.setBackground(new java.awt.Color(236, 233, 216));
        JMenuOutils.setText("Outils");
        JMenuItemRafraichir.setText("Rafraichir");
        JMenuOutils.add(JMenuItemRafraichir);

        JMenuItemPreferences.setText("Pr\u00e9f\u00e9rences...");
        JMenuItemPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemPreferencesActionPerformed(evt);
            }
        });

        JMenuOutils.add(JMenuItemPreferences);

        JMenuBar.add(JMenuOutils);

        JMenuAide.setBackground(new java.awt.Color(236, 233, 216));
        JMenuAide.setText("Aide");
        JMenuItemAProposDe.setText("A Propos de...");
        JMenuAide.add(JMenuItemAProposDe);

        JMenuBar.add(JMenuAide);

        setJMenuBar(JMenuBar);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-683)/2, (screenSize.height-523)/2, 683, 523);
    }//GEN-END:initComponents
    
    private void JMenuItemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JMenuItemQuitterActionPerformed
    
    private void JMenuItemCreerSupActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        new JDialogCreerSuperviseur(this,true).show();
    }   
    
    private void JMenuItemPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemPreferencesActionPerformed
        new JDialogPreferences(this,true).show();
    }//GEN-LAST:event_JMenuItemPreferencesActionPerformed
    
    private void JMenuItemCreerProjetActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        FenCreerProjet = new JDialogCreerProjet(this,true);
        FenCreerProjet.show();
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    private void initTexte() {
        // Initialisation des labels de la barre de menu
        JMenuFichier.setText(Bundle.getText("JMenuFichier"));
        JMenuOutils.setText(Bundle.getText("JMenuOutils"));
        JMenuAide.setText(Bundle.getText("JMenuAide"));
        
        //Initialisation des labels du menu "Fichier"
        JMenuItemQuitter.setText(Bundle.getText("JMenuItemQuitter"));
        
        //Initialisation des labels du menu "Outils"
        JMenuItemRafraichir.setText(Bundle.getText("JMenuItemRafraichir"));
        JMenuItemPreferences.setText(Bundle.getText("JMenuItemPreferences"));
        
        //Initialisation des labels du menu "Aide"
        JMenuItemAProposDe.setText(Bundle.getText("JMenuItemAProposDe"));
        
    }
    
    private void verifierLogin() {
        boolean loginOK = false;
        do
        {
            if(this.utilisateur != null) {
                loginOK = true;
                // si c'est un superviseur, on cr?e son environnement
                if(this.utilisateur instanceof Superviseur) {
                    creerEnvironnementSup();
                } else // sinon c'est un directeur
                {
                    creerEnvironnementDir();
                }
            } else {
                JOptionPane.showMessageDialog(this, new String("Login Inconnu ou mot de passe incorrect"),new String("Probl?me Login") , JOptionPane.WARNING_MESSAGE);
                JDialogLog = new JDialogLogin(this,true);
                JDialogLog.show();
            }
        }while(!loginOK);
    }
    
    
    private void creerEnvironnementSup() {
        
        // On ajoute le menu "Creer projet" au menu Outils
        JMenuItem JMenuItemCreerProjet = new JMenuItem();
        JMenuItemCreerProjet.setText(Bundle.getText("JMenuItemCreerProjet"));
        JMenuItemCreerProjet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemCreerProjetActionPerformed(evt);                
            }
        });
        JMenuOutils.add(JMenuItemCreerProjet,0);
        
        
        // Construction de l'arborescence
        
        // Premier noeud
        racine = new DefaultMutableTreeNode(Bundle.getText("NoeudSuperviseur"));
        
        // Ajout des projets supervis?s par l'utilisateur
        
        // Ajout du noeud "Projets"
        DefaultMutableTreeNode racineProjet = new DefaultMutableTreeNode(Bundle.getText("NoeudProjets"));
        
        // Ajout des projets du superviseur
        for(int i = 0 ; i < ((Superviseur) utilisateur).nbProjets(); i++){
            NoeudProjet noeudProjet = new NoeudProjet(((Superviseur) utilisateur).getProjet(i));
            racineProjet.add(noeudProjet);
        }
        racine.add(racineProjet);
        
        // Met ? jour l'arborescence
        jTree1.setModel(new DefaultTreeModel(racine));
        
        // Ajout du listener pour la selection d'un projet
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                // On recupere le noeud sur lequel on a clique
                DefaultMutableTreeNode d = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
                if(d instanceof NoeudProjet) // si le noeud est un projet
                    afficherInfoProjet(((NoeudProjet)d).getProjet());
                // si c'est le noeud "projets"
                else if(d.getUserObject() instanceof String && d.toString().compareTo(Bundle.getText("NoeudProjets")) == 0)
                {
                    Vector listeProjets = new Vector();
                    for(int i=0;i<d.getChildCount();i++)
                    {
                        listeProjets.add(((NoeudProjet)d.getChildAt(i)).getProjet());
                    }
                    
                    PanelContenu.removeAll(); // On supprime tout ce qu'il y a dans le panel contenu
                    PanelContenu.add(new JPanelTousLesProjets(listeProjets));
                    validate();
                }
            }
        }
        );        
    }
    
    
    private void creerEnvironnementDir() {
        //On ajoute le menu "Creer superviseur" dans la barre d'outils
        JMenuItem JMenuItemCreerSup = new javax.swing.JMenuItem();
        JMenuItemCreerSup.setText(Bundle.getText("JMenuItemCreerSuperviseur"));
        JMenuItemCreerSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemCreerSupActionPerformed(evt);
            }
        });
        
        JMenuOutils.add(JMenuItemCreerSup);
                        
        // Construction de l'arborescence
        
        // Premier noeud
        racine = new DefaultMutableTreeNode(Bundle.getText("NoeudRessources"));
        
        // Ajout de toutes les ressources
        
        //Ajout de la feuille "Tous les membres"
        DefaultMutableTreeNode racineMembre = new DefaultMutableTreeNode(Bundle.getText("NoeudTousLesMembres"));
        
        // Ajout des membres
        for(int i = 0 ; i < ((Directeur) utilisateur).nbMembres(); i++){
            NoeudMembre noeudMembre = new NoeudMembre(((Directeur) utilisateur).getMembre(i));
            racineMembre.add(noeudMembre);
        }
        racine.add(racineMembre);
        
        racine.add( new DefaultMutableTreeNode(Bundle.getText("NoeudProjetsEnCours")));
        
        // Met ? jour l'arborescence
        jTree1.setModel(new DefaultTreeModel(racine));
        
        // Ajout du listener pour la selection d'un membre
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                // on recupere le noeud sur lequel on clique
                DefaultMutableTreeNode d = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
                if(d instanceof NoeudMembre) // si le noeud est un membre
                    afficherInfoMembre(((NoeudMembre)d).getMembre());
            }
        }
        );
    }
    

    private void afficherInfoProjet(Projet proj)
    {
        JTabbedPaneProjet Tab = new JTabbedPaneProjet(proj);
        PanelContenu.removeAll();
        PanelContenu.add(Tab, java.awt.BorderLayout.CENTER);
        this.validate();        
    }
    

    private void afficherInfoMembre(Membre membre)
    {
        JTabbedPaneMembre Tab = new JTabbedPaneMembre(membre);
        PanelContenu.removeAll();
        PanelContenu.add(Tab, java.awt.BorderLayout.CENTER);
        this.validate();        
    }
    
    /**
     * ajoute un projet au superviseur
     * @param projet projet a ajouter
     */
    public void ajouterProjet(Projet projet) {
        // Ajout du projet dans la base de donnees
        try{
            Calendar calendarDebut = new GregorianCalendar();
            Calendar calendarFin = new GregorianCalendar();
            calendarDebut.setTime(projet.getDateDebut());
            calendarFin.setTime(projet.getDateFin());
                     
            // Envoie des infos sur lengthprojet � la servlet "AjoutProjetServlet" pour l'ajouter a la BD
            URL url = new URL("http://localhost:8084/p2sserver/AjoutProjetServlet?login="+((Superviseur)this.utilisateur).getLogin() +"&nom="+projet.getNom()+"&jourDateDebut="+calendarDebut.get(Calendar.DAY_OF_MONTH)+"&moisDateDebut="+(calendarDebut.get(Calendar.MONTH)+1)+"&anneeDateDebut="+calendarDebut.get(Calendar.YEAR)+"&jourDateFin="+calendarFin.get(Calendar.DAY_OF_MONTH)+"&moisDateFin="+(calendarFin.get(Calendar.MONTH)+1)+"&anneeDateFin="+calendarFin.get(Calendar.YEAR)+"&description="+projet.getDescription());
            
            // Buffer qui va recuperer la reponse de la servlet
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    url.openStream()));
            
            //Recuperation de la reponse envoye par la Servlet
            String reponse = new String("");
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                reponse += inputLine;
            
            if(reponse.compareTo("erreur") == 0) {
                System.out.println("Probleme lors de l'ajout du projet dans la BD");                                
            }
            in.close();
        } catch(MalformedURLException e1){
            e1.printStackTrace();
        } catch(IOException e2){
            e2.printStackTrace();
        }
        
        
        ((Superviseur)this.utilisateur).ajouterProjet(projet);
        
        DefaultMutableTreeNode noeud = this.racine.getNextNode();
        while(noeud.toString().compareTo(Bundle.getText("NoeudProjets")) != 0)
            noeud = noeud.getNextNode();
        
        noeud.add( new NoeudProjet(projet));
        // Met a jour l'arborescence
        jTree1.setModel(new DefaultTreeModel(racine));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JMenuAide;
    private javax.swing.JMenuBar JMenuBar;
    private javax.swing.JMenu JMenuFichier;
    private javax.swing.JMenuItem JMenuItemAProposDe;
    private javax.swing.JMenuItem JMenuItemPreferences;
    private javax.swing.JMenuItem JMenuItemQuitter;
    private javax.swing.JMenuItem JMenuItemRafraichir;
    private javax.swing.JMenu JMenuOutils;
    private javax.swing.JPanel PanelContenu;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
    
}
