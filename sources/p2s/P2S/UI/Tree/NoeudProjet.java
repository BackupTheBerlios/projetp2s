/*
 * Noeud.java
 *
 * Created on 1 décembre 2004, 23:54
 */

package P2S.UI.Tree;

import javax.swing.tree.*;
import P2S.Model.*;

/**
 *
 * @author  Fabien
 */
public class NoeudProjet extends DefaultMutableTreeNode{
    
    private Projet Projet;
        
    /** Creates a new instance of Noeud */       
    public NoeudProjet(Projet proj)
    {
        super(proj);
        this.Projet = proj;
    }    
      
    public Projet getProjet()
    {
        return this.Projet;
    }
    
    public String toString()
    {
        return this.Projet.getNom();
    }
    
}
