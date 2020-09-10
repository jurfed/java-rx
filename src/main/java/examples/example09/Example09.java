package examples.example09;

import rx.Observable;

public class Example09 {

    public Example09() {

//более сложные действия над последовательностью
        Observable<String> values = Observable.just("No", "side", "effects", "please");

        Inc index = new Inc();

        Observable<String> indexed =
                values.map(w -> {
                    index.inc();
                    return w;
                });

        indexed.subscribe(w -> System.out.println(index.getCount() + ": " + w));

        //sout:     1: No
        //          2: side
        //          3: effects
        //          4: please



//doOnEach
        values = Observable.just("side", "effects");

        values
                .doOnEach(new PrintSubscriber("Log"))
                .map(s -> s.toString().toUpperCase())
                .subscribe(new PrintSubscriber("Process"));

        //Log: side
        //Process: SIDE
        //Log: effects
        //Process: EFFECTS
        //Log: Completed
        //Process: Completed

        System.out.println();
    }
}
