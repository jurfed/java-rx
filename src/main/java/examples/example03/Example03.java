package examples.example03;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

public class Example03 {

    public Example03() {

        Action1<Emitter<Employee>> emitterAction1 = new Action1<Emitter<Employee>>() {
            @Override
            public void call(Emitter<Employee> employeeEmitter) {
                employeeEmitter.onNext(new Employee("Ivan", 35, "policeman"));
                employeeEmitter.onNext(new Employee("Kuzya", 20, "cook"));
                employeeEmitter.onNext(new Employee("Petya", 50, "engineer"));
                employeeEmitter.onNext(new Employee("Marina", 21, "doctor"));
                employeeEmitter.onNext(new Employee("Inna", 40, "Policman"));
            }
        };

        //создаем последовательность
        Observable<Employee> values = Observable.create(emitterAction1, Emitter.BackpressureMode.BUFFER);


/*        Observable<Employee> values = Observable.create(studentEmitter -> {
            studentEmitter.onNext(new Employee("Vasia", 30, "Policeman"));
            studentEmitter.onCompleted();
        });*/

        //подписываемся на последовательность нашим классом Action1
        CustomAction myAction1 = new CustomAction();
        values.subscribe(myAction1);

    }
}
