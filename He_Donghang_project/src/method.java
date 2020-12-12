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

    // array list for save the path without backtrack
    public ArrayList<String> pathGood = new ArrayList<>();
    // array list for save the path with backtrack
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

    // print the path
    public String printPath(ArrayList<String> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                stringBuilder.append(list.get(i));
            } else {
                stringBuilder.append(list.get(i)).append(" -> ");
            }
        }

        return stringBuilder.toString();
    }


}
