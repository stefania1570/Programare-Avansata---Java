import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Network {
    /**
     * Comparator that compares 2 nodes by the number of connections in descending order
     */
    Comparator<Node> mostConnectionsComparator = new Comparator<Node>() {

        public int compare(Node n1, Node n2) {
            int numberOfConnections1 = n1.getNumberOfConnections();
            int numberOfConnections2 = n2.getNumberOfConnections();
            //Descending order
            return numberOfConnections2 - numberOfConnections1;

        }
    };
    private List<Node> nodes;

    public Network() {
        nodes = new ArrayList<>();
    }

    /**
     * Adds a note into the network
     * The names of the nodes must be unique!
     *
     * @param node of type Node
     */
    public void addNode(Node node) {
        //First, we check if the name of the node we are about to insert already exists
        for (Node n : nodes)
            if (n.getName().equals(node.getName())) {
                throw new IllegalArgumentException("This name already exists!");
            }
        nodes.add(node);
    }

    /**
     * Method that displays the nodes and their number of connections on the screen, in a reverse order
     */
    public void printNodes() {
        Collections.sort(nodes, mostConnectionsComparator);
        for (Node node : nodes) {
            System.out.println(node.getName() + " - connections: " + node.getNumberOfConnections());
        }
    }
}
