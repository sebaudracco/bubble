package mf.org.apache.xml.serialize;

import java.io.OutputStream;
import java.io.Writer;

public class XHTMLSerializer extends HTMLSerializer {
    public XHTMLSerializer() {
        super(true, new OutputFormat(Method.XHTML, null, false));
    }

    public XHTMLSerializer(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XHTML, null, false);
        }
        super(true, format);
    }

    public XHTMLSerializer(Writer writer, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XHTML, null, false);
        }
        super(true, format);
        setOutputCharStream(writer);
    }

    public XHTMLSerializer(OutputStream output, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XHTML, null, false);
        }
        super(true, format);
        setOutputByteStream(output);
    }

    public void setOutputFormat(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XHTML, null, false);
        }
        super.setOutputFormat(format);
    }
}
