
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            Simulator s = new Simulator(2, 10, "C:/Users/hamzehhatamleh/Desktop/ja/ProcessorExecutionAssigment/src/TestFileJava.txt");
            s.simulate();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}


