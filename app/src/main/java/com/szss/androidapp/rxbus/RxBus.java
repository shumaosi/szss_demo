package com.szss.androidapp.rxbus;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by wuwei on 2017/6/23.
 */

public class RxBus {

	private static volatile RxBus mInstance;
	private final Subject<Object> mSubject = PublishSubject.create().toSerialized();
	private Disposable mDispoable;


	private RxBus() {
	}

	public static RxBus getInstance() {
		if (mInstance == null) {
			synchronized (RxBus.class) {
				if (mInstance == null) {
					mInstance = new RxBus();
				}
			}
		}
		return mInstance;
	}


	/**
	 * 发送事件
	 */
	public void send(Object object) {
		mSubject.onNext(object);
	}

	private <T> Observable<T> toObservale(Class<T> classType) {
		return mSubject.ofType(classType);
	}

	/**
	 * 订阅
	 */
	public void subscribe(Class bean, Consumer consumer) {
		mDispoable = toObservale(bean).subscribe(consumer);
	}

	/**
	 * 取消订阅
	 */
	public void unSubscribe() {
		if (mDispoable != null && mDispoable.isDisposed()) {
			mDispoable.dispose();
		}

	}
}
