package mf.org.apache.xerces.jaxp;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map.Entry;
import mf.javax.xml.parsers.DocumentBuilder;
import mf.javax.xml.validation.Schema;
import mf.org.apache.xerces.dom.DOMImplementationImpl;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.dom.DocumentImpl;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import mf.org.apache.xerces.parsers.DOMParser;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class DocumentBuilderImpl extends DocumentBuilder implements JAXPConstants {
    private static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
    private static final String CREATE_ENTITY_REF_NODES_FEATURE = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
    private static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
    private static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
    private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    private static final String XMLSCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
    private final DOMParser domParser;
    private final EntityResolver fInitEntityResolver;
    private final ErrorHandler fInitErrorHandler;
    private final ValidationManager fSchemaValidationManager;
    private final XMLComponent fSchemaValidator;
    private final XMLComponentManager fSchemaValidatorComponentManager;
    private final UnparsedEntityHandler fUnparsedEntityHandler;
    private final Schema grammar;

    DocumentBuilderImpl(DocumentBuilderFactoryImpl dbf, Hashtable dbfAttrs, Hashtable features) throws SAXNotRecognizedException, SAXNotSupportedException {
        this(dbf, dbfAttrs, features, false);
    }

    DocumentBuilderImpl(DocumentBuilderFactoryImpl dbf, Hashtable dbfAttrs, Hashtable features, boolean secureProcessing) throws SAXNotRecognizedException, SAXNotSupportedException {
        boolean z;
        boolean z2 = false;
        this.domParser = new DOMParser();
        if (dbf.isValidating()) {
            this.fInitErrorHandler = new DefaultValidationErrorHandler();
            setErrorHandler(this.fInitErrorHandler);
        } else {
            this.fInitErrorHandler = this.domParser.getErrorHandler();
        }
        this.domParser.setFeature(VALIDATION_FEATURE, dbf.isValidating());
        this.domParser.setFeature(NAMESPACES_FEATURE, dbf.isNamespaceAware());
        DOMParser dOMParser = this.domParser;
        String str = INCLUDE_IGNORABLE_WHITESPACE;
        if (dbf.isIgnoringElementContentWhitespace()) {
            z = false;
        } else {
            z = true;
        }
        dOMParser.setFeature(str, z);
        dOMParser = this.domParser;
        str = CREATE_ENTITY_REF_NODES_FEATURE;
        if (dbf.isExpandEntityReferences()) {
            z = false;
        } else {
            z = true;
        }
        dOMParser.setFeature(str, z);
        dOMParser = this.domParser;
        str = INCLUDE_COMMENTS_FEATURE;
        if (dbf.isIgnoringComments()) {
            z = false;
        } else {
            z = true;
        }
        dOMParser.setFeature(str, z);
        DOMParser dOMParser2 = this.domParser;
        String str2 = CREATE_CDATA_NODES_FEATURE;
        if (!dbf.isCoalescing()) {
            z2 = true;
        }
        dOMParser2.setFeature(str2, z2);
        if (dbf.isXIncludeAware()) {
            this.domParser.setFeature(XINCLUDE_FEATURE, true);
        }
        if (secureProcessing) {
            this.domParser.setProperty(SECURITY_MANAGER, new SecurityManager());
        }
        this.grammar = dbf.getSchema();
        if (this.grammar != null) {
            XMLComponent validatorComponent;
            XMLParserConfiguration config = this.domParser.getXMLParserConfiguration();
            if (this.grammar instanceof XSGrammarPoolContainer) {
                validatorComponent = new XMLSchemaValidator();
                this.fSchemaValidationManager = new ValidationManager();
                this.fUnparsedEntityHandler = new UnparsedEntityHandler(this.fSchemaValidationManager);
                config.setDTDHandler(this.fUnparsedEntityHandler);
                this.fUnparsedEntityHandler.setDTDHandler(this.domParser);
                this.domParser.setDTDSource(this.fUnparsedEntityHandler);
                this.fSchemaValidatorComponentManager = new SchemaValidatorConfiguration(config, (XSGrammarPoolContainer) this.grammar, this.fSchemaValidationManager);
            } else {
                validatorComponent = new JAXPValidatorComponent(this.grammar.newValidatorHandler());
                this.fSchemaValidationManager = null;
                this.fUnparsedEntityHandler = null;
                this.fSchemaValidatorComponentManager = config;
            }
            config.addRecognizedFeatures(validatorComponent.getRecognizedFeatures());
            config.addRecognizedProperties(validatorComponent.getRecognizedProperties());
            config.setDocumentHandler((XMLDocumentHandler) validatorComponent);
            ((XMLDocumentSource) validatorComponent).setDocumentHandler(this.domParser);
            this.domParser.setDocumentSource((XMLDocumentSource) validatorComponent);
            this.fSchemaValidator = validatorComponent;
        } else {
            this.fSchemaValidationManager = null;
            this.fUnparsedEntityHandler = null;
            this.fSchemaValidatorComponentManager = null;
            this.fSchemaValidator = null;
        }
        setFeatures(features);
        setDocumentBuilderFactoryAttributes(dbfAttrs);
        this.fInitEntityResolver = this.domParser.getEntityResolver();
    }

    private void setFeatures(Hashtable features) throws SAXNotSupportedException, SAXNotRecognizedException {
        if (features != null) {
            for (Entry entry : features.entrySet()) {
                this.domParser.setFeature((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
            }
        }
    }

    private void setDocumentBuilderFactoryAttributes(Hashtable dbfAttrs) throws SAXNotSupportedException, SAXNotRecognizedException {
        if (dbfAttrs != null) {
            for (Entry entry : dbfAttrs.entrySet()) {
                String name = (String) entry.getKey();
                Object val = entry.getValue();
                if (val instanceof Boolean) {
                    this.domParser.setFeature(name, ((Boolean) val).booleanValue());
                } else if (JAXPConstants.JAXP_SCHEMA_LANGUAGE.equals(name)) {
                    if ("http://www.w3.org/2001/XMLSchema".equals(val) && isValidating()) {
                        this.domParser.setFeature(XMLSCHEMA_VALIDATION_FEATURE, true);
                        this.domParser.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
                    }
                } else if (!JAXPConstants.JAXP_SCHEMA_SOURCE.equals(name)) {
                    this.domParser.setProperty(name, val);
                } else if (isValidating()) {
                    String value = (String) dbfAttrs.get(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
                    if (value == null || !"http://www.w3.org/2001/XMLSchema".equals(value)) {
                        throw new IllegalArgumentException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "jaxp-order-not-supported", new Object[]{JAXPConstants.JAXP_SCHEMA_LANGUAGE, JAXPConstants.JAXP_SCHEMA_SOURCE}));
                    }
                    this.domParser.setProperty(name, val);
                } else {
                    continue;
                }
            }
        }
    }

    public Document newDocument() {
        return new DocumentImpl();
    }

    public DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }

    public Document parse(InputSource is) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "jaxp-null-input-source", null));
        }
        if (this.fSchemaValidator != null) {
            if (this.fSchemaValidationManager != null) {
                this.fSchemaValidationManager.reset();
                this.fUnparsedEntityHandler.reset();
            }
            resetSchemaValidator();
        }
        this.domParser.parse(is);
        Document doc = this.domParser.getDocument();
        this.domParser.dropDocumentReferences();
        return doc;
    }

    public boolean isNamespaceAware() {
        try {
            return this.domParser.getFeature(NAMESPACES_FEATURE);
        } catch (SAXException x) {
            throw new IllegalStateException(x.getMessage());
        }
    }

    public boolean isValidating() {
        try {
            return this.domParser.getFeature(VALIDATION_FEATURE);
        } catch (SAXException x) {
            throw new IllegalStateException(x.getMessage());
        }
    }

    public boolean isXIncludeAware() {
        try {
            return this.domParser.getFeature(XINCLUDE_FEATURE);
        } catch (SAXException e) {
            return false;
        }
    }

    public void setEntityResolver(EntityResolver er) {
        this.domParser.setEntityResolver(er);
    }

    public void setErrorHandler(ErrorHandler eh) {
        this.domParser.setErrorHandler(eh);
    }

    public Schema getSchema() {
        return this.grammar;
    }

    public void reset() {
        if (this.domParser.getErrorHandler() != this.fInitErrorHandler) {
            this.domParser.setErrorHandler(this.fInitErrorHandler);
        }
        if (this.domParser.getEntityResolver() != this.fInitEntityResolver) {
            this.domParser.setEntityResolver(this.fInitEntityResolver);
        }
    }

    DOMParser getDOMParser() {
        return this.domParser;
    }

    private void resetSchemaValidator() throws SAXException {
        try {
            this.fSchemaValidator.reset(this.fSchemaValidatorComponentManager);
        } catch (XMLConfigurationException e) {
            throw new SAXException(e);
        }
    }
}
