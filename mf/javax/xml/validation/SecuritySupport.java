package mf.javax.xml.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;

class SecuritySupport {

    class C45901 implements PrivilegedAction {
        C45901() {
        }

        public Object run() {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl == null) {
                return ClassLoader.getSystemClassLoader();
            }
            return cl;
        }
    }

    SecuritySupport() {
    }

    ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new C45901());
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

    InputStream getURLInputStream(final URL url) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    return url.openStream();
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    URL getResourceAsURL(final ClassLoader cl, final String name) {
        return (URL) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                if (cl == null) {
                    return Object.class.getResource(name);
                }
                return cl.getResource(name);
            }
        });
    }

    Enumeration getResources(final ClassLoader cl, final String name) throws IOException {
        try {
            return (Enumeration) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    if (cl == null) {
                        return ClassLoader.getSystemResources(name);
                    }
                    return cl.getResources(name);
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
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
