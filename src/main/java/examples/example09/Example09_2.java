package examples.example09;

import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Example09_2 {

    public Example09_2() throws InterruptedException, ExecutionException {

// forEach no blocking
        System.out.println("-------------------- forEach");
        Observable<Integer> values = Observable.range(1,10);
        values
                .take(3)
                .forEach(System.out::println);
        //sout: 1
        //      2
        //      3
        //      No blocking


// forEach blocking
        System.out.println("-------------------------forEach blocking");
        Observable<Long> values2 = Observable.interval(100, TimeUnit.MILLISECONDS).limit(10);
        values2
                .take(3)
                .toBlocking()
                .forEach(System.out::println);

        System.out.println("end blocking");
        //sout:     0
        //          1
        //          2
        //          blocking
        //          3
        //          ...

// first
        System.out.println("-------------------------first");
        long value = values
                .take(5)
                .toBlocking()
                .first(i -> i>2);
        System.out.println(value);

        //sout:     3


// toIterable
        System.out.println("-------------------------toIterable");
        Iterable<Integer> iterable = values.take(2).toBlocking().toIterable();
        for (long l : iterable) {
            System.out.println(l);
        }


//latest
        System.out.println("---------------------------latest");
        Observable<Long> values3 = Observable.interval(500, TimeUnit.MILLISECONDS);

        values3.take(5)
                .subscribe(v -> System.out.println("Emitted: " + v));

        Iterable<Long> iterable2 = values3.take(5).toBlocking().latest();
        for (long l : iterable2) {
            System.out.println(l);
            Thread.sleep(750);
        }

        //Emitted: 0
        //0
        //Emitted: 1
        //1
        //Emitted: 2
        //Emitted: 3
        //3
        //Emitted: 4
        System.out.println();


//Future - для получения единственного значения
        System.out.println("------------------------------ Future");
        Observable<Long> values4 = Observable.timer(500, TimeUnit.MILLISECONDS);

        values4.subscribe(v -> System.out.println("Emitted: " + v));

        Future<Long> future = values4.toBlocking().toFuture();
        System.out.println(future.get());

        //Emitted: 0
    }
}
