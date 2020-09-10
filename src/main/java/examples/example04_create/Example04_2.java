package examples.example04_create;


import examples.example02.Student;
import rx.Observable;
import rx.Subscription;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Example04_2 {

    public Example04_2() throws InterruptedException, IOException {

        //from 1 - коллекцию в последовательность
        Iterable<People> peoples = Arrays.asList(new People("p 1"), new People("p 2"), new People("p 3"));
        Observable<People> peopleObservable = Observable.from(peoples);

        //from 2 например из FutureTask
        FutureTask<Integer> f = new FutureTask<Integer>(() -> {
            Thread.sleep(2000);
            return 21;
        });
        new Thread(f).start();

        Observable<Integer> values = Observable.from(f);

        Subscription subscription = values.subscribe(
                v -> System.out.println("From future task: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        //sout: From future task: 21
        //      Completed
    }


}
