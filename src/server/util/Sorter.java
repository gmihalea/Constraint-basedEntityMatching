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

        // Sorts by the Score criteria (checks which of the items has a bigger score)
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
        // Sorts by the ProgrammingLanguages criteria (checks which of the items' list has more elements)
        } else if(Constants.PROGRAMMING_LANGUAGES_CRITERIA.equals(criteria)) {
            Collections.sort(list, (item1, item2) -> {
                final int noOfProgrammingLanguages1 = item1.getAttributes().get(criteria).size();
                final int noOfProgrammingLanguages2 = item2.getAttributes().get(criteria).size();

                if(noOfProgrammingLanguages1 == noOfProgrammingLanguages2)
                    return 0;

                return noOfProgrammingLanguages1 - noOfProgrammingLanguages2 < 0 ? -1 : 1;
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
