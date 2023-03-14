public class Homework3 {
    public static void main(String[] args) {
        Network n1 = new Network();
        Programmer p1 = new Programmer("Maria Neacsu", "08-09-1990", 32, true);
        Programmer p2 = new Programmer("Cristian Ilie", "12-12-2000", 22, false);
        Designer p3 = new Designer("Dragos Mihai", "29-03-2001", 21, 32);
        Designer p4 = new Designer("Marina Crihana", "09-09-2002", 20, 3);
        Company c1 = new Company("Google");
        Company c2 = new Company("Continental");
        Company c3 = new Company("Amazon");
        p1.addRelationship(p2, "FRIEND");
        p1.addRelationship(p4, "FRIEND");
        p1.addRelationship(c3, "EMPLOYEE");
        p2.addRelationship(p3, "FRIEND");
        p3.addRelationship(p4, "FAMILY");
        p1.addRelationship(c1, "EMPLOYEE");
        c2.addEmployee(p3, "WEB DESIGNER");
        n1.addNode(p1);
        n1.addNode(p2);
        n1.addNode(p3);
        n1.addNode(p4);
        n1.addNode(c1);
        n1.addNode(c2);
        n1.addNode(c3);
        n1.printNodes();


    }
}
