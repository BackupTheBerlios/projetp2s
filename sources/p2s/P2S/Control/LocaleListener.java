package P2S.Control;

import java.util.*;

/**
 * Listener pour le changement de langue
 * @author Fabien Bouyssou
 */
public interface LocaleListener extends EventListener {
    /**
     * Methode changeant la langue
     */    
    public void localeChanged (); 
}
