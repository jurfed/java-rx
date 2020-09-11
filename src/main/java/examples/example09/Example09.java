package examples.example09;

import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;

public class Example09 {

    public Example09() {

////более сложные действия над последовательностью - do, ...
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
        System.out.println("----------------------- doOnEach");
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


//doOnEach with method
//doOnNext, doOnError, doOnCompleted
        System.out.println("----------------------- doOnEach with method");
        service()
                .map(s -> s.toUpperCase())
                .filter(s -> s.length() > 5)
                .subscribe(new PrintSubscriber("Process"));
        //sout: Log: First
        //      Log: Second
        //      Process: SECOND
        //      Log: Third
        //      Log: Completed
        //      Process: Completed


//doOnSubscribe doOnUnsubscribe - какие-либо действия на подписку и отписку
        System.out.println("------------------------------ doOnSubscribe doOnUnsubscribe");

        Observable<Integer> values2 = Observable.create(integerEmitter -> {
            integerEmitter.onNext(1);
            integerEmitter.onNext(2);
        });

        Observable<Integer> tmp = values2
                .doOnSubscribe(() -> System.out.println("New subscription"))
                .doOnUnsubscribe(() -> System.out.println("Subscription over"));

        Subscription s1 = tmp.subscribe(new PrintSubscriber("result: "));
        s1.unsubscribe();

        //sout: New subscription
        //      result: : 1
        //      result: : 2
        //      Subscription over

        System.out.println();
    }

    static Observable<String> service() {
        return Observable.just("First", "Second", "Third")
                .doOnEach(new PrintSubscriber("Log"));
    }


}
