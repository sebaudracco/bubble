package mf.org.apache.xerces.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

final class ObjectFactory {
    private static final boolean DEBUG = isDebugEnabled();
    private static final int DEFAULT_LINE_LENGTH = 80;
    private static final String DEFAULT_PROPERTIES_FILENAME = "xerces.properties";
    private static long fLastModified = -1;
    private static Properties fXercesProperties = null;

    static final class ConfigurationError extends Error {
        static final long serialVersionUID = 1914065341994951202L;
        private Exception exception;

        ConfigurationError(String msg, Exception x) {
            super(msg);
            this.exception = x;
        }

        Exception getException() {
            return this.exception;
        }
    }

    ObjectFactory() {
    }

    static Object createObject(String factoryId, String fallbackClassName) throws ConfigurationError {
        return createObject(factoryId, null, fallbackClassName);
    }

    static Object createObject(String factoryId, String propertiesFilename, String fallbackClassName) throws ConfigurationError {
        FileInputStream fis;
        Object provider;
        if (DEBUG) {
            debugPrintln("debug is on");
        }
        ClassLoader cl = findClassLoader();
        try {
            String systemProp = SecuritySupport.getSystemProperty(factoryId);
            if (systemProp != null && systemProp.length() > 0) {
                if (DEBUG) {
                    debugPrintln("found system property, value=" + systemProp);
                }
                return newInstance(systemProp, cl, true);
            }
        } catch (SecurityException e) {
        }
        String factoryClassName = null;
        if (propertiesFilename == null) {
            boolean loadProperties;
            long j;
            long lastModified;
            File propertiesFile = null;
            boolean propertiesFileExists = false;
            try {
                propertiesFilename = new StringBuilder(String.valueOf(SecuritySupport.getSystemProperty("java.home"))).append(File.separator).append("lib").append(File.separator).append(DEFAULT_PROPERTIES_FILENAME).toString();
                File propertiesFile2 = new File(propertiesFilename);
                try {
                    propertiesFileExists = SecuritySupport.getFileExists(propertiesFile2);
                    propertiesFile = propertiesFile2;
                } catch (SecurityException e2) {
                    propertiesFile = propertiesFile2;
                    fLastModified = -1;
                    fXercesProperties = null;
                    synchronized (ObjectFactory.class) {
                        loadProperties = false;
                        fis = null;
                        try {
                            if (fLastModified < 0) {
                                if (propertiesFileExists) {
                                    j = fLastModified;
                                    lastModified = SecuritySupport.getLastModified(propertiesFile);
                                    fLastModified = lastModified;
                                    if (j < lastModified) {
                                        loadProperties = true;
                                    }
                                }
                                if (!propertiesFileExists) {
                                    fLastModified = -1;
                                    fXercesProperties = null;
                                }
                            } else if (propertiesFileExists) {
                                loadProperties = true;
                                fLastModified = SecuritySupport.getLastModified(propertiesFile);
                            }
                            if (loadProperties) {
                                fXercesProperties = new Properties();
                                fis = SecuritySupport.getFileInputStream(propertiesFile);
                                fXercesProperties.load(fis);
                            }
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e3) {
                                }
                            }
                        } catch (Exception e4) {
                            fXercesProperties = null;
                            fLastModified = -1;
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e5) {
                                }
                            }
                        } catch (Throwable th) {
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e6) {
                                }
                            }
                        }
                    }
                    if (fXercesProperties != null) {
                        factoryClassName = fXercesProperties.getProperty(factoryId);
                    }
                    if (factoryClassName != null) {
                        if (DEBUG) {
                            debugPrintln("found in " + propertiesFilename + ", value=" + factoryClassName);
                        }
                        return newInstance(factoryClassName, cl, true);
                    }
                    provider = findJarServiceProvider(factoryId);
                    if (provider == null) {
                        return provider;
                    }
                    if (fallbackClassName == null) {
                        throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
                    }
                    if (DEBUG) {
                        debugPrintln("using fallback, value=" + fallbackClassName);
                    }
                    return newInstance(fallbackClassName, cl, true);
                }
            } catch (SecurityException e7) {
                fLastModified = -1;
                fXercesProperties = null;
                synchronized (ObjectFactory.class) {
                    loadProperties = false;
                    fis = null;
                    if (fLastModified < 0) {
                        if (propertiesFileExists) {
                            j = fLastModified;
                            lastModified = SecuritySupport.getLastModified(propertiesFile);
                            fLastModified = lastModified;
                            if (j < lastModified) {
                                loadProperties = true;
                            }
                        }
                        if (propertiesFileExists) {
                            fLastModified = -1;
                            fXercesProperties = null;
                        }
                    } else if (propertiesFileExists) {
                        loadProperties = true;
                        fLastModified = SecuritySupport.getLastModified(propertiesFile);
                    }
                    if (loadProperties) {
                        fXercesProperties = new Properties();
                        fis = SecuritySupport.getFileInputStream(propertiesFile);
                        fXercesProperties.load(fis);
                    }
                    if (fis != null) {
                        fis.close();
                    }
                }
                if (fXercesProperties != null) {
                    factoryClassName = fXercesProperties.getProperty(factoryId);
                }
                if (factoryClassName != null) {
                    provider = findJarServiceProvider(factoryId);
                    if (provider == null) {
                        return provider;
                    }
                    if (fallbackClassName == null) {
                        if (DEBUG) {
                            debugPrintln("using fallback, value=" + fallbackClassName);
                        }
                        return newInstance(fallbackClassName, cl, true);
                    }
                    throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
                }
                if (DEBUG) {
                    debugPrintln("found in " + propertiesFilename + ", value=" + factoryClassName);
                }
                return newInstance(factoryClassName, cl, true);
            }
            synchronized (ObjectFactory.class) {
                loadProperties = false;
                fis = null;
                if (fLastModified < 0) {
                    if (propertiesFileExists) {
                        j = fLastModified;
                        lastModified = SecuritySupport.getLastModified(propertiesFile);
                        fLastModified = lastModified;
                        if (j < lastModified) {
                            loadProperties = true;
                        }
                    }
                    if (propertiesFileExists) {
                        fLastModified = -1;
                        fXercesProperties = null;
                    }
                } else if (propertiesFileExists) {
                    loadProperties = true;
                    fLastModified = SecuritySupport.getLastModified(propertiesFile);
                }
                if (loadProperties) {
                    fXercesProperties = new Properties();
                    fis = SecuritySupport.getFileInputStream(propertiesFile);
                    fXercesProperties.load(fis);
                }
                if (fis != null) {
                    fis.close();
                }
            }
            if (fXercesProperties != null) {
                factoryClassName = fXercesProperties.getProperty(factoryId);
            }
        } else {
            fis = null;
            try {
                fis = SecuritySupport.getFileInputStream(new File(propertiesFilename));
                Properties props = new Properties();
                props.load(fis);
                factoryClassName = props.getProperty(factoryId);
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e8) {
                    }
                }
            } catch (Exception e9) {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e10) {
                    }
                }
            } catch (Throwable th2) {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e11) {
                    }
                }
            }
        }
        if (factoryClassName != null) {
            if (DEBUG) {
                debugPrintln("found in " + propertiesFilename + ", value=" + factoryClassName);
            }
            return newInstance(factoryClassName, cl, true);
        }
        provider = findJarServiceProvider(factoryId);
        if (provider == null) {
            return provider;
        }
        if (fallbackClassName == null) {
            throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
        }
        if (DEBUG) {
            debugPrintln("using fallback, value=" + fallbackClassName);
        }
        return newInstance(fallbackClassName, cl, true);
    }

    private static boolean isDebugEnabled() {
        try {
            String val = SecuritySupport.getSystemProperty("xerces.debug");
            if (val == null || SchemaSymbols.ATTVAL_FALSE.equals(val)) {
                return false;
            }
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }

    private static void debugPrintln(String msg) {
        if (DEBUG) {
            System.err.println("XERCES: " + msg);
        }
    }

    static ClassLoader findClassLoader() throws ConfigurationError {
        ClassLoader chain;
        ClassLoader context = SecuritySupport.getContextClassLoader();
        ClassLoader system = SecuritySupport.getSystemClassLoader();
        for (chain = system; context != chain; chain = SecuritySupport.getParentClassLoader(chain)) {
            if (chain == null) {
                return context;
            }
        }
        ClassLoader current = ObjectFactory.class.getClassLoader();
        for (chain = system; current != chain; chain = SecuritySupport.getParentClassLoader(chain)) {
            if (chain == null) {
                return current;
            }
        }
        return system;
    }

    static Object newInstance(String className, ClassLoader cl, boolean doFallback) throws ConfigurationError {
        try {
            Class providerClass = findProviderClass(className, cl, doFallback);
            Object instance = providerClass.newInstance();
            if (DEBUG) {
                debugPrintln("created new instance of " + providerClass + " using ClassLoader: " + cl);
            }
            return instance;
        } catch (ClassNotFoundException x) {
            throw new ConfigurationError("Provider " + className + " not found", x);
        } catch (Exception x2) {
            throw new ConfigurationError("Provider " + className + " could not be instantiated: " + x2, x2);
        }
    }

    static Class findProviderClass(String className, ClassLoader cl, boolean doFallback) throws ClassNotFoundException, ConfigurationError {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            int lastDot = className.lastIndexOf(".");
            String packageName = className;
            if (lastDot != -1) {
                packageName = className.substring(0, lastDot);
            }
            security.checkPackageAccess(packageName);
        }
        if (cl == null) {
            return Class.forName(className);
        }
        try {
            return cl.loadClass(className);
        } catch (ClassNotFoundException x) {
            if (doFallback) {
                ClassLoader current = ObjectFactory.class.getClassLoader();
                if (current == null) {
                    return Class.forName(className);
                }
                if (cl != current) {
                    return current.loadClass(className);
                }
                throw x;
            }
            throw x;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object findJarServiceProvider(java.lang.String r12) throws mf.org.apache.xerces.dom.ObjectFactory.ConfigurationError {
        /*
        r11 = 80;
        r8 = 0;
        r9 = new java.lang.StringBuilder;
        r10 = "META-INF/services/";
        r9.<init>(r10);
        r9 = r9.append(r12);
        r6 = r9.toString();
        r4 = 0;
        r0 = findClassLoader();
        r4 = mf.org.apache.xerces.dom.SecuritySupport.getResourceAsStream(r0, r6);
        if (r4 != 0) goto L_0x002b;
    L_0x001e:
        r9 = mf.org.apache.xerces.dom.ObjectFactory.class;
        r1 = r9.getClassLoader();
        if (r0 == r1) goto L_0x002b;
    L_0x0026:
        r0 = r1;
        r4 = mf.org.apache.xerces.dom.SecuritySupport.getResourceAsStream(r0, r6);
    L_0x002b:
        if (r4 != 0) goto L_0x002e;
    L_0x002d:
        return r8;
    L_0x002e:
        r9 = DEBUG;
        if (r9 == 0) goto L_0x0050;
    L_0x0032:
        r9 = new java.lang.StringBuilder;
        r10 = "found jar resource=";
        r9.<init>(r10);
        r9 = r9.append(r6);
        r10 = " using ClassLoader: ";
        r9 = r9.append(r10);
        r9 = r9.append(r0);
        r9 = r9.toString();
        debugPrintln(r9);
    L_0x0050:
        r5 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x008f }
        r9 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x008f }
        r10 = "UTF-8";
        r9.<init>(r4, r10);	 Catch:{ UnsupportedEncodingException -> 0x008f }
        r10 = 80;
        r5.<init>(r9, r10);	 Catch:{ UnsupportedEncodingException -> 0x008f }
    L_0x005f:
        r3 = 0;
        r3 = r5.readLine();	 Catch:{ IOException -> 0x009b, all -> 0x00a2 }
        r5.close();	 Catch:{ IOException -> 0x00a9 }
    L_0x0067:
        if (r3 == 0) goto L_0x002d;
    L_0x0069:
        r9 = "";
        r9 = r9.equals(r3);
        if (r9 != 0) goto L_0x002d;
    L_0x0072:
        r8 = DEBUG;
        if (r8 == 0) goto L_0x0089;
    L_0x0076:
        r8 = new java.lang.StringBuilder;
        r9 = "found in resource, value=";
        r8.<init>(r9);
        r8 = r8.append(r3);
        r8 = r8.toString();
        debugPrintln(r8);
    L_0x0089:
        r8 = 0;
        r8 = newInstance(r3, r0, r8);
        goto L_0x002d;
    L_0x008f:
        r2 = move-exception;
        r5 = new java.io.BufferedReader;
        r9 = new java.io.InputStreamReader;
        r9.<init>(r4);
        r5.<init>(r9, r11);
        goto L_0x005f;
    L_0x009b:
        r7 = move-exception;
        r5.close();	 Catch:{ IOException -> 0x00a0 }
        goto L_0x002d;
    L_0x00a0:
        r9 = move-exception;
        goto L_0x002d;
    L_0x00a2:
        r8 = move-exception;
        r5.close();	 Catch:{ IOException -> 0x00a7 }
    L_0x00a6:
        throw r8;
    L_0x00a7:
        r9 = move-exception;
        goto L_0x00a6;
    L_0x00a9:
        r9 = move-exception;
        goto L_0x0067;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.dom.ObjectFactory.findJarServiceProvider(java.lang.String):java.lang.Object");
    }
}
