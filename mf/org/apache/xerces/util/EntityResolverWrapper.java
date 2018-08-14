package mf.org.apache.xerces.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class EntityResolverWrapper implements XMLEntityResolver {
    protected EntityResolver fEntityResolver;

    public EntityResolverWrapper(EntityResolver entityResolver) {
        setEntityResolver(entityResolver);
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.fEntityResolver = entityResolver;
    }

    public EntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }

    public XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier) throws XNIException, IOException {
        XMLInputSource xMLInputSource = null;
        String pubId = resourceIdentifier.getPublicId();
        String sysId = resourceIdentifier.getExpandedSystemId();
        if (!((pubId == null && sysId == null) || this.fEntityResolver == null || resourceIdentifier == null)) {
            try {
                InputSource inputSource = this.fEntityResolver.resolveEntity(pubId, sysId);
                if (inputSource != null) {
                    String publicId = inputSource.getPublicId();
                    String systemId = inputSource.getSystemId();
                    String baseSystemId = resourceIdentifier.getBaseSystemId();
                    InputStream byteStream = inputSource.getByteStream();
                    Reader charStream = inputSource.getCharacterStream();
                    String encoding = inputSource.getEncoding();
                    xMLInputSource = new XMLInputSource(publicId, systemId, baseSystemId);
                    xMLInputSource.setByteStream(byteStream);
                    xMLInputSource.setCharacterStream(charStream);
                    xMLInputSource.setEncoding(encoding);
                }
            } catch (Exception e) {
                Exception ex = e.getException();
                if (ex == null) {
                    ex = e;
                }
                throw new XNIException(ex);
            }
        }
        return xMLInputSource;
    }
}
