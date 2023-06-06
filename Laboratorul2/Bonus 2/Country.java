public class Country extends Road {
    Country() {

    }

    /**
     * Constructor pentru initializarea unui drum de tip Country (muchie in graf)
     *
     * @param a             Locatia(Nodul) a
     * @param b             Locatia(Nodul) b
     * @param speedLimit    Limita de viteza a drumului
     * @param bidirectional true/false daca e drum bidirectional
     */
    Country(Location a, Location b, int speedLimit, boolean bidirectional,String name) {
        super(a, b, bidirectional,name);
        this.type = "Country";
        if (speedLimit < 0)
            throw new IllegalArgumentException("The speed limit can not be a negative number.");
        else if (speedLimit > 90)
            throw new IllegalArgumentException("Country roads have a speed limit of less than 90 km/h.");
        this.speedLimit = speedLimit;
    }

    /**
     * Formeaza un String care contine toate datele unui drum de tip Country
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Country{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", a=" + a.getName() +
                ", b=" + b.getName() +
                '}';
    }
}
