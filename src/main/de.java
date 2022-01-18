package main;
import java.io.*;


public class de {
    public static void main(String[] args) throws Exception {
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("1.ser"));
    	ois.readObject();
    }
}
