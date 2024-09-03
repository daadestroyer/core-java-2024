package exceptionhandling;

import java.io.IOException;
import java.sql.SQLException;

class A {
    static void m1() throws IOException {

    }
}

public class App {
    public static void main(String[] args) throws IOException {
        try {

            A.m1();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}



