/*
 * IndicateursTache.java
 *
 * Created on 9 février 2005, 21:35
 */

package P2S.Model;

/**
 *
 * @author LAFFARGUE
 */
public class IndicateursTache {
    
    //ATTRIBUTS
    private int nombreTotal;
    private int nombreTerminees;
    private int dureeMoyenne;
    
    //CONSTRUCTEUR
    
    //Constructeur sans paramètres
    public IndicateursTache() {
        
    }
    
    public IndicateursTache(int _nombreTotal,int _nombreTerminees,int _dureeMoyenne) {
        this.nombreTotal = _nombreTotal;
        this.nombreTerminees = _nombreTerminees;
        this.dureeMoyenne = _dureeMoyenne;
        
    }
    
    public int getNombreTotal() {
        return nombreTotal;
    }
    
    public void setNombreTotal(int nombreTotal) {
        this.nombreTotal = nombreTotal;
    }
    
    public int getNombreTerminees() {
        return nombreTerminees;
    }
    
    public void setNombreTerminees(int nombreTerminees) {
        this.nombreTerminees = nombreTerminees;
    }
    
    public int getDureeMoyenne() {
        return dureeMoyenne;
    }
    
    public void setDureeMoyenne(int dureeMoyenne) {
        this.dureeMoyenne = dureeMoyenne;
    }
    
    
    
    //GETTEURS ET SETTEURS
    
}
