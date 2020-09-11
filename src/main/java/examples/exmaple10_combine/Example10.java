package examples.exmaple10_combine;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.TimeUnit;

public class Example10 {

    public Example10() {

//concat
        System.out.println("--------------------------- concat");
        Observable<Integer> seq1 = Observable.range(0, 3);
        Observable<Integer> seq2 = Observable.range(10, 3);

        Observable.concat(seq1, seq2)
                .subscribe(System.out::println);

        //0
        //1
        //2
        //10
        //11
        //12

//concat with group by
        System.out.println("---------------------------------- concat with group by");
        Observable<String> words = Observable.just(
                "First",
                "Second",
                "Third",
                "Fourth",
                "Fifth",
                "Sixth"
        );

        Observable.concat(words.groupBy(v -> v.charAt(0)))
                .subscribe(System.out::println);
        //First
        //Fourth
        //Fifth
        //Second
        //Sixth
        //Third


//start with
        System.out.println("---------------------------------- start with");
        Observable<Integer> values = Observable.range(0, 3);

        values.startWith(-1, -2)
                .subscribe(System.out::println);
        //-1
        //-2
        // 0
        // 1
        // 2

//merge
        System.out.println("---------------------------------- merge");
        Observable.merge(
                Observable.interval(250, TimeUnit.MILLISECONDS).map(i -> "First"),
                Observable.interval(150, TimeUnit.MILLISECONDS).map(i -> "Second"))
                .take(4).toBlocking()
                .subscribe(System.out::println);

        //Second
        //First
        //Second
        //Second

//combine - берет последний элемент из первой последовательности - 30
// и поочередно складывает его со всеми элементами во второй последовательности 30+1, 30+10, 30+4
        System.out.println("---------------------------------- combine");
        Observable<Integer> studentObservable = Observable.just(2, 30);
        Observable<Integer> studentObservable2 = Observable.just(1, 10, 4);

        Subscription subscription = studentObservable
                .combineLatest(studentObservable, studentObservable2, (integer, integer2) -> integer + integer2)
                .subscribe(
                        integer -> System.out.println(integer));

            //31
            //40
            //34
    }
}
