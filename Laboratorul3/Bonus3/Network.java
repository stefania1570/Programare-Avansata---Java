import java.util.*;


public class Network {
    private int numberOfVertices;
    // Array  of lists for Adjacency List Representation
    private List<ArrayList<Integer>> adj = new ArrayList<>();
    int time = 0;
    static final int NO_PARENT = -1;
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

    public List<Node> getNodes() {
        return nodes;
    }

    public Network(int nr) {
        nodes = new ArrayList<>();
        numberOfVertices=nr+1;
        // Initialize the adj list with empty lists for each vertex
        for (int i = 1; i < numberOfVertices+1; i++) {
            adj.add(new ArrayList<Integer>());
        }
    }

    /**
     * Adds a note into the network
     * The names of the nodes must be unique!
     *
     * @param node of type Node
     */
    public void addNode(Node node) {
        //check if the name of the node we are about to insert already exists
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

    public void addEdge(int u, int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private void APUtil(int u, boolean visited[], int disc[], int low[], int parent, boolean isAP[])
    {
        // Count of children in DFS Tree
        int children = 0;

        // Mark the current node as visited
        visited[u] = true;

        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;

        // Go through all vertices adjacent to this
        for (Integer v : adj.get(u)) {
            // If v is not visited yet, then make it a child of u in DFS tree
            if (!visited[v]) {
                children++;
                APUtil( v, visited, disc, low, u, isAP);

                // Check if the subtree rooted with v has a connection to one of the ancestors of u
                low[u] = Math.min(low[u], low[v]);

                // If u is not root and low value of one of its child is more than discovery value of u.
                if (parent != -1 && low[v] >= disc[u])
                    isAP[u] = true;
            }

            // Update low value of u for parent function calls.
            else if (v != parent)
                low[u] = Math.min(low[u], disc[v]);
        }

        // If u is root of DFS tree and has two or more children.
        if (parent == -1 && children > 1)
            isAP[u] = true;
    }

    public void AP()
    {
        boolean[] visited = new boolean[numberOfVertices];
        int[] disc = new int[numberOfVertices];
        int[] low = new int[numberOfVertices];
        boolean[] isAP = new boolean[numberOfVertices];
        int time = 0, par = -1;

        // Adding this loop so that the code works even if we are given a disconnected graph
        for (int u = 0; u < numberOfVertices; u++)
            if (!visited[u])
                APUtil( u, visited, disc, low, par, isAP);

        for (int u = 0; u < numberOfVertices; u++)
            if (isAP[u])
                System.out.print(u + " ");
        System.out.println();
    }

}
