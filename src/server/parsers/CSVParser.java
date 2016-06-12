package server.parsers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import server.core.*;
import server.util.*;

/**
 * Class that parses a CSV file.
 */
public class CSVParser {

    /**
     * Parses the CSV file.
     * @return the list of Entities associated with the CSV file.
     */
    public static ArrayList<? extends Item> parseCSV(final String path, final String type) {
        if(!Constants.CONSTRAINT.equals(type) && !Constants.ENTITY.equals(type)) {
            System.out.println("[ERROR] Method < parseCSV > called with wrong parameter < " + type + " >");
            return null;
        }

        final ArrayList<Constraint> constraints = new ArrayList();
        final ArrayList<Entity> entities = new ArrayList();
        HashMap<String, ArrayList<String>> hashMap;
        boolean keyCollected = false;
        BufferedReader br = null;
        String[] keys = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                hashMap = new HashMap();
                if (!keyCollected) {
                    keys = line.split(Constants.CSV_SEPARATOR);
                    CSVParser.initializeHashMap(hashMap, keys);
                    keyCollected = true;
                } else {
                    final String[] attributes = line.split(Constants.CSV_SEPARATOR);
                    CSVParser.addAttributes(hashMap, attributes, keys);
                    if(Constants.ENTITY.equals(type)) {
                        entities.add(new Entity(hashMap));
                    } else {
                        constraints.add(new Constraint(hashMap));
                    }
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
        return Constants.CONSTRAINT.equals(type) ? constraints : entities;
    }

    /**
     * Initializes the HashMap with specific keys
     * @param hashMap the hashMap to initialize
     * @param keys the specific keys
     */
    private static void initializeHashMap(final HashMap<String, ArrayList<String>> hashMap, final String[] keys) {
        final ArrayList<String> emptyList = new ArrayList<>();

        for (int i = 0; i < keys.length; ++i) {
            hashMap.put(keys[i], emptyList);
        }
    }

    /**
     * Adds values to a specific key of the HashMap.
     * @param hashMap the hashMap that contains the key
     * @param attributes attributes
     * @param keys keys to add
     */
    private static void addAttributes(final HashMap<String, ArrayList<String>> hashMap,
                                      final String[] attributes, final String[] keys) {
        ArrayList<String> value;

        for (int i = 0; i < attributes.length; ++i) {
            value = new ArrayList<>();
            final String[] pieces = attributes[i].split(Constants.SPACE);

            for (int j = 0; j < pieces.length; ++j){
                if (!pieces[j].equals(Constants.EMPTY_STRING)) {
                    value.add(pieces[j]);
                }
            }
            hashMap.put(keys[i], value);
        }
    }
}
