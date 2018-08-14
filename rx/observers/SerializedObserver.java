package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;

public class SerializedObserver<T> implements Observer<T> {
    private final Observer<? super T> actual;
    private boolean emitting;
    private final NotificationLite<T> nl = NotificationLite.instance();
    private FastList queue;
    private volatile boolean terminated;

    static final class FastList {
        Object[] array;
        int size;

        FastList() {
        }

        public void add(Object o) {
            int s = this.size;
            Object[] a = this.array;
            if (a == null) {
                a = new Object[16];
                this.array = a;
            } else if (s == a.length) {
                Object[] array2 = new Object[((s >> 2) + s)];
                System.arraycopy(a, 0, array2, 0, s);
                a = array2;
                this.array = a;
            }
            a[s] = o;
            this.size = s + 1;
        }
    }

    public SerializedObserver(Observer<? super T> s) {
        this.actual = s;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNext(T r10) {
        /*
        r9 = this;
        r8 = 1;
        r6 = r9.terminated;
        if (r6 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        monitor-enter(r9);
        r6 = r9.terminated;	 Catch:{ all -> 0x000d }
        if (r6 == 0) goto L_0x0010;
    L_0x000b:
        monitor-exit(r9);	 Catch:{ all -> 0x000d }
        goto L_0x0005;
    L_0x000d:
        r6 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x000d }
        throw r6;
    L_0x0010:
        r6 = r9.emitting;	 Catch:{ all -> 0x000d }
        if (r6 == 0) goto L_0x002a;
    L_0x0014:
        r4 = r9.queue;	 Catch:{ all -> 0x000d }
        if (r4 != 0) goto L_0x001f;
    L_0x0018:
        r4 = new rx.observers.SerializedObserver$FastList;	 Catch:{ all -> 0x000d }
        r4.<init>();	 Catch:{ all -> 0x000d }
        r9.queue = r4;	 Catch:{ all -> 0x000d }
    L_0x001f:
        r6 = r9.nl;	 Catch:{ all -> 0x000d }
        r6 = r6.next(r10);	 Catch:{ all -> 0x000d }
        r4.add(r6);	 Catch:{ all -> 0x000d }
        monitor-exit(r9);	 Catch:{ all -> 0x000d }
        goto L_0x0005;
    L_0x002a:
        r6 = 1;
        r9.emitting = r6;	 Catch:{ all -> 0x000d }
        monitor-exit(r9);	 Catch:{ all -> 0x000d }
        r6 = r9.actual;	 Catch:{ Throwable -> 0x0040 }
        r6.onNext(r10);	 Catch:{ Throwable -> 0x0040 }
    L_0x0033:
        monitor-enter(r9);
        r4 = r9.queue;	 Catch:{ all -> 0x003d }
        if (r4 != 0) goto L_0x0049;
    L_0x0038:
        r6 = 0;
        r9.emitting = r6;	 Catch:{ all -> 0x003d }
        monitor-exit(r9);	 Catch:{ all -> 0x003d }
        goto L_0x0005;
    L_0x003d:
        r6 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x003d }
        throw r6;
    L_0x0040:
        r1 = move-exception;
        r9.terminated = r8;
        r6 = r9.actual;
        rx.exceptions.Exceptions.throwOrReport(r1, r6, r10);
        goto L_0x0005;
    L_0x0049:
        r6 = 0;
        r9.queue = r6;	 Catch:{ all -> 0x003d }
        monitor-exit(r9);	 Catch:{ all -> 0x003d }
        r0 = r4.array;
        r3 = r0.length;
        r2 = 0;
    L_0x0051:
        if (r2 >= r3) goto L_0x0033;
    L_0x0053:
        r5 = r0[r2];
        if (r5 == 0) goto L_0x0033;
    L_0x0057:
        r6 = r9.nl;	 Catch:{ Throwable -> 0x0065 }
        r7 = r9.actual;	 Catch:{ Throwable -> 0x0065 }
        r6 = r6.accept(r7, r5);	 Catch:{ Throwable -> 0x0065 }
        if (r6 == 0) goto L_0x0075;
    L_0x0061:
        r6 = 1;
        r9.terminated = r6;	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0005;
    L_0x0065:
        r1 = move-exception;
        r9.terminated = r8;
        rx.exceptions.Exceptions.throwIfFatal(r1);
        r6 = r9.actual;
        r7 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r10);
        r6.onError(r7);
        goto L_0x0005;
    L_0x0075:
        r2 = r2 + 1;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.observers.SerializedObserver.onNext(java.lang.Object):void");
    }

    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (!this.terminated) {
            synchronized (this) {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                if (this.emitting) {
                    FastList list = this.queue;
                    if (list == null) {
                        list = new FastList();
                        this.queue = list;
                    }
                    list.add(this.nl.error(e));
                    return;
                }
                this.emitting = true;
                this.actual.onError(e);
            }
        }
    }

    public void onCompleted() {
        if (!this.terminated) {
            synchronized (this) {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                if (this.emitting) {
                    FastList list = this.queue;
                    if (list == null) {
                        list = new FastList();
                        this.queue = list;
                    }
                    list.add(this.nl.completed());
                    return;
                }
                this.emitting = true;
                this.actual.onCompleted();
            }
        }
    }
}
