package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Subscriber;

public final class OnSubscribeFromArray<T> implements Observable$OnSubscribe<T> {
    final T[] array;

    static final class FromArrayProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = 3534218984725836979L;
        final T[] array;
        final Subscriber<? super T> child;
        int index;

        public FromArrayProducer(Subscriber<? super T> child, T[] array) {
            this.child = child;
            this.array = array;
        }

        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            } else if (n == Long.MAX_VALUE) {
                if (BackpressureUtils.getAndAddRequest(this, n) == 0) {
                    fastPath();
                }
            } else if (n != 0 && BackpressureUtils.getAndAddRequest(this, n) == 0) {
                slowPath(n);
            }
        }

        void fastPath() {
            Subscriber<? super T> child = this.child;
            Object[] arr$ = this.array;
            int len$ = arr$.length;
            int i$ = 0;
            while (i$ < len$) {
                T t = arr$[i$];
                if (!child.isUnsubscribed()) {
                    child.onNext(t);
                    i$++;
                } else {
                    return;
                }
            }
            if (!child.isUnsubscribed()) {
                child.onCompleted();
            }
        }

        void slowPath(long r) {
            Subscriber<? super T> child = this.child;
            T[] array = this.array;
            int n = array.length;
            long e = 0;
            int i = this.index;
            while (true) {
                if (r == 0 || i == n) {
                    r = get() + e;
                    if (r == 0) {
                        this.index = i;
                        r = addAndGet(e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (!child.isUnsubscribed()) {
                    child.onNext(array[i]);
                    i++;
                    if (i == n) {
                        break;
                    }
                    r--;
                    e--;
                } else {
                    return;
                }
            }
            if (!child.isUnsubscribed()) {
                child.onCompleted();
            }
        }
    }

    public OnSubscribeFromArray(T[] array) {
        this.array = array;
    }

    public void call(Subscriber<? super T> child) {
        child.setProducer(new FromArrayProducer(child, this.array));
    }
}
