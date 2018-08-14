package mf.javax.xml.datatype;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

class SecuritySupport {

    class C45671 implements PrivilegedAction {
        C45671() {
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

    SecuritySupport() {
    }

    ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C45671());
    }

    String getSystemProperty(final String propName) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return System.getProperty(propName);
            }
        });
    }

    FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws FileNotFoundException {
                    return new FileInputStream(file);
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    InputStream getResourceAsStream(final ClassLoader cl, final String name) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                if (cl == null) {
                    return Object.class.getResourceAsStream(name);
                }
                return cl.getResourceAsStream(name);
            }
        });
    }

    boolean doesFileExist(final File f) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return new Boolean(f.exists());
            }
        })).booleanValue();
    }
}
