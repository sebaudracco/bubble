package mf.org.apache.html.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

final class SecuritySupport {

    class C46081 implements PrivilegedAction {
        C46081() {
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

    class C46092 implements PrivilegedAction {
        C46092() {
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

    class C46103 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;

        C46103(ClassLoader classLoader) {
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

    class C46114 implements PrivilegedAction {
        private final /* synthetic */ String val$propName;

        C46114(String str) {
            this.val$propName = str;
        }

        public Object run() {
            return System.getProperty(this.val$propName);
        }
    }

    class C46125 implements PrivilegedExceptionAction {
        private final /* synthetic */ File val$file;

        C46125(File file) {
            this.val$file = file;
        }

        public Object run() throws FileNotFoundException {
            return new FileInputStream(this.val$file);
        }
    }

    class C46136 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C46136(ClassLoader classLoader, String str) {
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

    class C46147 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C46147(File file) {
            this.val$f = file;
        }

        public Object run() {
            return this.val$f.exists() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    class C46158 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C46158(File file) {
            this.val$f = file;
        }

        public Object run() {
            return new Long(this.val$f.lastModified());
        }
    }

    static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C46081());
    }

    static ClassLoader getSystemClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C46092());
    }

    static ClassLoader getParentClassLoader(ClassLoader cl) {
        return (ClassLoader) AccessController.doPrivileged(new C46103(cl));
    }

    static String getSystemProperty(String propName) {
        return (String) AccessController.doPrivileged(new C46114(propName));
    }

    static FileInputStream getFileInputStream(File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new C46125(file));
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    static InputStream getResourceAsStream(ClassLoader cl, String name) {
        return (InputStream) AccessController.doPrivileged(new C46136(cl, name));
    }

    static boolean getFileExists(File f) {
        return ((Boolean) AccessController.doPrivileged(new C46147(f))).booleanValue();
    }

    static long getLastModified(File f) {
        return ((Long) AccessController.doPrivileged(new C46158(f))).longValue();
    }

    private SecuritySupport() {
    }
}
