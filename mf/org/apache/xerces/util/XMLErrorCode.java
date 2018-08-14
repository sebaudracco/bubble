package mf.org.apache.xerces.util;

final class XMLErrorCode {
    private String fDomain;
    private String fKey;

    public XMLErrorCode(String domain, String key) {
        this.fDomain = domain;
        this.fKey = key;
    }

    public void setValues(String domain, String key) {
        this.fDomain = domain;
        this.fKey = key;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XMLErrorCode)) {
            return false;
        }
        XMLErrorCode err = (XMLErrorCode) obj;
        if (this.fDomain.equals(err.fDomain) && this.fKey.equals(err.fKey)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.fDomain.hashCode() + this.fKey.hashCode();
    }
}
