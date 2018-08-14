package mf.org.apache.xerces.jaxp;

import java.util.Hashtable;
import mf.javax.xml.XMLConstants;
import mf.javax.xml.parsers.DocumentBuilder;
import mf.javax.xml.parsers.DocumentBuilderFactory;
import mf.javax.xml.parsers.ParserConfigurationException;
import mf.javax.xml.validation.Schema;
import mf.org.apache.xerces.parsers.DOMParser;
import mf.org.apache.xerces.util.SAXMessageFormatter;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory {
    private static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
    private static final String CREATE_ENTITY_REF_NODES_FEATURE = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
    private static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
    private static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
    private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    private Hashtable attributes;
    private boolean fSecureProcess = false;
    private Hashtable features;
    private Schema grammar;
    private boolean isXIncludeAware;

    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        if (!(this.grammar == null || this.attributes == null)) {
            if (this.attributes.containsKey(JAXPConstants.JAXP_SCHEMA_LANGUAGE)) {
                throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "schema-already-specified", new Object[]{JAXPConstants.JAXP_SCHEMA_LANGUAGE}));
            } else if (this.attributes.containsKey(JAXPConstants.JAXP_SCHEMA_SOURCE)) {
                throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "schema-already-specified", new Object[]{JAXPConstants.JAXP_SCHEMA_SOURCE}));
            }
        }
        try {
            return new DocumentBuilderImpl(this, this.attributes, this.features, this.fSecureProcess);
        } catch (SAXException se) {
            throw new ParserConfigurationException(se.getMessage());
        }
    }

    public void setAttribute(String name, Object value) throws IllegalArgumentException {
        if (value != null) {
            if (this.attributes == null) {
                this.attributes = new Hashtable();
            }
            this.attributes.put(name, value);
            try {
                DocumentBuilderImpl documentBuilderImpl = new DocumentBuilderImpl(this, this.attributes, this.features);
            } catch (Exception e) {
                this.attributes.remove(name);
                throw new IllegalArgumentException(e.getMessage());
            }
        } else if (this.attributes != null) {
            this.attributes.remove(name);
        }
    }

    public Object getAttribute(String name) throws IllegalArgumentException {
        if (this.attributes != null) {
            Object val = this.attributes.get(name);
            if (val != null) {
                return val;
            }
        }
        DOMParser domParser = null;
        try {
            domParser = new DocumentBuilderImpl(this, this.attributes, this.features).getDOMParser();
            return domParser.getProperty(name);
        } catch (SAXException se1) {
            try {
                return domParser.getFeature(name) ? Boolean.TRUE : Boolean.FALSE;
            } catch (SAXException e) {
                throw new IllegalArgumentException(se1.getMessage());
            }
        }
    }

    public Schema getSchema() {
        return this.grammar;
    }

    public void setSchema(Schema grammar) {
        this.grammar = grammar;
    }

    public boolean isXIncludeAware() {
        return this.isXIncludeAware;
    }

    public void setXIncludeAware(boolean state) {
        this.isXIncludeAware = state;
    }

    public boolean getFeature(String name) throws ParserConfigurationException {
        if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            return this.fSecureProcess;
        }
        if (name.equals(NAMESPACES_FEATURE)) {
            return isNamespaceAware();
        }
        if (name.equals(VALIDATION_FEATURE)) {
            return isValidating();
        }
        if (name.equals(XINCLUDE_FEATURE)) {
            return isXIncludeAware();
        }
        if (name.equals(INCLUDE_IGNORABLE_WHITESPACE)) {
            if (isIgnoringElementContentWhitespace()) {
                return false;
            }
            return true;
        } else if (name.equals(CREATE_ENTITY_REF_NODES_FEATURE)) {
            if (isExpandEntityReferences()) {
                return false;
            }
            return true;
        } else if (name.equals(INCLUDE_COMMENTS_FEATURE)) {
            if (isIgnoringComments()) {
                return false;
            }
            return true;
        } else if (!name.equals(CREATE_CDATA_NODES_FEATURE)) {
            if (this.features != null) {
                Object val = this.features.get(name);
                if (val != null) {
                    return ((Boolean) val).booleanValue();
                }
            }
            try {
                return new DocumentBuilderImpl(this, this.attributes, this.features).getDOMParser().getFeature(name);
            } catch (SAXException e) {
                throw new ParserConfigurationException(e.getMessage());
            }
        } else if (isCoalescing()) {
            return false;
        } else {
            return true;
        }
    }

    public void setFeature(String name, boolean value) throws ParserConfigurationException {
        boolean z = false;
        if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            this.fSecureProcess = value;
        } else if (name.equals(NAMESPACES_FEATURE)) {
            setNamespaceAware(value);
        } else if (name.equals(VALIDATION_FEATURE)) {
            setValidating(value);
        } else if (name.equals(XINCLUDE_FEATURE)) {
            setXIncludeAware(value);
        } else if (name.equals(INCLUDE_IGNORABLE_WHITESPACE)) {
            if (!value) {
                z = true;
            }
            setIgnoringElementContentWhitespace(z);
        } else if (name.equals(CREATE_ENTITY_REF_NODES_FEATURE)) {
            if (!value) {
                z = true;
            }
            setExpandEntityReferences(z);
        } else if (name.equals(INCLUDE_COMMENTS_FEATURE)) {
            if (!value) {
                z = true;
            }
            setIgnoringComments(z);
        } else if (name.equals(CREATE_CDATA_NODES_FEATURE)) {
            if (!value) {
                z = true;
            }
            setCoalescing(z);
        } else {
            if (this.features == null) {
                this.features = new Hashtable();
            }
            this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
            try {
                DocumentBuilderImpl documentBuilderImpl = new DocumentBuilderImpl(this, this.attributes, this.features);
            } catch (SAXNotSupportedException e) {
                this.features.remove(name);
                throw new ParserConfigurationException(e.getMessage());
            } catch (SAXNotRecognizedException e2) {
                this.features.remove(name);
                throw new ParserConfigurationException(e2.getMessage());
            }
        }
    }
}
