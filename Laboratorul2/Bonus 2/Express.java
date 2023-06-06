public class Express extends Road {
    Express() {

    }

    /**
     * Constructor pentru initializarea unui drum de tip Express (muchie in graf)
     *
     * @param a             Locatia(Nodul) a
     * @param b             Locatia(Nodul) b
     * @param speedLimit    Limita de viteza a drumului
     * @param bidirectional true/false daca e drum bidirectional
     */
    Express(Location a, Location b, int speedLimit, boolean bidirectional,String name) {
        super(a, b, bidirectional,name);
        this.type = "Express";
        if (speedLimit < 0)
            throw new IllegalArgumentException("The speed limit can not be a negative number.");
        else if (speedLimit > 121 || speedLimit <= 90)
            throw new IllegalArgumentException("Express roads have a speed limit of more than 91 km/h and less than 120 km/h.");
        this.speedLimit = speedLimit;
    }

    /**
     * Formeaza un String care contine toate datele unui drum de tip Express
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Express{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", a=" + a.getName() +
                ", b=" + b.getName() +
                '}';
    }
}
