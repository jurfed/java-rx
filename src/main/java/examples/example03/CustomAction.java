package examples.example03;

import rx.functions.Action1;

public class CustomAction implements Action1<Employee> {

    @Override
    public void call(Employee employee) {
        System.out.println(employee);
    }
}
