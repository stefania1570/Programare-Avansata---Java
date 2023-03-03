public class Road {
    private int length;
    private int speedLimit;
    private RoadType type;
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
    @Override //!!!
    public String toString(){
        return ("Lungime drum: " + this.getLength() + "\n" +
                "Limita de viteza: " + this.getSpeedLimit() + "\n"+
                "Tip drum:" + this.getType() );
    }
}
