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
public class MatchEntities {

    /**
     * File with the constraints definition.
     */
    private static final String CONSTRAINTS_FILE_NAME = "./resources/Constraints.xml";

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
    private HashMap<String, String> constraints;

    //TODO delete this constructor. For test purpose only
    public MatchEntities(){
        this.constraints = new HashMap<>();
    }

    public MatchEntities(ArrayList<Entity> aEntity, ArrayList<Entity> bEntity){
        this.aEntity = aEntity;
        this.bEntity = bEntity;
        this.constraints = new HashMap<>();
    }

    /**
     * Populate the constraints HashMap with the data from file.
     */
    public void readConstraints() {
        try {
            File inputFile = new File(CONSTRAINTS_FILE_NAME);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Get a list of all elements in the document
            NodeList nList = doc.getElementsByTagName(Constants.ALL);
            for (int i = 1; i < nList.getLength(); ++i) {

                // Get element
                Element element = (Element)nList.item(i);
                System.out.println(element.getNodeName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void readMentee(){
//        try {
//            File inputFile = new File("./resources/Mentee.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(inputFile);
//            doc.getDocumentElement().normalize();
//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//            NodeList nList = doc.getElementsByTagName("mentee");
//            System.out.println("----------------------------");
//            for (int temp = 0; temp < nList.getLength(); temp++) {
//                Node nNode = nList.item(temp);
//                System.out.println("\nCurrent Element :"
//                        + nNode.getNodeName());
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) nNode;
//

//                    System.out.println("Name : "
//                            + eElement
//                            .getElementsByTagName("name")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Language : "
//                            + eElement
//                            .getElementsByTagName("language")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("ProgrammingLanguage : "
//                            + eElement
//                            .getElementsByTagName("programmingLanguage")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Country : "
//                            + eElement
//                            .getElementsByTagName("country")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("TimeZone : "
//                            + eElement
//                            .getElementsByTagName("timeZone")
//                            .item(0)
//                            .getTextContent());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void printConstraints(){
        for(Map.Entry<String, String> entry : this.constraints.entrySet()){
            System.out.println(entry.getKey() + Constants.SPACE + Constants.COLON + Constants.SPACE + entry.getValue());
        }
        System.out.println();
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
