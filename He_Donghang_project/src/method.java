import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Donghang He
 * @date 2020/11/17 4:46 下午
 */
public class method {
    public String city;
    public String lastPair;
    public String lastCity;
    public int shortestDistance = 0;
    public HashMap<String, Integer> graph;
    public HashMap<String, Integer> distance;

    public ArrayList<String> pathGood = new ArrayList<>();
    public ArrayList<String> pathBad = new ArrayList<>();
    public ArrayList<String> usedCityList = new ArrayList<>();

    // get the adjacent city from current city
    public ArrayList<String> getAdjacentCity(String city) {
        ArrayList<String> cityKey = new ArrayList<>(graph.keySet());
        ArrayList<String> adjacentCity = new ArrayList<>();

        for (String key : cityKey) {
            char[] c = key.toCharArray();
            String cityName = Character.toString(c[0]);
            if (cityName.equals(city)) {
                adjacentCity.add(key);
            }
        }
        return adjacentCity;
    }

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
