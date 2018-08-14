package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

public final class ReplaySubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    final ReplayState<T> state;

    interface ReplayBuffer<T> {
        void complete();

        void drain(ReplayProducer<T> replayProducer);

        Throwable error();

        void error(Throwable th);

        boolean isComplete();

        boolean isEmpty();

        T last();

        void next(T t);

        int size();

        T[] toArray(T[] tArr);
    }

    static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
        private static final long serialVersionUID = -5006209596735204567L;
        final Subscriber<? super T> actual;
        int index;
        Object node;
        final AtomicLong requested = new AtomicLong();
        final ReplayState<T> state;
        int tailIndex;

        public ReplayProducer(Subscriber<? super T> actual, ReplayState<T> state) {
            this.actual = actual;
            this.state = state;
        }

        public void unsubscribe() {
            this.state.remove(this);
        }

        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        public void request(long n) {
            if (n > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                this.state.buffer.drain(this);
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
        }
    }

    static final class ReplaySizeAndTimeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile TimedNode<T> head;
        final int limit;
        final long maxAgeMillis;
        final Scheduler scheduler;
        int size;
        TimedNode<T> tail;

        static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final long timestamp;
            final T value;

            public TimedNode(T value, long timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        public ReplaySizeAndTimeBoundBuffer(int limit, long maxAgeMillis, Scheduler scheduler) {
            this.limit = limit;
            TimedNode<T> n = new TimedNode(null, 0);
            this.tail = n;
            this.head = n;
            this.maxAgeMillis = maxAgeMillis;
            this.scheduler = scheduler;
        }

        public void next(T value) {
            long now = this.scheduler.now();
            TimedNode<T> n = new TimedNode(value, now);
            this.tail.set(n);
            this.tail = n;
            now -= this.maxAgeMillis;
            int s = this.size;
            TimedNode<T> h0 = this.head;
            TimedNode<T> h = h0;
            if (s == this.limit) {
                h = (TimedNode) h.get();
            } else {
                s++;
            }
            while (true) {
                n = (TimedNode) h.get();
                if (n == null || n.timestamp > now) {
                    this.size = s;
                } else {
                    h = n;
                    s--;
                }
            }
            this.size = s;
            if (h != h0) {
                this.head = h;
            }
        }

        public void error(Throwable ex) {
            evictFinal();
            this.error = ex;
            this.done = true;
        }

        public void complete() {
            evictFinal();
            this.done = true;
        }

        void evictFinal() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> h0 = this.head;
            TimedNode<T> h = h0;
            while (true) {
                TimedNode<T> n = (TimedNode) h.get();
                if (n != null && n.timestamp <= now) {
                    h = n;
                } else if (h0 != h) {
                    this.head = h;
                }
            }
            if (h0 != h) {
                this.head = h;
            }
        }

        TimedNode<T> latestHead() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> h = this.head;
            while (true) {
                TimedNode<T> n = (TimedNode) h.get();
                if (n == null || n.timestamp > now) {
                    return h;
                }
                h = n;
            }
            return h;
        }

        public void drain(ReplayProducer<T> rp) {
            if (rp.getAndIncrement() == 0) {
                Subscriber<? super T> a = rp.actual;
                int missed = 1;
                do {
                    boolean d;
                    boolean empty;
                    Throwable ex;
                    long r = rp.requested.get();
                    long e = 0;
                    TimedNode<T> node = rp.node;
                    if (node == null) {
                        node = latestHead();
                    }
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        d = this.done;
                        TimedNode<T> next = (TimedNode) node.get();
                        empty = next == null;
                        if (d && empty) {
                            rp.node = null;
                            ex = this.error;
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(next.value);
                            e++;
                            node = next;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        d = this.done;
                        empty = node.get() == null;
                        if (d && empty) {
                            rp.node = null;
                            ex = this.error;
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        }
                    }
                    if (!(e == 0 || r == Long.MAX_VALUE)) {
                        BackpressureUtils.produced(rp.requested, e);
                    }
                    rp.node = node;
                    missed = rp.addAndGet(-missed);
                } while (missed != 0);
            }
        }

        public boolean isComplete() {
            return this.done;
        }

        public Throwable error() {
            return this.error;
        }

        public T last() {
            TimedNode<T> h = latestHead();
            while (true) {
                TimedNode<T> n = (TimedNode) h.get();
                if (n == null) {
                    return h.value;
                }
                h = n;
            }
        }

        public int size() {
            int s = 0;
            TimedNode<T> n = (TimedNode) latestHead().get();
            while (n != null && s != Integer.MAX_VALUE) {
                n = (TimedNode) n.get();
                s++;
            }
            return s;
        }

        public boolean isEmpty() {
            return latestHead().get() == null;
        }

        public T[] toArray(T[] a) {
            List<T> list = new ArrayList();
            for (TimedNode<T> n = (TimedNode) latestHead().get(); n != null; n = (TimedNode) n.get()) {
                list.add(n.value);
            }
            return list.toArray(a);
        }
    }

    static final class ReplaySizeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile Node<T> head;
        final int limit;
        int size;
        Node<T> tail;

        static final class Node<T> extends AtomicReference<Node<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final T value;

            public Node(T value) {
                this.value = value;
            }
        }

        public ReplaySizeBoundBuffer(int limit) {
            this.limit = limit;
            Node<T> n = new Node(null);
            this.tail = n;
            this.head = n;
        }

        public void next(T value) {
            Node<T> n = new Node(value);
            this.tail.set(n);
            this.tail = n;
            int s = this.size;
            if (s == this.limit) {
                this.head = (Node) this.head.get();
            } else {
                this.size = s + 1;
            }
        }

        public void error(Throwable ex) {
            this.error = ex;
            this.done = true;
        }

        public void complete() {
            this.done = true;
        }

        public void drain(ReplayProducer<T> rp) {
            if (rp.getAndIncrement() == 0) {
                Subscriber<? super T> a = rp.actual;
                int missed = 1;
                do {
                    boolean d;
                    boolean empty;
                    Throwable ex;
                    long r = rp.requested.get();
                    long e = 0;
                    Node<T> node = rp.node;
                    if (node == null) {
                        node = this.head;
                    }
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        d = this.done;
                        Node<T> next = (Node) node.get();
                        empty = next == null;
                        if (d && empty) {
                            rp.node = null;
                            ex = this.error;
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(next.value);
                            e++;
                            node = next;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        d = this.done;
                        empty = node.get() == null;
                        if (d && empty) {
                            rp.node = null;
                            ex = this.error;
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        }
                    }
                    if (!(e == 0 || r == Long.MAX_VALUE)) {
                        BackpressureUtils.produced(rp.requested, e);
                    }
                    rp.node = node;
                    missed = rp.addAndGet(-missed);
                } while (missed != 0);
            }
        }

        public boolean isComplete() {
            return this.done;
        }

        public Throwable error() {
            return this.error;
        }

        public T last() {
            Node<T> h = this.head;
            while (true) {
                Node<T> n = (Node) h.get();
                if (n == null) {
                    return h.value;
                }
                h = n;
            }
        }

        public int size() {
            int s = 0;
            Node<T> n = (Node) this.head.get();
            while (n != null && s != Integer.MAX_VALUE) {
                n = (Node) n.get();
                s++;
            }
            return s;
        }

        public boolean isEmpty() {
            return this.head.get() == null;
        }

        public T[] toArray(T[] a) {
            List<T> list = new ArrayList();
            for (Node<T> n = (Node) this.head.get(); n != null; n = (Node) n.get()) {
                list.add(n.value);
            }
            return list.toArray(a);
        }
    }

    static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements Observable$OnSubscribe<T>, Observer<T> {
        static final ReplayProducer[] EMPTY = new ReplayProducer[0];
        static final ReplayProducer[] TERMINATED = new ReplayProducer[0];
        private static final long serialVersionUID = 5952362471246910544L;
        final ReplayBuffer<T> buffer;

        public ReplayState(ReplayBuffer<T> buffer) {
            this.buffer = buffer;
            lazySet(EMPTY);
        }

        public void call(Subscriber<? super T> t) {
            ReplayProducer<T> rp = new ReplayProducer(t, this);
            t.add(rp);
            t.setProducer(rp);
            if (add(rp) && rp.isUnsubscribed()) {
                remove(rp);
            } else {
                this.buffer.drain(rp);
            }
        }

        boolean add(ReplayProducer<T> rp) {
            ReplayProducer[] a;
            ReplayProducer<T>[] b;
            do {
                a = (ReplayProducer[]) get();
                if (a == TERMINATED) {
                    return false;
                }
                int n = a.length;
                b = new ReplayProducer[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = rp;
            } while (!compareAndSet(a, b));
            return true;
        }

        void remove(ReplayProducer<T> rp) {
            ReplayProducer[] a;
            ReplayProducer<T>[] b;
            do {
                a = (ReplayProducer[]) get();
                if (a != TERMINATED && a != EMPTY) {
                    int n = a.length;
                    int j = -1;
                    for (int i = 0; i < n; i++) {
                        if (a[i] == rp) {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (n == 1) {
                        b = EMPTY;
                    } else {
                        b = new ReplayProducer[(n - 1)];
                        System.arraycopy(a, 0, b, 0, j);
                        System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(a, b));
        }

        public void onNext(T t) {
            ReplayBuffer<T> b = this.buffer;
            b.next(t);
            for (ReplayProducer<T> rp : (ReplayProducer[]) get()) {
                b.drain(rp);
            }
        }

        public void onError(Throwable e) {
            ReplayBuffer<T> b = this.buffer;
            b.error(e);
            List<Throwable> errors = null;
            for (ReplayProducer<T> rp : (ReplayProducer[]) getAndSet(TERMINATED)) {
                try {
                    b.drain(rp);
                } catch (Throwable ex) {
                    if (errors == null) {
                        errors = new ArrayList();
                    }
                    errors.add(ex);
                }
            }
            Exceptions.throwIfAny(errors);
        }

        public void onCompleted() {
            ReplayBuffer<T> b = this.buffer;
            b.complete();
            for (ReplayProducer<T> rp : (ReplayProducer[]) getAndSet(TERMINATED)) {
                b.drain(rp);
            }
        }

        boolean isTerminated() {
            return get() == TERMINATED;
        }
    }

    static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T> {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;

        public ReplayUnboundedBuffer(int capacity) {
            this.capacity = capacity;
            Object[] objArr = new Object[(capacity + 1)];
            this.head = objArr;
            this.tail = objArr;
        }

        public void next(T t) {
            if (!this.done) {
                int i = this.tailIndex;
                Object[] a = this.tail;
                if (i == a.length - 1) {
                    Object[] b = new Object[a.length];
                    b[0] = t;
                    this.tailIndex = 1;
                    a[i] = b;
                    this.tail = b;
                } else {
                    a[i] = t;
                    this.tailIndex = i + 1;
                }
                this.size++;
            }
        }

        public void error(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.error = e;
            this.done = true;
        }

        public void complete() {
            this.done = true;
        }

        public void drain(ReplayProducer<T> rp) {
            if (rp.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = rp.actual;
                int n = this.capacity;
                do {
                    boolean d;
                    boolean empty;
                    Throwable ex;
                    long r = rp.requested.get();
                    long e = 0;
                    Object[] node = (Object[]) rp.node;
                    if (node == null) {
                        node = this.head;
                    }
                    int tailIndex = rp.tailIndex;
                    int index = rp.index;
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        d = this.done;
                        empty = index == this.size;
                        if (d && empty) {
                            rp.node = null;
                            ex = this.error;
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            if (tailIndex == n) {
                                node = (Object[]) node[tailIndex];
                                tailIndex = 0;
                            }
                            a.onNext(node[tailIndex]);
                            e++;
                            tailIndex++;
                            index++;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        d = this.done;
                        empty = index == this.size;
                        if (d && empty) {
                            rp.node = null;
                            ex = this.error;
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        }
                    }
                    if (!(e == 0 || r == Long.MAX_VALUE)) {
                        BackpressureUtils.produced(rp.requested, e);
                    }
                    rp.index = index;
                    rp.tailIndex = tailIndex;
                    rp.node = node;
                    missed = rp.addAndGet(-missed);
                } while (missed != 0);
            }
        }

        public boolean isComplete() {
            return this.done;
        }

        public Throwable error() {
            return this.error;
        }

        public T last() {
            int s = this.size;
            if (s == 0) {
                return null;
            }
            Object[] h = this.head;
            int n = this.capacity;
            while (s >= n) {
                h = (Object[]) h[n];
                s -= n;
            }
            return h[s - 1];
        }

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public T[] toArray(T[] a) {
            int s = this.size;
            if (a.length < s) {
                a = (Object[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), s));
            }
            Object[] h = this.head;
            int n = this.capacity;
            int j = 0;
            while (j + n < s) {
                System.arraycopy(h, 0, a, j, n);
                j += n;
                h = (Object[]) h[n];
            }
            System.arraycopy(h, 0, a, j, s - j);
            if (a.length > s) {
                a[s] = null;
            }
            return a;
        }
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    public static <T> ReplaySubject<T> create(int capacity) {
        if (capacity > 0) {
            return new ReplaySubject(new ReplayState(new ReplayUnboundedBuffer(capacity)));
        }
        throw new IllegalArgumentException("capacity > 0 required but it was " + capacity);
    }

    static <T> ReplaySubject<T> createUnbounded() {
        return new ReplaySubject(new ReplayState(new ReplaySizeBoundBuffer(Integer.MAX_VALUE)));
    }

    static <T> ReplaySubject<T> createUnboundedTime() {
        return new ReplaySubject(new ReplayState(new ReplaySizeAndTimeBoundBuffer(Integer.MAX_VALUE, Long.MAX_VALUE, Schedulers.immediate())));
    }

    public static <T> ReplaySubject<T> createWithSize(int size) {
        return new ReplaySubject(new ReplayState(new ReplaySizeBoundBuffer(size)));
    }

    public static <T> ReplaySubject<T> createWithTime(long time, TimeUnit unit, Scheduler scheduler) {
        return createWithTimeAndSize(time, unit, Integer.MAX_VALUE, scheduler);
    }

    public static <T> ReplaySubject<T> createWithTimeAndSize(long time, TimeUnit unit, int size, Scheduler scheduler) {
        return new ReplaySubject(new ReplayState(new ReplaySizeAndTimeBoundBuffer(size, unit.toMillis(time), scheduler)));
    }

    ReplaySubject(ReplayState<T> state) {
        super(state);
        this.state = state;
    }

    public void onNext(T t) {
        this.state.onNext(t);
    }

    public void onError(Throwable e) {
        this.state.onError(e);
    }

    public void onCompleted() {
        this.state.onCompleted();
    }

    int subscriberCount() {
        return ((ReplayProducer[]) this.state.get()).length;
    }

    public boolean hasObservers() {
        return ((ReplayProducer[]) this.state.get()).length != 0;
    }

    public boolean hasThrowable() {
        return this.state.isTerminated() && this.state.buffer.error() != null;
    }

    public boolean hasCompleted() {
        return this.state.isTerminated() && this.state.buffer.error() == null;
    }

    public Throwable getThrowable() {
        if (this.state.isTerminated()) {
            return this.state.buffer.error();
        }
        return null;
    }

    public int size() {
        return this.state.buffer.size();
    }

    public boolean hasAnyValue() {
        return !this.state.buffer.isEmpty();
    }

    public boolean hasValue() {
        return hasAnyValue();
    }

    public T[] getValues(T[] a) {
        return this.state.buffer.toArray(a);
    }

    public Object[] getValues() {
        T[] r = getValues(EMPTY_ARRAY);
        if (r == EMPTY_ARRAY) {
            return new Object[0];
        }
        return r;
    }

    public T getValue() {
        return this.state.buffer.last();
    }
}
