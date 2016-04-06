import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gmihalea on 3/14/2016.
 */
public class UseCase {

    public static void main(String[] args){

        ArrayList<HashMap<String, ArrayList<String>>> mentees = Parser.parseCSV("./data/MenteesCSV.csv");
        Parser.printAttributes(mentees);
    }
}
