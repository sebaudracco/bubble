package com.bgjd.ici.p028c;

import android.database.Cursor;
import java.lang.reflect.InvocationTargetException;

public class C1437b<T> implements C1436e<T> {
    private Class<T> cls = null;
    private Cursor cursor = null;
    private boolean moveNext = true;

    public C1437b(Cursor cursor, Class<T> cls) {
        this.cursor = cursor;
        this.cls = cls;
        this.cursor.moveToFirst();
    }

    public boolean read() {
        return !this.cursor.isAfterLast() && this.moveNext;
    }

    public T fetch() {
        try {
            T newInstance = this.cls.getConstructor(new Class[]{Cursor.class}).newInstance(new Object[]{this.cursor});
            this.moveNext = this.cursor.moveToNext();
            return newInstance;
        } catch (NoSuchMethodException e) {
        } catch (InstantiationException e2) {
        } catch (IllegalAccessException e3) {
        } catch (IllegalArgumentException e4) {
        } catch (InvocationTargetException e5) {
        }
        return null;
    }

    public void close() {
        this.cursor.close();
    }
}
