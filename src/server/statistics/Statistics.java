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


}
