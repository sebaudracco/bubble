package mf.org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.lang.ref.SoftReference;
import mf.javax.xml.transform.Result;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.stream.StreamResult;
import mf.javax.xml.transform.stream.StreamSource;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.parsers.SAXParser;
import mf.org.apache.xerces.parsers.XML11Configuration;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParseException;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.apache.xml.serialize.OutputFormat;
import mf.org.apache.xml.serialize.Serializer;
import mf.org.apache.xml.serialize.SerializerFactory;
import org.xml.sax.SAXException;

final class StreamValidatorHelper implements ValidatorHelper {
    private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private final XMLSchemaValidatorComponentManager fComponentManager;
    private SoftReference fConfiguration = new SoftReference(null);
    private SoftReference fParser = new SoftReference(null);
    private final XMLSchemaValidator fSchemaValidator;
    private SerializerFactory fSerializerFactory;

    public StreamValidatorHelper(XMLSchemaValidatorComponentManager componentManager) {
        this.fComponentManager = componentManager;
        this.fSchemaValidator = (XMLSchemaValidator) this.fComponentManager.getProperty(SCHEMA_VALIDATOR);
    }

    public void validate(Source source, Result result) throws SAXException, IOException {
        if ((result instanceof StreamResult) || result == null) {
            StreamSource streamSource = (StreamSource) source;
            StreamResult streamResult = (StreamResult) result;
            XMLInputSource input = new XMLInputSource(streamSource.getPublicId(), streamSource.getSystemId(), null);
            input.setByteStream(streamSource.getInputStream());
            input.setCharacterStream(streamSource.getReader());
            boolean newConfig = false;
            XMLParserConfiguration config = (XMLParserConfiguration) this.fConfiguration.get();
            if (config == null) {
                config = initialize();
                newConfig = true;
            } else if (this.fComponentManager.getFeature(PARSER_SETTINGS)) {
                config.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver"));
                config.setProperty(ERROR_HANDLER, this.fComponentManager.getProperty(ERROR_HANDLER));
                config.setProperty(SECURITY_MANAGER, this.fComponentManager.getProperty(SECURITY_MANAGER));
            }
            this.fComponentManager.reset();
            if (streamResult != null) {
                Serializer ser;
                if (this.fSerializerFactory == null) {
                    this.fSerializerFactory = SerializerFactory.getSerializerFactory("xml");
                }
                if (streamResult.getWriter() != null) {
                    ser = this.fSerializerFactory.makeSerializer(streamResult.getWriter(), new OutputFormat());
                } else if (streamResult.getOutputStream() != null) {
                    ser = this.fSerializerFactory.makeSerializer(streamResult.getOutputStream(), new OutputFormat());
                } else if (streamResult.getSystemId() != null) {
                    ser = this.fSerializerFactory.makeSerializer(XMLEntityManager.createOutputStream(streamResult.getSystemId()), new OutputFormat());
                } else {
                    throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "StreamResultNotInitialized", null));
                }
                SAXParser parser = (SAXParser) this.fParser.get();
                if (newConfig || parser == null) {
                    parser = new SAXParser(config);
                    this.fParser = new SoftReference(parser);
                } else {
                    parser.reset();
                }
                config.setDocumentHandler(this.fSchemaValidator);
                this.fSchemaValidator.setDocumentHandler(parser);
                parser.setContentHandler(ser.asContentHandler());
            } else {
                this.fSchemaValidator.setDocumentHandler(null);
            }
            try {
                config.parse(input);
                this.fSchemaValidator.setDocumentHandler(null);
            } catch (XMLParseException e) {
                throw Util.toSAXParseException(e);
            } catch (XNIException e2) {
                throw Util.toSAXException(e2);
            } catch (Throwable th) {
                this.fSchemaValidator.setDocumentHandler(null);
            }
        } else {
            throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "SourceResultMismatch", new Object[]{source.getClass().getName(), result.getClass().getName()}));
        }
    }

    private XMLParserConfiguration initialize() {
        XML11Configuration config = new XML11Configuration();
        config.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver"));
        config.setProperty(ERROR_HANDLER, this.fComponentManager.getProperty(ERROR_HANDLER));
        XMLErrorReporter errorReporter = (XMLErrorReporter) this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        config.setProperty("http://apache.org/xml/properties/internal/error-reporter", errorReporter);
        if (errorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xmft = new XMLMessageFormatter();
            errorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmft);
            errorReporter.putMessageFormatter(XMLMessageFormatter.XMLNS_DOMAIN, xmft);
        }
        config.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table"));
        config.setProperty(VALIDATION_MANAGER, this.fComponentManager.getProperty(VALIDATION_MANAGER));
        config.setProperty(SECURITY_MANAGER, this.fComponentManager.getProperty(SECURITY_MANAGER));
        config.setDocumentHandler(this.fSchemaValidator);
        config.setDTDHandler(null);
        config.setDTDContentModelHandler(null);
        this.fConfiguration = new SoftReference(config);
        return config;
    }
}
