/*
 * Seuil.java
 *
 * Created on 21 mars 2005, 18:10
 */

package P2S.Model;

/**
 *
 * @author Guillaume
 */
public class Seuil {
    
    private Object seuilMin;
    private Object seuilMax;
    
    /** Creates a new instance of Seuil */
    public Seuil() {
    }
    
    public Seuil(Object seuilMin, Object seuilMax) {
        this.seuilMin = seuilMin;
        this.seuilMax = seuilMax;
    }
    
    public Object getSeuilMin() {
        return seuilMin;
    }
    
    public void setSeuilMin(Object seuilMin) {
        this.seuilMin = seuilMin;
    }
    
    public Object getSeuilMax() {
        return seuilMax;
    }
    
    public void setSeuilMax(Object seuilMax) {
        this.seuilMax = seuilMax;
    }
    
    public static int estHorsIntervalle(Float valeur,Float min,Float max){
        
        if(min.floatValue() == 0.0 && max.floatValue()==0.0)
            return 0;
        
        if(valeur.floatValue()<min.floatValue())
            return 1;
        
        if(valeur.floatValue() > max.floatValue())
            return 2;
        
        return 0;  
    }
    
        public static int estHorsIntervalle(Integer valeur,Integer min,Integer max){
        
        if(min.intValue() == 0 && max.intValue()==0)
            return 0;
        
        if(valeur.intValue()<min.intValue())
            return 1;
        
        if(valeur.intValue() > max.intValue())
            return 2;
        
        return 0;    
    }
    
}
