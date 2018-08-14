package mf.org.apache.xerces.impl.xs.models;

import java.util.Vector;
import mf.org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import mf.org.apache.xerces.impl.xs.XMLSchemaException;
import mf.org.apache.xerces.xni.QName;

public interface XSCMValidator {
    public static final short FIRST_ERROR = (short) -1;
    public static final short SUBSEQUENT_ERROR = (short) -2;

    boolean checkUniqueParticleAttribution(SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException;

    boolean endContentModel(int[] iArr);

    String getTermName(int i);

    boolean isCompactedForUPA();

    int[] occurenceInfo(int[] iArr);

    Object oneTransition(QName qName, int[] iArr, SubstitutionGroupHandler substitutionGroupHandler);

    int[] startContentModel();

    Vector whatCanGoHere(int[] iArr);
}
