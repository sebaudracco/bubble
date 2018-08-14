package mf.org.apache.xerces.impl.dtd;

public class XMLEntityDecl {
    public String baseSystemId;
    public boolean inExternal;
    public boolean isPE;
    public String name;
    public String notation;
    public String publicId;
    public String systemId;
    public String value;

    public void setValues(String name, String publicId, String systemId, String baseSystemId, String notation, boolean isPE, boolean inExternal) {
        setValues(name, publicId, systemId, baseSystemId, notation, null, isPE, inExternal);
    }

    public void setValues(String name, String publicId, String systemId, String baseSystemId, String notation, String value, boolean isPE, boolean inExternal) {
        this.name = name;
        this.publicId = publicId;
        this.systemId = systemId;
        this.baseSystemId = baseSystemId;
        this.notation = notation;
        this.value = value;
        this.isPE = isPE;
        this.inExternal = inExternal;
    }

    public void clear() {
        this.name = null;
        this.publicId = null;
        this.systemId = null;
        this.baseSystemId = null;
        this.notation = null;
        this.value = null;
        this.isPE = false;
        this.inExternal = false;
    }
}
