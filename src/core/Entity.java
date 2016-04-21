package core;

import parsers.CSVParser;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that incapsulates the attributes of an entity.
 */
public class Entity {

    /**
     * HashMap containing the attributes of the entity
     */
    private HashMap<String, ArrayList<String>> attributes;


    public Entity(final HashMap<String, ArrayList<String>> attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * @return the HashMap of attributes
     */
    public HashMap<String, ArrayList<String>> getAttributes() {
        return this.attributes;
    }

    /**
     * Print the HashMap of attributes.
     */
    public void printAttributes() {
        CSVParser.printHashMap(this.attributes);
    }
}
