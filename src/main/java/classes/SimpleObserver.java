package classes;

import rx.Observer;

public class SimpleObserver<Students> implements Observer {

    @Override
    public void onCompleted() {
        System.out.println("SimpleObserver comleted!");
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onNext(Object o) {
        System.out.println("SimpleObserver: " + o);
    }
}
