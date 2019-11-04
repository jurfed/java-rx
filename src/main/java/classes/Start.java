package classes;

import rx.Observable;
import rx.Subscription;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Start {
    public static void main(String[] args) {
        new Start().run();
    }

    public void run(){
        Students one = new Students("Ivan", 1980, "computer");
        Students two = new Students("Petr", 1990, "coock");
        Students three = new Students("Vasia", 2000, "engener");
        List<Students> students = Arrays.asList(one,two, three);

        Observable<Students> values = Observable.from(students);
        Subscription subscription1 = values.subscribe(new SimpleObserver<Students>());
        Subscription subscription2 = values.filter(students1 -> students1.getName().equals("Ivan")).subscribe(new SimpleAction());


    }

}
