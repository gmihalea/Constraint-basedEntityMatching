package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import util.*;

/**
 * core.Matcher class.
 */
public class Matcher {

    /**
     * List of entities of type A.
     */
    private ArrayList<Entity> aEntity;

    /**
     * List of entities of type B.
     */
    private ArrayList<Entity> bEntity;

    /**
     * List of constraints.
     */
    private Constraint constraints;


    public Matcher(final ArrayList<Entity> aEntity,
                   final ArrayList<Entity> bEntity,
                   final ArrayList<Constraint> constraints) {
        this.aEntity = aEntity;
        this.bEntity = bEntity;
        this.constraints = constraints.get(Constants.CONSTRAINTS_INDEX);
    }

    /**
     * Does all the matching logic and writes the result in a specific file
     */
    public void matchEntities() {
        //TODO implementation
    }

    /**
     * Check if tow entities are eligible for the matching process.
     * @param a,b Entities to be checked.
     * @return true if the entities are eligible, and false otherwise.
     */
    public boolean checkEligibility(final Entity a, final Entity b) throws IOException {

        final HashMap<String, ArrayList<String>> aTypeHashMap = a.getAttributes();
        final HashMap<String, ArrayList<String>> bTypeHashMap = b.getAttributes();

        for (HashMap.Entry<String, ArrayList<String>> constraint : this.constraints.getAttributes().entrySet()) {
            //Sanity checks.
            if(!aTypeHashMap.containsKey(constraint.getKey()) || !bTypeHashMap.containsKey(constraint.getKey())){
                System.out.println("[ERROR] The key " + constraint.getKey() + " does not appear in both entities.");
                return false;
            }
            if (!this.evaluateConstraint(aTypeHashMap.get(constraint.getKey()),
                    bTypeHashMap.get(constraint.getKey()),
                    constraint)){
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates if two given values respect the constraint.
     * @param aTypeValue value of type A.
     * @param bTypeValue value of type B.
     * @param constraint the constraint.
     * @return
     */
    public boolean evaluateConstraint(final ArrayList<String> aTypeValue,
                                      final ArrayList<String> bTypeValue,
                                      final HashMap.Entry<String, ArrayList<String>> constraint) throws IOException {

        // Iterates through all the list, in case the constraint is more complex
        // e.g. min1, max3
        for (int i = 0; i < constraint.getValue().size(); ++i) {
            // All the elements in aTypeValue must be different from the
            // elements in bTypeValue
            if (constraint.getValue().get(i).equals(Constants.DIFF)) {
                // If the two lists are not entirely different, then the constraint is not respected
                if(!this.checkDiff(aTypeValue, bTypeValue)) {
                    Printer.printInFile("[REASON]:" + "<" + constraint.getKey() + ": " + constraint.getValue().get(i)
                            + "> " +  aTypeValue +  " vs. " + bTypeValue);
                    return false;
                }
            }
            if (constraint.getValue().get(i).startsWith(Constants.MIN)) {
                // Extract the number that follows the string MIN
                final int count = Integer.parseInt(constraint.getValue().get(i).substring(Constants.MIN.length()));

                // The number of attributes in common must be greater than or equal to count
                if(this.computeAttributesInCommon(aTypeValue, bTypeValue) < count) {
                    Printer.printInFile("[REASON]:" + "<" + constraint.getKey() + ": " + constraint.getValue().get(i)
                            + "> " +  "[" + this.computeAttributesInCommon(aTypeValue, bTypeValue) +  " vs. " + count + "]");
                    return false;
                }
            }
            if (constraint.getValue().get(i).startsWith(Constants.MAX)) {
                final int count = Integer.parseInt(constraint.getValue().get(i).substring(Constants.MAX.length()));

                // The number of attributes in common must be lower than or equal to count
                if(this.computeAttributesInCommon(aTypeValue, bTypeValue) > count) {
                    Printer.printInFile("[REASON]:" + "<" + constraint.getKey() + ": " + constraint.getValue().get(i)
                            + "> " + "[" + this.computeAttributesInCommon(aTypeValue, bTypeValue) +  " vs. " + count + "]");
                    return false;
                }
            }
//            if (constraint.getValue().get(i).startsWith(Constants.GMT_MIN)) {
//                final int count = Integer.parseInt(constraint.getValue().get(i).substring(Constants.GMT_MIN.length()));
//
//                // Extract both timezones and compare them
//                final int aTimeZone = Integer.parseInt(aTypeValue.get(0).substring(Constants.GMT.length()));
//                final int bTimeZone = Integer.parseInt(bTypeValue.get(0).substring(Constants.GMT.length()));
//
//                // If the difference between the two timezones is bigger than count,
//                // then the constraint is not respected.
//                if (Math.abs(aTimeZone - bTimeZone) > count) {
//                    System.out.println("[REASON]:" + constraint.getValue().get(i) + "["
//                            + aTypeValue.get(0) + " vs. " + bTypeValue + "]");
//                    return false;
//                }
//            }
        }
        return true;
    }

    /**
     * Checks if two given lists contain entirely different elements.
     * E.g. for (1,2,3) and (1,2,4) the result must be false.
     * E.g. for (1,2,3) and (4,5,6) the result must be true.
     * @param aTypeValue the first list
     * @param bTypeValue the second list
     * @return true if the lists are entirely different, false otherwise.
     */
    public boolean checkDiff(final ArrayList<String> aTypeValue, final ArrayList<String> bTypeValue) {
        int noOfDifferences = 0;

        for (int i = 0; i < aTypeValue.size(); ++i) {
            if(!CustomContains.containsCaseInsensitive(aTypeValue.get(i), bTypeValue)) {
                ++noOfDifferences;
            }
        }

        if (noOfDifferences == aTypeValue.size()) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the number of attributes in common.
     * @param aTypeValue the first list.
     * @param bTypeValue the second list.
     * @return the number of attributes in common.
     */
    public int computeAttributesInCommon(final ArrayList<String> aTypeValue, final ArrayList<String> bTypeValue) {
        int noOfCommonAttributes = 0;

        for (int i = 0; i < aTypeValue.size(); ++i) {
            if(CustomContains.containsCaseInsensitive(aTypeValue.get(i), bTypeValue)) {
                ++noOfCommonAttributes;
            }
        }
        return noOfCommonAttributes;
    }
}
