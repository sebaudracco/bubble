package mf.org.apache.xerces.impl.xs.util;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xs.XSObject;

public final class XSInputSource extends XMLInputSource {
    private XSObject[] fComponents;
    private SchemaGrammar[] fGrammars;

    public XSInputSource(SchemaGrammar[] grammars) {
        super(null, null, null);
        this.fGrammars = grammars;
        this.fComponents = null;
    }

    public XSInputSource(XSObject[] component) {
        super(null, null, null);
        this.fGrammars = null;
        this.fComponents = component;
    }

    public SchemaGrammar[] getGrammars() {
        return this.fGrammars;
    }

    public void setGrammars(SchemaGrammar[] grammars) {
        this.fGrammars = grammars;
    }

    public XSObject[] getComponents() {
        return this.fComponents;
    }

    public void setComponents(XSObject[] components) {
        this.fComponents = components;
    }
}
