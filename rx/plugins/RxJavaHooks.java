package rx.plugins;

import java.util.concurrent.ScheduledExecutorService;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.Completable.Operator;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Observable$Operator;
import rx.Scheduler;
import rx.Single;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OnSubscribeOnAssembly;
import rx.internal.operators.OnSubscribeOnAssemblyCompletable;
import rx.internal.operators.OnSubscribeOnAssemblySingle;

@Experimental
public final class RxJavaHooks {
    static volatile boolean lockdown;
    static volatile Func1<OnSubscribe, OnSubscribe> onCompletableCreate;
    static volatile Func1<Operator, Operator> onCompletableLift;
    static volatile Func2<Completable, OnSubscribe, OnSubscribe> onCompletableStart;
    static volatile Func1<Throwable, Throwable> onCompletableSubscribeError;
    static volatile Func1<Scheduler, Scheduler> onComputationScheduler;
    static volatile Action1<Throwable> onError;
    static volatile Func0<? extends ScheduledExecutorService> onGenericScheduledExecutorService;
    static volatile Func1<Scheduler, Scheduler> onIOScheduler;
    static volatile Func1<Scheduler, Scheduler> onNewThreadScheduler;
    static volatile Func1<Observable$OnSubscribe, Observable$OnSubscribe> onObservableCreate;
    static volatile Func1<Observable$Operator, Observable$Operator> onObservableLift;
    static volatile Func1<Subscription, Subscription> onObservableReturn;
    static volatile Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe> onObservableStart;
    static volatile Func1<Throwable, Throwable> onObservableSubscribeError;
    static volatile Func1<Action0, Action0> onScheduleAction;
    static volatile Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate;
    static volatile Func1<Observable$Operator, Observable$Operator> onSingleLift;
    static volatile Func1<Subscription, Subscription> onSingleReturn;
    static volatile Func2<Single, Observable$OnSubscribe, Observable$OnSubscribe> onSingleStart;
    static volatile Func1<Throwable, Throwable> onSingleSubscribeError;

    static class C72801 implements Action1<Throwable> {
        C72801() {
        }

