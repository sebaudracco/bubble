package mf.org.apache.xml.serialize;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Hashtable;
import java.util.StringTokenizer;

public abstract class SerializerFactory {
    public static final String FactoriesProperty = "org.apache.xml.serialize.factories";
    private static Hashtable _factories = new Hashtable();

    protected abstract String getSupportedMethod();

    public abstract Serializer makeSerializer(OutputStream outputStream, OutputFormat outputFormat) throws UnsupportedEncodingException;

    public abstract Serializer makeSerializer(Writer writer, OutputFormat outputFormat);

    public abstract Serializer makeSerializer(OutputFormat outputFormat);

    static {
        registerSerializerFactory(new SerializerFactoryImpl("xml"));
        registerSerializerFactory(new SerializerFactoryImpl("html"));
        registerSerializerFactory(new SerializerFactoryImpl(Method.XHTML));
        registerSerializerFactory(new SerializerFactoryImpl("text"));
        String list = SecuritySupport.getSystemProperty(FactoriesProperty);
        if (list != null) {
            StringTokenizer token = new StringTokenizer(list, " ;,:");
            while (token.hasMoreTokens()) {
                try {
                    SerializerFactory factory = (SerializerFactory) ObjectFactory.newInstance(token.nextToken(), SerializerFactory.class.getClassLoader(), true);
                    if (_factories.containsKey(factory.getSupportedMethod())) {
                        _factories.put(factory.getSupportedMethod(), factory);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static void registerSerializerFactory(SerializerFactory factory) {
        synchronized (_factories) {
            _factories.put(factory.getSupportedMethod(), factory);
        }
    }

    public static SerializerFactory getSerializerFactory(String method) {
        return (SerializerFactory) _factories.get(method);
    }
}
