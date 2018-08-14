package rx;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Scheduler.Worker;
import rx.annotations.Beta;
import rx.annotations.Experimental;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.CompletableFromEmitter;
import rx.internal.operators.CompletableOnSubscribeConcat;
import rx.internal.operators.CompletableOnSubscribeConcatArray;
import rx.internal.operators.CompletableOnSubscribeConcatIterable;
import rx.internal.operators.CompletableOnSubscribeMerge;
import rx.internal.operators.CompletableOnSubscribeMergeArray;
import rx.internal.operators.CompletableOnSubscribeMergeDelayErrorArray;
import rx.internal.operators.CompletableOnSubscribeMergeDelayErrorIterable;
import rx.internal.operators.CompletableOnSubscribeMergeIterable;
import rx.internal.operators.CompletableOnSubscribeTimeout;
import rx.internal.util.SubscriptionList;
import rx.internal.util.UtilityFunctions;
import rx.observers.SafeCompletableSubscriber;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

@Beta
public class Completable {
    static final Completable COMPLETE = new Completable(new C69861(), false);
    static final Completable NEVER = new Completable(new C69932(), false);
    private final OnSubscribe onSubscribe;

    public interface OnSubscribe extends Action1<CompletableSubscriber> {
    }

    static class C69861 implements OnSubscribe {
        C69861() {
        }

        public void call(CompletableSubscriber s) {
            s.onSubscribe(Subscriptions.unsubscribed());
            s.onCompleted();
        }
    }

    static class C69932 implements OnSubscribe {
        C69932() {
        }

        public void call(CompletableSubscriber s) {
            s.onSubscribe(Subscriptions.unsubscribed());
        }
    }

    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber> {
    }

    public interface Transformer extends Func1<Completable, Completable> {
    }

