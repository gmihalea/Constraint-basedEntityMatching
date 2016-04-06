import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gmihalea on 3/14/2016.
 */
public class UseCase {

    public static void main(String[] args){

        ArrayList<Entity> mentees = Parser.parseCSV(Constants.CSV_MENTEES);
        ArrayList<Entity> mentors = Parser.parseCSV(Constants.CSV_MENTORS);
        ArrayList<Entity> constraints = Parser.parseCSV(Constants.CSV_CONSTRAINTS);

        //TODO sanity checks
        Matcher matcher = new Matcher(mentees, mentors, constraints);
    }
}
