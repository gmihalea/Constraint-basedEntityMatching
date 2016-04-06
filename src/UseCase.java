import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gmihalea on 3/14/2016.
 */
public class UseCase {

    public static void main(String[] args){

        ArrayList<HashMap<String, ArrayList<String>>> mentees = Parser.parseCSV(Constants.CSV_MENTEES);
        System.out.println("----------------- MENTEES -------------------");
        Parser.printAllHashMaps(mentees);

        ArrayList<HashMap<String, ArrayList<String>>> mentors = Parser.parseCSV(Constants.CSV_MENTORS);
        System.out.println("----------------- MENTORS -------------------");
        Parser.printAllHashMaps(mentors);

        ArrayList<HashMap<String, ArrayList<String>>> constraints = Parser.parseCSV(Constants.CSV_CONSTRAINTS);
        System.out.println("----------------- CONSTRAINTS -------------------");
        Parser.printAllHashMaps(constraints);
    }
}
