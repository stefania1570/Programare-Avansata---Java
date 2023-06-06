public class Homework3 {
    public static void main(String[] args) {
        int index = 1;
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

        System.out.println("Articulation points in the network graph are ");
        n1.AP();
    }
}
