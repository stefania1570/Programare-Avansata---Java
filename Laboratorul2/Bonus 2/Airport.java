public class Airport extends Location {
    private int numberOfTerminals;

    Airport() {

    }

    /**
     * Constructor pentru initializarea unui aeroport
     *
     * @param x                 coordonata x a aeroportului(numar real)
     * @param y                 coordonata y a aeroportului(numar real)
     * @param name              numele aeroportului
     * @param numberOfTerminals numarul de terminale ale aeroportului
     */
    Airport(double x, double y, String name, int numberOfTerminals) {
        super(x, y, name);
        this.type = "Airport";
        this.numberOfTerminals = numberOfTerminals;
    }

    /**
     * Returneaza numarul de terminale ale aeroportului
     *
     * @return numberOfTerminals (intreg)
     */
    public int getNumberOfTerminals() {
        return numberOfTerminals;
    }

    /**
     * Seteaza numarul de terminale ale aeroportului
     *
     * @param numberOfTerminals Numarul de terminale (intreg)
     */
    public void setNumberOfTerminals(int numberOfTerminals) {
        this.numberOfTerminals = numberOfTerminals;
    }

    /**
     * Formeaza un String care contine toate datele unui aeroport
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Airport{" +
                "numberOfTerminals=" + numberOfTerminals +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
