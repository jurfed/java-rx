package examples.example08_transformation;

import rx.Observable;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Example08 {

    public Example08() throws InterruptedException {

        //map - преобзарует исходную последовательность в новую
        Observable<Integer> values = Observable.range(0, 4);
//1
        values
                .map(i -> i + 3)
                .subscribe(new PrintSubscriber("Map"));
        //sout: Map: 3
        //      Map: 4
        //      Map: 5
        //      Map: 6
        //      Map: Completed


//2
        values =
                Observable.just("0", "1", "2", "3")
                        .map(Integer::parseInt);
        values.subscribe(new PrintSubscriber("Map"));


//cast - приведение к типу
        Observable<Two> values2 = Observable.just(new Two(), new Two());

        values2
                .cast(One.class)
                .subscribe(new PrintSubscriber("Cast "));


//ofType - игрнорирует неприводимые значения
        Observable<Object> values3 = Observable.just(0, 1, "2", 3);

        values3
                .ofType(Integer.class)
                .subscribe(new PrintSubscriber("ofType"));
        //sout:     ofType: 0
        //          ofType: 1
        //          ofType: 3
        //          ofType: Completed


//StempAndInterval
        Observable<Long> values4 = Observable.interval(100, TimeUnit.MILLISECONDS);

        values4.take(3)
                .timestamp()//добавляет время вывода
                .timeInterval()//добавляет индетвал вывода
                .subscribe(new PrintSubscriber("StempAndInterval"));

        Thread.sleep(500);
//sout: StempAndInterval: TimeInterval [intervalInMilliseconds=101, value=Timestamped(timestampMillis = 1599748511735, value = 0)]
//StempAndInterval: TimeInterval [intervalInMilliseconds=99, value=Timestamped(timestampMillis = 1599748511834, value = 1)]
//StempAndInterval: TimeInterval [intervalInMilliseconds=100, value=Timestamped(timestampMillis = 1599748511934, value = 2)]
//StempAndInterval: Completed


//flatMap - разбивает последовательность на новые последовательности
        Observable<Integer> values5 = Observable.just(-1, 3);

        values5
                .flatMap(i -> i >= 0 ? Observable.range(0, i) : Observable.just(i))
                .subscribe(new PrintSubscriber("flatMap"));

        //sout: flatMap: -1
        //      flatMap: 0
        //      flatMap: 1
        //      flatMap: 2
        //      flatMap: Completed


//concatMap - ???
        Observable.just(100, 150)
                .concatMap(i ->
                        Observable.range(i, 2)
                                .map(v -> i)
                )
                .subscribe(
                        System.out::println);
        //sout: 100
        //      100
        //      150
        //      150
    }
}
