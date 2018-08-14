package mf.org.apache.xerces.dom;

import java.lang.ref.SoftReference;
import mf.org.apache.xerces.impl.RevalidationHandler;
import mf.org.apache.xerces.impl.dtd.XMLDTDLoader;
import mf.org.apache.xerces.parsers.DOMParserImpl;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xml.serialize.DOMSerializerImpl;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.ls.DOMImplementationLS;
import mf.org.w3c.dom.ls.LSInput;
import mf.org.w3c.dom.ls.LSOutput;
import mf.org.w3c.dom.ls.LSParser;
import mf.org.w3c.dom.ls.LSSerializer;
import org.slf4j.Marker;

public class CoreDOMImplementationImpl implements DOMImplementation, DOMImplementationLS {
    private static final int SIZE = 2;
    static final CoreDOMImplementationImpl singleton = new CoreDOMImplementationImpl();
    private int docAndDoctypeCounter = 0;
    private int freeSchemaValidatorIndex = -1;
    private int freeXML10DTDLoaderIndex = -1;
    private int freeXML10DTDValidatorIndex = -1;
    private int freeXML11DTDLoaderIndex = -1;
    private int freeXML11DTDValidatorIndex = -1;
    private SoftReference[] schemaValidators = new SoftReference[2];
    private int schemaValidatorsCurrentSize = 2;
    private int xml10DTDLoaderCurrentSize = 2;
    private SoftReference[] xml10DTDLoaders = new SoftReference[2];
    private SoftReference[] xml10DTDValidators = new SoftReference[2];
    private int xml10DTDValidatorsCurrentSize = 2;
    private int xml11DTDLoaderCurrentSize = 2;
    private SoftReference[] xml11DTDLoaders = new SoftReference[2];
    private SoftReference[] xml11DTDValidators = new SoftReference[2];
    private int xml11DTDValidatorsCurrentSize = 2;

    static final class RevalidationHandlerHolder {
        RevalidationHandler handler;

        RevalidationHandlerHolder(RevalidationHandler handler) {
            this.handler = handler;
        }
    }

    static final class XMLDTDLoaderHolder {
        XMLDTDLoader loader;

