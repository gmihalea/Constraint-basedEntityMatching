package core;

import parsers.CSVParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by geanina on 30.05.2016.
 */
public class Constraint {
    /**
     * HashMap containing the attributes of the entity
     */
    private HashMap<String, ArrayList<String>> attributes;


    public Constraint(final HashMap<String, ArrayList<String>> attributes) {
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
