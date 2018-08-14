package mf.org.apache.xerces.parsers;

import java.io.IOException;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;

public abstract class XMLParser {
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/entity-resolver", ERROR_HANDLER};
    protected final XMLParserConfiguration fConfiguration;

    protected XMLParser(XMLParserConfiguration config) {
        this.fConfiguration = config;
        this.fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
    }

    public void parse(XMLInputSource inputSource) throws XNIException, IOException {
        reset();
        this.fConfiguration.parse(inputSource);
    }

    protected void reset() throws XNIException {
    }
}
