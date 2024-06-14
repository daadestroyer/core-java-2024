


public class Test {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("abc");
        String s3 = new String("pqr");

        System.out.println(s1.equals(s2)); // true
        System.out.println(s3.equals(s2)); // false

        String s4 = "abc";

        System.out.println(s4.equals(s1)); // true
        System.out.println(s4 == s1); // false
    }
}

