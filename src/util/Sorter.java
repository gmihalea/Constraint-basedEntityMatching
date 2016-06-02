package util;

import core.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class that incapsulates a sorter.
 */
public class Sorter {

    public static ArrayList<? extends Item> sortListByCriteria(final ArrayList<? extends Item> list,
                                                     final int type,
                                                     final String criteria) {

        Collections.sort(list, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2)
            {
                return  Double.parseDouble(item1.getAttributes().get(criteria).get(Constants.CONSTRAINTS_INDEX))
                        - Double.parseDouble(item2.getAttributes().get(criteria).get(Constants.CONSTRAINTS_INDEX))
                        < 0 ? -1 : 1;
            }
        });

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
