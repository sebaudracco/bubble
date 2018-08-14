package mf.javax.xml.xpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;

class XPathFactoryFinder {
    private static final Class SERVICE_CLASS = XPathFactory.class;
    private static final String SERVICE_ID = ("META-INF/services/" + SERVICE_CLASS.getName());
    private static Properties cacheProps = new Properties();
    private static boolean debug;
    private static volatile boolean firstTime = true;
    private static SecuritySupport ss = new SecuritySupport();
    private final ClassLoader classLoader;

    private static abstract class SingleIterator implements Iterator {
        private boolean seen;

        protected abstract Object value();

        private SingleIterator() {
            this.seen = false;
        }

        public final void remove() {
            throw new UnsupportedOperationException();
        }

        public final boolean hasNext() {
            return !this.seen;
        }

        public final Object next() {
            if (this.seen) {
                throw new NoSuchElementException();
            }
            this.seen = true;
            return value();
        }
    }

    class C46061 extends SingleIterator {
        C46061() {
            super();
        }

        protected Object value() {
            return XPathFactoryFinder.ss.getResourceAsURL(XPathFactoryFinder.class.getClassLoader(), XPathFactoryFinder.SERVICE_ID);
        }
    }

    static {
        debug = false;
        try {
            boolean z;
            if (ss.getSystemProperty("jaxp.debug") != null) {
                z = true;
            } else {
                z = false;
            }
            debug = z;
        } catch (Exception e) {
            debug = false;
        }
    }

    private static void debugPrintln(String msg) {
        if (debug) {
            System.err.println("JAXP: " + msg);
        }
    }

    public XPathFactoryFinder(ClassLoader loader) {
        this.classLoader = loader;
        if (debug) {
            debugDisplayClassLoader();
        }
    }

    private void debugDisplayClassLoader() {
        try {
            if (this.classLoader == ss.getContextClassLoader()) {
                debugPrintln("using thread context class loader (" + this.classLoader + ") for search");
                return;
            }
        } catch (Throwable th) {
        }
        if (this.classLoader == ClassLoader.getSystemClassLoader()) {
            debugPrintln("using system class loader (" + this.classLoader + ") for search");
        } else {
            debugPrintln("using class loader (" + this.classLoader + ") for search");
        }
    }

    public XPathFactory newFactory(String uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        XPathFactory f = _newFactory(uri);
        if (f != null) {
            debugPrintln("factory '" + f.getClass().getName() + "' was found for " + uri);
        } else {
            debugPrintln("unable to find a factory for " + uri);
        }
        return f;
    }

    private XPathFactory _newFactory(String uri) {
        XPathFactory xpathFactory;
        String propertyName = new StringBuilder(String.valueOf(SERVICE_CLASS.getName())).append(":").append(uri).toString();
        try {
            debugPrintln("Looking up system property '" + propertyName + "'");
            String r = ss.getSystemProperty(propertyName);
            if (r != null) {
                debugPrintln("The value is '" + r + "'");
                xpathFactory = createInstance(r, true);
                if (xpathFactory != null) {
                    return xpathFactory;
                }
            }
            debugPrintln("The property is undefined.");
        } catch (Throwable t) {
            if (debug) {
                debugPrintln("failed to look up system property '" + propertyName + "'");
                t.printStackTrace();
            }
        }
        String configFile = new StringBuilder(String.valueOf(ss.getSystemProperty("java.home"))).append(File.separator).append("lib").append(File.separator).append("jaxp.properties").toString();
        try {
            if (firstTime) {
                synchronized (cacheProps) {
                    if (firstTime) {
                        File f = new File(configFile);
                        firstTime = false;
                        if (ss.doesFileExist(f)) {
                            debugPrintln("Read properties file " + f);
                            cacheProps.load(ss.getFileInputStream(f));
                        }
                    }
                }
            }
            String factoryClassName = cacheProps.getProperty(propertyName);
            debugPrintln("found " + factoryClassName + " in $java.home/jaxp.properties");
            if (factoryClassName != null) {
                xpathFactory = createInstance(factoryClassName, true);
                if (xpathFactory != null) {
                    return xpathFactory;
                }
            }
        } catch (Exception ex) {
            if (debug) {
                ex.printStackTrace();
            }
        }
        Iterator sitr = createServiceFileIterator();
        while (sitr.hasNext()) {
            URL resource = (URL) sitr.next();
            debugPrintln("looking into " + resource);
            try {
                xpathFactory = loadFromService(uri, resource.toExternalForm(), ss.getURLInputStream(resource));
                if (xpathFactory != null) {
                    return xpathFactory;
                }
            } catch (IOException e) {
                if (debug) {
                    debugPrintln("failed to read " + resource);
                    e.printStackTrace();
                }
            }
        }
        if (uri.equals("http://java.sun.com/jaxp/xpath/dom")) {
            debugPrintln("attempting to use the platform default W3C DOM XPath lib");
            return createInstance("com.sun.org.apache.xpath.internal.jaxp.XPathFactoryImpl", true);
        }
        debugPrintln("all things were tried, but none was found. bailing out.");
        return null;
    }

