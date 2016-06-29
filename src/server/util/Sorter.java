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

package server.util;

import server.core.Item;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that incapsulates a sorter.
 */
public class Sorter {

    public static ArrayList<? extends Item> sortListByCriteria(final ArrayList<? extends Item> list,
                                                               final int type,
                                                               final String criteria) {

        /* Sorts by the Score criteria (checks which of the items has a bigger score) */
        if(Constants.SCORE_CRITERIA.equals(criteria)) {
            Collections.sort(list, (item1, item2) -> {
                final double diff = Double.parseDouble(item1.getAttributes().get(criteria)
                        .get(Constants.CONSTRAINTS_INDEX))
                                - Double.parseDouble(item2.getAttributes().get(criteria)
                        .get(Constants.CONSTRAINTS_INDEX));

                if (diff == 0)
                    return 0;

                return diff < 0 ? -1 : 1;
            });
        /* Sorts by the ProgrammingLanguages criteria (checks which of the items' list has more elements) */
        } else if(Constants.PROGRAMMING_LANGUAGES_CRITERIA.equals(criteria)) {
            Collections.sort(list, (item1, item2) -> {
                final int noOfProgrammingLanguages1 = item1.getAttributes().get(criteria).size();
                final int noOfProgrammingLanguages2 = item2.getAttributes().get(criteria).size();

                if(noOfProgrammingLanguages1 == noOfProgrammingLanguages2)
                    return 0;

                return noOfProgrammingLanguages1 - noOfProgrammingLanguages2 < 0 ? -1 : 1;
            });
        } else if(Constants.TIME_ZONE_CRITERIA.equals(criteria)) {
            Collections.sort(list, (item1, item2) -> {
                final String timeZone1 = item1.getAttributes().get(criteria).get(Constants.CONSTRAINTS_INDEX);
                final String timeZone2 = item2.getAttributes().get(criteria).get(Constants.CONSTRAINTS_INDEX);

                final int delay1 = Integer.parseInt(timeZone1.substring(Constants.GMT.length(), timeZone1.length()));
                final int delay2 = Integer.parseInt(timeZone2.substring(Constants.GMT.length(), timeZone2.length()));

                if(delay1 == delay2)
                    return 0;

                return delay1 - delay2 < 0 ? -1 : 1;
            });
        }

        if(type == Constants.ASCENDING_SORT) {
            return list;
        } else if(type == Constants.DESCENDING_SORT) {
            Collections.reverse(list);
            return list;
        }

        System.out.println("[ERROR]: Wrong parameter <type> in sortListByCriteria().");
        return null;
    }
}
