import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gmihalea on 3/14/2016.
 */
public class Entity {

    /**
     * HashMap containing the attributes of the entity
     */
    private HashMap<String, ArrayList<String>> attributes;


    public Entity(HashMap<String, ArrayList<String>> attributes){
        this.attributes = attributes;
    }

    /**
     *
     * @return the HashMap of attributes
     */
    protected HashMap<String, ArrayList<String>> getAttributes(){
        return this.attributes;
    }

    /**
     * Print the HashMap of attributes.
     */
    protected void printAttributes(){
        Parser.printHashMap(this.attributes);
    }
}