    public static Completable amb(final Completable... sources) {
        requireNonNull(sources);
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return sources[0];
        }
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final CompositeSubscription set = new CompositeSubscription();
                s.onSubscribe(set);
                final AtomicBoolean once = new AtomicBoolean();
                CompletableSubscriber inner = new CompletableSubscriber() {
                    public void onCompleted() {
                        if (once.compareAndSet(false, true)) {
                            set.unsubscribe();
                            s.onCompleted();
                        }
                    }

                    public void onError(Throwable e) {
                        if (once.compareAndSet(false, true)) {
                            set.unsubscribe();
                            s.onError(e);
                            return;
                        }
                        RxJavaHooks.onError(e);
                    }

                    public void onSubscribe(Subscription d) {
                        set.add(d);
                    }
                };
                Completable[] arr$ = sources;
                int len$ = arr$.length;
                int i$ = 0;
                while (i$ < len$) {
                    Completable c = arr$[i$];
                    if (!set.isUnsubscribed()) {
                        if (c == null) {
                            NullPointerException npe = new NullPointerException("One of the sources is null");
                            if (once.compareAndSet(false, true)) {
                                set.unsubscribe();
                                s.onError(npe);
                                return;
                            }
                            RxJavaHooks.onError(npe);
                            return;
                        } else if (!once.get() && !set.isUnsubscribed()) {
                            c.unsafeSubscribe(inner);
                            i$++;
                        } else {
                            return;
                        }
                    }
                    return;
                }
            }
        });
    }

    public static Completable amb(final Iterable<? extends Completable> sources) {
        requireNonNull(sources);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final CompositeSubscription set = new CompositeSubscription();
                s.onSubscribe(set);
                try {
                    Iterator<? extends Completable> it = sources.iterator();
                    if (it == null) {
                        s.onError(new NullPointerException("The iterator returned is null"));
                        return;
                    }
                    boolean empty = true;
                    final AtomicBoolean once = new AtomicBoolean();
                    CompletableSubscriber inner = new CompletableSubscriber() {
                        public void onCompleted() {
                            if (once.compareAndSet(false, true)) {
                                set.unsubscribe();
                                s.onCompleted();
                            }
                        }

                        public void onError(Throwable e) {
                            if (once.compareAndSet(false, true)) {
                                set.unsubscribe();
                                s.onError(e);
                                return;
                            }
                            RxJavaHooks.onError(e);
                        }

                        public void onSubscribe(Subscription d) {
                            set.add(d);
                        }
                    };
                    while (!once.get() && !set.isUnsubscribed()) {
                        try {
                            if (it.hasNext()) {
                                empty = false;
                                if (!once.get() && !set.isUnsubscribed()) {
                                    try {
                                        Completable c = (Completable) it.next();
                                        if (c == null) {
                                            NullPointerException npe = new NullPointerException("One of the sources is null");
                                            if (once.compareAndSet(false, true)) {
                                                set.unsubscribe();
                                                s.onError(npe);
                                                return;
                                            }
                                            RxJavaHooks.onError(npe);
                                            return;
                                        } else if (!once.get() && !set.isUnsubscribed()) {
                                            c.unsafeSubscribe(inner);
                                        } else {
                                            return;
                                        }
                                    } catch (Throwable e) {
                                        if (once.compareAndSet(false, true)) {
                                            set.unsubscribe();
                                            s.onError(e);
                                            return;
                                        }
                                        RxJavaHooks.onError(e);
                                        return;
                                    }
                                }
                                return;
                            } else if (empty) {
                                s.onCompleted();
                                return;
                            } else {
                                return;
                            }
                        } catch (Throwable e2) {
                            if (once.compareAndSet(false, true)) {
                                set.unsubscribe();
                                s.onError(e2);
                                return;
                            }
                            RxJavaHooks.onError(e2);
                            return;
                        }
                    }
                } catch (Throwable e22) {
                    s.onError(e22);
                }
            }
        });
    }

    public static Completable complete() {
        OnSubscribe cos = RxJavaHooks.onCreate(COMPLETE.onSubscribe);
        if (cos == COMPLETE.onSubscribe) {
            return COMPLETE;
        }
        return new Completable(cos, false);
    }

    public static Completable concat(Completable... sources) {
        requireNonNull(sources);
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return sources[0];
        }
        return create(new CompletableOnSubscribeConcatArray(sources));
    }

    public static Completable concat(Iterable<? extends Completable> sources) {
        requireNonNull(sources);
        return create(new CompletableOnSubscribeConcatIterable(sources));
    }

    public static Completable concat(Observable<? extends Completable> sources) {
        return concat(sources, 2);
    }

    public static Completable concat(Observable<? extends Completable> sources, int prefetch) {
        requireNonNull(sources);
        if (prefetch >= 1) {
            return create(new CompletableOnSubscribeConcat(sources, prefetch));
        }
        throw new IllegalArgumentException("prefetch > 0 required but it was " + prefetch);
    }

    public static Completable create(OnSubscribe onSubscribe) {
        requireNonNull(onSubscribe);
        try {
            return new Completable(onSubscribe);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            RxJavaHooks.onError(ex2);
            NullPointerException toNpe = toNpe(ex2);
        }
    }

    public static Completable defer(final Func0<? extends Completable> completableFunc0) {
        requireNonNull(completableFunc0);
        return create(new OnSubscribe() {
            public void call(CompletableSubscriber s) {
                try {
                    Completable c = (Completable) completableFunc0.call();
                    if (c == null) {
                        s.onSubscribe(Subscriptions.unsubscribed());
                        s.onError(new NullPointerException("The completable returned is null"));
                        return;
                    }
                    c.unsafeSubscribe(s);
                } catch (Throwable e) {
                    s.onSubscribe(Subscriptions.unsubscribed());
                    s.onError(e);
                }
            }
        });
    }

    public static Completable error(final Func0<? extends Throwable> errorFunc0) {
        requireNonNull(errorFunc0);
        return create(new OnSubscribe() {
            public void call(CompletableSubscriber s) {
                Throwable error;
                s.onSubscribe(Subscriptions.unsubscribed());
                try {
                    error = (Throwable) errorFunc0.call();
                } catch (Throwable e) {
                    error = e;
                }
                if (error == null) {
                    error = new NullPointerException("The error supplied is null");
                }
                s.onError(error);
            }
        });
    }

    public static Completable error(final Throwable error) {
        requireNonNull(error);
        return create(new OnSubscribe() {
            public void call(CompletableSubscriber s) {
                s.onSubscribe(Subscriptions.unsubscribed());
                s.onError(error);
            }
        });
    }

    public static Completable fromAction(final Action0 action) {
        requireNonNull(action);
        return create(new OnSubscribe() {
            public void call(CompletableSubscriber s) {
                BooleanSubscription bs = new BooleanSubscription();
                s.onSubscribe(bs);
                try {
                    action.call();
                    if (!bs.isUnsubscribed()) {
                        s.onCompleted();
                    }
                } catch (Throwable e) {
                    if (!bs.isUnsubscribed()) {
                        s.onError(e);
                    }
                }
            }
        });
    }

    public static Completable fromCallable(final Callable<?> callable) {
        requireNonNull(callable);
        return create(new OnSubscribe() {
            public void call(CompletableSubscriber s) {
                BooleanSubscription bs = new BooleanSubscription();
                s.onSubscribe(bs);
                try {
                    callable.call();
                    if (!bs.isUnsubscribed()) {
                        s.onCompleted();
                    }
                } catch (Throwable e) {
                    if (!bs.isUnsubscribed()) {
                        s.onError(e);
                    }
                }
            }
        });
    }

    @Experimental
    public static Completable fromEmitter(Action1<CompletableEmitter> producer) {
        return create(new CompletableFromEmitter(producer));
    }

    public static Completable fromFuture(Future<?> future) {
        requireNonNull(future);
        return fromObservable(Observable.from(future));
    }

    public static Completable fromObservable(final Observable<?> flowable) {
        requireNonNull(flowable);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber cs) {
                Subscriber<Object> subscriber = new Subscriber<Object>() {
                    public void onCompleted() {
                        cs.onCompleted();
                    }

                    public void onError(Throwable t) {
                        cs.onError(t);
                    }

                    public void onNext(Object t) {
                    }
                };
                cs.onSubscribe(subscriber);
                flowable.unsafeSubscribe(subscriber);
            }
        });
    }

    public static Completable fromSingle(final Single<?> single) {
        requireNonNull(single);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                SingleSubscriber te = new SingleSubscriber<Object>() {
                    public void onError(Throwable e) {
                        s.onError(e);
                    }

                    public void onSuccess(Object value) {
                        s.onCompleted();
                    }
                };
                s.onSubscribe(te);
                single.subscribe(te);
            }
        });
    }

    public static Completable merge(Completable... sources) {
        requireNonNull(sources);
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return sources[0];
        }
        return create(new CompletableOnSubscribeMergeArray(sources));
    }

    public static Completable merge(Iterable<? extends Completable> sources) {
        requireNonNull(sources);
        return create(new CompletableOnSubscribeMergeIterable(sources));
    }

    public static Completable merge(Observable<? extends Completable> sources) {
        return merge0(sources, Integer.MAX_VALUE, false);
    }

    public static Completable merge(Observable<? extends Completable> sources, int maxConcurrency) {
        return merge0(sources, maxConcurrency, false);
    }

    protected static Completable merge0(Observable<? extends Completable> sources, int maxConcurrency, boolean delayErrors) {
        requireNonNull(sources);
        if (maxConcurrency >= 1) {
            return create(new CompletableOnSubscribeMerge(sources, maxConcurrency, delayErrors));
        }
        throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + maxConcurrency);
    }

    public static Completable mergeDelayError(Completable... sources) {
        requireNonNull(sources);
        return create(new CompletableOnSubscribeMergeDelayErrorArray(sources));
    }

    public static Completable mergeDelayError(Iterable<? extends Completable> sources) {
        requireNonNull(sources);
        return create(new CompletableOnSubscribeMergeDelayErrorIterable(sources));
    }

    public static Completable mergeDelayError(Observable<? extends Completable> sources) {
        return merge0(sources, Integer.MAX_VALUE, true);
    }

    public static Completable mergeDelayError(Observable<? extends Completable> sources, int maxConcurrency) {
        return merge0(sources, maxConcurrency, true);
    }

    public static Completable never() {
        OnSubscribe cos = RxJavaHooks.onCreate(NEVER.onSubscribe);
        if (cos == NEVER.onSubscribe) {
            return NEVER;
        }
        return new Completable(cos, false);
    }

    static <T> T requireNonNull(T o) {
        if (o != null) {
            return o;
        }
        throw new NullPointerException();
    }

    public static Completable timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    public static Completable timer(final long delay, final TimeUnit unit, final Scheduler scheduler) {
        requireNonNull(unit);
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                MultipleAssignmentSubscription mad = new MultipleAssignmentSubscription();
                s.onSubscribe(mad);
                if (!mad.isUnsubscribed()) {
                    final Worker w = scheduler.createWorker();
                    mad.set(w);
                    w.schedule(new Action0() {
                        public void call() {
                            try {
                                s.onCompleted();
                            } finally {
                                w.unsubscribe();
                            }
                        }
                    }, delay, unit);
                }
            }
        });
    }

    static NullPointerException toNpe(Throwable ex) {
        NullPointerException npe = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        npe.initCause(ex);
        return npe;
    }

    public static <R> Completable using(Func0<R> resourceFunc0, Func1<? super R, ? extends Completable> completableFunc1, Action1<? super R> disposer) {
        return using(resourceFunc0, completableFunc1, disposer, true);
    }

    public static <R> Completable using(final Func0<R> resourceFunc0, final Func1<? super R, ? extends Completable> completableFunc1, final Action1<? super R> disposer, final boolean eager) {
        requireNonNull(resourceFunc0);
        requireNonNull(completableFunc1);
        requireNonNull(disposer);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                try {
                    final R resource = resourceFunc0.call();
                    try {
                        Completable cs = (Completable) completableFunc1.call(resource);
                        if (cs == null) {
                            try {
                                disposer.call(resource);
                                s.onSubscribe(Subscriptions.unsubscribed());
                                s.onError(new NullPointerException("The completable supplied is null"));
                                return;
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                s.onSubscribe(Subscriptions.unsubscribed());
                                s.onError(new CompositeException(Arrays.asList(new Throwable[]{new NullPointerException("The completable supplied is null"), ex})));
                                return;
                            }
                        }
                        final AtomicBoolean once = new AtomicBoolean();
                        cs.unsafeSubscribe(new CompletableSubscriber() {
                            Subscription f12670d;

                            class C69791 implements Action0 {
                                C69791() {
                                }

                                public void call() {
                                    C69801.this.dispose();
                                }
                            }

                            void dispose() {
                                this.f12670d.unsubscribe();
                                if (once.compareAndSet(false, true)) {
                                    try {
                                        disposer.call(resource);
                                    } catch (Throwable ex) {
                                        RxJavaHooks.onError(ex);
                                    }
                                }
                            }

                            public void onCompleted() {
                                if (eager && once.compareAndSet(false, true)) {
                                    try {
                                        disposer.call(resource);
                                    } catch (Throwable ex) {
                                        s.onError(ex);
                                        return;
                                    }
                                }
                                s.onCompleted();
                                if (!eager) {
                                    dispose();
                                }
                            }

                            public void onError(Throwable e) {
                                if (eager && once.compareAndSet(false, true)) {
                                    try {
                                        disposer.call(resource);
                                    } catch (Throwable ex) {
                                        e = new CompositeException(Arrays.asList(new Throwable[]{e, ex}));
                                    }
                                }
                                s.onError(e);
                                if (!eager) {
                                    dispose();
                                }
                            }

                            public void onSubscribe(Subscription d) {
                                this.f12670d = d;
                                s.onSubscribe(Subscriptions.create(new C69791()));
                            }
                        });
                    } catch (Throwable ex2) {
                        Exceptions.throwIfFatal(e);
                        Exceptions.throwIfFatal(ex2);
                        s.onSubscribe(Subscriptions.unsubscribed());
                        s.onError(new CompositeException(Arrays.asList(new Throwable[]{e, ex2})));
                    }
                } catch (Throwable e) {
                    s.onSubscribe(Subscriptions.unsubscribed());
                    s.onError(e);
                }
            }
        });
    }

    protected Completable(OnSubscribe onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }

    protected Completable(OnSubscribe onSubscribe, boolean useHook) {
        if (useHook) {
            onSubscribe = RxJavaHooks.onCreate(onSubscribe);
        }
        this.onSubscribe = onSubscribe;
    }

    public final Completable ambWith(Completable other) {
        requireNonNull(other);
        return amb(this, other);
    }

    public final void await() {
        final CountDownLatch cdl = new CountDownLatch(1);
        final Throwable[] err = new Throwable[1];
        unsafeSubscribe(new CompletableSubscriber() {
            public void onCompleted() {
                cdl.countDown();
            }

            public void onError(Throwable e) {
                err[0] = e;
                cdl.countDown();
            }

            public void onSubscribe(Subscription d) {
            }
        });
        if (cdl.getCount() != 0) {
            try {
                cdl.await();
                if (err[0] != null) {
                    Exceptions.propagate(err[0]);
                }
            } catch (InterruptedException ex) {
                throw Exceptions.propagate(ex);
            }
        } else if (err[0] != null) {
            Exceptions.propagate(err[0]);
        }
    }

    public final boolean await(long timeout, TimeUnit unit) {
        boolean z = true;
        requireNonNull(unit);
        final CountDownLatch cdl = new CountDownLatch(1);
        final Throwable[] err = new Throwable[1];
        unsafeSubscribe(new CompletableSubscriber() {
            public void onCompleted() {
                cdl.countDown();
            }

            public void onError(Throwable e) {
                err[0] = e;
                cdl.countDown();
            }

            public void onSubscribe(Subscription d) {
            }
        });
        if (cdl.getCount() != 0) {
            try {
                z = cdl.await(timeout, unit);
                if (z && err[0] != null) {
                    Exceptions.propagate(err[0]);
                }
            } catch (InterruptedException ex) {
                throw Exceptions.propagate(ex);
            }
        } else if (err[0] != null) {
            Exceptions.propagate(err[0]);
        }
        return z;
    }

    public final Completable compose(Transformer transformer) {
        return (Completable) to(transformer);
    }

    public final <T> Observable<T> andThen(Observable<T> next) {
        requireNonNull(next);
        return next.delaySubscription(toObservable());
    }

    public final <T> Single<T> andThen(Single<T> next) {
        requireNonNull(next);
        return next.delaySubscription(toObservable());
    }

    public final Completable andThen(Completable next) {
        return concatWith(next);
    }

    public final Completable concatWith(Completable other) {
        requireNonNull(other);
        return concat(this, other);
    }

    public final Completable delay(long delay, TimeUnit unit) {
        return delay(delay, unit, Schedulers.computation(), false);
    }

    public final Completable delay(long delay, TimeUnit unit, Scheduler scheduler) {
        return delay(delay, unit, scheduler, false);
    }

    public final Completable delay(long delay, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        requireNonNull(unit);
        requireNonNull(scheduler);
        final Scheduler scheduler2 = scheduler;
        final long j = delay;
        final TimeUnit timeUnit = unit;
        final boolean z = delayError;
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final CompositeSubscription set = new CompositeSubscription();
                final Worker w = scheduler2.createWorker();
                set.add(w);
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {

                    class C69811 implements Action0 {
                        C69811() {
                        }

                        public void call() {
                            try {
                                s.onCompleted();
                            } finally {
                                w.unsubscribe();
                            }
                        }
                    }

                    public void onCompleted() {
                        set.add(w.schedule(new C69811(), j, timeUnit));
                    }

                    public void onError(final Throwable e) {
                        if (z) {
                            set.add(w.schedule(new Action0() {
                                public void call() {
                                    try {
                                        s.onError(e);
                                    } finally {
                                        w.unsubscribe();
                                    }
                                }
                            }, j, timeUnit));
                        } else {
                            s.onError(e);
                        }
                    }

                    public void onSubscribe(Subscription d) {
                        set.add(d);
                        s.onSubscribe(set);
                    }
                });
            }
        });
    }

    public final Completable doOnCompleted(Action0 onCompleted) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), onCompleted, Actions.empty(), Actions.empty());
    }

    public final Completable doOnEach(final Action1<Notification<Object>> onNotification) {
        if (onNotification != null) {
            return doOnLifecycle(Actions.empty(), new Action1<Throwable>() {
                public void call(Throwable throwable) {
                    onNotification.call(Notification.createOnError(throwable));
                }
            }, new Action0() {
                public void call() {
                    onNotification.call(Notification.createOnCompleted());
                }
            }, Actions.empty(), Actions.empty());
        }
        throw new IllegalArgumentException("onNotification is null");
    }

    public final Completable doOnUnsubscribe(Action0 onUnsubscribe) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), Actions.empty(), Actions.empty(), onUnsubscribe);
    }

    public final Completable doOnError(Action1<? super Throwable> onError) {
        return doOnLifecycle(Actions.empty(), onError, Actions.empty(), Actions.empty(), Actions.empty());
    }

    protected final Completable doOnLifecycle(Action1<? super Subscription> onSubscribe, Action1<? super Throwable> onError, Action0 onComplete, Action0 onAfterComplete, Action0 onUnsubscribe) {
        requireNonNull(onSubscribe);
        requireNonNull(onError);
        requireNonNull(onComplete);
        requireNonNull(onAfterComplete);
        requireNonNull(onUnsubscribe);
        final Action0 action0 = onComplete;
        final Action0 action02 = onAfterComplete;
        final Action1<? super Throwable> action1 = onError;
        final Action1<? super Subscription> action12 = onSubscribe;
        final Action0 action03 = onUnsubscribe;
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    public void onCompleted() {
                        try {
                            action0.call();
                            s.onCompleted();
                            try {
                                action02.call();
                            } catch (Throwable e) {
                                RxJavaHooks.onError(e);
                            }
                        } catch (Throwable e2) {
                            s.onError(e2);
                        }
                    }

                    public void onError(Throwable e) {
                        try {
                            action1.call(e);
                        } catch (Throwable ex) {
                            e = new CompositeException(Arrays.asList(new Throwable[]{e, ex}));
                        }
                        s.onError(e);
                    }

                    public void onSubscribe(final Subscription d) {
                        try {
                            action12.call(d);
                            s.onSubscribe(Subscriptions.create(new Action0() {
                                public void call() {
                                    try {
                                        action03.call();
                                    } catch (Throwable e) {
                                        RxJavaHooks.onError(e);
                                    }
                                    d.unsubscribe();
                                }
                            }));
                        } catch (Throwable ex) {
                            d.unsubscribe();
                            s.onSubscribe(Subscriptions.unsubscribed());
                            s.onError(ex);
                        }
                    }
                });
            }
        });
    }

    public final Completable doOnSubscribe(Action1<? super Subscription> onSubscribe) {
        return doOnLifecycle(onSubscribe, Actions.empty(), Actions.empty(), Actions.empty(), Actions.empty());
    }

    public final Completable doOnTerminate(final Action0 onTerminate) {
        return doOnLifecycle(Actions.empty(), new Action1<Throwable>() {
            public void call(Throwable e) {
                onTerminate.call();
            }
        }, onTerminate, Actions.empty(), Actions.empty());
    }

    public final Completable doAfterTerminate(Action0 onAfterComplete) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), Actions.empty(), onAfterComplete, Actions.empty());
    }

    public final Throwable get() {
        final CountDownLatch cdl = new CountDownLatch(1);
        final Throwable[] err = new Throwable[1];
        unsafeSubscribe(new CompletableSubscriber() {
            public void onCompleted() {
                cdl.countDown();
            }

            public void onError(Throwable e) {
                err[0] = e;
                cdl.countDown();
            }

            public void onSubscribe(Subscription d) {
            }
        });
        if (cdl.getCount() == 0) {
            return err[0];
        }
        try {
            cdl.await();
            return err[0];
        } catch (InterruptedException ex) {
            throw Exceptions.propagate(ex);
        }
    }

    public final Throwable get(long timeout, TimeUnit unit) {
        requireNonNull(unit);
        final CountDownLatch cdl = new CountDownLatch(1);
        final Throwable[] err = new Throwable[1];
        unsafeSubscribe(new CompletableSubscriber() {
            public void onCompleted() {
                cdl.countDown();
            }

            public void onError(Throwable e) {
                err[0] = e;
                cdl.countDown();
            }

            public void onSubscribe(Subscription d) {
            }
        });
        if (cdl.getCount() == 0) {
            return err[0];
        }
        try {
            if (cdl.await(timeout, unit)) {
                return err[0];
            }
            Exceptions.propagate(new TimeoutException());
            return null;
        } catch (InterruptedException ex) {
            throw Exceptions.propagate(ex);
        }
    }

    public final Completable lift(final Operator onLift) {
        requireNonNull(onLift);
        return create(new OnSubscribe() {
            public void call(CompletableSubscriber s) {
                try {
                    Completable.this.unsafeSubscribe((CompletableSubscriber) RxJavaHooks.onCompletableLift(onLift).call(s));
                } catch (NullPointerException ex) {
                    throw ex;
                } catch (Throwable ex2) {
                    NullPointerException toNpe = Completable.toNpe(ex2);
                }
            }
        });
    }

    public final Completable mergeWith(Completable other) {
        requireNonNull(other);
        return merge(this, other);
    }

    public final Completable observeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final SubscriptionList ad = new SubscriptionList();
                final Worker w = scheduler.createWorker();
                ad.add(w);
                s.onSubscribe(ad);
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {

                    class C69871 implements Action0 {
                        C69871() {
                        }

                        public void call() {
                            try {
                                s.onCompleted();
                            } finally {
                                ad.unsubscribe();
                            }
                        }
                    }

                    public void onCompleted() {
                        w.schedule(new C69871());
                    }

                    public void onError(final Throwable e) {
                        w.schedule(new Action0() {
                            public void call() {
                                try {
                                    s.onError(e);
                                } finally {
                                    ad.unsubscribe();
                                }
                            }
                        });
                    }

                    public void onSubscribe(Subscription d) {
                        ad.add(d);
                    }
                });
            }
        });
    }

    public final Completable onErrorComplete() {
        return onErrorComplete(UtilityFunctions.alwaysTrue());
    }

    public final Completable onErrorComplete(final Func1<? super Throwable, Boolean> predicate) {
        requireNonNull(predicate);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    public void onCompleted() {
                        s.onCompleted();
                    }

                    public void onError(Throwable e) {
                        boolean b;
                        try {
                            b = ((Boolean) predicate.call(e)).booleanValue();
                        } catch (Throwable ex) {
                            Exceptions.throwIfFatal(ex);
                            b = false;
                            e = new CompositeException(Arrays.asList(new Throwable[]{e, ex}));
                        }
                        if (b) {
                            s.onCompleted();
                        } else {
                            s.onError(e);
                        }
                    }

                    public void onSubscribe(Subscription d) {
                        s.onSubscribe(d);
                    }
                });
            }
        });
    }

    public final Completable onErrorResumeNext(final Func1<? super Throwable, ? extends Completable> errorMapper) {
        requireNonNull(errorMapper);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final SerialSubscription sd = new SerialSubscription();
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {

                    class C69911 implements CompletableSubscriber {
                        C69911() {
                        }

                        public void onCompleted() {
                            s.onCompleted();
                        }

                        public void onError(Throwable e) {
                            s.onError(e);
                        }

                        public void onSubscribe(Subscription d) {
                            sd.set(d);
                        }
                    }

                    public void onCompleted() {
                        s.onCompleted();
                    }

                    public void onError(Throwable e) {
                        Throwable e2;
                        try {
                            Completable c = (Completable) errorMapper.call(e);
                            if (c == null) {
                                NullPointerException npe = new NullPointerException("The completable returned is null");
                                e2 = new CompositeException(Arrays.asList(new Throwable[]{e, npe}));
                                s.onError(e2);
                                e = e2;
                                return;
                            }
                            c.unsafeSubscribe(new C69911());
                        } catch (Throwable ex) {
                            e2 = new CompositeException(Arrays.asList(new Throwable[]{e, ex}));
                            s.onError(e2);
                            e = e2;
                        }
                    }

                    public void onSubscribe(Subscription d) {
                        sd.set(d);
                    }
                });
            }
        });
    }

    public final Completable repeat() {
        return fromObservable(toObservable().repeat());
    }

    public final Completable repeat(long times) {
        return fromObservable(toObservable().repeat(times));
    }

    public final Completable repeatWhen(Func1<? super Observable<? extends Void>, ? extends Observable<?>> handler) {
        requireNonNull(handler);
        return fromObservable(toObservable().repeatWhen(handler));
    }

    public final Completable retry() {
        return fromObservable(toObservable().retry());
    }

    public final Completable retry(Func2<Integer, Throwable, Boolean> predicate) {
        return fromObservable(toObservable().retry(predicate));
    }

    public final Completable retry(long times) {
        return fromObservable(toObservable().retry(times));
    }

    public final Completable retryWhen(Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> handler) {
        return fromObservable(toObservable().retryWhen(handler));
    }

    public final Completable startWith(Completable other) {
        requireNonNull(other);
        return concat(other, this);
    }

    public final <T> Observable<T> startWith(Observable<T> other) {
        requireNonNull(other);
        return toObservable().startWith(other);
    }

    public final Subscription subscribe() {
        final MultipleAssignmentSubscription mad = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() {
            public void onCompleted() {
                mad.unsubscribe();
            }

            public void onError(Throwable e) {
                RxJavaHooks.onError(e);
                mad.unsubscribe();
                Completable.deliverUncaughtException(e);
            }

            public void onSubscribe(Subscription d) {
                mad.set(d);
            }
        });
        return mad;
    }

    public final Subscription subscribe(final Action0 onComplete) {
        requireNonNull(onComplete);
        final MultipleAssignmentSubscription mad = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() {
            boolean done;

            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    try {
                        onComplete.call();
                    } catch (Throwable e) {
                        RxJavaHooks.onError(e);
                        Completable.deliverUncaughtException(e);
                    } finally {
                        mad.unsubscribe();
                    }
                }
            }

            public void onError(Throwable e) {
                RxJavaHooks.onError(e);
                mad.unsubscribe();
                Completable.deliverUncaughtException(e);
            }

            public void onSubscribe(Subscription d) {
                mad.set(d);
            }
        });
        return mad;
    }

    public final Subscription subscribe(final Action0 onComplete, final Action1<? super Throwable> onError) {
        requireNonNull(onComplete);
        requireNonNull(onError);
        final MultipleAssignmentSubscription mad = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() {
            boolean done;

            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    try {
                        onComplete.call();
                        mad.unsubscribe();
                    } catch (Throwable e) {
                        callOnError(e);
                    }
                }
            }

            public void onError(Throwable e) {
                if (this.done) {
                    RxJavaHooks.onError(e);
                    Completable.deliverUncaughtException(e);
                    return;
                }
                this.done = true;
                callOnError(e);
            }

            void callOnError(Throwable e) {
                Throwable e2;
                Throwable th;
                try {
                    onError.call(e);
                    mad.unsubscribe();
                } catch (Throwable th2) {
                    th = th2;
                    e = e2;
                    mad.unsubscribe();
                    throw th;
                }
            }

            public void onSubscribe(Subscription d) {
                mad.set(d);
            }
        });
        return mad;
    }

    static void deliverUncaughtException(Throwable e) {
        Thread thread = Thread.currentThread();
        thread.getUncaughtExceptionHandler().uncaughtException(thread, e);
    }

    public final void unsafeSubscribe(CompletableSubscriber s) {
        requireNonNull(s);
        try {
            RxJavaHooks.onCompletableStart(this, this.onSubscribe).call(s);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            Exceptions.throwIfFatal(ex2);
            Throwable ex22 = RxJavaHooks.onCompletableError(ex22);
            RxJavaHooks.onError(ex22);
            NullPointerException toNpe = toNpe(ex22);
        }
    }

    public final void subscribe(CompletableSubscriber s) {
        if (!(s instanceof SafeCompletableSubscriber)) {
            s = new SafeCompletableSubscriber(s);
        }
        unsafeSubscribe(s);
    }

    public final <T> void unsafeSubscribe(Subscriber<T> s) {
        unsafeSubscribe(s, true);
    }

    private <T> void unsafeSubscribe(final Subscriber<T> s, boolean callOnStart) {
        requireNonNull(s);
        if (callOnStart) {
            try {
                s.onStart();
            } catch (NullPointerException ex) {
                throw ex;
            } catch (Throwable ex2) {
                Exceptions.throwIfFatal(ex2);
                Throwable ex22 = RxJavaHooks.onObservableError(ex22);
                RxJavaHooks.onError(ex22);
                NullPointerException toNpe = toNpe(ex22);
            }
        }
        unsafeSubscribe(new CompletableSubscriber() {
            public void onCompleted() {
                s.onCompleted();
            }

            public void onError(Throwable e) {
                s.onError(e);
            }

            public void onSubscribe(Subscription d) {
                s.add(d);
            }
        });
        RxJavaHooks.onObservableReturn(s);
    }

    public final <T> void subscribe(Subscriber<T> s) {
        s.onStart();
        if (!(s instanceof SafeSubscriber)) {
            s = new SafeSubscriber(s);
        }
        unsafeSubscribe(s, false);
    }

    public final Completable subscribeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final Worker w = scheduler.createWorker();
                w.schedule(new Action0() {
                    public void call() {
                        try {
                            Completable.this.unsafeSubscribe(s);
                        } finally {
                            w.unsubscribe();
                        }
                    }
                });
            }
        });
    }

    public final Completable timeout(long timeout, TimeUnit unit) {
        return timeout0(timeout, unit, Schedulers.computation(), null);
    }

    public final Completable timeout(long timeout, TimeUnit unit, Completable other) {
        requireNonNull(other);
        return timeout0(timeout, unit, Schedulers.computation(), other);
    }

    public final Completable timeout(long timeout, TimeUnit unit, Scheduler scheduler) {
        return timeout0(timeout, unit, scheduler, null);
    }

    public final Completable timeout(long timeout, TimeUnit unit, Scheduler scheduler, Completable other) {
        requireNonNull(other);
        return timeout0(timeout, unit, scheduler, other);
    }

    public final Completable timeout0(long timeout, TimeUnit unit, Scheduler scheduler, Completable other) {
        requireNonNull(unit);
        requireNonNull(scheduler);
        return create(new CompletableOnSubscribeTimeout(this, timeout, unit, scheduler, other));
    }

    public final <R> R to(Func1<? super Completable, R> converter) {
        return converter.call(this);
    }

    public final <T> Observable<T> toObservable() {
        return Observable.create(new Observable$OnSubscribe<T>() {
            public void call(Subscriber<? super T> s) {
                Completable.this.unsafeSubscribe((Subscriber) s);
            }
        });
    }

    public final <T> Single<T> toSingle(final Func0<? extends T> completionValueFunc0) {
        requireNonNull(completionValueFunc0);
        return Single.create(new rx.Single.OnSubscribe<T>() {
            public void call(final SingleSubscriber<? super T> s) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    public void onCompleted() {
                        try {
                            T v = completionValueFunc0.call();
                            if (v == null) {
                                s.onError(new NullPointerException("The value supplied is null"));
                            } else {
                                s.onSuccess(v);
                            }
                        } catch (Throwable e) {
                            s.onError(e);
                        }
                    }

                    public void onError(Throwable e) {
                        s.onError(e);
                    }

                    public void onSubscribe(Subscription d) {
                        s.add(d);
                    }
                });
            }
        });
    }

    public final <T> Single<T> toSingleDefault(final T completionValue) {
        requireNonNull(completionValue);
        return toSingle(new Func0<T>() {
            public T call() {
                return completionValue;
            }
        });
    }

    public final Completable unsubscribeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() {
                    public void onCompleted() {
                        s.onCompleted();
                    }

                    public void onError(Throwable e) {
                        s.onError(e);
                    }

                    public void onSubscribe(final Subscription d) {
                        s.onSubscribe(Subscriptions.create(new Action0() {
                            public void call() {
                                final Worker w = scheduler.createWorker();
                                w.schedule(new Action0() {
                                    public void call() {
                                        try {
                                            d.unsubscribe();
                                        } finally {
                                            w.unsubscribe();
                                        }
                                    }
                                });
                            }
                        }));
                    }
                });
            }
        });
    }
}
