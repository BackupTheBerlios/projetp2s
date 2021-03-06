/*
 * JTabbedPaneIteration.java
 *
 * Created on 22 f�vrier 2005, 23:01
 */

package P2S.UI.View;

import P2S.Control.Bundle.Bundle;
import P2S.Model.Iteration;
import P2S.Model.SeuilsFixes;
import P2S.UI.View.JPanel.JPanelInfoIteration;
import P2S.UI.View.JPanel.JPanelInfoTaches;
import javax.swing.JTabbedPane;

/**
 *
 * @author  Conde Mike K
 */
public class JTabbedPaneIteration extends JTabbedPane {
    
    /** Creates new form BeanForm */
    public JTabbedPaneIteration (Iteration iter, SeuilsFixes seuils) {
        initComponents ();
                
       addTab(Bundle.getText("JTabbedPaneIteration_TabInfoGen"), new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/tab_info.gif")), new JPanelInfoIteration(iter, seuils)) ;
       addTab(Bundle.getText("JTabbedPaneIteration_TabTaches"),new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/tab_tasks.gif")), new JPanelInfoTaches(iter.getListeTache())) ;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
