package mf.org.apache.xerces.jaxp.validation;

import java.io.IOException;
import mf.javax.xml.transform.Result;
import mf.javax.xml.transform.Source;
import org.xml.sax.SAXException;

interface ValidatorHelper {
    void validate(Source source, Result result) throws SAXException, IOException;
}
