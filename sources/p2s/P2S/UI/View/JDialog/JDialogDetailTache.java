package P2S.UI.View.JDialog;

import P2S.Control.Bundle.*;
import P2S.Model.*;
import java.text.DateFormat;
import java.util.Locale;
import java.awt.Color;

import java.text.NumberFormat;

/**
 *
 * @author  kassem
 */
public class JDialogDetailTache extends javax.swing.JDialog {
   /*private String[] titresColonnes = {Bundle.getText("JTableRisquesColonne1"),
                Bundle.getText("JTableRisquesColonne2"),
                Bundle.getText("JTableRisquesColonne3"),
                Bundle.getText("JTableRisquesColonne4"),
                Bundle.getText("JTableRisquesColonne5")} ; ;
    private Object[][] donnees = null ;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tableScrollPane;*/
    
    /** Creates new form JDialogAjoutMesure */
    public JDialogDetailTache(java.awt.Frame parent, boolean modal, Tache tacheDetail) {
        super(parent, modal);
        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        
        initComponents();
        this.initText();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
        this.textIndNom.setText(tacheDetail.getNom());
        this.textIndNom.setBackground(new Color(255,255,255));
        this.textIndRealisateur.setText((tacheDetail.getRealisateur()).getNom());
        this.textIndRealisateur.setBackground(new Color(255,255,255));
        this.textDescription.setText(tacheDetail.getDescription());
        this.textIndCharge.setText(new Integer(tacheDetail.getChargePrevue()).toString());
        this.textIndCharge.setBackground(new Color(255,255,255));
        this.textIndTempsPasse.setText(nf.format(new Float(tacheDetail.getTempsPasse())).toString());
        this.textIndTempsPasse.setBackground(new Color(255,255,255));
        this.textIndTempsRestant.setText(nf.format(new Float(tacheDetail.getResteAPasser())).toString());
        this.textIndTempsRestant.setBackground(new Color(255,255,255));
        
        if(tacheDetail.getDateDebutPrevue() != null){
            this.textIndDateDebutPrevue.setText(dateFormat.format(tacheDetail.getDateDebutPrevue()));
        }else{
            this.textIndDateDebutPrevue.setText("N/C");          
        }
        this.textIndDateDebutPrevue.setBackground(new Color(255,255,255));
        
        
        if(tacheDetail.getDateDebutReelle() != null){
            this.textIndDateDebutReelle.setText(dateFormat.format(tacheDetail.getDateDebutReelle()));
        }else{
            this.textIndDateDebutReelle.setText("N/C");
            
        }
        this.textIndDateDebutReelle.setBackground(new Color(255,255,255));
        
        
        if(tacheDetail.getDateFinPrevue() != null){
            this.textIndDateFinPrevue.setText(dateFormat.format(tacheDetail.getDateFinPrevue()));
        }else{
            this.textIndDateFinPrevue.setText("N/C");
        }
        this.textIndDateFinPrevue.setBackground(new Color(255,255,255));
        
        
        if(tacheDetail.getDateFinReelle() != null){
            this.textIndDateFinReelle.setText(dateFormat.format(tacheDetail.getDateFinReelle()));
        }else{
            this.textIndDateFinReelle.setText("N/C");
        }
        this.textIndDateFinReelle.setBackground(new Color(255,255,255));
        
        
        //affichage de l'état
        Object value = tacheDetail.getEtat();
        this.textIndEtat.setText(Bundle.getText("Constante_tache"+value)) ;
        if (((String)value).equals("1")) {
            textIndEtat.setForeground(new Color(20, 20, 250)) ;
        }
        if (((String)value).equals("3")) {
            textIndEtat.setForeground(new Color(250, 20, 20)) ;
        }
        
        this.textIndEtat.setBackground(new Color(255,255,255));
        System.out.println(value);
        //recupération de la liste des artefacts en entrées
        /*Vector artefactsEntrees = new Vector();
        artefactsEntrees = tacheDetail.getListeArtefactsEntrees();
        donnees = new Object[artefactsEntrees.size()][5] ;
        for (int i = 0 ; i < donnees.length ; i++)
        {
            if (artefactsEntrees.get(i) instanceof Artefact)
            {
                donnees[i][0] = ((Artefact)artefactsEntrees.get(i)).getNom() ;
                donnees[i][1] = ((Artefact)artefactsEntrees.get(i)).getDescription() ;
                donnees[i][2] = ((Artefact)artefactsEntrees.get(i)).isLivrable() ;
                donnees[i][3] = ((Artefact)artefactsEntrees.get(i)).getResponsable() ;
                donnees[i][4] = ((Artefact)artefactsEntrees.get(i)).getEtat() ;
            }
        }
         
        /*ModeleTableMesure tableModel = new ModeleTableMesure(donnees, titresColonnes) ;
        table = new JTable(tableModel) ;
        tableScrollPane = new JScrollPane(table) ;
        tableScrollPane.setViewportView(table);
        this.getContentPane().add(tableScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 292, 410, 20));*/
        pack() ;
        show() ;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        labelStaticIndNom = new javax.swing.JLabel();
        textIndNom = new javax.swing.JTextField();
        labelStaticIndEtat = new javax.swing.JLabel();
        textIndEtat = new javax.swing.JTextField();
        labelDescription = new javax.swing.JLabel();
        textDescription = new javax.swing.JTextArea();
        labelStaticIndRealisateur = new javax.swing.JLabel();
        textIndRealisateur = new javax.swing.JTextField();
        labelStaticIndChargePrevue = new javax.swing.JLabel();
        textIndCharge = new javax.swing.JTextField();
        labelStaticIndTpsPasse = new javax.swing.JLabel();
        textIndTempsPasse = new javax.swing.JTextField();
        labelStaticIndTempsRestant = new javax.swing.JLabel();
        textIndTempsRestant = new javax.swing.JTextField();
        labelStaticIndDatePrevue = new javax.swing.JLabel();
        textIndDateDebutPrevue = new javax.swing.JTextField();
        labelStaticIndDateDebutReelle = new javax.swing.JLabel();
        textIndDateDebutReelle = new javax.swing.JTextField();
        labelStaticDateFinPrevue = new javax.swing.JLabel();
        textIndDateFinPrevue = new javax.swing.JTextField();
        labelStaticIndDateFinReelle = new javax.swing.JLabel();
        textIndDateFinReelle = new javax.swing.JTextField();
        buttonOk = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        labelStaticIndNom.setText("Nom :");
        getContentPane().add(labelStaticIndNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        textIndNom.setEditable(false);
        textIndNom.setText("nom");
        textIndNom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, -1));

