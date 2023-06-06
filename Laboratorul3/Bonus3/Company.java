import java.util.HashMap;
import java.util.Map;

public class Company implements Node, Comparable<Company> {
    private String name;
    int index;
    private Map<Person, String> employees;


    Company() {

    }

    /**
     * Constructor that initializes the name of the company and instantiates the employees list
     *
     * @param name String
     */
    public Company(String name, int index) {
        this.name = name;
        this.employees = new HashMap<>();
        this.index=index;
    }

    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Method that adds an employee to the list of employees of a company
     *
     * @param employee Person
     * @param job      The job of the person within the company
     */
    public void addEmployee(Person employee, String job) {
        //If a company hires a person, then the person is in relationship with the company
        employees.put(employee, job);
        employee.addRelationship(this,"EMPLOYED BY");
    }

    /**
     * Getter for the name of the company
     *
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name of the company
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the employee list
     *
     * @return employees list
     */
    public Map<Person, String> getEmployees() {
        return employees;
    }

    /**
     * Getter for the number of connections
     *
     * @return number of connections
     */
    public int getNumberOfConnections() {
        return employees.size();
    }

    /**
     * Overridden method that compares the names of 2 companies
     *
     * @param other the object to be compared.
     * @return 0 if the names are equal, < 0 if the string is lexicographically less than the other string
     * > 0 if the string is lexicographically greater than the other string (more characters)
     */
    @Override
    public int compareTo(Company other) {
        if (this.name.isEmpty() || other.name.isEmpty()) {
            System.err.println("One of the names is an empty string.");
        }
        return this.name.compareTo(other.getName());
    }

    /**
     * Overridden method that returns the company's data
     *
     * @return string that contains the name and list of employees
     */
    @Override
    public String nodeInfo() {
        StringBuilder aux = new StringBuilder();
        for (Map.Entry<Person, String> e : employees.entrySet()) {
            aux.append(this.name);
            aux.append(" + ");
            aux.append(e.getKey().getName());
            aux.append(" = ");
            aux.append(e.getValue());
            aux.append("\n");
        }
        return "Company{" +
                "name='" + name + '\'' +
                ", employees=" + aux +
                '}';
    }
}
