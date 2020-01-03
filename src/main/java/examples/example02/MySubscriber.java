package examples.example02;

import rx.Observer;
import rx.Subscriber;

public class MySubscriber implements Observer<Student> {

    @Override
    public void onCompleted() {
        System.out.println("There are all students!");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Ops, error occurred!");
    }

    @Override
    public void onNext(Student student) {
        System.out.println(student);
    }
}
