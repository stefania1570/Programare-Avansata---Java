import java.lang.Math;

public abstract class Road {
    protected double length;
    protected int speedLimit;
    protected String type;
    Location a;
    Location b;
    private boolean bidirectional;

    Road() {

    }

    /**
     * Constructor pentru initializarea unui drum (muchie in graf)
     *
     * @param a             Locatia(Nodul) a
     * @param b             Locatia(Nodul) b
     * @param bidirectional true/false daca e drum bidirectional
     */
    Road(Location a, Location b, boolean bidirectional) {
        /**
         * Seteaza lungimea unui drum folosind formula matematica a distantei dintre 2 puncte intr-un plan
         */
        this.setLength(Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2)));
        this.a = a;
        this.b = b;
        this.bidirectional = bidirectional;
    }

    /**
     * Returneaza lungimea drumului
     *
     * @return length in km
     */
    public double getLength() {
        return length;
    }

    /**
     * Seteaza lungimea drumului
     *
     * @param length in km
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Returneaza limita de viteza
     *
     * @return speedLimit in km/h
     */
    public int getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Seteaza limita de viteza a drumului (in caz ca se schimba)
     *
     * @param speedLimit in km/h
     */
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * Returneaza tipul drumului
     *
     * @return type Tipul drumului
     */
    public String getType() {
        return type;
    }

    /**
     * Returneaza locatia de la care am pornit prin drumul respectiv
     *
     * @return locatia a
     */
    public Location getA() {
        return a;
    }

    /**
     * Returneaza locatia la care am ajuns prin drumul respectiv
     *
     * @return locatia b
     */
    public Location getB() {
        return b;
    }

    /**
     * Verifica daca un drum (muchie) este bidirectional
     *
     * @return true daca este bidirectional, false daca nu este bidirectional
     */
    public boolean isBidirectional() {
        return bidirectional;
    }

    /**
     * Verifica daca doua drumuri sunt identice
     *
     * @param obj obiectul cu care il comparam
     * @return true daca sunt identice, false daca nu sunt identice
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Road)) {
            return false;
        }
        Road other = (Road) obj;
        return (a.getName().equals(other.a.getName()) && b.getName().equals(other.b.getName()));
    }

}