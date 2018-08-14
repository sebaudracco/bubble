package rx.internal.operators;

import java.io.Serializable;
import rx.Notification$Kind;
import rx.Observer;

public final class NotificationLite<T> {
    private static final NotificationLite INSTANCE = new NotificationLite();
    private static final Object ON_COMPLETED_SENTINEL = new C70631();
    private static final Object ON_NEXT_NULL_SENTINEL = new C70642();

    static class C70631 implements Serializable {
        private static final long serialVersionUID = 1;

        C70631() {
        }

        public String toString() {
            return "Notification=>Completed";
        }
    }

    static class C70642 implements Serializable {
        private static final long serialVersionUID = 2;

        C70642() {
        }

        public String toString() {
            return "Notification=>NULL";
        }
    }

    static final class OnErrorSentinel implements Serializable {
        private static final long serialVersionUID = 3;
        final Throwable f12672e;

        public OnErrorSentinel(Throwable e) {
            this.f12672e = e;
        }

        public String toString() {
            return "Notification=>Error:" + this.f12672e;
        }
    }

    private NotificationLite() {
    }

    public static <T> NotificationLite<T> instance() {
        return INSTANCE;
    }

    public Object next(T t) {
        if (t == null) {
            return ON_NEXT_NULL_SENTINEL;
        }
        return t;
    }

    public Object completed() {
        return ON_COMPLETED_SENTINEL;
    }

    public Object error(Throwable e) {
        return new OnErrorSentinel(e);
    }

    public boolean accept(Observer<? super T> o, Object n) {
        if (n == ON_COMPLETED_SENTINEL) {
            o.onCompleted();
            return true;
        } else if (n == ON_NEXT_NULL_SENTINEL) {
            o.onNext(null);
            return false;
        } else if (n == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (n.getClass() == OnErrorSentinel.class) {
            o.onError(((OnErrorSentinel) n).f12672e);
            return true;
        } else {
            o.onNext(n);
            return false;
        }
    }

    public boolean isCompleted(Object n) {
        return n == ON_COMPLETED_SENTINEL;
    }

    public boolean isError(Object n) {
        return n instanceof OnErrorSentinel;
    }

    public boolean isNull(Object n) {
        return n == ON_NEXT_NULL_SENTINEL;
    }

    public boolean isNext(Object n) {
        return (n == null || isError(n) || isCompleted(n)) ? false : true;
    }

    public Notification$Kind kind(Object n) {
        if (n == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (n == ON_COMPLETED_SENTINEL) {
            return Notification$Kind.OnCompleted;
        } else {
            if (n instanceof OnErrorSentinel) {
                return Notification$Kind.OnError;
            }
            return Notification$Kind.OnNext;
        }
    }

    public T getValue(Object n) {
        return n == ON_NEXT_NULL_SENTINEL ? null : n;
    }

    public Throwable getError(Object n) {
        return ((OnErrorSentinel) n).f12672e;
    }
}
