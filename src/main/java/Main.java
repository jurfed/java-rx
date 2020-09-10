import examples.example01.Example01;
import examples.example02.Example02;
import examples.example03.Example03;
import rx.Observable;
import rx.Subscription;

import java.security.spec.RSAOtherPrimeInfo;

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


        Observable<Integer> studentObservable = Observable.just(2, 30, 22, 5, 60, 2);
        Observable<Integer> studentObservable2 = Observable.just(1, 2, 3, 4, 5, 6);

        Subscription subscription = studentObservable.combineLatest(studentObservable, studentObservable2, (integer, integer2) -> integer + integer2).subscribe(
                integer -> System.out.println(integer), throwable -> System.out.println(), () -> System.out.println());


        studentObservable.startWith(100).distinct().subscribe(integer -> System.out.println(integer));

    }
}
