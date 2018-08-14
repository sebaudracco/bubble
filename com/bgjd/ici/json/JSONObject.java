package com.bgjd.ici.json;

import android.annotation.SuppressLint;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeSet;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class JSONObject {
    public static final Object NULL = new Null();
    private final Map map;

    private static final class Null {
        private Null() {
        }

        protected final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public String toString() {
            return "null";
        }
    }

    public JSONObject() {
        this.map = new HashMap();
    }

    public JSONObject(JSONObject jSONObject, String[] strArr) {
        this();
        for (String str : strArr) {
            try {
                putOnce(str, jSONObject.opt(str));
            } catch (Exception e) {
            }
        }
    }

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() != '{') {
            throw jSONTokener.syntaxError("A JSONObject text must begin with '{' found:" + jSONTokener.nextClean());
        }
        while (true) {
            switch (jSONTokener.nextClean()) {
                case '\u0000':
                    throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    jSONTokener.back();
                    String obj = jSONTokener.nextValue().toString();
                    char nextClean = jSONTokener.nextClean();
                    if (nextClean == '=') {
                        if (jSONTokener.next() != '>') {
                            jSONTokener.back();
                        }
                    } else if (nextClean != ':') {
                        throw jSONTokener.syntaxError("Expected a ':' after a key");
                    }
                    putOnce(obj, jSONTokener.nextValue());
                    switch (jSONTokener.nextClean()) {
                        case ',':
                        case ';':
                            if (jSONTokener.nextClean() != '}') {
                                jSONTokener.back();
                            } else {
                                return;
                            }
                        case '}':
                            return;
                        default:
                            throw jSONTokener.syntaxError("Expected a ',' or '}'");
                    }
            }
        }
    }

    public JSONObject(Map map) {
        this.map = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    this.map.put(entry.getKey(), wrap(value));
                }
            }
        }
    }

    public JSONObject(Object obj) {
        this();
        populateMap(obj);
    }

    public JSONObject(Object obj, String[] strArr) {
        this();
        Class cls = obj.getClass();
        for (String str : strArr) {
            try {
                putOpt(str, cls.getField(str).get(obj));
            } catch (Exception e) {
            }
        }
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        Enumeration keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            if (nextElement instanceof String) {
                String[] split = ((String) nextElement).split("\\.");
                int length = split.length - 1;
                int i = 0;
                JSONObject jSONObject = this;
                while (i < length) {
                    String str2 = split[i];
                    Object opt = jSONObject.opt(str2);
                    JSONObject jSONObject2 = opt instanceof JSONObject ? (JSONObject) opt : null;
                    if (jSONObject2 == null) {
                        jSONObject2 = new JSONObject();
                        jSONObject.put(str2, (Object) jSONObject2);
                    }
                    i++;
                    jSONObject = jSONObject2;
                }
                jSONObject.put(split[length], bundle.getString((String) nextElement));
            }
        }
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object opt = opt(str);
        if (opt == null) {
            put(str, new JSONArray().put(obj));
        } else if (opt instanceof JSONArray) {
            put(str, ((JSONArray) opt).put(obj));
        } else {
            throw new JSONException("JSONObject[" + str + "] is not a JSONArray.");
        }
        return this;
    }

    public Object get(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        Object opt = opt(str);
        if (opt != null) {
            return opt;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] not found.");
    }

    public boolean getBoolean(String str) throws JSONException {
        Object obj = get(str);
        if (obj.equals(Boolean.FALSE) || ((obj instanceof String) && ((String) obj).equalsIgnoreCase(SchemaSymbols.ATTVAL_FALSE))) {
            return false;
        }
        if (obj.equals(Boolean.TRUE) || ((obj instanceof String) && ((String) obj).equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE))) {
            return true;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a Boolean.");
    }

    public int getInt(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).intValue() : Integer.parseInt((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not an int.");
        }
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a JSONArray.");
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a JSONObject.");
    }

    public long getLong(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).longValue() : Long.parseLong((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not a long.");
        }
    }

    public String getString(String str) throws JSONException {
        Object obj = get(str);
        return obj == NULL ? null : obj.toString();
    }

    public boolean has(String str) {
        return this.map.containsKey(str);
    }

    public boolean isNull(String str) {
        return NULL.equals(opt(str));
    }

    public Iterator keys() {
        return this.map.keySet().iterator();
    }

    public int length() {
        return this.map.size();
    }

    public JSONArray names() {
        JSONArray jSONArray = new JSONArray();
        Iterator keys = keys();
        while (keys.hasNext()) {
            jSONArray.put(keys.next());
        }
        return jSONArray.length() == 0 ? null : jSONArray;
    }

    public static String numberToString(Number number) throws JSONException {
        if (number == null) {
            throw new JSONException("Null pointer");
        }
        testValidity(number);
        String obj = number.toString();
        if (obj.indexOf(46) <= 0 || obj.indexOf(101) >= 0 || obj.indexOf(69) >= 0) {
            return obj;
        }
        while (obj.endsWith(SchemaSymbols.ATTVAL_FALSE_0)) {
            obj = obj.substring(0, obj.length() - 1);
        }
        if (obj.endsWith(".")) {
            return obj.substring(0, obj.length() - 1);
        }
        return obj;
    }

    public Object opt(String str) {
        return str == null ? null : this.map.get(str);
    }

    @SuppressLint({"DefaultLocale"})
    private void populateMap(Object obj) {
        Object obj2;
        Class cls = obj.getClass();
        if (cls.getClassLoader() != null) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        for (Method method : r0 != null ? cls.getMethods() : cls.getDeclaredMethods()) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    String str = "";
                    if (name.startsWith("get")) {
                        if (name.equals("getClass") || name.equals("getDeclaringClass")) {
                            str = "";
                        } else {
                            str = name.substring(3);
                        }
                    } else if (name.startsWith("is")) {
                        str = name.substring(2);
                    }
                    if (str.length() > 0 && Character.isUpperCase(str.charAt(0)) && method.getParameterTypes().length == 0) {
                        Object toLowerCase;
                        if (str.length() == 1) {
                            toLowerCase = str.toLowerCase();
                        } else if (Character.isUpperCase(str.charAt(1))) {
                            name = str;
                        } else {
                            name = str.substring(0, 1).toLowerCase() + str.substring(1);
                        }
                        obj2 = method.invoke(obj, (Object[]) null);
                        if (obj2 != null) {
                            this.map.put(toLowerCase, wrap(obj2));
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public JSONObject put(String str, boolean z) throws JSONException {
        put(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONObject put(String str, Collection collection) throws JSONException {
        put(str, new JSONArray(collection));
        return this;
    }

    @SuppressLint({"UseValueOf"})
    public JSONObject put(String str, double d) throws JSONException {
        put(str, new Double(d));
        return this;
    }

    @SuppressLint({"UseValueOf"})
    public JSONObject put(String str, int i) throws JSONException {
        put(str, new Integer(i));
        return this;
    }

    @SuppressLint({"UseValueOf"})
    public JSONObject put(String str, long j) throws JSONException {
        put(str, new Long(j));
        return this;
    }

    public JSONObject put(String str, Map map) throws JSONException {
        put(str, new JSONObject(map));
        return this;
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        if (obj != null) {
            testValidity(obj);
            this.map.put(str, obj);
        } else {
            remove(str);
        }
        return this;
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        if (!(str == null || obj == null)) {
            if (opt(str) != null) {
                throw new JSONException("Duplicate key \"" + str + "\"");
            }
            put(str, obj);
        }
        return this;
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        if (!(str == null || obj == null)) {
            put(str, obj);
        }
        return this;
    }

    public static String quote(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(length + 4);
        stringBuilder.append('\"');
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    stringBuilder.append("\\b");
                    break;
                case '\t':
                    stringBuilder.append("\\t");
                    break;
                case '\n':
                    stringBuilder.append("\\n");
                    break;
                case '\f':
                    stringBuilder.append("\\f");
                    break;
                case '\r':
                    stringBuilder.append("\\r");
                    break;
                case '\"':
                case '\\':
                    stringBuilder.append('\\');
                    stringBuilder.append(charAt);
                    break;
                case '/':
                    if (i2 == 60) {
                        stringBuilder.append('\\');
                    }
                    stringBuilder.append(charAt);
                    break;
                default:
                    if (charAt >= ' ' && ((charAt < '' || charAt >= ' ') && (charAt < ' ' || charAt >= '℀'))) {
                        stringBuilder.append(charAt);
                        break;
                    }
                    String str2 = "000" + Integer.toHexString(charAt);
                    stringBuilder.append("\\u").append(str2.substring(str2.length() - 4));
                    break;
            }
            i++;
            char c = charAt;
        }
        stringBuilder.append('\"');
        return stringBuilder.toString();
    }

    public Object remove(String str) {
        return this.map.remove(str);
    }

    public Iterator sortedKeys() {
        return new TreeSet(this.map.keySet()).iterator();
    }

    @SuppressLint({"UseValueOf"})
    public static Object stringToValue(String str) {
        if (str.equals("")) {
            return str;
        }
        if (str.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase(SchemaSymbols.ATTVAL_FALSE)) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("null")) {
            return NULL;
        }
        char charAt = str.charAt(0);
        if ((charAt < '0' || charAt > '9') && charAt != '.' && charAt != '-' && charAt != '+') {
            return str;
        }
        if (charAt == '0' && str.length() > 2 && (str.charAt(1) == 'x' || str.charAt(1) == 'X')) {
            try {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            } catch (Exception e) {
            }
        }
        try {
            if (str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1) {
                return Double.valueOf(str);
            }
            Long l = new Long(str);
            return l.longValue() == ((long) l.intValue()) ? Integer.valueOf(l.intValue()) : l;
        } catch (Exception e2) {
            return str;
        }
    }

    public static void testValidity(Object obj) throws JSONException {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (!(obj instanceof Float)) {
        } else {
            if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    public String toString() {
        try {
            Iterator keys = keys();
            StringBuilder stringBuilder = new StringBuilder("{");
            while (keys.hasNext()) {
                if (stringBuilder.length() > 1) {
                    stringBuilder.append(',');
                }
                Object next = keys.next();
                stringBuilder.append(quote(next.toString()));
                stringBuilder.append(':');
                stringBuilder.append(valueToString(this.map.get(next)));
            }
            stringBuilder.append('}');
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String toString(int i) throws JSONException {
        return toString(i, 0);
    }

    String toString(int i, int i2) throws JSONException {
        int i3 = 0;
        int length = length();
        if (length == 0) {
            return "{}";
        }
        Iterator sortedKeys = sortedKeys();
        int i4 = i2 + i;
        StringBuilder stringBuilder = new StringBuilder("{");
        if (length == 1) {
            Object next = sortedKeys.next();
            stringBuilder.append(quote(next.toString()));
            stringBuilder.append(": ");
            stringBuilder.append(valueToString(this.map.get(next), i, i2));
        } else {
            while (sortedKeys.hasNext()) {
                Object next2 = sortedKeys.next();
                if (stringBuilder.length() > 1) {
                    stringBuilder.append(",\n");
                } else {
                    stringBuilder.append('\n');
                }
                for (length = 0; length < i4; length++) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(quote(next2.toString()));
                stringBuilder.append(": ");
                stringBuilder.append(valueToString(this.map.get(next2), i, i4));
            }
            if (stringBuilder.length() > 1) {
                stringBuilder.append('\n');
                while (i3 < i2) {
                    stringBuilder.append(' ');
                    i3++;
                }
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public static String valueToString(Object obj) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof Number) {
            return numberToString((Number) obj);
        }
        if ((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new JSONObject((Map) obj).toString();
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj).toString();
        }
        if (obj.getClass().isArray()) {
            return new JSONArray(obj).toString();
        }
        return quote(obj.toString());
    }

    static String valueToString(Object obj, int i, int i2) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof Number) {
            return numberToString((Number) obj);
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).toString(i, i2);
        }
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).toString(i, i2);
        }
        if (obj instanceof Map) {
            return new JSONObject((Map) obj).toString(i, i2);
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj).toString(i, i2);
        }
        if (obj.getClass().isArray()) {
            return new JSONArray(obj).toString(i, i2);
        }
        return quote(obj.toString());
    }

    public static Object wrap(Object obj) {
        if (obj == null) {
            try {
                return NULL;
            } catch (Exception e) {
                return null;
            }
        } else if ((obj instanceof JSONObject) || (obj instanceof JSONArray) || NULL.equals(obj) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof String)) {
            return obj;
        } else {
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj);
            }
            if (obj instanceof Map) {
                return new JSONObject((Map) obj);
            }
            Package packageR = obj.getClass().getPackage();
            String name = packageR != null ? packageR.getName() : "";
            if (name.startsWith("java.") || name.startsWith("javax.") || obj.getClass().getClassLoader() == null) {
                return obj.toString();
            }
            return new JSONObject(obj);
        }
    }

    public Writer write(Writer writer) throws JSONException {
        Object obj = null;
        try {
            Iterator keys = keys();
            writer.write(123);
            while (keys.hasNext()) {
                if (obj != null) {
                    writer.write(44);
                }
                obj = keys.next();
                writer.write(quote(obj.toString()));
                writer.write(58);
                obj = this.map.get(obj);
                if (obj instanceof JSONObject) {
                    ((JSONObject) obj).write(writer);
                } else if (obj instanceof JSONArray) {
                    ((JSONArray) obj).write(writer);
                } else {
                    writer.write(valueToString(obj));
                }
                obj = 1;
            }
            writer.write(125);
            return writer;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }
}
