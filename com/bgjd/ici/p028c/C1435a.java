package com.bgjd.ici.p028c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.C1410m;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class C1435a implements C1434d {
    private static final String TAG = "MKTDB";
    private SQLiteDatabase db = null;
    private boolean isOpen = false;
    private C1410m provider = null;

    public C1435a(C1410m c1410m) {
        this.provider = c1410m;
    }

    public void open() {
        try {
            this.db = this.provider.getWritableDatabase();
            this.isOpen = true;
        } catch (Exception e) {
        }
    }

    public boolean IsConnected() {
        return this.isOpen;
    }

    public <T, O> T getMapper(Class<T> cls, final Class<O> cls2) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (!C1435a.this.isOpen) {
                    return null;
                }
                if (method.isAnnotationPresent(C1441h.class)) {
                    Cursor rawQuery = C1435a.this.db.rawQuery(setQueryParameter(((C1441h) method.getAnnotation(C1441h.class)).query(), method, objArr), null);
                    if (rawQuery != null) {
                        try {
                            return new C1437b(rawQuery, cls2);
                        } catch (Exception e) {
                            C1402i.m2901b(C1435a.TAG, e.getMessage());
                        }
                    }
                    return null;
                } else if (method.isAnnotationPresent(C1439f.class)) {
                    try {
                        C1435a.this.db.execSQL(setQueryParameter(((C1439f) method.getAnnotation(C1439f.class)).query(), method, objArr));
                        return Boolean.valueOf(true);
                    } catch (Exception e2) {
                        C1402i.m2901b(C1435a.TAG, e2.getMessage());
                        return Boolean.valueOf(false);
                    }
                } else if (method.isAnnotationPresent(C1442i.class)) {
                    try {
                        C1435a.this.db.execSQL(setQueryParameter(((C1442i) method.getAnnotation(C1442i.class)).query(), method, objArr));
                        SQLiteStatement compileStatement = C1435a.this.db.compileStatement(C1404b.aG);
                        long simpleQueryForLong = compileStatement.simpleQueryForLong();
                        compileStatement.close();
                        return Long.valueOf(simpleQueryForLong);
                    } catch (Exception e22) {
                        C1402i.m2901b(C1435a.TAG, e22.getMessage());
                        return Integer.valueOf(0);
                    }
                } else if (!method.isAnnotationPresent(C1438c.class)) {
                    return null;
                } else {
                    try {
                        C1435a.this.db.execSQL(setQueryParameter(((C1438c) method.getAnnotation(C1438c.class)).query(), method, objArr));
                        return Boolean.valueOf(true);
                    } catch (Exception e222) {
                        C1402i.m2901b(C1435a.TAG, e222.getMessage());
                        return Boolean.valueOf(false);
                    }
                }
            }

            private String setQueryParameter(String str, Method method, Object[] objArr) {
                if (str.length() <= 0) {
                    return objArr[0].toString();
                }
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                int length = parameterAnnotations.length;
                int i = 0;
                int i2 = 0;
                String str2 = str;
                while (i < length) {
                    int length2 = parameterAnnotations[i].length;
                    int i3 = i2;
                    String str3 = str2;
                    for (i2 = 0; i2 < length2; i2++) {
                        str3 = str3.replace(String.format("#{%s}", new Object[]{((C1440g) r8[i2]).value()}), String.valueOf(objArr[i3]));
                        i3++;
                    }
                    i++;
                    i2 = i3;
                    str2 = str3;
                }
                return str2;
            }
        });
    }

    public void close() {
        this.isOpen = false;
        try {
            this.db.close();
        } catch (Exception e) {
        }
    }
}
