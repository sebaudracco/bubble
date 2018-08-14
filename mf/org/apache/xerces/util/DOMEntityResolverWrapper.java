package mf.org.apache.xerces.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.w3c.dom.ls.LSInput;
import mf.org.w3c.dom.ls.LSResourceResolver;

public class DOMEntityResolverWrapper implements XMLEntityResolver {
    private static final String XML_TYPE = "http://www.w3.org/TR/REC-xml";
    private static final String XSD_TYPE = "http://www.w3.org/2001/XMLSchema";
    protected LSResourceResolver fEntityResolver;

    public DOMEntityResolverWrapper(LSResourceResolver entityResolver) {
        setEntityResolver(entityResolver);
    }

    public void setEntityResolver(LSResourceResolver entityResolver) {
        this.fEntityResolver = entityResolver;
    }

    public LSResourceResolver getEntityResolver() {
        return this.fEntityResolver;
    }

    public XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier) throws XNIException, IOException {
        if (this.fEntityResolver != null) {
            LSInput inputSource;
            if (resourceIdentifier == null) {
                inputSource = this.fEntityResolver.resolveResource(null, null, null, null, null);
            } else {
                inputSource = this.fEntityResolver.resolveResource(getType(resourceIdentifier), resourceIdentifier.getNamespace(), resourceIdentifier.getPublicId(), resourceIdentifier.getLiteralSystemId(), resourceIdentifier.getBaseSystemId());
            }
            if (inputSource != null) {
                String publicId = inputSource.getPublicId();
                String systemId = inputSource.getSystemId();
                String baseSystemId = inputSource.getBaseURI();
                InputStream byteStream = inputSource.getByteStream();
                Reader charStream = inputSource.getCharacterStream();
                String encoding = inputSource.getEncoding();
                String data = inputSource.getStringData();
                XMLInputSource xmlInputSource = new XMLInputSource(publicId, systemId, baseSystemId);
                if (charStream != null) {
                    xmlInputSource.setCharacterStream(charStream);
                } else if (byteStream != null) {
                    xmlInputSource.setByteStream(byteStream);
                } else if (!(data == null || data.length() == 0)) {
                    xmlInputSource.setCharacterStream(new StringReader(data));
                }
                xmlInputSource.setEncoding(encoding);
                return xmlInputSource;
            }
        }
        return null;
    }

    private String getType(XMLResourceIdentifier resourceIdentifier) {
        if ((resourceIdentifier instanceof XMLGrammarDescription) && "http://www.w3.org/2001/XMLSchema".equals(((XMLGrammarDescription) resourceIdentifier).getGrammarType())) {
            return "http://www.w3.org/2001/XMLSchema";
        }
        return "http://www.w3.org/TR/REC-xml";
    }
}
