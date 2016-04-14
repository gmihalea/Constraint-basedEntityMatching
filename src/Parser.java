import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that parses a CSV file.
 */
public class Parser {

    /**
     * Parses the CSV file.
     * @return the list of Entities associated with the CSV file.
     */
    public static ArrayList<Entity> parseCSV(final String path) {

        ArrayList<Entity> entities = new ArrayList();
        HashMap<String, ArrayList<String>> hashMap;
        boolean keyCollected = false;
        BufferedReader br = null;
        String[] keys = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(path));

            while((line = br.readLine()) != null) {
                hashMap = new HashMap();
                if (!keyCollected) {
                    keys = line.split(Constants.CSV_SEPARATOR);
                    Parser.initializeHashMap(hashMap, keys);
                    keyCollected = true;
                } else {
                    String[] attributes = line.split(Constants.CSV_SEPARATOR);
                    Parser.addAttributes(hashMap, attributes, keys);
                    entities.add(new Entity(hashMap));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return entities;
    }

    /**
     * Initializes the HashMap with specific keys
     * @param hashMap
     * @param keys
     */
    private static void initializeHashMap(final HashMap<String, ArrayList<String>> hashMap, final String[] keys) {
        ArrayList<String> emptyList = new ArrayList<>();

        for(int i = 0; i < keys.length; ++i) {
            hashMap.put(keys[i], emptyList);
        }
    }

    /**
     * Adds values to a specific key of the HashMap.
     * @param hashMap
     * @param attributes
     * @param keys
     */
    private static void addAttributes(final HashMap<String, ArrayList<String>> hashMap,
                                      final String[] attributes, final String[]keys) {
        ArrayList<String> value;

        for(int i = 0; i < attributes.length; ++i) {
            value = new ArrayList<>();
            String[] pieces = attributes[i].split(Constants.SPACE);

            for(int j = 0; j < pieces.length; ++j){
                value.add(pieces[j]);
            }
            hashMap.put(keys[i], value);
        }
    }

    //TODO delete these print methods
    //For debug only
    /**
     * Prints the entire HashMap
     * @param attributes
     */
    public static void printHashMap(final HashMap<String, ArrayList<String>> attributes) {
        for (Map.Entry<String, ArrayList<String>> entry : attributes.entrySet()) {
            System.out.print(entry.getKey() + Constants.SPACE + Constants.COLON + Constants.SPACE);
            for (int i = 0; i < entry.getValue().size(); ++i) {
                System.out.print(entry.getValue().get(i) + Constants.COMMA + Constants.SPACE);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints a list of HashMaps
     * @param hashMaps
     */
    public static void printAllHashMaps(final ArrayList<HashMap<String, ArrayList<String>>> hashMaps) {
        for(int i = 0; i < hashMaps.size(); ++i) {
            Parser.printHashMap(hashMaps.get(i));
        }
    }
}
