package com.appnext.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C1135m {
    private static int mn = 2;

    public static void m2380i(int i) {
        if (i >= 0 && i <= 2) {
            mn = i;
        }
    }

    public static int dc() {
        return mn;
    }

    public static Object m2376a(Class<?> cls, JSONObject jSONObject) {
        try {
            Object newInstance = cls.newInstance();
            Field[] fields;
            if (mn == 0) {
                fields = cls.getFields();
            } else {
                fields = cls.getDeclaredFields();
            }
            for (Field field : r0) {
                if (mn == 2 && Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }
                try {
                    if (jSONObject.has(field.getName())) {
                        String simpleName = field.getType().getSimpleName();
                        if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_BOOLEAN)) {
                            field.setBoolean(newInstance, jSONObject.getBoolean(field.getName()));
                        } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_INT)) {
                            field.setInt(newInstance, jSONObject.getInt(field.getName()));
                        } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_DOUBLE)) {
                            field.setDouble(newInstance, jSONObject.getDouble(field.getName()));
                        } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_FLOAT)) {
                            field.setFloat(newInstance, (float) jSONObject.getDouble(field.getName()));
                        } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_STRING)) {
                            field.set(newInstance, jSONObject.getString(field.getName()));
                        } else if (field.getType().isArray()) {
                            field.set(newInstance, Array.newInstance(field.getType().getComponentType(), jSONObject.getJSONArray(field.getName()).length()));
                            C1135m.m2377a(field.get(newInstance), jSONObject.getJSONArray(field.getName()));
                        } else {
                            field.set(newInstance, C1135m.m2376a(field.getType(), jSONObject.getJSONObject(field.getName())));
                        }
                    }
                } catch (IllegalArgumentException e) {
                } catch (IllegalAccessException e2) {
                } catch (Throwable th) {
                }
                if (mn == 2 && Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(false);
                }
            }
            return newInstance;
        } catch (InstantiationException e3) {
            return null;
        } catch (IllegalAccessException e4) {
            return null;
        }
    }

    public static void m2377a(Object obj, JSONArray jSONArray) throws IllegalArgumentException, NegativeArraySizeException, IllegalAccessException, JSONException {
        Class cls = obj.getClass();
        String simpleName = cls.getComponentType().getSimpleName();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (cls.getComponentType().isArray()) {
                Array.set(obj, i, Array.newInstance(cls.getComponentType().getComponentType(), jSONArray.getJSONArray(i).length()));
                C1135m.m2377a(Array.get(obj, i), jSONArray.getJSONArray(i));
            } else if (cls.getComponentType().isPrimitive() || simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_STRING)) {
                Array.set(obj, i, jSONArray.get(i));
            } else {
                Array.set(obj, i, C1135m.m2376a(cls.getComponentType(), jSONArray.getJSONObject(i)));
            }
        }
    }

    public static JSONObject m2378b(Object obj) {
        JSONObject jSONObject = new JSONObject();
        Class cls = obj.getClass();
        Field[] fields;
        if (mn == 0) {
            fields = cls.getFields();
        } else {
            fields = cls.getDeclaredFields();
        }
        for (Field field : r1) {
            if (mn == 2 && Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            try {
                String name = field.getName();
                String simpleName = field.getType().getSimpleName();
                if (field.get(obj) != null) {
                    if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_BOOLEAN)) {
                        jSONObject.put(name, field.getBoolean(obj));
                    } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_INT)) {
                        jSONObject.put(name, field.getInt(obj));
                    } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_DOUBLE)) {
                        jSONObject.put(name, field.getDouble(obj));
                    } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_FLOAT)) {
                        jSONObject.put(name, (double) field.getFloat(obj));
                    } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_LONG)) {
                        jSONObject.put(name, field.getLong(obj));
                    } else if (simpleName.equalsIgnoreCase(SchemaSymbols.ATTVAL_STRING)) {
                        jSONObject.put(name, (String) field.get(obj));
                    } else if (simpleName.endsWith("]")) {
                        jSONObject.put(name, C1135m.m2379c(field.get(obj)));
                    } else {
                        jSONObject.put(name, C1135m.m2378b(field.get(obj)));
                    }
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e2) {
            } catch (Throwable th) {
            }
            if (mn == 2 && Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(false);
            }
        }
        return jSONObject;
    }

    public static JSONArray m2379c(Object obj) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < Array.getLength(obj); i++) {
            if (Array.get(obj, i).getClass().isArray()) {
                jSONArray.put(i, C1135m.m2379c(Array.get(obj, i)));
            } else {
                jSONArray.put(i, Array.get(obj, i));
            }
        }
        return jSONArray;
    }
}
