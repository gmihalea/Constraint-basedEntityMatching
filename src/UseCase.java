import java.util.ArrayList;

/**
 * The UseCase class
 */
public class UseCase {

    public static void main(String[] args){

        ArrayList<Entity> mentees = Parser.parseCSV(Constants.CSV_MENTEES);
        ArrayList<Entity> mentors = Parser.parseCSV(Constants.CSV_MENTORS);
        ArrayList<Entity> constraints = Parser.parseCSV(Constants.CSV_CONSTRAINTS);

        // Sanity checks
        if(!Checker.checkAllEntities(mentees)
                && !Checker.checkAllEntities(mentors)
                && !Checker.checkAllEntities(constraints))
            return;

        Matcher matcher = new Matcher(mentees, mentors, constraints);
    }
}
