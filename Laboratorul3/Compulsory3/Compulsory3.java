import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Compulsory3 {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        Person p1 = new Person("Maria Neacsu");
        Person p2 = new Person("Cristian Ilie");
        Person p3 = new Person("Dragos Mihai");
        Person p4 = new Person("Marina Crihana");
        Company c1 = new Company("Google");
        Company c2 = new Company("Continental");
        Company c3 = new Company("Amazon");
        c1.addEmployee(p1);
        c1.addEmployee(p2);
        c2.addEmployee(p3);
        c3.addEmployee(p4);
        p1.addFriend(p2);
        p1.addFriend(p4);
        p2.addFriend(p3);
        p2.addFriend(p4);
        nodes.add(p1);
        nodes.add(p2);
        nodes.add(p3);
        nodes.add(p4);
        nodes.add(c1);
        nodes.add(c2);
        nodes.add(c3);

        //Lambda expression for comparing 2 nodes
        nodes.sort((node1, node2) -> node1.getName().compareTo(node2.getName()));
        for (Node node : nodes) {
            System.out.println(node.nodeInfo()+"\n");
        }
    }
}
