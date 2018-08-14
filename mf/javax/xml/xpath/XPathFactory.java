package mf.javax.xml.xpath;

public abstract class XPathFactory {
    public static final String DEFAULT_OBJECT_MODEL_URI = "http://java.sun.com/jaxp/xpath/dom";
    public static final String DEFAULT_PROPERTY_NAME = "javax.xml.xpath.XPathFactory";
    private static SecuritySupport ss = new SecuritySupport();

    public abstract boolean getFeature(String str) throws XPathFactoryConfigurationException;

    public abstract boolean isObjectModelSupported(String str);

    public abstract XPath newXPath();

    public abstract void setFeature(String str, boolean z) throws XPathFactoryConfigurationException;

    public abstract void setXPathFunctionResolver(XPathFunctionResolver xPathFunctionResolver);

    public abstract void setXPathVariableResolver(XPathVariableResolver xPathVariableResolver);

    protected XPathFactory() {
    }

    public static final XPathFactory newInstance() {
        try {
            return newInstance("http://java.sun.com/jaxp/xpath/dom");
        } catch (XPathFactoryConfigurationException xpathFactoryConfigurationException) {
            throw new RuntimeException("XPathFactory#newInstance() failed to create an XPathFactory for the default object model: http://java.sun.com/jaxp/xpath/dom with the XPathFactoryConfigurationException: " + xpathFactoryConfigurationException.toString());
        }
    }

    public static final XPathFactory newInstance(String uri) throws XPathFactoryConfigurationException {
        if (uri == null) {
            throw new NullPointerException("XPathFactory#newInstance(String uri) cannot be called with uri == null");
        } else if (uri.length() == 0) {
            throw new IllegalArgumentException("XPathFactory#newInstance(String uri) cannot be called with uri == \"\"");
        } else {
            ClassLoader classLoader = ss.getContextClassLoader();
            if (classLoader == null) {
                classLoader = XPathFactory.class.getClassLoader();
            }
            XPathFactory xpathFactory = new XPathFactoryFinder(classLoader).newFactory(uri);
            if (xpathFactory != null) {
                return xpathFactory;
            }
            throw new XPathFactoryConfigurationException("No XPathFactory implementation found for the object model: " + uri);
        }
    }

    public static XPathFactory newInstance(String uri, String factoryClassName, ClassLoader classLoader) throws XPathFactoryConfigurationException {
        ClassLoader cl = classLoader;
        if (uri == null) {
            throw new NullPointerException("XPathFactory#newInstance(String uri) cannot be called with uri == null");
        } else if (uri.length() == 0) {
            throw new IllegalArgumentException("XPathFactory#newInstance(String uri) cannot be called with uri == \"\"");
        } else {
            if (cl == null) {
                cl = ss.getContextClassLoader();
            }
            XPathFactory f = new XPathFactoryFinder(cl).createInstance(factoryClassName);
            if (f == null) {
                throw new XPathFactoryConfigurationException("No XPathFactory implementation found for the object model: " + uri);
            } else if (f.isObjectModelSupported(uri)) {
                return f;
            } else {
                throw new XPathFactoryConfigurationException("Factory " + factoryClassName + " doesn't support given " + uri + " object model");
            }
        }
    }
}
