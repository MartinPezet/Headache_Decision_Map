package DecisionMap;

public class DecisionNode {

    /*** Definitions ***/
    int nodeID;
    int yesID;
    int noID;
    String description;
    String question;
    DecisionNode yesNode;
    DecisionNode noNode; // ------- Add more node links here -------

    DecisionNode linkedNode;



    /*** Constructor ***/
    public DecisionNode() {}



    /*** Gets and Sets ***/
    public DecisionNode getLinkedNode() {return linkedNode;}
    public void setLinkedNode(DecisionNode linkedNode) {this.linkedNode = linkedNode;}

    public int getNodeID() {return nodeID;}
    public void setNodeID(int nodeID) {this.nodeID = nodeID;}

    public int getYesID() {return yesID;}
    public void setYesID(int yesID) {this.yesID = yesID; }

    public int getNoID() {return noID;}
    public void setNoID(int noID) {this.noID = noID;}// ------- Add more node links here -------

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description; }

    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}

    public DecisionNode getYesNode() {return yesNode;}
    public void setYesNode(DecisionNode yesNode) {this.yesNode = yesNode;}

    public DecisionNode getNoNode() {return noNode;}
    public void setNoNode(DecisionNode noNode) {this.noNode = noNode;}

    //To Strings
    public String getData() {
        return ("nodeID=" + getNodeID() + ", description=" + getDescription() + ", question=" + getQuestion());
    }

    @Override
    public String toString() {
        return "nodeID=" + nodeID +
                ", yesID=" + yesID +
                ", noID=" + noID +
                ", description='" + description + '\'' +
                ", question='" + question;
    }
}

