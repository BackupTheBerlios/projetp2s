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
    
}
