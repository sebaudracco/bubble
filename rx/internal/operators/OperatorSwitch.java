package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

public final class OperatorSwitch<T> implements Observable$Operator<T, Observable<? extends T>> {
    final boolean delayError;

    static final class Holder {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch(false);

        Holder() {
        }
    }

    static final class HolderDelayError {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch(true);

        HolderDelayError() {
        }
    }

    static final class InnerSubscriber<T> extends Subscriber<T> {
        private final long id;
        private final SwitchSubscriber<T> parent;

        InnerSubscriber(long id, SwitchSubscriber<T> parent) {
            this.id = id;
            this.parent = parent;
        }

        public void setProducer(Producer p) {
            this.parent.innerProducer(p, this.id);
        }

        public void onNext(T t) {
            this.parent.emit(t, this);
        }

        public void onError(Throwable e) {
            this.parent.error(e, this.id);
        }

        public void onCompleted() {
            this.parent.complete(this.id);
        }
    }

    static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final Throwable TERMINAL_ERROR = new Throwable("Terminal error");
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        final AtomicLong index;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        final NotificationLite<T> nl;
        Producer producer;
        final SpscLinkedArrayQueue<Object> queue;
        long requested;
        final SerialSubscription serial = new SerialSubscription();

        class C71711 implements Action0 {
            C71711() {
            }

            public void call() {
                SwitchSubscriber.this.clearProducer();
            }
        }

        class C71722 implements Producer {
            C71722() {
            }

            public void request(long n) {
                if (n > 0) {
                    SwitchSubscriber.this.childRequested(n);
                } else if (n < 0) {
                    throw new IllegalArgumentException("n >= 0 expected but it was " + n);
                }
            }
        }

        SwitchSubscriber(Subscriber<? super T> child, boolean delayError) {
            this.child = child;
            this.delayError = delayError;
            this.index = new AtomicLong();
            this.queue = new SpscLinkedArrayQueue(RxRingBuffer.SIZE);
            this.nl = NotificationLite.instance();
        }

        void init() {
            this.child.add(this.serial);
            this.child.add(Subscriptions.create(new C71711()));
            this.child.setProducer(new C71722());
        }

