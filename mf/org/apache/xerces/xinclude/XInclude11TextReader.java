package mf.org.apache.xerces.xinclude;

import java.io.IOException;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XInclude11TextReader extends XIncludeTextReader {
    public XInclude11TextReader(XMLInputSource source, XIncludeHandler handler, int bufferSize) throws IOException {
        super(source, handler, bufferSize);
    }

    protected boolean isValid(int ch) {
        return XML11Char.isXML11Valid(ch);
    }
}
