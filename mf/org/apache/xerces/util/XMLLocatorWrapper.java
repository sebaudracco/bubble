package mf.org.apache.xerces.util;

import mf.org.apache.xerces.xni.XMLLocator;

public final class XMLLocatorWrapper implements XMLLocator {
    private XMLLocator fLocator = null;

    public void setLocator(XMLLocator locator) {
        this.fLocator = locator;
    }

    public XMLLocator getLocator() {
        return this.fLocator;
    }

    public String getPublicId() {
        if (this.fLocator != null) {
            return this.fLocator.getPublicId();
        }
        return null;
    }

    public String getLiteralSystemId() {
        if (this.fLocator != null) {
            return this.fLocator.getLiteralSystemId();
        }
        return null;
    }

    public String getBaseSystemId() {
        if (this.fLocator != null) {
            return this.fLocator.getBaseSystemId();
        }
        return null;
    }

    public String getExpandedSystemId() {
        if (this.fLocator != null) {
            return this.fLocator.getExpandedSystemId();
        }
        return null;
    }

    public int getLineNumber() {
        if (this.fLocator != null) {
            return this.fLocator.getLineNumber();
        }
        return -1;
    }

    public int getColumnNumber() {
        if (this.fLocator != null) {
            return this.fLocator.getColumnNumber();
        }
        return -1;
    }

    public int getCharacterOffset() {
        if (this.fLocator != null) {
            return this.fLocator.getCharacterOffset();
        }
        return -1;
    }

    public String getEncoding() {
        if (this.fLocator != null) {
            return this.fLocator.getEncoding();
        }
        return null;
    }

    public String getXMLVersion() {
        if (this.fLocator != null) {
            return this.fLocator.getXMLVersion();
        }
        return null;
    }
}
