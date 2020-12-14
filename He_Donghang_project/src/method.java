import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Donghang He
 * @date 2020/11/17 4:46 下午
 */
public class method {
    // input node
    public String city;
    // the last path we passed
    public String lastPair;
    // the last city we passed
    public String lastCity;
    // argument for the shortest distance
    public int shortestDistance = 0;
    // graph input
    public HashMap<String, Integer> graph;
    //direct distance
    public HashMap<String, Integer> distance;

    // array list for save the path without backtrack(shortest path)
    public ArrayList<String> pathGood = new ArrayList<>();
    // array list for save the path with backtrack(node sequence)
    public ArrayList<String> pathBad = new ArrayList<>();
    // array list for save the passing cities
    public ArrayList<String> usedCityList = new ArrayList<>();

    // get the adjacent city from current city
    public ArrayList<String> getAdjacentCity(String city) {
        // set the key of the graph in a array list
        ArrayList<String> cityKey = new ArrayList<>(graph.keySet());
        // arraylist for save the adjacent cities
        ArrayList<String> adjacentCity = new ArrayList<>();
        // loop all cities
        for (String key : cityKey) {
            char[] c = key.toCharArray();
            String cityName = Character.toString(c[0]);
            // if equal the input argument city
            if (cityName.equals(city)) {
                // add to list
                adjacentCity.add(key);
            }
        }
        return adjacentCity;
    }

    // check whether current city is the final station
    // or dead end or could find next node to final node
    public void checkCity(String city, String nextCity) {
        // if user enter city Z
        if (city.equals("Z")) {
            // set the final node to the lastCity
            this.lastCity = city;
            // add the final node to each path(node sequence and shortest path)
            pathBad.add(city);
            pathGood.add(city);
        } else if (nextCity == null) { // if we could not find the next city
            // backtrack to the last node
            // remove the last node from shortest path
            this.pathGood.remove(lastCity);
            // last node update to the one before the old last node
            this.lastCity = pathGood.get(pathGood.size() - 1);
            // add the dead end node to the sequence of node
            this.pathBad.add(lastCity);
            // update shortest distance
            shortestDistance -= graph.get(lastPair);
        } else {
            // set lastPair and lastCity
            lastPair = nextCity;
            lastCity = Character.toString(nextCity.charAt(1));
            // check whether the nextCity has been visited
//            for (char c : nextCity.toCharArray()) {
//                if (!this.usedCityList.contains(Character.toString(c))) {
//                    usedCityList.add(Character.toString(c));
//                }
//            }
            usedCityList.add(city);
            if (!this.usedCityList.contains(lastCity)) {
                usedCityList.add(lastCity);
            }
            // add the city to path(shortest path)
            if (pathGood.isEmpty()) {
                // if path is empty first add the input node
                pathGood.add(city);
            }
            // then add the next node
            pathGood.add(lastCity);
            // add the city to path(node sequence)
            if (pathBad.isEmpty()) {
                pathBad.add(city);
            }
            pathBad.add(lastCity);
            // add the distance
            shortestDistance += graph.get(nextCity);
        }
    }

    // print the path
    public String printPath(ArrayList<String> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                // last node
                stringBuilder.append(list.get(i));
            } else {
                stringBuilder.append(list.get(i)).append(" -> ");
            }
        }

        return stringBuilder.toString();
    }
}
