package interview;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(101,"Shubham");
        map.put(102,"Raj");
        map.put(101,"Ravi");
        System.out.println(map);

    }
}
