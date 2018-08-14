package mf.org.apache.xerces.dom;

import java.util.ArrayList;
import mf.org.apache.xerces.impl.xs.XSImplementationImpl;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DOMImplementationList;

public class DOMXSImplementationSourceImpl extends DOMImplementationSourceImpl {
    public DOMImplementation getDOMImplementation(String features) {
        DOMImplementation impl = super.getDOMImplementation(features);
        if (impl != null) {
            return impl;
        }
        impl = PSVIDOMImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            return impl;
        }
        impl = XSImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            return impl;
        }
        return null;
    }

    public DOMImplementationList getDOMImplementationList(String features) {
        ArrayList implementations = new ArrayList();
        DOMImplementationList list = super.getDOMImplementationList(features);
        for (int i = 0; i < list.getLength(); i++) {
            implementations.add(list.item(i));
        }
        DOMImplementation impl = PSVIDOMImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            implementations.add(impl);
        }
        impl = XSImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            implementations.add(impl);
        }
        return new DOMImplementationListImpl(implementations);
    }
}
