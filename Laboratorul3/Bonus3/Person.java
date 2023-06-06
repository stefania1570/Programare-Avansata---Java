import java.util.HashMap;
import java.util.Map;

public class Person implements Node, Comparable<Person> {
    private String name;
    private int age;
    int index;
    private String birthDate;
    private Map<Node, String> relationships;

    Person() {

    }

    /**
     * Constructor for Persons
     *
     * @param name      The person's name (SHOULD BE UNIQUE AMONG BOTH PEOPLE AND COMPANIES!)
     * @param birthDate String
     * @param age       The age of the person (int)
     */
    Person(String name, String birthDate, int age, int index) {
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
        this.relationships = new HashMap<>();
        this.index=index;
    }

    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Adds a relationship between a person and another node from the network
     *
     * @param node         of type Node
     * @param relationship String that determines the relationship between the person and the other node
     */
    public void addRelationship(Node node, String relationship) {
        relationships.put(node, relationship);
        //If node1 is in relationship with node2, then node2 is also in relationship with node1
        if (node instanceof Person && ((Person) node).getRelationships().get(this) == null)
            ((Person) node).addRelationship(this,relationship);
    }

    /**
     * Getter for the relationships map for this person node
     * @return map
     */
    public Map<Node, String> getRelationships() {
        return relationships;
    }

    /**
     * Getter for name
     *
     * @return name String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Setter for name in case it changes
     *
     * @param name string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of connections of a person node
     *
     * @return the number of key-value pairs in the map (int)
     */
    public int getNumberOfConnections() {
        return relationships.size();
    }

    /**
     * Getter for birthdate
     *
     * @return birthdate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Overridden method that compares the names of 2 people
     *
     * @param other the object to be compared.
     * @return 0 if the names are equal, < 0 if the string is lexicographically less than the other string
     * > 0 if the string is lexicographically greater than the other string (more characters)
     */
    @Override
    public int compareTo(Person other) {
        if (this.name.isEmpty() || other.getName().isEmpty()) {
            System.err.println("One of the names is an empty string.");
        }
        return this.name.compareTo(other.getName());
    }

    /**
     * Overridden method that returns the person's data
     *
     * @return string that contains the name,age and list of relationships
     */
    @Override
    public String nodeInfo() {
        StringBuilder aux = new StringBuilder();
        for (Map.Entry<Node, String> e : relationships.entrySet()) {
            aux.append(this.name);
            aux.append(" + ");
            aux.append(e.getKey().getName());
            aux.append(" = ");
            aux.append(e.getValue());
            aux.append("\n");
        }
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", relationships=" + aux +
                '}';
    }
}
