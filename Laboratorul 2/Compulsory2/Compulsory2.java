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
