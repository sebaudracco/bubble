package com.mopub.common.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection {

    public static class MethodBuilder {
        @Nullable
        private Class<?> mClass;
        @Nullable
        private final Object mInstance;
        private boolean mIsAccessible;
        private boolean mIsStatic;
        @NonNull
        private final String mMethodName;
        @NonNull
        private List<Class<?>> mParameterClasses = new ArrayList();
        @NonNull
        private List<Object> mParameters = new ArrayList();

        public MethodBuilder(@Nullable Object instance, @NonNull String methodName) {
            Preconditions.checkNotNull(methodName);
            this.mInstance = instance;
            this.mMethodName = methodName;
            this.mClass = instance != null ? instance.getClass() : null;
        }

        @NonNull
        public <T> MethodBuilder addParam(@NonNull Class<T> clazz, @Nullable T parameter) {
            Preconditions.checkNotNull(clazz);
            this.mParameterClasses.add(clazz);
            this.mParameters.add(parameter);
            return this;
        }

        @NonNull
        public MethodBuilder addParam(@NonNull String className, @Nullable Object parameter) throws ClassNotFoundException {
            Preconditions.checkNotNull(className);
            this.mParameterClasses.add(Class.forName(className));
            this.mParameters.add(parameter);
            return this;
        }

        @NonNull
        public MethodBuilder setAccessible() {
            this.mIsAccessible = true;
            return this;
        }

        @NonNull
        public MethodBuilder setStatic(@NonNull Class<?> clazz) {
            Preconditions.checkNotNull(clazz);
            this.mIsStatic = true;
            this.mClass = clazz;
            return this;
        }

        @NonNull
        public MethodBuilder setStatic(@NonNull String className) throws ClassNotFoundException {
            Preconditions.checkNotNull(className);
            this.mIsStatic = true;
            this.mClass = Class.forName(className);
            return this;
        }

        @Nullable
        public Object execute() throws Exception {
            Method method = Reflection.getDeclaredMethodWithTraversal(this.mClass, this.mMethodName, (Class[]) this.mParameterClasses.toArray(new Class[this.mParameterClasses.size()]));
            if (this.mIsAccessible) {
                method.setAccessible(true);
            }
            Object[] parameters = this.mParameters.toArray();
            if (this.mIsStatic) {
                return method.invoke(null, parameters);
            }
            return method.invoke(this.mInstance, parameters);
        }
    }

    @Nullable
    public static Method getDeclaredMethodWithTraversal(@Nullable Class<?> clazz, @NonNull String methodName, @NonNull Class<?>... parameterTypes) throws NoSuchMethodException {
        Preconditions.checkNotNull(methodName);
        Preconditions.checkNotNull(parameterTypes);
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                return currentClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }

    public static boolean classFound(@NonNull String className) {
        Preconditions.checkNotNull(className);
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @NonNull
    public static <T> T instantiateClassWithEmptyConstructor(@NonNull String className, @NonNull Class<? extends T> superclass) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NullPointerException {
        Preconditions.checkNotNull(className);
        Preconditions.checkNotNull(superclass);
        Constructor<? extends T> constructor = Class.forName(className).asSubclass(superclass).getDeclaredConstructor((Class[]) null);
        constructor.setAccessible(true);
        return constructor.newInstance(new Object[0]);
    }

    @NonNull
    public static <T> T instantiateClassWithConstructor(@NonNull String className, @NonNull Class<? extends T> superClass, @NonNull Class[] classes, @NonNull Object[] parameters) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Preconditions.checkNotNull(className);
        Preconditions.checkNotNull(superClass);
        Preconditions.checkNotNull(classes);
        Preconditions.checkNotNull(parameters);
        Constructor<? extends T> constructor = Class.forName(className).asSubclass(superClass).getDeclaredConstructor(classes);
        constructor.setAccessible(true);
        return constructor.newInstance(parameters);
    }
}
