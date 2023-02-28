public class Compulsory2 {
    public static void main(String[] args) {
        Location c1 = new Location(20, 20, LocationType.CITY, "Galati");
        Location a1 = new Location(25, 34, LocationType.AIRPORT, "Otopeni");
        Location g1 = new Location(25, 34, LocationType.GAS_STATION, "LukOil");
        Road h1= new Road(256, 150,RoadType.HIGHWAY);
        Road e1= new Road(400, 120,RoadType.EXPRESS);
        Road co1= new Road(340, 70,RoadType.COUNTRY);
        System.out.println(c1.toString());
        System.out.println(a1.toString());
        System.out.println(g1.toString());
        System.out.println(h1.toString());
        System.out.println(e1.toString());
        System.out.println(co1.toString());

    }
}
public class Location {
    private final String name;
    private final int x;
    private final int y;
    private final LocationType type;
    Location(int x,int y,LocationType type, String name){
        this.x=x;
        this.y=y;
        this.type=type;
        this.name = name;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public LocationType getType() {
        return type;
    }
    public String toString(){
        return ("Nume locatie: " + this.getName() + "\n" +
                "Coordonata x: " + this.getX() +"\n"+
                "Coordonata y:" + this.getY() +"\n"+
                "Tip Locatie:" +this.getType());
    }

}

public enum LocationType {
    CITY, AIRPORT, GAS_STATION
}

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

public enum RoadType {
    HIGHWAY, EXPRESS, COUNTRY
}

