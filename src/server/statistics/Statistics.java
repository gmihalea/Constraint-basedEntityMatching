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

package server.statistics;

import server.core.Entity;

import java.util.ArrayList;
import static java.util.Collections.*;

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
    private static ArrayList<String> collectAllValues(final String attribute, final ArrayList<Entity> entities) {
        final ArrayList<String> allValues = new ArrayList<>();

        for(Entity e : entities) {
            final ArrayList<String> listOfValues = e.getAttributes().get(attribute);
            listOfValues.stream().filter(value -> !containsCaseInsensitive(value, allValues)).forEach(allValues::add);
        }
        return allValues;
    }

    /**
     * Computes a sorted list with the most common values of a specific attribute
     * @param attribute the specified attribute
     * @param entities list of entities
     * @return sorted list
     */
    public static ArrayList<String> getMostCommonValues(final String attribute, final ArrayList<Entity> entities) {
        final ArrayList<String> allValues = collectAllValues(attribute, entities);

        sort(allValues, (value1, value2) -> {
            final float percentage1 = getPercentage(attribute,value1, entities);
            final float percentage2 = getPercentage(attribute,value2, entities);

            if(percentage1 == percentage2)
                return 0;

            return percentage1 - percentage2 < 0 ? -1 : 1;
        });

        reverse(allValues);
        return allValues;
    }
}
