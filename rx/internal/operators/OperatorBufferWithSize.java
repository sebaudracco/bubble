package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;

public final class OperatorBufferWithSize<T> implements Observable$Operator<List<T>, T> {
    final int count;
    final int skip;

    static final class BufferExact<T> extends Subscriber<T> {
        final Subscriber<? super List<T>> actual;
        List<T> buffer;
        final int count;

        class C70961 implements Producer {
            C70961() {
            }

            public void request(long n) {
                if (n < 0) {
                    throw new IllegalArgumentException("n >= required but it was " + n);
                } else if (n != 0) {
                    BufferExact.this.request(BackpressureUtils.multiplyCap(n, (long) BufferExact.this.count));
                }
            }
        }

        public BufferExact(Subscriber<? super List<T>> actual, int count) {
            this.actual = actual;
            this.count = count;
            request(0);
        }

        public void onNext(T t) {
            List<T> b = this.buffer;
            if (b == null) {
                b = new ArrayList(this.count);
                this.buffer = b;
            }
            b.add(t);
            if (b.size() == this.count) {
                this.buffer = null;
                this.actual.onNext(b);
            }
        }

        public void onError(Throwable e) {
            this.buffer = null;
            this.actual.onError(e);
        }

        public void onCompleted() {
            List<T> b = this.buffer;
            if (b != null) {
                this.actual.onNext(b);
            }
            this.actual.onCompleted();
        }

        Producer createProducer() {
            return new C70961();
        }
    }

    static final class BufferOverlap<T> extends Subscriber<T> {
        final Subscriber<? super List<T>> actual;
        final int count;
        long index;
        long produced;
        final ArrayDeque<List<T>> queue = new ArrayDeque();
        final AtomicLong requested = new AtomicLong();
        final int skip;

        final class BufferOverlapProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = -4015894850868853147L;

            BufferOverlapProducer() {
            }

            public void request(long n) {
                BufferOverlap<T> parent = BufferOverlap.this;
                if (BackpressureUtils.postCompleteRequest(parent.requested, n, parent.queue, parent.actual) && n != 0) {
                    if (get() || !compareAndSet(false, true)) {
                        parent.request(BackpressureUtils.multiplyCap((long) parent.skip, n));
                    } else {
                        parent.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap((long) parent.skip, n - 1), (long) parent.count));
                    }
                }
            }
        }

        public BufferOverlap(Subscriber<? super List<T>> actual, int count, int skip) {
            this.actual = actual;
            this.count = count;
            this.skip = skip;
            request(0);
        }

        public void onNext(T t) {
            long i = this.index;
            if (i == 0) {
                this.queue.offer(new ArrayList(this.count));
            }
            i++;
            if (i == ((long) this.skip)) {
                this.index = 0;
            } else {
                this.index = i;
            }
            Iterator i$ = this.queue.iterator();
            while (i$.hasNext()) {
                ((List) i$.next()).add(t);
            }
            List<T> b = (List) this.queue.peek();
            if (b != null && b.size() == this.count) {
                this.queue.poll();
                this.produced++;
                this.actual.onNext(b);
            }
        }

        public void onError(Throwable e) {
            this.queue.clear();
            this.actual.onError(e);
        }

        public void onCompleted() {
            long p = this.produced;
            if (p != 0) {
                if (p > this.requested.get()) {
                    this.actual.onError(new MissingBackpressureException("More produced than requested? " + p));
                    return;
                }
                this.requested.addAndGet(-p);
            }
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual);
        }

        Producer createProducer() {
            return new BufferOverlapProducer();
        }
    }

    static final class BufferSkip<T> extends Subscriber<T> {
        final Subscriber<? super List<T>> actual;
        List<T> buffer;
        final int count;
        long index;
        final int skip;

        final class BufferSkipProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 3428177408082367154L;

            BufferSkipProducer() {
            }

            public void request(long n) {
                if (n < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + n);
                } else if (n != 0) {
                    BufferSkip<T> parent = BufferSkip.this;
                    if (get() || !compareAndSet(false, true)) {
                        parent.request(BackpressureUtils.multiplyCap(n, (long) parent.skip));
                    } else {
                        parent.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(n, (long) parent.count), BackpressureUtils.multiplyCap((long) (parent.skip - parent.count), n - 1)));
                    }
                }
            }
        }

        public BufferSkip(Subscriber<? super List<T>> actual, int count, int skip) {
            this.actual = actual;
            this.count = count;
            this.skip = skip;
            request(0);
        }

        public void onNext(T t) {
            long i = this.index;
            List<T> b = this.buffer;
            if (i == 0) {
                b = new ArrayList(this.count);
                this.buffer = b;
            }
            i++;
            if (i == ((long) this.skip)) {
                this.index = 0;
            } else {
                this.index = i;
            }
            if (b != null) {
                b.add(t);
                if (b.size() == this.count) {
                    this.buffer = null;
                    this.actual.onNext(b);
                }
            }
        }

        public void onError(Throwable e) {
            this.buffer = null;
            this.actual.onError(e);
        }

        public void onCompleted() {
            List<T> b = this.buffer;
            if (b != null) {
                this.buffer = null;
                this.actual.onNext(b);
            }
            this.actual.onCompleted();
        }

        Producer createProducer() {
            return new BufferSkipProducer();
        }
    }

    public OperatorBufferWithSize(int count, int skip) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        } else if (skip <= 0) {
            throw new IllegalArgumentException("skip must be greater than 0");
        } else {
            this.count = count;
            this.skip = skip;
        }
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> child) {
        if (this.skip == this.count) {
            BufferExact<T> parent = new BufferExact(child, this.count);
            child.add(parent);
            child.setProducer(parent.createProducer());
            return parent;
        } else if (this.skip > this.count) {
            Subscriber parent2 = new BufferSkip(child, this.count, this.skip);
            child.add(parent2);
            child.setProducer(parent2.createProducer());
            return parent2;
        } else {
            Subscriber parent3 = new BufferOverlap(child, this.count, this.skip);
            child.add(parent3);
            child.setProducer(parent3.createProducer());
            return parent3;
        }
    }
}
