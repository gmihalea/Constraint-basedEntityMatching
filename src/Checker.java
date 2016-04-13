import java.util.ArrayList;
import java.util.Map;

/**
 * Class that makes sanity check for the entities.
 */
public class Checker {

    /**
     * Checks if an entity is correctly defined.
     * @param e the entity to check
     * @return true if the entity is correct, false otherwise
     */
    public static boolean checkEntity(Entity e) {

        if(e.getAttributes().size() == 0){
            System.out.println("[ERROR] The Entity has no attributes.");
            return false;
        }

        for (Map.Entry<String, ArrayList<String>> entry : e.getAttributes().entrySet()) {
            if(entry.getValue().size() == 0) {
                System.out.println("[ERROR] The key " + entry.getKey() + " has no value.");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a list of entities is correctly defined.
     * @param entities the entities to check
     * @return true if the list is correct, false otherwise
     */
    public static boolean checkAllEntities(ArrayList<Entity> entities) {
        for(int i = 0; i < entities.size(); ++i) {
            if(!Checker.checkEntity(entities.get(i))) {
                System.out.println("[ERROR] Entity " + i + " is incorrect.");
                return false;
            }
        }
        return true;
    }
}