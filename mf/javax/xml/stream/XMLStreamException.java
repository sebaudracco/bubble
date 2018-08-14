package mf.javax.xml.stream;

public class XMLStreamException extends Exception {
    protected Location location;
    protected Throwable nested;

    public XMLStreamException(String msg) {
        super(msg);
    }

    public XMLStreamException(Throwable th) {
        super(th);
        this.nested = th;
    }

    public XMLStreamException(String msg, Throwable th) {
        super(msg, th);
        this.nested = th;
    }

    public XMLStreamException(String msg, Location location, Throwable th) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\n" + "Message: " + msg);
        this.nested = th;
        this.location = location;
    }

    public XMLStreamException(String msg, Location location) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\n" + "Message: " + msg);
        this.location = location;
    }

    public Throwable getNestedException() {
        return this.nested;
    }

    public Location getLocation() {
        return this.location;
    }
}
