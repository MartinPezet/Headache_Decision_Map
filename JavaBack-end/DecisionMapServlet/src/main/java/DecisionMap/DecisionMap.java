package DecisionMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.*;

public class DecisionMap {

    /***
     * Definitions
     * ***/
    DecisionNode head;
    DecisionNode tail;



    /***
     * Constructor
     * ***/
    public DecisionMap() throws FileNotFoundException {
        Scanner inFile = connectDataSet("C:/Users/mpeze/IdeaProjects/DecisionMapServlet/src/main/java/DecisionMap/DecisionMap.csv");
        buildUnorderedList(inFile);
        buildOrderedMap();
        //unorderedMap = null;
    }

    public DecisionMap(String str) throws FileNotFoundException {
        Scanner inFile = connectDataSet("C:/Users/mpeze/IdeaProjects/DecisionMapServlet/src/main/java/DecisionMap/DecisionMap.csv");
        buildUnorderedList(inFile);
        if(str.equals("unordered")) {
            return;
        } else {
            buildOrderedMap();
        }
    }



    /***
     *
     * Public Methods
     *
     * ***/

    // Appends a new node to the list
    private void append(DecisionNode newNode) {

        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            this.tail.setLinkedNode(null);

            return;
        }

        this.tail.setLinkedNode(newNode);
        this.tail = newNode;
    }

    //File connection
    public Scanner connectDataSet(String pathName) throws FileNotFoundException {
        File prc = new File(pathName);
        return new Scanner(prc);
    }

    //Imports all data from csv file
    public void buildUnorderedList(Scanner dataSet) {

        dataSet.useDelimiter(",");
        DecisionNode node ;
        do {
            String line = dataSet.nextLine();
            node = buildNode(line);
            append(node);
        } while (dataSet.hasNext());
        dataSet.close();

    }

    public DecisionNode fetchNodeByID(int ID){ return nodeFetch(ID); }

    /***
     *
     * Private Functions
     *
     * ***/

    //Links the yes and no NodeIDs to their nodes
    private void buildOrderedMap() {

        if (head == null) {return;}

        DecisionNode nodeLinker = head;

        while (nodeLinker != null) {

            int yesID = nodeLinker.getYesID();// ------- Add more node links here -------
            int noID = nodeLinker.getNoID();

            DecisionNode yesNode = nodeFetch(yesID);
            DecisionNode noNode = nodeFetch(noID);

            nodeLinker.setYesNode(yesNode);
            nodeLinker.setNoNode(noNode);

            nodeLinker = nodeLinker.getLinkedNode();

        }

        cleanup();

    }

    //Cleans up links
    private void cleanup(){
        if (head == null) {return;}

        DecisionNode currentNode = head;
        DecisionNode nextNode = head.getLinkedNode();

        while (nextNode != null) {

            currentNode.setLinkedNode(null);

            currentNode = nextNode;
            nextNode = currentNode.getLinkedNode();
        }
    }

    //Builds the node from the csv
    private DecisionNode buildNode(String line) {
        String[] stringArray = line.split(",");
        DecisionNode n = new DecisionNode();

        n.setNodeID(valueOf(stringArray[0]));
        n.setYesID(valueOf(stringArray[1]));
        n.setNoID(valueOf(stringArray[2]));// ------- Add more node links here -------

        n.setDescription(stringArray[3]);
        n.setQuestion(stringArray[4]);

        return n;
    }



    /***
     * Helper Functions
     * ***/

    //Head = entry point
    public DecisionNode entryPoint() {
        return head;
    }

    // Searches list for the nodeID
    private DecisionNode nodeFetch(int nodeID) {

        DecisionNode nodeLinker = head;

        while (nodeLinker != null) {
            if(nodeLinker.getNodeID() == nodeID){ break ;}
            nodeLinker = nodeLinker.getLinkedNode();
        }

        return nodeLinker;
    }

    // Checks if head = null
    private boolean isEmpty() {
        return this.head == null;
    }
}