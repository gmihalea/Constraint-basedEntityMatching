package core;

import parsers.CSVParser;
import util.*;

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

        Matcher matcher = new Matcher(mentees, mentors, constraints);

//        for (int i = 0; i < mentees.size(); ++i) {
////            Printer.printInFile("Mentee " + i + ": " + mentees.get(i).getAttributes().get("FirstName") + "\n");
//            //mentees.get(i).printAttributes();
//            for (int j = 0; j < mentors.size(); ++j) {
//                Printer.printInFile("------------------------------------------------------------------------------------\n");
//                Printer.printInFile("Mentee " + i + ": " + mentees.get(i).getAttributes().get("FirstName")
//                                                         + mentees.get(i).getAttributes().get("LastName")
//                                                         + mentees.get(i).getAttributes().get("Languages")
//                                                         + mentees.get(i).getAttributes().get("ProgrammingLanguages")
//                                                         + mentees.get(i).getAttributes().get("ProgrammingLevel")
//                                                         + mentees.get(i).getAttributes().get("Country")+"\n");
//
//                Printer.printInFile("Mentor " + j + ": " + mentors.get(j).getAttributes().get("FirstName")
//                                                         + mentors.get(j).getAttributes().get("LastName")
//                                                         + mentors.get(j).getAttributes().get("Languages")
//                                                         + mentors.get(j).getAttributes().get("ProgrammingLanguages")
//                                                         + mentors.get(j).getAttributes().get("ProgrammingLevel")
//                                                         + mentors.get(j).getAttributes().get("Country")+"\n");
//
//                Printer.printInFile("--->>  Final result: [" + i + "][" + j + "]: "
//                        + matcher.checkEligibility(mentees.get(i), mentors.get(j)) + "\n");
//                Printer.printInFile("-------------------------------------------------------------------------------------\n");
//            }
//        }

        //matcher.generatesCandidatesPhaseOne(mentors.get(1));
//        mentors.get(0).printAttributes();
//        mentees.get(0).printAttributes();
//
//        System.out.println(matcher.computeAttributesInCommon(mentors.get(0).getAttributes().get("ProgrammingLanguages"), mentees.get(0).getAttributes().get("ProgrammingLanguages")));

        //check Sorter class
        mentors = (ArrayList<Entity>) Sorter.sortListByCriteria(mentors, Constants.DESCENDING_SORT, "ProgrammingLanguages");
        for (int i = 0; i < mentors.size(); ++i) {
            Printer.printInFile("------------------------------------------------------------------------------------\n");
            Printer.printInFile("Mentee " + i + ": "
                            + mentors.get(i).getAttributes().get("Score")
                    + mentors.get(i).getAttributes().get("FirstName")
                    + mentors.get(i).getAttributes().get("LastName")
                    + mentors.get(i).getAttributes().get("ProgrammingLanguages"));
        }
    }
}
