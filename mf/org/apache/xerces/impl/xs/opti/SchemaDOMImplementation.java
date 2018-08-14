package mf.org.apache.xerces.impl.xs.opti;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;

final class SchemaDOMImplementation implements DOMImplementation {
    private static final SchemaDOMImplementation singleton = new SchemaDOMImplementation();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    private SchemaDOMImplementation() {
    }

    public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Object getFeature(String feature, String version) {
        if (singleton.hasFeature(feature, version)) {
            return singleton;
        }
        return null;
    }

    public boolean hasFeature(String feature, String version) {
        boolean anyVersion;
        if (version == null || version.length() == 0) {
            anyVersion = true;
        } else {
            anyVersion = false;
        }
        if (!feature.equalsIgnoreCase("Core") && !feature.equalsIgnoreCase("XML")) {
            return false;
        }
        if (anyVersion || version.equals("1.0") || version.equals("2.0") || version.equals("3.0")) {
            return true;
        }
        return false;
    }
}
