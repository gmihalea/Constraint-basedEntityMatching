package tests;

import core.Matcher;
import org.junit.Test;

import parsers.CSVParser;
import util.Checker;
import util.Constants;
import core.Entity;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test for core.Matcher class.
 */
public class MatcherTest {

    //Parse all the CSV files.
    final ArrayList<Entity> mentees = CSVParser.parseCSV(Constants.CSV_MENTEES);
    final ArrayList<Entity> mentors = CSVParser.parseCSV(Constants.CSV_MENTORS);
    final ArrayList<Entity> constraints = CSVParser.parseCSV(Constants.CSV_CONSTRAINTS);

    //final Matcher matcher = new Matcher(mentees, mentors, constraints);

    @Test
    public void checkMenteesCorrectness() {
        assertTrue(Checker.checkAllEntitiesCorrectness(mentees));
    }

    @Test
    public void checkMentorsCorrectness() {
        assertTrue(Checker.checkAllEntitiesCorrectness(mentors));
    }

//    @Test
//    public void checkConstraintsCorrectness() {
//        assertTrue(Checker.checkAllEntitiesCorrectness(constraints));
//    }
}
