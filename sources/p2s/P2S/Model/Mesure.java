package P2S.Model;

/**
 *
 * @author  Fabien
 */
public class Mesure {
    
    private String _nom;
    private int _limiteInf;
    private int _limiteSup;
    private String _description;
    
    /** Creates a new instance of Mesure */
    public Mesure(String nom, int limiteInf, int limiteSup, String description) {
        this._nom = nom;
        this._limiteInf = limiteInf;
        this._limiteSup = limiteSup;
        this._description = description;
    }
    
    /**
     * Getter for property _nom.
     * @return Value of property _nom.
     */
    public java.lang.String get_nom() {
        return _nom;
    }
    
    /**
     * Setter for property _nom.
     * @param _nom New value of property _nom.
     */
    public void set_nom(java.lang.String _nom) {
        this._nom = _nom;
    }
    
    /**
     * Getter for property _limiteInf.
     * @return Value of property _limiteInf.
     */
    public int get_limiteInf() {
        return _limiteInf;
    }
    
    /**
     * Setter for property _limiteInf.
     * @param _limiteInf New value of property _limiteInf.
     */
    public void set_limiteInf(int _limiteInf) {
        this._limiteInf = _limiteInf;
    }
    
    /**
     * Getter for property _limiteSup.
     * @return Value of property _limiteSup.
     */
    public int get_limiteSup() {
        return _limiteSup;
    }
    
    /**
     * Setter for property _limiteSup.
     * @param _limiteSup New value of property _limiteSup.
     */
    public void set_limiteSup(int _limiteSup) {
        this._limiteSup = _limiteSup;
    }
    
    /**
     * Getter for property _description.
     * @return Value of property _description.
     */
    public java.lang.String get_description() {
        return _description;
    }
    
    /**
     * Setter for property _description.
     * @param _description New value of property _description.
     */
    public void set_description(java.lang.String _description) {
        this._description = _description;
    }
    
}
