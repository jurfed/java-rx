package begin;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MyObservable {
    public MyObservable() throws InterruptedException, IOException {
        System.out.println("Observable");
        Observable<String> values = Observable.just("one", "two", "three");
        Subscription subscription = values.subscribe(
                s -> System.out.println("Received: " + s),
                throwable -> System.out.println("Error: " + throwable),
                () -> System.out.println("Completed")
        );

        System.out.println("currentTimeMillis");
        Observable<Long> now = Observable.just(System.currentTimeMillis());

        now.subscribe(System.out::println);
        Thread.sleep(1000);
        now.subscribe(System.out::println);


        System.out.println("currentTimeMillis 2");
        Observable<Long> now2 = Observable.defer(() ->
                Observable.just(System.currentTimeMillis()));

        now2.subscribe(System.out::println);
        Thread.sleep(1000);
        now2.subscribe(System.out::println);

        System.out.println("Observable 3");
        Observable<String> values2 = Observable.create(o -> {
            o.onNext("Hello");
            o.onCompleted();
        });
        Subscription subscription2 = values2.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );

        Observable<Integer> values3 = Observable.range(10, 15);


        Subscription subscription1 = values3.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

        Observable<Long> values4 = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Subscription subscription4 = values4.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        System.in.read();


        Observable<Long> values5 = Observable.timer(2, 1, TimeUnit.SECONDS);
        Subscription subscription5 = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );


        System.out.println("FutureTask");
        FutureTask<Integer> f = new FutureTask<Integer>(() -> {
            Thread.sleep(2000);
            return 21;
        });
        new Thread(f).start();

        Observable<Integer> values6 = Observable.from(f);

        Subscription subscription6 = values6.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );

        Integer[] integers = {1,2,3,4,5};
        Observable<Integer> observable = Observable.from(integers);
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }



}
