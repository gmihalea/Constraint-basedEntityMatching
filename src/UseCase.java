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

        // Sanity checks
        if(!Checker.checkAllEntities(mentees)
                && !Checker.checkAllEntities(mentors)
                && !Checker.checkAllEntities(constraints))
            return;

        Matcher matcher = new Matcher(mentees, mentors, constraints);
    }
}
