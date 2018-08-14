package mf.javax.xml.stream;

public class FactoryConfigurationError extends Error {
    private static final long serialVersionUID = -2994412584589975744L;
    Exception nested;

    public FactoryConfigurationError(Exception e) {
        this.nested = e;
    }

    public FactoryConfigurationError(Exception e, String msg) {
        super(msg);
        this.nested = e;
    }

    public FactoryConfigurationError(String msg, Exception e) {
        super(msg);
        this.nested = e;
    }

    public FactoryConfigurationError(String msg) {
        super(msg);
    }

    public Exception getException() {
        return this.nested;
    }

    public Throwable getCause() {
        return this.nested;
    }

    public String getMessage() {
        String msg = super.getMessage();
        if (msg != null) {
            return msg;
        }
        if (this.nested != null) {
            msg = this.nested.getMessage();
            if (msg == null) {
                msg = this.nested.getClass().toString();
            }
        }
        return msg;
    }
}
