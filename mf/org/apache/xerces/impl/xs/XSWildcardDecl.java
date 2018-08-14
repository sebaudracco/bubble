package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSWildcard;

public class XSWildcardDecl implements XSWildcard {
    public static final String ABSENT = null;
    public XSObjectList fAnnotations = null;
    private String fDescription = null;
    public String[] fNamespaceList;
    public short fProcessContents = (short) 1;
    public short fType = (short) 1;

    public boolean allowNamespace(String namespace) {
        if (this.fType == (short) 1) {
            return true;
        }
        int listNum;
        int i;
        if (this.fType == (short) 2) {
            boolean found = false;
            listNum = this.fNamespaceList.length;
            for (i = 0; i < listNum && !found; i++) {
                if (namespace == this.fNamespaceList[i]) {
                    found = true;
                }
            }
            if (!found) {
                return true;
            }
        }
        if (this.fType == (short) 3) {
            for (String str : this.fNamespaceList) {
                if (namespace == str) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubsetOf(XSWildcardDecl superWildcard) {
        if (superWildcard == null) {
            return false;
        }
        if (superWildcard.fType == (short) 1) {
            return true;
        }
        if (this.fType == (short) 2 && superWildcard.fType == (short) 2 && this.fNamespaceList[0] == superWildcard.fNamespaceList[0]) {
            return true;
        }
        if (this.fType != (short) 3) {
            return false;
        }
        if (superWildcard.fType == (short) 3 && subset2sets(this.fNamespaceList, superWildcard.fNamespaceList)) {
            return true;
        }
        if (superWildcard.fType != (short) 2 || elementInSet(superWildcard.fNamespaceList[0], this.fNamespaceList) || elementInSet(ABSENT, this.fNamespaceList)) {
            return false;
        }
        return true;
    }

    public boolean weakerProcessContents(XSWildcardDecl superWildcard) {
        if (this.fProcessContents == (short) 3 && superWildcard.fProcessContents == (short) 1) {
            return true;
        }
        return this.fProcessContents == (short) 2 && superWildcard.fProcessContents != (short) 2;
    }

    public XSWildcardDecl performUnionWith(XSWildcardDecl wildcard, short processContents) {
        if (wildcard == null) {
            return null;
        }
        XSWildcardDecl unionWildcard = new XSWildcardDecl();
        unionWildcard.fProcessContents = processContents;
        if (areSame(wildcard)) {
            unionWildcard.fType = this.fType;
            unionWildcard.fNamespaceList = this.fNamespaceList;
            return unionWildcard;
        } else if (this.fType == (short) 1 || wildcard.fType == (short) 1) {
            unionWildcard.fType = (short) 1;
            return unionWildcard;
        } else if (this.fType == (short) 3 && wildcard.fType == (short) 3) {
            unionWildcard.fType = (short) 3;
            unionWildcard.fNamespaceList = union2sets(this.fNamespaceList, wildcard.fNamespaceList);
            return unionWildcard;
        } else if (this.fType == (short) 2 && wildcard.fType == (short) 2) {
            unionWildcard.fType = (short) 2;
            unionWildcard.fNamespaceList = new String[2];
            unionWildcard.fNamespaceList[0] = ABSENT;
            unionWildcard.fNamespaceList[1] = ABSENT;
            return unionWildcard;
        } else if ((this.fType != (short) 2 || wildcard.fType != (short) 3) && (this.fType != (short) 3 || wildcard.fType != (short) 2)) {
            return unionWildcard;
        } else {
            String[] other;
            String[] list;
            if (this.fType == (short) 2) {
                other = this.fNamespaceList;
                list = wildcard.fNamespaceList;
            } else {
                other = wildcard.fNamespaceList;
                list = this.fNamespaceList;
            }
            boolean foundAbsent = elementInSet(ABSENT, list);
            if (other[0] != ABSENT) {
                boolean foundNS = elementInSet(other[0], list);
                if (foundNS && foundAbsent) {
                    unionWildcard.fType = (short) 1;
                    return unionWildcard;
                } else if (foundNS && !foundAbsent) {
                    unionWildcard.fType = (short) 2;
                    unionWildcard.fNamespaceList = new String[2];
                    unionWildcard.fNamespaceList[0] = ABSENT;
                    unionWildcard.fNamespaceList[1] = ABSENT;
                    return unionWildcard;
                } else if (!foundNS && foundAbsent) {
                    return null;
                } else {
                    unionWildcard.fType = (short) 2;
                    unionWildcard.fNamespaceList = other;
                    return unionWildcard;
                }
            } else if (foundAbsent) {
                unionWildcard.fType = (short) 1;
                return unionWildcard;
            } else {
                unionWildcard.fType = (short) 2;
                unionWildcard.fNamespaceList = other;
                return unionWildcard;
            }
        }
    }

    public XSWildcardDecl performIntersectionWith(XSWildcardDecl wildcard, short processContents) {
        if (wildcard == null) {
            return null;
        }
        XSWildcardDecl intersectWildcard = new XSWildcardDecl();
        intersectWildcard.fProcessContents = processContents;
        if (areSame(wildcard)) {
            intersectWildcard.fType = this.fType;
            intersectWildcard.fNamespaceList = this.fNamespaceList;
            return intersectWildcard;
        } else if (this.fType == (short) 1 || wildcard.fType == (short) 1) {
            XSWildcardDecl other = this;
            if (this.fType == (short) 1) {
                other = wildcard;
            }
            intersectWildcard.fType = other.fType;
            intersectWildcard.fNamespaceList = other.fNamespaceList;
            return intersectWildcard;
        } else if ((this.fType == (short) 2 && wildcard.fType == (short) 3) || (this.fType == (short) 3 && wildcard.fType == (short) 2)) {
            String[] list;
            if (this.fType == (short) 2) {
                other = this.fNamespaceList;
                list = wildcard.fNamespaceList;
            } else {
                other = wildcard.fNamespaceList;
                list = this.fNamespaceList;
            }
            int listSize = list.length;
            String[] intersect = new String[listSize];
            int i = 0;
            int newSize = 0;
            while (i < listSize) {
                int newSize2;
                if (list[i] == other[0] || list[i] == ABSENT) {
                    newSize2 = newSize;
                } else {
                    newSize2 = newSize + 1;
                    intersect[newSize] = list[i];
                }
                i++;
                newSize = newSize2;
            }
            intersectWildcard.fType = (short) 3;
            intersectWildcard.fNamespaceList = new String[newSize];
            System.arraycopy(intersect, 0, intersectWildcard.fNamespaceList, 0, newSize);
            return intersectWildcard;
        } else if (this.fType == (short) 3 && wildcard.fType == (short) 3) {
            intersectWildcard.fType = (short) 3;
            intersectWildcard.fNamespaceList = intersect2sets(this.fNamespaceList, wildcard.fNamespaceList);
            return intersectWildcard;
        } else if (this.fType != (short) 2 || wildcard.fType != (short) 2) {
            return intersectWildcard;
        } else {
            if (this.fNamespaceList[0] != ABSENT && wildcard.fNamespaceList[0] != ABSENT) {
                return null;
            }
            other = this;
            if (this.fNamespaceList[0] == ABSENT) {
                other = wildcard;
            }
            intersectWildcard.fType = other.fType;
            intersectWildcard.fNamespaceList = other.fNamespaceList;
            return intersectWildcard;
        }
    }

    private boolean areSame(XSWildcardDecl wildcard) {
        if (this.fType == wildcard.fType) {
            if (this.fType == (short) 1) {
                return true;
            }
            if (this.fType == (short) 2) {
                if (this.fNamespaceList[0] != wildcard.fNamespaceList[0]) {
                    return false;
                }
                return true;
            } else if (this.fNamespaceList.length == wildcard.fNamespaceList.length) {
                for (String elementInSet : this.fNamespaceList) {
                    if (!elementInSet(elementInSet, wildcard.fNamespaceList)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    String[] intersect2sets(String[] one, String[] theOther) {
        String[] result = new String[Math.min(one.length, theOther.length)];
        int count = 0;
        for (int i = 0; i < one.length; i++) {
            if (elementInSet(one[i], theOther)) {
                int count2 = count + 1;
                result[count] = one[i];
                count = count2;
            }
        }
        String[] result2 = new String[count];
        System.arraycopy(result, 0, result2, 0, count);
        return result2;
    }

    String[] union2sets(String[] one, String[] theOther) {
        String[] result1 = new String[one.length];
        int count = 0;
        for (int i = 0; i < one.length; i++) {
            if (!elementInSet(one[i], theOther)) {
                int count2 = count + 1;
                result1[count] = one[i];
                count = count2;
            }
        }
        String[] result2 = new String[(theOther.length + count)];
        System.arraycopy(result1, 0, result2, 0, count);
        System.arraycopy(theOther, 0, result2, count, theOther.length);
        return result2;
    }

    boolean subset2sets(String[] subSet, String[] superSet) {
        for (String elementInSet : subSet) {
            if (!elementInSet(elementInSet, superSet)) {
                return false;
            }
        }
        return true;
    }

    boolean elementInSet(String ele, String[] set) {
        boolean found = false;
        for (int i = 0; i < set.length && !found; i++) {
            if (ele == set[i]) {
                found = true;
            }
        }
        return found;
    }

    public String toString() {
        if (this.fDescription == null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("WC[");
            switch (this.fType) {
                case (short) 1:
                    buffer.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
                    break;
                case (short) 2:
                    buffer.append(SchemaSymbols.ATTVAL_TWOPOUNDOTHER);
                    buffer.append(":\"");
                    if (this.fNamespaceList[0] != null) {
                        buffer.append(this.fNamespaceList[0]);
                    }
                    buffer.append("\"");
                    break;
                case (short) 3:
                    if (this.fNamespaceList.length != 0) {
                        buffer.append("\"");
                        if (this.fNamespaceList[0] != null) {
                            buffer.append(this.fNamespaceList[0]);
                        }
                        buffer.append("\"");
                        for (int i = 1; i < this.fNamespaceList.length; i++) {
                            buffer.append(",\"");
                            if (this.fNamespaceList[i] != null) {
                                buffer.append(this.fNamespaceList[i]);
                            }
                            buffer.append("\"");
                        }
                        break;
                    }
                    break;
            }
            buffer.append(']');
            this.fDescription = buffer.toString();
        }
        return this.fDescription;
    }

    public short getType() {
        return (short) 9;
    }

    public String getName() {
        return null;
    }

    public String getNamespace() {
        return null;
    }

    public short getConstraintType() {
        return this.fType;
    }

    public StringList getNsConstraintList() {
        return new StringListImpl(this.fNamespaceList, this.fNamespaceList == null ? 0 : this.fNamespaceList.length);
    }

    public short getProcessContents() {
        return this.fProcessContents;
    }

    public String getProcessContentsAsString() {
        switch (this.fProcessContents) {
            case (short) 1:
                return SchemaSymbols.ATTVAL_STRICT;
            case (short) 2:
                return "skip";
            case (short) 3:
                return SchemaSymbols.ATTVAL_LAX;
            default:
                return "invalid value";
        }
    }

    public XSAnnotation getAnnotation() {
        return this.fAnnotations != null ? (XSAnnotation) this.fAnnotations.item(0) : null;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    public XSNamespaceItem getNamespaceItem() {
        return null;
    }
}
