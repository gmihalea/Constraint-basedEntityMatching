package server.statistics;

import server.core.Entity;

import java.util.ArrayList;

import static server.util.CustomContains.containsCaseInsensitive;

/**
 * Class that computes all the statistics needed.
 */
public class Statistics {

    /**
     * Computes the percentage of appearance of a specific value in a list.
     * @param attribute specific attribute (e.g. Country, Languages, etc.)
     * @param valueToCompute the value of the attribute (e.g. Romania, Poland, etc.)
     * @param entities the list of entities
     * @return the percentage
     */
    public static float getPercentage(final String attribute, final String valueToCompute,
                                final ArrayList<Entity> entities) {
        int noOfAppearances = 0;

        for(Entity e : entities) {
            if(containsCaseInsensitive(valueToCompute, e.getAttributes().get(attribute))) {
                ++noOfAppearances;
            }
        }
        return ((float) noOfAppearances) / ((float) entities.size()) * 100;
    }

    /**
     * Collects all possible values for a specific attribute
     * @param attribute specified attribute
     * @param entities list of entities
     * @return list of all possible values
     */
    public static ArrayList<String> collectAllValues(final String attribute, final ArrayList<Entity> entities) {
        final ArrayList<String> allValues = new ArrayList<>();

        for(Entity e : entities) {
            final ArrayList<String> listOfValues = e.getAttributes().get(attribute);
            listOfValues.stream().filter(value -> !containsCaseInsensitive(value, allValues)).forEach(allValues::add);
        }
        return allValues;
    }

    public static ArrayList<String> getMostCommonAttribute(final String attribute, final ArrayList<Entity> entities) {
        return null;
    }

}
