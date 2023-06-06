import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class NetworkTest {
    int index=1;
    @Test
    public void testAddNode() {
        Network network = new Network(2);
        index=1;
        Person p1 = new Programmer("Maria Neacsu", "08-09-1990", 32, true,index++);
        Programmer p2 = new Programmer("Cristian Ilie", "12-12-2000", 22, false,index++);
        network.addNode(p1);
        network.addNode(p2);
        List<Node> nodes = network.getNodes();
        assertEquals(2, nodes.size());
        assertEquals(p1, nodes.get(0));
        assertEquals(p2, nodes.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeDuplicateName() {
        index=1;
        Network network = new Network(2);
        Person p1 = new Programmer("Maria Neacsu", "08-09-1990", 32, true,index++);
        Programmer p2 = new Programmer("Maria Neacsu", "12-12-2000", 22, false,index++);
        network.addNode(p1);
        network.addNode(p2);
    }

    @Test
    public void testPrintNodes() {
        index=1;
        Programmer p1 = new Programmer("Maria Neacsu", "08-09-1990", 32, true,index++);
        Programmer p2 = new Programmer("Cristian Ilie", "12-12-2000", 22, false,index++);
        Designer p3 = new Designer("Dragos Mihai", "29-03-2001", 21, 32,index++);
        Designer p4 = new Designer("Marina Crihana", "09-09-2002", 20, 3,index++);
        Company c1 = new Company("Google", index++);
        Company c2 = new Company("Continental", index++);
        Company c3 = new Company("Amazon", index);
        Network n1 = new Network(index);
        p1.addRelationship(p2, "FRIEND");n1.addEdge(p1.index,p2.index);
        p1.addRelationship(p4, "FRIEND");n1.addEdge(p1.index,p4.index);
        p1.addRelationship(c3, "EMPLOYEE");n1.addEdge(p1.index,c3.index);
        p2.addRelationship(p3, "FRIEND");n1.addEdge(p2.index,p3.index);
        p3.addRelationship(p4, "FAMILY");n1.addEdge(p3.index,p4.index);
        p1.addRelationship(c1, "EMPLOYEE");n1.addEdge(p1.index,c1.index);
        c2.addEmployee(p3, "WEB DESIGNER");n1.addEdge(c2.index,p3.index);
        c1.addEmployee(p1, "DEV OPS");
        n1.addNode(p1);
        n1.addNode(p2);
        n1.addNode(p3);
        n1.addNode(p4);
        n1.addNode(c1);
        n1.addNode(c2);
        n1.addNode(c3);
        // Since node2 has the most connections, it should be printed first
        String expectedOutput = "Maria Neacsu - connections: 4\n" +
                "Dragos Mihai - connections: 3\n" +
                "Cristian Ilie - connections: 2\n" +
                "Marina Crihana - connections: 2\n" +
                "Google - connections: 1\n" +
                "Continental - connections: 1\n" +
                "Amazon - connections: 0\n";


        n1.printNodes();

    }

    @Test
    public void testAP() {
        Network network = new Network(7);
        network.addEdge(1, 2);
        network.addEdge(1, 4);
        network.addEdge(1, 3);
        network.addEdge(1, 5);
        network.addEdge(1, 7);
        network.addEdge(2, 3);
        network.addEdge(2, 1);
        network.addEdge(3, 4);
        network.addEdge(3, 6);
        network.AP();
    }

    @Test
    public void testMostConnectionsComparator() {
        index=1;
        Programmer p1 = new Programmer("Maria Neacsu", "08-09-1990", 32, true,index++);
        Programmer p2 = new Programmer("Cristian Ilie", "12-12-2000", 22, false,index++);
        Designer p3 = new Designer("Dragos Mihai", "29-03-2001", 21, 32,index++);
        Designer p4 = new Designer("Marina Crihana", "09-09-2002", 20, 3,index++);
        Company c1 = new Company("Google", index++);
        Company c2 = new Company("Continental", index++);
        Company c3 = new Company("Amazon", index);
        Network n1 = new Network(index);
        p1.addRelationship(p2, "FRIEND");n1.addEdge(p1.index,p2.index);
        p1.addRelationship(p4, "FRIEND");n1.addEdge(p1.index,p4.index);
        p1.addRelationship(c3, "EMPLOYEE");n1.addEdge(p1.index,c3.index);
        p2.addRelationship(p3, "FRIEND");n1.addEdge(p2.index,p3.index);
        p3.addRelationship(p4, "FAMILY");n1.addEdge(p3.index,p4.index);
        p1.addRelationship(c1, "EMPLOYEE");n1.addEdge(p1.index,c1.index);
        c2.addEmployee(p3, "WEB DESIGNER");n1.addEdge(c2.index,p3.index);
        c1.addEmployee(p1, "DEV OPS");
        n1.addNode(p1);
        n1.addNode(p2);
        n1.addNode(p3);
        n1.addNode(p4);
        n1.addNode(c1);
        n1.addNode(c2);
        n1.addNode(c3);
        n1.printNodes();
        List<Node> nodes = n1.getNodes();
        Collections.sort(nodes, n1.mostConnectionsComparator);
        assertEquals(p1, nodes.get(0));
        assertEquals(p3, nodes.get(1));
        assertEquals(p2, nodes.get(2));
        assertEquals(p4, nodes.get(3));
    }
}