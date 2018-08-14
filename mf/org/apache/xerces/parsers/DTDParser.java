package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.impl.dtd.DTDGrammar;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLDTDScanner;

public abstract class DTDParser extends XMLGrammarParser implements XMLDTDHandler, XMLDTDContentModelHandler {
    protected XMLDTDScanner fDTDScanner;

    public DTDParser(SymbolTable symbolTable) {
        super(symbolTable);
    }

    public DTDGrammar getDTDGrammar() {
        return null;
    }

    public void startEntity(String name, String publicId, String systemId, String encoding) throws XNIException {
    }

    public void textDecl(String version, String encoding) throws XNIException {
    }

    public void startDTD(XMLLocator locator, Augmentations augmentations) throws XNIException {
    }

    public void comment(XMLString text, Augmentations augmentations) throws XNIException {
    }

    public void processingInstruction(String target, XMLString data, Augmentations augmentations) throws XNIException {
    }

    public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
    }

    public void endExternalSubset(Augmentations augmentations) throws XNIException {
    }

    public void elementDecl(String name, String contentModel, Augmentations augmentations) throws XNIException {
    }

    public void startAttlist(String elementName, Augmentations augmentations) throws XNIException {
    }

    public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augmentations) throws XNIException {
    }

    public void endAttlist(Augmentations augmentations) throws XNIException {
    }

    public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augmentations) throws XNIException {
    }

    public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
    }

    public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augmentations) throws XNIException {
    }

    public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
    }

    public void startConditional(short type, Augmentations augmentations) throws XNIException {
    }

    public void endConditional(Augmentations augmentations) throws XNIException {
    }

    public void endDTD(Augmentations augmentations) throws XNIException {
    }

    public void endEntity(String name, Augmentations augmentations) throws XNIException {
    }

    public void startContentModel(String elementName, short type) throws XNIException {
    }

    public void mixedElement(String elementName) throws XNIException {
    }

    public void childrenStartGroup() throws XNIException {
    }

    public void childrenElement(String elementName) throws XNIException {
    }

    public void childrenSeparator(short separator) throws XNIException {
    }

    public void childrenOccurrence(short occurrence) throws XNIException {
    }

    public void childrenEndGroup() throws XNIException {
    }

    public void endContentModel() throws XNIException {
    }
}
