package mf.org.apache.xerces.impl.xs.models;

import mf.org.apache.xerces.impl.dtd.models.CMNode;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.impl.xs.XSDeclarationPool;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.XSModelGroupImpl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.xs.XSTerm;

public class CMBuilder {
    private static final XSEmptyCM fEmptyCM = new XSEmptyCM();
    private XSDeclarationPool fDeclPool;
    private int fLeafCount;
    private final CMNodeFactory fNodeFactory;
    private int fParticleCount;

    public CMBuilder(CMNodeFactory nodeFactory) {
        this.fDeclPool = null;
        this.fDeclPool = null;
        this.fNodeFactory = nodeFactory;
    }

    public void setDeclPool(XSDeclarationPool declPool) {
        this.fDeclPool = declPool;
    }

    public XSCMValidator getContentModel(XSComplexTypeDecl typeDecl, boolean forUPA) {
        short contentType = typeDecl.getContentType();
        if (contentType == (short) 1 || contentType == (short) 0) {
            return null;
        }
        XSParticleDecl particle = (XSParticleDecl) typeDecl.getParticle();
        if (particle == null) {
            return fEmptyCM;
        }
        XSCMValidator cmValidator;
        if (particle.fType == (short) 3 && ((XSModelGroupImpl) particle.fValue).fCompositor == (short) 103) {
            cmValidator = createAllCM(particle);
        } else {
            cmValidator = createDFACM(particle, forUPA);
        }
        this.fNodeFactory.resetNodeCount();
        if (cmValidator == null) {
            return fEmptyCM;
        }
        return cmValidator;
    }

