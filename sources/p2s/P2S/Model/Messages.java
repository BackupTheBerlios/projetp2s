/*
 * Messages.java
 *
 * Created on 7 mars 2005, 21:58
 */

package P2S.Model;
import java.util.*;

/**
 *
 * @author kassem
 */
public class Messages {
    private String login;
    private String sujet;
    private String message;
    private Date date;
    /** Creates a new instance of Messages */
    
    //Constructeur
    public Messages(String login)
    {
        this.login = login;
        this.sujet = new String("");
        this.date = new Date();
        this.message = new String("");
    }
    
    public Messages(String login, String sujet, String message, Date date)
    {
        this.login = login;
        this.sujet = sujet;
        this.date = date;
        this.message = message;
    }
    
    //setteurs
    public void setSujet(String sujet)
    {
        this.sujet = sujet;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    //guetteurs
    public String getDestinataire()
    {
        return this.login;
    }
    
    public String getSujet()
    {
        return this.sujet;
    }
    
    public Date getDate()
    {
        return this.date;
    }
    
    public String getMessage()
    {
        return this.message;
    }
}
