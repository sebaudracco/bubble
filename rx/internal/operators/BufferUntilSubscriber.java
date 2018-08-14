package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public final class BufferUntilSubscriber<T> extends Subject<T, T> {
    static final Observer EMPTY_OBSERVER = new C70521();
    private boolean forward;
    final State<T> state;

    static class C70521 implements Observer {
        C70521() {
        }

        public void onCompleted() {
        }

        public void onError(Throwable e) {
        }

        public void onNext(Object t) {
        }
    }

    static final class OnSubscribeAction<T> implements Observable$OnSubscribe<T> {
        final State<T> state;

        class C70531 implements Action0 {
            C70531() {
            }

            public void call() {
                OnSubscribeAction.this.state.set(BufferUntilSubscriber.EMPTY_OBSERVER);
            }
        }

        public OnSubscribeAction(State<T> state) {
            this.state = state;
        }

        public void call(Subscriber<? super T> s) {
            if (this.state.casObserverRef(null, s)) {
                s.add(Subscriptions.create(new C70531()));
                boolean win = false;
                synchronized (this.state.guard) {
                    if (!this.state.emitting) {
                        this.state.emitting = true;
                        win = true;
                    }
                }
                if (win) {
                    NotificationLite<T> nl = NotificationLite.instance();
                    while (true) {
                        Object o = this.state.buffer.poll();
                        if (o != null) {
                            nl.accept((Observer) this.state.get(), o);
                        } else {
                            synchronized (this.state.guard) {
                                if (this.state.buffer.isEmpty()) {
                                    this.state.emitting = false;
                                    return;
                                }
                            }
                        }
                    }
                }
                return;
            }
            s.onError(new IllegalStateException("Only one subscriber allowed!"));
        }
    }

    static final class State<T> extends AtomicReference<Observer<? super T>> {
        private static final long serialVersionUID = 8026705089538090368L;
        final ConcurrentLinkedQueue<Object> buffer = new ConcurrentLinkedQueue();
        boolean emitting;
        final Object guard = new Object();
        final NotificationLite<T> nl = NotificationLite.instance();

        State() {
        }

        boolean casObserverRef(Observer<? super T> expected, Observer<? super T> next) {
            return compareAndSet(expected, next);
        }
    }

    public static <T> BufferUntilSubscriber<T> create() {
        return new BufferUntilSubscriber(new State());
    }

    private BufferUntilSubscriber(State<T> state) {
        super(new OnSubscribeAction(state));
        this.state = state;
    }

    private void emit(Object v) {
        synchronized (this.state.guard) {
            this.state.buffer.add(v);
            if (!(this.state.get() == null || this.state.emitting)) {
                this.forward = true;
                this.state.emitting = true;
            }
        }
        if (this.forward) {
            while (true) {
                Object o = this.state.buffer.poll();
                if (o != null) {
                    this.state.nl.accept((Observer) this.state.get(), o);
                } else {
                    return;
                }
            }
        }
    }

    public void onCompleted() {
        if (this.forward) {
            ((Observer) this.state.get()).onCompleted();
        } else {
            emit(this.state.nl.completed());
        }
    }

    public void onError(Throwable e) {
        if (this.forward) {
            ((Observer) this.state.get()).onError(e);
        } else {
            emit(this.state.nl.error(e));
        }
    }

    public void onNext(T t) {
        if (this.forward) {
            ((Observer) this.state.get()).onNext(t);
        } else {
            emit(this.state.nl.next(t));
        }
    }

    public boolean hasObservers() {
        boolean z;
        synchronized (this.state.guard) {
            z = this.state.get() != null;
        }
        return z;
    }
}
