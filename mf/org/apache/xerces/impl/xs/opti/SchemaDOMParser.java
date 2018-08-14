package mf.org.apache.xerces.impl.xs.opti;

import java.io.IOException;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.util.XMLAttributesImpl;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.w3c.dom.Document;

public class SchemaDOMParser extends DefaultXMLDocumentHandler {
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    public static final String GENERATE_SYNTHETIC_ANNOTATION = "http://apache.org/xml/features/generate-synthetic-annotations";
    XMLParserConfiguration config;
    private int fAnnotationDepth = -1;
    private ElementImpl fCurrentAnnotationElement;
    private int fDepth = -1;
    private XMLAttributes fEmptyAttr = new XMLAttributesImpl();
    XMLErrorReporter fErrorReporter;
    private boolean fGenerateSyntheticAnnotation = false;
    private BooleanStack fHasNonSchemaAttributes = new BooleanStack();
    private int fInnerAnnotationDepth = -1;
    protected XMLLocator fLocator;
    protected NamespaceContext fNamespaceContext = null;
    private BooleanStack fSawAnnotation = new BooleanStack();
    SchemaDOM schemaDOM;

    private static final class BooleanStack {
        private boolean[] fData;
        private int fDepth;

        public int size() {
            return this.fDepth;
        }

        public void push(boolean value) {
            ensureCapacity(this.fDepth + 1);
            boolean[] zArr = this.fData;
            int i = this.fDepth;
            this.fDepth = i + 1;
            zArr[i] = value;
        }

        public boolean pop() {
            boolean[] zArr = this.fData;
            int i = this.fDepth - 1;
            this.fDepth = i;
            return zArr[i];
        }

        public void clear() {
            this.fDepth = 0;
        }

        private void ensureCapacity(int size) {
            if (this.fData == null) {
                this.fData = new boolean[32];
            } else if (this.fData.length <= size) {
                boolean[] newdata = new boolean[(this.fData.length * 2)];
                System.arraycopy(this.fData, 0, newdata, 0, this.fData.length);
                this.fData = newdata;
            }
        }
    }

    public SchemaDOMParser(XMLParserConfiguration config) {
        this.config = config;
        config.setDocumentHandler(this);
        config.setDTDHandler(this);
        config.setDTDContentModelHandler(this);
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
        this.fErrorReporter = (XMLErrorReporter) this.config.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fGenerateSyntheticAnnotation = this.config.getFeature(GENERATE_SYNTHETIC_ANNOTATION);
        this.fHasNonSchemaAttributes.clear();
        this.fSawAnnotation.clear();
        this.schemaDOM = new SchemaDOM();
        this.fCurrentAnnotationElement = null;
        this.fAnnotationDepth = -1;
        this.fInnerAnnotationDepth = -1;
        this.fDepth = -1;
        this.fLocator = locator;
        this.fNamespaceContext = namespaceContext;
        this.schemaDOM.setDocumentURI(locator.getExpandedSystemId());
    }

