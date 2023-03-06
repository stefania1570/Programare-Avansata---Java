public class Highway extends Road {
    Highway() {

    }

    /**
     * Constructor pentru initializarea unei autostrazi (muchie in graf)
     *
     * @param a             Locatia(Nodul) a
     * @param b             Locatia(Nodul) b
     * @param speedLimit    Limita de viteza a drumului
     * @param bidirectional true/false daca e drum bidirectional
     */
    Highway(Location a, Location b, int speedLimit, boolean bidirectional) {
        super(a, b, bidirectional);
        this.type = "Highway";
        if (speedLimit < 0)
            throw new IllegalArgumentException("The speed limit can not be a negative number.");
        else if (speedLimit > 250 || speedLimit <= 121)
            throw new IllegalArgumentException("Highways have a speed limit of more than 121 km/h and less than 250 km/h.");
        this.speedLimit = speedLimit;
    }

    /**
     * Formeaza un String care contine toate datele unei autostrazi
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Highway{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", a=" + a.getName() +
                ", b=" + b.getName() +
                '}';
    }
}
