package mf.org.apache.xerces.impl.xs;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class XSGrammarBucket {
    Hashtable fGrammarRegistry = new Hashtable();
    SchemaGrammar fNoNSGrammar = null;

    public SchemaGrammar getGrammar(String namespace) {
        if (namespace == null) {
            return this.fNoNSGrammar;
        }
        return (SchemaGrammar) this.fGrammarRegistry.get(namespace);
    }

    public void putGrammar(SchemaGrammar grammar) {
        if (grammar.getTargetNamespace() == null) {
            this.fNoNSGrammar = grammar;
        } else {
            this.fGrammarRegistry.put(grammar.getTargetNamespace(), grammar);
        }
    }

    public boolean putGrammar(SchemaGrammar grammar, boolean deep) {
        SchemaGrammar sg = getGrammar(grammar.fTargetNamespace);
        if (sg != null) {
            if (sg == grammar) {
                return true;
            }
            return false;
        } else if (deep) {
            Vector currGrammars = grammar.getImportedGrammars();
            if (currGrammars == null) {
                putGrammar(grammar);
                return true;
            }
            int i;
            Vector grammars = (Vector) currGrammars.clone();
            for (i = 0; i < grammars.size(); i++) {
                SchemaGrammar sg1 = (SchemaGrammar) grammars.elementAt(i);
                SchemaGrammar sg2 = getGrammar(sg1.fTargetNamespace);
                if (sg2 == null) {
                    Vector gs = sg1.getImportedGrammars();
                    if (gs != null) {
                        for (int j = gs.size() - 1; j >= 0; j--) {
                            sg2 = (SchemaGrammar) gs.elementAt(j);
                            if (!grammars.contains(sg2)) {
                                grammars.addElement(sg2);
                            }
                        }
                    }
                } else if (sg2 != sg1) {
                    return false;
                }
            }
            putGrammar(grammar);
            for (i = grammars.size() - 1; i >= 0; i--) {
                putGrammar((SchemaGrammar) grammars.elementAt(i));
            }
            return true;
        } else {
            putGrammar(grammar);
            return true;
        }
    }

    public boolean putGrammar(SchemaGrammar grammar, boolean deep, boolean ignoreConflict) {
        if (!ignoreConflict) {
            return putGrammar(grammar, deep);
        }
        if (getGrammar(grammar.fTargetNamespace) == null) {
            putGrammar(grammar);
        }
        if (!deep) {
            return true;
        }
        Vector currGrammars = grammar.getImportedGrammars();
        if (currGrammars == null) {
            return true;
        }
        int i;
        Vector grammars = (Vector) currGrammars.clone();
        for (i = 0; i < grammars.size(); i++) {
            SchemaGrammar sg1 = (SchemaGrammar) grammars.elementAt(i);
            if (getGrammar(sg1.fTargetNamespace) == null) {
                Vector gs = sg1.getImportedGrammars();
                if (gs != null) {
                    for (int j = gs.size() - 1; j >= 0; j--) {
                        SchemaGrammar sg2 = (SchemaGrammar) gs.elementAt(j);
                        if (!grammars.contains(sg2)) {
                            grammars.addElement(sg2);
                        }
                    }
                }
            } else {
                grammars.remove(sg1);
            }
        }
        for (i = grammars.size() - 1; i >= 0; i--) {
            putGrammar((SchemaGrammar) grammars.elementAt(i));
        }
        return true;
    }

    public SchemaGrammar[] getGrammars() {
        int count = this.fGrammarRegistry.size() + (this.fNoNSGrammar == null ? 0 : 1);
        SchemaGrammar[] grammars = new SchemaGrammar[count];
        Enumeration schemas = this.fGrammarRegistry.elements();
        int i = 0;
        while (schemas.hasMoreElements()) {
            int i2 = i + 1;
            grammars[i] = (SchemaGrammar) schemas.nextElement();
            i = i2;
        }
        if (this.fNoNSGrammar != null) {
            grammars[count - 1] = this.fNoNSGrammar;
        }
        return grammars;
    }

    public void reset() {
        this.fNoNSGrammar = null;
        this.fGrammarRegistry.clear();
    }
}
