package mf.org.apache.xerces.jaxp;

import mf.javax.xml.validation.TypeInfoProvider;
import mf.javax.xml.validation.ValidatorHandler;
import mf.org.apache.xerces.dom.DOMInputImpl;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.xs.opti.DefaultXMLDocumentHandler;
import mf.org.apache.xerces.util.AttributesProxy;
import mf.org.apache.xerces.util.AugmentationsImpl;
import mf.org.apache.xerces.util.ErrorHandlerProxy;
import mf.org.apache.xerces.util.ErrorHandlerWrapper;
import mf.org.apache.xerces.util.LocatorProxy;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLResourceIdentifierImpl;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.w3c.dom.TypeInfo;
import mf.org.w3c.dom.ls.LSInput;
import mf.org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

final class JAXPValidatorComponent extends TeeXMLDocumentFilterImpl implements XMLComponent {
    private static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final TypeInfoProvider noInfoProvider = new C46461();
    private XMLAttributes fCurrentAttributes;
    private Augmentations fCurrentAug;
    private XMLEntityResolver fEntityResolver;
    private XMLErrorReporter fErrorReporter;
    private SymbolTable fSymbolTable;
    private final SAX2XNI sax2xni = new SAX2XNI();
    private final TypeInfoProvider typeInfoProvider;
    private final ValidatorHandler validator;
    private final XNI2SAX xni2sax = new XNI2SAX();

    class C46461 extends TypeInfoProvider {
        C46461() {
        }

        public TypeInfo getElementTypeInfo() {
            return null;
        }

        public TypeInfo getAttributeTypeInfo(int index) {
            return null;
        }

        public TypeInfo getAttributeTypeInfo(String attributeQName) {
            return null;
        }

        public TypeInfo getAttributeTypeInfo(String attributeUri, String attributeLocalName) {
            return null;
        }

        public boolean isIdAttribute(int index) {
            return false;
        }

        public boolean isSpecified(int index) {
            return false;
        }
    }

    class C46472 extends ErrorHandlerProxy {
        C46472() {
        }

        protected XMLErrorHandler getErrorHandler() {
            XMLErrorHandler handler = JAXPValidatorComponent.this.fErrorReporter.getErrorHandler();
            return handler != null ? handler : new ErrorHandlerWrapper(DraconianErrorHandler.getInstance());
        }
    }

    class C46483 implements LSResourceResolver {
        C46483() {
        }

