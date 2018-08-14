package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSModelGroupImpl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.w3c.dom.Element;

abstract class XSDAbstractParticleTraverser extends XSDAbstractTraverser {
    ParticleArray fPArray = new ParticleArray();

    static class ParticleArray {
        int fContextCount = 0;
        XSParticleDecl[] fParticles = new XSParticleDecl[10];
        int[] fPos = new int[5];

        ParticleArray() {
        }

        void pushContext() {
            this.fContextCount++;
            if (this.fContextCount == this.fPos.length) {
                int[] newArray = new int[(this.fContextCount * 2)];
                System.arraycopy(this.fPos, 0, newArray, 0, this.fContextCount);
                this.fPos = newArray;
            }
            this.fPos[this.fContextCount] = this.fPos[this.fContextCount - 1];
        }

        int getParticleCount() {
            return this.fPos[this.fContextCount] - this.fPos[this.fContextCount - 1];
        }

        void addParticle(XSParticleDecl particle) {
            if (this.fPos[this.fContextCount] == this.fParticles.length) {
                XSParticleDecl[] newArray = new XSParticleDecl[(this.fPos[this.fContextCount] * 2)];
                System.arraycopy(this.fParticles, 0, newArray, 0, this.fPos[this.fContextCount]);
                this.fParticles = newArray;
            }
            XSParticleDecl[] xSParticleDeclArr = this.fParticles;
            int[] iArr = this.fPos;
            int i = this.fContextCount;
            int i2 = iArr[i];
            iArr[i] = i2 + 1;
            xSParticleDeclArr[i2] = particle;
        }

        XSParticleDecl[] popContext() {
            int count = this.fPos[this.fContextCount] - this.fPos[this.fContextCount - 1];
            XSParticleDecl[] array = null;
            if (count != 0) {
                array = new XSParticleDecl[count];
                System.arraycopy(this.fParticles, this.fPos[this.fContextCount - 1], array, 0, count);
                for (int i = this.fPos[this.fContextCount - 1]; i < this.fPos[this.fContextCount]; i++) {
                    this.fParticles[i] = null;
                }
            }
            this.fContextCount--;
            return array;
        }
    }

    XSDAbstractParticleTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSParticleDecl traverseAll(Element allDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar, int allContextFlags, XSObject parent) {
        Element child;
        XSParticleDecl particle;
        XSObjectList annotations;
        Object[] attrValues = this.fAttrChecker.checkAttributes(allDecl, false, schemaDoc);
        Element child2 = DOMUtil.getFirstChildElement(allDecl);
        XSAnnotationImpl annotation = null;
        if (child2 == null || !DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String text = DOMUtil.getSyntheticAnnotation(allDecl);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(allDecl, text, attrValues, false, schemaDoc);
                child = child2;
            } else {
                child = child2;
            }
        } else {
            annotation = traverseAnnotationDecl(child2, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child2);
        }
        this.fPArray.pushContext();
        for (child = 
/*
Method generation error in method: mf.org.apache.xerces.impl.xs.traversers.XSDAbstractParticleTraverser.traverseAll(mf.org.w3c.dom.Element, mf.org.apache.xerces.impl.xs.traversers.XSDocumentInfo, mf.org.apache.xerces.impl.xs.SchemaGrammar, int, mf.org.apache.xerces.xs.XSObject):mf.org.apache.xerces.impl.xs.XSParticleDecl
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r9_1 'child' mf.org.w3c.dom.Element) = (r9_0 'child' mf.org.w3c.dom.Element), (r9_5 'child' mf.org.w3c.dom.Element), (r9_6 'child' mf.org.w3c.dom.Element) binds: {(r9_0 'child' mf.org.w3c.dom.Element)=B:4:0x0021, (r9_6 'child' mf.org.w3c.dom.Element)=B:23:0x012c, (r9_5 'child' mf.org.w3c.dom.Element)=B:14:0x00c7} in method: mf.org.apache.xerces.impl.xs.traversers.XSDAbstractParticleTraverser.traverseAll(mf.org.w3c.dom.Element, mf.org.apache.xerces.impl.xs.traversers.XSDocumentInfo, mf.org.apache.xerces.impl.xs.SchemaGrammar, int, mf.org.apache.xerces.xs.XSObject):mf.org.apache.xerces.impl.xs.XSParticleDecl
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:530)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:514)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 15 more

*/

        XSParticleDecl traverseSequence(Element seqDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar, int allContextFlags, XSObject parent) {
            return traverseSeqChoice(seqDecl, schemaDoc, grammar, allContextFlags, false, parent);
        }

        XSParticleDecl traverseChoice(Element choiceDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar, int allContextFlags, XSObject parent) {
            return traverseSeqChoice(choiceDecl, schemaDoc, grammar, allContextFlags, true, parent);
        }

        private XSParticleDecl traverseSeqChoice(Element decl, XSDocumentInfo schemaDoc, SchemaGrammar grammar, int allContextFlags, boolean choice, XSObject parent) {
            Element child;
            XSParticleDecl particle;
            XSObjectList annotations;
            Object[] attrValues = this.fAttrChecker.checkAttributes(decl, false, schemaDoc);
            Element child2 = DOMUtil.getFirstChildElement(decl);
            XSAnnotationImpl annotation = null;
            if (child2 == null || !DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                String text = DOMUtil.getSyntheticAnnotation(decl);
                if (text != null) {
                    annotation = traverseSyntheticAnnotation(decl, text, attrValues, false, schemaDoc);
                    child = child2;
                } else {
                    child = child2;
                }
            } else {
                annotation = traverseAnnotationDecl(child2, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child2);
            }
            this.fPArray.pushContext();
            for (child = 
/*
Method generation error in method: mf.org.apache.xerces.impl.xs.traversers.XSDAbstractParticleTraverser.traverseSeqChoice(mf.org.w3c.dom.Element, mf.org.apache.xerces.impl.xs.traversers.XSDocumentInfo, mf.org.apache.xerces.impl.xs.SchemaGrammar, int, boolean, mf.org.apache.xerces.xs.XSObject):mf.org.apache.xerces.impl.xs.XSParticleDecl
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r9_1 'child' mf.org.w3c.dom.Element) = (r9_0 'child' mf.org.w3c.dom.Element), (r9_5 'child' mf.org.w3c.dom.Element), (r9_6 'child' mf.org.w3c.dom.Element) binds: {(r9_0 'child' mf.org.w3c.dom.Element)=B:4:0x0021, (r9_6 'child' mf.org.w3c.dom.Element)=B:48:0x01c9, (r9_5 'child' mf.org.w3c.dom.Element)=B:20:0x00cb} in method: mf.org.apache.xerces.impl.xs.traversers.XSDAbstractParticleTraverser.traverseSeqChoice(mf.org.w3c.dom.Element, mf.org.apache.xerces.impl.xs.traversers.XSDocumentInfo, mf.org.apache.xerces.impl.xs.SchemaGrammar, int, boolean, mf.org.apache.xerces.xs.XSObject):mf.org.apache.xerces.impl.xs.XSParticleDecl
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:530)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:514)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 15 more

*/

            protected boolean hasAllContent(XSParticleDecl particle) {
                if (particle == null || particle.fType != (short) 3) {
                    return false;
                }
                if (((XSModelGroupImpl) particle.fValue).fCompositor == (short) 103) {
                    return true;
                }
                return false;
            }
        }
