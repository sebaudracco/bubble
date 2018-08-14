package mf.org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import mf.javax.xml.XMLConstants;
import mf.javax.xml.stream.XMLEventReader;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.dom.DOMSource;
import mf.javax.xml.transform.sax.SAXSource;
import mf.javax.xml.transform.stax.StAXSource;
import mf.javax.xml.transform.stream.StreamSource;
import mf.javax.xml.validation.Schema;
import mf.javax.xml.validation.SchemaFactory;
import mf.org.apache.xerces.impl.xs.XMLSchemaLoader;
import mf.org.apache.xerces.util.DOMEntityResolverWrapper;
import mf.org.apache.xerces.util.DOMInputSource;
import mf.org.apache.xerces.util.ErrorHandlerWrapper;
import mf.org.apache.xerces.util.SAXInputSource;
import mf.org.apache.xerces.util.SAXMessageFormatter;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.util.StAXInputSource;
import mf.org.apache.xerces.util.XMLGrammarPoolImpl;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

public final class XMLSchemaFactory extends SchemaFactory {
    private static final String JAXP_SOURCE_FEATURE_PREFIX = "http://javax.xml.transform";
    private static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private final DOMEntityResolverWrapper fDOMEntityResolverWrapper = new DOMEntityResolverWrapper();
    private ErrorHandler fErrorHandler;
    private final ErrorHandlerWrapper fErrorHandlerWrapper = new ErrorHandlerWrapper(DraconianErrorHandler.getInstance());
    private LSResourceResolver fLSResourceResolver;
    private SecurityManager fSecurityManager;
    private boolean fUseGrammarPoolOnly;
    private final XMLGrammarPoolWrapper fXMLGrammarPoolWrapper = new XMLGrammarPoolWrapper();
    private final XMLSchemaLoader fXMLSchemaLoader = new XMLSchemaLoader();

    static class XMLGrammarPoolImplExtension extends XMLGrammarPoolImpl {
        public XMLGrammarPoolImplExtension(int initialCapacity) {
            super(initialCapacity);
        }

        int getGrammarCount() {
            return this.fGrammarCount;
        }
    }

    static class XMLGrammarPoolWrapper implements XMLGrammarPool {
        private XMLGrammarPool fGrammarPool;

        XMLGrammarPoolWrapper() {
        }

        public Grammar[] retrieveInitialGrammarSet(String grammarType) {
            return this.fGrammarPool.retrieveInitialGrammarSet(grammarType);
        }

        public void cacheGrammars(String grammarType, Grammar[] grammars) {
            this.fGrammarPool.cacheGrammars(grammarType, grammars);
        }

        public Grammar retrieveGrammar(XMLGrammarDescription desc) {
            return this.fGrammarPool.retrieveGrammar(desc);
        }

        public void lockPool() {
            this.fGrammarPool.lockPool();
        }

        public void unlockPool() {
            this.fGrammarPool.unlockPool();
        }

        public void clear() {
            this.fGrammarPool.clear();
        }

        void setGrammarPool(XMLGrammarPool grammarPool) {
            this.fGrammarPool = grammarPool;
        }

        XMLGrammarPool getGrammarPool() {
            return this.fGrammarPool;
        }
    }