        public LSInput resolveResource(String type, String ns, String publicId, String systemId, String baseUri) {
            LSInput lSInput = null;
            if (JAXPValidatorComponent.this.fEntityResolver != null) {
                try {
                    XMLInputSource is = JAXPValidatorComponent.this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(publicId, systemId, baseUri, null));
                    if (is != null) {
                        lSInput = new DOMInputImpl();
                        lSInput.setBaseURI(is.getBaseSystemId());
                        lSInput.setByteStream(is.getByteStream());
                        lSInput.setCharacterStream(is.getCharacterStream());
                        lSInput.setEncoding(is.getEncoding());
                        lSInput.setPublicId(is.getPublicId());
                        lSInput.setSystemId(is.getSystemId());
                    }
                } catch (Exception e) {
                    throw new XNIException(e);
                }
            }
            return lSInput;
        }
    }

    private static final class DraconianErrorHandler implements ErrorHandler {
        private static final DraconianErrorHandler ERROR_HANDLER_INSTANCE = new DraconianErrorHandler();

        private DraconianErrorHandler() {
        }

        public static DraconianErrorHandler getInstance() {
            return ERROR_HANDLER_INSTANCE;
        }

        public void warning(SAXParseException e) throws SAXException {
        }

        public void error(SAXParseException e) throws SAXException {
            throw e;
        }

        public void fatalError(SAXParseException e) throws SAXException {
            throw e;
        }
    }

    private final class SAX2XNI extends DefaultHandler {
        private final Augmentations fAugmentations;
        private final QName fQName;

        private SAX2XNI() {
            this.fAugmentations = new AugmentationsImpl();
            this.fQName = new QName();
        }

        public void characters(char[] ch, int start, int len) throws SAXException {
            try {
                handler().characters(new XMLString(ch, start, len), aug());
            } catch (XNIException e) {
                throw toSAXException(e);
            }
        }

        public void ignorableWhitespace(char[] ch, int start, int len) throws SAXException {
            try {
                handler().ignorableWhitespace(new XMLString(ch, start, len), aug());
            } catch (XNIException e) {
                throw toSAXException(e);
            }
        }

        public void startElement(String uri, String localName, String qname, Attributes atts) throws SAXException {
            try {
                JAXPValidatorComponent.this.updateAttributes(atts);
                handler().startElement(toQName(uri, localName, qname), JAXPValidatorComponent.this.fCurrentAttributes, elementAug());
            } catch (XNIException e) {
                throw toSAXException(e);
            }
        }

        public void endElement(String uri, String localName, String qname) throws SAXException {
            try {
                handler().endElement(toQName(uri, localName, qname), aug());
            } catch (XNIException e) {
                throw toSAXException(e);
            }
        }

        private Augmentations elementAug() {
            return aug();
        }

        private Augmentations aug() {
            if (JAXPValidatorComponent.this.fCurrentAug != null) {
                Augmentations r = JAXPValidatorComponent.this.fCurrentAug;
                JAXPValidatorComponent.this.fCurrentAug = null;
                return r;
            }
            this.fAugmentations.removeAllItems();
            return this.fAugmentations;
        }

        private XMLDocumentHandler handler() {
            return JAXPValidatorComponent.this.getDocumentHandler();
        }

        private SAXException toSAXException(XNIException xe) {
            Exception e = xe.getException();
            if (e == null) {
                e = xe;
            }
            if (e instanceof SAXException) {
                return (SAXException) e;
            }
            return new SAXException(e);
        }

        private QName toQName(String uri, String localName, String qname) {
            String prefix = null;
            int idx = qname.indexOf(58);
            if (idx > 0) {
                prefix = JAXPValidatorComponent.this.symbolize(qname.substring(0, idx));
            }
            this.fQName.setValues(prefix, JAXPValidatorComponent.this.symbolize(localName), JAXPValidatorComponent.this.symbolize(qname), JAXPValidatorComponent.this.symbolize(uri));
            return this.fQName;
        }
    }

    private static final class XNI2SAX extends DefaultXMLDocumentHandler {
        private final AttributesProxy fAttributesProxy;
        private ContentHandler fContentHandler;
        protected NamespaceContext fNamespaceContext;
        private String fVersion;

        private XNI2SAX() {
            this.fAttributesProxy = new AttributesProxy(null);
        }

        public void setContentHandler(ContentHandler handler) {
            this.fContentHandler = handler;
        }

        public ContentHandler getContentHandler() {
            return this.fContentHandler;
        }

        public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
            this.fVersion = version;
        }

        public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
            this.fNamespaceContext = namespaceContext;
            this.fContentHandler.setDocumentLocator(new LocatorProxy(locator));
            try {
                this.fContentHandler.startDocument();
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }

        public void endDocument(Augmentations augs) throws XNIException {
            try {
                this.fContentHandler.endDocument();
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }

        public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
            try {
                this.fContentHandler.processingInstruction(target, data.toString());
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }

        public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
            try {
                String uri;
                int count = this.fNamespaceContext.getDeclaredPrefixCount();
                if (count > 0) {
                    for (int i = 0; i < count; i++) {
                        String str;
                        String prefix = this.fNamespaceContext.getDeclaredPrefixAt(i);
                        uri = this.fNamespaceContext.getURI(prefix);
                        ContentHandler contentHandler = this.fContentHandler;
                        if (uri == null) {
                            str = "";
                        } else {
                            str = uri;
                        }
                        contentHandler.startPrefixMapping(prefix, str);
                    }
                }
                uri = element.uri != null ? element.uri : "";
                String localpart = element.localpart;
                this.fAttributesProxy.setAttributes(attributes);
                this.fContentHandler.startElement(uri, localpart, element.rawname, this.fAttributesProxy);
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }

        public void endElement(QName element, Augmentations augs) throws XNIException {
            try {
                this.fContentHandler.endElement(element.uri != null ? element.uri : "", element.localpart, element.rawname);
                int count = this.fNamespaceContext.getDeclaredPrefixCount();
                if (count > 0) {
                    for (int i = 0; i < count; i++) {
                        this.fContentHandler.endPrefixMapping(this.fNamespaceContext.getDeclaredPrefixAt(i));
                    }
                }
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }

        public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
            startElement(element, attributes, augs);
            endElement(element, augs);
        }

        public void characters(XMLString text, Augmentations augs) throws XNIException {
            try {
                this.fContentHandler.characters(text.ch, text.offset, text.length);
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }

        public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
            try {
                this.fContentHandler.ignorableWhitespace(text.ch, text.offset, text.length);
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }
    }

    public JAXPValidatorComponent(ValidatorHandler validatorHandler) {
        this.validator = validatorHandler;
        TypeInfoProvider tip = validatorHandler.getTypeInfoProvider();
        if (tip == null) {
            tip = noInfoProvider;
        }
        this.typeInfoProvider = tip;
        this.xni2sax.setContentHandler(this.validator);
        this.validator.setContentHandler(this.sax2xni);
        setSide(this.xni2sax);
        this.validator.setErrorHandler(new C46472());
        this.validator.setResourceResolver(new C46483());
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        this.fCurrentAttributes = attributes;
        this.fCurrentAug = augs;
        this.xni2sax.startElement(element, attributes, null);
        this.fCurrentAttributes = null;
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        this.fCurrentAug = augs;
        this.xni2sax.endElement(element, null);
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        startElement(element, attributes, augs);
        endElement(element, augs);
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        this.fCurrentAug = augs;
        this.xni2sax.characters(text, null);
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        this.fCurrentAug = augs;
        this.xni2sax.ignorableWhitespace(text, null);
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        this.fSymbolTable = (SymbolTable) componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        try {
            this.fEntityResolver = (XMLEntityResolver) componentManager.getProperty(ENTITY_MANAGER);
        } catch (XMLConfigurationException e) {
            this.fEntityResolver = null;
        }
    }

    private void updateAttributes(Attributes atts) {
        int len = atts.getLength();
        for (int i = 0; i < len; i++) {
            String aqn = atts.getQName(i);
            int j = this.fCurrentAttributes.getIndex(aqn);
            String av = atts.getValue(i);
            if (j == -1) {
                String prefix;
                int idx = aqn.indexOf(58);
                if (idx < 0) {
                    prefix = null;
                } else {
                    prefix = symbolize(aqn.substring(0, idx));
                }
                j = this.fCurrentAttributes.addAttribute(new QName(prefix, symbolize(atts.getLocalName(i)), symbolize(aqn), symbolize(atts.getURI(i))), atts.getType(i), av);
            } else if (!av.equals(this.fCurrentAttributes.getValue(j))) {
                this.fCurrentAttributes.setValue(j, av);
            }
        }
    }

    private String symbolize(String s) {
        return this.fSymbolTable.addSymbol(s);
    }

    public String[] getRecognizedFeatures() {
        return null;
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
    }

    public String[] getRecognizedProperties() {
        return new String[]{ENTITY_MANAGER, "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/symbol-table"};
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
    }

    public Boolean getFeatureDefault(String featureId) {
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        return null;
    }
}
