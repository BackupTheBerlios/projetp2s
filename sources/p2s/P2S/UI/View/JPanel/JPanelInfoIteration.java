/*
 * JPanelInfoIteration.java
 *
 * Created on 11 f�vrier 2005, 03:16
 */

package P2S.UI.View.JPanel;

import P2S.Model.IndicateursIteration;
import P2S.Model.Iteration;

/**
 *
 * @author  Guillaume
 */
public class JPanelInfoIteration extends javax.swing.JPanel {
    
    /** Creates new form JPanelInfoIteration */
    public JPanelInfoIteration(Iteration ite) {
        initComponents();
        
        IndicateursIteration ind = ite.getIndicateursIteration();
        //Affichage des indicateurs de l'it�ration
        this.LabelIndChargeMoyenne.setText(new Integer(ind.getChargeMoyenneParticipants()).toString());
        this.LabelIndChargesTotales.setText(new Integer(ind.getTotalCharges()).toString());
        this.LabelIndDureeMoyenne.setText(new Integer(ind.getDureeMoyenneTaches()).toString());
        this.LabelIndMoyenneTache.setText(new Integer(ind.getNombreMoyenTachesParticipants()).toString());
        this.LabelIndNombreParticipants.setText(new Integer(ind.getNombreParticipants()).toString());
        this.LabelIndTachesTerminees.setText(new Integer(ind.getNombreTachesTerminees()).toString());
        
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LabelIndChargesTotales = new javax.swing.JLabel();
        LabelIndTachesTerminees = new javax.swing.JLabel();
        LabelIndDureeMoyenne = new javax.swing.JLabel();
        LabelIndNombreParticipants = new javax.swing.JLabel();
        LabelIndChargeMoyenne = new javax.swing.JLabel();
        LabelIndMoyenneTache = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Total des charges :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, -1));

        jLabel2.setText("Nombre de taches termin\u00e9es :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 170, -1));

        jLabel3.setText("Dur\u00e9e moyenne des taches :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 170, -1));

        jLabel4.setText("Nombre de participants :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 190, -1));

        jLabel5.setText("Charge moyenne des participants :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 180, -1));

        jLabel6.setText("Nombre moyen de taches par participants :");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, -1));

        LabelIndChargesTotales.setText("chargesTotales");
        add(LabelIndChargesTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        LabelIndTachesTerminees.setText("tachesTermin\u00e9es");
        add(LabelIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 100, -1));

        LabelIndDureeMoyenne.setText("DureeMoyenne");
        add(LabelIndDureeMoyenne, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 100, -1));

        LabelIndNombreParticipants.setText("nombreParticipant");
        add(LabelIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        LabelIndChargeMoyenne.setText("chargeMoyenne");
        add(LabelIndChargeMoyenne, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 100, -1));

        LabelIndMoyenneTache.setText("moyenneTache");
        add(LabelIndMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 90, -1));

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelIndChargeMoyenne;
    private javax.swing.JLabel LabelIndChargesTotales;
    private javax.swing.JLabel LabelIndDureeMoyenne;
    private javax.swing.JLabel LabelIndMoyenneTache;
    private javax.swing.JLabel LabelIndNombreParticipants;
    private javax.swing.JLabel LabelIndTachesTerminees;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
    
}
