public class Token {
    private int number;

    Token() {
        this.number = 0;
    }

    /**
     * Constructor for the token
     * @param number int
     */
    Token(int number) {
        this.number = number;
    }

    /**
     * Getter for the token
     * @return number int
     */
    int getNumber() {
        return this.number;
    }

    /**
     * Overridden toString method
     * @return string
     */
    @Override
    public String toString() {
        return Integer.toString(number);
    }
}