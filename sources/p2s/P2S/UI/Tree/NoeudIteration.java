/*
 * NoeudIteration.java
 *
 * Created on 10 février 2005, 00:22
 */

package P2S.UI.Tree;

import javax.swing.tree.*;
import P2S.Model.*;

/**
 * represente un neoud iteration
 * @author Guillaume
 */
public class NoeudIteration extends DefaultMutableTreeNode{
    
    private Iteration iteration;
        
    /**
     * Creates a new instance of NoeudIteration
     * @param membre membre represente par le noeud
     */       
    public NoeudIteration(Iteration iteration)
    {
        super(iteration);
        this.iteration = iteration;
    }    
      
    /**
     * recupere l'itération represente par le noeud
     * @return membre
     */
    public Iteration getIteration()
    {
        return this.iteration;
    }
    
    /**
     * 
     * @return nom du membre
     */
    public String toString()
    {
        return "Iteration "+new Integer(iteration.getNumero()).toString();
    }
    
}
