package client.util;

import server.core.Constraint;
import server.core.Entity;
import server.util.Constants;
import server.parsers.CSVParser;

import java.util.ArrayList;

/**
 * Checker class
 */
public class Checker {
    public static void check(final String mentorPath, final String menteePath, final String constraintPath) {
        ArrayList<Entity> mentees = (ArrayList<Entity>) CSVParser.parseCSV(menteePath, Constants.ENTITY);
        ArrayList<Entity> mentors = (ArrayList<Entity>) CSVParser.parseCSV(mentorPath, Constants.ENTITY);
        ArrayList<Constraint> constraints = (ArrayList<Constraint>) CSVParser.parseCSV(constraintPath,
            Constants.CONSTRAINT);
    }
}
