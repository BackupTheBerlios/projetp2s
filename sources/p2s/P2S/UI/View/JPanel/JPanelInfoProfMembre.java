/*
 * JPanelInfoRolesMembres.java
 *
 * Created on 26 f�vrier 2005, 14:54
 */

package P2S.UI.View.JPanel;

import P2S.Control.Bundle.Bundle;
import P2S.Model.IndicateursProjetMembre;
import P2S.Model.Role;
import P2S.UI.View.JDialog.ModeleTableMesure;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author  Conde Mike K
 */
public class JPanelInfoProfMembre extends javax.swing.JPanel {
    
    private String[] nomsColonnes1 = {Bundle.getText("JTableRolesColonne1"), Bundle.getText("JTableRolesColonne2")} ;
    private Object[][] donnees1 = null ;
    private String[] nomsColonnes2 = {Bundle.getText("JTableIndicProjetColonne1"), Bundle.getText("JTableIndicProjetColonne2"), Bundle.getText("JTableIndicProjetColonne3")} ;
    private Object[][] donnees2 = null ;
    private javax.swing.JTable table1;
    private javax.swing.JScrollPane tableScrollPane1;
    private javax.swing.JTable table2;
    private javax.swing.JScrollPane tableScrollPane2;
    
    /** Creates new form JPanelInfoProfMembre */
    public JPanelInfoProfMembre (Vector roles, Vector projets) {
        initComponents ();
        
        donnees1 = new Object[roles.size()][2] ;
        for (int i = 0 ; i < donnees1.length ; i++)
        {
            if (roles.get(i) instanceof Role)
            {
                donnees1[i][0] = ((Role)roles.get(i)).getDesignation() ;
                donnees1[i][1] = ((Role)roles.get(i)).getDescription() ;
            }
        }
        
        
        ModeleTableMesure tableModel1 = new ModeleTableMesure(donnees1, nomsColonnes1) ;
        table1 = new JTable(tableModel1) ;
        tableScrollPane1 = new JScrollPane(table1) ;
        
        JLabel labelRoles = new JLabel(Bundle.getText("JTableRolesLabel")) ;
        jPanel1.add(labelRoles) ;
        
        jPanel1.add(tableScrollPane1) ;
        
        donnees2 = new Object[projets.size()][3] ;
        for (int i = 0 ; i < donnees2.length ; i++)
        {
            if (projets.get(i) instanceof IndicateursProjetMembre)
            {
                donnees2[i][0] = ((IndicateursProjetMembre)projets.get(i)).getNom() ;
                donnees2[i][1] = new Integer(((IndicateursProjetMembre)projets.get(i)).getCharges()) ;
                donnees2[i][2] = new Integer(((IndicateursProjetMembre)projets.get(i)).getTempsTravail()) ;
            }
        }
        
        
        JLabel labelProjet = new JLabel(Bundle.getText("JTableIndicProjetLabel")) ;
        jPanel1.add(labelProjet) ;
        
        ModeleTableMesure tableModel2 = new ModeleTableMesure(donnees2, nomsColonnes2) ;
        table2 = new JTable(tableModel2) ;
        table2.setPreferredScrollableViewportSize(new Dimension(100, 50)) ;
        table2.setDefaultRenderer(Integer.class, new NumericRenderer()) ;
        tableScrollPane2 = new JScrollPane(table2) ;
        
        jPanel1.add(tableScrollPane2) ;
        
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
    
    /** classe NumericRenderer
    * @author C Mike K
    * @version 1.0
    */
   class NumericRenderer implements TableCellRenderer
   {
      private JFormattedTextField numericField = new JFormattedTextField() ;

      public NumericRenderer()
      {
         numericField.setHorizontalAlignment(JTextField.RIGHT) ;
         numericField.setBorder(BorderFactory.createEmptyBorder()) ;
      }

      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
         numericField.setValue(value + " " +Bundle.getText("Constante_heures")) ;
         return numericField ;
      }

   } // fin de la classe NumericRenderer
}
