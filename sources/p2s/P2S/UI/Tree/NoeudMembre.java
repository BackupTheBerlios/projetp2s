/*
 * NoeudMembre.java
 *
 * Created on 20 janvier 2005, 00:22
 */

package P2S.UI.Tree;

import javax.swing.tree.*;
import P2S.Model.*;

/**
 * represente un neoud membre
 * @author Fabien
 */
public class NoeudMembre extends DefaultMutableTreeNode{
    
    private Membre Membre;
        
    /**
     * Creates a new instance of NoeudMembre
     * @param membre membre represente par le noeud
     */       
    public NoeudMembre(Membre membre)
    {
        super(membre);
        this.Membre = membre;
    }    
      
    /**
     * recupere le membre represente par le noeud
     * @return membre
     */
    public Membre getMembre()
    {
        return this.Membre;
    }
    
    /**
     * renvoie le nom du membre
     * @return nom du membre
     */
    public String toString()
    {
        return this.Membre.getNom();
    }
    
}
