/*
 * TacheCollaborative.java
 *
 * Created on 8 f�vrier 2005, 01:54
 */

package P2S.Model;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author LAFFARGUE
 *Cette classe h�rite de Tache mais n'h�rite pas de l'attribut 'r�alisateur' car une tache
 *collaborative est r�alis� par plusieurs personnes,ainsi l'attribut a �t� mis en priv�
 *donc non visible dans cette classe.
 */
public class TacheCollaborative extends Tache {
    
    //ATTRIBUTS
    private Vector realisateurs;
    
    //CONSTRUCTEURS
    public TacheCollaborative(){
        super();
        this.realisateurs = new Vector();
    }
    
    public TacheCollaborative(String _nom, String _description, String _etat,int _chargePrevue, int _tempsPasse, int _resteAPasser, Date _dateDebutPrevue, Date _dateDebutReelle,Date _dateFinPrevue,Date _dateFinReelle) {
        super(_nom,_description,_etat,_chargePrevue,_tempsPasse,_resteAPasser,_dateDebutPrevue,_dateDebutReelle,_dateFinPrevue,_dateFinReelle);
        this.listeArtefactsEntrees = new Vector();
        this.listeArtefactsSorties = new Vector();
        this.setRealisateurs(new Vector());
    }
    
    public TacheCollaborative(String _nom, String _description, String _etat,int _chargePrevue, int _tempsPasse, int _resteAPasser, Date _dateDebutPrevue, Date _dateDebutReelle,Date _dateFinPrevue,Date _dateFinReelle,Vector _realisateurs) {
        super(_nom,_description,_etat,_chargePrevue,_tempsPasse,_resteAPasser,_dateDebutPrevue,_dateDebutReelle,_dateFinPrevue,_dateFinReelle);
        this.listeArtefactsEntrees = new Vector();
        this.listeArtefactsSorties = new Vector();
        this.setRealisateurs(_realisateurs);
    }
    
    //GETTEURS ET SETTEURS
    
    public Vector getRealisateurs() {
        return realisateurs;
    }
    
    public void setRealisateurs(Vector realisateurs) {
        this.realisateurs = realisateurs;
    }
    
}
