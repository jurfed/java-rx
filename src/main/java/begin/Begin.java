package begin;

import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class Begin {
    public static void main(String[] args) {
        new Begin();
    }

    public Begin() {
        //PublishSubject
        System.out.println("PublishSubject");
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);

        //ReplaySubject - с кэшированием данных
        System.out.println("\nReplaySubject");
        ReplaySubject<Integer> s = ReplaySubject.create();
        s.subscribe(v -> System.out.println("Early:" + v));
        s.onNext(0);
        s.onNext(1);
        s.subscribe(v -> System.out.println("Late: " + v));
        s.onNext(2);

        System.out.println();
        //ReplaySubject - с кэшированием данных с ограниченным буфером
        ReplaySubject<Integer> s2 = ReplaySubject.createWithSize(2);
        s2.onNext(1);
        s2.onNext(2);
        s2.onNext(3);
        s2.subscribe(integer -> System.out.println(integer));
        s2.onNext(4);

        System.out.println("on complete");
        PublishSubject<Integer> subject2 = PublishSubject.create();
        subject2.subscribe(System.out::println);
        subject2.onNext(1);
        subject2.onCompleted();
        subject2.onNext(2);

    }

}
