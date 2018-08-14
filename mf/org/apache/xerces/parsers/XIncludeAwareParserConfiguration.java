package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xinclude.XIncludeHandler;
import mf.org.apache.xerces.xinclude.XIncludeNamespaceSupport;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;

public class XIncludeAwareParserConfiguration extends XML11Configuration {
    protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
    protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    protected static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
    protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
    protected static final String XINCLUDE_HANDLER = "http://apache.org/xml/properties/internal/xinclude-handler";
    protected NamespaceContext fCurrentNSContext;
    protected NamespaceSupport fNonXIncludeNSContext;
    protected boolean fXIncludeEnabled;
    protected XIncludeHandler fXIncludeHandler;
    protected XIncludeNamespaceSupport fXIncludeNSContext;

    public XIncludeAwareParserConfiguration() {
        this(null, null, null);
    }

    public XIncludeAwareParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public XIncludeAwareParserConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }

    public XIncludeAwareParserConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
        this.fXIncludeEnabled = false;
        addRecognizedFeatures(new String[]{ALLOW_UE_AND_NOTATION_EVENTS, XINCLUDE_FIXUP_BASE_URIS, XINCLUDE_FIXUP_LANGUAGE});
        addRecognizedProperties(new String[]{XINCLUDE_HANDLER, NAMESPACE_CONTEXT});
        setFeature(ALLOW_UE_AND_NOTATION_EVENTS, true);
        setFeature(XINCLUDE_FIXUP_BASE_URIS, true);
        setFeature(XINCLUDE_FIXUP_LANGUAGE, true);
        this.fNonXIncludeNSContext = new NamespaceSupport();
        this.fCurrentNSContext = this.fNonXIncludeNSContext;
        setProperty(NAMESPACE_CONTEXT, this.fNonXIncludeNSContext);
    }

    protected void configurePipeline() {
        super.configurePipeline();
        if (this.fXIncludeEnabled) {
            XMLDocumentSource prev;
            if (this.fXIncludeHandler == null) {
                this.fXIncludeHandler = new XIncludeHandler();
                setProperty(XINCLUDE_HANDLER, this.fXIncludeHandler);
                addCommonComponent(this.fXIncludeHandler);
                this.fXIncludeHandler.reset(this);
            }
            if (this.fCurrentNSContext != this.fXIncludeNSContext) {
                if (this.fXIncludeNSContext == null) {
                    this.fXIncludeNSContext = new XIncludeNamespaceSupport();
                }
                this.fCurrentNSContext = this.fXIncludeNSContext;
                setProperty(NAMESPACE_CONTEXT, this.fXIncludeNSContext);
            }
            this.fDTDScanner.setDTDHandler(this.fDTDProcessor);
            this.fDTDProcessor.setDTDSource(this.fDTDScanner);
            this.fDTDProcessor.setDTDHandler(this.fXIncludeHandler);
            this.fXIncludeHandler.setDTDSource(this.fDTDProcessor);
            this.fXIncludeHandler.setDTDHandler(this.fDTDHandler);
            if (this.fDTDHandler != null) {
                this.fDTDHandler.setDTDSource(this.fXIncludeHandler);
            }
            if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
                prev = this.fSchemaValidator.getDocumentSource();
            } else {
                prev = this.fLastComponent;
                this.fLastComponent = this.fXIncludeHandler;
            }
            XMLDocumentHandler next = prev.getDocumentHandler();
            prev.setDocumentHandler(this.fXIncludeHandler);
            this.fXIncludeHandler.setDocumentSource(prev);
            if (next != null) {
                this.fXIncludeHandler.setDocumentHandler(next);
                next.setDocumentSource(this.fXIncludeHandler);
            }
        } else if (this.fCurrentNSContext != this.fNonXIncludeNSContext) {
            this.fCurrentNSContext = this.fNonXIncludeNSContext;
            setProperty(NAMESPACE_CONTEXT, this.fNonXIncludeNSContext);
        }
    }

    protected void configureXML11Pipeline() {
        super.configureXML11Pipeline();
        if (this.fXIncludeEnabled) {
            XMLDocumentSource prev;
            if (this.fXIncludeHandler == null) {
                this.fXIncludeHandler = new XIncludeHandler();
                setProperty(XINCLUDE_HANDLER, this.fXIncludeHandler);
                addCommonComponent(this.fXIncludeHandler);
                this.fXIncludeHandler.reset(this);
            }
            if (this.fCurrentNSContext != this.fXIncludeNSContext) {
                if (this.fXIncludeNSContext == null) {
                    this.fXIncludeNSContext = new XIncludeNamespaceSupport();
                }
                this.fCurrentNSContext = this.fXIncludeNSContext;
                setProperty(NAMESPACE_CONTEXT, this.fXIncludeNSContext);
            }
            this.fXML11DTDScanner.setDTDHandler(this.fXML11DTDProcessor);
            this.fXML11DTDProcessor.setDTDSource(this.fXML11DTDScanner);
            this.fXML11DTDProcessor.setDTDHandler(this.fXIncludeHandler);
            this.fXIncludeHandler.setDTDSource(this.fXML11DTDProcessor);
            this.fXIncludeHandler.setDTDHandler(this.fDTDHandler);
            if (this.fDTDHandler != null) {
                this.fDTDHandler.setDTDSource(this.fXIncludeHandler);
            }
            if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
                prev = this.fSchemaValidator.getDocumentSource();
            } else {
                prev = this.fLastComponent;
                this.fLastComponent = this.fXIncludeHandler;
            }
            XMLDocumentHandler next = prev.getDocumentHandler();
            prev.setDocumentHandler(this.fXIncludeHandler);
            this.fXIncludeHandler.setDocumentSource(prev);
            if (next != null) {
                this.fXIncludeHandler.setDocumentHandler(next);
                next.setDocumentSource(this.fXIncludeHandler);
            }
        } else if (this.fCurrentNSContext != this.fNonXIncludeNSContext) {
            this.fCurrentNSContext = this.fNonXIncludeNSContext;
            setProperty(NAMESPACE_CONTEXT, this.fNonXIncludeNSContext);
        }
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (featureId.equals("http://apache.org/xml/features/internal/parser-settings")) {
            return this.fConfigUpdated;
        }
        if (featureId.equals(XINCLUDE_FEATURE)) {
            return this.fXIncludeEnabled;
        }
        return super.getFeature0(featureId);
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        if (featureId.equals(XINCLUDE_FEATURE)) {
            this.fXIncludeEnabled = state;
            this.fConfigUpdated = true;
            return;
        }
        super.setFeature(featureId, state);
    }
}
