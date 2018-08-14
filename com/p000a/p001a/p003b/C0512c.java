package com.p000a.p001a.p003b;

import com.p000a.p001a.C0553f;
import com.p000a.p001a.C0558k;
import com.p000a.p001a.p003b.C0512c;
import com.p000a.p001a.p007c.C0542a;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class C0512c {
    private final Map<Type, C0553f<?>> f204a;

    class C05042 implements C0502h<T> {
        final /* synthetic */ C0512c f190a;

        C05042(C0512c c0512c) {
            this.f190a = c0512c;
        }

        public T mo1830a() {
            return new ConcurrentHashMap();
        }
    }

    class C05053 implements C0502h<T> {
        final /* synthetic */ C0512c f191a;

        C05053(C0512c c0512c) {
            this.f191a = c0512c;
        }

        public T mo1830a() {
            return new TreeMap();
        }
    }

    class C05064 implements C0502h<T> {
        final /* synthetic */ C0512c f192a;

        C05064(C0512c c0512c) {
            this.f192a = c0512c;
        }

        public T mo1830a() {
            return new LinkedHashMap();
        }
    }

    class C05075 implements C0502h<T> {
        final /* synthetic */ C0512c f193a;

        C05075(C0512c c0512c) {
            this.f193a = c0512c;
        }

        public T mo1830a() {
            return new C0524g();
        }
    }

    class C05119 implements C0502h<T> {
        final /* synthetic */ C0512c f203a;

        C05119(C0512c c0512c) {
            this.f203a = c0512c;
        }

        public T mo1830a() {
            return new TreeSet();
        }
    }

    public C0512c(Map<Type, C0553f<?>> map) {
        this.f204a = map;
    }

    private <T> C0502h<T> m345a(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new C0502h<T>(this) {
                final /* synthetic */ C0512c f202b;

                public T mo1830a() {
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (Throwable e) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> C0502h<T> m346a(final Type type, Class<? super T> cls) {
        return Collection.class.isAssignableFrom(cls) ? SortedSet.class.isAssignableFrom(cls) ? new C05119(this) : EnumSet.class.isAssignableFrom(cls) ? new C0502h<T>(this) {
            final /* synthetic */ C0512c f182b;

            public T mo1830a() {
                if (type instanceof ParameterizedType) {
                    Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (type instanceof Class) {
                        return EnumSet.noneOf((Class) type);
                    }
                    throw new C0558k("Invalid EnumSet type: " + type.toString());
                }
                throw new C0558k("Invalid EnumSet type: " + type.toString());
            }
        } : Set.class.isAssignableFrom(cls) ? new C0502h<T>(this) {
            final /* synthetic */ C0512c f183a;

            {
                this.f183a = r1;
            }

            public T mo1830a() {
                return new LinkedHashSet();
            }
        } : Queue.class.isAssignableFrom(cls) ? new C0502h<T>(this) {
            final /* synthetic */ C0512c f184a;

            {
                this.f184a = r1;
            }

            public T mo1830a() {
                return new ArrayDeque();
            }
        } : new C0502h<T>(this) {
            final /* synthetic */ C0512c f185a;

            {
                this.f185a = r1;
            }

            public T mo1830a() {
                return new ArrayList();
            }
        } : Map.class.isAssignableFrom(cls) ? ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new C0502h<T>(this) {
            final /* synthetic */ C0512c f186a;

            {
                this.f186a = r1;
            }

            public T mo1830a() {
                return new ConcurrentSkipListMap();
            }
        } : ConcurrentMap.class.isAssignableFrom(cls) ? new C05042(this) : SortedMap.class.isAssignableFrom(cls) ? new C05053(this) : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(C0542a.m400a(((ParameterizedType) type).getActualTypeArguments()[0]).m403a())) ? new C05075(this) : new C05064(this) : null;
    }

    private <T> C0502h<T> m347b(final Type type, final Class<? super T> cls) {
        return new C0502h<T>(this) {
            final /* synthetic */ C0512c f196c;
            private final C0529k f197d = C0529k.m384a();

            public T mo1830a() {
                try {
                    return this.f197d.mo1831a(cls);
                } catch (Throwable e) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
                }
            }
        };
    }

    public <T> C0502h<T> m348a(C0542a<T> c0542a) {
        final Type b = c0542a.m404b();
        Class a = c0542a.m403a();
        final C0553f c0553f = (C0553f) this.f204a.get(b);
        if (c0553f != null) {
            return new C0502h<T>(this) {
                final /* synthetic */ C0512c f189c;

                public T mo1830a() {
                    return c0553f.m452a(b);
                }
            };
        }
        c0553f = (C0553f) this.f204a.get(a);
        if (c0553f != null) {
            return new C0502h<T>(this) {
                final /* synthetic */ C0512c f200c;

                public T mo1830a() {
                    return c0553f.m452a(b);
                }
            };
        }
        C0502h<T> a2 = m345a(a);
        if (a2 != null) {
            return a2;
        }
        a2 = m346a(b, a);
        return a2 == null ? m347b(b, a) : a2;
    }

    public String toString() {
        return this.f204a.toString();
    }
}
