package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.subjects.Subject;
import rx.subjects.UnicastSubject;
import rx.subscriptions.Subscriptions;

public final class OperatorWindowWithSize<T> implements Observable$Operator<Observable<T>, T> {
    final int size;
    final int skip;

    static final class WindowExact<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel = Subscriptions.create(this);
        int index;
        final int size;
        Subject<T, T> window;
        final AtomicInteger wip = new AtomicInteger(1);

        class C72001 implements Producer {
            C72001() {
            }

            public void request(long n) {
                if (n < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + n);
                } else if (n != 0) {
                    WindowExact.this.request(BackpressureUtils.multiplyCap((long) WindowExact.this.size, n));
                }
            }
        }

        public WindowExact(Subscriber<? super Observable<T>> actual, int size) {
            this.actual = actual;
            this.size = size;
            add(this.cancel);
            request(0);
        }

        public void onNext(T t) {
            int i = this.index;
            Subject<T, T> w = this.window;
            if (i == 0) {
                this.wip.getAndIncrement();
                w = UnicastSubject.create(this.size, this);
                this.window = w;
                this.actual.onNext(w);
            }
            i++;
            w.onNext(t);
            if (i == this.size) {
                this.index = 0;
                this.window = null;
                w.onCompleted();
                return;
            }
            this.index = i;
        }

        public void onError(Throwable e) {
            Subject<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onError(e);
            }
            this.actual.onError(e);
        }

        public void onCompleted() {
            Subject<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onCompleted();
            }
            this.actual.onCompleted();
        }

        Producer createProducer() {
            return new C72001();
        }

        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                unsubscribe();
            }
        }
    }

    static final class WindowOverlap<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel = Subscriptions.create(this);
        volatile boolean done;
        final AtomicInteger drainWip = new AtomicInteger();
        Throwable error;
        int index;
        int produced;
        final Queue<Subject<T, T>> queue;
        final AtomicLong requested = new AtomicLong();
        final int size;
        final int skip;
        final ArrayDeque<Subject<T, T>> windows = new ArrayDeque();
        final AtomicInteger wip = new AtomicInteger(1);

        final class WindowOverlapProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 4625807964358024108L;

            WindowOverlapProducer() {
            }

            public void request(long n) {
                if (n < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + n);
                } else if (n != 0) {
                    WindowOverlap<T> parent = WindowOverlap.this;
                    if (get() || !compareAndSet(false, true)) {
                        WindowOverlap.this.request(BackpressureUtils.multiplyCap((long) parent.skip, n));
                    } else {
                        parent.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap((long) parent.skip, n - 1), (long) parent.size));
                    }
                    BackpressureUtils.getAndAddRequest(parent.requested, n);
                    parent.drain();
                }
            }
        }

        public WindowOverlap(Subscriber<? super Observable<T>> actual, int size, int skip) {
            this.actual = actual;
            this.size = size;
            this.skip = skip;
            add(this.cancel);
            request(0);
            this.queue = new SpscLinkedArrayQueue(((skip - 1) + size) / skip);
        }

        public void onNext(T t) {
            int i = this.index;
            ArrayDeque<Subject<T, T>> q = this.windows;
            if (i == 0 && !this.actual.isUnsubscribed()) {
                this.wip.getAndIncrement();
                Subject<T, T> w = UnicastSubject.create(16, this);
                q.offer(w);
                this.queue.offer(w);
                drain();
            }
            Iterator i$ = this.windows.iterator();
            while (i$.hasNext()) {
                ((Subject) i$.next()).onNext(t);
            }
            int p = this.produced + 1;
            if (p == this.size) {
                this.produced = p - this.skip;
                w = (Subject) q.poll();
                if (w != null) {
                    w.onCompleted();
                }
            } else {
                this.produced = p;
            }
            i++;
            if (i == this.skip) {
                this.index = 0;
            } else {
                this.index = i;
            }
        }

        public void onError(Throwable e) {
            Iterator i$ = this.windows.iterator();
            while (i$.hasNext()) {
                ((Subject) i$.next()).onError(e);
            }
            this.windows.clear();
            this.error = e;
            this.done = true;
            drain();
        }

        public void onCompleted() {
            Iterator i$ = this.windows.iterator();
            while (i$.hasNext()) {
                ((Subject) i$.next()).onCompleted();
            }
            this.windows.clear();
            this.done = true;
            drain();
        }

        Producer createProducer() {
            return new WindowOverlapProducer();
        }

        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        void drain() {
            AtomicInteger dw = this.drainWip;
            if (dw.getAndIncrement() == 0) {
                Subscriber<? super Subject<T, T>> a = this.actual;
                Queue<Subject<T, T>> q = this.queue;
                int missed = 1;
                do {
                    long r = this.requested.get();
                    long e = 0;
                    while (e != r) {
                        boolean d = this.done;
                        Subject<T, T> v = (Subject) q.poll();
                        boolean empty = v == null;
                        if (!checkTerminated(d, empty, a, q)) {
                            if (empty) {
                                break;
                            }
                            a.onNext(v);
                            e++;
                        } else {
                            return;
                        }
                    }
                    if (e != r || !checkTerminated(this.done, q.isEmpty(), a, q)) {
                        if (!(e == 0 || r == Long.MAX_VALUE)) {
                            this.requested.addAndGet(-e);
                        }
                        missed = dw.addAndGet(-missed);
                    } else {
                        return;
                    }
                } while (missed != 0);
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<? super Subject<T, T>> a, Queue<Subject<T, T>> q) {
            if (a.isUnsubscribed()) {
                q.clear();
                return true;
            }
            if (d) {
                Throwable e = this.error;
                if (e != null) {
                    q.clear();
                    a.onError(e);
                    return true;
                } else if (empty) {
                    a.onCompleted();
                    return true;
                }
            }
            return false;
        }
    }

    static final class WindowSkip<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel = Subscriptions.create(this);
        int index;
        final int size;
        final int skip;
        Subject<T, T> window;
        final AtomicInteger wip = new AtomicInteger(1);

        final class WindowSkipProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 4625807964358024108L;

            WindowSkipProducer() {
            }

            public void request(long n) {
                if (n < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + n);
                } else if (n != 0) {
                    WindowSkip<T> parent = WindowSkip.this;
                    if (get() || !compareAndSet(false, true)) {
                        parent.request(BackpressureUtils.multiplyCap(n, (long) parent.skip));
                    } else {
                        parent.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(n, (long) parent.size), BackpressureUtils.multiplyCap((long) (parent.skip - parent.size), n - 1)));
                    }
                }
            }
        }

        public WindowSkip(Subscriber<? super Observable<T>> actual, int size, int skip) {
            this.actual = actual;
            this.size = size;
            this.skip = skip;
            add(this.cancel);
            request(0);
        }

        public void onNext(T t) {
            int i = this.index;
            Subject<T, T> w = this.window;
            if (i == 0) {
                this.wip.getAndIncrement();
                w = UnicastSubject.create(this.size, this);
                this.window = w;
                this.actual.onNext(w);
            }
            i++;
            if (w != null) {
                w.onNext(t);
            }
            if (i == this.size) {
                this.index = i;
                this.window = null;
                w.onCompleted();
            } else if (i == this.skip) {
                this.index = 0;
            } else {
                this.index = i;
            }
        }

        public void onError(Throwable e) {
            Subject<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onError(e);
            }
            this.actual.onError(e);
        }

        public void onCompleted() {
            Subject<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onCompleted();
            }
            this.actual.onCompleted();
        }

        Producer createProducer() {
            return new WindowSkipProducer();
        }

        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                unsubscribe();
            }
        }
    }

    public OperatorWindowWithSize(int size, int skip) {
        this.size = size;
        this.skip = skip;
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> child) {
        if (this.skip == this.size) {
            WindowExact<T> parent = new WindowExact(child, this.size);
            child.add(parent.cancel);
            child.setProducer(parent.createProducer());
            return parent;
        } else if (this.skip > this.size) {
            Subscriber parent2 = new WindowSkip(child, this.size, this.skip);
            child.add(parent2.cancel);
            child.setProducer(parent2.createProducer());
            return parent2;
        } else {
            Subscriber parent3 = new WindowOverlap(child, this.size, this.skip);
            child.add(parent3.cancel);
            child.setProducer(parent3.createProducer());
            return parent3;
        }
    }
}
