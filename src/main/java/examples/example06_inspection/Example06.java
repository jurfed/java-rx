package examples.example06_inspection;

import rx.Observable;
import rx.Subscription;

public class Example06 {

    public Example06() {

//all - метод который проверяет, что каждое значение произведенное последовательностью отвечает заданному критерию
        //вощзыращает 1 true (в самом конце, когда все элементы обработаны) или 1 false на любом элементе и прекращает обработку
        // но Observable продолжает выдавает элементы, например другим подписчиками
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(0);
            o.onNext(10);
            o.onNext(10);
            o.onNext(2);
            o.onCompleted();
        });


        Subscription evenNumbers = values
                .all(i -> i % 2 == 0)
                .subscribe(
                        v -> System.out.println("all: " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
        System.out.println();
        //sout: all: true
        //      Completed


//exists - возвращает Observable, который вернет false, если хотябы 1 элемент не удовлетворяет условию
        values = Observable.range(0, 2);

        Subscription subscription = values
                .exists(i -> i > -1)
                .subscribe(
                        v -> System.out.println("exists  " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
        //sout: false
        //      Completed


//isEmpty
//Оператор возвращает логическое значение указывающее выдавала ли исходная последовательность элементы до завершения или нет.


//contains
        values = Observable.range(0, 5);
        subscription = values
                .contains(4)
                .subscribe(
                        v -> System.out.println("contains  " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
        //sout: contains  true
        //      Completed


//elementAt - выбрать элемент
        values = Observable.range(10, 5);
        values.elementAt(4)
                .subscribe(
                        v -> System.out.println("elementAt  " + v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
//sout: elementAt  14
//      Completed
        System.out.println();


//sequenceEqual - определяет являются ли две последовательности эквивалентны

        Observable<String> strings = Observable.just("1", "2", "3");
        Observable<Integer> ints = Observable.just(1, 2, 3);

        Observable.sequenceEqual(strings, ints, (s,i) -> s.equals(i.toString()))
//Observable.sequenceEqual(strings, ints)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
    }
}
