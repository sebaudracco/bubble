package mf.javax.xml.transform.sax;

import mf.javax.xml.transform.Result;
import mf.javax.xml.transform.Transformer;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;

public interface TransformerHandler extends ContentHandler, LexicalHandler, DTDHandler {
    String getSystemId();

    Transformer getTransformer();

    void setResult(Result result) throws IllegalArgumentException;

    void setSystemId(String str);
}
