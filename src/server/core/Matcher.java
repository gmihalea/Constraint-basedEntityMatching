package server.core;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import server.util.*;

/**
 * server.core.Matcher class.
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
     * The main match method.
     * Iterates through all aEntity list and finds suitable bEntities
     * @param criteria the criteria based on what will be sorted the aEntity
     * @param relation how many bEntity to one aEntity
     * @return the final hashMap containing the match results
     * @throws IOException
     */
    public HashMap<Entity, ArrayList<Entity>> match(final String criteria, final int relation) {
        final HashMap<Entity, ArrayList<Entity>> matching = new HashMap<>();

        //Printer.printInFile("Mentor <-> Mentee/s\n");
        for(Entity entity : this.aEntity) {
            ArrayList<Entity> matchingEntity = this.pickTheMatchingEntity(entity,
                    this.generatesCandidates(entity, this.bEntity), criteria, relation);
            if(matchingEntity != null) {
                matching.put(entity, matchingEntity);
                //Printer.printInFile(entity.getAttributes().get("FirstName") + " "
                        //+ entity.getAttributes().get("LastName") + " <-> ");
                for(Entity e : matchingEntity) {
                    //Printer.printInFile(e.getAttributes().get("FirstName") + " "
                            //+ e.getAttributes().get("LastName") + " , ");
                    this.bEntity.remove(e);
                }
                //Printer.printInFile("\n");
            }
        }
//        Printer.printInFile("----------------------------------------------\n\n");
//        Printer.printInFile("Sorting criteria: " + criteria + "\n");
//        Printer.printInFile("Matching couples: " + matching.size() + "\n");
//        Printer.printInFile("Mentors without a match: " + (this.aEntity.size() - matching.size())
//                + " out of " + this.aEntity.size() +  "\n");
//        Printer.printInFile("Mentees without a match: " + this.bEntity.size()
//                + " out of " + (this.bEntity.size() + matching.size()) +  "\n");

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
    private ArrayList<Entity> pickTheMatchingEntity(final Entity aTypeEntity,
                                                    final ArrayList<Entity> candidates,
                                                    final String criteria,
                                                    final int relation) {
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
            return null;
        }

        return this.evaluateSoftConstraint(aTypeEntity, sortedListOfCandidates, relation);
    }

    /**
     * Chooses a candidate for the given aTypeEntity based on the soft constraints.
     * @param aTypeEntity entity to match.
     * @param candidates  the list of candidates.
     * @return the Entity that matches.
     */
    private ArrayList<Entity> evaluateSoftConstraint(final Entity aTypeEntity,
                                                     final ArrayList<Entity> candidates,
                                                     final int relation) {
        Map<String, ArrayList<String>> softConstraints = this.getSoftConstraintsByPriority();
        ArrayList<Entity> shortList = candidates;
        boolean found = false;

        if(shortList.size() == relation) {
            return shortList;
        }

        for (final Map.Entry<String, ArrayList<String>> entry : softConstraints.entrySet()) {
            if(!found) {

                final String originalConstraint = entry.getValue().get(Constants.CONSTRAINTS_INDEX);

                final String typeOfConstraint = originalConstraint.substring(0, Constants.CONSTRAINTS_CODE_SIZE);
                final String pieceOfConstraint = originalConstraint.substring(Constants.CONSTRAINTS_CODE_SIZE
                        + Constants.CONSTRAINT_PRIORITY_SIZE, originalConstraint.length());

                if (Constants.SOFT_CONSTRAINT.equals(typeOfConstraint)) {
                    switch (pieceOfConstraint) {
                        case Constants.GMT_MIN_CONSTRAINT:
                            shortList = this.getMinimumTimeZoneEntities(aTypeEntity, candidates, relation);
                            break;
                        // In case there are candidates who have the same time zone difference
                        // If the aTypeEntity has multiple choices for ProgrammingLevel (e.g all of them: Beginner
                        // Medium Confident) and it also has the maximum DedicatedTime (more than 7 or 10 h),
                        // then it should be matched with a Beginner
                        case Constants.MORE_DEDICATED_TIME_WITH_BEGINNER_CONSTRAINT:
                            if (aTypeEntity.getAttributes().get(Constants.PROGRAMMING_LEVEL_ATTRIBUTE)
                                    .contains(Constants.PROGRAMMING_LEVEL_BEGINNER)
                                    && (Constants.MORE_THAN_SEVEN_HOURS.equals(aTypeEntity.getAttributes()
                                    .get(Constants.DEDICATED_TIME_ATTRIBUTE).get(Constants.CONSTRAINTS_INDEX))
                                    || Constants.MORE_THAN_TEN_HOURS.equals(aTypeEntity.getAttributes()
                                    .get(Constants.DEDICATED_TIME_ATTRIBUTE).get(Constants.CONSTRAINTS_INDEX)))) {
                                // In the short list remain only those entities that contain Beginner as
                                // ProgrammingLevel attribute
                                shortList = this.extractBeginners(shortList);
                            }
                            break;
                        // In case shortList has more than one item, there will be selected those items that
                        // have the biggest score
                        case Constants.BIGGER_SCORE_CONSTRAINT:
                            shortList = this.getEntitiesWithMaxScore(shortList, relation);
                            break;
                        // In case shortList has more than one item, there will be selected those items that
                        // have the biggest dedicated time
                        case Constants.MORE_DEDICATED_TIME:
                            shortList = this.getEntitiesWithMaximumTime(shortList, relation);
                            break;
                        // In case shortList has more than one item, there will be selected those items that
                        // have the smallest number of desired programming languages
                        case Constants.LESS_PROGRAMMING_LANGUAGES:
                            shortList = this.getEntitiesWithLessProgrammingLanguages(shortList, relation);
                            break;
                    }
                    // If the list contains exactly the required number, then the process should finish
                    if(shortList.size() == relation)
                        found = true;
                }
            }
        }
        // If there still are cases in which shortList includes more than required number of candidates, the last ones
        // would be dropped
        if(relation > shortList.size()) {
            for(int i = relation; i < shortList.size(); ++i) {
                shortList.remove(shortList.get(i));
            }
        }
        return shortList;
    }

    /**
     * Phase One of generating matching candidates for given Entity.
     * In this phase, the entities are filtered by the mandatory attributes.
     * @param aTypeEntity entity to match.
     * @param bTypeEntity list of entities to search in.
     * @return the list of candidates
     */
    private ArrayList<Entity> generatesCandidates(final Entity aTypeEntity, final ArrayList<Entity> bTypeEntity) {
        final ArrayList<Entity> candidates = new ArrayList<>();

        // Iterates through all bTypeEntities and check eligibility
        for (int i = 0; i < bTypeEntity.size(); ++i) {

            if (checkEligibility(aTypeEntity, bTypeEntity.get(i))) {
                candidates.add(bTypeEntity.get(i));
            }
        }
        return candidates;
    }

    /**
     * Check if tow entities are eligible for the matching process evaluating the hard constraints.
     * @param a,b Entities to be checked.
     * @return true if the entities are eligible, and false otherwise.
     */
    private boolean checkEligibility(final Entity a, final Entity b) {

        final HashMap<String, ArrayList<String>> aTypeHashMap = a.getAttributes();
        final HashMap<String, ArrayList<String>> bTypeHashMap = b.getAttributes();

        for (HashMap.Entry<String, ArrayList<String>> constraint : this.constraints.getAttributes().entrySet()) {
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
                                           final HashMap.Entry<String, ArrayList<String>> constraint) {
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
                        return false;
                    }
                }
                if (pieceOfConstraint.startsWith(Constants.MIN)) {
                    // Extract the number that follows the string MIN
                    final int threshold = Integer.parseInt(pieceOfConstraint.substring(Constants.MIN.length()));

                    // The number of attributes in common must be greater than or equal to count
                    if (this.computeAttributesInCommon(aTypeValue, bTypeValue) < threshold) {
                        return false;
                    }
                }
                if (pieceOfConstraint.startsWith(Constants.MAX)) {
                    final int threshold = Integer.parseInt(pieceOfConstraint.substring(Constants.MAX.length()));

                    // The number of attributes in common must be lower than or equal to the threshold
                    if (this.computeAttributesInCommon(aTypeValue, bTypeValue) > threshold) {
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
        final Set<Map.Entry<String,ArrayList<String>>> softCons = new HashSet<>();

        // Removes all the hard constraints
        for (final Map.Entry<String, ArrayList<String>> entry : this.constraints.getAttributes().entrySet()) {
            final String value = entry.getValue().get(Constants.CONSTRAINTS_INDEX);
            if(value.startsWith(Constants.SOFT_CONSTRAINT)) {
                softCons.add(entry);
            }
        }

        List<Map.Entry<String,ArrayList<String>>> list = new LinkedList<>(softCons);

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
    private ArrayList<Entity> getMinimumTimeZoneEntities(final Entity aTypeEntity,
                                                         final ArrayList<Entity> candidates,
                                                         final int relation) {
        final ArrayList<Entity> matches = new ArrayList<>();
        final ArrayList<Entity> remainCandidates = candidates;
        ArrayList<Entity> result;

        for(int i = 0; i < relation; ++i) {
            result = this.getEntitiesWithMinTimeZoneDiff(aTypeEntity, remainCandidates);
            matches.addAll(result);
            remainCandidates.removeAll(result);
        }
        return matches;
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

    /**
     * Computes the list of entities with the max score
     * @param candidates list of candidates
     * @return list of entities with max score
     */
    private ArrayList<Entity> getEntitiesWithMaxScore(final ArrayList<Entity> candidates, final int relation) {
        final ArrayList<Entity> entities = new ArrayList<>();
        final ArrayList<Entity> remainingCandidates = candidates;

        for(int i = 0; i < relation; ++i) {
            entities.addAll(candidates.stream().filter(candidate -> Double.parseDouble(candidate.getAttributes()
                    .get(Constants.SCORE_ATTRIBUTE).get(Constants.CONSTRAINTS_INDEX))
                    == this.getMaxScore(remainingCandidates)).collect(Collectors.toList()));
            remainingCandidates.removeAll(entities);
        }
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

    /**
     * Computes the list of entities with maximum dedicated time
     * @param candidates list of candidates
     * @return list of entities with maximum dedicated time
     */
    private ArrayList<Entity> getEntitiesWithMaximumTime(final ArrayList<Entity> candidates, final int relation) {
        final ArrayList<Entity> remainingCandidates = candidates;
        ArrayList<Entity> entities = new ArrayList<>();

        for(int i = 0; i < relation; ++i) {
            entities.addAll(candidates.stream().filter(entity -> entity.getAttributes()
                    .get(Constants.DEDICATED_TIME_ATTRIBUTE).get(Constants.CONSTRAINTS_INDEX)
                    .equals(this.getMaxDedicatedTime(remainingCandidates)))
                    .collect(Collectors.toCollection(ArrayList::new)));
            remainingCandidates.removeAll(entities);
        }
        return entities;
    }

    /**
     * Computes the maximum dedicated time
     * @param candidates list of candidates
     * @return maximum dedicated time
     */
    private String getMaxDedicatedTime(final ArrayList<Entity> candidates) {
        String maxDedicatedTime = Constants.EMPTY_STRING;
        int maxHours = -1;
        int hours;

        for(Entity entity : candidates) {
            String dedicatedTime = entity.getAttributes().get(Constants.DEDICATED_TIME_ATTRIBUTE)
                    .get(Constants.CONSTRAINTS_INDEX);
            if(dedicatedTime.startsWith(Constants.MORE)) {
                hours = Integer.parseInt(dedicatedTime.substring(1, dedicatedTime.length()));
            } else {
                // The format is int-int (e.g 3-5)
                hours = (Integer.parseInt(dedicatedTime.substring(0, 1))
                        + Integer.parseInt(dedicatedTime.substring(2, 3))) / 2;
            }
            if(hours > maxHours) {
                maxHours = hours;
                maxDedicatedTime = dedicatedTime;
            }
        }
        return maxDedicatedTime;
    }

    /**
     * Computes the list of entities that have minimum number of programming languages
     * @param candidates list of candidates
     * @return list of entities
     */
    private ArrayList<Entity> getEntitiesWithLessProgrammingLanguages(final ArrayList<Entity> candidates,
                                                                      final int relation) {
        ArrayList<Entity> entities = new ArrayList<>();
        final ArrayList<Entity> remainingCandidates = candidates;

        for(int i = 0; i < relation; ++i) {
            entities.addAll(candidates.stream().filter(entity -> entity.getAttributes()
                    .get(Constants.PROGRAMMING_LANGUAGES_ATTRIBUTE).size()
                    == this.getMinimumNumberOfProgrammingLanguages(remainingCandidates))
                    .collect(Collectors.toCollection(ArrayList::new)));
            remainingCandidates.removeAll(entities);
        }
        return entities;
    }

    /**
     * Computes the minimum number of programming languages
     * @param candidates list of candidates
     * @return minimum number
     */
    private int getMinimumNumberOfProgrammingLanguages(final ArrayList<Entity> candidates) {
        int minimumNo = Integer.MAX_VALUE;
        int temp;

        for(Entity entity : candidates) {
            if((temp = entity.getAttributes().get(Constants.PROGRAMMING_LANGUAGES_ATTRIBUTE).size()) < minimumNo)
                minimumNo = temp;
        }
        return minimumNo;
    }
}