    public XMLSchemaFactory() {
        this.fXMLSchemaLoader.setFeature(SCHEMA_FULL_CHECKING, true);
        this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fXMLGrammarPoolWrapper);
        this.fXMLSchemaLoader.setEntityResolver(this.fDOMEntityResolverWrapper);
        this.fXMLSchemaLoader.setErrorHandler(this.fErrorHandlerWrapper);
        this.fUseGrammarPoolOnly = true;
    }

    public boolean isSchemaLanguageSupported(String schemaLanguage) {
        if (schemaLanguage == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaLanguageNull", null));
        } else if (schemaLanguage.length() != 0) {
            return schemaLanguage.equals("http://www.w3.org/2001/XMLSchema");
        } else {
            throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaLanguageLengthZero", null));
        }
    }

    public LSResourceResolver getResourceResolver() {
        return this.fLSResourceResolver;
    }

    public void setResourceResolver(LSResourceResolver resourceResolver) {
        this.fLSResourceResolver = resourceResolver;
        this.fDOMEntityResolverWrapper.setEntityResolver(resourceResolver);
        this.fXMLSchemaLoader.setEntityResolver(this.fDOMEntityResolverWrapper);
    }

    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.fErrorHandler = errorHandler;
        ErrorHandlerWrapper errorHandlerWrapper = this.fErrorHandlerWrapper;
        if (errorHandler == null) {
            errorHandler = DraconianErrorHandler.getInstance();
        }
        errorHandlerWrapper.setErrorHandler(errorHandler);
        this.fXMLSchemaLoader.setErrorHandler(this.fErrorHandlerWrapper);
    }

    public Schema newSchema(Source[] schemas) throws SAXException {
        XMLGrammarPoolImplExtension pool = new XMLGrammarPoolImplExtension();
        this.fXMLGrammarPoolWrapper.setGrammarPool(pool);
        XMLInputSource[] xmlInputSources = new XMLInputSource[schemas.length];
        for (int i = 0; i < schemas.length; i++) {
            Source source = schemas[i];
            if (source instanceof StreamSource) {
                StreamSource streamSource = (StreamSource) source;
                String publicId = streamSource.getPublicId();
                String systemId = streamSource.getSystemId();
                InputStream inputStream = streamSource.getInputStream();
                Reader reader = streamSource.getReader();
                XMLInputSource xMLInputSource = new XMLInputSource(publicId, systemId, null);
                xMLInputSource.setByteStream(inputStream);
                xMLInputSource.setCharacterStream(reader);
                xmlInputSources[i] = xMLInputSource;
            } else if (source instanceof SAXSource) {
                SAXSource saxSource = (SAXSource) source;
                InputSource inputSource = saxSource.getInputSource();
                if (inputSource == null) {
                    throw new SAXException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SAXSourceNullInputSource", null));
                }
                xmlInputSources[i] = new SAXInputSource(saxSource.getXMLReader(), inputSource);
            } else if (source instanceof DOMSource) {
                DOMSource domSource = (DOMSource) source;
                xmlInputSources[i] = new DOMInputSource(domSource.getNode(), domSource.getSystemId());
            } else if (source instanceof StAXSource) {
                StAXSource staxSource = (StAXSource) source;
                XMLEventReader eventReader = staxSource.getXMLEventReader();
                if (eventReader != null) {
                    xmlInputSources[i] = new StAXInputSource(eventReader);
                } else {
                    xmlInputSources[i] = new StAXInputSource(staxSource.getXMLStreamReader());
                }
            } else if (source == null) {
                throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaSourceArrayMemberNull", null));
            } else {
                throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaFactorySourceUnrecognized", new Object[]{source.getClass().getName()}));
            }
        }
        try {
            AbstractXMLSchema schema;
            this.fXMLSchemaLoader.loadGrammar(xmlInputSources);
            this.fXMLGrammarPoolWrapper.setGrammarPool(null);
            int grammarCount = pool.getGrammarCount();
            AbstractXMLSchema xMLSchema;
            if (!this.fUseGrammarPoolOnly) {
                xMLSchema = new XMLSchema(new ReadOnlyGrammarPool(pool), false);
            } else if (grammarCount > 1) {
                xMLSchema = new XMLSchema(new ReadOnlyGrammarPool(pool));
            } else if (grammarCount == 1) {
                xMLSchema = new SimpleXMLSchema(pool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema")[0]);
            } else {
                schema = new EmptyXMLSchema();
            }
            propagateFeatures(schema);
            return schema;
        } catch (XNIException e) {
            throw Util.toSAXException(e);
        } catch (IOException e2) {
            SAXParseException sAXParseException = new SAXParseException(e2.getMessage(), null, e2);
            if (this.fErrorHandler != null) {
                this.fErrorHandler.error(sAXParseException);
            }
            throw sAXParseException;
        }
    }

    public Schema newSchema() throws SAXException {
        AbstractXMLSchema schema = new WeakReferenceXMLSchema();
        propagateFeatures(schema);
        return schema;
    }

    public Schema newSchema(XMLGrammarPool pool) throws SAXException {
        AbstractXMLSchema schema;
        if (this.fUseGrammarPoolOnly) {
            schema = new XMLSchema(new ReadOnlyGrammarPool(pool));
        } else {
            schema = new XMLSchema(pool, false);
        }
        propagateFeatures(schema);
        return schema;
    }

    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        int i = 1;
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "FeatureNameNull", null));
        } else if (name.startsWith(JAXP_SOURCE_FEATURE_PREFIX) && (name.equals(StreamSource.FEATURE) || name.equals(SAXSource.FEATURE) || name.equals(DOMSource.FEATURE) || name.equals(StAXSource.FEATURE))) {
            return i;
        } else {
            if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
                if (this.fSecurityManager == null) {
                    return 0;
                }
                return i;
            } else if (name.equals(USE_GRAMMAR_POOL_ONLY)) {
                return this.fUseGrammarPoolOnly;
            } else {
                try {
                    return this.fXMLSchemaLoader.getFeature(name);
                } catch (XMLConfigurationException e) {
                    String identifier = e.getIdentifier();
                    Object[] objArr;
                    if (e.getType() == (short) 0) {
                        objArr = new Object[i];
                        objArr[0] = identifier;
                        throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-recognized", objArr));
                    }
                    objArr = new Object[i];
                    objArr[0] = identifier;
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-supported", objArr));
                }
            }
        }
    }

    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "ProperyNameNull", null));
        } else if (name.equals(SECURITY_MANAGER)) {
            return this.fSecurityManager;
        } else {
            if (name.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{name}));
            }
            try {
                return this.fXMLSchemaLoader.getProperty(name);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() == (short) 0) {
                    throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-recognized", new Object[]{identifier}));
                }
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{identifier}));
            }
        }
    }

    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        SecurityManager securityManager = null;
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "FeatureNameNull", null));
        } else if (name.startsWith(JAXP_SOURCE_FEATURE_PREFIX) && (name.equals(StreamSource.FEATURE) || name.equals(SAXSource.FEATURE) || name.equals(DOMSource.FEATURE) || name.equals(StAXSource.FEATURE))) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-read-only", new Object[]{name}));
        } else if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            if (value) {
                securityManager = new SecurityManager();
            }
            this.fSecurityManager = securityManager;
            this.fXMLSchemaLoader.setProperty(SECURITY_MANAGER, this.fSecurityManager);
        } else if (name.equals(USE_GRAMMAR_POOL_ONLY)) {
            this.fUseGrammarPoolOnly = value;
        } else {
            try {
                this.fXMLSchemaLoader.setFeature(name, value);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() == (short) 0) {
                    throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-recognized", new Object[]{identifier}));
                } else {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-supported", new Object[]{identifier}));
                }
            }
        }
    }

    public void setProperty(String name, Object object) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "ProperyNameNull", null));
        } else if (name.equals(SECURITY_MANAGER)) {
            this.fSecurityManager = (SecurityManager) object;
            this.fXMLSchemaLoader.setProperty(SECURITY_MANAGER, this.fSecurityManager);
        } else if (name.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{name}));
        } else {
            try {
                this.fXMLSchemaLoader.setProperty(name, object);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() == (short) 0) {
                    throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-recognized", new Object[]{identifier}));
                } else {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{identifier}));
                }
            }
        }
    }

    private void propagateFeatures(AbstractXMLSchema schema) {
        schema.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, this.fSecurityManager != null);
        String[] features = this.fXMLSchemaLoader.getRecognizedFeatures();
        for (int i = 0; i < features.length; i++) {
            schema.setFeature(features[i], this.fXMLSchemaLoader.getFeature(features[i]));
        }
    }
}
