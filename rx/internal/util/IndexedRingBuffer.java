package rx.internal.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.Subscription;
import rx.functions.Func1;

public final class IndexedRingBuffer<E> implements Subscription {
    private static final ObjectPool<IndexedRingBuffer<?>> POOL = new C72321();
    static final int SIZE;
    private final ElementSection<E> elements = new ElementSection();
    final AtomicInteger index = new AtomicInteger();
    private final IndexSection removed = new IndexSection();
    final AtomicInteger removedIndex = new AtomicInteger();

    static class C72321 extends ObjectPool<IndexedRingBuffer<?>> {
        C72321() {
        }

        protected IndexedRingBuffer<?> createObject() {
            return new IndexedRingBuffer();
        }
    }

    static final class ElementSection<E> {
        final AtomicReferenceArray<E> array = new AtomicReferenceArray(IndexedRingBuffer.SIZE);
        final AtomicReference<ElementSection<E>> next = new AtomicReference();

        ElementSection() {
        }

        ElementSection<E> getNext() {
            if (this.next.get() != null) {
                return (ElementSection) this.next.get();
            }
            ElementSection<E> newSection = new ElementSection();
            if (this.next.compareAndSet(null, newSection)) {
                return newSection;
            }
            return (ElementSection) this.next.get();
        }
    }

    static class IndexSection {
        private final AtomicReference<IndexSection> _next = new AtomicReference();
        private final AtomicIntegerArray unsafeArray = new AtomicIntegerArray(IndexedRingBuffer.SIZE);

        IndexSection() {
        }

        public int getAndSet(int expected, int newValue) {
            return this.unsafeArray.getAndSet(expected, newValue);
        }

        public void set(int i, int elementIndex) {
            this.unsafeArray.set(i, elementIndex);
        }

        IndexSection getNext() {
            if (this._next.get() != null) {
                return (IndexSection) this._next.get();
            }
            IndexSection newSection = new IndexSection();
            if (this._next.compareAndSet(null, newSection)) {
                return newSection;
            }
            return (IndexSection) this._next.get();
        }
    }

    static {
        int defaultSize = 128;
        if (PlatformDependent.isAndroid()) {
            defaultSize = 8;
        }
        String sizeFromProperty = System.getProperty("rx.indexed-ring-buffer.size");
        if (sizeFromProperty != null) {
            try {
                defaultSize = Integer.parseInt(sizeFromProperty);
            } catch (NumberFormatException e) {
                System.err.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + sizeFromProperty + " => " + e.getMessage());
            }
        }
        SIZE = defaultSize;
    }

    public static <T> IndexedRingBuffer<T> getInstance() {
        return (IndexedRingBuffer) POOL.borrowObject();
    }

    public void releaseToPool() {
        int maxIndex = this.index.get();
        int realIndex = 0;
        loop0:
        for (ElementSection<E> section = this.elements; section != null; section = (ElementSection) section.next.get()) {
            int i = 0;
            while (i < SIZE) {
                if (realIndex >= maxIndex) {
                    break loop0;
                }
                section.array.set(i, null);
                i++;
                realIndex++;
            }
        }
        this.index.set(0);
        this.removedIndex.set(0);
        POOL.returnObject(this);
    }

    public void unsubscribe() {
        releaseToPool();
    }

    IndexedRingBuffer() {
    }

    public int add(E e) {
        int i = getIndexForAdd();
        if (i < SIZE) {
            this.elements.array.set(i, e);
        } else {
            getElementSection(i).array.set(i % SIZE, e);
        }
        return i;
    }

    public E remove(int index) {
        E e;
        if (index < SIZE) {
            e = this.elements.array.getAndSet(index, null);
        } else {
            e = getElementSection(index).array.getAndSet(index % SIZE, null);
        }
        pushRemovedIndex(index);
        return e;
    }

    private IndexSection getIndexSection(int index) {
        if (index < SIZE) {
            return this.removed;
        }
        int numSections = index / SIZE;
        IndexSection a = this.removed;
        for (int i = 0; i < numSections; i++) {
            a = a.getNext();
        }
        return a;
    }

    private ElementSection<E> getElementSection(int index) {
        if (index < SIZE) {
            return this.elements;
        }
        int numSections = index / SIZE;
        ElementSection<E> a = this.elements;
        for (int i = 0; i < numSections; i++) {
            a = a.getNext();
        }
        return a;
    }

    private synchronized int getIndexForAdd() {
        int i;
        int ri = getIndexFromPreviouslyRemoved();
        if (ri >= 0) {
            if (ri < SIZE) {
                i = this.removed.getAndSet(ri, -1);
            } else {
                i = getIndexSection(ri).getAndSet(ri % SIZE, -1);
            }
            if (i == this.index.get()) {
                this.index.getAndIncrement();
            }
        } else {
            i = this.index.getAndIncrement();
        }
        return i;
    }

    private synchronized int getIndexFromPreviouslyRemoved() {
        int i;
        int currentRi;
        do {
            currentRi = this.removedIndex.get();
            if (currentRi <= 0) {
                i = -1;
                break;
            }
        } while (!this.removedIndex.compareAndSet(currentRi, currentRi - 1));
        i = currentRi - 1;
        return i;
    }

    private synchronized void pushRemovedIndex(int elementIndex) {
        int i = this.removedIndex.getAndIncrement();
        if (i < SIZE) {
            this.removed.set(i, elementIndex);
        } else {
            getIndexSection(i).set(i % SIZE, elementIndex);
        }
    }

    public boolean isUnsubscribed() {
        return false;
    }

    public int forEach(Func1<? super E, Boolean> action) {
        return forEach(action, 0);
    }

    public int forEach(Func1<? super E, Boolean> action, int startIndex) {
        int endedAt = forEach(action, startIndex, this.index.get());
        if (startIndex > 0 && endedAt == this.index.get()) {
            return forEach(action, 0, startIndex);
        }
        if (endedAt == this.index.get()) {
            return 0;
        }
        return endedAt;
    }

    private int forEach(Func1<? super E, Boolean> action, int startIndex, int endIndex) {
        int maxIndex = this.index.get();
        int realIndex = startIndex;
        ElementSection<E> section = this.elements;
        if (startIndex >= SIZE) {
            section = getElementSection(startIndex);
            startIndex %= SIZE;
        }
        loop0:
        while (section != null) {
            int i = startIndex;
            while (i < SIZE) {
                if (realIndex >= maxIndex || realIndex >= endIndex) {
                    break loop0;
                }
                E element = section.array.get(i);
                if (element != null) {
                    int lastIndex = realIndex;
                    if (!((Boolean) action.call(element)).booleanValue()) {
                        return lastIndex;
                    }
                }
                i++;
                realIndex++;
            }
            section = (ElementSection) section.next.get();
            startIndex = 0;
        }
        return realIndex;
    }
}
