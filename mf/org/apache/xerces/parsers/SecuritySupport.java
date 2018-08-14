package mf.org.apache.xerces.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

final class SecuritySupport {

    class C46501 implements PrivilegedAction {
        C46501() {
        }

        public Object run() {
            ClassLoader cl = null;
            try {
                cl = Thread.currentThread().getContextClassLoader();
            } catch (SecurityException e) {
            }
            return cl;
        }
    }

    class C46512 implements PrivilegedAction {
        C46512() {
        }

        public Object run() {
            ClassLoader cl = null;
            try {
                cl = ClassLoader.getSystemClassLoader();
            } catch (SecurityException e) {
            }
            return cl;
        }
    }

    class C46523 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;

        C46523(ClassLoader classLoader) {
            this.val$cl = classLoader;
        }

        public Object run() {
            ClassLoader parent = null;
            try {
                parent = this.val$cl.getParent();
            } catch (SecurityException e) {
            }
            return parent == this.val$cl ? null : parent;
        }
    }

    class C46534 implements PrivilegedAction {
        private final /* synthetic */ String val$propName;

        C46534(String str) {
            this.val$propName = str;
        }

        public Object run() {
            return System.getProperty(this.val$propName);
        }
    }

    class C46545 implements PrivilegedExceptionAction {
        private final /* synthetic */ File val$file;

        C46545(File file) {
            this.val$file = file;
        }

        public Object run() throws FileNotFoundException {
            return new FileInputStream(this.val$file);
        }
    }

    class C46556 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C46556(ClassLoader classLoader, String str) {
            this.val$cl = classLoader;
            this.val$name = str;
        }

        public Object run() {
            if (this.val$cl == null) {
                return ClassLoader.getSystemResourceAsStream(this.val$name);
            }
            return this.val$cl.getResourceAsStream(this.val$name);
        }
    }

    class C46567 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C46567(File file) {
            this.val$f = file;
        }

        public Object run() {
            return this.val$f.exists() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    class C46578 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C46578(File file) {
            this.val$f = file;
        }

        public Object run() {
            return new Long(this.val$f.lastModified());
        }
    }

    static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C46501());
    }

    static ClassLoader getSystemClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C46512());
    }

    static ClassLoader getParentClassLoader(ClassLoader cl) {
        return (ClassLoader) AccessController.doPrivileged(new C46523(cl));
    }

    static String getSystemProperty(String propName) {
        return (String) AccessController.doPrivileged(new C46534(propName));
    }

    static FileInputStream getFileInputStream(File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new C46545(file));
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    static InputStream getResourceAsStream(ClassLoader cl, String name) {
        return (InputStream) AccessController.doPrivileged(new C46556(cl, name));
    }

    static boolean getFileExists(File f) {
        return ((Boolean) AccessController.doPrivileged(new C46567(f))).booleanValue();
    }

    static long getLastModified(File f) {
        return ((Long) AccessController.doPrivileged(new C46578(f))).longValue();
    }

    private SecuritySupport() {
    }
}
