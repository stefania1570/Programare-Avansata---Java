
import java.util.Random;

public class Homework2 {
    public static void main(String[] args) {
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();

        Random random = new Random();
        final int MAX_COORDINATE = 100; // Maximum coordinate value for x and y
        int maxLocations=100; int maxRoads=150;

        Location[] locations = new Location[200];
        int numberOfLocations=0;
        Location c1 = new City(12,20,"Galati",28123);c1.setIndex(numberOfLocations); locations[numberOfLocations++]=c1;
        Location c2 = new City(4, -7, "Braila", 20345);c2.setIndex(numberOfLocations);locations[numberOfLocations++]=c2;
        Location c3 = new City(-16, 8, "Suceava", 12788);c3.setIndex(numberOfLocations);locations[numberOfLocations++]=c3;
        Location c4 = new City(-9, -12, "Iasi", 34567);c4.setIndex(numberOfLocations);locations[numberOfLocations++]=c4;
        Location a1 = new Airport(-14,-18, "AeroportIasi",67);a1.setIndex(numberOfLocations);locations[numberOfLocations++]=a1;
        Location a2 = new Airport(-24,9, "AeroportTimisoara",34);a2.setIndex(numberOfLocations);locations[numberOfLocations++]=a2;
        Location g1 = new gasStation(15,16,"LukOil",6.5);g1.setIndex(numberOfLocations);locations[numberOfLocations++]=g1;
        Location g2 = new gasStation(-18,10,"Rompetrol",6.4);g2.setIndex(numberOfLocations);locations[numberOfLocations++]=g2;
        Location g3 = new gasStation(10,17,"BenGas",8.3);g3.setIndex(numberOfLocations);locations[numberOfLocations++]=g3;

//        //Generating random instances of locations
//        for (int i = 0; i < maxLocations; i++) {
//            String name = getRandomName();
//            int x = random.nextInt(MAX_COORDINATE);
//            int y = random.nextInt(MAX_COORDINATE);
//            Location location = null;
//            if (random.nextBoolean()){
//               location = new City(x,y,name,12345);
//            } else if (random.nextBoolean()){
//                name = "Airport "+name;
//                location = new Airport(x,y,name,45);
//            } else {
//                name = "Gas Station "+name;
//                location = new gasStation(x,y,name,45);
//            }
//            location.setIndex(numberOfLocations);
//            locations[numberOfLocations++] = location;
//        }

        Road[] roads = new Road[200];
        int numberOfRoads=0;
        Road h1= new Highway(c1,c2,160,true,"h1");roads[numberOfRoads++]=h1;
        Road h2= new Highway(c4,c3, 200,false,"h2");roads[numberOfRoads++]=h2;
        Road h3= new Highway(a1,c4,135,true,"h3");roads[numberOfRoads++]=h3;
        Road h4= new Highway(c3,a2,230,true,"h4");roads[numberOfRoads++]=h4;
        Road e1= new Express(c4,c1,100,false,"e1");roads[numberOfRoads++]=e1;
        Road e2= new Express(a1,c2,105,false,"e2");roads[numberOfRoads++]=e2;
        Road e3= new Express(c3,g3,100,false,"e3");roads[numberOfRoads++]=e3;
        Road co1= new Country(c3,g2,85,false,"co1");roads[numberOfRoads++]=co1;
        Road co2= new Country(g2,a2,70,false,"co2");roads[numberOfRoads++]=co2;
        Road co3= new Country(g3,c1,90,false,"co3");roads[numberOfRoads++]=co3;
        Road co4= new Country(g1,g3,60,false,"co4");roads[numberOfRoads++]=co4;

//        for (int i = 0; i < maxRoads; i++) {
//            String name = getRandomName();
//            Location source = locations[random.nextInt(numberOfLocations)];
//            Location destination = locations[random.nextInt(numberOfLocations)];
//            while(source.getName().equals(destination.getName())){
//                //source must be different from the destination
//                destination = locations[random.nextInt(numberOfLocations)];
//                if(!source.getName().equals(destination.getName())){
//                    break;
//                }
//            }
//            int speedLimit = 1;
//            Random random2 = new Random();
//            Road road;
//            if (random.nextBoolean()){
//                speedLimit = 1+ random2.nextInt(89);
//                road = new Country(source,destination,speedLimit,random.nextBoolean(),name);
//                road.setLength(1+random.nextDouble(200));
//            } else if (random.nextBoolean()){
//                speedLimit = 91+random2.nextInt(29);
//                road = new Express(source,destination,speedLimit,random.nextBoolean(),name);
//                road.setLength(1+ random.nextDouble(200));
//            } else {
//                speedLimit = 122+random2.nextInt(128);
//                road = new Highway(source,destination,speedLimit,random.nextBoolean(),name);
//                road.setLength(1+random.nextDouble(200));
//            }
//            roads[numberOfRoads++] = road;
//        }

        Problem pb = new Problem(locations, roads, numberOfLocations,numberOfRoads);
        if(pb.isValid(pb))
        {
            Problem.printAdjacencyMatrix(pb.getAdjMatrix());
            for(int iterator=0;iterator< numberOfLocations-1;iterator++)
                for(int iterator2=iterator+1;iterator2<numberOfLocations;iterator2++) {
                    System.out.print(locations[iterator].getName() + " - " + locations[iterator2].getName() + " : ");
                    System.out.println(pb.pathExists(locations[iterator],locations[iterator2]));
                }
            Solution sol = new Solution(pb);
            System.out.print("\nSOLUTION TO THE PROBLEM: Shortest path using DIJKSTRA from location: ");
            System.out.println(pb.getLocations()[2].getName()+"\n");
            sol.dijkstra(pb.getAdjMatrix(), 2);
        }

        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;

        System.out.println("Running Time: " + runningTime + " milliseconds");
        System.out.println("Memory Increase: " + memoryIncrease + " bytes");
    }
    private static String getRandomName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        int length = random.nextInt(5) + 5; // Random name length between 5 and 10 characters

        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'A'); // Random uppercase letter
            name.append(c);
        }

        return name.toString();
    }
    
}
