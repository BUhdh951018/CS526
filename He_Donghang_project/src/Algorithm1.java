import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Donghang He
 * @date 2020/11/17 4:43 下午
 */
public class Algorithm1 extends method{

    public static int maxDistance =  Integer.MAX_VALUE;

    public Algorithm1(String city, HashMap<String, Integer> graph, HashMap<String, Integer> distance) {
         this.city = city;
         this.graph = graph;
         this.distance = distance;
    }

    public void getPath() {
        getDistance(this.city);
    }

    public void getDistance(String city) {
        // according to the algorithm get the next node
        String nextCity = getNextCity(city);

        checkCity(city, nextCity);

        if (lastCity.equals("Z")) {
            // if reach the end print
            System.out.println("Sequence of all node: " + printPath(this.pathBad));
            System.out.println("Shortest path: " + printPath(this.pathGood));
            System.out.println("Shortest path length: " + this.shortestDistance);
            System.out.println();
        } else {
            // if not recursive itself and find the next city
            getDistance(lastCity);
        }
    }

    public String getNextCity(String city) {
        ArrayList<String> adjacentCity = getAdjacentCity(city);
        // city pair for save the smallest dd of adjacent city
        String smallestDD = null;
        maxDistance = Integer.MAX_VALUE;
        // get the smallest dd of adjacent city
        for (String cityPair : adjacentCity) {
            // second char in the city pair is the adjacent city
            String nextCity = Character.toString(cityPair.charAt(1));
            // get the distance of adjacent city to Z, and check whether is smaller and not has been used
            if (distance.get(nextCity) < maxDistance &&
                    !this.usedCityList.contains(nextCity)) {
                // set max distance for dd of next city
                maxDistance = distance.get(nextCity);
                // save city pair
                smallestDD = cityPair;
            }
        }
        return smallestDD;
    }
}
