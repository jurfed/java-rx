package examples.example02;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

import java.util.Arrays;
import java.util.jar.JarOutputStream;

public class Example02 {

    public Example02() {
        System.out.println("\nExamples 2 !!!!!!!!!!!!!!!!!!!!!");
        MySubscriber mySubscriber = new MySubscriber();

        System.out.println("\nExample 2.1 !!!!!!!!!!!!!!!!!!!!!");

        Student student1 = new Student("Ivan", 23, "doctor");
        Student student2 = new Student("Vasya", 19, "engineer");
        Student student3 = new Student("Petya", 39, "electric");

        Observable<Student> studentObservable = Observable.just(student1, student2, student3);
        Subscription subscription = studentObservable.subscribe(mySubscriber);

//-----------------------------------------------------------------------------------------------
        System.out.println("\nExample 2.2 !!!!!!!!!!!!!!!!!!!!!");
        Iterable<Student> students = Arrays.asList(student1, student2, student3);
        studentObservable = Observable.from(students);

        if(subscription!=null  && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        subscription = studentObservable.subscribe(mySubscriber);

    }


}
