package mf.org.apache.xerces.impl.xs.models;

import java.util.Vector;
import mf.org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import mf.org.apache.xerces.impl.xs.XMLSchemaException;
import mf.org.apache.xerces.xni.QName;

public class XSEmptyCM implements XSCMValidator {
    private static final Vector EMPTY = new Vector(0);
    private static final short STATE_START = (short) 0;

    public int[] startContentModel() {
        return new int[1];
    }

    public Object oneTransition(QName elementName, int[] currentState, SubstitutionGroupHandler subGroupHandler) {
        if (currentState[0] < 0) {
            currentState[0] = -2;
        } else {
            currentState[0] = -1;
        }
        return null;
    }

    public boolean endContentModel(int[] currentState) {
        if (currentState[0] < 0) {
            return false;
        }
        return true;
    }

    public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException {
        return false;
    }

    public Vector whatCanGoHere(int[] state) {
        return EMPTY;
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
