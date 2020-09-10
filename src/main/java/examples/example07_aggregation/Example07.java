package examples.example07_aggregation;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Example07 {

    public Example07() {

//count
        Observable<Integer> values = Observable.range(0, 3);

        values
                .count()
                .subscribe(new PrintSubscriber("Count"));
        //sout: Count: 3
        //      Count: Completed


//first - вернет первый элемент, удовлетворяющий условию
//last - последний
        values = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        values
                .first(v -> v > 5)
                .subscribe(new PrintSubscriber("First"));

        //sout: First: 6
        //      First: Completed

        values
                .last(v -> v < 6)
                .subscribe(new PrintSubscriber("last"));
        //last: 5
        //last: Completed


//single - последовательность должна содержать только 1 указанный элемент. Его и вернет
//иначе вернет ошибку
        values
                .single(v -> v == 5L)
                .subscribe(new PrintSubscriber("Single2"));
        //sout: Single2: 5
        //      Single2: Completed


//reduce - собственная агрегатная ф-ция
        values
                .reduce((integer, integer2) -> integer + integer2 + 1)
                .subscribe(new PrintSubscriber("reduce"));
//sout: reduce: 64
//      reduce: Completed
        System.out.println();


//scan - похож на reduce, но выдаст все промежуточные результаты
        values
                .scan((integer, integer2) -> integer + integer2 + 1)
                .subscribe(new PrintSubscriber("scan"));
//sout: scan: 1
//scan: 4
//scan: 8
//scan: 13
//scan: 19
//scan: 26
//scan: 34
//scan: 43
//scan: 53
//scan: 64
//scan: Completed
        System.out.println();
    }
}
