package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Subscriber;

public final class OnSubscribeRange implements Observable$OnSubscribe<Integer> {
    private final int endIndex;
    private final int startIndex;

    static final class RangeProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = 4114392207069098388L;
        private final Subscriber<? super Integer> childSubscriber;
        private long currentIndex;
        private final int endOfRange;

        RangeProducer(Subscriber<? super Integer> childSubscriber, int startIndex, int endIndex) {
            this.childSubscriber = childSubscriber;
            this.currentIndex = (long) startIndex;
            this.endOfRange = endIndex;
        }

        public void request(long requestedAmount) {
            if (get() != Long.MAX_VALUE) {
                if (requestedAmount == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    fastPath();
                } else if (requestedAmount > 0 && BackpressureUtils.getAndAddRequest(this, requestedAmount) == 0) {
                    slowPath(requestedAmount);
                }
            }
        }

        void slowPath(long requestedAmount) {
            long emitted = 0;
            long endIndex = ((long) this.endOfRange) + 1;
            long index = this.currentIndex;
            Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            while (true) {
                if (emitted == requestedAmount || index == endIndex) {
                    if (!childSubscriber.isUnsubscribed()) {
                        if (index == endIndex) {
                            childSubscriber.onCompleted();
                            return;
                        }
                        requestedAmount = get();
                        if (requestedAmount == emitted) {
                            this.currentIndex = index;
                            requestedAmount = addAndGet(-emitted);
                            if (requestedAmount != 0) {
                                emitted = 0;
                            } else {
                                return;
                            }
                        }
                        continue;
                    } else {
                        return;
                    }
                } else if (!childSubscriber.isUnsubscribed()) {
                    childSubscriber.onNext(Integer.valueOf((int) index));
                    index++;
                    emitted++;
                } else {
                    return;
                }
            }
        }

        void fastPath() {
            long endIndex = ((long) this.endOfRange) + 1;
            Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            long index = this.currentIndex;
            while (index != endIndex) {
                if (!childSubscriber.isUnsubscribed()) {
                    childSubscriber.onNext(Integer.valueOf((int) index));
                    index++;
                } else {
                    return;
                }
            }
            if (!childSubscriber.isUnsubscribed()) {
                childSubscriber.onCompleted();
            }
        }
    }

    public OnSubscribeRange(int start, int end) {
        this.startIndex = start;
        this.endIndex = end;
    }

    public void call(Subscriber<? super Integer> childSubscriber) {
        childSubscriber.setProducer(new RangeProducer(childSubscriber, this.startIndex, this.endIndex));
    }
}