    public void endDocument(Augmentations augs) throws XNIException {
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        if (this.fAnnotationDepth > -1) {
            this.schemaDOM.comment(text);
        }
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        if (this.fAnnotationDepth > -1) {
            this.schemaDOM.processingInstruction(target, data);
        }
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        if (this.fInnerAnnotationDepth == -1) {
            int i = text.offset;
            while (i < text.offset + text.length) {
                if (XMLChar.isSpace(text.ch[i])) {
                    i++;
                } else {
                    String txt = new String(text.ch, i, (text.length + text.offset) - i);
                    this.fErrorReporter.reportError(this.fLocator, XSMessageFormatter.SCHEMA_DOMAIN, "s4s-elt-character", new Object[]{txt}, (short) 1);
                    return;
                }
            }
            return;
        }
        this.schemaDOM.characters(text);
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        this.fDepth++;
        if (this.fAnnotationDepth == -1) {
            if (element.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && element.localpart == SchemaSymbols.ELT_ANNOTATION) {
                if (this.fGenerateSyntheticAnnotation) {
                    if (this.fSawAnnotation.size() > 0) {
                        this.fSawAnnotation.pop();
                    }
                    this.fSawAnnotation.push(true);
                }
                this.fAnnotationDepth = this.fDepth;
                this.schemaDOM.startAnnotation(element, attributes, this.fNamespaceContext);
                this.fCurrentAnnotationElement = this.schemaDOM.startElement(element, attributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
                return;
            } else if (element.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && this.fGenerateSyntheticAnnotation) {
                this.fSawAnnotation.push(false);
                this.fHasNonSchemaAttributes.push(hasNonSchemaAttributes(element, attributes));
            }
        } else if (this.fDepth == this.fAnnotationDepth + 1) {
            this.fInnerAnnotationDepth = this.fDepth;
            this.schemaDOM.startAnnotationElement(element, attributes);
        } else {
            this.schemaDOM.startAnnotationElement(element, attributes);
            return;
        }
        this.schemaDOM.startElement(element, attributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        if (this.fGenerateSyntheticAnnotation && this.fAnnotationDepth == -1 && element.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && element.localpart != SchemaSymbols.ELT_ANNOTATION && hasNonSchemaAttributes(element, attributes)) {
            String elemRawName;
            this.schemaDOM.startElement(element, attributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
            attributes.removeAllAttributes();
            String schemaPrefix = this.fNamespaceContext.getPrefix(SchemaSymbols.URI_SCHEMAFORSCHEMA);
            String annRawName = schemaPrefix.length() == 0 ? SchemaSymbols.ELT_ANNOTATION : new StringBuilder(String.valueOf(schemaPrefix)).append(':').append(SchemaSymbols.ELT_ANNOTATION).toString();
            this.schemaDOM.startAnnotation(annRawName, attributes, this.fNamespaceContext);
            if (schemaPrefix.length() == 0) {
                elemRawName = SchemaSymbols.ELT_DOCUMENTATION;
            } else {
                elemRawName = new StringBuilder(String.valueOf(schemaPrefix)).append(':').append(SchemaSymbols.ELT_DOCUMENTATION).toString();
            }
            this.schemaDOM.startAnnotationElement(elemRawName, attributes);
            this.schemaDOM.charactersRaw("SYNTHETIC_ANNOTATION");
            this.schemaDOM.endSyntheticAnnotationElement(elemRawName, false);
            this.schemaDOM.endSyntheticAnnotationElement(annRawName, true);
            this.schemaDOM.endElement();
            return;
        }
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.startAnnotationElement(element, attributes);
        } else if (element.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && element.localpart == SchemaSymbols.ELT_ANNOTATION) {
            this.schemaDOM.startAnnotation(element, attributes, this.fNamespaceContext);
        }
        ElementImpl newElem = this.schemaDOM.emptyElement(element, attributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.endAnnotationElement(element);
        } else if (element.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && element.localpart == SchemaSymbols.ELT_ANNOTATION) {
            this.schemaDOM.endAnnotation(element, newElem);
        }
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        if (this.fAnnotationDepth <= -1) {
            if (element.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && this.fGenerateSyntheticAnnotation) {
                boolean value = this.fHasNonSchemaAttributes.pop();
                boolean sawann = this.fSawAnnotation.pop();
                if (value && !sawann) {
                    String schemaPrefix = this.fNamespaceContext.getPrefix(SchemaSymbols.URI_SCHEMAFORSCHEMA);
                    String annRawName = schemaPrefix.length() == 0 ? SchemaSymbols.ELT_ANNOTATION : new StringBuilder(String.valueOf(schemaPrefix)).append(':').append(SchemaSymbols.ELT_ANNOTATION).toString();
                    this.schemaDOM.startAnnotation(annRawName, this.fEmptyAttr, this.fNamespaceContext);
                    String elemRawName = schemaPrefix.length() == 0 ? SchemaSymbols.ELT_DOCUMENTATION : new StringBuilder(String.valueOf(schemaPrefix)).append(':').append(SchemaSymbols.ELT_DOCUMENTATION).toString();
                    this.schemaDOM.startAnnotationElement(elemRawName, this.fEmptyAttr);
                    this.schemaDOM.charactersRaw("SYNTHETIC_ANNOTATION");
                    this.schemaDOM.endSyntheticAnnotationElement(elemRawName, false);
                    this.schemaDOM.endSyntheticAnnotationElement(annRawName, true);
                }
            }
            this.schemaDOM.endElement();
        } else if (this.fInnerAnnotationDepth == this.fDepth) {
            this.fInnerAnnotationDepth = -1;
            this.schemaDOM.endAnnotationElement(element);
            this.schemaDOM.endElement();
        } else if (this.fAnnotationDepth == this.fDepth) {
            this.fAnnotationDepth = -1;
            this.schemaDOM.endAnnotation(element, this.fCurrentAnnotationElement);
            this.schemaDOM.endElement();
        } else {
            this.schemaDOM.endAnnotationElement(element);
        }
        this.fDepth--;
    }

    private boolean hasNonSchemaAttributes(QName element, XMLAttributes attributes) {
        int length = attributes.getLength();
        int i = 0;
        while (i < length) {
            String uri = attributes.getURI(i);
            if (uri != null && uri != SchemaSymbols.URI_SCHEMAFORSCHEMA && uri != NamespaceContext.XMLNS_URI && (uri != NamespaceContext.XML_URI || attributes.getQName(i) != SchemaSymbols.ATT_XML_LANG || element.localpart != SchemaSymbols.ELT_SCHEMA)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.characters(text);
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.startAnnotationCDATA();
        }
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.endAnnotationCDATA();
        }
    }

    public Document getDocument() {
        return this.schemaDOM;
    }

    public void setFeature(String featureId, boolean state) {
        this.config.setFeature(featureId, state);
    }

    public boolean getFeature(String featureId) {
        return this.config.getFeature(featureId);
    }

    public void setProperty(String propertyId, Object value) {
        this.config.setProperty(propertyId, value);
    }

    public Object getProperty(String propertyId) {
        return this.config.getProperty(propertyId);
    }

    public void setEntityResolver(XMLEntityResolver er) {
        this.config.setEntityResolver(er);
    }

    public void parse(XMLInputSource inputSource) throws IOException {
        this.config.parse(inputSource);
    }

    public void reset() {
        ((SchemaParsingConfig) this.config).reset();
    }

    public void resetNodePool() {
        ((SchemaParsingConfig) this.config).resetNodePool();
    }
}
