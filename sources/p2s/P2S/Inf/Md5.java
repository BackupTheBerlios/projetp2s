/*
 * Md5.java
 *
 * Created on 10 f�vrier 2005, 23:37
 */

package P2S.Inf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Guillaume
 */
public class Md5 {
    
    public static String getEncodedPassword(String key) {
        byte[] uniqueKey = key.getBytes();
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no MD5 support in this VM");
        }
        StringBuffer hashString = new StringBuffer();
        for ( int i = 0; i < hash.length; ++i ) {
            String hex = Integer.toHexString(hash[i]);
            if ( hex.length() == 1 ) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length()-1));
            } else {
                hashString.append(hex.substring(hex.length()-2));
            }
        }
        return hashString.toString();
    }
    
    /*
     * Test une chaine et une valeur encod� (chaine hexad�cimale)
     *
     * @param clearTextTestPassword : la chaine non cod� � tester
     * @param encodedActualPassword : la valeur hexa MD5 de r�f�rence
     *
     * @return true si v�rifi� false sinon
     */
    public static boolean testPassword(String clearTextTestPassword,
            String encodedActualPassword)
            throws NoSuchAlgorithmException {
        String encodedTestPassword = Md5.getEncodedPassword(
                clearTextTestPassword);
        
        return (encodedTestPassword.equals(encodedActualPassword));
    }
    
}


