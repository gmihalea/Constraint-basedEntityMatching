import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Matcher class.
 */
public class Matcher {

    /**
     * List of entities of type A.
     */
    private ArrayList<Entity> aEntity;

    /**
     * List of entities of type B.
     */
    private ArrayList<Entity> bEntity;

    /**
     * List of constraints entities.
     */
    private ArrayList<Entity> constraints;


    public Matcher(final ArrayList<Entity> aEntity,
                   final ArrayList<Entity> bEntity,
                   final ArrayList<Entity> constraints){
        this.aEntity = aEntity;
        this.bEntity = bEntity;
        this.constraints = constraints;
    }

    /**
     * Check if tow entities are eligible for the matching process
     * @param a,b Entities to check
     * @return true if the entities are eligible, and false otherwise
     */
    public boolean checkEligibility(final Entity a, final Entity b){
        //TODO implementation
        //TODO read the constraints from file and compare the keys
        //TODO start with the entities who have only one element into the HashMap lists

        return false;
    }
}
