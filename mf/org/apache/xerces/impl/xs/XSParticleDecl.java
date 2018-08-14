package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSParticle;
import mf.org.apache.xerces.xs.XSTerm;

public class XSParticleDecl implements XSParticle {
    public static final short PARTICLE_ELEMENT = (short) 1;
    public static final short PARTICLE_EMPTY = (short) 0;
    public static final short PARTICLE_MODELGROUP = (short) 3;
    public static final short PARTICLE_ONE_OR_MORE = (short) 6;
    public static final short PARTICLE_WILDCARD = (short) 2;
    public static final short PARTICLE_ZERO_OR_MORE = (short) 4;
    public static final short PARTICLE_ZERO_OR_ONE = (short) 5;
    public XSObjectList fAnnotations = null;
    private String fDescription = null;
    public int fMaxOccurs = 1;
    public int fMinOccurs = 1;
    public short fType = (short) 0;
    public XSTerm fValue = null;

    public XSParticleDecl makeClone() {
        XSParticleDecl particle = new XSParticleDecl();
        particle.fType = this.fType;
        particle.fMinOccurs = this.fMinOccurs;
        particle.fMaxOccurs = this.fMaxOccurs;
        particle.fDescription = this.fDescription;
        particle.fValue = this.fValue;
        particle.fAnnotations = this.fAnnotations;
        return particle;
    }

    public boolean emptiable() {
        return minEffectiveTotalRange() == 0;
    }

    public boolean isEmpty() {
        if (this.fType == (short) 0) {
            return true;
        }
        if (this.fType == (short) 1 || this.fType == (short) 2) {
            return false;
        }
        return ((XSModelGroupImpl) this.fValue).isEmpty();
    }

    public int minEffectiveTotalRange() {
        if (this.fType == (short) 0) {
            return 0;
        }
        if (this.fType == (short) 3) {
            return ((XSModelGroupImpl) this.fValue).minEffectiveTotalRange() * this.fMinOccurs;
        }
        return this.fMinOccurs;
    }

    public int maxEffectiveTotalRange() {
        if (this.fType == (short) 0) {
            return 0;
        }
        if (this.fType != (short) 3) {
            return this.fMaxOccurs;
        }
        int max = ((XSModelGroupImpl) this.fValue).maxEffectiveTotalRange();
        if (max == -1) {
            return -1;
        }
        if (max == 0 || this.fMaxOccurs != -1) {
            return this.fMaxOccurs * max;
        }
        return -1;
    }

    public String toString() {
        if (this.fDescription == null) {
            StringBuffer buffer = new StringBuffer();
            appendParticle(buffer);
            if (!((this.fMinOccurs == 0 && this.fMaxOccurs == 0) || (this.fMinOccurs == 1 && this.fMaxOccurs == 1))) {
                buffer.append('{').append(this.fMinOccurs);
                if (this.fMaxOccurs == -1) {
                    buffer.append("-UNBOUNDED");
                } else if (this.fMinOccurs != this.fMaxOccurs) {
                    buffer.append('-').append(this.fMaxOccurs);
                }
                buffer.append('}');
            }
            this.fDescription = buffer.toString();
        }
        return this.fDescription;
    }

    void appendParticle(StringBuffer buffer) {
        switch (this.fType) {
            case (short) 0:
                buffer.append("EMPTY");
                return;
            case (short) 1:
                buffer.append(this.fValue.toString());
                return;
            case (short) 2:
                buffer.append('(');
                buffer.append(this.fValue.toString());
                buffer.append(')');
                return;
            case (short) 3:
                buffer.append(this.fValue.toString());
                return;
            default:
                return;
        }
    }

    public void reset() {
        this.fType = (short) 0;
        this.fValue = null;
        this.fMinOccurs = 1;
        this.fMaxOccurs = 1;
        this.fDescription = null;
        this.fAnnotations = null;
    }

    public short getType() {
        return (short) 8;
    }

    public String getName() {
        return null;
    }

    public String getNamespace() {
        return null;
    }

    public int getMinOccurs() {
        return this.fMinOccurs;
    }

    public boolean getMaxOccursUnbounded() {
        return this.fMaxOccurs == -1;
    }

    public int getMaxOccurs() {
        return this.fMaxOccurs;
    }

    public XSTerm getTerm() {
        return this.fValue;
    }

    public XSNamespaceItem getNamespaceItem() {
        return null;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }
}
