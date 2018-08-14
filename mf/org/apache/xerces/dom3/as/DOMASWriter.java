package mf.org.apache.xerces.dom3.as;

import java.io.OutputStream;
import mf.org.w3c.dom.ls.LSSerializer;

public interface DOMASWriter extends LSSerializer {
    void writeASModel(OutputStream outputStream, ASModel aSModel) throws Exception;
}
