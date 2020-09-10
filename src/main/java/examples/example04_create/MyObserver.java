package examples.example04_create;

import rx.Observer;

public class MyObserver implements Observer<People> {
    @Override
    public void onCompleted() {
        System.out.println("конец!");
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onNext(People people) {
        System.out.println("человек: " + people);
    }
}
