/**
 * Copyright (c) 2016, Geanina Mihalea <geanina.mihalea@gmail.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