    private Class createClass(String className) {
        try {
            if (this.classLoader != null) {
                return this.classLoader.loadClass(className);
            }
            return Class.forName(className);
        } catch (Throwable t) {
            if (debug) {
                t.printStackTrace();
            }
            return null;
        }
    }

    XPathFactory createInstance(String className) {
        return createInstance(className, false);
    }

    XPathFactory createInstance(String className, boolean useServicesMechanism) {
        XPathFactory xPathFactory = null;
        debugPrintln("createInstance(" + className + ")");
        Class clazz = createClass(className);
        if (clazz == null) {
            debugPrintln("failed to getClass(" + className + ")");
            return null;
        }
        debugPrintln("loaded " + className + " from " + which(clazz));
        if (!useServicesMechanism) {
            try {
                xPathFactory = (XPathFactory) newInstanceNoServiceLoader(clazz);
            } catch (ClassCastException classCastException) {
                debugPrintln("could not instantiate " + clazz.getName());
                if (debug) {
                    classCastException.printStackTrace();
                }
                return null;
            } catch (IllegalAccessException illegalAccessException) {
                debugPrintln("could not instantiate " + clazz.getName());
                if (debug) {
                    illegalAccessException.printStackTrace();
                }
                return null;
            } catch (InstantiationException instantiationException) {
                debugPrintln("could not instantiate " + clazz.getName());
                if (debug) {
                    instantiationException.printStackTrace();
                }
                return null;
            }
        }
        if (xPathFactory == null) {
            xPathFactory = (XPathFactory) clazz.newInstance();
        }
        return xPathFactory;
    }

    private static Object newInstanceNoServiceLoader(Class<?> providerClass) {
        Object obj = null;
        if (System.getSecurityManager() != null) {
            try {
                obj = providerClass.getDeclaredMethod("newXPathFactoryNoServiceLoader", new Class[0]).invoke(null, null);
            } catch (NoSuchMethodException e) {
            } catch (Exception e2) {
            }
        }
        return obj;
    }

    private XPathFactory loadFromService(String objectModel, String inputName, InputStream in) throws IOException {
        XPathFactory xPathFactory = null;
        Class[] stringClassArray = new Class[]{"".getClass()};
        Object[] objectModelObjectArray = new Object[]{objectModel};
        String isObjectModelSupportedMethod = "isObjectModelSupported";
        debugPrintln("Reading " + inputName);
        BufferedReader configFile = new BufferedReader(new InputStreamReader(in));
        while (true) {
            String line = configFile.readLine();
            if (line != null) {
                int comment = line.indexOf("#");
                switch (comment) {
                    case -1:
                        break;
                    case 0:
                        line = "";
                        break;
                    default:
                        line = line.substring(0, comment);
                        break;
                }
                line = line.trim();
                if (line.length() != 0) {
                    Class clazz = createClass(line);
                    if (clazz != null) {
                        try {
                            xPathFactory = (XPathFactory) clazz.newInstance();
                            try {
                                if (((Boolean) clazz.getMethod("isObjectModelSupported", stringClassArray).invoke(xPathFactory, objectModelObjectArray)).booleanValue()) {
                                }
                            } catch (NoSuchMethodException e) {
                            } catch (IllegalAccessException e2) {
                            } catch (InvocationTargetException e3) {
                            }
                            xPathFactory = null;
                        } catch (ClassCastException e4) {
                            xPathFactory = null;
                        } catch (InstantiationException e5) {
                            xPathFactory = null;
                        } catch (IllegalAccessException e6) {
                            xPathFactory = null;
                        }
                    } else {
                        continue;
                    }
                }
            }
            configFile.close();
            return xPathFactory;
        }
    }

    private XPathFactory loadFromProperty(String keyName, String resourceName, InputStream in) throws IOException {
        debugPrintln("Reading " + resourceName);
        Properties props = new Properties();
        props.load(in);
        in.close();
        String factoryClassName = props.getProperty(keyName);
        if (factoryClassName != null) {
            debugPrintln("found " + keyName + " = " + factoryClassName);
            return createInstance(factoryClassName, true);
        }
        debugPrintln(new StringBuilder(String.valueOf(keyName)).append(" is not in the property file").toString());
        return null;
    }

    private Iterator createServiceFileIterator() {
        if (this.classLoader == null) {
            return new C46061();
        }
        try {
            final Enumeration e = ss.getResources(this.classLoader, SERVICE_ID);
            if (!e.hasMoreElements()) {
                debugPrintln("no " + SERVICE_ID + " file was found");
            }
            return new Iterator() {
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                public boolean hasNext() {
                    return e.hasMoreElements();
                }

                public Object next() {
                    return e.nextElement();
                }
            };
        } catch (IOException e2) {
            debugPrintln("failed to enumerate resources " + SERVICE_ID);
            if (debug) {
                e2.printStackTrace();
            }
            return new ArrayList().iterator();
        }
    }

    private static String which(Class clazz) {
        return which(clazz.getName(), clazz.getClassLoader());
    }

    private static String which(String classname, ClassLoader loader) {
        String classnameAsResource = classname.replace('.', '/') + ".class";
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        URL it = ss.getResourceAsURL(loader, classnameAsResource);
        if (it != null) {
            return it.toString();
        }
        return null;
    }
}
