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
    private HashMap<String, List<String>> attributes;


    public Entity(){
        this.attributes = new HashMap<String, List<String>>();
    }

    public Entity(HashMap<String, List<String>> attributes){
        this.attributes = attributes;
    }

    /**
     *
     * @return the HashMap of attributes
     */
    protected HashMap getAttributes(){
        return this.attributes;
    }

    /**
     * Print the HashMap of attributes.
     */
    protected void printAttributes(){
        for(Map.Entry<String, List<String>> entry : this.attributes.entrySet()){
            System.out.print(entry.getKey() + Constants.SPACE + Constants.COLON + Constants.SPACE);
            for(int i = 0; i < entry.getValue().size(); ++i){
                System.out.print(entry.getValue().get(i) + Constants.COMMA + Constants.SPACE);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void readAttributes(){

    }
}
