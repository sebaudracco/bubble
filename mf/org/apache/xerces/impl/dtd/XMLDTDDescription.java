package mf.org.apache.xerces.impl.dtd;

import java.util.ArrayList;
import java.util.Vector;
import mf.org.apache.xerces.util.XMLResourceIdentifierImpl;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDTDDescription extends XMLResourceIdentifierImpl implements mf.org.apache.xerces.xni.grammars.XMLDTDDescription {
    protected ArrayList fPossibleRoots = null;
    protected String fRootName = null;

    public XMLDTDDescription(XMLResourceIdentifier id, String rootName) {
        setValues(id.getPublicId(), id.getLiteralSystemId(), id.getBaseSystemId(), id.getExpandedSystemId());
        this.fRootName = rootName;
        this.fPossibleRoots = null;
    }

    public XMLDTDDescription(String publicId, String literalId, String baseId, String expandedId, String rootName) {
        setValues(publicId, literalId, baseId, expandedId);
        this.fRootName = rootName;
        this.fPossibleRoots = null;
    }

    public XMLDTDDescription(XMLInputSource source) {
        setValues(source.getPublicId(), null, source.getBaseSystemId(), source.getSystemId());
        this.fRootName = null;
        this.fPossibleRoots = null;
    }

    public String getGrammarType() {
        return "http://www.w3.org/TR/REC-xml";
    }

    public String getRootName() {
        return this.fRootName;
    }

    public void setRootName(String rootName) {
        this.fRootName = rootName;
        this.fPossibleRoots = null;
    }

    public void setPossibleRoots(ArrayList possibleRoots) {
        this.fPossibleRoots = possibleRoots;
    }

    public void setPossibleRoots(Vector possibleRoots) {
        this.fPossibleRoots = possibleRoots != null ? new ArrayList(possibleRoots) : null;
    }

    public boolean equals(Object desc) {
        if (!(desc instanceof XMLGrammarDescription)) {
            return false;
        }
        if (!getGrammarType().equals(((XMLGrammarDescription) desc).getGrammarType())) {
            return false;
        }
        XMLDTDDescription dtdDesc = (XMLDTDDescription) desc;
        if (this.fRootName != null) {
            if (dtdDesc.fRootName != null && !dtdDesc.fRootName.equals(this.fRootName)) {
                return false;
            }
            if (!(dtdDesc.fPossibleRoots == null || dtdDesc.fPossibleRoots.contains(this.fRootName))) {
                return false;
            }
        } else if (this.fPossibleRoots != null) {
            if (dtdDesc.fRootName != null) {
                if (!this.fPossibleRoots.contains(dtdDesc.fRootName)) {
                    return false;
                }
            } else if (dtdDesc.fPossibleRoots == null) {
                return false;
            } else {
                boolean found = false;
                int size = this.fPossibleRoots.size();
                for (int i = 0; i < size; i++) {
                    found = dtdDesc.fPossibleRoots.contains((String) this.fPossibleRoots.get(i));
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        if (this.fExpandedSystemId != null) {
            if (!this.fExpandedSystemId.equals(dtdDesc.fExpandedSystemId)) {
                return false;
            }
        } else if (dtdDesc.fExpandedSystemId != null) {
            return false;
        }
        if (this.fPublicId != null) {
            if (!this.fPublicId.equals(dtdDesc.fPublicId)) {
                return false;
            }
        } else if (dtdDesc.fPublicId != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.fExpandedSystemId != null) {
            return this.fExpandedSystemId.hashCode();
        }
        if (this.fPublicId != null) {
            return this.fPublicId.hashCode();
        }
        return 0;
    }
}
