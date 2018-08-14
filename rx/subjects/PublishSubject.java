package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.BackpressureUtils;

public final class PublishSubject<T> extends Subject<T, T> {
    final PublishSubjectState<T> state;

    static final class PublishSubjectProducer<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        private static final long serialVersionUID = 6451806817170721536L;
        final Subscriber<? super T> actual;
        final PublishSubjectState<T> parent;
        long produced;

        public PublishSubjectProducer(PublishSubjectState<T> parent, Subscriber<? super T> actual) {
            this.parent = parent;
            this.actual = actual;
        }

        public void request(long n) {
            if (BackpressureUtils.validate(n)) {
                long r;
                do {
                    r = get();
                    if (r == Long.MIN_VALUE) {
                        return;
                    }
                } while (!compareAndSet(r, BackpressureUtils.addCap(r, n)));
            }
        }

        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public void unsubscribe() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }

        public void onNext(T t) {
            long r = get();
            if (r != Long.MIN_VALUE) {
                long p = this.produced;
                if (r != p) {
                    this.produced = 1 + p;
                    this.actual.onNext(t);
                    return;
                }
                unsubscribe();
                this.actual.onError(new MissingBackpressureException("PublishSubject: could not emit value due to lack of requests"));
            }
        }

        public void onError(Throwable e) {
            if (get() != Long.MIN_VALUE) {
                this.actual.onError(e);
            }
        }

        public void onCompleted() {
            if (get() != Long.MIN_VALUE) {
                this.actual.onCompleted();
            }
        }
    }

    static final class PublishSubjectState<T> extends AtomicReference<PublishSubjectProducer<T>[]> implements Observable$OnSubscribe<T>, Observer<T> {
        static final PublishSubjectProducer[] EMPTY = new PublishSubjectProducer[0];
        static final PublishSubjectProducer[] TERMINATED = new PublishSubjectProducer[0];
        private static final long serialVersionUID = -7568940796666027140L;
        Throwable error;

        public PublishSubjectState() {
            lazySet(EMPTY);
        }

        public void call(Subscriber<? super T> t) {
            PublishSubjectProducer<T> pp = new PublishSubjectProducer(this, t);
            t.add(pp);
            t.setProducer(pp);
            if (!add(pp)) {
                Throwable ex = this.error;
                if (ex != null) {
                    t.onError(ex);
                } else {
                    t.onCompleted();
                }
            } else if (pp.isUnsubscribed()) {
                remove(pp);
            }
        }

        boolean add(PublishSubjectProducer<T> inner) {
            PublishSubjectProducer[] curr;
            PublishSubjectProducer<T>[] next;
            do {
                curr = (PublishSubjectProducer[]) get();
                if (curr == TERMINATED) {
                    return false;
                }
                int n = curr.length;
                next = new PublishSubjectProducer[(n + 1)];
                System.arraycopy(curr, 0, next, 0, n);
                next[n] = inner;
            } while (!compareAndSet(curr, next));
            return true;
        }

        void remove(PublishSubjectProducer<T> inner) {
            PublishSubjectProducer[] curr;
            PublishSubjectProducer<T>[] next;
            do {
                curr = (PublishSubjectProducer[]) get();
                if (curr != TERMINATED && curr != EMPTY) {
                    int n = curr.length;
                    int j = -1;
                    for (int i = 0; i < n; i++) {
                        if (curr[i] == inner) {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (n == 1) {
                        next = EMPTY;
                    } else {
                        next = new PublishSubjectProducer[(n - 1)];
                        System.arraycopy(curr, 0, next, 0, j);
                        System.arraycopy(curr, j + 1, next, j, (n - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(curr, next));
        }

        public void onNext(T t) {
            for (PublishSubjectProducer<T> pp : (PublishSubjectProducer[]) get()) {
                pp.onNext(t);
            }
        }

        public void onError(Throwable e) {
            this.error = e;
            List<Throwable> errors = null;
            for (PublishSubjectProducer<T> pp : (PublishSubjectProducer[]) getAndSet(TERMINATED)) {
                try {
                    pp.onError(e);
                } catch (Throwable ex) {
                    if (errors == null) {
                        errors = new ArrayList(1);
                    }
                    errors.add(ex);
                }
            }
            Exceptions.throwIfAny(errors);
        }

        public void onCompleted() {
            for (PublishSubjectProducer<T> pp : (PublishSubjectProducer[]) getAndSet(TERMINATED)) {
                pp.onCompleted();
            }
        }
    }

    public static <T> PublishSubject<T> create() {
        return new PublishSubject(new PublishSubjectState());
    }

    protected PublishSubject(PublishSubjectState<T> state) {
        super(state);
        this.state = state;
    }

    public void onNext(T v) {
        this.state.onNext(v);
    }

    public void onError(Throwable e) {
        this.state.onError(e);
    }

    public void onCompleted() {
        this.state.onCompleted();
    }

    public boolean hasObservers() {
        return ((PublishSubjectProducer[]) this.state.get()).length != 0;
    }

    public boolean hasThrowable() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error != null;
    }

    public boolean hasCompleted() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error == null;
    }

    public Throwable getThrowable() {
        if (this.state.get() == PublishSubjectState.TERMINATED) {
            return this.state.error;
        }
        return null;
    }
}
