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
import P2S.Model.Seuil;
import P2S.Model.SeuilsFixes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;
import javax.swing.JPanel;
import java.awt.geom.*;
import java.text.NumberFormat;



/**
 *
 * @author Conde Mike K
 */
public class GrapheIndicateursProjet extends JPanel {
    private int chargesMax = 0 ;
    private float moyenneChargesMax = 0 ;
    private int participantsMax = 0 ;
    private int tachesTermineesMax = 0 ;
    private float tachesParticipantsMax = 0 ;
    private float dureeMoyenneTacheMax = 0 ;
    
    private int nbIt = 0 ;
    private SeuilsFixes seuils = null ;
    
    private static final int RECT_DIM1 = 10 ;
    private static final int RECT_DIM2 = 100 ;
    private static final int ITERATION_WIDTH = 180 ;
    
    /*private final Color color1 = new Color(200, 0, 50) ;
    private final Color color2 = new Color(200, 200, 50) ;
    private final Color color3 = new Color(0, 50, 200) ;
    private final Color color4 = new Color(0, 200, 100) ;
    private final Color color5 = new Color(100, 0, 100) ;*/
    private final Color color1 = new Color(0, 200, 50) ;
    private final Color color2 = new Color(0, 180, 70) ;
    private final Color color3 = new Color(0, 160, 90) ;
    private final Color color4 = new Color(0, 140, 110) ;
    private final Color color5 = new Color(0, 120, 130) ;
    private final Color color6 = new Color(0, 110, 150) ;
    
    
    private Vector iterations ;
    
