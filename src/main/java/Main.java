import examples.example01.Example01;
import examples.example02.Example02;
import examples.example03.Example03;
import examples.example04_create.Example04_1;
import examples.example04_create.Example04_2;
import examples.example05_filters.Example05;
import examples.example06_inspection.Example06;
import examples.example07_aggregation.Example07;
import examples.example07_aggregation.Example07_2;
import examples.example07_aggregation.Example07_3;
import examples.example08_transformation.Example08;
import examples.example09.Example09;
import examples.example09.Example09_2;
import examples.example11_buffer_time.Example11;
import examples.exmaple10_combine.Example10;
import rx.Observable;
import rx.Subscription;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

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

        /**
         * Создание Observable
         */
//        new Example04_1();
        new Example04_2();

        new Example05();//filters
        new Example06();//inspections
        new Example07();//aggregation
        new Example07_2();//Aggregation to collections
        new Example07_3();//toMap, groupBy
        new Example08();//Transformation of sequences
        new Example09();//более сложные действия над последовательностью - do
        new Example09_2();//forEach, first, last, single, ...
        new Example10();//forEach, first, last, single, ...
        new Example11();//buffer, buffer by time







    }
}
