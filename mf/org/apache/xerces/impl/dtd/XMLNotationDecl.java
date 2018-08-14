package mf.org.apache.xerces.impl.dtd;

public class XMLNotationDecl {
    public String baseSystemId;
    public String name;
    public String publicId;
    public String systemId;

    public void setValues(String name, String publicId, String systemId, String baseSystemId) {
        this.name = name;
        this.publicId = publicId;
        this.systemId = systemId;
        this.baseSystemId = baseSystemId;
    }

    public void clear() {
        this.name = null;
        this.publicId = null;
        this.systemId = null;
        this.baseSystemId = null;
    }
}
