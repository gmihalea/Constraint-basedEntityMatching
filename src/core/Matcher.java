package core;

import java.io.IOException;
import java.util.*;

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
     * Set of constraints.
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
     * Phase Two of generating matching candidates for given Entity.
     * In this phase, the entities are filtered by the soft attributes.
     *
     * @param aTypeEntity entity to match.
     * @param candidates  the list of candidates.
     * @param criteria    specifies the sorting criteria for the list of candidates.
     * @return the list of matching entities.
     */
    public ArrayList<Entity> generatesCandidatesPhaseTwo(final Entity aTypeEntity,
                                                         final ArrayList<Entity> candidates,
                                                         final String criteria) {
        ArrayList<Entity> sortedListOfCandidates;

        switch (criteria) {
            // If the criteria is Score, then the list of candidates should be sorted descending.
            case Constants.SCORE_CRITERIA:
                sortedListOfCandidates = (ArrayList<Entity>) Sorter.sortListByCriteria(candidates,
                        Constants.DESCENDING_SORT,
                        criteria);
                break;
            // If the criteria is ProgrammingLanguages, then the list of candidates should be sorted ascending.
            case Constants.PROGRAMMING_LANGUAGES_CRITERIA:
                sortedListOfCandidates = (ArrayList<Entity>) Sorter.sortListByCriteria(candidates,
                        Constants.ASCENDING_SORT,
                        criteria);
                break;
            // If the criteria does not match any of the above possibilities, then it is wrong.
            default:
                System.out.println("[ERROR]: Wrong parameter <criteria> in generatesCandidatesPhaseTwo().");
                return null;
        }

        // If the number of candidates is 0 or 1, then the final result is list of candidates itself.
        if (candidates.size() == 0 || candidates.size() == 1) {
            return candidates;
        }


        //[done] TODO take the sorted list of elements of type A (sorted based on Score criteria)
        //[done] TODO check if there is minimum one candidate. If not, then return an empty list + a message
        //[done] TODO check if there are more than one candidate
        //TODO begin applying the soft constraints and reducing the number of candidates
        //TODO pick the
        return null;
    }

    /**
     * Chooses a candidate for the given aTypeEntity based on the soft constraints.
     *
     * @param aTypeEntity
     * @param candidates
     * @return
     */
    public ArrayList<Entity> evaluateSoftConstraint(final Entity aTypeEntity,
                                                    final ArrayList<Entity> candidates) {
        Map<String, ArrayList<String>> softConstraints = this.getSoftConstraintsByPriority();

        for (final Map.Entry<String, ArrayList<String>> entry : softConstraints.entrySet()) {
            final String constraint = entry.getKey();
            // In case the constraint is more complex (e.g. Languages: min1, max 3)
            for (int i = 0; i < entry.getValue().size(); ++i) {
                final String originalConstraint = entry.getValue().get(i);

                final String typeOfConstraint = originalConstraint.substring(0, Constants.CONSTRAINTS_CODE_SIZE);
                final int priority = Integer.parseInt(originalConstraint.substring(Constants.CONSTRAINTS_CODE_SIZE,
                        (Constants.CONSTRAINTS_CODE_SIZE + Constants.CONSTRAINT_PRIORITY_SIZE)));
                final String pieceOfConstraint = originalConstraint.substring(Constants.CONSTRAINTS_CODE_SIZE,
                        originalConstraint.length());
                // Iterates through the candidates
                for (int j = 0; j < candidates.size(); ++j) {

                }
            }
        }
        return null;
    }


    /**
     * Phase One of generating matching candidates for given Entity.
     * In this phase, the entities are filtered by the mandatory attributes.
     *
     * @param aTypeEntity entity to match.
     * @param bTypeEntity list of entities to search in.
     * @return the list of candidates
     * @throws IOException
     */
    public ArrayList<Entity> generatesCandidatesPhaseOne(final Entity aTypeEntity, ArrayList<Entity> bTypeEntity) throws IOException {
        final ArrayList<Entity> candidates = new ArrayList<>();

        System.out.println("Candidates: ");
        // Iterates through all bTypeEntities and check eligibility
        for (int i = 0; i < bTypeEntity.size(); ++i) {
            if (checkEligibility(aTypeEntity, bTypeEntity.get(i))) {
                candidates.add(bTypeEntity.get(i));
                bTypeEntity.get(i).printAttributes();
            }
        }
        return candidates;
    }

    /**
     * Check if tow entities are eligible for the matching process evaluating the hard constraints.
     *
     * @param a,b Entities to be checked.
     * @return true if the entities are eligible, and false otherwise.
     */
    public boolean checkEligibility(final Entity a, final Entity b) throws IOException {

        final HashMap<String, ArrayList<String>> aTypeHashMap = a.getAttributes();
        final HashMap<String, ArrayList<String>> bTypeHashMap = b.getAttributes();

        for (HashMap.Entry<String, ArrayList<String>> constraint : this.constraints.getAttributes().entrySet()) {
            //Sanity checks.
            if (!aTypeHashMap.containsKey(constraint.getKey()) || !bTypeHashMap.containsKey(constraint.getKey())) {
                System.out.println("[ERROR] The key " + constraint.getKey() + " does not appear in both entities.");
                return false;
            }
            if (!this.evaluateHardConstraint(aTypeHashMap.get(constraint.getKey()),
                    bTypeHashMap.get(constraint.getKey()),
                    constraint)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates if two given values respect the hard constraint.
     *
     * @param aTypeValue value of type A.
     * @param bTypeValue value of type B.
     * @param constraint the constraint.
     * @return true if there is a match, false otherwise
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

            if (Constants.HARD_CONSTRAINT.equals(typeOfConstraint)) {
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
     *
     * @param aTypeValue the first list
     * @param bTypeValue the second list
     * @return true if the lists are entirely different, false otherwise.
     */
    public boolean checkDiff(final ArrayList<String> aTypeValue, final ArrayList<String> bTypeValue) {
        int noOfDifferences = 0;

        for (int i = 0; i < aTypeValue.size(); ++i) {
            if (!CustomContains.containsCaseInsensitive(aTypeValue.get(i), bTypeValue)) {
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
     *
     * @param aTypeValue the first list.
     * @param bTypeValue the second list.
     * @return the number of attributes in common.
     */
    public int computeAttributesInCommon(final ArrayList<String> aTypeValue, final ArrayList<String> bTypeValue) {
        int noOfCommonAttributes = 0;

        for (int i = 0; i < aTypeValue.size(); ++i) {
            if (CustomContains.containsCaseInsensitive(aTypeValue.get(i), bTypeValue)) {
                ++noOfCommonAttributes;
            }
        }
        return noOfCommonAttributes;
    }


    public Map<String, ArrayList<String>> getSoftConstraintsByPriority() {
        Set<Map.Entry<String,ArrayList<String>>> constraints = this.constraints.getAttributes().entrySet();

        // Removes all the hard constraints
        for (final Map.Entry<String, ArrayList<String>> entry : this.constraints.getAttributes().entrySet()) {
            final String value = entry.getValue().get(Constants.CONSTRAINTS_INDEX);
            if(value.startsWith(Constants.HARD_CONSTRAINT)) {
                constraints.remove(entry.getKey());
            }
        }

        List<Map.Entry<String,ArrayList<String>>> list = new LinkedList<>(constraints);

        Collections.sort(list, (e1, e2) -> {
            final int priority1 = Integer.parseInt(e1.getValue().get(Constants.CONSTRAINTS_INDEX)
                    .substring(Constants.CONSTRAINTS_CODE_SIZE, Constants.CONSTRAINTS_CODE_SIZE + 1));
            final int priority2 = Integer.parseInt(e2.getValue().get(Constants.CONSTRAINTS_INDEX)
                    .substring(Constants.CONSTRAINTS_CODE_SIZE, Constants.CONSTRAINTS_CODE_SIZE + 1));

            if(priority1 == priority2)
                return 0;
            return priority1 < priority2 ? -1 : 1;
        });

        Map<String, ArrayList<String>> softConstraints = new LinkedHashMap<>();
        for(Map.Entry<String, ArrayList<String>> entry: list) {
            softConstraints.put(entry.getKey(), entry.getValue());
        }
        return softConstraints;
    }

    /**
     * Returns the Entity that has the closest TimeZone to the target.
     * @param aTypeEntity target entity.
     * @param candidates list of candidates entities
     * @return the entity that has the minimum distance to the target.
     */
    public Entity getMinimumTimeZoneEntity(final Entity aTypeEntity, final ArrayList<Entity> candidates) {
        final String aTypeEntityTimeZone = aTypeEntity.getAttributes().get(Constants.TIME_ZONE_CONSTRAINT)
                .get(Constants.CONSTRAINTS_INDEX);
        final int aTypeEntityDelay = Integer.parseInt(aTypeEntityTimeZone
                .substring(Constants.GMT.length(), aTypeEntityTimeZone.length()));

        Entity entity = null;
        int diff = Integer.MAX_VALUE;

        for(int i = 0; i < candidates.size(); ++i) {
            String candidateTimeZone = candidates.get(i).getAttributes().get(Constants.TIME_ZONE_CONSTRAINT)
                    .get(Constants.CONSTRAINTS_INDEX);
            int candidateDelay = Integer.parseInt(candidateTimeZone
                    .substring(Constants.GMT.length(), candidateTimeZone.length()));

            if(Math.abs(candidateDelay - aTypeEntityDelay) < diff) {
                diff = Math.abs(candidateDelay - aTypeEntityDelay);
                entity = candidates.get(i);
            }
        }
        return entity;
    }
}

