package mf.org.apache.xerces.impl;

public class XML11NamespaceBinder extends XMLNamespaceBinder {
    protected boolean prefixBoundToNullURI(String uri, String localpart) {
        return false;
    }
}