        labelStaticIndEtat.setText("Etat :");
        getContentPane().add(labelStaticIndEtat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 100, -1));

        textIndEtat.setEditable(false);
        textIndEtat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndEtat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 170, -1));

        labelDescription.setText("Description :");
        getContentPane().add(labelDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 100, -1));

        textDescription.setEditable(false);
        textDescription.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 170, 50));

        labelStaticIndRealisateur.setText("R\u00e9alisateur :");
        getContentPane().add(labelStaticIndRealisateur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, -1));

        textIndRealisateur.setEditable(false);
        textIndRealisateur.setText("reallisateur");
        textIndRealisateur.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndRealisateur, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 170, -1));

        labelStaticIndChargePrevue.setText("Charge pr\u00e9vue :");
        getContentPane().add(labelStaticIndChargePrevue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, -1));

        textIndCharge.setEditable(false);
        textIndCharge.setText("charge");
        textIndCharge.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndCharge, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 170, -1));

        labelStaticIndTpsPasse.setText("Temps pass\u00e9 :");
        getContentPane().add(labelStaticIndTpsPasse, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 100, -1));

        textIndTempsPasse.setEditable(false);
        textIndTempsPasse.setText("temps pass\u00e9");
        textIndTempsPasse.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndTempsPasse, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 170, -1));

        labelStaticIndTempsRestant.setText("Temps Restant :");
        getContentPane().add(labelStaticIndTempsRestant, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, -1));

        textIndTempsRestant.setEditable(false);
        textIndTempsRestant.setText("restant");
        textIndTempsRestant.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndTempsRestant, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 170, -1));

        labelStaticIndDatePrevue.setText("Date d\u00e9but pr\u00e9vue :");
        getContentPane().add(labelStaticIndDatePrevue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 100, -1));

        textIndDateDebutPrevue.setEditable(false);
        textIndDateDebutPrevue.setText("date pr\u00e9vue");
        textIndDateDebutPrevue.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndDateDebutPrevue, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 170, -1));

        labelStaticIndDateDebutReelle.setText("Date d\u00e9but r\u00e9elle :");
        getContentPane().add(labelStaticIndDateDebutReelle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 100, -1));

        textIndDateDebutReelle.setEditable(false);
        textIndDateDebutReelle.setText("date r\u00e9elle");
        textIndDateDebutReelle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndDateDebutReelle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 170, -1));

        labelStaticDateFinPrevue.setText("Date fin pr\u00e9vue :");
        getContentPane().add(labelStaticDateFinPrevue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 100, -1));

        textIndDateFinPrevue.setEditable(false);
        textIndDateFinPrevue.setText("date fin");
        textIndDateFinPrevue.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndDateFinPrevue, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 170, -1));

        labelStaticIndDateFinReelle.setText("Date fin r\u00e9elle :");
        getContentPane().add(labelStaticIndDateFinReelle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 100, -1));

        textIndDateFinReelle.setEditable(false);
        textIndDateFinReelle.setText("date fin r\u00e9elle");
        textIndDateFinReelle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(textIndDateFinReelle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 170, -1));

        buttonOk.setText("jButton1");
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        getContentPane().add(buttonOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, -1, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-445)/2, (screenSize.height-445)/2, 445, 445);
    }//GEN-END:initComponents
    
    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonOkActionPerformed
    
    public void initText() {
        this.setTitle(Bundle.getText("JDialogDetailTacheTitle"));
        labelStaticIndNom.setText(Bundle.getText("JDialogDetailTacheName"));
        labelStaticIndRealisateur.setText(Bundle.getText("JDialogDetailTacheRealisator"));
        labelDescription.setText(Bundle.getText("JDialogDetailTacheDescription"));
        labelStaticIndChargePrevue.setText(Bundle.getText("JDialogDetailTachePredictedHours"));
        labelStaticIndTpsPasse.setText(Bundle.getText("JDialogDetailTacheSpentTime"));
        labelStaticIndTempsRestant.setText(Bundle.getText("JDialogDetailTacheRemainingTime"));
        labelStaticIndDatePrevue.setText(Bundle.getText("JDialogDetailTachePredictedBeginningDate"));
        labelStaticIndDateDebutReelle.setText(Bundle.getText("JDialogDetailTacheRealBeginningDate"));
        labelStaticDateFinPrevue.setText(Bundle.getText("JDialogDetailTachePredictedEndingDate"));
        labelStaticIndDateFinReelle.setText(Bundle.getText("JDialogDetailTacheRealEndingDate"));
        labelStaticIndEtat.setText(Bundle.getText("JDialogDetailTacheStatus"));
        this.buttonOk.setText(Bundle.getText("JDialogDetailTacheButtonOk"));
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonOk;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelStaticDateFinPrevue;
    private javax.swing.JLabel labelStaticIndChargePrevue;
    private javax.swing.JLabel labelStaticIndDateDebutReelle;
    private javax.swing.JLabel labelStaticIndDateFinReelle;
    private javax.swing.JLabel labelStaticIndDatePrevue;
    private javax.swing.JLabel labelStaticIndEtat;
    private javax.swing.JLabel labelStaticIndNom;
    private javax.swing.JLabel labelStaticIndRealisateur;
    private javax.swing.JLabel labelStaticIndTempsRestant;
    private javax.swing.JLabel labelStaticIndTpsPasse;
    private javax.swing.JTextArea textDescription;
    private javax.swing.JTextField textIndCharge;
    private javax.swing.JTextField textIndDateDebutPrevue;
    private javax.swing.JTextField textIndDateDebutReelle;
    private javax.swing.JTextField textIndDateFinPrevue;
    private javax.swing.JTextField textIndDateFinReelle;
    private javax.swing.JTextField textIndEtat;
    private javax.swing.JTextField textIndNom;
    private javax.swing.JTextField textIndRealisateur;
    private javax.swing.JTextField textIndTempsPasse;
    private javax.swing.JTextField textIndTempsRestant;
    // End of variables declaration//GEN-END:variables
    
}
