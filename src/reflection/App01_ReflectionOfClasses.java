package reflection;

import java.lang.reflect.Modifier;

class Bird {
    public String breed;
    private boolean canSwim;

    public void fly() {
        System.out.println("fly");
    }

    public void eat() {
        System.out.println("eat");
    }
}

public class App01_ReflectionOfClasses {
    public static void main(String[] args) throws ClassNotFoundException {
        // get the object of class Bird

        // way - 1 using forName method
        /*
            Class birdClass = Class.forName("reflection.Bird");
            System.out.println(birdClass.getSimpleName());
         */

        // way - 2 using .class
        /*
            Class birdClass = Bird.class;
            System.out.println(birdClass.getSimpleName());
        */

        // way - 3 using .getClass method
        Bird bird = new Bird();
        Class birdClass = bird.getClass();
        System.out.println(birdClass.getSimpleName());

    }
}





















