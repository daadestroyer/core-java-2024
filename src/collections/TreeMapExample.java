package collections;

import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        // decreasing order
        TreeMap<Integer, String> tm = new TreeMap<>((Integer key1, Integer key2) -> key2 - key1);
        tm.put(21, "ABC");
        tm.put(1, "PRE");
        tm.put(45, "ARE");
        tm.put(56, "POL");
        tm.forEach((Integer key, String val)-> System.out.println(key+"---"+val));

        System.out.println("---------------------------------");

        // ascending order
        TreeMap<Integer, String> tm1 = new TreeMap<>((Integer key1, Integer key2) -> key1 - key2);
        tm1.put(21, "ABC");
        tm1.put(1, "PRE");
        tm1.put(45, "ARE");
        tm1.put(56, "POL");
        tm1.forEach((Integer key, String val)-> System.out.println(key+"---"+val));

    }
}
