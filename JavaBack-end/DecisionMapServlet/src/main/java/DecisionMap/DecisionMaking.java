package DecisionMap;

import Exceptions.CustomNullException;

import java.io.FileNotFoundException;

public class DecisionMaking {

    /***
     * Public Methods
     *  ***/
    public DecisionNode StartMap(){
        // Making ordered map
        DecisionMap map = CreateMap();
        DecisionNode node = map.entryPoint();

        // Checking if the map is empty and throwing null
        if (node == null){
            throw new CustomNullException("Node with ID of 1 does not exist");
        } else {
            return node;
        }
    }

    public DecisionNode MapYes(int nodeID) throws CustomNullException {
        // Creating unordered map
        DecisionMap map = CreateUnorderedMap();

        // Checking if the node inputted is null
        if (map.fetchNodeByID(nodeID) == null) {
            throw new CustomNullException("Node with ID of " + nodeID + " does not exist");
        } else {
            // Gets Yes Node
            DecisionNode newNode = map.fetchNodeByID(map.fetchNodeByID(nodeID).getYesID());
            // Checking if the Yes Node is null
            if (newNode == null) {
                throw new CustomNullException("Node with ID of " + nodeID + " does not have a Yes node linked to it.");
            } else {
                return newNode;
            }
        }
    }

    public DecisionNode MapNo(int nodeID) throws CustomNullException{
        // Creating unordered map
        DecisionMap map = CreateUnorderedMap();

        // Checking if the node inputted is null
        if (map.fetchNodeByID(nodeID) == null) {
            throw new CustomNullException("Node with ID of " + nodeID + " does not exist");
        } else {
            //Gets No Node
            DecisionNode newNode = map.fetchNodeByID(map.fetchNodeByID(nodeID).getNoID());
            // Checking if the No Node is null
            if (newNode == null) {
                throw new CustomNullException("Node with ID of " + nodeID + " does not have a No node linked to it.");
            } else {
                return newNode;
            }
        }
    }

    /***
     * Private Methods
     * ***/
    private DecisionMap CreateMap(){
        DecisionMap map;

        // File Not Found Exception
        try {
            map = new DecisionMap();
        } catch(FileNotFoundException fe){
            System.out.println("DecisionMap file not found");
            return null;
        }
        return map;
    }

    private DecisionMap CreateUnorderedMap(){
        DecisionMap map;

        // File Not Found Exception
        try {
            map = new DecisionMap("unordered");
        } catch(FileNotFoundException fe){
            System.out.println("File not found");
            return null;
        }
        return map;
    }
}
