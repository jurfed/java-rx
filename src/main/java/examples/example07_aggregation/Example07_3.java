package examples.example07_aggregation;

import rx.Observable;

public class Example07_3 {

    public Example07_3() {
//toMap превращает последовательность T в карту<TKey, T>. Есть 3 перегрузки
        Observable<Person> values = Observable.just(
                new Person("Will", 25),
                new Person("Nick", 40),
                new Person("Saul", 35)
        );

//1
        values
                .toMap(person -> person.name)//key selector
                .subscribe(new PrintSubscriber("toMap"));
        //sout: toMap: {
        //              Saul=Person{name='Saul', age=35},
        //              Nick=Person{name='Nick', age=40},
        //              Will=Person{name='Will', age=25}}
        //      toMap: Completed


//2
        values
                .toMap(
                        person -> person.name,//key selector
                        person -> person.age)//value selector
                .subscribe(new PrintSubscriber("toMap"));
        //sout: toMap: {Saul=35, Nick=40, Will=25}
        //      toMap: Completed


//toMultimap - создает карты, у которых в значения список
        values = Observable.just(
                new Person("Will", 35),
                new Person("Nick", 40),
                new Person("Saul", 35)
        );

        values
                .toMultimap(
                        person -> person.age,//key selector
                        person -> person.name)//value selector
                .subscribe(new PrintSubscriber("toMultimap"));
        //sout: toMultimap: {35=[Will, Saul], 40=[Nick]}
        //      toMultimap: Completed


//groupBy and flatMap
        Observable<String> values2 = Observable.just(
                "first",
                "second",
                "third",
                "forth",
                "fifth",
                "sixth"
        );

        values2.groupBy(word -> word.charAt(0))//key selector
                .flatMap(group ->
                        group.last().map(v -> group.getKey() + ": " + v)
                )
                .subscribe(v -> System.out.println(v));


//nest - вложенная последовательность
        Observable.range(0, 3)
                .nest()
                .subscribe(ob -> ob.subscribe(System.out::println));
        //sout: 0
        //      1
        //      2
        System.out.println();
    }
}
