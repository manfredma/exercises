package rxjava2.ex1;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * @author Manfred since 2019/6/12
 */
public class Main {

    public static void main(String[] args) {
        // 第一步：初始化Observable

        Observable.create(
                (ObservableOnSubscribe<Integer>) e -> {
                    System.out.println("Observable emit 1");
                    e.onNext(1);
                    System.out.println("Observable emit 2");
                    e.onNext(2);
                    System.out.println("Observable emit 3");
                    e.onNext(3);
                    e.onComplete();
                    System.out.println("Observable emit 4");
                    e.onNext(4);
                }).subscribe(new Observer<Integer>() {

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext : value : " + integer);
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
                if (mDisposable.isDisposed()) {
                    System.out.println("onNext : isDisposable  : true");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError : value : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
