/*
 * NoeudMembre.java
 *
 * Created on 20 janvier 2005, 00:22
 */

package P2S.UI.Tree;

import javax.swing.tree.*;
import P2S.Model.*;

/**
 *
 * @author Fabien
 */
public class NoeudMembre extends DefaultMutableTreeNode{
    
    private Membre Membre;
        
    /** Creates a new instance of NoeudMembre */       
    public NoeudMembre(Membre membre)
    {
        super(membre);
        this.Membre = membre;
    }    
      
    public Membre getMembre()
    {
        return this.Membre;
    }
    
    public String toString()
    {
        return this.Membre.getNom();
    }
    
}
