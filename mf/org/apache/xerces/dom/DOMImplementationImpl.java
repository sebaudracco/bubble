package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DocumentType;
import org.slf4j.Marker;

public class DOMImplementationImpl extends CoreDOMImplementationImpl implements DOMImplementation {
    static final DOMImplementationImpl singleton = new DOMImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    public boolean hasFeature(String feature, String version) {
        boolean result = super.hasFeature(feature, version);
        if (result) {
            return result;
        }
        boolean anyVersion = version == null || version.length() == 0;
        if (feature.startsWith(Marker.ANY_NON_NULL_MARKER)) {
            feature = feature.substring(1);
        }
        if (!((feature.equalsIgnoreCase("Events") && (anyVersion || version.equals("2.0"))) || ((feature.equalsIgnoreCase("MutationEvents") && (anyVersion || version.equals("2.0"))) || ((feature.equalsIgnoreCase("Traversal") && (anyVersion || version.equals("2.0"))) || (feature.equalsIgnoreCase("Range") && (anyVersion || version.equals("2.0"))))))) {
            if (!feature.equalsIgnoreCase("MutationEvents")) {
                return false;
            }
            if (!(anyVersion || version.equals("2.0"))) {
                return false;
            }
        }
        return true;
    }

    protected CoreDocumentImpl createDocument(DocumentType doctype) {
        return new DocumentImpl(doctype);
    }
}
