package rx.internal.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue<T> implements Queue<T>, Cloneable {
    private final Queue<T> list;
    private final int size;

    public SynchronizedQueue() {
        this.list = new LinkedList();
        this.size = -1;
    }

    public SynchronizedQueue(int size) {
        this.list = new LinkedList();
        this.size = size;
    }

    public synchronized boolean isEmpty() {
        return this.list.isEmpty();
    }

    public synchronized boolean contains(Object o) {
        return this.list.contains(o);
    }

    public synchronized Iterator<T> iterator() {
        return this.list.iterator();
    }

    public synchronized int size() {
        return this.list.size();
    }

    public synchronized boolean add(T e) {
        return this.list.add(e);
    }

    public synchronized boolean remove(Object o) {
        return this.list.remove(o);
    }

    public synchronized boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    public synchronized boolean addAll(Collection<? extends T> c) {
        return this.list.addAll(c);
    }

    public synchronized boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    public synchronized boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    public synchronized void clear() {
        this.list.clear();
    }

    public synchronized String toString() {
        return this.list.toString();
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.list.equals(((SynchronizedQueue) obj).list);
    }

    public synchronized T peek() {
        return this.list.peek();
    }

    public synchronized T element() {
        return this.list.element();
    }

    public synchronized T poll() {
        return this.list.poll();
    }

    public synchronized T remove() {
        return this.list.remove();
    }

    public synchronized boolean offer(T e) {
        boolean z;
        z = (this.size <= -1 || this.list.size() + 1 <= this.size) && this.list.offer(e);
        return z;
    }

    public synchronized Object clone() {
        SynchronizedQueue<T> q;
        q = new SynchronizedQueue(this.size);
        q.addAll(this.list);
        return q;
    }

    public synchronized Object[] toArray() {
        return this.list.toArray();
    }

    public synchronized <R> R[] toArray(R[] a) {
        return this.list.toArray(a);
    }
}
