package mf.org.w3c.dom.bootstrap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.StringTokenizer;
import java.util.Vector;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DOMImplementationList;
import mf.org.w3c.dom.DOMImplementationSource;

public final class DOMImplementationRegistry {
    private static final String DEFAULT_DOM_IMPLEMENTATION_SOURCE = "mf.org.apache.xerces.dom.DOMXSImplementationSourceImpl";
    private static final int DEFAULT_LINE_LENGTH = 80;
    public static final String PROPERTY = "org.w3c.dom.DOMImplementationSourceList";
    private Vector sources;

    class C46812 implements PrivilegedAction {
        C46812() {
        }

        public Object run() {
            ClassLoader classLoader = null;
            try {
                classLoader = Thread.currentThread().getContextClassLoader();
            } catch (SecurityException e) {
            }
            return classLoader;
        }
    }

    class C46823 implements PrivilegedAction {
        private final /* synthetic */ String val$name;

        C46823(String str) {
            this.val$name = str;
        }

        public Object run() {
            return System.getProperty(this.val$name);
        }
    }

    class C46834 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$classLoader;
        private final /* synthetic */ String val$name;

        C46834(ClassLoader classLoader, String str) {
            this.val$classLoader = classLoader;
            this.val$name = str;
        }

        public Object run() {
            if (this.val$classLoader == null) {
                return ClassLoader.getSystemResourceAsStream(this.val$name);
            }
            return this.val$classLoader.getResourceAsStream(this.val$name);
        }
    }

    private DOMImplementationRegistry(Vector srcs) {
        this.sources = srcs;
    }

    public static DOMImplementationRegistry newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ClassCastException {
        Vector sources = new Vector();
        ClassLoader classLoader = getClassLoader();
        String p = getSystemProperty(PROPERTY);
        if (p == null || p.length() == 0) {
            p = getServiceValue(classLoader);
        }
        if (p == null) {
            p = DEFAULT_DOM_IMPLEMENTATION_SOURCE;
        }
        if (p != null) {
            StringTokenizer st = new StringTokenizer(p);
            while (st.hasMoreTokens()) {
                Class sourceClass;
                String sourceName = st.nextToken();
                if (classLoader != null) {
                    sourceClass = classLoader.loadClass(sourceName);
                } else {
                    sourceClass = Class.forName(sourceName);
                }
                sources.addElement((DOMImplementationSource) sourceClass.newInstance());
            }
        }
        return new DOMImplementationRegistry(sources);
    }

    public DOMImplementation getDOMImplementation(String features) {
        int size = this.sources.size();
        for (int i = 0; i < size; i++) {
            DOMImplementation impl = ((DOMImplementationSource) this.sources.elementAt(i)).getDOMImplementation(features);
            if (impl != null) {
                return impl;
            }
        }
        return null;
    }

    public DOMImplementationList getDOMImplementationList(String features) {
        final Vector implementations = new Vector();
        int size = this.sources.size();
        for (int i = 0; i < size; i++) {
            DOMImplementationList impls = ((DOMImplementationSource) this.sources.elementAt(i)).getDOMImplementationList(features);
            for (int j = 0; j < impls.getLength(); j++) {
                implementations.addElement(impls.item(j));
            }
        }
        return new DOMImplementationList() {
            public DOMImplementation item(int index) {
                if (index < 0 || index >= implementations.size()) {
                    return null;
                }
                try {
                    return (DOMImplementation) implementations.elementAt(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
            }

            public int getLength() {
                return implementations.size();
            }
        };
    }

    public void addSource(DOMImplementationSource s) {
        if (s == null) {
            throw new NullPointerException();
        } else if (!this.sources.contains(s)) {
            this.sources.addElement(s);
        }
    }

    private static ClassLoader getClassLoader() {
        try {
            ClassLoader contextClassLoader = getContextClassLoader();
            return contextClassLoader != null ? contextClassLoader : DOMImplementationRegistry.class.getClassLoader();
        } catch (Exception e) {
            return DOMImplementationRegistry.class.getClassLoader();
        }
    }

    private static String getServiceValue(ClassLoader classLoader) {
        BufferedReader rd;
        try {
            InputStream is = getResourceAsStream(classLoader, "META-INF/services/org.w3c.dom.DOMImplementationSourceList");
            if (is != null) {
                try {
                    rd = new BufferedReader(new InputStreamReader(is, "UTF-8"), 80);
                } catch (UnsupportedEncodingException e) {
                    rd = new BufferedReader(new InputStreamReader(is), 80);
                }
                String serviceValue = rd.readLine();
                rd.close();
                if (serviceValue != null && serviceValue.length() > 0) {
                    return serviceValue;
                }
            }
            return null;
        } catch (Exception e2) {
            return null;
        } catch (Throwable th) {
            rd.close();
        }
    }

    private static boolean isJRE11() {
        try {
            Class c = Class.forName("java.security.AccessController");
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private static ClassLoader getContextClassLoader() {
        if (isJRE11()) {
            return null;
        }
        return (ClassLoader) AccessController.doPrivileged(new C46812());
    }

    private static String getSystemProperty(String name) {
        if (isJRE11()) {
            return System.getProperty(name);
        }
        return (String) AccessController.doPrivileged(new C46823(name));
    }

    private static InputStream getResourceAsStream(ClassLoader classLoader, String name) {
        if (!isJRE11()) {
            return (InputStream) AccessController.doPrivileged(new C46834(classLoader, name));
        }
        if (classLoader == null) {
            return ClassLoader.getSystemResourceAsStream(name);
        }
        return classLoader.getResourceAsStream(name);
    }
}
