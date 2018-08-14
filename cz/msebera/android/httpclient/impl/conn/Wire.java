package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Immutable
public class Wire {
    private final String id;
    public HttpClientAndroidLog log;

    public Wire(HttpClientAndroidLog log, String id) {
        this.log = log;
        this.id = id;
    }

    public Wire(HttpClientAndroidLog log) {
        this(log, "");
    }

    private void wire(String header, InputStream instream) throws IOException {
        StringBuilder buffer = new StringBuilder();
        while (true) {
            int ch = instream.read();
            if (ch == -1) {
                break;
            } else if (ch == 13) {
                buffer.append("[\\r]");
            } else if (ch == 10) {
                buffer.append("[\\n]\"");
                buffer.insert(0, "\"");
                buffer.insert(0, header);
                this.log.debug(this.id + " " + buffer.toString());
                buffer.setLength(0);
            } else if (ch < 32 || ch > 127) {
                buffer.append("[0x");
                buffer.append(Integer.toHexString(ch));
                buffer.append("]");
            } else {
                buffer.append((char) ch);
            }
        }
        if (buffer.length() > 0) {
            buffer.append('\"');
            buffer.insert(0, '\"');
            buffer.insert(0, header);
            this.log.debug(this.id + " " + buffer.toString());
        }
    }

    public boolean enabled() {
        return this.log.isDebugEnabled();
    }

    public void output(InputStream outstream) throws IOException {
        Args.notNull(outstream, "Output");
        wire(">> ", outstream);
    }

    public void input(InputStream instream) throws IOException {
        Args.notNull(instream, "Input");
        wire("<< ", instream);
    }

    public void output(byte[] b, int off, int len) throws IOException {
        Args.notNull(b, "Output");
        wire(">> ", new ByteArrayInputStream(b, off, len));
    }

    public void input(byte[] b, int off, int len) throws IOException {
        Args.notNull(b, "Input");
        wire("<< ", new ByteArrayInputStream(b, off, len));
    }

    public void output(byte[] b) throws IOException {
        Args.notNull(b, "Output");
        wire(">> ", new ByteArrayInputStream(b));
    }

    public void input(byte[] b) throws IOException {
        Args.notNull(b, "Input");
        wire("<< ", new ByteArrayInputStream(b));
    }

    public void output(int b) throws IOException {
        output(new byte[]{(byte) b});
    }

    public void input(int b) throws IOException {
        input(new byte[]{(byte) b});
    }

    public void output(String s) throws IOException {
        Args.notNull(s, "Output");
        output(s.getBytes());
    }

    public void input(String s) throws IOException {
        Args.notNull(s, "Input");
        input(s.getBytes());
    }
}