        public void call(Throwable e) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        }
    }

    static class C72812 implements Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe> {
        C72812() {
        }

        public Observable$OnSubscribe call(Observable t1, Observable$OnSubscribe t2) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(t1, t2);
        }
    }

    static class C72823 implements Func1<Subscription, Subscription> {
        C72823() {
        }

        public Subscription call(Subscription f) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeReturn(f);
        }
    }

    static class C72834 implements Func2<Single, Observable$OnSubscribe, Observable$OnSubscribe> {
        C72834() {
        }

        public Observable$OnSubscribe call(Single t1, Observable$OnSubscribe t2) {
            return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeStart(t1, t2);
        }
    }

    static class C72845 implements Func1<Subscription, Subscription> {
        C72845() {
        }

        public Subscription call(Subscription f) {
            return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeReturn(f);
        }
    }

    static class C72856 implements Func2<Completable, OnSubscribe, OnSubscribe> {
        C72856() {
        }

        public OnSubscribe call(Completable t1, OnSubscribe t2) {
            return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeStart(t1, t2);
        }
    }

    static class C72867 implements Func1<Action0, Action0> {
        C72867() {
        }

        public Action0 call(Action0 a) {
            return RxJavaPlugins.getInstance().getSchedulersHook().onSchedule(a);
        }
    }

    static class C72878 implements Func1<Throwable, Throwable> {
        C72878() {
        }

        public Throwable call(Throwable t) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeError(t);
        }
    }

    static class C72889 implements Func1<Observable$Operator, Observable$Operator> {
        C72889() {
        }

        public Observable$Operator call(Observable$Operator t) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onLift(t);
        }
    }

    static {
        init();
    }

    private RxJavaHooks() {
        throw new IllegalStateException("No instances!");
    }

    static void init() {
        onError = new C72801();
        onObservableStart = new C72812();
        onObservableReturn = new C72823();
        onSingleStart = new C72834();
        onSingleReturn = new C72845();
        onCompletableStart = new C72856();
        onScheduleAction = new C72867();
        onObservableSubscribeError = new C72878();
        onObservableLift = new C72889();
        onSingleSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable t) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeError(t);
            }
        };
        onSingleLift = new Func1<Observable$Operator, Observable$Operator>() {
            public Observable$Operator call(Observable$Operator t) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onLift(t);
            }
        };
        onCompletableSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable t) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeError(t);
            }
        };
        onCompletableLift = new Func1<Operator, Operator>() {
            public Operator call(Operator t) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onLift(t);
            }
        };
        initCreate();
    }

    static void initCreate() {
        onObservableCreate = new Func1<Observable$OnSubscribe, Observable$OnSubscribe>() {
            public Observable$OnSubscribe call(Observable$OnSubscribe f) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onCreate(f);
            }
        };
        onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() {
            public Single.OnSubscribe call(Single.OnSubscribe f) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onCreate(f);
            }
        };
        onCompletableCreate = new Func1<OnSubscribe, OnSubscribe>() {
            public OnSubscribe call(OnSubscribe f) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onCreate(f);
            }
        };
    }

    public static void reset() {
        if (!lockdown) {
            init();
            onComputationScheduler = null;
            onIOScheduler = null;
            onNewThreadScheduler = null;
            onGenericScheduledExecutorService = null;
        }
    }

    public static void clear() {
        if (!lockdown) {
            onError = null;
            onObservableCreate = null;
            onObservableStart = null;
            onObservableReturn = null;
            onObservableSubscribeError = null;
            onObservableLift = null;
            onSingleCreate = null;
            onSingleStart = null;
            onSingleReturn = null;
            onSingleSubscribeError = null;
            onSingleLift = null;
            onCompletableCreate = null;
            onCompletableStart = null;
            onCompletableSubscribeError = null;
            onCompletableLift = null;
            onComputationScheduler = null;
            onIOScheduler = null;
            onNewThreadScheduler = null;
            onScheduleAction = null;
            onGenericScheduledExecutorService = null;
        }
    }

    public static void lockdown() {
        lockdown = true;
    }

    public static boolean isLockdown() {
        return lockdown;
    }

    public static void onError(Throwable ex) {
        Action1<Throwable> f = onError;
        if (f != null) {
            try {
                f.call(ex);
                return;
            } catch (Throwable pluginException) {
                System.err.println("The onError handler threw an Exception. It shouldn't. => " + pluginException.getMessage());
                pluginException.printStackTrace();
                signalUncaught(pluginException);
            }
        }
        signalUncaught(ex);
    }

    static void signalUncaught(Throwable ex) {
        Thread current = Thread.currentThread();
        current.getUncaughtExceptionHandler().uncaughtException(current, ex);
    }

    public static <T> Observable$OnSubscribe<T> onCreate(Observable$OnSubscribe<T> onSubscribe) {
        Func1<Observable$OnSubscribe, Observable$OnSubscribe> f = onObservableCreate;
        if (f != null) {
            return (Observable$OnSubscribe) f.call(onSubscribe);
        }
        return onSubscribe;
    }

    public static <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> onSubscribe) {
        Func1<Single.OnSubscribe, Single.OnSubscribe> f = onSingleCreate;
        if (f != null) {
            return (Single.OnSubscribe) f.call(onSubscribe);
        }
        return onSubscribe;
    }

    public static OnSubscribe onCreate(OnSubscribe onSubscribe) {
        Func1<OnSubscribe, OnSubscribe> f = onCompletableCreate;
        if (f != null) {
            return (OnSubscribe) f.call(onSubscribe);
        }
        return onSubscribe;
    }

    public static Scheduler onComputationScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> f = onComputationScheduler;
        if (f != null) {
            return (Scheduler) f.call(scheduler);
        }
        return scheduler;
    }

    public static Scheduler onIOScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> f = onIOScheduler;
        if (f != null) {
            return (Scheduler) f.call(scheduler);
        }
        return scheduler;
    }

    public static Scheduler onNewThreadScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> f = onNewThreadScheduler;
        if (f != null) {
            return (Scheduler) f.call(scheduler);
        }
        return scheduler;
    }

    public static Action0 onScheduledAction(Action0 action) {
        Func1<Action0, Action0> f = onScheduleAction;
        if (f != null) {
            return (Action0) f.call(action);
        }
        return action;
    }

    public static <T> Observable$OnSubscribe<T> onObservableStart(Observable<T> instance, Observable$OnSubscribe<T> onSubscribe) {
        Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe> f = onObservableStart;
        if (f != null) {
            return (Observable$OnSubscribe) f.call(instance, onSubscribe);
        }
        return onSubscribe;
    }

    public static Subscription onObservableReturn(Subscription subscription) {
        Func1<Subscription, Subscription> f = onObservableReturn;
        if (f != null) {
            return (Subscription) f.call(subscription);
        }
        return subscription;
    }

    public static Throwable onObservableError(Throwable error) {
        Func1<Throwable, Throwable> f = onObservableSubscribeError;
        if (f != null) {
            return (Throwable) f.call(error);
        }
        return error;
    }

    public static <T, R> Observable$Operator<R, T> onObservableLift(Observable$Operator<R, T> operator) {
        Func1<Observable$Operator, Observable$Operator> f = onObservableLift;
        if (f != null) {
            return (Observable$Operator) f.call(operator);
        }
        return operator;
    }

    public static <T> Observable$OnSubscribe<T> onSingleStart(Single<T> instance, Observable$OnSubscribe<T> onSubscribe) {
        Func2<Single, Observable$OnSubscribe, Observable$OnSubscribe> f = onSingleStart;
        if (f != null) {
            return (Observable$OnSubscribe) f.call(instance, onSubscribe);
        }
        return onSubscribe;
    }

    public static Subscription onSingleReturn(Subscription subscription) {
        Func1<Subscription, Subscription> f = onSingleReturn;
        if (f != null) {
            return (Subscription) f.call(subscription);
        }
        return subscription;
    }

    public static Throwable onSingleError(Throwable error) {
        Func1<Throwable, Throwable> f = onSingleSubscribeError;
        if (f != null) {
            return (Throwable) f.call(error);
        }
        return error;
    }

    public static <T, R> Observable$Operator<R, T> onSingleLift(Observable$Operator<R, T> operator) {
        Func1<Observable$Operator, Observable$Operator> f = onSingleLift;
        if (f != null) {
            return (Observable$Operator) f.call(operator);
        }
        return operator;
    }

    public static <T> OnSubscribe onCompletableStart(Completable instance, OnSubscribe onSubscribe) {
        Func2<Completable, OnSubscribe, OnSubscribe> f = onCompletableStart;
        if (f != null) {
            return (OnSubscribe) f.call(instance, onSubscribe);
        }
        return onSubscribe;
    }

    public static Throwable onCompletableError(Throwable error) {
        Func1<Throwable, Throwable> f = onCompletableSubscribeError;
        if (f != null) {
            return (Throwable) f.call(error);
        }
        return error;
    }

    public static <T, R> Operator onCompletableLift(Operator operator) {
        Func1<Operator, Operator> f = onCompletableLift;
        if (f != null) {
            return (Operator) f.call(operator);
        }
        return operator;
    }

    public static void setOnError(Action1<Throwable> onError) {
        if (!lockdown) {
            onError = onError;
        }
    }

    public static void setOnCompletableCreate(Func1<OnSubscribe, OnSubscribe> onCompletableCreate) {
        if (!lockdown) {
            onCompletableCreate = onCompletableCreate;
        }
    }

    public static void setOnObservableCreate(Func1<Observable$OnSubscribe, Observable$OnSubscribe> onObservableCreate) {
        if (!lockdown) {
            onObservableCreate = onObservableCreate;
        }
    }

    public static void setOnSingleCreate(Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate) {
        if (!lockdown) {
            onSingleCreate = onSingleCreate;
        }
    }

    public static void setOnComputationScheduler(Func1<Scheduler, Scheduler> onComputationScheduler) {
        if (!lockdown) {
            onComputationScheduler = onComputationScheduler;
        }
    }

    public static void setOnIOScheduler(Func1<Scheduler, Scheduler> onIOScheduler) {
        if (!lockdown) {
            onIOScheduler = onIOScheduler;
        }
    }

    public static void setOnNewThreadScheduler(Func1<Scheduler, Scheduler> onNewThreadScheduler) {
        if (!lockdown) {
            onNewThreadScheduler = onNewThreadScheduler;
        }
    }

    public static void setOnScheduleAction(Func1<Action0, Action0> onScheduleAction) {
        if (!lockdown) {
            onScheduleAction = onScheduleAction;
        }
    }

    public static void setOnCompletableStart(Func2<Completable, OnSubscribe, OnSubscribe> onCompletableStart) {
        if (!lockdown) {
            onCompletableStart = onCompletableStart;
        }
    }

    public static void setOnObservableStart(Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe> onObservableStart) {
        if (!lockdown) {
            onObservableStart = onObservableStart;
        }
    }

    public static void setOnSingleStart(Func2<Single, Observable$OnSubscribe, Observable$OnSubscribe> onSingleStart) {
        if (!lockdown) {
            onSingleStart = onSingleStart;
        }
    }

    public static void setOnObservableReturn(Func1<Subscription, Subscription> onObservableReturn) {
        if (!lockdown) {
            onObservableReturn = onObservableReturn;
        }
    }

    public static void setOnSingleReturn(Func1<Subscription, Subscription> onSingleReturn) {
        if (!lockdown) {
            onSingleReturn = onSingleReturn;
        }
    }

    public static void setOnSingleSubscribeError(Func1<Throwable, Throwable> onSingleSubscribeError) {
        if (!lockdown) {
            onSingleSubscribeError = onSingleSubscribeError;
        }
    }

    public static Func1<Throwable, Throwable> getOnSingleSubscribeError() {
        return onSingleSubscribeError;
    }

    public static void setOnCompletableSubscribeError(Func1<Throwable, Throwable> onCompletableSubscribeError) {
        if (!lockdown) {
            onCompletableSubscribeError = onCompletableSubscribeError;
        }
    }

    public static Func1<Throwable, Throwable> getOnCompletableSubscribeError() {
        return onCompletableSubscribeError;
    }

    public static void setOnObservableSubscribeError(Func1<Throwable, Throwable> onObservableSubscribeError) {
        if (!lockdown) {
            onObservableSubscribeError = onObservableSubscribeError;
        }
    }

    public static Func1<Throwable, Throwable> getOnObservableSubscribeError() {
        return onObservableSubscribeError;
    }

    public static void setOnObservableLift(Func1<Observable$Operator, Observable$Operator> onObservableLift) {
        if (!lockdown) {
            onObservableLift = onObservableLift;
        }
    }

    public static Func1<Observable$Operator, Observable$Operator> getOnObservableLift() {
        return onObservableLift;
    }

    public static void setOnSingleLift(Func1<Observable$Operator, Observable$Operator> onSingleLift) {
        if (!lockdown) {
            onSingleLift = onSingleLift;
        }
    }

    public static Func1<Observable$Operator, Observable$Operator> getOnSingleLift() {
        return onSingleLift;
    }

    public static void setOnCompletableLift(Func1<Operator, Operator> onCompletableLift) {
        if (!lockdown) {
            onCompletableLift = onCompletableLift;
        }
    }

    public static Func1<Operator, Operator> getOnCompletableLift() {
        return onCompletableLift;
    }

    public static Func1<Scheduler, Scheduler> getOnComputationScheduler() {
        return onComputationScheduler;
    }

    public static Action1<Throwable> getOnError() {
        return onError;
    }

    public static Func1<Scheduler, Scheduler> getOnIOScheduler() {
        return onIOScheduler;
    }

    public static Func1<Scheduler, Scheduler> getOnNewThreadScheduler() {
        return onNewThreadScheduler;
    }

    public static Func1<Observable$OnSubscribe, Observable$OnSubscribe> getOnObservableCreate() {
        return onObservableCreate;
    }

    public static Func1<Action0, Action0> getOnScheduleAction() {
        return onScheduleAction;
    }

    public static Func1<Single.OnSubscribe, Single.OnSubscribe> getOnSingleCreate() {
        return onSingleCreate;
    }

    public static Func1<OnSubscribe, OnSubscribe> getOnCompletableCreate() {
        return onCompletableCreate;
    }

    public static Func2<Completable, OnSubscribe, OnSubscribe> getOnCompletableStart() {
        return onCompletableStart;
    }

    public static Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe> getOnObservableStart() {
        return onObservableStart;
    }

    public static Func2<Single, Observable$OnSubscribe, Observable$OnSubscribe> getOnSingleStart() {
        return onSingleStart;
    }

    public static Func1<Subscription, Subscription> getOnObservableReturn() {
        return onObservableReturn;
    }

    public static Func1<Subscription, Subscription> getOnSingleReturn() {
        return onSingleReturn;
    }

    public static void resetAssemblyTracking() {
        if (!lockdown) {
            initCreate();
        }
    }

    public static void clearAssemblyTracking() {
        if (!lockdown) {
            onObservableCreate = null;
            onSingleCreate = null;
            onCompletableCreate = null;
        }
    }

    public static void enableAssemblyTracking() {
        if (!lockdown) {
            onObservableCreate = new Func1<Observable$OnSubscribe, Observable$OnSubscribe>() {
                public Observable$OnSubscribe call(Observable$OnSubscribe f) {
                    return new OnSubscribeOnAssembly(f);
                }
            };
            onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() {
                public Single.OnSubscribe call(Single.OnSubscribe f) {
                    return new OnSubscribeOnAssemblySingle(f);
                }
            };
            onCompletableCreate = new Func1<OnSubscribe, OnSubscribe>() {
                public OnSubscribe call(OnSubscribe f) {
                    return new OnSubscribeOnAssemblyCompletable(f);
                }
            };
        }
    }

    public static void setOnGenericScheduledExecutorService(Func0<? extends ScheduledExecutorService> factory) {
        if (!lockdown) {
            onGenericScheduledExecutorService = factory;
        }
    }

    public static Func0<? extends ScheduledExecutorService> getOnGenericScheduledExecutorService() {
        return onGenericScheduledExecutorService;
    }
}
