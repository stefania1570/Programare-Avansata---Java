public class Road {
    private final int length;
    private final int speedLimit;
    private final RoadType type;
    Road(int length, int speedLimit,RoadType type){
        this.length=length;
        this.speedLimit=speedLimit;
        this.type=type;
    }

    public int getLength() {
        return length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public RoadType getType() {
        return type;
    }
    public String toString(){
        return ("Lungime drum: " + this.getLength() + "\n" +
                "Limita de viteza: " + this.getSpeedLimit() + "\n"+
                "Tip drum:" + this.getType() );
    }
}
