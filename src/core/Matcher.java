package core;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    public HashMap<Entity, Entity> match(String criteria) throws IOException {
        final HashMap<Entity, Entity> matching = new HashMap<>();

        for(Entity entity : this.aEntity) {
            Entity matchingEntity = this.pickTheMatchingEntity(entity, this.generatesCandidates(entity, this.bEntity), criteria);
            if(matchingEntity != null) {
                matching.put(entity, matchingEntity);
                this.bEntity.remove(matchingEntity);
            }
        }
        System.out.println("Cupluri: " + matching.size());
        return matching;
    }

    /**
     * Phase Two of matching.
     * In this phase, the entities are filtered by the soft attributes.
     * @param aTypeEntity entity to match.
     * @param candidates  the list of candidates.
     * @param criteria    specifies the sorting criteria for the list of candidates.
     * @return the match entity.
     */
    private Entity pickTheMatchingEntity(final Entity aTypeEntity,
                                        final ArrayList<Entity> candidates,
                                        final String criteria) {
        ArrayList<Entity> sortedListOfCandidates;

        switch (criteria) {
            // If the criteria is Score, then the list of candidates should be sorted descending.
            case Constants.SCORE_CRITERIA:
                sortedListOfCandidates = (ArrayList<Entity>) Sorter.sortListByCriteria(candidates,
                        Constants.DESCENDING_SORT, criteria);
                break;
            // If the criteria is ProgrammingLanguages, then the list of candidates should be sorted ascending.
            case Constants.PROGRAMMING_LANGUAGES_CRITERIA:
                sortedListOfCandidates = (ArrayList<Entity>) Sorter.sortListByCriteria(candidates,
                        Constants.ASCENDING_SORT, criteria);
                break;
            // If the criteria does not match any of the above possibilities, then it is wrong.
            default:
                System.out.println("[ERROR]: Wrong parameter <criteria> in pickTheMatchingEntity().");
                return null;
        }

        if(candidates.size() == 0) {
            System.out.println("[NO candidates]");
            return null;
        }

        // If the number of candidates 1, then the final result is the first element of the list
        if (candidates.size() == 1) {
            return candidates.get(0);
        }

        return this.evaluateSoftConstraint(aTypeEntity, sortedListOfCandidates);
    }

    /**
     * Chooses a candidate for the given aTypeEntity based on the soft constraints.
     * @param aTypeEntity entity to match.
     * @param candidates  the list of candidates.
     * @return the Entity that matches.
     */
    private Entity evaluateSoftConstraint(final Entity aTypeEntity,
                                         final ArrayList<Entity> candidates) {
        Map<String, ArrayList<String>> softConstraints = this.getSoftConstraintsByPriority();
        ArrayList<Entity> shortList = candidates;
        boolean found = false;

        for (final Map.Entry<String, ArrayList<String>> entry : softConstraints.entrySet()) {
            if(!found) {
                final String originalConstraint = entry.getValue().get(Constants.CONSTRAINTS_INDEX);

                final String typeOfConstraint = originalConstraint.substring(0, Constants.CONSTRAINTS_CODE_SIZE);
                final String pieceOfConstraint = originalConstraint.substring(Constants.CONSTRAINTS_CODE_SIZE
                        + Constants.CONSTRAINT_PRIORITY_SIZE, originalConstraint.length());

                if (Constants.SOFT_CONSTRAINT.equals(typeOfConstraint)) {
                    switch (pieceOfConstraint) {
                        case Constants.GMT_MIN_CONSTRAINT:
                            shortList = this.getMinimumTimeZoneEntities(aTypeEntity, candidates);
                            break;
                        // In case there are candidates who have the same time zone difference
                        // If the aTypeEntity has multiple choices for ProgrammingLevel (e.g all of them: Beginner
                        // Medium Confident) and it also has the maximum DedicatedTime (more than 7 or 10 h),
                        // then it should be matched with a Beginner
                        case Constants.MORE_WITH_BEGINNER_CONSTRAINT:
                            if (aTypeEntity.getAttributes().get(Constants.PROGRAMMING_LEVEL_ATTRIBUTE)
                                    .contains(Constants.PROGRAMMING_LEVEL_BEGINNER)
                                    && (Constants.MORE_THAN_SEVEN_HOURS.equals(aTypeEntity.getAttributes()
                                    .get(Constants.DEDICATED_TIME_ATTRIBUTE))
                                    || Constants.MORE_THAN_TEN_HOURS.equals(aTypeEntity.getAttributes()
                                    .get(Constants.DEDICATED_TIME_ATTRIBUTE)))) {
                                // In the short list remain only those entities that contain Beginner as
                                // ProgrammingLevel attribute
                                shortList = this.extractBeginners(shortList);
                            }
                            break;
                        case Constants.BIGGER_SCORE_CONSTRAINT:
                            shortList = this.getEntitiesWithMaxScore(shortList);
                            break;
                    }
                    // If the list contains only one element, then the match was found
                    if(shortList.size() == 1)
                        found = true;
                }
            }
        }
        System.out.println("Mentorul: ");
        aTypeEntity.printAttributes();
        System.out.println("Mentee-ul cu care face match: ");
        shortList.get(0).printAttributes();
        if(shortList.size() > 1)
            System.out.println("MERGEAU MAI MULTI !!!");
        return shortList.get(0);
    }

    /**
     * Phase One of generating matching candidates for given Entity.
     * In this phase, the entities are filtered by the mandatory attributes.
     * @param aTypeEntity entity to match.
     * @param bTypeEntity list of entities to search in.
     * @return the list of candidates
     * @throws IOException
     */
    private ArrayList<Entity> generatesCandidates(final Entity aTypeEntity, final ArrayList<Entity> bTypeEntity) throws IOException {
        final ArrayList<Entity> candidates = new ArrayList<>();

        //System.out.println("Candidates: ");
        // Iterates through all bTypeEntities and check eligibility
        for (int i = 0; i < bTypeEntity.size(); ++i) {
            if (checkEligibility(aTypeEntity, bTypeEntity.get(i))) {
                candidates.add(bTypeEntity.get(i));
                //bTypeEntity.get(i).printAttributes();
            }
        }
        return candidates;
    }

    /**
     * Check if tow entities are eligible for the matching process evaluating the hard constraints.
     * @param a,b Entities to be checked.
     * @return true if the entities are eligible, and false otherwise.
     */
    private boolean checkEligibility(final Entity a, final Entity b) throws IOException {

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
    private boolean evaluateHardConstraint(final ArrayList<String> aTypeValue,
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
    private boolean checkDiff(final ArrayList<String> aTypeValue, final ArrayList<String> bTypeValue) {
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
    private int computeAttributesInCommon(final ArrayList<String> aTypeValue, final ArrayList<String> bTypeValue) {
        int noOfCommonAttributes = 0;

        for (int i = 0; i < aTypeValue.size(); ++i) {
            if (CustomContains.containsCaseInsensitive(aTypeValue.get(i), bTypeValue)) {
                ++noOfCommonAttributes;
            }
        }
        return noOfCommonAttributes;
    }

    /**
     * Returns the map including only the soft constraints.
     * @return the list of soft constraints.
     */
    private Map<String, ArrayList<String>> getSoftConstraintsByPriority() {
        final Set<Map.Entry<String,ArrayList<String>>> softs = new HashSet<>();

        // Removes all the hard constraints
        for (final Map.Entry<String, ArrayList<String>> entry : this.constraints.getAttributes().entrySet()) {
            final String value = entry.getValue().get(Constants.CONSTRAINTS_INDEX);
            if(value.startsWith(Constants.SOFT_CONSTRAINT)) {
                softs.add(entry);
            }
        }

        List<Map.Entry<String,ArrayList<String>>> list = new LinkedList<>(softs);

        // Sort the soft constraints based on their priority.
        Collections.sort(list, (e1, e2) -> {
            final int priority1 = Integer.parseInt(e1.getValue().get(Constants.CONSTRAINTS_INDEX)
                    .substring(Constants.CONSTRAINTS_CODE_SIZE + 1, Constants.CONSTRAINTS_CODE_SIZE + 2));
            final int priority2 = Integer.parseInt(e2.getValue().get(Constants.CONSTRAINTS_INDEX)
                    .substring(Constants.CONSTRAINTS_CODE_SIZE + 1, Constants.CONSTRAINTS_CODE_SIZE + 2));

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
     * Returns the list of entities that have the closest TimeZone to the target.
     * @param aTypeEntity target entity.
     * @param candidates list of candidates entities
     * @return the entities that have the minimum distance to the target.
     */
    private ArrayList<Entity> getMinimumTimeZoneEntities(final Entity aTypeEntity, final ArrayList<Entity> candidates) {
        return this.getEntitiesWithMinTimeZoneDiff(aTypeEntity, candidates);
    }

    /**
     * Computes the minimum time zone difference
     * @param aTypeEntity target entity
     * @param candidates list of candidates
     * @return the minimum difference
     */
    private int getMinimumTimeZoneDiff(final Entity aTypeEntity, final ArrayList<Entity> candidates) {
        final String aTypeEntityTimeZone = aTypeEntity.getAttributes().get(Constants.TIME_ZONE_CONSTRAINT)
                .get(Constants.CONSTRAINTS_INDEX);
        final int aTypeEntityDelay = Integer.parseInt(aTypeEntityTimeZone
                .substring(Constants.GMT.length(), aTypeEntityTimeZone.length()));

        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i < candidates.size(); ++i) {
            String candidateTimeZone = candidates.get(i).getAttributes().get(Constants.TIME_ZONE_CONSTRAINT)
                    .get(Constants.CONSTRAINTS_INDEX);
            int candidateDelay = Integer.parseInt(candidateTimeZone
                    .substring(Constants.GMT.length(), candidateTimeZone.length()));

            if (Math.abs(candidateDelay - aTypeEntityDelay) < minDiff) {
                minDiff = Math.abs(candidateDelay - aTypeEntityDelay);
            }
        }
        return minDiff;
    }

    /**
     * Computes the list of candidates that have the closest time zone with the aTypeEntity
     * @param aTypeEntity the target entity
     * @param candidates list of candidates
     * @return the list of the closest candidates.
     */
    private ArrayList<Entity> getEntitiesWithMinTimeZoneDiff(final Entity aTypeEntity,
                                                            final ArrayList<Entity> candidates) {
        ArrayList<Entity> shortListOfCandidates = new ArrayList<>();

        final String aTypeEntityTimeZone = aTypeEntity.getAttributes().get(Constants.TIME_ZONE_CONSTRAINT)
                .get(Constants.CONSTRAINTS_INDEX);
        final int aTypeEntityDelay = Integer.parseInt(aTypeEntityTimeZone
                .substring(Constants.GMT.length(), aTypeEntityTimeZone.length()));

        for(int i = 0; i < candidates.size(); ++i) {
            String candidateTimeZone = candidates.get(i).getAttributes().get(Constants.TIME_ZONE_CONSTRAINT)
                    .get(Constants.CONSTRAINTS_INDEX);
            int candidateDelay = Integer.parseInt(candidateTimeZone
                    .substring(Constants.GMT.length(), candidateTimeZone.length()));

            if (Math.abs(candidateDelay - aTypeEntityDelay) == this.getMinimumTimeZoneDiff(aTypeEntity, candidates)) {
                shortListOfCandidates.add(candidates.get(i));
            }
        }
        return shortListOfCandidates;
    }

    /**
     * Computes the list of beginners from the list of candidates
     * @param candidates list of candidates
     * @return the list of beginners
     */
    private ArrayList<Entity> extractBeginners(final ArrayList<Entity> candidates) {
        final ArrayList<Entity> beginners = candidates.stream().filter(entity -> entity.getAttributes()
                .get(Constants.PROGRAMMING_LEVEL_ATTRIBUTE).contains(Constants.PROGRAMMING_LEVEL_BEGINNER))
                .collect(Collectors.toCollection(ArrayList::new));

        return beginners;
    }

    private ArrayList<Entity> getEntitiesWithMaxScore(final ArrayList<Entity> candidates) {
        final ArrayList<Entity> entities = new ArrayList<>();
        final double maxScore = this.getMaxScore(candidates);

        entities.addAll(candidates.stream().filter(entity -> Double.parseDouble(entity.getAttributes()
                .get(Constants.SCORE_ATTRIBUTE).get(Constants.CONSTRAINTS_INDEX)) == maxScore)
                .collect(Collectors.toList()));
        return entities;
    }

    /**
     * Computes the maximum score.
     * @param candidates list of candidates
     * @return the maximum score
     */
    private double getMaxScore(final ArrayList<Entity> candidates) {
        double maxScore = -1;
        double temp;

        for(Entity entity : candidates) {
            if((temp = Double.parseDouble(entity.getAttributes().get(Constants.SCORE_ATTRIBUTE)
                    .get(Constants.CONSTRAINTS_INDEX))) > maxScore)
                maxScore = temp;
        }
        return maxScore;
    }
}