package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xinclude.XIncludeHandler;
import mf.org.apache.xerces.xinclude.XIncludeNamespaceSupport;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xpointer.XPointerHandler;

public class XPointerParserConfiguration extends XML11Configuration {
    protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
    protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
    protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
    protected static final String XINCLUDE_HANDLER = "http://apache.org/xml/properties/internal/xinclude-handler";
    protected static final String XPOINTER_HANDLER = "http://apache.org/xml/properties/internal/xpointer-handler";
    private XIncludeHandler fXIncludeHandler;
    private XPointerHandler fXPointerHandler;

    public XPointerParserConfiguration() {
        this(null, null, null);
    }

    public XPointerParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public XPointerParserConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }

    public XPointerParserConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
        this.fXIncludeHandler = new XIncludeHandler();
        addCommonComponent(this.fXIncludeHandler);
        this.fXPointerHandler = new XPointerHandler();
        addCommonComponent(this.fXPointerHandler);
        addRecognizedFeatures(new String[]{ALLOW_UE_AND_NOTATION_EVENTS, XINCLUDE_FIXUP_BASE_URIS, XINCLUDE_FIXUP_LANGUAGE});
        addRecognizedProperties(new String[]{XINCLUDE_HANDLER, XPOINTER_HANDLER, NAMESPACE_CONTEXT});
        setFeature(ALLOW_UE_AND_NOTATION_EVENTS, true);
        setFeature(XINCLUDE_FIXUP_BASE_URIS, true);
        setFeature(XINCLUDE_FIXUP_LANGUAGE, true);
        setProperty(XINCLUDE_HANDLER, this.fXIncludeHandler);
        setProperty(XPOINTER_HANDLER, this.fXPointerHandler);
        setProperty(NAMESPACE_CONTEXT, new XIncludeNamespaceSupport());
    }

    protected void configurePipeline() {
        XMLDocumentSource prev;
        super.configurePipeline();
        this.fDTDScanner.setDTDHandler(this.fDTDProcessor);
        this.fDTDProcessor.setDTDSource(this.fDTDScanner);
        this.fDTDProcessor.setDTDHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDTDSource(this.fDTDProcessor);
        this.fXIncludeHandler.setDTDHandler(this.fXPointerHandler);
        this.fXPointerHandler.setDTDSource(this.fXIncludeHandler);
        this.fXPointerHandler.setDTDHandler(this.fDTDHandler);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.setDTDSource(this.fXPointerHandler);
        }
        if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
            prev = this.fSchemaValidator.getDocumentSource();
        } else {
            prev = this.fLastComponent;
            this.fLastComponent = this.fXPointerHandler;
        }
        XMLDocumentHandler next = prev.getDocumentHandler();
        prev.setDocumentHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDocumentSource(prev);
        if (next != null) {
            this.fXIncludeHandler.setDocumentHandler(next);
            next.setDocumentSource(this.fXIncludeHandler);
        }
        this.fXIncludeHandler.setDocumentHandler(this.fXPointerHandler);
        this.fXPointerHandler.setDocumentSource(this.fXIncludeHandler);
    }

    protected void configureXML11Pipeline() {
        XMLDocumentSource prev;
        super.configureXML11Pipeline();
        this.fXML11DTDScanner.setDTDHandler(this.fXML11DTDProcessor);
        this.fXML11DTDProcessor.setDTDSource(this.fXML11DTDScanner);
        this.fDTDProcessor.setDTDHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDTDSource(this.fXML11DTDProcessor);
        this.fXIncludeHandler.setDTDHandler(this.fXPointerHandler);
        this.fXPointerHandler.setDTDSource(this.fXIncludeHandler);
        this.fXPointerHandler.setDTDHandler(this.fDTDHandler);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.setDTDSource(this.fXPointerHandler);
        }
        if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
            prev = this.fSchemaValidator.getDocumentSource();
        } else {
            prev = this.fLastComponent;
            this.fLastComponent = this.fXPointerHandler;
        }
        XMLDocumentHandler next = prev.getDocumentHandler();
        prev.setDocumentHandler(this.fXIncludeHandler);
        this.fXIncludeHandler.setDocumentSource(prev);
        if (next != null) {
            this.fXIncludeHandler.setDocumentHandler(next);
            next.setDocumentSource(this.fXIncludeHandler);
        }
        this.fXIncludeHandler.setDocumentHandler(this.fXPointerHandler);
        this.fXPointerHandler.setDocumentSource(this.fXIncludeHandler);
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        super.setProperty(propertyId, value);
    }
}
