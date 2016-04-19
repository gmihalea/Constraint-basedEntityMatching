package core;

import parsers.CSVParser;
import util.*;

import java.util.ArrayList;

/**
 * The UseCase class
 */
public class UseCase {

    public static void main(String[] args){

        //Parse all the CSV files.
        final ArrayList<Entity> mentees = CSVParser.parseCSV(Constants.CSV_MENTEES);
        final ArrayList<Entity> mentors = CSVParser.parseCSV(Constants.CSV_MENTORS);
        final ArrayList<Entity> constraints = CSVParser.parseCSV(Constants.CSV_CONSTRAINTS);

        // Sanity checks
        if (!Checker.checkAllEntitiesCorrectness(mentees)
                && !Checker.checkAllEntitiesCorrectness(mentors)
                && !Checker.checkAllEntitiesCorrectness(constraints))
            return;

        Matcher matcher = new Matcher(mentees, mentors, constraints);

        for (int i = 0; i < mentees.size(); ++i) {
            System.out.println(" --- Mentee " + i);
            mentees.get(i).printAttributes();
            for (int j = 0; j < mentors.size(); ++j) {
                System.out.println(" --- Mentor " + j);
                mentors.get(j).printAttributes();
                System.out.println("--->>  Final result: [" + i + "][" + j + "]: "
                        + matcher.checkEligibility(mentees.get(i), mentors.get(j)));
                System.out.println();
            }
        }
    }
}
