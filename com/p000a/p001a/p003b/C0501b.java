package com.p000a.p001a.p003b;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class C0501b {
    static final Type[] f180a = new Type[0];

    private static final class C0498a implements Serializable, GenericArrayType {
        private static final long serialVersionUID = 0;
        private final Type f174a;

        public C0498a(Type type) {
            this.f174a = C0501b.m325d(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && C0501b.m320a((Type) this, (GenericArrayType) obj);
        }

        public Type getGenericComponentType() {
            return this.f174a;
        }

        public int hashCode() {
            return this.f174a.hashCode();
        }

        public String toString() {
            return C0501b.m327f(this.f174a) + "[]";
        }
    }

    private static final class C0499b implements Serializable, ParameterizedType {
        private static final long serialVersionUID = 0;
        private final Type f175a;
        private final Type f176b;
        private final Type[] f177c;

        public C0499b(Type type, Type type2, Type... typeArr) {
            int i;
            int i2 = 0;
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                i = (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null) ? 1 : 0;
                boolean z = (type == null && i == 0) ? false : true;
                C0497a.m309a(z);
            }
            this.f175a = type == null ? null : C0501b.m325d(type);
            this.f176b = C0501b.m325d(type2);
            this.f177c = (Type[]) typeArr.clone();
            i = this.f177c.length;
            while (i2 < i) {
                C0497a.m308a(this.f177c[i2]);
                C0501b.m329h(this.f177c[i2]);
                this.f177c[i2] = C0501b.m325d(this.f177c[i2]);
                i2++;
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && C0501b.m320a((Type) this, (ParameterizedType) obj);
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.f177c.clone();
        }

        public Type getOwnerType() {
            return this.f175a;
        }

        public Type getRawType() {
            return this.f176b;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.f177c) ^ this.f176b.hashCode()) ^ C0501b.m310a(this.f175a);
        }

        public String toString() {
            int length = this.f177c.length;
            if (length == 0) {
                return C0501b.m327f(this.f176b);
            }
            StringBuilder stringBuilder = new StringBuilder((length + 1) * 30);
            stringBuilder.append(C0501b.m327f(this.f176b)).append("<").append(C0501b.m327f(this.f177c[0]));
            for (int i = 1; i < length; i++) {
                stringBuilder.append(", ").append(C0501b.m327f(this.f177c[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    private static final class C0500c implements Serializable, WildcardType {
        private static final long serialVersionUID = 0;
        private final Type f178a;
        private final Type f179b;

        public C0500c(Type[] typeArr, Type[] typeArr2) {
            boolean z = true;
            C0497a.m309a(typeArr2.length <= 1);
            C0497a.m309a(typeArr.length == 1);
            if (typeArr2.length == 1) {
                C0497a.m308a(typeArr2[0]);
                C0501b.m329h(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z = false;
                }
                C0497a.m309a(z);
                this.f179b = C0501b.m325d(typeArr2[0]);
                this.f178a = Object.class;
                return;
            }
            C0497a.m308a(typeArr[0]);
            C0501b.m329h(typeArr[0]);
            this.f179b = null;
            this.f178a = C0501b.m325d(typeArr[0]);
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && C0501b.m320a((Type) this, (WildcardType) obj);
        }

        public Type[] getLowerBounds() {
            if (this.f179b == null) {
                return C0501b.f180a;
            }
            return new Type[]{this.f179b};
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.f178a};
        }

        public int hashCode() {
            return (this.f179b != null ? this.f179b.hashCode() + 31 : 1) ^ (this.f178a.hashCode() + 31);
        }

        public String toString() {
            return this.f179b != null ? "? super " + C0501b.m327f(this.f179b) : this.f178a == Object.class ? "?" : "? extends " + C0501b.m327f(this.f178a);
        }
    }

    static int m310a(Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    private static int m311a(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> m312a(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
    }

    public static GenericArrayType m313a(Type type) {
        return new C0498a(type);
    }

    public static ParameterizedType m314a(Type type, Type type2, Type... typeArr) {
        return new C0499b(type, type2, typeArr);
    }

    public static Type m315a(Type type, Class<?> cls) {
        Type b = C0501b.m321b(type, cls, Collection.class);
        if (b instanceof WildcardType) {
            b = ((WildcardType) b).getUpperBounds()[0];
        }
        return b instanceof ParameterizedType ? ((ParameterizedType) b).getActualTypeArguments()[0] : Object.class;
    }

    static Type m316a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return C0501b.m316a(cls.getGenericInterfaces()[i], interfaces[i], (Class) cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            Class cls3;
            while (cls3 != Object.class) {
                Class superclass = cls3.getSuperclass();
                if (superclass == cls2) {
                    return cls3.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return C0501b.m316a(cls3.getGenericSuperclass(), superclass, (Class) cls2);
                }
                cls3 = superclass;
            }
        }
        return cls2;
    }

    public static Type m317a(Type type, Class<?> cls, Type type2) {
        Type type3 = type2;
        while (type3 instanceof TypeVariable) {
            type3 = (TypeVariable) type3;
            type2 = C0501b.m318a(type, (Class) cls, (TypeVariable) type3);
            if (type2 == type3) {
                return type2;
            }
            type3 = type2;
        }
        Type componentType;
        Type a;
        if ((type3 instanceof Class) && ((Class) type3).isArray()) {
            Class cls2 = (Class) type3;
            componentType = cls2.getComponentType();
            a = C0501b.m317a(type, (Class) cls, componentType);
            return componentType != a ? C0501b.m313a(a) : cls2;
        } else if (type3 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type3;
            componentType = genericArrayType.getGenericComponentType();
            a = C0501b.m317a(type, (Class) cls, componentType);
            return componentType != a ? C0501b.m313a(a) : genericArrayType;
        } else if (type3 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type3;
            componentType = parameterizedType.getOwnerType();
            Type a2 = C0501b.m317a(type, (Class) cls, componentType);
            int i = a2 != componentType ? 1 : 0;
            r4 = parameterizedType.getActualTypeArguments();
            int length = r4.length;
            int i2 = i;
            r1 = r4;
            for (int i3 = 0; i3 < length; i3++) {
                Type a3 = C0501b.m317a(type, (Class) cls, r1[i3]);
                if (a3 != r1[i3]) {
                    if (i2 == 0) {
                        r1 = (Type[]) r1.clone();
                        i2 = 1;
                    }
                    r1[i3] = a3;
                }
            }
            return i2 != 0 ? C0501b.m314a(a2, parameterizedType.getRawType(), r1) : parameterizedType;
        } else if (!(type3 instanceof WildcardType)) {
            return type3;
        } else {
            WildcardType wildcardType = (WildcardType) type3;
            r1 = wildcardType.getLowerBounds();
            r4 = wildcardType.getUpperBounds();
            if (r1.length == 1) {
                a = C0501b.m317a(type, (Class) cls, r1[0]);
                return a != r1[0] ? C0501b.m324c(a) : wildcardType;
            } else if (r4.length != 1) {
                return wildcardType;
            } else {
                componentType = C0501b.m317a(type, (Class) cls, r4[0]);
                return componentType != r4[0] ? C0501b.m322b(componentType) : wildcardType;
            }
        }
    }

    static Type m318a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class a = C0501b.m312a((TypeVariable) typeVariable);
        if (a == null) {
            return typeVariable;
        }
        Type a2 = C0501b.m316a(type, (Class) cls, a);
        if (!(a2 instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) a2).getActualTypeArguments()[C0501b.m311a(a.getTypeParameters(), (Object) typeVariable)];
    }

    static boolean m319a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean m320a(Type type, Type type2) {
        boolean z = true;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!(C0501b.m319a(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()))) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return C0501b.m320a(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!(Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds()))) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (!(typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName()))) {
                z = false;
            }
            return z;
        }
    }

    static Type m321b(Type type, Class<?> cls, Class<?> cls2) {
        C0497a.m309a(cls2.isAssignableFrom(cls));
        return C0501b.m317a(type, (Class) cls, C0501b.m316a(type, (Class) cls, (Class) cls2));
    }

    public static WildcardType m322b(Type type) {
        return new C0500c(type instanceof WildcardType ? ((WildcardType) type).getUpperBounds() : new Type[]{type}, f180a);
    }

    public static Type[] m323b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type b = C0501b.m321b(type, cls, Map.class);
        if (b instanceof ParameterizedType) {
            return ((ParameterizedType) b).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static WildcardType m324c(Type type) {
        return new C0500c(new Type[]{Object.class}, type instanceof WildcardType ? ((WildcardType) type).getLowerBounds() : new Type[]{type});
    }

    public static Type m325d(Type type) {
        if (type instanceof Class) {
            C0498a c0498a;
            Class cls = (Class) type;
            if (cls.isArray()) {
                c0498a = new C0498a(C0501b.m325d(cls.getComponentType()));
            } else {
                Object obj = cls;
            }
            return c0498a;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new C0499b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new C0498a(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new C0500c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> m326e(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            C0497a.m309a(rawType instanceof Class);
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(C0501b.m326e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return C0501b.m326e(((WildcardType) type).getUpperBounds()[0]);
            }
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
        }
    }

    public static String m327f(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static Type m328g(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    static void m329h(Type type) {
        boolean z = ((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true;
        C0497a.m309a(z);
    }
}
