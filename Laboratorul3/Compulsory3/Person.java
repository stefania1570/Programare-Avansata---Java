import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person implements Node, Comparable<Person> {
    private String name;
    private Company company;
    private List<Person> friends;

    Person() {

    }

    /**
     * Constructor for Persons
     *
     * @param name The person's name (SHOULD BE UNIQUE AMONG BOTH PEOPLE AND COMPANIES!)
     */
    Person(String name) {
        //In network verific daca persoanele/companiile au nume unice??
        this.name = name;
        this.company = null;
        this.friends = new ArrayList<Person>();

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
     * Setter for name
     *
     * @param name string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for Company
     *
     * @return company object
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Setter for company
     *
     * @param company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Getter for friends of a person
     *
     * @return friends
     */
    public List<Person> getFriends() {
        return friends;
    }

    /**
     * Setter for friends
     *
     * @param friends list
     */
    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    /**
     * Function that adds a friend to the list of friends for a person
     * If person p1 is a friend of person p2, then person p2 is also a friends of person p1
     *
     * @param person Person
     */
    public void addFriend(Person person) {
        this.friends.add(person);
        person.friends.add(this); //Daca p1 e prieten cu p2, atunci si p2 e prieten cu p1
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
        if (this.name.isEmpty() || other.name.isEmpty()) {
            System.err.println("One of the names is an empty string.");
        }
        return this.name.compareTo(other.name);
    }

    /**
     * Overridden method that returns the person's data
     *
     * @return string that contains the name,company and list of friends
     */
    @Override
    public String nodeInfo() {
        StringBuilder friendNames = new StringBuilder("[");
        for (Person p : friends) {
            friendNames.append(p.getName());
            //Ca sa nu puna virgula si la ultimul nume
            if (friends.indexOf(p) != friends.size() - 1)
                friendNames.append(", ");
        }
        friendNames.append("]");
        return "Person{" +
                "name='" + name + '\'' +
                ", company=" + company.getName() +
                ", friends=" + friendNames +
                '}';
    }
}
