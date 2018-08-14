package mf.javax.xml.transform.sax;

import mf.javax.xml.transform.Templates;
import org.xml.sax.ContentHandler;

public interface TemplatesHandler extends ContentHandler {
    String getSystemId();

    Templates getTemplates();

    void setSystemId(String str);
}
