package examples.example01;

import rx.functions.Action1;

public class MyAction1 implements Action1<Integer> {

    @Override
    public void call(Integer integer) {
        System.out.println("получено: " + integer);
    }
}
