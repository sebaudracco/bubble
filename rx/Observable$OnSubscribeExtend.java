package rx;

final class Observable$OnSubscribeExtend<T> implements Observable$OnSubscribe<T> {
    final Observable<T> parent;

    Observable$OnSubscribeExtend(Observable<T> parent) {
        this.parent = parent;
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.add(Observable.subscribe(subscriber, this.parent));
    }
}
