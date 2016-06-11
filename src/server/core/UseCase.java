package server.core;

import server.parsers.CSVParser;
import server.statistics.Statistics;
import server.util.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The UseCase class
 */
public class UseCase {

    public static void main(String[] args) throws IOException {

        //Parse all the CSV files.
         ArrayList<Entity> mentees = (ArrayList<Entity>) CSVParser.parseCSV(Constants.CSV_MENTEES, Constants.ENTITY);
         ArrayList<Entity> mentors = (ArrayList<Entity>) CSVParser.parseCSV(Constants.CSV_MENTORS, Constants.ENTITY);
         ArrayList<Constraint> constraints = (ArrayList<Constraint>) CSVParser.parseCSV(Constants.CSV_CONSTRAINTS,
                Constants.CONSTRAINT);

        // Sanity checks
        if (!Checker.checkAllEntitiesCorrectness(mentees)
                && !Checker.checkAllEntitiesCorrectness(mentors)
                && !Checker.checkAllEntitiesCorrectness(constraints))
            return;

        //Matcher matcher = new Matcher(mentors, mentees, constraints);
        //matcher.match(Constants.PROGRAMMING_LANGUAGES_CRITERIA, 2);
        //System.out.println(Statistics.getPercentage("Country", "Poland", mentees));

        System.out.println(Statistics.collectAllValues("Languages", mentees));
    }
}
