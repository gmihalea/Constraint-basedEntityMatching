package server.util;

import java.util.ArrayList;

/**
 * Class that incapsulates a Case Insensitive Contains.
 */
public class CustomContains {

    public static boolean containsCaseInsensitive(final String strToCompare, final ArrayList<String> list)
    {
        for(final String str : list) {
            if(str.equalsIgnoreCase(strToCompare)) {
                return true;
            }
        }
        return false;
    }
}
