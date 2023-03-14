public class Designer extends Person {
    private int numberOfOriginalDesigns;

    Designer() {
        numberOfOriginalDesigns = 0;
    }

    /**
     * Constructor for designers
     *
     * @param name                    String
     * @param birthDate               String
     * @param age                     int
     * @param numberOfOriginalDesigns int
     */
    public Designer(String name, String birthDate, int age, int numberOfOriginalDesigns) {
        super(name, birthDate, age);
        this.numberOfOriginalDesigns = numberOfOriginalDesigns;
    }

    /**
     * Getter for the number of original designs
     *
     * @return number of original designs integer
     */
    public int getNumberOfOriginalDesigns() {
        return numberOfOriginalDesigns;
    }

    /**
     * Setter for the number of original designs
     *
     * @param numberOfOriginalDesigns int
     */
    public void setNumberOfOriginalDesigns(int numberOfOriginalDesigns) {
        this.numberOfOriginalDesigns = numberOfOriginalDesigns;
    }
}
