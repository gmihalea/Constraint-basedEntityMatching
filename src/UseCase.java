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

        if(!Checker.checkAllEntities(mentees)
            && !Checker.checkAllEntities(mentors)
            && !Checker.checkAllEntities(constraints)) {
            return;
        } else {
            Matcher matcher = new Matcher(mentees, mentors, constraints);
        }


    }
}
