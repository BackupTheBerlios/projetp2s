package P2S.Control;

import java.util.*;
import javax.swing.event.*;
import P2S.Control.Bundle.*;


/**
 * Controller qui gère le changement de langue
 * @author Fabien Bouyssou
 */
public class LocaleController {
    
    /**
     * Liste contenant tous les LocaleListener
     */    
    protected EventListenerList listenerList = new EventListenerList(); 
    
    /**
     * Ajoute un LocaleListener
     * @param l LocaleListener à ajouter à la liste
     */    
    public void addLocaleListener (LocaleListener l) { 
        listenerList.add(LocaleListener.class, l); 
    } 
    
    /**
     * Supprime un LocaleListener
     * @param l LocaleListener à supprimer
     */    
    public void removeLocaleListener (LocaleListener l) { 
        listenerList.remove(LocaleListener.class, l); 
    } 
    
    /**
     * Change la langue de tous les LocaleListener enregistrés
     */    
    public void fireLocaleChanged () { 
        LocaleListener[] listeners = (LocaleListener[]) 
            listenerList.getListeners(LocaleListener.class); 
       
        for (int i = 0; i < listeners.length; i++) { 
            listeners[i].localeChanged(); 
        } 
    } 
    
    /**
     * Change la locale et appelle fireLocaleChanged()
     * @param newLocale Nouvelle Locale
     */    
    public void changeLocale (Locale newLocale) { 
        //Locale.setDefault(newLocale); 
        Bundle.setCurrentLocale(newLocale);
        fireLocaleChanged(); 
    }
    
}
