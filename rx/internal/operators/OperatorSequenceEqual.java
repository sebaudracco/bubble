package rx.internal.operators;

import rx.Observable;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;

public final class OperatorSequenceEqual {
    static final Object LOCAL_ON_COMPLETED = new Object();

    private OperatorSequenceEqual() {
        throw new IllegalStateException("No instances!");
    }

    static <T> Observable<Object> materializeLite(Observable<T> source) {
        return Observable.concat(source, Observable.just(LOCAL_ON_COMPLETED));
    }

    public static <T> Observable<Boolean> sequenceEqual(Observable<? extends T> first, Observable<? extends T> second, final Func2<? super T, ? super T, Boolean> equality) {
        return Observable.zip(materializeLite(first), materializeLite(second), new Func2<Object, Object, Boolean>() {
            public Boolean call(Object t1, Object t2) {
                boolean c1;
                if (t1 == OperatorSequenceEqual.LOCAL_ON_COMPLETED) {
                    c1 = true;
                } else {
                    c1 = false;
                }
                boolean c2;
                if (t2 == OperatorSequenceEqual.LOCAL_ON_COMPLETED) {
                    c2 = true;
                } else {
                    c2 = false;
                }
                if (c1 && c2) {
                    return Boolean.valueOf(true);
                }
                if (c1 || c2) {
                    return Boolean.valueOf(false);
                }
                return (Boolean) equality.call(t1, t2);
            }
        }).all(UtilityFunctions.identity());
    }
}
