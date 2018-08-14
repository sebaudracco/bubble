package rx;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import rx.Scheduler.Worker;
import rx.annotations.Beta;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;
import rx.internal.operators.CompletableFlatMapSingleToCompletable;
import rx.internal.operators.OnSubscribeToObservableFuture;
import rx.internal.operators.OperatorDelay;
import rx.internal.operators.OperatorDoOnSubscribe;
import rx.internal.operators.OperatorDoOnUnsubscribe;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import rx.internal.operators.OperatorTimeout;
import rx.internal.operators.SingleDoAfterTerminate;
import rx.internal.operators.SingleDoOnEvent;
import rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther;
import rx.internal.operators.SingleOnSubscribeMap;
import rx.internal.operators.SingleOnSubscribeUsing;
import rx.internal.operators.SingleOperatorOnErrorResumeNext;
import rx.internal.operators.SingleOperatorZip;
import rx.internal.producers.SingleDelayedProducer;
import rx.internal.util.ScalarSynchronousSingle;
import rx.internal.util.UtilityFunctions;
import rx.observers.SafeSubscriber;
import rx.observers.SerializedSubscriber;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;
import rx.singles.BlockingSingle;

public class Single<T> {
    final Observable$OnSubscribe<T> onSubscribe;

    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }

    public interface Transformer<T, R> extends Func1<Single<T>, Single<R>> {
    }

    protected Single(OnSubscribe<T> f) {
        final OnSubscribe<T> g = RxJavaHooks.onCreate((OnSubscribe) f);
        this.onSubscribe = new Observable$OnSubscribe<T>() {
            public void call(final Subscriber<? super T> child) {
                final SingleDelayedProducer<T> producer = new SingleDelayedProducer(child);
                child.setProducer(producer);
                SingleSubscriber<T> ss = new SingleSubscriber<T>() {
                    public void onSuccess(T value) {
                        producer.setValue(value);
                    }

                    public void onError(Throwable error) {
                        child.onError(error);
                    }
                };
                child.add(ss);
                g.call(ss);
            }
        };
    }

    private Single(Observable$OnSubscribe<T> f) {
        this.onSubscribe = RxJavaHooks.onCreate((Observable$OnSubscribe) f);
    }

    public static <T> Single<T> create(OnSubscribe<T> f) {
        return new Single((OnSubscribe) f);
    }

    @Beta
    public final <R> Single<R> lift(final Observable$Operator<? extends R, ? super T> lift) {
        return new Single(new Observable$OnSubscribe<R>() {
            public void call(Subscriber<? super R> o) {
                Observer st;
                try {
                    st = (Subscriber) RxJavaHooks.onSingleLift(lift).call(o);
                    st.onStart();
                    Single.this.onSubscribe.call(st);
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, (Observer) o);
                }
            }
        });
    }

    public <R> Single<R> compose(Transformer<? super T, ? extends R> transformer) {
        return (Single) transformer.call(this);
    }

    private static <T> Observable<T> asObservable(Single<T> t) {
        return Observable.create(t.onSubscribe);
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2) {
        return Observable.concat(asObservable(t1), asObservable(t2));
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3) {
        return Observable.concat(asObservable(t1), asObservable(t2), asObservable(t3));
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4) {
        return Observable.concat(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4));
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5) {
        return Observable.concat(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5));
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6) {
        return Observable.concat(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6));
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6, Single<? extends T> t7) {
        return Observable.concat(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6), asObservable(t7));
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6, Single<? extends T> t7, Single<? extends T> t8) {
        return Observable.concat(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6), asObservable(t7), asObservable(t8));
    }

    public static <T> Observable<T> concat(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6, Single<? extends T> t7, Single<? extends T> t8, Single<? extends T> t9) {
        return Observable.concat(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6), asObservable(t7), asObservable(t8), asObservable(t9));
    }

    public static <T> Single<T> error(final Throwable exception) {
        return create(new OnSubscribe<T>() {
            public void call(SingleSubscriber<? super T> te) {
                te.onError(exception);
            }
        });
    }

    public static <T> Single<T> from(Future<? extends T> future) {
        return new Single(OnSubscribeToObservableFuture.toObservableFuture(future));
    }

    public static <T> Single<T> from(Future<? extends T> future, long timeout, TimeUnit unit) {
        return new Single(OnSubscribeToObservableFuture.toObservableFuture(future, timeout, unit));
    }

    public static <T> Single<T> from(Future<? extends T> future, Scheduler scheduler) {
        return new Single(OnSubscribeToObservableFuture.toObservableFuture(future)).subscribeOn(scheduler);
    }

    public static <T> Single<T> fromCallable(final Callable<? extends T> func) {
        return create(new OnSubscribe<T>() {
            public void call(SingleSubscriber<? super T> singleSubscriber) {
                try {
                    singleSubscriber.onSuccess(func.call());
                } catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    singleSubscriber.onError(t);
                }
            }
        });
    }

    public static <T> Single<T> just(T value) {
        return ScalarSynchronousSingle.create(value);
    }

    public static <T> Single<T> merge(final Single<? extends Single<? extends T>> source) {
        if (source instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) source).scalarFlatMap(UtilityFunctions.identity());
        }
        return create(new OnSubscribe<T>() {
            public void call(final SingleSubscriber<? super T> child) {
                SingleSubscriber parent = new SingleSubscriber<Single<? extends T>>() {
                    public void onSuccess(Single<? extends T> innerSingle) {
                        innerSingle.subscribe(child);
                    }

                    public void onError(Throwable error) {
                        child.onError(error);
                    }
                };
                child.add(parent);
                source.subscribe(parent);
            }
        });
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2) {
        return Observable.merge(asObservable(t1), asObservable(t2));
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3) {
        return Observable.merge(asObservable(t1), asObservable(t2), asObservable(t3));
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4) {
        return Observable.merge(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4));
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5) {
        return Observable.merge(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5));
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6) {
        return Observable.merge(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6));
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6, Single<? extends T> t7) {
        return Observable.merge(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6), asObservable(t7));
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6, Single<? extends T> t7, Single<? extends T> t8) {
        return Observable.merge(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6), asObservable(t7), asObservable(t8));
    }

    public static <T> Observable<T> merge(Single<? extends T> t1, Single<? extends T> t2, Single<? extends T> t3, Single<? extends T> t4, Single<? extends T> t5, Single<? extends T> t6, Single<? extends T> t7, Single<? extends T> t8, Single<? extends T> t9) {
        return Observable.merge(asObservable(t1), asObservable(t2), asObservable(t3), asObservable(t4), asObservable(t5), asObservable(t6), asObservable(t7), asObservable(t8), asObservable(t9));
    }

    public static <T1, T2, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, final Func2<? super T1, ? super T2, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1]);
            }
        });
    }

    public static <T1, T2, T3, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, Single<? extends T3> s3, final Func3<? super T1, ? super T2, ? super T3, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2, s3}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1], args[2]);
            }
        });
    }

    public static <T1, T2, T3, T4, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, Single<? extends T3> s3, Single<? extends T4> s4, final Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2, s3, s4}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1], args[2], args[3]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, Single<? extends T3> s3, Single<? extends T4> s4, Single<? extends T5> s5, final Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2, s3, s4, s5}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1], args[2], args[3], args[4]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, Single<? extends T3> s3, Single<? extends T4> s4, Single<? extends T5> s5, Single<? extends T6> s6, final Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2, s3, s4, s5, s6}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1], args[2], args[3], args[4], args[5]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, Single<? extends T3> s3, Single<? extends T4> s4, Single<? extends T5> s5, Single<? extends T6> s6, Single<? extends T7> s7, final Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2, s3, s4, s5, s6, s7}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, Single<? extends T3> s3, Single<? extends T4> s4, Single<? extends T5> s5, Single<? extends T6> s6, Single<? extends T7> s7, Single<? extends T8> s8, final Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2, s3, s4, s5, s6, s7, s8}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(Single<? extends T1> s1, Single<? extends T2> s2, Single<? extends T3> s3, Single<? extends T4> s4, Single<? extends T5> s5, Single<? extends T6> s6, Single<? extends T7> s7, Single<? extends T8> s8, Single<? extends T9> s9, final Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> zipFunction) {
        return SingleOperatorZip.zip(new Single[]{s1, s2, s3, s4, s5, s6, s7, s8, s9}, new FuncN<R>() {
            public R call(Object... args) {
                return zipFunction.call(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            }
        });
    }

    public static <R> Single<R> zip(Iterable<? extends Single<?>> singles, FuncN<? extends R> zipFunction) {
        return SingleOperatorZip.zip(iterableToArray(singles), zipFunction);
    }

    public final Observable<T> concatWith(Single<? extends T> t1) {
        return concat(this, t1);
    }

    public final <R> Single<R> flatMap(Func1<? super T, ? extends Single<? extends R>> func) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarFlatMap(func);
        }
        return merge(map(func));
    }

    public final <R> Observable<R> flatMapObservable(Func1<? super T, ? extends Observable<? extends R>> func) {
        return Observable.merge(asObservable(map(func)));
    }

    @Beta
    public final Completable flatMapCompletable(Func1<? super T, ? extends Completable> func) {
        return Completable.create(new CompletableFlatMapSingleToCompletable(this, func));
    }

    public final <R> Single<R> map(Func1<? super T, ? extends R> func) {
        return create(new SingleOnSubscribeMap(this, func));
    }

    public final Observable<T> mergeWith(Single<? extends T> t1) {
        return merge(this, t1);
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        return lift(new OperatorObserveOn(scheduler, false));
    }

    public final Single<T> onErrorReturn(Func1<Throwable, ? extends T> resumeFunction) {
        return lift(OperatorOnErrorResumeNextViaFunction.withSingle(resumeFunction));
    }

    @Beta
    public final Single<T> onErrorResumeNext(Single<? extends T> resumeSingleInCaseOfError) {
        return new Single(SingleOperatorOnErrorResumeNext.withOther(this, resumeSingleInCaseOfError));
    }

    @Beta
    public final Single<T> onErrorResumeNext(Func1<Throwable, ? extends Single<? extends T>> resumeFunctionInCaseOfError) {
        return new Single(SingleOperatorOnErrorResumeNext.withFunction(this, resumeFunctionInCaseOfError));
    }

    public final Subscription subscribe() {
        return subscribe(new Subscriber<T>() {
            public final void onCompleted() {
            }

            public final void onError(Throwable e) {
                throw new OnErrorNotImplementedException(e);
            }

            public final void onNext(T t) {
            }
        });
    }

    public final Subscription subscribe(final Action1<? super T> onSuccess) {
        if (onSuccess != null) {
            return subscribe(new Subscriber<T>() {
                public final void onCompleted() {
                }

                public final void onError(Throwable e) {
                    throw new OnErrorNotImplementedException(e);
                }

                public final void onNext(T args) {
                    onSuccess.call(args);
                }
            });
        }
        throw new IllegalArgumentException("onSuccess can not be null");
    }

    public final Subscription subscribe(final Action1<? super T> onSuccess, final Action1<Throwable> onError) {
        if (onSuccess == null) {
            throw new IllegalArgumentException("onSuccess can not be null");
        } else if (onError != null) {
            return subscribe(new Subscriber<T>() {
                public final void onCompleted() {
                }

                public final void onError(Throwable e) {
                    onError.call(e);
                }

                public final void onNext(T args) {
                    onSuccess.call(args);
                }
            });
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            RxJavaHooks.onSingleStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onSingleReturn(subscriber);
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            RxJavaHooks.onSingleError(new RuntimeException("Error occurred attempting to subscribe [" + e.getMessage() + "] and then again while trying to pass to onError.", e2));
        }
    }

    public final Subscription subscribe(final Observer<? super T> observer) {
        if (observer != null) {
            return subscribe(new SingleSubscriber<T>() {
                public void onSuccess(T value) {
                    observer.onNext(value);
                    observer.onCompleted();
                }

                public void onError(Throwable error) {
                    observer.onError(error);
                }
            });
        }
        throw new NullPointerException("observer is null");
    }

    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("observer can not be null");
        } else if (this.onSubscribe == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        } else {
            subscriber.onStart();
            if (!(subscriber instanceof SafeSubscriber)) {
                subscriber = new SafeSubscriber(subscriber);
            }
            try {
                RxJavaHooks.onSingleStart(this, this.onSubscribe).call(subscriber);
                return RxJavaHooks.onSingleReturn(subscriber);
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                RxJavaHooks.onSingleError(new RuntimeException("Error occurred attempting to subscribe [" + e.getMessage() + "] and then again while trying to pass to onError.", e2));
            }
        }
    }

    public final Subscription subscribe(final SingleSubscriber<? super T> te) {
        Subscriber s = new Subscriber<T>() {
            public void onCompleted() {
            }

            public void onError(Throwable e) {
                te.onError(e);
            }

            public void onNext(T t) {
                te.onSuccess(t);
            }
        };
        te.add(s);
        subscribe(s);
        return s;
    }

    public final Single<T> subscribeOn(final Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        return create(new OnSubscribe<T>() {
            public void call(final SingleSubscriber<? super T> t) {
                final Worker w = scheduler.createWorker();
                t.add(w);
                w.schedule(new Action0() {

                    class C70101 extends SingleSubscriber<T> {
                        C70101() {
                        }

                        public void onSuccess(T value) {
                            try {
                                t.onSuccess(value);
                            } finally {
                                w.unsubscribe();
                            }
                        }

                        public void onError(Throwable error) {
                            try {
                                t.onError(error);
                            } finally {
                                w.unsubscribe();
                            }
                        }
                    }

                    public void call() {
                        SingleSubscriber single = new C70101();
                        t.add(single);
                        Single.this.subscribe(single);
                    }
                });
            }
        });
    }

    public final Single<T> takeUntil(final Completable other) {
        return lift(new Observable$Operator<T, T>() {
            public Subscriber<? super T> call(Subscriber<? super T> child) {
                final Subscriber<T> serial = new SerializedSubscriber(child, false);
                final Subscriber<T> main = new Subscriber<T>(false, serial) {
                    public void onNext(T t) {
                        serial.onNext(t);
                    }

                    public void onError(Throwable e) {
                        try {
                            serial.onError(e);
                        } finally {
                            serial.unsubscribe();
                        }
                    }

                    public void onCompleted() {
                        try {
                            serial.onCompleted();
                        } finally {
                            serial.unsubscribe();
                        }
                    }
                };
                CompletableSubscriber so = new CompletableSubscriber() {
                    public void onCompleted() {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    public void onError(Throwable e) {
                        main.onError(e);
                    }

                    public void onSubscribe(Subscription d) {
                        serial.add(d);
                    }
                };
                serial.add(main);
                child.add(serial);
                other.unsafeSubscribe(so);
                return main;
            }
        });
    }

    public final <E> Single<T> takeUntil(final Observable<? extends E> other) {
        return lift(new Observable$Operator<T, T>() {
            public Subscriber<? super T> call(Subscriber<? super T> child) {
                final Subscriber<T> serial = new SerializedSubscriber(child, false);
                final Subscriber<T> main = new Subscriber<T>(false, serial) {
                    public void onNext(T t) {
                        serial.onNext(t);
                    }

                    public void onError(Throwable e) {
                        try {
                            serial.onError(e);
                        } finally {
                            serial.unsubscribe();
                        }
                    }

                    public void onCompleted() {
                        try {
                            serial.onCompleted();
                        } finally {
                            serial.unsubscribe();
                        }
                    }
                };
                Subscriber<E> so = new Subscriber<E>() {
                    public void onCompleted() {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    public void onError(Throwable e) {
                        main.onError(e);
                    }

                    public void onNext(E e) {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                };
                serial.add(main);
                serial.add(so);
                child.add(serial);
                other.unsafeSubscribe(so);
                return main;
            }
        });
    }

    public final <E> Single<T> takeUntil(final Single<? extends E> other) {
        return lift(new Observable$Operator<T, T>() {
            public Subscriber<? super T> call(Subscriber<? super T> child) {
                final Subscriber<T> serial = new SerializedSubscriber(child, false);
                final Subscriber<T> main = new Subscriber<T>(false, serial) {
                    public void onNext(T t) {
                        serial.onNext(t);
                    }

                    public void onError(Throwable e) {
                        try {
                            serial.onError(e);
                        } finally {
                            serial.unsubscribe();
                        }
                    }

                    public void onCompleted() {
                        try {
                            serial.onCompleted();
                        } finally {
                            serial.unsubscribe();
                        }
                    }
                };
                SingleSubscriber so = new SingleSubscriber<E>() {
                    public void onSuccess(E e) {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    public void onError(Throwable e) {
                        main.onError(e);
                    }
                };
                serial.add(main);
                serial.add(so);
                child.add(serial);
                other.subscribe(so);
                return main;
            }
        });
    }

    @Experimental
    public final <R> R to(Func1<? super Single<T>, R> converter) {
        return converter.call(this);
    }

    public final Observable<T> toObservable() {
        return asObservable(this);
    }

    @Beta
    public final Completable toCompletable() {
        return Completable.fromSingle(this);
    }

    public final Single<T> timeout(long timeout, TimeUnit timeUnit) {
        return timeout(timeout, timeUnit, null, Schedulers.computation());
    }

    public final Single<T> timeout(long timeout, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout(timeout, timeUnit, null, scheduler);
    }

    public final Single<T> timeout(long timeout, TimeUnit timeUnit, Single<? extends T> other) {
        return timeout(timeout, timeUnit, other, Schedulers.computation());
    }

    public final Single<T> timeout(long timeout, TimeUnit timeUnit, Single<? extends T> other, Scheduler scheduler) {
        if (other == null) {
            other = error(new TimeoutException());
        }
        return lift(new OperatorTimeout(timeout, timeUnit, asObservable(other), scheduler));
    }

    @Beta
    public final BlockingSingle<T> toBlocking() {
        return BlockingSingle.from(this);
    }

    public final <T2, R> Single<R> zipWith(Single<? extends T2> other, Func2<? super T, ? super T2, ? extends R> zipFunction) {
        return zip(this, other, zipFunction);
    }

    @Beta
    public final Single<T> doOnError(final Action1<Throwable> onError) {
        if (onError != null) {
            return create(new SingleDoOnEvent(this, Actions.empty(), new Action1<Throwable>() {
                public void call(Throwable throwable) {
                    onError.call(throwable);
                }
            }));
        }
        throw new IllegalArgumentException("onError is null");
    }

    @Experimental
    public final Single<T> doOnEach(final Action1<Notification<? extends T>> onNotification) {
        if (onNotification != null) {
            return create(new SingleDoOnEvent(this, new Action1<T>() {
                public void call(T t) {
                    onNotification.call(Notification.createOnNext(t));
                }
            }, new Action1<Throwable>() {
                public void call(Throwable throwable) {
                    onNotification.call(Notification.createOnError(throwable));
                }
            }));
        }
        throw new IllegalArgumentException("onNotification is null");
    }

    @Experimental
    public final Single<T> doOnSuccess(final Action1<? super T> onSuccess) {
        if (onSuccess != null) {
            return create(new SingleDoOnEvent(this, new Action1<T>() {
                public void call(T t) {
                    onSuccess.call(t);
                }
            }, new Action1<Throwable>() {
                public void call(Throwable throwable) {
                }
            }));
        }
        throw new IllegalArgumentException("onSuccess is null");
    }

    @Beta
    public final Single<T> doOnSubscribe(Action0 subscribe) {
        return lift(new OperatorDoOnSubscribe(subscribe));
    }

    @Beta
    public final Single<T> delay(long delay, TimeUnit unit, Scheduler scheduler) {
        return lift(new OperatorDelay(delay, unit, scheduler));
    }

    @Beta
    public final Single<T> delay(long delay, TimeUnit unit) {
        return delay(delay, unit, Schedulers.computation());
    }

    @Beta
    public static <T> Single<T> defer(final Callable<Single<T>> singleFactory) {
        return create(new OnSubscribe<T>() {
            public void call(SingleSubscriber<? super T> singleSubscriber) {
                try {
                    ((Single) singleFactory.call()).subscribe((SingleSubscriber) singleSubscriber);
                } catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    singleSubscriber.onError(t);
                }
            }
        });
    }

    @Beta
    public final Single<T> doOnUnsubscribe(Action0 action) {
        return lift(new OperatorDoOnUnsubscribe(action));
    }

    @Beta
    public final Single<T> doAfterTerminate(Action0 action) {
        return create(new SingleDoAfterTerminate(this, action));
    }

    static <T> Single<? extends T>[] iterableToArray(Iterable<? extends Single<? extends T>> singlesIterable) {
        if (singlesIterable instanceof Collection) {
            Collection<? extends Single<? extends T>> list = (Collection) singlesIterable;
            return (Single[]) list.toArray(new Single[list.size()]);
        }
        Single<? extends T>[] tempArray = new Single[8];
        int count = 0;
        for (Single<? extends T> s : singlesIterable) {
            if (count == tempArray.length) {
                Single<? extends T>[] sb = new Single[((count >> 2) + count)];
                System.arraycopy(tempArray, 0, sb, 0, count);
                tempArray = sb;
            }
            tempArray[count] = s;
            count++;
        }
        if (tempArray.length == count) {
            return tempArray;
        }
        Single<? extends T>[] singlesArray = new Single[count];
        System.arraycopy(tempArray, 0, singlesArray, 0, count);
        return singlesArray;
    }

    public final Single<T> retry() {
        return toObservable().retry().toSingle();
    }

    public final Single<T> retry(long count) {
        return toObservable().retry(count).toSingle();
    }

    public final Single<T> retry(Func2<Integer, Throwable, Boolean> predicate) {
        return toObservable().retry(predicate).toSingle();
    }

    public final Single<T> retryWhen(Func1<Observable<? extends Throwable>, ? extends Observable<?>> notificationHandler) {
        return toObservable().retryWhen(notificationHandler).toSingle();
    }

    @Beta
    public static <T, Resource> Single<T> using(Func0<Resource> resourceFactory, Func1<? super Resource, ? extends Single<? extends T>> singleFactory, Action1<? super Resource> disposeAction) {
        return using(resourceFactory, singleFactory, disposeAction, false);
    }

    @Beta
    public static <T, Resource> Single<T> using(Func0<Resource> resourceFactory, Func1<? super Resource, ? extends Single<? extends T>> singleFactory, Action1<? super Resource> disposeAction, boolean disposeEagerly) {
        if (resourceFactory == null) {
            throw new NullPointerException("resourceFactory is null");
        } else if (singleFactory == null) {
            throw new NullPointerException("singleFactory is null");
        } else if (disposeAction != null) {
            return create(new SingleOnSubscribeUsing(resourceFactory, singleFactory, disposeAction, disposeEagerly));
        } else {
            throw new NullPointerException("disposeAction is null");
        }
    }

    @Beta
    public final Single<T> delaySubscription(Observable<?> other) {
        if (other != null) {
            return create(new SingleOnSubscribeDelaySubscriptionOther(this, other));
        }
        throw new NullPointerException();
    }
}
