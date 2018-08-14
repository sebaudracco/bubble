package mf.org.apache.xerces.jaxp.validation;

import mf.javax.xml.transform.dom.DOMResult;
import mf.org.apache.xerces.dom.AttrImpl;
import mf.org.apache.xerces.dom.CoreDocumentImpl;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.apache.xerces.dom.ElementNSImpl;
import mf.org.apache.xerces.dom.PSVIAttrNSImpl;
import mf.org.apache.xerces.dom.PSVIDocumentImpl;
import mf.org.apache.xerces.dom.PSVIElementNSImpl;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xs.AttributePSVI;
import mf.org.apache.xerces.xs.ElementPSVI;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.CDATASection;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.Text;

final class DOMResultAugmentor implements DOMDocumentHandler {
    private final QName fAttributeQName = new QName();
    private final DOMValidatorHelper fDOMValidatorHelper;
    private Document fDocument;
    private CoreDocumentImpl fDocumentImpl;
    private boolean fIgnoreChars;
    private boolean fStorePSVI;

    public DOMResultAugmentor(DOMValidatorHelper helper) {
        this.fDOMValidatorHelper = helper;
    }

    public void setDOMResult(DOMResult result) {
        CoreDocumentImpl coreDocumentImpl = null;
        this.fIgnoreChars = false;
        if (result != null) {
            Node target = result.getNode();
            this.fDocument = target.getNodeType() == (short) 9 ? (Document) target : target.getOwnerDocument();
            if (this.fDocument instanceof CoreDocumentImpl) {
                coreDocumentImpl = (CoreDocumentImpl) this.fDocument;
            }
            this.fDocumentImpl = coreDocumentImpl;
            this.fStorePSVI = this.fDocument instanceof PSVIDocumentImpl;
            return;
        }
        this.fDocument = null;
        this.fDocumentImpl = null;
        this.fStorePSVI = false;
    }

    public void doctypeDecl(DocumentType node) throws XNIException {
    }

    public void characters(Text node) throws XNIException {
    }

    public void cdata(CDATASection node) throws XNIException {
    }

    public void comment(Comment node) throws XNIException {
    }

    public void processingInstruction(ProcessingInstruction node) throws XNIException {
    }

    public void setIgnoringCharacters(boolean ignore) {
        this.fIgnoreChars = ignore;
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        int i;
        AttrImpl attr;
        AttributePSVI attrPSVI;
        Element currentElement = (Element) this.fDOMValidatorHelper.getCurrentElement();
        NamedNodeMap attrMap = currentElement.getAttributes();
        int oldLength = attrMap.getLength();
        if (this.fDocumentImpl != null) {
            for (i = 0; i < oldLength; i++) {
                attr = (AttrImpl) attrMap.item(i);
                attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
                if (attrPSVI != null && processAttributePSVI(attr, attrPSVI)) {
                    ((ElementImpl) currentElement).setIdAttributeNode(attr, true);
                }
            }
        }
        int newLength = attributes.getLength();
        if (newLength <= oldLength) {
            return;
        }
        if (this.fDocumentImpl == null) {
            for (i = oldLength; i < newLength; i++) {
                attributes.getName(i, this.fAttributeQName);
                currentElement.setAttributeNS(this.fAttributeQName.uri, this.fAttributeQName.rawname, attributes.getValue(i));
            }
            return;
        }
        for (i = oldLength; i < newLength; i++) {
            attributes.getName(i, this.fAttributeQName);
            attr = (AttrImpl) this.fDocumentImpl.createAttributeNS(this.fAttributeQName.uri, this.fAttributeQName.rawname, this.fAttributeQName.localpart);
            attr.setValue(attributes.getValue(i));
            currentElement.setAttributeNodeNS(attr);
            attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
            if (attrPSVI != null && processAttributePSVI(attr, attrPSVI)) {
                ((ElementImpl) currentElement).setIdAttributeNode(attr, true);
            }
            attr.setSpecified(false);
        }
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        startElement(element, attributes, augs);
        endElement(element, augs);
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        if (!this.fIgnoreChars) {
            ((Element) this.fDOMValidatorHelper.getCurrentElement()).appendChild(this.fDocument.createTextNode(text.toString()));
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        characters(text, augs);
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        Node currentElement = this.fDOMValidatorHelper.getCurrentElement();
        if (augs != null && this.fDocumentImpl != null) {
            ElementPSVI elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null) {
                if (this.fStorePSVI) {
                    ((PSVIElementNSImpl) currentElement).setPSVI(elementPSVI);
                }
                XSTypeDefinition type = elementPSVI.getMemberTypeDefinition();
                if (type == null) {
                    type = elementPSVI.getTypeDefinition();
                }
                ((ElementNSImpl) currentElement).setType(type);
            }
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {
    }

    public void endCDATA(Augmentations augs) throws XNIException {
    }

    public void endDocument(Augmentations augs) throws XNIException {
    }

    public void setDocumentSource(XMLDocumentSource source) {
    }

    public XMLDocumentSource getDocumentSource() {
        return null;
    }

    private boolean processAttributePSVI(AttrImpl attr, AttributePSVI attrPSVI) {
        if (this.fStorePSVI) {
            ((PSVIAttrNSImpl) attr).setPSVI(attrPSVI);
        }
        XSTypeDefinition type = attrPSVI.getMemberTypeDefinition();
        if (type == null) {
            XSSimpleTypeDefinition type2 = attrPSVI.getTypeDefinition();
            if (type2 == null) {
                return false;
            }
            attr.setType(type2);
            return ((XSSimpleType) type2).isIDType();
        }
        attr.setType(type);
        return ((XSSimpleType) type).isIDType();
    }
}
