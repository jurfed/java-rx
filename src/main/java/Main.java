import examples.example01.Example01;
import examples.example02.Example02;
import examples.example03.Example03;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Subjects
         */
        new Example01();

        /**
         * Custom Subscriber with Employee - sequence
         */
        new Example02();

        /**
         * Custom Action1
         */
        new Example03();

    }
}
