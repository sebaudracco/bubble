package rx.internal.operators;

import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

public final class CompletableFlatMapSingleToCompletable<T> implements OnSubscribe {
    final Func1<? super T, ? extends Completable> mapper;
    final Single<T> source;

    static final class SourceSubscriber<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        final CompletableSubscriber actual;
        final Func1<? super T, ? extends Completable> mapper;

        public SourceSubscriber(CompletableSubscriber actual, Func1<? super T, ? extends Completable> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        public void onSuccess(T value) {
            try {
                Completable c = (Completable) this.mapper.call(value);
                if (c == null) {
                    onError(new NullPointerException("The mapper returned a null Completable"));
                } else {
                    c.subscribe((CompletableSubscriber) this);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                onError(ex);
            }
        }

        public void onError(Throwable error) {
            this.actual.onError(error);
        }

        public void onCompleted() {
            this.actual.onCompleted();
        }

        public void onSubscribe(Subscription d) {
            add(d);
        }
    }

    public CompletableFlatMapSingleToCompletable(Single<T> source, Func1<? super T, ? extends Completable> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    public void call(CompletableSubscriber t) {
        SingleSubscriber parent = new SourceSubscriber(t, this.mapper);
        t.onSubscribe(parent);
        this.source.subscribe(parent);
    }
}
