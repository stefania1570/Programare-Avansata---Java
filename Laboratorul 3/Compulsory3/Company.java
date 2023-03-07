import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Company implements Node, Comparable<Company> {
    private String name;
    private List<Person> employees;

    Company() {

    }

    /**
     * Constructor that initializes the name of the company and instantiates the employees list
     *
     * @param name String
     */
    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    /**
     * Method that adds an employee to the list of employees of a company
     *
     * @param employee Person
     */
    public void addEmployee(Person employee) {
        //DACA E ANGAJAT LA 2 COMPANII? DACA E NULL
        this.employees.add(employee);
        employee.setCompany(this);
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
    public List<Person> getEmployees() {
        return employees;
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
        StringBuilder employeeNames = new StringBuilder("[");
        for (Person p : employees) {
            employeeNames.append(p.getName());
            //Ca sa nu puna virgula si la ultimul nume
            if (employees.indexOf(p) != employees.size() - 1)
                employeeNames.append(", ");
        }
        employeeNames.append("]");
        return "Company{" +
                "name='" + this.name + '\'' +
                ", employees=" + employeeNames +
                '}';
    }
}
