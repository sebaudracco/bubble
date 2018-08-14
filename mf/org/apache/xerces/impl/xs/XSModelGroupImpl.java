package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSModelGroup;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;

public class XSModelGroupImpl implements XSModelGroup {
    public static final short MODELGROUP_ALL = (short) 103;
    public static final short MODELGROUP_CHOICE = (short) 101;
    public static final short MODELGROUP_SEQUENCE = (short) 102;
    public XSObjectList fAnnotations = null;
    public short fCompositor;
    private String fDescription = null;
    public int fParticleCount = 0;
    public XSParticleDecl[] fParticles = null;

    public boolean isEmpty() {
        for (int i = 0; i < this.fParticleCount; i++) {
            if (!this.fParticles[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int minEffectiveTotalRange() {
        if (this.fCompositor == (short) 101) {
            return minEffectiveTotalRangeChoice();
        }
        return minEffectiveTotalRangeAllSeq();
    }

    private int minEffectiveTotalRangeAllSeq() {
        int total = 0;
        for (int i = 0; i < this.fParticleCount; i++) {
            total += this.fParticles[i].minEffectiveTotalRange();
        }
        return total;
    }

    private int minEffectiveTotalRangeChoice() {
        int min = 0;
        if (this.fParticleCount > 0) {
            min = this.fParticles[0].minEffectiveTotalRange();
        }
        for (int i = 1; i < this.fParticleCount; i++) {
            int one = this.fParticles[i].minEffectiveTotalRange();
            if (one < min) {
                min = one;
            }
        }
        return min;
    }

    public int maxEffectiveTotalRange() {
        if (this.fCompositor == (short) 101) {
            return maxEffectiveTotalRangeChoice();
        }
        return maxEffectiveTotalRangeAllSeq();
    }

    private int maxEffectiveTotalRangeAllSeq() {
        int total = 0;
        for (int i = 0; i < this.fParticleCount; i++) {
            int one = this.fParticles[i].maxEffectiveTotalRange();
            if (one == -1) {
                return -1;
            }
            total += one;
        }
        return total;
    }

    private int maxEffectiveTotalRangeChoice() {
        int max = 0;
        if (this.fParticleCount > 0) {
            max = this.fParticles[0].maxEffectiveTotalRange();
            if (max == -1) {
                return -1;
            }
        }
        for (int i = 1; i < this.fParticleCount; i++) {
            int one = this.fParticles[i].maxEffectiveTotalRange();
            if (one == -1) {
                return -1;
            }
            if (one > max) {
                max = one;
            }
        }
        return max;
    }

    public String toString() {
        if (this.fDescription == null) {
            StringBuffer buffer = new StringBuffer();
            if (this.fCompositor == (short) 103) {
                buffer.append("all(");
            } else {
                buffer.append('(');
            }
            if (this.fParticleCount > 0) {
                buffer.append(this.fParticles[0].toString());
            }
            for (int i = 1; i < this.fParticleCount; i++) {
                if (this.fCompositor == (short) 101) {
                    buffer.append('|');
                } else {
                    buffer.append(',');
                }
                buffer.append(this.fParticles[i].toString());
            }
            buffer.append(')');
            this.fDescription = buffer.toString();
        }
        return this.fDescription;
    }

    public void reset() {
        this.fCompositor = (short) 102;
        this.fParticles = null;
        this.fParticleCount = 0;
        this.fDescription = null;
        this.fAnnotations = null;
    }

    public short getType() {
        return (short) 7;
    }

    public String getName() {
        return null;
    }

    public String getNamespace() {
        return null;
    }

    public short getCompositor() {
        if (this.fCompositor == (short) 101) {
            return (short) 2;
        }
        if (this.fCompositor == (short) 102) {
            return (short) 1;
        }
        return (short) 3;
    }

    public XSObjectList getParticles() {
        return new XSObjectListImpl(this.fParticles, this.fParticleCount);
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
