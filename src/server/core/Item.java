package server.core;

import server.parsers.CSVParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that incapsulates an item.
 */
public class Item {
    /**
     * HashMap containing the attributes of the item
     */
    private HashMap<String, ArrayList<String>> attributes;

    public Item(){
        this.attributes = null;
    }

    public Item(final HashMap<String, ArrayList<String>> attributes) {
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
    public void printAttributes() throws IOException {
        CSVParser.printHashMap(this.attributes);
    }

}
