package classes;

import rx.functions.Action1;

public class SimpleAction implements Action1 {
    @Override
    public void call(Object o) {
        System.out.println("SimpleAction: " + o);
    }
}
