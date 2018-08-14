package mf.javax.xml.parsers;

public class FactoryConfigurationError extends Error {
    private static final long serialVersionUID = -827108682472263355L;
    private Exception exception;

    public FactoryConfigurationError() {
        this.exception = null;
    }

    public FactoryConfigurationError(String msg) {
        super(msg);
        this.exception = null;
    }

    public FactoryConfigurationError(Exception e) {
        super(e.toString());
        this.exception = e;
    }

    public FactoryConfigurationError(Exception e, String msg) {
        super(msg);
        this.exception = e;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message != null || this.exception == null) {
            return message;
        }
        return this.exception.getMessage();
    }

    public Exception getException() {
        return this.exception;
    }

    public Throwable getCause() {
        return this.exception;
    }
}
