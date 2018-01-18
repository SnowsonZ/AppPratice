package com.example.java.rxjava;

import io.reactivex.Observable;

/**
 * Created by snowson on 18-1-18.
 */

public class RxJavaTest {
    public static void main(String[] args) {
        testRxJava();
    }

    public static void testRxJava() {
        Observable<String> observable = Observable.create(emitter -> {
            emitter.onNext("one");
            emitter.onNext("two");
            emitter.onNext("three");
            emitter.onNext("four");
        });

//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

//        observable.subscribe(observer);
    }
}
