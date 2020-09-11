package examples.example11_buffer_time;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Example11 {

    public Example11() {
System.out.println("------------------------ buffer");
        Observable.range(0, 10)
                .buffer(4)
                .subscribe(System.out::println);
        //[0, 1, 2, 3]
        //[4, 5, 6, 7]
        //[8, 9]

System.out.println("------------------------ buffer by time");
        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
                .buffer(250, TimeUnit.MILLISECONDS)
                .toBlocking()
                .subscribe(System.out::println);
        //[0, 1]
        //[2, 3, 4]
        //[5, 6]
        //[7, 8]
        //[9]
    }
}
