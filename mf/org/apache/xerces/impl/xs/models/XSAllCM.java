package mf.org.apache.xerces.impl.xs.models;

import java.util.Vector;
import mf.org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import mf.org.apache.xerces.impl.xs.XMLSchemaException;
import mf.org.apache.xerces.impl.xs.XSConstraints;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.xni.QName;

public class XSAllCM implements XSCMValidator {
    private static final short STATE_CHILD = (short) 1;
    private static final short STATE_START = (short) 0;
    private static final short STATE_VALID = (short) 1;
    private final XSElementDecl[] fAllElements;
    private final boolean fHasOptionalContent;
    private final boolean[] fIsOptionalElement;
    private int fNumElements = 0;

    public XSAllCM(boolean hasOptionalContent, int size) {
        this.fHasOptionalContent = hasOptionalContent;
        this.fAllElements = new XSElementDecl[size];
        this.fIsOptionalElement = new boolean[size];
    }

    public void addElement(XSElementDecl element, boolean isOptional) {
        this.fAllElements[this.fNumElements] = element;
        this.fIsOptionalElement[this.fNumElements] = isOptional;
        this.fNumElements++;
    }

    public int[] startContentModel() {
        int[] state = new int[(this.fNumElements + 1)];
        for (int i = 0; i <= this.fNumElements; i++) {
            state[i] = 0;
        }
        return state;
    }

    Object findMatchingDecl(QName elementName, SubstitutionGroupHandler subGroupHandler) {
        Object matchingDecl = null;
        for (int i = 0; i < this.fNumElements; i++) {
            matchingDecl = subGroupHandler.getMatchingElemDecl(elementName, this.fAllElements[i]);
            if (matchingDecl != null) {
                break;
            }
        }
        return matchingDecl;
    }

    public Object oneTransition(QName elementName, int[] currentState, SubstitutionGroupHandler subGroupHandler) {
        if (currentState[0] < 0) {
            currentState[0] = -2;
            return findMatchingDecl(elementName, subGroupHandler);
        }
        currentState[0] = 1;
        for (int i = 0; i < this.fNumElements; i++) {
            if (currentState[i + 1] == 0) {
                Object matchingDecl = subGroupHandler.getMatchingElemDecl(elementName, this.fAllElements[i]);
                if (matchingDecl != null) {
                    currentState[i + 1] = 1;
                    return matchingDecl;
                }
            }
        }
        currentState[0] = -1;
        return findMatchingDecl(elementName, subGroupHandler);
    }

    public boolean endContentModel(int[] currentState) {
        int state = currentState[0];
        if (state == -1 || state == -2) {
            return false;
        }
        if (this.fHasOptionalContent && state == 0) {
            return true;
        }
        int i = 0;
        while (i < this.fNumElements) {
            if (!this.fIsOptionalElement[i] && currentState[i + 1] == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException {
        for (int i = 0; i < this.fNumElements; i++) {
            for (int j = i + 1; j < this.fNumElements; j++) {
                if (XSConstraints.overlapUPA(this.fAllElements[i], this.fAllElements[j], subGroupHandler)) {
                    throw new XMLSchemaException("cos-nonambig", new Object[]{this.fAllElements[i].toString(), this.fAllElements[j].toString()});
                }
            }
        }
        return false;
    }

    public Vector whatCanGoHere(int[] state) {
        Vector ret = new Vector();
        for (int i = 0; i < this.fNumElements; i++) {
            if (state[i + 1] == 0) {
                ret.addElement(this.fAllElements[i]);
            }
        }
        return ret;
    }

    public int[] occurenceInfo(int[] state) {
        return null;
    }

    public String getTermName(int termId) {
        return null;
    }

    public boolean isCompactedForUPA() {
        return false;
    }
}