        void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }

        public void onNext(Observable<? extends T> t) {
            InnerSubscriber<T> inner;
            long id = this.index.incrementAndGet();
            Subscription s = this.serial.get();
            if (s != null) {
                s.unsubscribe();
            }
            synchronized (this) {
                inner = new InnerSubscriber(id, this);
                this.innerActive = true;
                this.producer = null;
            }
            this.serial.set(inner);
            t.unsafeSubscribe(inner);
        }

        public void onError(Throwable e) {
            synchronized (this) {
                boolean success = updateError(e);
            }
            if (success) {
                this.mainDone = true;
                drain();
                return;
            }
            pluginError(e);
        }

        boolean updateError(Throwable next) {
            Throwable e = this.error;
            if (e == TERMINAL_ERROR) {
                return false;
            }
            if (e == null) {
                this.error = next;
            } else if (e instanceof CompositeException) {
                List<Throwable> list = new ArrayList(((CompositeException) e).getExceptions());
                list.add(next);
                this.error = new CompositeException(list);
            } else {
                this.error = new CompositeException(new Throwable[]{e, next});
            }
            return true;
        }

        public void onCompleted() {
            this.mainDone = true;
            drain();
        }

        void emit(T value, InnerSubscriber<T> inner) {
            synchronized (this) {
                if (this.index.get() != inner.id) {
                    return;
                }
                this.queue.offer(inner, this.nl.next(value));
                drain();
            }
        }

        void error(Throwable e, long id) {
            boolean success;
            synchronized (this) {
                if (this.index.get() == id) {
                    success = updateError(e);
                    this.innerActive = false;
                    this.producer = null;
                } else {
                    success = true;
                }
            }
            if (success) {
                drain();
            } else {
                pluginError(e);
            }
        }

        void complete(long id) {
            synchronized (this) {
                if (this.index.get() != id) {
                    return;
                }
                this.innerActive = false;
                this.producer = null;
                drain();
            }
        }

        void pluginError(Throwable e) {
            RxJavaHooks.onError(e);
        }

        void innerProducer(Producer p, long id) {
            synchronized (this) {
                if (this.index.get() != id) {
                    return;
                }
                long n = this.requested;
                this.producer = p;
                p.request(n);
            }
        }

        void childRequested(long n) {
            synchronized (this) {
                Producer p = this.producer;
                this.requested = BackpressureUtils.addCap(this.requested, n);
            }
            if (p != null) {
                p.request(n);
            }
            drain();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drain() {
            /*
            r25 = this;
            monitor-enter(r25);
            r0 = r25;
            r4 = r0.emitting;	 Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x000e;
        L_0x0007:
            r4 = 1;
            r0 = r25;
            r0.missed = r4;	 Catch:{ all -> 0x00a8 }
            monitor-exit(r25);	 Catch:{ all -> 0x00a8 }
        L_0x000d:
            return;
        L_0x000e:
            r4 = 1;
            r0 = r25;
            r0.emitting = r4;	 Catch:{ all -> 0x00a8 }
            r0 = r25;
            r6 = r0.innerActive;	 Catch:{ all -> 0x00a8 }
            r0 = r25;
            r0 = r0.requested;	 Catch:{ all -> 0x00a8 }
            r22 = r0;
            r0 = r25;
            r7 = r0.error;	 Catch:{ all -> 0x00a8 }
            if (r7 == 0) goto L_0x0033;
        L_0x0023:
            r4 = TERMINAL_ERROR;	 Catch:{ all -> 0x00a8 }
            if (r7 == r4) goto L_0x0033;
        L_0x0027:
            r0 = r25;
            r4 = r0.delayError;	 Catch:{ all -> 0x00a8 }
            if (r4 != 0) goto L_0x0033;
        L_0x002d:
            r4 = TERMINAL_ERROR;	 Catch:{ all -> 0x00a8 }
            r0 = r25;
            r0.error = r4;	 Catch:{ all -> 0x00a8 }
        L_0x0033:
            monitor-exit(r25);	 Catch:{ all -> 0x00a8 }
            r0 = r25;
            r8 = r0.queue;
            r0 = r25;
            r0 = r0.index;
            r19 = r0;
            r0 = r25;
            r9 = r0.child;
            r0 = r25;
            r5 = r0.mainDone;
        L_0x0046:
            r20 = 0;
        L_0x0048:
            r4 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1));
            if (r4 == 0) goto L_0x0060;
        L_0x004c:
            r4 = r9.isUnsubscribed();
            if (r4 != 0) goto L_0x000d;
        L_0x0052:
            r10 = r8.isEmpty();
            r4 = r25;
            r4 = r4.checkTerminated(r5, r6, r7, r8, r9, r10);
            if (r4 != 0) goto L_0x000d;
        L_0x005e:
            if (r10 == 0) goto L_0x00ab;
        L_0x0060:
            r4 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1));
            if (r4 != 0) goto L_0x007f;
        L_0x0064:
            r4 = r9.isUnsubscribed();
            if (r4 != 0) goto L_0x000d;
        L_0x006a:
            r0 = r25;
            r12 = r0.mainDone;
            r17 = r8.isEmpty();
            r11 = r25;
            r13 = r6;
            r14 = r7;
            r15 = r8;
            r16 = r9;
            r4 = r11.checkTerminated(r12, r13, r14, r15, r16, r17);
            if (r4 != 0) goto L_0x000d;
        L_0x007f:
            monitor-enter(r25);
            r0 = r25;
            r0 = r0.requested;	 Catch:{ all -> 0x00a5 }
            r22 = r0;
            r12 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r4 = (r22 > r12 ? 1 : (r22 == r12 ? 0 : -1));
            if (r4 == 0) goto L_0x0097;
        L_0x008f:
            r22 = r22 - r20;
            r0 = r22;
            r2 = r25;
            r2.requested = r0;	 Catch:{ all -> 0x00a5 }
        L_0x0097:
            r0 = r25;
            r4 = r0.missed;	 Catch:{ all -> 0x00a5 }
            if (r4 != 0) goto L_0x00d4;
        L_0x009d:
            r4 = 0;
            r0 = r25;
            r0.emitting = r4;	 Catch:{ all -> 0x00a5 }
            monitor-exit(r25);	 Catch:{ all -> 0x00a5 }
            goto L_0x000d;
        L_0x00a5:
            r4 = move-exception;
            monitor-exit(r25);	 Catch:{ all -> 0x00a5 }
            throw r4;
        L_0x00a8:
            r4 = move-exception;
            monitor-exit(r25);	 Catch:{ all -> 0x00a8 }
            throw r4;
        L_0x00ab:
            r18 = r8.poll();
            r18 = (rx.internal.operators.OperatorSwitch.InnerSubscriber) r18;
            r0 = r25;
            r4 = r0.nl;
            r11 = r8.poll();
            r24 = r4.getValue(r11);
            r12 = r19.get();
            r14 = r18.id;
            r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
            if (r4 != 0) goto L_0x0048;
        L_0x00c9:
            r0 = r24;
            r9.onNext(r0);
            r12 = 1;
            r20 = r20 + r12;
            goto L_0x0048;
        L_0x00d4:
            r4 = 0;
            r0 = r25;
            r0.missed = r4;	 Catch:{ all -> 0x00a5 }
            r0 = r25;
            r5 = r0.mainDone;	 Catch:{ all -> 0x00a5 }
            r0 = r25;
            r6 = r0.innerActive;	 Catch:{ all -> 0x00a5 }
            r0 = r25;
            r7 = r0.error;	 Catch:{ all -> 0x00a5 }
            if (r7 == 0) goto L_0x00f7;
        L_0x00e7:
            r4 = TERMINAL_ERROR;	 Catch:{ all -> 0x00a5 }
            if (r7 == r4) goto L_0x00f7;
        L_0x00eb:
            r0 = r25;
            r4 = r0.delayError;	 Catch:{ all -> 0x00a5 }
            if (r4 != 0) goto L_0x00f7;
        L_0x00f1:
            r4 = TERMINAL_ERROR;	 Catch:{ all -> 0x00a5 }
            r0 = r25;
            r0.error = r4;	 Catch:{ all -> 0x00a5 }
        L_0x00f7:
            monitor-exit(r25);	 Catch:{ all -> 0x00a5 }
            goto L_0x0046;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorSwitch.SwitchSubscriber.drain():void");
        }

        protected boolean checkTerminated(boolean localMainDone, boolean localInnerActive, Throwable localError, SpscLinkedArrayQueue<Object> localQueue, Subscriber<? super T> localChild, boolean empty) {
            if (this.delayError) {
                if (localMainDone && !localInnerActive && empty) {
                    if (localError != null) {
                        localChild.onError(localError);
                        return true;
                    }
                    localChild.onCompleted();
                    return true;
                }
            } else if (localError != null) {
                localQueue.clear();
                localChild.onError(localError);
                return true;
            } else if (localMainDone && !localInnerActive && empty) {
                localChild.onCompleted();
                return true;
            }
            return false;
        }
    }

    public static <T> OperatorSwitch<T> instance(boolean delayError) {
        if (delayError) {
            return HolderDelayError.INSTANCE;
        }
        return Holder.INSTANCE;
    }

    OperatorSwitch(boolean delayError) {
        this.delayError = delayError;
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> child) {
        SwitchSubscriber<T> sws = new SwitchSubscriber(child, this.delayError);
        child.add(sws);
        sws.init();
        return sws;
    }
}
