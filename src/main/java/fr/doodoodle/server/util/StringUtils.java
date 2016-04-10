package fr.doodoodle.server.util;

/**
 * Created by Florent on 10/04/2016.
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String string){
        if(string==null || string.trim().equals("")){
            return true;
        }
        return false;
    }
}
