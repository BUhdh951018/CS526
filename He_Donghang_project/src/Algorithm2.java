import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Donghang He
 * @date 2020/11/17 4:43 下午
 */
public class Algorithm2 extends method{
    public static int maxDistance =  Integer.MAX_VALUE;

    public Algorithm2(String city, HashMap<String, Integer> graph, HashMap<String, Integer> distance) {
        this.city = city;
        this.graph = graph;
        this.distance = distance;
    }

    public void getPath() {
        getDistance(this.city);
    }
    // this function is same as the one in algorithm1
    public void getDistance(String city) {
        String nextCity = getNextCity(city);

        if (city.equals("Z")) {
            this.lastCity = city;
            pathBad.add(city);
            pathGood.add(city);
        } else if (nextCity == null) {
            this.pathGood.remove(lastCity);
            this.lastCity = pathGood.get(pathGood.size() - 1);
            this.pathBad.add(lastCity);
            shortestDistance -= graph.get(lastPair);
        } else {
            lastPair = nextCity;
            lastCity = Character.toString(nextCity.charAt(1));

            for (char c : nextCity.toCharArray()) {
                if (!this.usedCityList.contains(Character.toString(c))) {
                    usedCityList.add(Character.toString(c));
                }
            }

            if (pathGood.isEmpty()) {
                pathGood.add(city);
            }
            pathGood.add(lastCity);

            if (pathBad.isEmpty()) {
                pathBad.add(city);
            }
            pathBad.add(lastCity);

            shortestDistance += graph.get(nextCity);
        }

        if (lastCity.equals("Z")) {
            System.out.println("Sequence of all node: " + printPath(this.pathGood));
            System.out.println("Shortest path: " + printPath(this.pathBad));
            System.out.println("Shortest path length: " + this.shortestDistance);
            System.out.println();
        } else {
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
            if ((distance.get(nextCity) + graph.get(cityPair)) < maxDistance &&
                    !this.usedCityList.contains(nextCity)) {
                maxDistance = distance.get(nextCity) + graph.get(cityPair);
                smallestDD = cityPair;
            }
        }
        return smallestDD;
    }
}
