package P2S.UI.View.JDialog;

import javax.swing.table.*;

/**
 *
 * @author  Fabien
 */
public class ModeleTableMesure extends AbstractTableModel {
    
    Object donnees[][];
    String titres[];

   public ModeleTableMesure(Object donnees[][], String titres[]) {
      this.donnees = donnees;
      this.titres = titres;
   }

   public int getColumnCount() {
        if(getRowCount() != 0)
            return donnees[0].length;
        else
            return 0;
   }

   public Object getValueAt(int ligne, int colonne) {
       return donnees[ligne][colonne];
   }

   public int getRowCount() {
       return donnees.length;
   }

   public String getColumnName(int colonne){
     return titres[colonne];
   }
   
   public Class getColumnClass(int colonne) {
       return getValueAt(0,colonne).getClass();
    }
    
   public boolean isCellEditable(int row, int col) {
        // toutes les cellules sont non editables
        return false;        
    }



}
