package rxjava1.ex1;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @author Manfred since 2019/6/14
 */
public class Main {
    public static void main(String[] args) {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("Observer Item: " + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("Observer Completed!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Observer Error!");
            }
        };


        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("Subscriber Item: " + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("Subscriber Completed!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Subscriber Error!");
            }

            @Override
            public void onStart() {
                System.out.println("Subscriber onStart!");
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        observable = observable.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return "map from: " + s;
            }
        });

        observable.subscribe(observer);
        observable.subscribe(subscriber);

    }
}
