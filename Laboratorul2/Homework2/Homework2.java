public class Homework2 {
    public static void main(String[] args) {
        Location[] locations = new Location[10];
        int numberOfLocations=0;
        City c1 = new City(12,20,"Galati",28123);c1.setIndex(numberOfLocations); locations[numberOfLocations++]=c1;
        City c2 = new City(4, -7, "Braila", 20345);c2.setIndex(numberOfLocations);locations[numberOfLocations++]=c2;
        City c3 = new City(-16, 8, "Suceava", 12788);c3.setIndex(numberOfLocations);locations[numberOfLocations++]=c3;
        City c4 = new City(-9, -12, "Iasi", 34567);c4.setIndex(numberOfLocations);locations[numberOfLocations++]=c4;
        Airport a1 = new Airport(-14,-18, "AeroportIasi",67);a1.setIndex(numberOfLocations);locations[numberOfLocations++]=a1;
        Airport a2 = new Airport(-24,9, "AeroportTimisoara",34);a2.setIndex(numberOfLocations);locations[numberOfLocations++]=a2;
        gasStation g1 = new gasStation(15,16,"LukOil",6.5);g1.setIndex(numberOfLocations);locations[numberOfLocations++]=g1;
        gasStation g2 = new gasStation(-18,10,"Rompetrol",6.4);g2.setIndex(numberOfLocations);locations[numberOfLocations++]=g2;
        gasStation g3 = new gasStation(10,17,"BenGas",8.3);g3.setIndex(numberOfLocations);locations[numberOfLocations++]=g3;

        Road[] roads = new Road[20];
        int numberOfRoads=0;
        Highway h1= new Highway(c1,c2,160,true);roads[numberOfRoads++]=h1;
        Highway h2= new Highway(c4,c3, 200,false);roads[numberOfRoads++]=h2;
        Highway h3= new Highway(a1,c4,135,true);roads[numberOfRoads++]=h3;
        Highway h4= new Highway(c3,a2,230,true);roads[numberOfRoads++]=h4;
        Express e1= new Express(c4,c1,100,false);roads[numberOfRoads++]=e1;
        Express e2= new Express(a1,c2,105,false);roads[numberOfRoads++]=e2;
        Express e3= new Express(c3,g3,100,false);roads[numberOfRoads++]=e3;
        Country co1= new Country(c3,g2,85,false);roads[numberOfRoads++]=co1;
        Country co2= new Country(g2,a2,70,false);roads[numberOfRoads++]=co2;
        Country co3= new Country(g3,c1,90,false);roads[numberOfRoads++]=co3;
        Country co4= new Country(g1,g3,60,false);roads[numberOfRoads++]=co4;

        Problem pb = new Problem(locations, roads, numberOfLocations,numberOfRoads);
        if(pb.isValid(pb))
        {
            Problem.printAdjacencyMatrix(pb.getAdjMatrix());
            for(int iterator=0;iterator< numberOfLocations-1;iterator++)
                for(int iterator2=iterator+1;iterator2<numberOfLocations;iterator2++) {
                    System.out.print(locations[iterator].getName() + " - " + locations[iterator2].getName() + " : ");
                    System.out.println(pb.pathExists(locations[iterator],locations[iterator2]));
                }
        }


    }
}
