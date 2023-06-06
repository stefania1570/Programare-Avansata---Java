import java.text.DecimalFormat;
import java.util.Arrays;

public class Problem {
    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    double[][] adjMatrix;
    Location[] locations;
    Road[] roads;
    private int numberOfLocations;
    private int numberOfRoads;

    Problem() {

    }

    /**
     * Constructor ce initializeaza o instanta a problemei "Best Route Problem"
     *
     * @param locations         Array de locatii (noduri)
     * @param roads             Array de drumuri (muchii)
     * @param numberOfLocations Numarul de locatii din array
     * @param numberOfRoads     Numarul de drumuri din array
     */
    Problem(Location[] locations, Road[] roads, int numberOfLocations, int numberOfRoads) {
        this.locations = new Location[numberOfLocations];
        this.roads = new Road[numberOfRoads];
        this.numberOfLocations = numberOfLocations;
        this.numberOfRoads = numberOfRoads;
        System.arraycopy(locations, 0, this.locations, 0, numberOfLocations);
        System.arraycopy(roads, 0, this.roads, 0, numberOfRoads);

        this.adjMatrix = new double[numberOfLocations][numberOfLocations];
        buildAdjacencyMatrix(this.roads);

    }

    public void setAdjMatrix(double[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }

    public Road[] getRoads() {
        return roads;
    }

    public void setRoads(Road[] roads) {
        this.roads = roads;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public void setNumberOfLocations(int numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public int getNumberOfRoads() {
        return numberOfRoads;
    }

    public void setNumberOfRoads(int numberOfRoads) {
        this.numberOfRoads = numberOfRoads;
    }

    /**
     * Afiseaza matricea de adiacenta
     *
     * @param adjMat Matricea de adiacenta
     */
    public static void printAdjacencyMatrix(double[][] adjMat) {
        //Pentru fiecare rand
        for (double[] row : adjMat)
            //Converteste fiecare rand ca string si apoi afiseaza pe ecran
            System.out.println(Arrays.toString(row));
    }

    /**
     * Adauga muchii (drumuri) in graful ce modeleaza instanta problemei
     *
     * @param a    Locatia a - cea de la care pornim
     * @param b    Locatia b - cea la care trebuie sa ajungem
     * @param road Drumul care leaga cele doua locatii
     */
    public void addEdge(Location a, Location b, Road road) {
        String aux = decfor.format(road.getLength());
        // Adauga muchia cu costul (lungimea) corespunzator
        this.adjMatrix[a.getIndex()][b.getIndex()] = Double.parseDouble(aux);
        // Adauga muchia inapoi pentru drumurile marcate ca bidirectional
        if (road.isBidirectional())
            this.adjMatrix[b.getIndex()][a.getIndex()] = Double.parseDouble(aux);
    }

    /**
     * Construieste matricea de adiacenta
     *
     * @param roads Array de drumuri
     */
    public void buildAdjacencyMatrix(Road[] roads) {
        for (Road road : roads) {
            addEdge(road.a, road.b, road);
        }
    }

    /**
     * Verifica daca instanta problemei este valida
     *
     * @param pb Problema
     * @return true daca este valida, false daca nu este valida
     */
    public boolean isValid(Problem pb) {
        //Verificare daca numele este null
        for (int iterator = 0; iterator < pb.numberOfLocations; iterator++)
            if (pb.locations[iterator].getName().isEmpty()) {
                System.err.println("NUMELE UNEI LOCATII NU POATE FI SIRUL VID!");
                System.exit(-1);
            }
        //Verificare daca avem drumuri de 2 ori
        for (int iterator = 0; iterator < pb.numberOfRoads - 1; iterator++)
            for (int iterator2 = iterator + 1; iterator2 < pb.numberOfRoads; iterator2++)
                if (pb.roads[iterator].getName().equals(pb.roads[iterator2].getName())) {
                    System.err.println("NU PUTEM AVEA DRUMURI CU ACELASI NUME!");
                    System.exit(-1);
                }
        //Verificare daca avem locatii cu acelasi nume sau adresa
        for (int iterator = 0; iterator < pb.numberOfLocations - 1; iterator++)
            for (int iterator2 = iterator + 1; iterator2 < pb.numberOfLocations; iterator2++)
                if (pb.locations[iterator].equals(pb.locations[iterator2])) {
                    System.err.println("NU PUTEM AVEA LOCATII CU ACELASI NUME SAU ADRESA!");
                    System.exit(-1);
                }
        //Verificare daca fiecare drum are lungimea >0
        for (int iterator = 0; iterator < pb.numberOfRoads; iterator++)
            if (pb.roads[iterator].getLength() <= 0) {
                System.err.println("LUNGIMEA UNUI DRUM NU POATE FI UN NUMAR NEGATIV SAU 0!");
                System.exit(-1);
            }
        return true;
    }

    /**
     * Returneaza matricea de adiacenta
     *
     * @return adjMatrix
     */
    public double[][] getAdjMatrix() {
        return adjMatrix;
    }

    /**
     * Verifica recursiv daca exista drum de la nodul a la nodul b
     *
     * @param a Locatia de la care plecam
     * @param b Locatia la care vrem sa ajungem
     */
    public boolean pathExists(Location a, Location b) {
        int startIndex = a.getIndex();
        int endIndex = b.getIndex();
        if (startIndex == -1 || endIndex == -1) {
            return false;
        }
        boolean[] visited = new boolean[locations.length];
        return DFS(startIndex, endIndex, visited);
    }

    /**
     * Parcurgere DFS recursiva
     *
     * @param startNode Nodul de la care incepem parcurgerea
     * @param endNode   Nodul la care ne oprim
     * @param visited   Array care marcheaza nodurile vizitate pana in momentul respectiv
     */
    private boolean DFS(int startNode, int endNode, boolean[] visited) {
        if (startNode == endNode) {
            return true;
        }
        visited[startNode] = true;
        for (int i = 0; i < locations.length; i++) {
            //Daca un nod e adiacent cu nodul curent si nu a fost deja vizitat se face DFS recursiv in continuare pana se ajunge la nodul final
            //Si astfel aflam daca avem un drum de la startNode la endNode
            if (adjMatrix[startNode][i] != 0 && !visited[i]) {
                if (DFS(i, endNode, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Road getRoad(int ux, int vx) {
        for (Road road : roads){
            if((road.getA().getIndex()==ux && road.getB().getIndex()==vx)){
                return road;
            }
        }
        return null;
    }
}
