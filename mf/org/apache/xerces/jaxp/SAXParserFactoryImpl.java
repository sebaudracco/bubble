package mf.org.apache.xerces.jaxp;

import java.util.Hashtable;
import mf.javax.xml.XMLConstants;
import mf.javax.xml.parsers.ParserConfigurationException;
import mf.javax.xml.parsers.SAXParser;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.javax.xml.validation.Schema;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class SAXParserFactoryImpl extends SAXParserFactory {
    private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    private boolean fSecureProcess = false;
    private Hashtable features;
    private Schema grammar;
    private boolean isXIncludeAware;

    public SAXParser newSAXParser() throws ParserConfigurationException {
        try {
            return new SAXParserImpl(this, this.features, this.fSecureProcess);
        } catch (SAXException se) {
            throw new ParserConfigurationException(se.getMessage());
        }
    }

    private SAXParserImpl newSAXParserImpl() throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        try {
            return new SAXParserImpl(this, this.features);
        } catch (SAXNotSupportedException e) {
            throw e;
        } catch (SAXNotRecognizedException e2) {
            throw e2;
        } catch (SAXException se) {
            throw new ParserConfigurationException(se.getMessage());
        }
    }

    public void setFeature(String name, boolean value) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException();
        } else if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            this.fSecureProcess = value;
        } else if (name.equals(NAMESPACES_FEATURE)) {
            setNamespaceAware(value);
        } else if (name.equals(VALIDATION_FEATURE)) {
            setValidating(value);
        } else if (name.equals(XINCLUDE_FEATURE)) {
            setXIncludeAware(value);
        } else {
            if (this.features == null) {
                this.features = new Hashtable();
            }
            this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
            try {
                newSAXParserImpl();
            } catch (SAXNotSupportedException e) {
                this.features.remove(name);
                throw e;
            } catch (SAXNotRecognizedException e2) {
                this.features.remove(name);
                throw e2;
            }
        }
    }

    public boolean getFeature(String name) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException();
        } else if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            return this.fSecureProcess;
        } else {
            if (name.equals(NAMESPACES_FEATURE)) {
                return isNamespaceAware();
            }
            if (name.equals(VALIDATION_FEATURE)) {
                return isValidating();
            }
            if (name.equals(XINCLUDE_FEATURE)) {
                return isXIncludeAware();
            }
            return newSAXParserImpl().getXMLReader().getFeature(name);
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
}
