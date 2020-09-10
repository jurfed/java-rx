package examples.example04_create;


import org.w3c.dom.ls.LSOutput;
import rx.Observable;
import rx.Subscription;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Example04_1 {

    public Example04_1() throws InterruptedException, IOException {

//just создает Observable, который выдаст определенное заранее количество значений, после чего завершится.
        Observable<People> values = Observable.just(new People("one"), new People("two"), new People("three"));

        MyObserver myObserver = new MyObserver();

        Subscription subscription = values.subscribe(myObserver);

//defer не создает новый Observable сразу, а создает при подписке на него
//defer принимает функцию, которая возвращает Observable и будет выполнена для каждого нового подписчика

        Observable<Long> now = Observable.defer(() -> Observable.just(System.currentTimeMillis()));

        now.subscribe(System.out::println);
        Thread.sleep(1000);
        now.subscribe(System.out::println);

//create - очень мощный метод
//static <T> Observable<T> create(Observable.OnSubscribe<T> f)
//Внутри нее мы можем вручную определить события, которые будут выдаваться подписчику.
//Тоже выдает значения только при наличии подписчика ("lazily")
        Observable<String> values2 = Observable.create(o -> {
            o.onNext("Hello");
            o.onCompleted();
        });
        Subscription subscription2 = values2.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );

//range
        Observable<Integer> valuesRange = Observable.range(10, 15);
        valuesRange.subscribe(integer -> System.out.println("число: " + integer));

//interval - бесконечная последовательность
        Observable<Long> values3 = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Subscription subscription3 = values3.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        System.in.read();

//timer 1 Первый вариант создает Observable выдающий 0L через заданный промежуток времени.
        Observable<Long> valuesTimer = Observable.timer(1, TimeUnit.SECONDS);
        Subscription subscriptionTimer = valuesTimer.subscribe(
                v -> System.out.println("Timer: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
// Второй вариант ожидает заданный промежуток времени, затем начинает выдавать значения так же как interval с заданной частотой.
        Observable<Long> valuesT2 = Observable.timer(2, 1, TimeUnit.SECONDS);
        Subscription subscriptionT2 = valuesT2.subscribe(
                v -> System.out.println("Timer with interval: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );

        Thread.sleep(10000);


//можно превращать в Observable события, например клика мышки
//        Button button2 = new Button();
//        Observable<ActionEvent> events = Observable.create(o -> {
//            button2.setOnAction(new EventHandler<ActionEvent>() {
//                @Override public void handle(ActionEvent e) {
//                    o.onNext(e);
//                }
//            });
//        });

    }


}
