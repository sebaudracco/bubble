package mf.javax.xml.validation;

import java.io.File;
import java.net.URL;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.stream.StreamSource;
import mf.org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public abstract class SchemaFactory {
    private static SecuritySupport ss = new SecuritySupport();

    public abstract ErrorHandler getErrorHandler();

    public abstract LSResourceResolver getResourceResolver();

    public abstract boolean isSchemaLanguageSupported(String str);

    public abstract Schema newSchema() throws SAXException;

    public abstract Schema newSchema(Source[] sourceArr) throws SAXException;

    public abstract void setErrorHandler(ErrorHandler errorHandler);

    public abstract void setResourceResolver(LSResourceResolver lSResourceResolver);

    protected SchemaFactory() {
    }

    public static final SchemaFactory newInstance(String schemaLanguage) {
        ClassLoader cl = ss.getContextClassLoader();
        if (cl == null) {
            cl = SchemaFactory.class.getClassLoader();
        }
        SchemaFactory f = new SchemaFactoryFinder(cl).newFactory(schemaLanguage);
        if (f != null) {
            return f;
        }
        throw new IllegalArgumentException("No SchemaFactory that implements the schema language specified by: " + schemaLanguage + " could be loaded");
    }

    public static SchemaFactory newInstance(String schemaLanguage, String factoryClassName, ClassLoader classLoader) {
        ClassLoader cl = classLoader;
        if (cl == null) {
            cl = ss.getContextClassLoader();
        }
        SchemaFactory f = new SchemaFactoryFinder(cl).createInstance(factoryClassName);
        if (f == null) {
            throw new IllegalArgumentException("Factory " + factoryClassName + " could not be loaded to implement the schema language specified by: " + schemaLanguage);
        } else if (f.isSchemaLanguageSupported(schemaLanguage)) {
            return f;
        } else {
            throw new IllegalArgumentException("Factory " + f.getClass().getName() + " does not implement the schema language specified by: " + schemaLanguage);
        }
    }

    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(name);
    }

    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(name);
    }

    public void setProperty(String name, Object object) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(name);
    }

    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(name);
    }

    public Schema newSchema(Source schema) throws SAXException {
        return newSchema(new Source[]{schema});
    }

    public Schema newSchema(File schema) throws SAXException {
        return newSchema(new StreamSource(schema));
    }

    public Schema newSchema(URL schema) throws SAXException {
        return newSchema(new StreamSource(schema.toExternalForm()));
    }
}
