/*
 * GrapheIndicateursProjet.java
 *
 * Created on 5 mars 2005, 15:11
 */

package P2S.UI.Graphic2D;

import P2S.Control.Bundle.Bundle;
import P2S.Model.IndicateursIteration;
import P2S.Model.Iteration;
import P2S.Model.Projet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;
import javax.swing.JPanel;
import java.awt.geom.*;



/**
 *
 * @author Conde Mike K
 */
public class GrapheIndicateursProjet extends JPanel {
    private int chargesMax = 0 ;
    private int moyenneChargesMax = 0 ;
    private int participantsMax = 0 ;
    private int tachesTermineesMax = 0 ;
    private float tachesParticipantsMax = 0 ;
    private int nbIt = 0 ;
    
    private static final int RECT_DIM1 = 10 ;
    private static final int RECT_DIM2 = 100 ;
    private static final int ITERATION_WIDTH = 150 ;
    
    private final Color color1 = new Color(200, 0, 50) ;
    private final Color color2 = new Color(200, 200, 50) ;
    private final Color color3 = new Color(0, 50, 200) ;
    private final Color color4 = new Color(0, 200, 100) ;
    private final Color color5 = new Color(100, 0, 100) ;
    
    
    private Vector iterations ;
    
    /** Creates a new instance of GrapheIndicateursProjet */
    public GrapheIndicateursProjet (Projet p) {
        // NOTES : pour chaque iteration seront affichees 5 mesures : total des charges, 
        // moyenne des charges par participant, nombre de participants, taches/participants ..
        
        // pour l'echelle, on determine les mesures max de toutes les iterations
        
        
        iterations = p.getListeIt() ;        
        nbIt = iterations.size() ;
        
        // calcul des maximums
        Iteration tempIt = null ;
        IndicateursIteration tempIndIt = null ;
        for (int i =  0 ; i < nbIt ; i++)
        {
            if (iterations.get(i) instanceof Iteration)
            {
                tempIt = (Iteration)iterations.get(i) ;
                tempIndIt = tempIt.getIndicateursIteration() ;
                // charges
                if (tempIndIt.getTotalCharges() > chargesMax) { chargesMax = tempIndIt.getTotalCharges() ; }
		if (tempIndIt.getDureeMoyenneTaches() > moyenneChargesMax) { moyenneChargesMax = tempIndIt.getDureeMoyenneTaches() ; }
		if (tempIndIt.getNombreParticipants() > participantsMax) { participantsMax = tempIndIt.getNombreParticipants() ; }
		if (tempIndIt.getNombreTachesTerminees() > tachesTermineesMax) { tachesTermineesMax = tempIndIt.getNombreTachesTerminees() ; }
		if (tempIndIt.getNombreMoyenTachesParticipants() > tachesParticipantsMax) { tachesParticipantsMax = tempIndIt.getNombreMoyenTachesParticipants() ; }
            }
        }   
        setPreferredSize(new Dimension(ITERATION_WIDTH * nbIt,400));
	//setBorder(new TitledBorder(new EtchedBorder(), "toto", 5, TitledBorder.ABOVE_TOP)) ;
    }
    
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g) ;
	Graphics2D g2d = (Graphics2D)g ;
	Rectangle2D.Double ligne1 = new Rectangle2D.Double(5.0, 300.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Double ligne2 = new Rectangle2D.Double(5.0, 315.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Double ligne3 = new Rectangle2D.Double(5.0, 330.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Double ligne4 = new Rectangle2D.Double(5.0, 345.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Double ligne5 = new Rectangle2D.Double(5.0, 360.0, RECT_DIM2, RECT_DIM1) ;
	Line2D.Double axeX = new Line2D.Double(0.0, 230.0, new Double(ITERATION_WIDTH * nbIt).doubleValue(), 230.0) ;
	Line2D.Double axeY = new Line2D.Double(0.0, 230.0, 0.0, 0.0) ;
	Line2D.Double underline = new Line2D.Double() ;
	Line2D.Double limit = new Line2D.Double() ;
	
	g2d.setPaint(new Color(240, 240, 240)) ;
	g2d.fill(new Rectangle2D.Double(0.0, 0.0, new Double(ITERATION_WIDTH * nbIt).doubleValue(), 230.0)) ;
	
	g2d.setPaint(Color.black) ;
	g2d.draw(axeX) ;
	g2d.draw(axeY) ;
	
	// legende		
	g2d.setPaint(new Color(220, 220, 220)) ;
	g2d.fill(new Rectangle2D.Double(0.0, 270.0, 300.0, 110.0)) ;	
	g2d.setPaint(color1) ;
	g2d.fill(ligne1) ;
	g2d.setPaint(color2) ;
	g2d.fill(ligne2) ;
	g2d.setPaint(color3) ;
	g2d.fill(ligne3) ;
	g2d.setPaint(color4) ;
	g2d.fill(ligne4) ;
	g2d.setPaint(color5) ;
	g2d.fill(ligne5) ;
	g2d.setPaint(Color.blue) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_Legende"), 5, 290) ;	
	g2d.setPaint(Color.black) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L1"), 115, 310) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L2"), 115, 325) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L3"), 115, 340) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L4"), 115, 355) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L5"), 115, 370) ;
	g2d.draw(ligne1) ;
	g2d.draw(ligne2) ;
	g2d.draw(ligne3) ;
	g2d.draw(ligne4) ;
	g2d.draw(ligne5) ;
	g2d.draw(new Rectangle2D.Double(0.0, 270.0, 300.0, 110.0)) ;
	
	
	// graphe pour chaque iteration
	Iteration tempIt = null ;
        IndicateursIteration tempIndIt = null ;
        for (int i =  0 ; i < nbIt ; i++)
        {
            if (iterations.get(i) instanceof Iteration)
            {
                 tempIt = (Iteration)iterations.get(i) ;
		 tempIndIt = tempIt.getIndicateursIteration() ;
		 		 
		 
		 // affichage de la premiere colonne : total des charges
		 int charges = tempIndIt.getTotalCharges() ;
		 if (charges > 0)
		 {
		    Double y1Height = new Double((new Double(charges).doubleValue()/new Double(chargesMax).doubleValue())*200.0) ; // longueur de la barre
		    Double y1Pos = new Double(230.0 - y1Height.doubleValue()) ; // position de la barre
		    ligne1.setRect(new Double(ITERATION_WIDTH * i + 5).doubleValue(), y1Pos.doubleValue(), RECT_DIM1, y1Height.doubleValue()) ;
		    g2d.setPaint(color1) ;
		    g2d.fill(ligne1) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne1) ;
		    g2d.drawString((new Integer(charges)).toString(), ITERATION_WIDTH * i + 5, y1Pos.intValue() - 10) ;
		 }
		 
		 
		 // affichage de la deuxieme colonne : nombre participants
		 int participants = tempIndIt.getNombreParticipants() ;
		 if (participants > 0)
		 {
		    Double y2Height = new Double((new Double(participants).doubleValue()/new Double(participantsMax).doubleValue())*200.0) ; // longueur de la barre
		    Double y2Pos = new Double(230.0 - y2Height.doubleValue()) ; // position de la barre
		    ligne2.setRect(new Double(ITERATION_WIDTH * i + RECT_DIM1 + 20).doubleValue(), y2Pos.doubleValue(), RECT_DIM1, y2Height.doubleValue()) ;
		    g2d.setPaint(color2) ;
		    g2d.fill(ligne2) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne2) ;
		    g2d.drawString((new Integer(participants)).toString(), ITERATION_WIDTH * i + 20 + RECT_DIM1, y2Pos.intValue() - 10) ;
		 }
		 
		 
		 // affichage de la troisieme colonne : moyenne charge/participant
		 int moyenneCharges = tempIndIt.getDureeMoyenneTaches() ;
		 if (moyenneCharges > 0)
		 {
		    Double y3Height = new Double((new Double(moyenneCharges).doubleValue()/new Double(moyenneChargesMax).doubleValue())*200.0) ; // longueur de la barre
		    Double y3Pos = new Double(230.0 - y3Height.doubleValue()) ; // position de la barre
		    ligne3.setRect(new Double(ITERATION_WIDTH * i + RECT_DIM1 + 45).doubleValue(), y3Pos.doubleValue(), RECT_DIM1, y3Height.doubleValue()) ;
		    g2d.setPaint(color3) ;
		    g2d.fill(ligne3) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne3) ;
		    g2d.drawString((new Integer(moyenneCharges)).toString(), ITERATION_WIDTH * i + 45+ RECT_DIM1, y3Pos.intValue() - 10) ;
		 }
		 
		 
		 // affichage de la quatrieme colonne : nombre de taches terminees
		 int tachesTerminees = tempIndIt.getNombreTachesTerminees() ;
		 if (tachesTerminees > 0)
		 {
		    Double y4Height = new Double((new Double(tachesTerminees).doubleValue()/new Double(tachesTermineesMax).doubleValue())*200.0) ; // longueur de la barre
		    Double y4Pos = new Double(230.0 - y4Height.doubleValue()) ; // position de la barre
		    ligne4.setRect(new Double(ITERATION_WIDTH * i + RECT_DIM1 + 70).doubleValue(), y4Pos.doubleValue(), RECT_DIM1, y4Height.doubleValue()) ;
		    g2d.setPaint(color4) ;
		    g2d.fill(ligne4) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne4) ;
		    g2d.drawString((new Integer(tachesTerminees)).toString(), ITERATION_WIDTH * i + 70+ RECT_DIM1, y4Pos.intValue() - 10) ;
		 }
		 
		 
		 // affichage de la quatrieme colonne : taches par participant
		 float tachesParticipants = tempIndIt.getNombreMoyenTachesParticipants() ;
		 if (tachesParticipants > 0)
		 {
		    Double y5Height = new Double((new Double(tachesParticipants).doubleValue()/new Double(tachesParticipantsMax).doubleValue())*200.0) ; // longueur de la barre
		    Double y5Pos = new Double(230.0 - y5Height.doubleValue()) ; // position de la barre
		    ligne5.setRect(new Double(ITERATION_WIDTH * i + RECT_DIM1 + 95).doubleValue(), y5Pos.doubleValue(), RECT_DIM1, y5Height.doubleValue()) ;
		    g2d.setPaint(color5) ;
		    g2d.fill(ligne5) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne5) ;
		    g2d.drawString((new Float(tachesParticipants)).toString(), ITERATION_WIDTH * i + 95+ RECT_DIM1, y5Pos.intValue() - 10) ;
		 }
		 
		 // lignes du bas pour delimiter l'iteration
		 underline.setLine(new Double(ITERATION_WIDTH * i + 5).doubleValue(), 250.0, new Double(ITERATION_WIDTH - 15 +ITERATION_WIDTH * i).doubleValue(), 250.0) ;
		 g2d.draw(underline) ;
		 limit.setLine(new Double(ITERATION_WIDTH * i + 5).doubleValue(), 250.0, new Double(5 + ITERATION_WIDTH * i).doubleValue(), 240.0) ;
		 g2d.draw(limit) ;
		 limit.setLine(new Double(ITERATION_WIDTH - 15 +ITERATION_WIDTH * i).doubleValue(), 250.0, new Double(ITERATION_WIDTH - 15 +ITERATION_WIDTH * i).doubleValue(), 240.0) ;
		 g2d.draw(limit) ;
		 g2d.drawString("Iteration "+tempIt.getNumero(), ITERATION_WIDTH * i + 20, 245) ;
            }
        } 
    }
    
}
