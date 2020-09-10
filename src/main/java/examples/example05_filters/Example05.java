package examples.example05_filters;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.TimeUnit;

public class Example05 {

    public Example05() throws InterruptedException {
//filter
        //public final Observable<T> filter(Func1<? super T,java.lang.Boolean> predicate)
        Observable<Integer> values = Observable.range(0,10);
        Subscription oddNumbers = values
                .filter(v -> v % 2 == 0)
                .subscribe(
                        v -> System.out.println("after filter: " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
//distinct
        values = Observable.create(o -> {
            o.onNext(1);
            o.onNext(1);
            o.onNext(2);
            o.onNext(3);
            o.onNext(2);
            o.onCompleted();
        });

        Subscription subscription = values
                .distinct()
                .subscribe(
                        v -> System.out.println("after distinct: " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
//distinct с функцией
        Observable<String> values2 = Observable.create(o -> {
            o.onNext("First");
            o.onNext("Second");
            o.onNext("Third");
            o.onNext("Fourth");
            o.onNext("Fifth");
            o.onCompleted();
        });

        Subscription subscription2 = values2
                .distinct(v -> v.charAt(0))
                .subscribe(
                        v -> System.out.println("distinct с функцией:  " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

//ignoreElements игнорирует каждое значение, но пропускает onCompleted и onError.

//skip и take -         values.take(2).subscribe(...
//skip и take -         values.skip(1).subscribe(...

//Есть перегруженные версии операторов где последовательность обрезается в определенный момент времени
// а не на конкретном элементе.
        Observable<Long> values3 = Observable.interval(100, TimeUnit.MILLISECONDS);

        Subscription subscription3 = values3
                .take(250, TimeUnit.MILLISECONDS)
                .subscribe(
                        v -> System.out.println("take with time:  " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        Thread.sleep(1000);
        //sout: take with time 0
        //      take with time 1

//skipLast и takeLast что точка отсечения отсчитывается с конца.
    }

}