        XMLDTDLoaderHolder(XMLDTDLoader loader) {
            this.loader = loader;
        }
    }

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    public boolean hasFeature(String feature, String version) {
        boolean anyVersion;
        if (version == null || version.length() == 0) {
            anyVersion = true;
        } else {
            anyVersion = false;
        }
        if (feature.equalsIgnoreCase("+XPath") && (anyVersion || version.equals("3.0"))) {
            try {
                Class[] interfaces = ObjectFactory.findProviderClass("org.apache.xpath.domapi.XPathEvaluatorImpl", ObjectFactory.findClassLoader(), true).getInterfaces();
                int i = 0;
                while (i < interfaces.length && !interfaces[i].getName().equals("org.w3c.dom.xpath.XPathEvaluator")) {
                    i++;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        if (feature.startsWith(Marker.ANY_NON_NULL_MARKER)) {
            feature = feature.substring(1);
        }
        if (feature.equalsIgnoreCase("Core") && (anyVersion || version.equals("1.0") || version.equals("2.0") || version.equals("3.0"))) {
            return true;
        }
        if (feature.equalsIgnoreCase("XML") && (anyVersion || version.equals("1.0") || version.equals("2.0") || version.equals("3.0"))) {
            return true;
        }
        if (feature.equalsIgnoreCase("XMLVersion") && (anyVersion || version.equals("1.0") || version.equals("1.1"))) {
            return true;
        }
        if (feature.equalsIgnoreCase("LS") && (anyVersion || version.equals("3.0"))) {
            return true;
        }
        if (feature.equalsIgnoreCase("ElementTraversal") && (anyVersion || version.equals("1.0"))) {
            return true;
        }
        return false;
    }

    public DocumentType createDocumentType(String qualifiedName, String publicID, String systemID) {
        checkQName(qualifiedName);
        return new DocumentTypeImpl(null, qualifiedName, publicID, systemID);
    }

    final void checkQName(String qname) {
        int index = qname.indexOf(58);
        int lastIndex = qname.lastIndexOf(58);
        int length = qname.length();
        if (index == 0 || index == length - 1 || lastIndex != index) {
            throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        }
        int i;
        int start = 0;
        if (index > 0) {
            if (XMLChar.isNCNameStart(qname.charAt(0))) {
                i = 1;
                while (i < index) {
                    if (XMLChar.isNCName(qname.charAt(i))) {
                        i++;
                    } else {
                        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                    }
                }
                start = index + 1;
            } else {
                throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
            }
        }
        if (XMLChar.isNCNameStart(qname.charAt(start))) {
            i = start + 1;
            while (i < length) {
                if (XMLChar.isNCName(qname.charAt(i))) {
                    i++;
                } else {
                    throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                }
            }
            return;
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
        if (doctype == null || doctype.getOwnerDocument() == null) {
            CoreDocumentImpl doc = createDocument(doctype);
            if (!(qualifiedName == null && namespaceURI == null)) {
                doc.appendChild(doc.createElementNS(namespaceURI, qualifiedName));
            }
            return doc;
        }
        throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
    }

    protected CoreDocumentImpl createDocument(DocumentType doctype) {
        return new CoreDocumentImpl(doctype);
    }

    public Object getFeature(String feature, String version) {
        if (!singleton.hasFeature(feature, version)) {
            return null;
        }
        if (!feature.equalsIgnoreCase("+XPath")) {
            return singleton;
        }
        try {
            Class xpathClass = ObjectFactory.findProviderClass("org.apache.xpath.domapi.XPathEvaluatorImpl", ObjectFactory.findClassLoader(), true);
            Class[] interfaces = xpathClass.getInterfaces();
            for (Class name : interfaces) {
                if (name.getName().equals("org.w3c.dom.xpath.XPathEvaluator")) {
                    return xpathClass.newInstance();
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public LSParser createLSParser(short mode, String schemaType) throws DOMException {
        if (mode != (short) 1 || (schemaType != null && !"http://www.w3.org/2001/XMLSchema".equals(schemaType) && !"http://www.w3.org/TR/REC-xml".equals(schemaType))) {
            throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        } else if (schemaType == null || !schemaType.equals("http://www.w3.org/TR/REC-xml")) {
            return new DOMParserImpl("mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration", schemaType);
        } else {
            return new DOMParserImpl("mf.org.apache.xerces.parsers.DTDConfiguration", schemaType);
        }
    }

    public LSSerializer createLSSerializer() {
        try {
            return (LSSerializer) ObjectFactory.findProviderClass("org.apache.xml.serializer.dom3.LSSerializerImpl", ObjectFactory.findClassLoader(), true).newInstance();
        } catch (Exception e) {
            return new DOMSerializerImpl();
        }
    }

    public LSInput createLSInput() {
        return new DOMInputImpl();
    }

    synchronized RevalidationHandler getValidator(String schemaType, String xmlVersion) {
        RevalidationHandler revalidationHandler = null;
        synchronized (this) {
            RevalidationHandlerHolder holder;
            SoftReference[] softReferenceArr;
            int i;
            if (schemaType == "http://www.w3.org/2001/XMLSchema") {
                while (this.freeSchemaValidatorIndex >= 0) {
                    holder = (RevalidationHandlerHolder) this.schemaValidators[this.freeSchemaValidatorIndex].get();
                    if (holder != null && holder.handler != null) {
                        revalidationHandler = holder.handler;
                        holder.handler = null;
                        this.freeSchemaValidatorIndex--;
                        break;
                    }
                    softReferenceArr = this.schemaValidators;
                    i = this.freeSchemaValidatorIndex;
                    this.freeSchemaValidatorIndex = i - 1;
                    softReferenceArr[i] = null;
                }
                revalidationHandler = (RevalidationHandler) ObjectFactory.newInstance("mf.org.apache.xerces.impl.xs.XMLSchemaValidator", ObjectFactory.findClassLoader(), true);
            } else if (schemaType == "http://www.w3.org/TR/REC-xml") {
                if ("1.1".equals(xmlVersion)) {
                    while (this.freeXML11DTDValidatorIndex >= 0) {
                        holder = (RevalidationHandlerHolder) this.xml11DTDValidators[this.freeXML11DTDValidatorIndex].get();
                        if (holder != null && holder.handler != null) {
                            revalidationHandler = holder.handler;
                            holder.handler = null;
                            this.freeXML11DTDValidatorIndex--;
                            break;
                        }
                        softReferenceArr = this.xml11DTDValidators;
                        i = this.freeXML11DTDValidatorIndex;
                        this.freeXML11DTDValidatorIndex = i - 1;
                        softReferenceArr[i] = null;
                    }
                    revalidationHandler = (RevalidationHandler) ObjectFactory.newInstance("mf.org.apache.xerces.impl.dtd.XML11DTDValidator", ObjectFactory.findClassLoader(), true);
                } else {
                    while (this.freeXML10DTDValidatorIndex >= 0) {
                        holder = (RevalidationHandlerHolder) this.xml10DTDValidators[this.freeXML10DTDValidatorIndex].get();
                        if (holder != null && holder.handler != null) {
                            revalidationHandler = holder.handler;
                            holder.handler = null;
                            this.freeXML10DTDValidatorIndex--;
                            break;
                        }
                        softReferenceArr = this.xml10DTDValidators;
                        i = this.freeXML10DTDValidatorIndex;
                        this.freeXML10DTDValidatorIndex = i - 1;
                        softReferenceArr[i] = null;
                    }
                    revalidationHandler = (RevalidationHandler) ObjectFactory.newInstance("mf.org.apache.xerces.impl.dtd.XMLDTDValidator", ObjectFactory.findClassLoader(), true);
                }
            }
        }
        return revalidationHandler;
    }

    synchronized void releaseValidator(String schemaType, String xmlVersion, RevalidationHandler validator) {
        SoftReference[] newarray;
        SoftReference ref;
        RevalidationHandlerHolder holder;
        if (schemaType == "http://www.w3.org/2001/XMLSchema") {
            this.freeSchemaValidatorIndex++;
            if (this.schemaValidators.length == this.freeSchemaValidatorIndex) {
                this.schemaValidatorsCurrentSize += 2;
                newarray = new SoftReference[this.schemaValidatorsCurrentSize];
                System.arraycopy(this.schemaValidators, 0, newarray, 0, this.schemaValidators.length);
                this.schemaValidators = newarray;
            }
            ref = this.schemaValidators[this.freeSchemaValidatorIndex];
            if (ref != null) {
                holder = (RevalidationHandlerHolder) ref.get();
                if (holder != null) {
                    holder.handler = validator;
                }
            }
            this.schemaValidators[this.freeSchemaValidatorIndex] = new SoftReference(new RevalidationHandlerHolder(validator));
        } else if (schemaType == "http://www.w3.org/TR/REC-xml") {
            if ("1.1".equals(xmlVersion)) {
                this.freeXML11DTDValidatorIndex++;
                if (this.xml11DTDValidators.length == this.freeXML11DTDValidatorIndex) {
                    this.xml11DTDValidatorsCurrentSize += 2;
                    newarray = new SoftReference[this.xml11DTDValidatorsCurrentSize];
                    System.arraycopy(this.xml11DTDValidators, 0, newarray, 0, this.xml11DTDValidators.length);
                    this.xml11DTDValidators = newarray;
                }
                ref = this.xml11DTDValidators[this.freeXML11DTDValidatorIndex];
                if (ref != null) {
                    holder = (RevalidationHandlerHolder) ref.get();
                    if (holder != null) {
                        holder.handler = validator;
                    }
                }
                this.xml11DTDValidators[this.freeXML11DTDValidatorIndex] = new SoftReference(new RevalidationHandlerHolder(validator));
            } else {
                this.freeXML10DTDValidatorIndex++;
                if (this.xml10DTDValidators.length == this.freeXML10DTDValidatorIndex) {
                    this.xml10DTDValidatorsCurrentSize += 2;
                    newarray = new SoftReference[this.xml10DTDValidatorsCurrentSize];
                    System.arraycopy(this.xml10DTDValidators, 0, newarray, 0, this.xml10DTDValidators.length);
                    this.xml10DTDValidators = newarray;
                }
                ref = this.xml10DTDValidators[this.freeXML10DTDValidatorIndex];
                if (ref != null) {
                    holder = (RevalidationHandlerHolder) ref.get();
                    if (holder != null) {
                        holder.handler = validator;
                    }
                }
                this.xml10DTDValidators[this.freeXML10DTDValidatorIndex] = new SoftReference(new RevalidationHandlerHolder(validator));
            }
        }
    }

    final synchronized XMLDTDLoader getDTDLoader(String xmlVersion) {
        XMLDTDLoader val;
        XMLDTDLoaderHolder holder;
        SoftReference[] softReferenceArr;
        int i;
        if ("1.1".equals(xmlVersion)) {
            while (this.freeXML11DTDLoaderIndex >= 0) {
                holder = (XMLDTDLoaderHolder) this.xml11DTDLoaders[this.freeXML11DTDLoaderIndex].get();
                if (holder != null && holder.loader != null) {
                    val = holder.loader;
                    holder.loader = null;
                    this.freeXML11DTDLoaderIndex--;
                    break;
                }
                softReferenceArr = this.xml11DTDLoaders;
                i = this.freeXML11DTDLoaderIndex;
                this.freeXML11DTDLoaderIndex = i - 1;
                softReferenceArr[i] = null;
            }
            val = (XMLDTDLoader) ObjectFactory.newInstance("mf.org.apache.xerces.impl.dtd.XML11DTDProcessor", ObjectFactory.findClassLoader(), true);
        } else {
            while (this.freeXML10DTDLoaderIndex >= 0) {
                holder = (XMLDTDLoaderHolder) this.xml10DTDLoaders[this.freeXML10DTDLoaderIndex].get();
                if (holder != null && holder.loader != null) {
                    val = holder.loader;
                    holder.loader = null;
                    this.freeXML10DTDLoaderIndex--;
                    break;
                }
                softReferenceArr = this.xml10DTDLoaders;
                i = this.freeXML10DTDLoaderIndex;
                this.freeXML10DTDLoaderIndex = i - 1;
                softReferenceArr[i] = null;
            }
            val = new XMLDTDLoader();
        }
        return val;
    }

    final synchronized void releaseDTDLoader(String xmlVersion, XMLDTDLoader loader) {
        SoftReference[] newarray;
        SoftReference ref;
        XMLDTDLoaderHolder holder;
        if ("1.1".equals(xmlVersion)) {
            this.freeXML11DTDLoaderIndex++;
            if (this.xml11DTDLoaders.length == this.freeXML11DTDLoaderIndex) {
                this.xml11DTDLoaderCurrentSize += 2;
                newarray = new SoftReference[this.xml11DTDLoaderCurrentSize];
                System.arraycopy(this.xml11DTDLoaders, 0, newarray, 0, this.xml11DTDLoaders.length);
                this.xml11DTDLoaders = newarray;
            }
            ref = this.xml11DTDLoaders[this.freeXML11DTDLoaderIndex];
            if (ref != null) {
                holder = (XMLDTDLoaderHolder) ref.get();
                if (holder != null) {
                    holder.loader = loader;
                }
            }
            this.xml11DTDLoaders[this.freeXML11DTDLoaderIndex] = new SoftReference(new XMLDTDLoaderHolder(loader));
        } else {
            this.freeXML10DTDLoaderIndex++;
            if (this.xml10DTDLoaders.length == this.freeXML10DTDLoaderIndex) {
                this.xml10DTDLoaderCurrentSize += 2;
                newarray = new SoftReference[this.xml10DTDLoaderCurrentSize];
                System.arraycopy(this.xml10DTDLoaders, 0, newarray, 0, this.xml10DTDLoaders.length);
                this.xml10DTDLoaders = newarray;
            }
            ref = this.xml10DTDLoaders[this.freeXML10DTDLoaderIndex];
            if (ref != null) {
                holder = (XMLDTDLoaderHolder) ref.get();
                if (holder != null) {
                    holder.loader = loader;
                }
            }
            this.xml10DTDLoaders[this.freeXML10DTDLoaderIndex] = new SoftReference(new XMLDTDLoaderHolder(loader));
        }
    }

    protected synchronized int assignDocumentNumber() {
        int i;
        i = this.docAndDoctypeCounter + 1;
        this.docAndDoctypeCounter = i;
        return i;
    }

    protected synchronized int assignDocTypeNumber() {
        int i;
        i = this.docAndDoctypeCounter + 1;
        this.docAndDoctypeCounter = i;
        return i;
    }

    public LSOutput createLSOutput() {
        return new DOMOutputImpl();
    }
}
