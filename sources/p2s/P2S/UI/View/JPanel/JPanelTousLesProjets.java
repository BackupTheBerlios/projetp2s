/*
 * PanelTousLesProjets.java
 *
 * Created on 27 janvier 2005, 20:04
 */

package P2S.UI.View.JPanel;

import P2S.Control.Bundle.*;
import P2S.Model.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTable ;
import javax.swing.table.TableCellRenderer;
import java.util.*;
import P2S.UI.View.JDialog.ModeleTableMesure;
import P2S.UI.View.JDialog.JDialogDetailProjet;

import java.text.NumberFormat;

/**
 * JPanel affichant les informations sur l'ensemble des projets
 * @author Fabien
 */
public class JPanelTousLesProjets extends javax.swing.JPanel {
    
    /**
     * Creates new form PanelTousLesProjets
     * @param listeProjets liste des projets du superviseur
     */
    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    
    private String login;
    private JLabel nomTache ;
    private JButton boutonDetails ;
    private JTable tableProjets ;
    private String[] titresColonnes = {Bundle.getText("JPanelTousLesProjets_Projet"),
            Bundle.getText("JPanelTousLesProjets_IndicateurParticipant"),
            Bundle.getText("JPanelTousLesProjets_IndicateurTachesTerminees"),
            Bundle.getText("JPanelTousLesProjets_IndicateurTotalCharges"),
            Bundle.getText("JPanelTousLesProjets_IndicateurMoyenneTache"),
            Bundle.getText("JPanelTousLesProjetsDetail")};
            private Object[][] donnees = null ;
            private Vector projets ;
            
            public JPanelTousLesProjets(Vector listeProjets) {
                
                this.projets = listeProjets ;
                
                NumberFormat nf = NumberFormat.getInstance();
                nf.setMaximumFractionDigits(2);
                
                donnees = new Object[projets.size()][6] ;
                for (int i = 0 ; i < donnees.length ; i++) {
                    if (projets.get(i) instanceof Projet) {
                        donnees[i][0] = ((Projet)projets.get(i)).getNom() ;
                        //donnees[i][1] = new Float(((Projet)projets.get(i)).getIndicateursProjet().getAvancementProjet());
                        donnees[i][1] = new Integer(((Projet)projets.get(i)).getIndicateursProjet().getNombreParticipants());
                        donnees[i][2] = new Integer(((Projet)projets.get(i)).getIndicateursProjet().getTachesTerminees());
                        donnees[i][3] = new Integer(((Projet)projets.get(i)).getIndicateursProjet().getTotalCharges());
                        donnees[i][4] = new Integer(((Projet)projets.get(i)).getIndicateursProjet().getDureeMoyenneTache());
                        donnees[i][5] = new JButton(Bundle.getText("JTableTachesDetails")) ;
                    }
                }
                
                ModeleTableTaches tableModel = new ModeleTableTaches(donnees, titresColonnes) ;
                
                setLayout(new java.awt.BorderLayout());
                table = new JTable(tableModel) ;
                table.setDefaultRenderer(Integer.class, new NumericRenderer()) ;
                table.setDefaultRenderer(Float.class, new FloatRenderer()) ;
                table.setDefaultRenderer(String.class, new StatusRenderer()) ;
                table.setDefaultRenderer(JButton.class, new ButtonRenderer()) ;
                table.setDefaultEditor(JButton.class, new ButtonEditor(this)) ;
                table.getTableHeader().setReorderingAllowed(false) ;
                
                table.setPreferredScrollableViewportSize(new Dimension(500, 100)) ;
                jScrollPane1 = new JScrollPane(table) ;
                add(jScrollPane1, java.awt.BorderLayout.CENTER);
            }
            
            /** This method is called from within the constructor to
             * initialize the form.
             * WARNING: Do NOT modify this code. The content of this method is
             * always regenerated by the Form Editor.
             */
    private void initComponents() {//GEN-BEGIN:initComponents

        setLayout(new java.awt.GridBagLayout());

    }//GEN-END:initComponents
    
    private void initText() {
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    class ModeleTableTaches extends ModeleTableMesure {
        public ModeleTableTaches(Object donnees[][], String titres[]) {
            super(donnees, titres) ;
        }
        
        public boolean isCellEditable(int row, int col) {
            return (col == 5) ;
        }
    }
    
    class NumericRenderer implements TableCellRenderer {
        private JFormattedTextField numericField = new JFormattedTextField() ;
        
        public NumericRenderer() {
            numericField.setHorizontalAlignment(JTextField.RIGHT) ;
            numericField.setBorder(BorderFactory.createEmptyBorder()) ;
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            numericField.setValue(value) ;
            return numericField ;
        }
        
    } // fin de la classe NumericRenderer
    
    class FloatRenderer implements TableCellRenderer {
        private JFormattedTextField numericField = new JFormattedTextField() ;
        
        public FloatRenderer() {
            numericField.setHorizontalAlignment(JTextField.RIGHT) ;
            numericField.setBorder(BorderFactory.createEmptyBorder()) ;
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            numericField.setValue(value + " " + "%") ;
            return numericField ;
        }
        
    } // fin de la classe FloatRenderer
    
    class ButtonRenderer implements TableCellRenderer {
        private JButton details = null ;
        
        public ButtonRenderer() {
            details = new JButton(Bundle.getText("JTableTachesDetails")) ;
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            details.setText(((JButton)value).getText()) ;
            return details ;
        }
        
    } // fin de la classe ButtonRenderer
    
    
    
    class StatusRenderer implements TableCellRenderer {
        private JFormattedTextField statusField = new JFormattedTextField() ;
        
        public StatusRenderer() {
            statusField.setHorizontalAlignment(JTextField.LEFT) ;
            statusField.setBorder(BorderFactory.createEmptyBorder()) ;
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            statusField.setValue(value) ;
            // on le fait uniquement pour la colonne etat
            if (column == 1) {
                statusField.setValue(Bundle.getText("Constante_tache"+value)) ;
                if (((String)value).equals("1")) {
                    statusField.setForeground(new Color(20, 20, 250)) ;
                }
                if (((String)value).equals("3")) {
                    statusField.setForeground(new Color(250, 20, 20)) ;
                }
            } else {
                statusField.setForeground(new Color(0, 0, 0)) ;
                
            }
            return statusField ;
        }
        
    } // fin de la classe NumericRenderer
    
    class ButtonEditor extends DefaultCellEditor {
        private JButton details = null ;
        private JPanelTousLesProjets owner;
        
        public ButtonEditor(JPanelTousLesProjets owner) {
            super(new JTextField()) ;
            this.owner = owner ;
            this.clickCountToStart = 1;
            editorComponent = new JButton(Bundle.getText("JTableTachesDetails")) ;
            ((JButton)editorComponent).addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new JDialogDetailProjet(null, true, (Projet)projets.get(table.getSelectedRow()));
                }
            }) ;
        }
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            details.setText(((JButton)value).getText()) ;
            
            return details ;
        }
        
    } // fin de la classe ButtonEditor
}
