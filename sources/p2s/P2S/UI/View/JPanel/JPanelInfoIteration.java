/*
 * JPanelInfoIteration.java
 *
 * Created on 11 f�vrier 2005, 03:16
 */

package P2S.UI.View.JPanel;

import P2S.Model.IndicateursIteration;
import P2S.Model.Iteration;
import P2S.Model.Tache;
import java.util.Vector ;

/**
 *
 * @author  Guillaume
 */
public class JPanelInfoIteration extends javax.swing.JPanel {
    
    /** Creates new form JPanelInfoIteration */
    public JPanelInfoIteration(Iteration ite) {
        initComponents();
        
        IndicateursIteration ind = ite.getIndicateursIteration();

        this.textIndChargeMoyenne.setText(new Float(ind.getChargeMoyenneParticipants()).toString());
        this.textIndChargesTotales.setText(new Integer(ind.getTotalCharges()).toString());
        this.textIndDureeMoyenne.setText(new Integer(ind.getDureeMoyenneTaches()).toString());
        this.textIndMoyenneTache.setText(new Integer(ind.getNombreMoyenTachesParticipants()).toString());
        this.textIndNombreParticipants.setText(new Integer(ind.getNombreParticipants()).toString());
        this.textIndTachesTerminees.setText(new Integer(ind.getNombreTachesTerminees()).toString());
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
        textIndMoyenneTache = new javax.swing.JTextField();
        textIndChargeMoyenne = new javax.swing.JTextField();
        textIndNombreParticipants = new javax.swing.JTextField();
        textIndDureeMoyenne = new javax.swing.JTextField();
        textIndTachesTerminees = new javax.swing.JTextField();
        textIndChargesTotales = new javax.swing.JTextField();

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

        textIndMoyenneTache.setEditable(false);
        textIndMoyenneTache.setText("jTextField1");
        textIndMoyenneTache.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndMoyenneTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 40, -1));

        textIndChargeMoyenne.setEditable(false);
        textIndChargeMoyenne.setText("jTextField2");
        textIndChargeMoyenne.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndChargeMoyenne, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 40, -1));

        textIndNombreParticipants.setEditable(false);
        textIndNombreParticipants.setText("jTextField3");
        textIndNombreParticipants.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndNombreParticipants, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 40, -1));

        textIndDureeMoyenne.setEditable(false);
        textIndDureeMoyenne.setText("jTextField4");
        textIndDureeMoyenne.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndDureeMoyenne, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 40, -1));

        textIndTachesTerminees.setEditable(false);
        textIndTachesTerminees.setText("jTextField5");
        textIndTachesTerminees.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndTachesTerminees, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 40, -1));

        textIndChargesTotales.setEditable(false);
        textIndChargesTotales.setText("jTextField6");
        textIndChargesTotales.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        add(textIndChargesTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 40, -1));

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField textIndChargeMoyenne;
    private javax.swing.JTextField textIndChargesTotales;
    private javax.swing.JTextField textIndDureeMoyenne;
    private javax.swing.JTextField textIndMoyenneTache;
    private javax.swing.JTextField textIndNombreParticipants;
    private javax.swing.JTextField textIndTachesTerminees;
    // End of variables declaration//GEN-END:variables
    
}