    XSCMValidator createAllCM(XSParticleDecl particle) {
        if (particle.fMaxOccurs == 0) {
            return null;
        }
        boolean z;
        XSModelGroupImpl group = particle.fValue;
        if (particle.fMinOccurs == 0) {
            z = true;
        } else {
            z = false;
        }
        XSCMValidator allContent = new XSAllCM(z, group.fParticleCount);
        for (int i = 0; i < group.fParticleCount; i++) {
            boolean z2;
            XSElementDecl xSElementDecl = (XSElementDecl) group.fParticles[i].fValue;
            if (group.fParticles[i].fMinOccurs == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            allContent.addElement(xSElementDecl, z2);
        }
        return allContent;
    }

    XSCMValidator createDFACM(XSParticleDecl particle, boolean forUPA) {
        this.fLeafCount = 0;
        this.fParticleCount = 0;
        CMNode node = useRepeatingLeafNodes(particle) ? buildCompactSyntaxTree(particle) : buildSyntaxTree(particle, forUPA);
        if (node == null) {
            return null;
        }
        return new XSDFACM(node, this.fLeafCount);
    }

    private CMNode buildSyntaxTree(XSParticleDecl particle, boolean forUPA) {
        int maxOccurs = particle.fMaxOccurs;
        int minOccurs = particle.fMinOccurs;
        boolean compactedForUPA = false;
        if (forUPA) {
            if (minOccurs > 1) {
                if (maxOccurs > minOccurs || particle.getMaxOccursUnbounded()) {
                    minOccurs = 1;
                    compactedForUPA = true;
                } else {
                    minOccurs = 2;
                    compactedForUPA = true;
                }
            }
            if (maxOccurs > 1) {
                maxOccurs = 2;
                compactedForUPA = true;
            }
        }
        short type = particle.fType;
        CMNode nodeRet = null;
        if (type == (short) 2 || type == (short) 1) {
            CMNodeFactory cMNodeFactory = this.fNodeFactory;
            short s = particle.fType;
            XSTerm xSTerm = particle.fValue;
            int i = this.fParticleCount;
            this.fParticleCount = i + 1;
            int i2 = this.fLeafCount;
            this.fLeafCount = i2 + 1;
            nodeRet = expandContentModel(cMNodeFactory.getCMLeafNode(s, xSTerm, i, i2), minOccurs, maxOccurs);
            if (nodeRet == null) {
                return nodeRet;
            }
            nodeRet.setIsCompactUPAModel(compactedForUPA);
            return nodeRet;
        } else if (type != (short) 3) {
            return null;
        } else {
            XSModelGroupImpl group = particle.fValue;
            int count = 0;
            for (int i3 = 0; i3 < group.fParticleCount; i3++) {
                CMNode temp = buildSyntaxTree(group.fParticles[i3], forUPA);
                if (temp != null) {
                    compactedForUPA |= temp.isCompactedForUPA();
                    count++;
                    if (nodeRet == null) {
                        nodeRet = temp;
                    } else {
                        nodeRet = this.fNodeFactory.getCMBinOpNode(group.fCompositor, nodeRet, temp);
                    }
                }
            }
            if (nodeRet == null) {
                return nodeRet;
            }
            if (group.fCompositor == (short) 101 && count < group.fParticleCount) {
                nodeRet = this.fNodeFactory.getCMUniOpNode(5, nodeRet);
            }
            nodeRet = expandContentModel(nodeRet, minOccurs, maxOccurs);
            nodeRet.setIsCompactUPAModel(compactedForUPA);
            return nodeRet;
        }
    }

    private CMNode expandContentModel(CMNode node, int minOccurs, int maxOccurs) {
        CMNode nodeRet = null;
        if (minOccurs == 1 && maxOccurs == 1) {
            return node;
        }
        if (minOccurs == 0 && maxOccurs == 1) {
            return this.fNodeFactory.getCMUniOpNode(5, node);
        }
        if (minOccurs == 0 && maxOccurs == -1) {
            return this.fNodeFactory.getCMUniOpNode(4, node);
        }
        if (minOccurs == 1 && maxOccurs == -1) {
            return this.fNodeFactory.getCMUniOpNode(6, node);
        }
        if (maxOccurs == -1) {
            return this.fNodeFactory.getCMBinOpNode(102, multiNodes(node, minOccurs - 1, true), this.fNodeFactory.getCMUniOpNode(6, node));
        }
        if (minOccurs > 0) {
            nodeRet = multiNodes(node, minOccurs, false);
        }
        if (maxOccurs <= minOccurs) {
            return nodeRet;
        }
        node = this.fNodeFactory.getCMUniOpNode(5, node);
        if (nodeRet == null) {
            return multiNodes(node, maxOccurs - minOccurs, false);
        }
        return this.fNodeFactory.getCMBinOpNode(102, nodeRet, multiNodes(node, maxOccurs - minOccurs, true));
    }

    private CMNode multiNodes(CMNode node, int num, boolean copyFirst) {
        if (num == 0) {
            return null;
        }
        if (num != 1) {
            int num1 = num / 2;
            return this.fNodeFactory.getCMBinOpNode(102, multiNodes(node, num1, copyFirst), multiNodes(node, num - num1, true));
        } else if (copyFirst) {
            return copyNode(node);
        } else {
            return node;
        }
    }

    private CMNode copyNode(CMNode node) {
        int type = node.type();
        if (type == 101 || type == 102) {
            XSCMBinOp bin = (XSCMBinOp) node;
            return this.fNodeFactory.getCMBinOpNode(type, copyNode(bin.getLeft()), copyNode(bin.getRight()));
        } else if (type == 4 || type == 6 || type == 5) {
            return this.fNodeFactory.getCMUniOpNode(type, copyNode(((XSCMUniOp) node).getChild()));
        } else if (type != 1 && type != 2) {
            return node;
        } else {
            XSCMLeaf leaf = (XSCMLeaf) node;
            CMNodeFactory cMNodeFactory = this.fNodeFactory;
            int type2 = leaf.type();
            Object leaf2 = leaf.getLeaf();
            int particleId = leaf.getParticleId();
            int i = this.fLeafCount;
            this.fLeafCount = i + 1;
            return cMNodeFactory.getCMLeafNode(type2, leaf2, particleId, i);
        }
    }

    private CMNode buildCompactSyntaxTree(XSParticleDecl particle) {
        int maxOccurs = particle.fMaxOccurs;
        int minOccurs = particle.fMinOccurs;
        short type = particle.fType;
        CMNode nodeRet = null;
        if (type == (short) 2 || type == (short) 1) {
            return buildCompactSyntaxTree2(particle, minOccurs, maxOccurs);
        }
        if (type == (short) 3) {
            XSModelGroupImpl group = particle.fValue;
            if (group.fParticleCount == 1 && (minOccurs != 1 || maxOccurs != 1)) {
                return buildCompactSyntaxTree2(group.fParticles[0], minOccurs, maxOccurs);
            }
            int count = 0;
            for (int i = 0; i < group.fParticleCount; i++) {
                CMNode temp = buildCompactSyntaxTree(group.fParticles[i]);
                if (temp != null) {
                    count++;
                    if (nodeRet == null) {
                        nodeRet = temp;
                    } else {
                        nodeRet = this.fNodeFactory.getCMBinOpNode(group.fCompositor, nodeRet, temp);
                    }
                }
            }
            if (nodeRet != null && group.fCompositor == (short) 101 && count < group.fParticleCount) {
                nodeRet = this.fNodeFactory.getCMUniOpNode(5, nodeRet);
            }
        }
        return nodeRet;
    }

    private CMNode buildCompactSyntaxTree2(XSParticleDecl particle, int minOccurs, int maxOccurs) {
        CMNodeFactory cMNodeFactory;
        short s;
        XSTerm xSTerm;
        int i;
        int i2;
        if (minOccurs == 1 && maxOccurs == 1) {
            cMNodeFactory = this.fNodeFactory;
            s = particle.fType;
            xSTerm = particle.fValue;
            i = this.fParticleCount;
            this.fParticleCount = i + 1;
            i2 = this.fLeafCount;
            this.fLeafCount = i2 + 1;
            return cMNodeFactory.getCMLeafNode(s, xSTerm, i, i2);
        } else if (minOccurs == 0 && maxOccurs == 1) {
            cMNodeFactory = this.fNodeFactory;
            s = particle.fType;
            xSTerm = particle.fValue;
            i = this.fParticleCount;
            this.fParticleCount = i + 1;
            i2 = this.fLeafCount;
            this.fLeafCount = i2 + 1;
            return this.fNodeFactory.getCMUniOpNode(5, cMNodeFactory.getCMLeafNode(s, xSTerm, i, i2));
        } else if (minOccurs == 0 && maxOccurs == -1) {
            cMNodeFactory = this.fNodeFactory;
            s = particle.fType;
            xSTerm = particle.fValue;
            i = this.fParticleCount;
            this.fParticleCount = i + 1;
            i2 = this.fLeafCount;
            this.fLeafCount = i2 + 1;
            return this.fNodeFactory.getCMUniOpNode(4, cMNodeFactory.getCMLeafNode(s, xSTerm, i, i2));
        } else if (minOccurs == 1 && maxOccurs == -1) {
            cMNodeFactory = this.fNodeFactory;
            s = particle.fType;
            xSTerm = particle.fValue;
            i = this.fParticleCount;
            this.fParticleCount = i + 1;
            i2 = this.fLeafCount;
            this.fLeafCount = i2 + 1;
            return this.fNodeFactory.getCMUniOpNode(6, cMNodeFactory.getCMLeafNode(s, xSTerm, i, i2));
        } else {
            cMNodeFactory = this.fNodeFactory;
            s = particle.fType;
            xSTerm = particle.fValue;
            int i3 = this.fParticleCount;
            this.fParticleCount = i3 + 1;
            int i4 = this.fLeafCount;
            this.fLeafCount = i4 + 1;
            CMNode nodeRet = cMNodeFactory.getCMRepeatingLeafNode(s, xSTerm, minOccurs, maxOccurs, i3, i4);
            if (minOccurs == 0) {
                return this.fNodeFactory.getCMUniOpNode(4, nodeRet);
            }
            return this.fNodeFactory.getCMUniOpNode(6, nodeRet);
        }
    }

    private boolean useRepeatingLeafNodes(XSParticleDecl particle) {
        int maxOccurs = particle.fMaxOccurs;
        int minOccurs = particle.fMinOccurs;
        if (particle.fType == (short) 3) {
            XSModelGroupImpl group = particle.fValue;
            if (minOccurs == 1 && maxOccurs == 1) {
                for (int i = 0; i < group.fParticleCount; i++) {
                    if (!useRepeatingLeafNodes(group.fParticles[i])) {
                        return false;
                    }
                }
            } else if (group.fParticleCount == 1) {
                XSParticleDecl particle2 = group.fParticles[0];
                short type2 = particle2.fType;
                if ((type2 == (short) 1 || type2 == (short) 2) && particle2.fMinOccurs == 1 && particle2.fMaxOccurs == 1) {
                    return true;
                }
                return false;
            } else if (group.fParticleCount == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
