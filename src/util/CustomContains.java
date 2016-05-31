package util;

import java.util.ArrayList;

/**
 * Class that incapsulates a Case Insensitive Contains.
 */
public class CustomContains {

    public static boolean containsCaseInsensitive(String strToCompare, ArrayList<String> list)
    {
        for(String str : list) {
            if(str.equalsIgnoreCase(strToCompare)) {
                return true;
            }
        }
        return false;
    }
}
