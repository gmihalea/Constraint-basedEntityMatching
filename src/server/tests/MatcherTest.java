package server.tests;

import server.core.Constraint;
import org.junit.Test;

import server.parsers.CSVParser;
import server.util.Checker;
import server.util.Constants;
import server.core.Entity;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test for server.core.Matcher class.
 */
public class MatcherTest {

    //Parse all the CSV actions.
    final ArrayList<Entity> mentees = (ArrayList<Entity>) CSVParser.parseCSV(Constants.CSV_MENTEES, Constants.ENTITY);
    final ArrayList<Entity> mentors = (ArrayList<Entity>) CSVParser.parseCSV(Constants.CSV_MENTORS, Constants.ENTITY);
    final ArrayList<Constraint> constraints = (ArrayList<Constraint>) CSVParser.parseCSV(Constants.CSV_CONSTRAINTS,
                                                                                         Constants.CONSTRAINT);

    //final Matcher matcher = new Matcher(mentees, mentors, constraints);

    @Test
    public void checkMenteesCorrectness() throws IOException {
        assertTrue(Checker.checkAllEntitiesCorrectness(mentees));
    }

    @Test
    public void checkMentorsCorrectness() throws IOException {
        assertTrue(Checker.checkAllEntitiesCorrectness(mentors));
    }

    @Test
    public void checkConstraintsCorrectness() throws IOException {
        assertTrue(Checker.checkAllEntitiesCorrectness(constraints));
    }
}
