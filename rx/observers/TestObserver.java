package rx.observers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Notification;
import rx.Observer;
import rx.exceptions.CompositeException;

@Deprecated
public class TestObserver<T> implements Observer<T> {
    private static final Observer<Object> INERT = new 1();
    private final Observer<T> delegate;
    private final List<Notification<T>> onCompletedEvents;
    private final List<Throwable> onErrorEvents;
    private final List<T> onNextEvents;

    public TestObserver(Observer<T> delegate) {
        this.onNextEvents = new ArrayList();
        this.onErrorEvents = new ArrayList();
        this.onCompletedEvents = new ArrayList();
        this.delegate = delegate;
    }

    public TestObserver() {
        this.onNextEvents = new ArrayList();
        this.onErrorEvents = new ArrayList();
        this.onCompletedEvents = new ArrayList();
        this.delegate = INERT;
    }

    public void onCompleted() {
        this.onCompletedEvents.add(Notification.createOnCompleted());
        this.delegate.onCompleted();
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return Collections.unmodifiableList(this.onCompletedEvents);
    }

    public void onError(Throwable e) {
        this.onErrorEvents.add(e);
        this.delegate.onError(e);
    }

    public List<Throwable> getOnErrorEvents() {
        return Collections.unmodifiableList(this.onErrorEvents);
    }

    public void onNext(T t) {
        this.onNextEvents.add(t);
        this.delegate.onNext(t);
    }

    public List<T> getOnNextEvents() {
        return Collections.unmodifiableList(this.onNextEvents);
    }

    public List<Object> getEvents() {
        ArrayList<Object> events = new ArrayList();
        events.add(this.onNextEvents);
        events.add(this.onErrorEvents);
        events.add(this.onCompletedEvents);
        return Collections.unmodifiableList(events);
    }

    public void assertReceivedOnNext(List<T> items) {
        if (this.onNextEvents.size() != items.size()) {
            assertionError("Number of items does not match. Provided: " + items.size() + "  Actual: " + this.onNextEvents.size() + ".\n" + "Provided values: " + items + "\n" + "Actual values: " + this.onNextEvents + "\n");
        }
        for (int i = 0; i < items.size(); i++) {
            T expected = items.get(i);
            T actual = this.onNextEvents.get(i);
            if (expected == null) {
                if (actual != null) {
                    assertionError("Value at index: " + i + " expected to be [null] but was: [" + actual + "]\n");
                }
            } else if (!expected.equals(actual)) {
                assertionError("Value at index: " + i + " expected to be [" + expected + "] (" + expected.getClass().getSimpleName() + ") but was: [" + actual + "] (" + (actual != null ? actual.getClass().getSimpleName() : "null") + ")\n");
            }
        }
    }

    public void assertTerminalEvent() {
        if (this.onErrorEvents.size() > 1) {
            assertionError("Too many onError events: " + this.onErrorEvents.size());
        }
        if (this.onCompletedEvents.size() > 1) {
            assertionError("Too many onCompleted events: " + this.onCompletedEvents.size());
        }
        if (this.onCompletedEvents.size() == 1 && this.onErrorEvents.size() == 1) {
            assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.onCompletedEvents.isEmpty() && this.onErrorEvents.isEmpty()) {
            assertionError("No terminal events received.");
        }
    }

    final void assertionError(String message) {
        StringBuilder b = new StringBuilder(message.length() + 32);
        b.append(message).append(" (");
        int c = this.onCompletedEvents.size();
        b.append(c).append(" completion");
        if (c != 1) {
            b.append('s');
        }
        b.append(')');
        if (!this.onErrorEvents.isEmpty()) {
            int size = this.onErrorEvents.size();
            b.append(" (+").append(size).append(" error");
            if (size != 1) {
                b.append('s');
            }
            b.append(')');
        }
        AssertionError ae = new AssertionError(b.toString());
        if (!this.onErrorEvents.isEmpty()) {
            if (this.onErrorEvents.size() == 1) {
                ae.initCause((Throwable) this.onErrorEvents.get(0));
            } else {
                ae.initCause(new CompositeException(this.onErrorEvents));
            }
        }
        throw ae;
    }
}
