public class Programmer extends Person{
    private boolean finishedUniversity;
    Programmer(){

    }
    /**
     * Constructor for Programmers
     *
     * @param name The person's name (SHOULD BE UNIQUE AMONG BOTH PEOPLE AND COMPANIES!)
     * @param birthDate the date of birth
     * @param age The age of the person (int)
     * @param finishedUniversity true if the programmer finished university, false otherwise
     */
    Programmer(String name, String birthDate, int age, boolean finishedUniversity){
        super(name,birthDate,age);
        this.finishedUniversity=finishedUniversity;
    }

    /**
     * Finds out if a programmer finished university
     * @return true/false
     */
    public boolean isFinishedUniversity() {
        return finishedUniversity;
    }

    /**
     * Changes if a programmer finished university
     * @param finishedUniversity true/false
     */
    public void setFinishedUniversity(boolean finishedUniversity) {
        this.finishedUniversity = finishedUniversity;
    }
}
