import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that incapsulates the attributes of an entity.
 */
public class Entity {

    /**
     * HashMap containing the attributes of the entity
     */
    private HashMap<String, ArrayList<String>> attributes;


    public Entity(final HashMap<String, ArrayList<String>> attributes){
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
