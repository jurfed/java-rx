package classes;

import rx.Observable;
import rx.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassesStart {
    public static void main(String[] args) {
        new ClassesStart().run();
    }

    public void run() {

        //filter ***********************************************
        System.out.println("\nfilter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Students one = new Students("Ivan", 1980, "computer");
        Students one2 = new Students("Ivan", 1980, "computer");
        Students two = new Students("Petr", 1990, "coock");
        Students three = new Students("Vasia", 2000, "engener");
        List<Students> students = Arrays.asList(one, one2, two, three);

        Observable<Students> values = Observable.from(students);
        Subscription subscription1 = values.subscribe(new SimpleObserver<Students>());

        //distinct ***********************************************
        System.out.println("\nDistinct !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Subscription subscription2 = values.filter(students1 -> students1.getName().equals("Ivan"))
                .distinct().subscribe(new SimpleAction());

        //distinct 2 ***********************************************
        System.out.println("\ndistinct 2 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<Students> students1 = new ArrayList<>();
        students1.add(new Students("Sharik", 2001, "adsf"));
        students1.add(new Students("Ivanov", 2015, "dfgdsgdf"));
        students1.add(new Students("Sushin", 2011, "dfgfdsg"));
        students1.add(new Students("Sashin", 2011, "dfgfdsg"));

        Observable<Students> observable3 = Observable.from(students1);
        Subscription subscription3 = observable3.distinct(students2 -> students2.getName().charAt(0)).subscribe(new SimpleObserver<>());

        // take and skip
        System.out.println("\n take and skip");

    }

}
