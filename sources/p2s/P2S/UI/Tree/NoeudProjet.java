/*
 * Noeud.java
 *
 * Created on 1 decembre 2004, 23:54
 */

package P2S.UI.Tree;

import javax.swing.tree.*;
import P2S.Model.*;

/**
 * Represente un noeud projet
 * @author Fabien
 */
public class NoeudProjet extends DefaultMutableTreeNode{
    
    private Projet Projet;
        
    /**
     * Creates a new instance of Noeud
     * @param proj projet represente par le noeud
     */       
    public NoeudProjet(Projet proj)
    {
        super(proj);
        this.Projet = proj;
    }    
      
    /**
     * recuperer le projet represente par le noeud
     * @return le projet
     */
    public Projet getProjet()
    {
        return this.Projet;
    }
    
    /**
     * renvoie le nom du projet
     * @return le nom du projet
     */
    public String toString()
    {
        return this.Projet.getNom();
    }    
        
}
