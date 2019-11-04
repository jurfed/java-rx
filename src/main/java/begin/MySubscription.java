package begin;

import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class MySubscription {

    public MySubscription() {

        System.out.println("\nSubject !!!");
        Subject<Integer, Integer> s = PublishSubject.create();
        s.subscribe(
                integerOnNext -> System.out.println(integerOnNext),
                integerOnError -> System.out.println(integerOnError),
                () -> {
                    System.out.println("completed");
                });

        s.onNext(1);
        s.onError(new Exception("!!!!!"));
//        s.onCompleted();

        System.out.println("\nSubscription !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Subscription");
        Subject<Integer, Integer> integerSubject = PublishSubject.create();

        rx.Subscription subscription = integerSubject.subscribe(
                integer -> System.out.println("Subcriptin 1  " + integer),
                throwable -> System.out.println(throwable),
                () -> System.out.println("done"));

        rx.Subscription subscription2 = integerSubject.subscribe(
                integer -> System.out.println("Subcriptin 2  " + integer),
                throwable -> System.out.println(throwable),
                () -> System.out.println("Subcriptin 2  done"));

        integerSubject.onNext(1);
        subscription.unsubscribe();
        integerSubject.onNext(2);
        integerSubject.onCompleted();
    }


}
