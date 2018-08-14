package mf.org.apache.xerces.xni;

public class XNIException extends RuntimeException {
    static final long serialVersionUID = 9019819772686063775L;
    private Exception fException = this;

    public XNIException(String message) {
        super(message);
    }

    public XNIException(Exception exception) {
        super(exception.getMessage());
        this.fException = exception;
    }

    public XNIException(String message, Exception exception) {
        super(message);
        this.fException = exception;
    }

    public Exception getException() {
        return this.fException != this ? this.fException : null;
    }

    public synchronized Throwable initCause(Throwable throwable) {
        if (this.fException != this) {
            throw new IllegalStateException();
        } else if (throwable == this) {
            throw new IllegalArgumentException();
        } else {
            this.fException = (Exception) throwable;
        }
        return this;
    }

    public Throwable getCause() {
        return getException();
    }
}
