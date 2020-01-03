package examples;

import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

public class Example01 {

    public Example01() throws InterruptedException {
        System.out.println("Publish subject: 1, 2, 3, 4");

        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);//подписываемся и получаем дальнейшие сообщения
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);

        System.out.println("\nReplay subject:");
        ReplaySubject<Integer> replaySubject = ReplaySubject.create();
        replaySubject.subscribe(integer -> System.out.println("Early:" + integer));
        replaySubject.onNext(1);
        replaySubject.onNext(2);

        replaySubject.subscribe(integer -> System.out.println("Late:" + integer));
        replaySubject.onNext(3);
        replaySubject.onNext(4);

        System.out.println("\nReplay subject with time scheduler 1, 2, 3, 4:");
        ReplaySubject<Integer> s = ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS,
                Schedulers.immediate());
        s.onNext(1);
        Thread.sleep(100);
        s.onNext(2);
        Thread.sleep(100);
        s.onNext(3);
        Thread.sleep(100);
        s.onNext(4);
        Thread.sleep(100);
        s.subscribe(v -> System.out.println("Late: " + v));


        System.out.println("\nBehaviorSubject: ");
        BehaviorSubject<Integer> s2 = BehaviorSubject.create();
        s2.onNext(1);
        s2.onNext(2);
        s2.onNext(3);

        s2.subscribe(
                v -> System.out.println("Late: " + v),
                e -> System.out.println("Error"),
                () -> System.out.println("Completed")
        );
        s2.onCompleted();

        System.out.println("\nError subject:");
        ReplaySubject s3 = ReplaySubject.create();
        s3.onNext(1);
        s3.onNext(2);
        s3.onNext(3);

        s3.subscribe(
                v -> System.out.println("Late: " + v),
                e -> System.out.println("Error"),
                () -> System.out.println("Completed")
        );
        s3.onError(new Exception("Ow my god!!! This id error..."));
        s3.onNext(4);

    }
}
