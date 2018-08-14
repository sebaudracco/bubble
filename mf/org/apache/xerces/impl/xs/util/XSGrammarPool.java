package mf.org.apache.xerces.impl.xs.util;

import java.util.ArrayList;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.XSModelImpl;
import mf.org.apache.xerces.util.XMLGrammarPoolImpl;
import mf.org.apache.xerces.xs.XSModel;

public class XSGrammarPool extends XMLGrammarPoolImpl {
    public XSModel toXSModel() {
        return toXSModel((short) 1);
    }

    public XSModel toXSModel(short schemaVersion) {
        ArrayList list = new ArrayList();
        for (Entry entry : this.fGrammars) {
            for (Entry entry2 = this.fGrammars[i]; entry2 != null; entry2 = entry2.next) {
                if (entry2.desc.getGrammarType().equals("http://www.w3.org/2001/XMLSchema")) {
                    list.add(entry2.grammar);
                }
            }
        }
        int size = list.size();
        if (size == 0) {
            return toXSModel(new SchemaGrammar[0], schemaVersion);
        }
        return toXSModel((SchemaGrammar[]) list.toArray(new SchemaGrammar[size]), schemaVersion);
    }

    protected XSModel toXSModel(SchemaGrammar[] grammars, short schemaVersion) {
        return new XSModelImpl(grammars, schemaVersion);
    }
}
