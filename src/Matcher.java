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
 * Created by gmihalea on 3/14/2016.
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
     * HashMap of constraints on which the matching is made.
     */
    private ArrayList<Entity> constraints;


    public Matcher(ArrayList<Entity> aEntity,
                   ArrayList<Entity> bEntity,
                   ArrayList<Entity> constraints){
        this.aEntity = aEntity;
        this.bEntity = bEntity;
        this.constraints = constraints;
    }

    /**
     * Check if tow entities are eligible for the matching process
     * @param a,b Entities to check
     * @return true if the entities are eligible, and false otherwise
     */
    public boolean checkEligibility(Entity a, Entity b){
        //TODO implementation
        //TODO read the constraints from file and compare the keys
        //TODO start with the entities who have only one element into the HashMap lists

        return false;
    }
}
