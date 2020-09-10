package examples.example07_aggregation;

import rx.Observable;

import java.util.ArrayList;

public class Example07_2 {
    //Aggregation to collections
    public Example07_2() {


        Observable<Integer> values = Observable.range(10, 5);
        values
                .reduce(
                        new ArrayList<Integer>(),// насальный список, который клонируется при каждом добавлении значений
                        (acc, value) -> {//acc - это список, склонированный из предыдущей иттерации, к которому добавляется
                            //новые значение, value - добавляемое очередное значение в склонированный список
                            acc.add(value);
                            return acc;
                        })
                .subscribe(v -> System.out.println(v));
        //sout: [10, 11, 12, 13, 14]


//collect
        values.collect(() -> new ArrayList(), (arrayList, integer) -> arrayList.add(integer))
                .subscribe(System.out::println);
        //sout: [10, 11, 12, 13, 14]


//toList
        values
                .toList()
                .subscribe(System.out::println);
        //sout: [10, 11, 12, 13, 14]


//toSortedList
        values
                .toSortedList((i1,i2) -> i2 - i1)
                .subscribe(v -> System.out.println(v));
//sout: [14, 13, 12, 11, 10]



        System.out.println();
    }
}