    /** Creates a new instance of GrapheIndicateursProjet */
    public GrapheIndicateursProjet (Projet p) {
        seuils = p.getSeuilFixes() ;
        
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
		if (tempIndIt.getChargeMoyenneParticipants() > moyenneChargesMax) { moyenneChargesMax = tempIndIt.getChargeMoyenneParticipants() ; }
		if (tempIndIt.getNombreParticipants() > participantsMax) { participantsMax = tempIndIt.getNombreParticipants() ; }
		if (tempIndIt.getNombreTachesTerminees() > tachesTermineesMax) { tachesTermineesMax = tempIndIt.getNombreTachesTerminees() ; }
		if (tempIndIt.getNombreMoyenTachesParticipants() > tachesParticipantsMax) { tachesParticipantsMax = tempIndIt.getNombreMoyenTachesParticipants() ; }
		if (tempIndIt.getDureeMoyenneTaches() > dureeMoyenneTacheMax) { dureeMoyenneTacheMax = tempIndIt.getDureeMoyenneTaches() ; }
            }
        }
        setPreferredSize(new Dimension(ITERATION_WIDTH * nbIt,440));
	//setBorder(new TitledBorder(new EtchedBorder(), "toto", 5, TitledBorder.ABOVE_TOP)) ;
    }
    
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g) ;
	Graphics2D g2d = (Graphics2D)g ;
	Rectangle2D.Float ligne1 = new Rectangle2D.Float((float)5.0, (float)300.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Float ligne2 = new Rectangle2D.Float((float)5.0, (float)315.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Float ligne3 = new Rectangle2D.Float((float)5.0, (float)330.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Float ligne4 = new Rectangle2D.Float((float)5.0, (float)345.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Float ligne5 = new Rectangle2D.Float((float)5.0, (float)360.0, RECT_DIM2, RECT_DIM1) ;
	Rectangle2D.Float ligne6 = new Rectangle2D.Float((float)5.0, (float)375.0, RECT_DIM2, RECT_DIM1) ;
	Line2D.Float axeX = new Line2D.Float((float)0.0, (float)230.0, new Float(ITERATION_WIDTH * nbIt).floatValue(), (float)230.0) ;
	Line2D.Float axeY = new Line2D.Float((float)0.0, (float)230.0, (float)0.0, (float)0.0) ;
	Line2D.Float underline = new Line2D.Float() ;
	Line2D.Float limit = new Line2D.Float() ;
	
	g2d.setPaint(new Color(255, 255, 255)) ;
	g2d.fill(new Rectangle2D.Float((float)0.0, (float)0.0, new Float(ITERATION_WIDTH * nbIt).floatValue(), (float)230.0)) ;
	
	g2d.setPaint(Color.black) ;
	g2d.draw(axeX) ;
	g2d.draw(axeY) ;
	
	// legende		
	g2d.setPaint(new Color(220, 220, 220)) ;
	g2d.fill(new Rectangle2D.Float((float)0.0, (float)270.0, (float)300.0, (float)160.0)) ;	
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
	g2d.setPaint(color6) ;
	g2d.fill(ligne6) ;
	g2d.setPaint(Color.red) ;
	g2d.fill(new Rectangle2D.Float((float)5.0, (float)400.0, RECT_DIM2, RECT_DIM1)) ;
	g2d.setPaint(new Color(150, 150, 150)) ;
	g2d.fill(new Rectangle2D.Float((float)5.0, (float)415.0, RECT_DIM2, RECT_DIM1)) ;
	g2d.setPaint(Color.black) ;
	
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_Legende"), 5, 290) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L1"), 115, 310) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L2"), 115, 325) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L3"), 115, 340) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L4"), 115, 355) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L5"), 115, 370) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_L6"), 115, 385) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_C1"), 115, 410) ;
	g2d.drawString(Bundle.getText("GrapheIndicateursProjet_C2"), 115, 425) ;
	g2d.draw(ligne1) ;
	g2d.draw(ligne2) ;
	g2d.draw(ligne3) ;
	g2d.draw(ligne4) ;
	g2d.draw(ligne5) ;
	g2d.draw(ligne6) ;
	g2d.draw(new Rectangle2D.Float((float)5.0, (float)400.0, RECT_DIM2, RECT_DIM1)) ;
	g2d.draw(new Rectangle2D.Float((float)5.0, (float)415.0, RECT_DIM2, RECT_DIM1)) ;
	g2d.draw(new Rectangle2D.Float((float)0.0, (float)270.0, (float)300.0, (float)160.0)) ;
	
	
	// graphe pour chaque iteration
	Iteration tempIt = null ;
        IndicateursIteration tempIndIt = null ;
	
	NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
	
        for (int i =  0 ; i < nbIt ; i++)
        {
            if (iterations.get(i) instanceof Iteration)
            {
                 tempIt = (Iteration)iterations.get(i) ;
		 tempIndIt = tempIt.getIndicateursIteration() ;
		 Seuil seuil ;
		 		 
		 
		 // affichage de la premiere colonne : total des charges
		 int charges = tempIndIt.getTotalCharges() ;
		 if (charges > 0)
		 {		    
		    float y1Height = ((float)charges/(float)chargesMax)*200 ; // longueur de la barre
		    float y1Pos = (float)230.0 - y1Height ; // position de la barre	    
		  
		    seuil = seuils.getTotalChargesIteration() ;
		    // si les seuils ne sont pas atteints
		    if (((Integer)seuil.getSeuilMin()).intValue() > charges && ((Integer)seuil.getSeuilMin()).intValue() != 0)
		    {
			float minusHeight = ((float)((Integer)seuil.getSeuilMin()).intValue()/(float)chargesMax)*200 ;
			float minusPos = 230 - minusHeight ;
			ligne1.setRect((float)(ITERATION_WIDTH * i + 10), minusPos, RECT_DIM1, minusHeight) ;
			g2d.setPaint(new Color(150, 150, 150)) ;
			g2d.fill(ligne1) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne1) ;
		    }
		    
		    
		    // si les seuils sont depasses
		    if (((Integer)seuil.getSeuilMax()).intValue() < charges && ((Integer)seuil.getSeuilMax()).intValue() != 0)
		    {
			float plusHeight = ((float)((Integer)seuil.getSeuilMax()).intValue()/(float)chargesMax)*200 ;
			float plusPos = 230 - plusHeight ;
			ligne1.setRect((float)(ITERATION_WIDTH * i + 10), plusPos, RECT_DIM1, plusHeight) ;
			g2d.setPaint(Color.red) ;
			g2d.fill(ligne1) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne1) ;
		    }		    
		    
		    
		    ligne1.setRect((float)(ITERATION_WIDTH * i + 5), y1Pos, RECT_DIM1, y1Height) ;
		    g2d.setPaint(color1) ;
		    g2d.fill(ligne1) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne1) ;		    
		    g2d.drawString((new Integer(charges)).toString(), ITERATION_WIDTH * i + 5, y1Pos - 10) ;
		 }
		 
		 
		 // affichage de la deuxieme colonne : nombre participants
		 int participants = tempIndIt.getNombreParticipants() ;
		 if (participants > 0)
		 {
		    float y2Height = ((float)participants/(float)participantsMax)*200 ; // longueur de la barre
		    float y2Pos = 230 - y2Height ; // position de la barre
		    
		    
		    seuil = seuils.getNombreParticipantIteration() ;
		    // si les seuils ne sont pas atteints
		    if (((Integer)seuil.getSeuilMin()).intValue() > participants && ((Integer)seuil.getSeuilMin()).intValue() != 0)
		    {
			float minusHeight = ((float)((Integer)seuil.getSeuilMin()).intValue()/(float)participantsMax)*200 ;
			float minusPos = 230 - minusHeight ;
			ligne2.setRect((float)(ITERATION_WIDTH * i + 25 + RECT_DIM1), minusPos, RECT_DIM1, minusHeight) ;
			g2d.setPaint(new Color(150, 150, 150)) ;
			g2d.fill(ligne2) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne2) ;
		    }
		    
		    
		    // si les seuils sont depasses
		    if (((Integer)seuil.getSeuilMax()).intValue() < participants && ((Integer)seuil.getSeuilMax()).intValue() != 0)
		    {
			float plusHeight = ((float)((Integer)seuil.getSeuilMax()).intValue()/(float)participantsMax)*200 ;
			float plusPos = 230 - plusHeight ;
			ligne2.setRect((float)(ITERATION_WIDTH * i + 25 + RECT_DIM1), plusPos, RECT_DIM1, plusHeight) ;
			g2d.setPaint(Color.red) ;
			g2d.fill(ligne2) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne2) ;
		    }
		    
		    
		    ligne2.setRect((float)(ITERATION_WIDTH * i + RECT_DIM1 + 20), y2Pos, RECT_DIM1, y2Height) ;
		    g2d.setPaint(color2) ;
		    g2d.fill(ligne2) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne2) ;
		    g2d.drawString((new Integer(participants)).toString(), ITERATION_WIDTH * i + 20 + RECT_DIM1, y2Pos - 10) ;
		 }
		 
		 
		 // affichage de la troisieme colonne : moyenne charge/participant
		 float moyenneCharges = tempIndIt.getChargeMoyenneParticipants() ;		 
		 if (moyenneCharges > 0)
		 {
		    float y3Height = (moyenneCharges/moyenneChargesMax)*200 ; // longueur de la barre
		    float y3Pos = 230 - y3Height ; // position de la barre
		    
		    seuil = seuils.getChargeMoyenne() ;
		    // si les seuils ne sont pas atteints
		    if (((Float)seuil.getSeuilMin()).intValue() > moyenneCharges && ((Float)seuil.getSeuilMin()).intValue() != 0)
		    {
			float minusHeight = (((Float)seuil.getSeuilMin()).floatValue()/moyenneChargesMax)*200 ;
			float minusPos = 230 - minusHeight ;
			ligne3.setRect((float)(ITERATION_WIDTH * i + 50 + RECT_DIM1), minusPos, RECT_DIM1, minusHeight) ;
			g2d.setPaint(new Color(150, 150, 150)) ;
			g2d.fill(ligne3) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne3) ;
		    }
		    
		    
		    // si les seuils sont depasses
		    if (((Float)seuil.getSeuilMax()).intValue() < moyenneCharges && ((Float)seuil.getSeuilMax()).intValue() != 0)
		    {
			float plusHeight = (((Float)seuil.getSeuilMax()).intValue()/moyenneChargesMax)*200 ;
			float plusPos = 230 - plusHeight ;
			ligne3.setRect((float)(ITERATION_WIDTH * i + 50 + RECT_DIM1), plusPos, RECT_DIM1, plusHeight) ;
			g2d.setPaint(Color.red) ;
			g2d.fill(ligne3) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne3) ;
		    }
		    
		    
		    ligne3.setRect((float)(ITERATION_WIDTH * i + RECT_DIM1 + 45), y3Pos, RECT_DIM1, y3Height) ;
		    g2d.setPaint(color3) ;
		    g2d.fill(ligne3) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne3) ;
		    g2d.drawString(nf.format(moyenneCharges), ITERATION_WIDTH * i + 45+ RECT_DIM1, y3Pos - 10) ;
		 }
		 
		 
		 // affichage de la quatrieme colonne : nombre de taches terminees
		 int tachesTerminees = tempIndIt.getNombreTachesTerminees() ;
		 if (tachesTerminees > 0)
		 {
		    float y4Height = ((float)tachesTerminees/(float)tachesTermineesMax)*200 ; // longueur de la barre
		    float y4Pos = 230 - y4Height ; // position de la barre
		    
		    seuil = seuils.getTacheTermineesIteration() ;
		    // si les seuils ne sont pas atteints
		    if (((Integer)seuil.getSeuilMin()).intValue() > tachesTerminees && ((Integer)seuil.getSeuilMin()).intValue() != 0)
		    {
			float minusHeight = ((float)((Integer)seuil.getSeuilMin()).intValue()/(float)tachesTermineesMax)*200 ;
			float minusPos = 230 - minusHeight ;
			ligne4.setRect((float)(ITERATION_WIDTH * i + 75 + RECT_DIM1), minusPos, RECT_DIM1, minusHeight) ;
			g2d.setPaint(new Color(150, 150, 150)) ;
			g2d.fill(ligne4) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne4) ;
		    }
		    
		    
		    // si les seuils sont depasses
		    if (((Integer)seuil.getSeuilMax()).intValue() < tachesTerminees && ((Integer)seuil.getSeuilMax()).intValue() != 0)
		    {
			float plusHeight = ((float)((Integer)seuil.getSeuilMax()).intValue()/(float)tachesTermineesMax)*200 ;
			float plusPos = 230 - plusHeight ;
			ligne4.setRect((float)(ITERATION_WIDTH * i + 75 + RECT_DIM1), plusPos, RECT_DIM1, plusHeight) ;
			g2d.setPaint(Color.red) ;
			g2d.fill(ligne4) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne4) ;
		    }
		    
		    ligne4.setRect((float)(ITERATION_WIDTH * i + RECT_DIM1 + 70), y4Pos, RECT_DIM1, y4Height) ;
		    g2d.setPaint(color4) ;
		    g2d.fill(ligne4) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne4) ;
		    g2d.drawString((new Integer(tachesTerminees)).toString(), ITERATION_WIDTH * i + 70+ RECT_DIM1, y4Pos - 10) ;
		 }
		 
		 
		 // affichage de la cinquieme colonne : taches par participant
		 float tachesParticipants = tempIndIt.getNombreMoyenTachesParticipants() ;
		 if (tachesParticipants > 0)
		 {
		    float y5Height = ((float)tachesParticipants/(float)tachesParticipantsMax)*200 ; // longueur de la barre
		    float y5Pos = 230 - y5Height ; // position de la barre
		    
		    seuil = seuils.getNombreTacheParticipant() ;
		    // si les seuils ne sont pas atteints
		    if (((Float)seuil.getSeuilMin()).intValue() > tachesParticipants && ((Float)seuil.getSeuilMin()).intValue() != 0)
		    {
			float minusHeight = (((Float)seuil.getSeuilMin()).intValue()/(float)tachesParticipantsMax)*200 ;
			float minusPos = 230 - minusHeight ;
			ligne5.setRect((float)(ITERATION_WIDTH * i + 100 + RECT_DIM1), minusPos, RECT_DIM1, minusHeight) ;
			g2d.setPaint(new Color(150, 150, 150)) ;
			g2d.fill(ligne5) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne5) ;
		    }
		    
		    
		    // si les seuils sont depasses
		    if (((Float)seuil.getSeuilMax()).intValue() < tachesParticipants && ((Float)seuil.getSeuilMax()).intValue() != 0)
		    {
			float plusHeight = (((Float)seuil.getSeuilMax()).intValue()/(float)tachesParticipantsMax)*200 ;
			float plusPos = 230 - plusHeight ;
			ligne5.setRect((float)(ITERATION_WIDTH * i + 100 + RECT_DIM1), plusPos, RECT_DIM1, plusHeight) ;
			g2d.setPaint(Color.red) ;
			g2d.fill(ligne5) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne5) ;
		    }
		    
		    
		    ligne5.setRect((float)(ITERATION_WIDTH * i + RECT_DIM1 + 95), y5Pos, RECT_DIM1, y5Height) ;
		    g2d.setPaint(color5) ;
		    g2d.fill(ligne5) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne5) ;
		    g2d.setPaint(new Color(50, 50, 50)) ;
		    g2d.drawString(nf.format(tachesParticipants), ITERATION_WIDTH * i + 95+ RECT_DIM1, y5Pos - 10) ;
		 }
		 
		 
		 // affichage de la sixieme colonne : duree moyenne des taches
		 float dureeMoyenneTache = tempIndIt.getDureeMoyenneTaches() ;
		 if (dureeMoyenneTache > 0)
		 {
		    float y6Height = ((float)dureeMoyenneTache/(float)dureeMoyenneTacheMax)*200 ; // longueur de la barre
		    float y6Pos = 230 - y6Height ; // position de la barre
		    
		    seuil = seuils.getDureeMoyenneIteration() ;
		    // si les seuils ne sont pas atteints
		    if (((Float)seuil.getSeuilMin()).intValue() > dureeMoyenneTache && ((Float)seuil.getSeuilMin()).intValue() != 0)
		    {
			float minusHeight = (((Float)seuil.getSeuilMin()).intValue()/(float)dureeMoyenneTacheMax)*200 ;
			float minusPos = 230 - minusHeight ;
			ligne6.setRect((float)(ITERATION_WIDTH * i + 125 + RECT_DIM1), minusPos, RECT_DIM1, minusHeight) ;
			g2d.setPaint(new Color(150, 150, 150)) ;
			g2d.fill(ligne6) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne6) ;
		    }
		    
		    
		    // si les seuils sont depasses
		    if (((Float)seuil.getSeuilMax()).intValue() < dureeMoyenneTache && ((Float)seuil.getSeuilMax()).intValue() != 0)
		    {
			float plusHeight = (((Float)seuil.getSeuilMax()).intValue()/(float)dureeMoyenneTacheMax)*200 ;
			float plusPos = 230 - plusHeight ;
			ligne6.setRect((float)(ITERATION_WIDTH * i + 125 + RECT_DIM1), plusPos, RECT_DIM1, plusHeight) ;
			g2d.setPaint(Color.red) ;
			g2d.fill(ligne6) ;
			g2d.setPaint(Color.black) ;
			g2d.draw(ligne6) ;
		    }
		    
		    
		    ligne6.setRect((float)(ITERATION_WIDTH * i + RECT_DIM1 + 120), y6Pos, RECT_DIM1, y6Height) ;
		    g2d.setPaint(color6) ;
		    g2d.fill(ligne6) ;
		    g2d.setPaint(Color.black) ;
		    g2d.draw(ligne6) ;
		    g2d.drawString(nf.format(dureeMoyenneTache), ITERATION_WIDTH * i + 120+ RECT_DIM1, y6Pos - 10) ;
		 }
		 
		 
		 // lignes du bas pour delimiter l'iteration
		 underline.setLine(new Float(ITERATION_WIDTH * i + 5).floatValue(), 250.0, new Float(ITERATION_WIDTH - 15 +ITERATION_WIDTH * i).floatValue(), 250.0) ;
		 g2d.draw(underline) ;
		 limit.setLine(new Float(ITERATION_WIDTH * i + 5).floatValue(), 250.0, new Float(5 + ITERATION_WIDTH * i).floatValue(), 240.0) ;
		 g2d.draw(limit) ;
		 limit.setLine(new Float(ITERATION_WIDTH - 15 +ITERATION_WIDTH * i).floatValue(), 250.0, new Float(ITERATION_WIDTH - 15 +ITERATION_WIDTH * i).floatValue(), 240.0) ;
		 g2d.draw(limit) ;
		 g2d.drawString("Iteration "+tempIt.getNumero(), ITERATION_WIDTH * i + 20, 245) ;
            }
        } 
    }
    
}
