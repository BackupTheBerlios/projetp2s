/*
 * JPanelMessages.java
 *
 * Created on 10 mars 2005, 19:54
 */

package P2S.UI.View.JPanel;

import java.lang.*;
import P2S.Model.*;
import javax.swing.* ;
import java.net.*;
import java.io.*;
import P2S.Control.Bundle.Bundle ;
import P2S.UI.View.JDialog.*;
import P2S.UI.View.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTable ;
import javax.swing.table.TableCellRenderer;
import java.util.Vector;
import java.util.*;
import P2S.UI.View.JFrameP2S;

/**
 *
 * @author  kass
 */
public class JPanelMessages extends JPanel{
    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    
    private String login;
    private JLabel nomTache ;
    private JButton boutonDetails ;
    private JTable tableTaches ;
    private String[] titresColonnes = {Bundle.getText("JTableMessagesColonne1"),
                Bundle.getText("JTableMessagesColonne2"),
                Bundle.getText("JTableMessagesColonne3"),
                Bundle.getText("JTableMessagesColonne4")};
    private Object[][] donnees = null ;
    private Vector messages ;
    
    /** Creates new form BeanForm */
    public JPanelMessages(Vector messages) {
        /*this.messages = messages;
        for (int i = 0; i < messages.size(); i++)
        {
            System.out.println("mess");
        }*/
        
        this.messages = messages ;
        this.login = ((Messages)messages.get(0)).getDestinataire();
       
        donnees = new Object[messages.size()][4] ;
        for (int i = 0 ; i < donnees.length ; i++)
        {
            if (messages.get(i) instanceof Messages)
            {
                donnees[i][0] = ((Messages)messages.get(i)).getDate() ;
                donnees[i][1] = ((Messages)messages.get(i)).getSujet() ;
                donnees[i][2] = new JButton(Bundle.getText("JTableMessagesColonne3")) ;
                donnees[i][3] = new JButton(Bundle.getText("JTableMessagesColonne4")) ;
            }
        }
         
        ModeleTableTaches tableModel = new ModeleTableTaches(donnees, titresColonnes) ;
        
        setLayout(new java.awt.BorderLayout());
        initComponents ();
        table = new JTable(tableModel) ;
        table.setDefaultRenderer(JButton.class, new ButtonRenderer()) ;
        table.setDefaultEditor(JButton.class, new ButtonEditor(this)) ;
        table.getTableHeader().setReorderingAllowed(false) ;
	
	table.setPreferredScrollableViewportSize(new Dimension(50, 10)) ;
	jScrollPane1 = new JScrollPane(table) ;
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

    }//GEN-END:initComponents
    
      class ModeleTableTaches extends ModeleTableMesure
    {
        public ModeleTableTaches(Object donnees[][], String titres[]) {
            super(donnees, titres) ;
        }
        
        public boolean isCellEditable(int row, int col) {
            return ((col==2)||(col==3))  ;
        }
    }
      
      
       class ButtonRenderer implements TableCellRenderer
   {
      private JButton details = null ;

      public ButtonRenderer()
      {
         details = new JButton() ;
         
      }

      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
	details.setText(((JButton)value).getText()) ;
         return details ;
      }

   } // fin de la classe ButtonRenderer
       
         class ButtonEditor extends DefaultCellEditor
   {
      private JButton details = null ;
      private JButton supprimer = null;
      private JPanelMessages owner ;

      public ButtonEditor(JPanelMessages owner)
      {
         
         
         super (new JTextField()) ;
         this.owner = owner ;
         
         
         editorComponent = new JButton(Bundle.getText("")) ;        
         ((JButton)editorComponent).addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(ActionEvent e) {
		   if (table.getSelectedColumn() == 2)
		   {
			new JDialogLireMessages(null, true, (Messages)messages.get(table.getSelectedRow())) ;
		   }
		   else if (table.getSelectedColumn() == 3)
		   {
                        Messages mess = new Messages();
                        mess = (Messages)messages.get(table.getSelectedRow());
                  
                        String sujet = mess.getSujet();
                        String message = mess.getMessage();
                      
                  
                         try
                         {
                            // Envoie du login et du password a la servlet "CreerSuperviseurServlet" pour l'ajouter a la BD
                             URL url = new URL("http://"+P2S.P2S.Preferences.getProperty("host")+":"+P2S.P2S.Preferences.getProperty("port")+"/p2sserver/SupprimerUnMessageServlet?message="+message+"&sujet="+sujet);
            
                            // Buffer qui va recuperer la reponse de la servlet
                             
                            BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                url.openStream()));
                             
                            // On recupere la reponse
                            String inputLine = in.readLine();
            
                            if(inputLine.equalsIgnoreCase("ok")){ // Si la servlet repond que ce n'est pas Ok                 
                                messages.removeElementAt(table.getEditingRow());
                                
                                
                            }
                            in.close();
                        } catch(MalformedURLException e1){
                            javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorURL"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
                        } catch(IOException e2){
                            javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorIO"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
                        } catch(IllegalArgumentException e3){
                            javax.swing.JOptionPane.showMessageDialog(null, Bundle.getText("ExceptionErrorARGS"), Bundle.getText("ExceptionErrorTitle"), javax.swing.JOptionPane.ERROR_MESSAGE) ;
                    }
                 }
		}
           }) ;   
      }

      public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
         
	 details.setText(((JButton)value).getText()) ;
         return details ;
      }

   } // fin de la classe ButtonEditor
    
     // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration
   // Variables declaration - do not modify                     
    // End of variables declaration//GEN-END:variables
    
}
