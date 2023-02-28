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
