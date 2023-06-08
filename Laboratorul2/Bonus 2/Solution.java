import java.util.*;

public class Solution {
    static int totalVertex = 0; //DEFAULT
    private Problem pb;

    public Solution(Problem pb) {
        totalVertex = pb.getNumberOfLocations();
        this.pb = pb;
    }

    private int minimumDistance(double distance[], Boolean spSet[]) {
        // Initialize min value
        double m = Double.MAX_VALUE;
        int m_index = -1;

        for (int vx = 0; vx < totalVertex; vx++) {
            if (spSet[vx] == false && distance[vx] <= m) {
                m = distance[vx];
                m_index = vx;
            }
        }
        return m_index; //min distance vertex

    }

    private void printSolution(double distance[], int n, Map<Integer, List<Road>> path) {
        for (Location location : pb.getLocations()) {
            int j = location.getIndex(); // Get the index of the location
            if (distance[j] == Double.MAX_VALUE || distance[j] == 0.0) {
                continue;
            }
            System.out.println("To " + location.getName() + " the shortest distance is: " + distance[j]);
            System.out.println("The path is: ");
            List<Road> shortestPath = path.get(j);
            for (Road road : shortestPath) {
                if (road != null && !Objects.equals(road.getA().getName(), location.getName()))
                    System.out.print(road.getA().getName() + " -> ");
            }
            System.out.println(location.getName() + "\n");
        }
    }

    public void dijkstra(double graph[][], int s) {
        // The output array distance[i] holds the shortest distance from source s to j
        double distance[] = new double[totalVertex];

        // spSet[j] will be true if vertex j is included in the shortest path
        Boolean spSet[] = new Boolean[totalVertex];
        Map<Integer, List<Road>> path = new HashMap<>();

        // Initializing all the distances as INFINITE and spSet[] as false
        for (int j = 0; j < totalVertex; j++) {
            distance[j] = Double.MAX_VALUE;
            spSet[j] = false;
        }
        distance[s] = 0; // Distance from the source vertex to itself is always 0

        // compute the shortest path for all the given vertices
        for (int cnt = 0; cnt < totalVertex - 1; cnt++) {
            // Update the distance between neighbouring vertex and source vertex
            int ux = minimumDistance(distance, spSet);
            // the chosen vertex is marked as true (processed)
            spSet[ux] = true;
            // Update all the neighbouring vertex distances
            for (int vx = 0; vx < totalVertex; vx++)
                if (!spSet[vx] && graph[ux][vx] != 0.0 && distance[ux] != Double.MAX_VALUE && distance[ux] + graph[ux][vx] < distance[vx]) {
                    distance[vx] = distance[ux] + graph[ux][vx];
                    // Update the path
                    List<Road> roadPath = path.get(ux);
                    if (roadPath == null) {
                        roadPath = new ArrayList<>();
                    }
                    roadPath.add(pb.getRoad(ux, vx));
                    path.put(vx, roadPath);
                }

        }

        // display the build distance array
        printSolution(distance, totalVertex, path);

    }
}
