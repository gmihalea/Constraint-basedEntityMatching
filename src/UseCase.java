import java.util.ArrayList;

/**
 * The UseCase class
 */
public class UseCase {

    public static void main(String[] args){

        //Parse all the CSV files.
        final ArrayList<Entity> mentees = Parser.parseCSV(Constants.CSV_MENTEES);
        final ArrayList<Entity> mentors = Parser.parseCSV(Constants.CSV_MENTORS);
        final ArrayList<Entity> constraints = Parser.parseCSV(Constants.CSV_CONSTRAINTS);

//        System.out.println(constraints.size());
//        Parser.printHashMap(constraints.get(0).getAttributes());

        // Sanity checks
        if(!Checker.checkAllEntitiesCorrectness(mentees)
                && !Checker.checkAllEntitiesCorrectness(mentors)
                && !Checker.checkAllEntitiesCorrectness(constraints))
            return;

        Matcher matcher = new Matcher(mentees, mentors, constraints);
    }
}
