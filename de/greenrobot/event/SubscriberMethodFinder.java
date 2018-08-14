package de.greenrobot.event;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SubscriberMethodFinder {
    private static final int MODIFIERS_IGNORE = 1032;
    private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
    private static final Map<Class<?>, Class<?>> skipMethodVerificationForClasses = new ConcurrentHashMap();

    SubscriberMethodFinder() {
    }

    List<SubscriberMethod> findSubscriberMethods(Class<?> subscriberClass, String eventMethodName) {
        String key = subscriberClass.getName() + '.' + eventMethodName;
        synchronized (methodCache) {
            List<SubscriberMethod> subscriberMethods = (List) methodCache.get(key);
        }
        if (subscriberMethods != null) {
            return subscriberMethods;
        }
        subscriberMethods = new ArrayList();
        HashSet<String> eventTypesFound = new HashSet();
        StringBuilder methodKeyBuilder = new StringBuilder();
        for (Class<?> clazz = subscriberClass; clazz != null; clazz = clazz.getSuperclass()) {
            String name = clazz.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            for (Method method : clazz.getMethods()) {
                String methodName = method.getName();
                if (methodName.startsWith(eventMethodName)) {
                    int modifiers = method.getModifiers();
                    if ((modifiers & 1) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            ThreadMode threadMode;
                            String modifierString = methodName.substring(eventMethodName.length());
                            if (modifierString.length() == 0) {
                                threadMode = ThreadMode.PostThread;
                            } else if (modifierString.equals("MainThread")) {
                                threadMode = ThreadMode.MainThread;
                            } else if (modifierString.equals("BackgroundThread")) {
                                threadMode = ThreadMode.BackgroundThread;
                            } else if (modifierString.equals("Async")) {
                                threadMode = ThreadMode.Async;
                            } else if (!skipMethodVerificationForClasses.containsKey(clazz)) {
                                throw new EventBusException("Illegal onEvent method, check for typos: " + method);
                            }
                            Class<?> eventType = parameterTypes[0];
                            methodKeyBuilder.setLength(0);
                            methodKeyBuilder.append(methodName);
                            methodKeyBuilder.append('>').append(eventType.getName());
                            if (eventTypesFound.add(methodKeyBuilder.toString())) {
                                subscriberMethods.add(new SubscriberMethod(method, threadMode, eventType));
                            }
                        } else {
                            continue;
                        }
                    } else if (!skipMethodVerificationForClasses.containsKey(clazz)) {
                        Log.d(EventBus.TAG, "Skipping method (not public, static or abstract): " + clazz + "." + methodName);
                    }
                }
            }
        }
        if (subscriberMethods.isEmpty()) {
            throw new EventBusException("Subscriber " + subscriberClass + " has no public methods called " + eventMethodName);
        }
        synchronized (methodCache) {
            methodCache.put(key, subscriberMethods);
        }
        return subscriberMethods;
    }

    static void clearCaches() {
        synchronized (methodCache) {
            methodCache.clear();
        }
    }

    static void skipMethodVerificationFor(Class<?> clazz) {
        if (methodCache.isEmpty()) {
            skipMethodVerificationForClasses.put(clazz, clazz);
            return;
        }
        throw new IllegalStateException("This method must be called before registering anything");
    }

    public static void clearSkipMethodVerifications() {
        skipMethodVerificationForClasses.clear();
    }
}
