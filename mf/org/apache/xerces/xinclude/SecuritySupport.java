package mf.org.apache.xerces.xinclude;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

final class SecuritySupport {

    class C46621 implements PrivilegedAction {
        C46621() {
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

    class C46632 implements PrivilegedAction {
        C46632() {
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

    class C46643 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;

        C46643(ClassLoader classLoader) {
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

    class C46654 implements PrivilegedAction {
        private final /* synthetic */ String val$propName;

        C46654(String str) {
            this.val$propName = str;
        }

        public Object run() {
            return System.getProperty(this.val$propName);
        }
    }

    class C46665 implements PrivilegedExceptionAction {
        private final /* synthetic */ File val$file;

        C46665(File file) {
            this.val$file = file;
        }

        public Object run() throws FileNotFoundException {
            return new FileInputStream(this.val$file);
        }
    }

    class C46676 implements PrivilegedAction {
        private final /* synthetic */ ClassLoader val$cl;
        private final /* synthetic */ String val$name;

        C46676(ClassLoader classLoader, String str) {
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

    class C46687 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C46687(File file) {
            this.val$f = file;
        }

        public Object run() {
            return this.val$f.exists() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    class C46698 implements PrivilegedAction {
        private final /* synthetic */ File val$f;

        C46698(File file) {
            this.val$f = file;
        }

        public Object run() {
            return new Long(this.val$f.lastModified());
        }
    }

    static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C46621());
    }

    static ClassLoader getSystemClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C46632());
    }

    static ClassLoader getParentClassLoader(ClassLoader cl) {
        return (ClassLoader) AccessController.doPrivileged(new C46643(cl));
    }

    static String getSystemProperty(String propName) {
        return (String) AccessController.doPrivileged(new C46654(propName));
    }

    static FileInputStream getFileInputStream(File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new C46665(file));
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    static InputStream getResourceAsStream(ClassLoader cl, String name) {
        return (InputStream) AccessController.doPrivileged(new C46676(cl, name));
    }

    static boolean getFileExists(File f) {
        return ((Boolean) AccessController.doPrivileged(new C46687(f))).booleanValue();
    }

    static long getLastModified(File f) {
        return ((Long) AccessController.doPrivileged(new C46698(f))).longValue();
    }

    private SecuritySupport() {
    }
}
