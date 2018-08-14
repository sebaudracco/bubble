package mf.org.apache.xerces.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.util.ParserConfigurationSettings;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;

public abstract class BasicParserConfiguration extends ParserConfigurationSettings implements XMLParserConfiguration {
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    protected ArrayList fComponents;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fLastComponent;
    protected Locale fLocale;
    protected SymbolTable fSymbolTable;

    public abstract void parse(XMLInputSource xMLInputSource) throws XNIException, IOException;

    protected BasicParserConfiguration() {
        this(null, null);
    }

    protected BasicParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    protected BasicParserConfiguration(SymbolTable symbolTable, XMLComponentManager parentSettings) {
        super(parentSettings);
        this.fComponents = new ArrayList();
        this.fRecognizedFeatures = new ArrayList();
        this.fRecognizedProperties = new ArrayList();
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        addRecognizedFeatures(new String[]{"http://apache.org/xml/features/internal/parser-settings", VALIDATION, NAMESPACES, EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES});
        this.fFeatures.put("http://apache.org/xml/features/internal/parser-settings", Boolean.TRUE);
        this.fFeatures.put(VALIDATION, Boolean.FALSE);
        this.fFeatures.put(NAMESPACES, Boolean.TRUE);
        this.fFeatures.put(EXTERNAL_GENERAL_ENTITIES, Boolean.TRUE);
        this.fFeatures.put(EXTERNAL_PARAMETER_ENTITIES, Boolean.TRUE);
        addRecognizedProperties(new String[]{XML_STRING, "http://apache.org/xml/properties/internal/symbol-table", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver"});
        if (symbolTable == null) {
            symbolTable = new SymbolTable();
        }
        this.fSymbolTable = symbolTable;
        this.fProperties.put("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
    }

    protected void addComponent(XMLComponent component) {
        if (!this.fComponents.contains(component)) {
            this.fComponents.add(component);
            String[] recognizedFeatures = component.getRecognizedFeatures();
            addRecognizedFeatures(recognizedFeatures);
            String[] recognizedProperties = component.getRecognizedProperties();
            addRecognizedProperties(recognizedProperties);
            if (recognizedFeatures != null) {
                for (String featureId : recognizedFeatures) {
                    Boolean state = component.getFeatureDefault(featureId);
                    if (state != null) {
                        super.setFeature(featureId, state.booleanValue());
                    }
                }
            }
            if (recognizedProperties != null) {
                for (String propertyId : recognizedProperties) {
                    Object value = component.getPropertyDefault(propertyId);
                    if (value != null) {
                        super.setProperty(propertyId, value);
                    }
                }
            }
        }
    }

    public void setDocumentHandler(XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
        if (this.fLastComponent != null) {
            this.fLastComponent.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fLastComponent);
            }
        }
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public void setDTDHandler(XMLDTDHandler dtdHandler) {
        this.fDTDHandler = dtdHandler;
    }

    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    public void setDTDContentModelHandler(XMLDTDContentModelHandler handler) {
        this.fDTDContentModelHandler = handler;
    }

    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    public void setEntityResolver(XMLEntityResolver resolver) {
        this.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", resolver);
    }

    public XMLEntityResolver getEntityResolver() {
        return (XMLEntityResolver) this.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }

    public void setErrorHandler(XMLErrorHandler errorHandler) {
        this.fProperties.put(ERROR_HANDLER, errorHandler);
    }

    public XMLErrorHandler getErrorHandler() {
        return (XMLErrorHandler) this.fProperties.get(ERROR_HANDLER);
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        int count = this.fComponents.size();
        for (int i = 0; i < count; i++) {
            ((XMLComponent) this.fComponents.get(i)).setFeature(featureId, state);
        }
        super.setFeature(featureId, state);
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        int count = this.fComponents.size();
        for (int i = 0; i < count; i++) {
            ((XMLComponent) this.fComponents.get(i)).setProperty(propertyId, value);
        }
        super.setProperty(propertyId, value);
    }

    public void setLocale(Locale locale) throws XNIException {
        this.fLocale = locale;
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    protected void reset() throws XNIException {
        int count = this.fComponents.size();
        for (int i = 0; i < count; i++) {
            ((XMLComponent) this.fComponents.get(i)).reset(this);
        }
    }

    protected void checkProperty(String propertyId) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.SAX_PROPERTY_PREFIX) && propertyId.length() - Constants.SAX_PROPERTY_PREFIX.length() == Constants.XML_STRING_PROPERTY.length() && propertyId.endsWith(Constants.XML_STRING_PROPERTY)) {
            throw new XMLConfigurationException((short) 1, propertyId);
        }
        super.checkProperty(propertyId);
    }

    protected void checkFeature(String featureId) throws XMLConfigurationException {
        if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX) && featureId.length() - Constants.XERCES_FEATURE_PREFIX.length() == Constants.PARSER_SETTINGS.length() && featureId.endsWith(Constants.PARSER_SETTINGS)) {
            throw new XMLConfigurationException((short) 1, featureId);
        }
        super.checkFeature(featureId);
    }
}
