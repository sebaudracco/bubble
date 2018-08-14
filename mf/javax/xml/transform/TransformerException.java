package mf.javax.xml.transform;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransformerException extends Exception {
    Throwable containedException;
    SourceLocator locator;

    public SourceLocator getLocator() {
        return this.locator;
    }

    public void setLocator(SourceLocator location) {
        this.locator = location;
    }

    public Throwable getException() {
        return this.containedException;
    }

    public Throwable getCause() {
        if (this.containedException == this) {
            return null;
        }
        return this.containedException;
    }

    public synchronized Throwable initCause(Throwable cause) {
        if (this.containedException != null) {
            throw new IllegalStateException("Can't overwrite cause");
        } else if (cause == this) {
            throw new IllegalArgumentException("Self-causation not permitted");
        } else {
            this.containedException = cause;
        }
        return this;
    }

    public TransformerException(String message) {
        super(message);
        this.containedException = null;
        this.locator = null;
    }

    public TransformerException(Throwable e) {
        super(e.toString());
        this.containedException = e;
        this.locator = null;
    }

    public TransformerException(String message, Throwable e) {
        if (message == null || message.length() == 0) {
            message = e.toString();
        }
        super(message);
        this.containedException = e;
        this.locator = null;
    }

    public TransformerException(String message, SourceLocator locator) {
        super(message);
        this.containedException = null;
        this.locator = locator;
    }

    public TransformerException(String message, SourceLocator locator, Throwable e) {
        super(message);
        this.containedException = e;
        this.locator = locator;
    }

    public String getMessageAndLocation() {
        StringBuffer sbuffer = new StringBuffer();
        String message = super.getMessage();
        if (message != null) {
            sbuffer.append(message);
        }
        if (this.locator != null) {
            String systemID = this.locator.getSystemId();
            int line = this.locator.getLineNumber();
            int column = this.locator.getColumnNumber();
            if (systemID != null) {
                sbuffer.append("; SystemID: ");
                sbuffer.append(systemID);
            }
            if (line != 0) {
                sbuffer.append("; Line#: ");
                sbuffer.append(line);
            }
            if (column != 0) {
                sbuffer.append("; Column#: ");
                sbuffer.append(column);
            }
        }
        return sbuffer.toString();
    }

    public String getLocationAsString() {
        if (this.locator == null) {
            return null;
        }
        StringBuffer sbuffer = new StringBuffer();
        String systemID = this.locator.getSystemId();
        int line = this.locator.getLineNumber();
        int column = this.locator.getColumnNumber();
        if (systemID != null) {
            sbuffer.append("; SystemID: ");
            sbuffer.append(systemID);
        }
        if (line != 0) {
            sbuffer.append("; Line#: ");
            sbuffer.append(line);
        }
        if (column != 0) {
            sbuffer.append("; Column#: ");
            sbuffer.append(column);
        }
        return sbuffer.toString();
    }

    public void printStackTrace() {
        printStackTrace(new PrintWriter(System.err, true));
    }

    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(s));
    }

    public void printStackTrace(PrintWriter s) {
        String locInfo;
        if (s == null) {
            s = new PrintWriter(System.err, true);
        }
        try {
            locInfo = getLocationAsString();
            if (locInfo != null) {
                s.println(locInfo);
            }
            super.printStackTrace(s);
        } catch (Throwable th) {
        }
        Throwable exception = getException();
        for (int i = 0; i < 10 && exception != null; i++) {
            s.println("---------");
            try {
                if (exception instanceof TransformerException) {
                    locInfo = ((TransformerException) exception).getLocationAsString();
                    if (locInfo != null) {
                        s.println(locInfo);
                    }
                }
                exception.printStackTrace(s);
            } catch (Throwable th2) {
                s.println("Could not print stack trace...");
            }
            try {
                Method meth = exception.getClass().getMethod("getException", null);
                if (meth != null) {
                    Throwable prev = exception;
                    exception = (Throwable) meth.invoke(exception, null);
                    if (prev == exception) {
                        break;
                    }
                } else {
                    exception = null;
                }
            } catch (InvocationTargetException e) {
                exception = null;
            } catch (IllegalAccessException e2) {
                exception = null;
            } catch (NoSuchMethodException e3) {
                exception = null;
            }
        }
        s.flush();
    }
}
