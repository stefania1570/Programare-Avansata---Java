public class Road {
    private int length;
    private int speedLimit;
    private RoadType type;

    /**
     * Constructor pentru initializarea unui drum
     * @param length
     * @param speedLimit
     * @param type
     */
    Road(int length, int speedLimit,RoadType type){
        this.length=length;
        this.speedLimit=speedLimit;
        this.type=type;
    }

    /**
     * Returneaza lungimea drumului
     * @return length in km
     */
    public int getLength() {
        return length;
    }

    /**
     * Returneaza limita de viteza
     * @return speedLimit in km/h
     */
    public int getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Returneaza tipul drumului
     * @return type
     */
    public RoadType getType() {
        return type;
    }

    /**
     * Seteaza lungimea drumului (in caz ca se schimba)
     * @param length in km
     */
    public void setLength(int length) {
        this.length = length;
    }
    /**
     * Seteaza limita de viteza a drumului (in caz ca se schimba)
     * @param speedLimit in km/h
     */
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * Formeaza un String care contine toate datele unei locatii
     * @return String
     */
    @Override
    public String toString(){
        return ("Lungime drum: " + this.getLength() + "\n" +
                "Limita de viteza: " + this.getSpeedLimit() + "\n"+
                "Tip drum:" + this.getType() );
    }
}
