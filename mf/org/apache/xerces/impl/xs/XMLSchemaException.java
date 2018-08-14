package mf.org.apache.xerces.impl.xs;

public class XMLSchemaException extends Exception {
    static final long serialVersionUID = -9096984648537046218L;
    Object[] args;
    String key;

    public XMLSchemaException(String key, Object[] args) {
        this.key = key;
        this.args = args;
    }

    public String getKey() {
        return this.key;
    }

    public Object[] getArgs() {
        return this.args;
    }
}
