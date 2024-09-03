package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

class Eagle {
    public int breadId;
    public String bread;
    private boolean canSwim;

    public Eagle() {
        System.out.println("private constructor");
    }

    public void fly() {
        System.out.println("fly");
    }

    private void eat() {
        System.out.println("fly");
    }
}

public class App02_ReflectionOfMethods {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class eagleClass = Eagle.class;

        // get the public fields with this
        /*
        Field[] fields = eagleClass.getFields();
        for(Field field : fields){
            System.out.println("Field Name : "+field.getName());
            System.out.println("Type : "+field.getType());
            System.out.println("Modifier : "+ Modifier.toString(field.getModifiers()));
            System.out.println("********");
        }
        */
        /*
        System.out.println("****************************************************************");
        // get both public and private fields
        Field[] allFields = eagleClass.getDeclaredFields();
        for(Field field : allFields){
            System.out.println("Field Name : "+field.getName());
            System.out.println("Type : "+field.getType());
            System.out.println("Modifier : "+ Modifier.toString(field.getModifiers()));
            System.out.println("********");
        }
        */

        // setting the public field
        /*
        Eagle eagle = new Eagle();
        Field breadIdField = eagleClass.getField("breadId");
        Field breadNameField = eagleClass.getField("bread");
        breadIdField.set(eagle,101);
        breadNameField.set(eagle,"crow");

        System.out.println(eagle.breadId);
        System.out.println(eagle.bread);
        */

        // setting the private field
        /*
        Eagle eagle = new Eagle();
        Field swimField = eagleClass.getDeclaredField("canSwim");
        swimField.setAccessible(true);
        swimField.set(eagle,true);

        if(swimField.getBoolean(eagle)){
            System.out.println("value is set to true");
        }
        */

        // accessing private class constructor
        Constructor[] constructors = eagleClass.getDeclaredConstructors();
        for(Constructor eagleConstructor : constructors){
            System.out.println("Modifier : "+Modifier.toString((eagleConstructor.getModifiers())));
            eagleConstructor.setAccessible(true);
            Eagle eagleObj = (Eagle) eagleConstructor.newInstance();
        }
    }
}
