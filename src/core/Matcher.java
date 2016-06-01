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
     * Phase One for generating matching candidates for given Entity.
     * In this phase, the entities are filtered by the mandatory attributes
     * @param aTypeEntity entity to match
     */
    public ArrayList<Entity> generatesCandidatesPhaseOne(Entity aTypeEntity) throws IOException {
        ArrayList<Entity> candidates = new ArrayList<>();

        System.out.println("Candidates: ");
        // Iterates through all bTypeEntities and check eligibility
        for(int i = 0; i < this.bEntity.size(); ++i) {
            if(checkEligibility(aTypeEntity, this.bEntity.get(i))) {
                candidates.add(this.bEntity.get(i));
                this.bEntity.get(i).printAttributes();
            }
        }
        return candidates;
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
            if (!this.evaluateHardConstraint(aTypeHashMap.get(constraint.getKey()),
                    bTypeHashMap.get(constraint.getKey()),
                    constraint)){
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates if two given values respect the hard constraint.
     * @param aTypeValue value of type A.
     * @param bTypeValue value of type B.
     * @param constraint the constraint.
     * @return
     */
    public boolean evaluateHardConstraint(final ArrayList<String> aTypeValue,
                                          final ArrayList<String> bTypeValue,
                                          final HashMap.Entry<String, ArrayList<String>> constraint) throws IOException {
        int count = 0;
        // Iterates through all the list, in case the constraint is more complex ( e.g. min1, max3)
        for (int i = 0; i < constraint.getValue().size(); ++i) {
            final String originalConstraint = constraint.getValue().get(i);
            final String typeOfConstraint = originalConstraint.substring(0, Constants.CONSTRAINTS_CODE_SIZE);
            final String pieceOfConstraint = originalConstraint.substring(Constants.CONSTRAINTS_CODE_SIZE,
                                                                    originalConstraint.length());

            if(Constants.HARD_CONSTRAINT.equals(typeOfConstraint)) {
                // All the elements in aTypeValue must be different from the elements in bTypeValue
                if (Constants.DIFF.equals(pieceOfConstraint)) {
                    // If the two lists are not entirely different, then the constraint is not respected
                    if (!this.checkDiff(aTypeValue, bTypeValue)) {
                        Printer.printInFile("[REASON]:" + "<" + constraint.getKey() + ": " + pieceOfConstraint
                                + "> " + aTypeValue + " vs. " + bTypeValue);
                        return false;
                    }
                }
                if (pieceOfConstraint.startsWith(Constants.MIN)) {
                    // Extract the number that follows the string MIN
                    final int threshold = Integer.parseInt(pieceOfConstraint.substring(Constants.MIN.length()));

                    // The number of attributes in common must be greater than or equal to count
                    if ((count = this.computeAttributesInCommon(aTypeValue, bTypeValue)) < threshold) {
                        Printer.printInFile("[REASON]:" + "<" + constraint.getKey() + ": " + pieceOfConstraint
                                + "> " + "[" + count + " vs. " + threshold + "]");
                        return false;
                    }
                }
                if (pieceOfConstraint.startsWith(Constants.MAX)) {
                    final int threshold = Integer.parseInt(pieceOfConstraint.substring(Constants.MAX.length()));

                    // The number of attributes in common must be lower than or equal to the threshold
                    if ((count = this.computeAttributesInCommon(aTypeValue, bTypeValue)) > threshold) {
                        Printer.printInFile("[REASON]:" + "<" + constraint.getKey() + ": " + pieceOfConstraint
                                + "> " + "[" + count + " vs. " + threshold + "]");
                        return false;
                    }
                }
            }
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
