package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.dom.PSVIDOMImplementationImpl;
import mf.org.apache.xerces.impl.xs.util.LSInputListImpl;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.xs.LSInputList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSException;
import mf.org.apache.xerces.xs.XSImplementation;
import mf.org.apache.xerces.xs.XSLoader;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.ls.LSInput;

public class XSImplementationImpl extends PSVIDOMImplementationImpl implements XSImplementation {
    static final XSImplementationImpl singleton = new XSImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    public boolean hasFeature(String feature, String version) {
        return (feature.equalsIgnoreCase("XS-Loader") && (version == null || version.equals("1.0"))) || super.hasFeature(feature, version);
    }

    public XSLoader createXSLoader(StringList versions) throws XSException {
        XSLoader loader = new XSLoaderImpl();
        if (versions != null) {
            int i = 0;
            while (i < versions.getLength()) {
                if (versions.item(i).equals("1.0")) {
                    i++;
                } else {
                    throw new XSException((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{versions.item(i)}));
                }
            }
        }
        return loader;
    }

    public StringList createStringList(String[] values) {
        int length = values != null ? values.length : 0;
        return length != 0 ? new StringListImpl((String[]) values.clone(), length) : StringListImpl.EMPTY_LIST;
    }

    public LSInputList createLSInputList(LSInput[] values) {
        int length = values != null ? values.length : 0;
        return length != 0 ? new LSInputListImpl((LSInput[]) values.clone(), length) : LSInputListImpl.EMPTY_LIST;
    }

    public StringList getRecognizedVersions() {
        return new StringListImpl(new String[]{"1.0"}, 1);
    }
}
