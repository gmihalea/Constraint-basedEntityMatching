package core;

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
    private ArrayList<Entity> constraints;


    public Matcher(final ArrayList<Entity> aEntity,
                   final ArrayList<Entity> bEntity,
                   final ArrayList<Entity> constraints){
        this.aEntity = aEntity;
        this.bEntity = bEntity;
        this.constraints = constraints;
    }

    /**
     * Check if tow entities are eligible for the matching process.
     * @param a,b Entities to be checked.
     * @return true if the entities are eligible, and false otherwise.
     */
    public boolean checkEligibility(final Entity a, final Entity b){

        final HashMap<String, ArrayList<String>> aHashMap = a.getAttributes();
        final HashMap<String, ArrayList<String>> bHashMap = b.getAttributes();

        //TODO modify constraint.get(0)
        for (HashMap.Entry<String, ArrayList<String>> constraint : this.constraints.get(0).getAttributes().entrySet()) {
            //Sanity checks.
            if(!aHashMap.containsKey(constraint.getKey()) || !bHashMap.containsKey(constraint.getKey())){
                System.out.println("[ERROR] The key " + constraint.getKey() + " does not appear in both entities.");
                return false;
            }
            if(!this.evaluateConstraint(aHashMap.get(constraint.getKey()),
                    bHashMap.get(constraint.getKey()),
                    constraint)){
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates if two given values respect the constraint.
     * @param aValue value of type A.
     * @param bValue value of type B.
     * @param constraint the constraint.
     * @return
     */
    public boolean evaluateConstraint(final ArrayList<String> aValue,
                                      final ArrayList<String> bValue,
                                      final HashMap.Entry<String, ArrayList<String>> constraint) {

        // Iterates through all the list, in case the constraint is more complex
        // e.g. min1, max3
        for(int i = 0; i < constraint.getValue().size(); ++i) {
            // All the elements in aValue must be different from the
            // elements in bValue
            if(constraint.getValue().get(i).equals(Constants.DIFF)) {
                // If the two lists are not entirely different, then the constraint is not respected
                if(!this.checkDiff(aValue, bValue)) {
                    System.out.println("[REASON]:" + "<" + constraint.getKey() + ": " + constraint.getValue().get(i)
                            + "> " +  aValue +  " vs. " + bValue);
                    return false;
                }
            }
            if(constraint.getValue().get(i).startsWith(Constants.MIN)) {
                // Extract the number that follows the string MIN
                final int count = Integer.parseInt(constraint.getValue().get(i).substring(Constants.MIN.length()));

                // The number of attributes in common must be greater than or equal to count
                if(this.computeAttributesInCommon(aValue, bValue) < count) {
                    System.out.println("[REASON]:" + "<" + constraint.getKey() + ": " + constraint.getValue().get(i)
                            + "> " +  "[" + this.computeAttributesInCommon(aValue, bValue) +  " vs. " + count + "]");
                    return false;
                }
            }
            if(constraint.getValue().get(i).startsWith(Constants.MAX)) {
                final int count = Integer.parseInt(constraint.getValue().get(i).substring(Constants.MAX.length()));

                // The number of attributes in common must be lower than or equal to count
                if(this.computeAttributesInCommon(aValue, bValue) > count) {
                    System.out.println("[REASON]:" + "<" + constraint.getKey() + ": " + constraint.getValue().get(i)
                            + "> " + "[" + this.computeAttributesInCommon(aValue, bValue) +  " vs. " + count + "]");
                    return false;
                }
            }
            if(constraint.getValue().get(i).startsWith(Constants.GMT_MAX)) {
                final int count = Integer.parseInt(constraint.getValue().get(i).substring(Constants.GMT_MAX.length()));

                // Extract both timezones and compare them
                final int aTimeZone = Integer.parseInt(aValue.get(0).substring(Constants.GMT.length()));
                final int bTimeZone = Integer.parseInt(bValue.get(0).substring(Constants.GMT.length()));

                // If the difference between the two timezones is bigger than count,
                // then the constraint is not respected.
                if(Math.abs(aTimeZone - bTimeZone) > count) {
                    System.out.println("[REASON]:" + constraint.getValue().get(i) + "["
                            + aValue.get(0) + " vs. " + bValue + "]");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if two given lists contain entirely different elements.
     * E.g. for (1,2,3) and (1,2,4) the result must be false.
     * E.g. for (1,2,3) and (4,5,6) the result must be true.
     * @param aValue the first list
     * @param bValue the second list
     * @return true if the lists are entirely different, false otherwise.
     */
    public boolean checkDiff(final ArrayList<String> aValue, final ArrayList<String> bValue) {

        int noOfDifferences = 0;

        for(int i = 0; i < aValue.size(); ++i) {
            if(!bValue.contains(aValue.get(i))) {
                ++noOfDifferences;
            }
        }

        if(noOfDifferences == aValue.size()) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the number of attributes in common.
     * @param aValue the first list.
     * @param bValue the second list.
     * @return the number of attributes in common.
     */
    public int computeAttributesInCommon(final ArrayList<String> aValue, final ArrayList<String> bValue) {

        int noOfCommonAttributes = 0;

        for(int i = 0; i < aValue.size(); ++i) {
            if(bValue.contains(aValue.get(i))) {
                ++noOfCommonAttributes;
            }
        }
        return noOfCommonAttributes;
    }
}
