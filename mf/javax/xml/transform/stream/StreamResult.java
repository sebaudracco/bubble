package mf.javax.xml.transform.stream;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import mf.javax.xml.transform.Result;

public class StreamResult implements Result {
    public static final String FEATURE = "http://javax.xml.transform.stream.StreamResult/feature";
    private OutputStream outputStream;
    private String systemId;
    private Writer writer;

    public StreamResult(OutputStream outputStream) {
        setOutputStream(outputStream);
    }

    public StreamResult(Writer writer) {
        setWriter(writer);
    }

    public StreamResult(String systemId) {
        this.systemId = systemId;
    }

    public StreamResult(File f) {
        setSystemId(f.toURI().toASCIIString());
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Writer getWriter() {
        return this.writer;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public void setSystemId(File f) {
        this.systemId = f.toURI().toASCIIString();
    }

    public String getSystemId() {
        return this.systemId;
    }
}
