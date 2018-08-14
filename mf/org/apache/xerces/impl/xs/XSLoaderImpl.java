package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.XSGrammarPool;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XSGrammar;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xs.LSInputList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSLoader;
import mf.org.apache.xerces.xs.XSModel;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMStringList;
import mf.org.w3c.dom.ls.LSInput;

public final class XSLoaderImpl implements XSLoader, DOMConfiguration {
    private final XSGrammarPool fGrammarPool = new XSGrammarMerger();
    private final XMLSchemaLoader fSchemaLoader = new XMLSchemaLoader();

    private static final class XSGrammarMerger extends XSGrammarPool {
        public void putGrammar(Grammar grammar) {
            SchemaGrammar cachedGrammar = toSchemaGrammar(super.getGrammar(grammar.getGrammarDescription()));
            if (cachedGrammar != null) {
                SchemaGrammar newGrammar = toSchemaGrammar(grammar);
                if (newGrammar != null) {
                    mergeSchemaGrammars(cachedGrammar, newGrammar);
                    return;
                }
                return;
            }
            super.putGrammar(grammar);
        }

        private SchemaGrammar toSchemaGrammar(Grammar grammar) {
            return grammar instanceof SchemaGrammar ? (SchemaGrammar) grammar : null;
        }

        private void mergeSchemaGrammars(SchemaGrammar cachedGrammar, SchemaGrammar newGrammar) {
            int i;
            XSNamedMap map = newGrammar.getComponents((short) 2);
            int length = map.getLength();
            for (i = 0; i < length; i++) {
                XSElementDecl decl = (XSElementDecl) map.item(i);
                if (cachedGrammar.getGlobalElementDecl(decl.getName()) == null) {
                    cachedGrammar.addGlobalElementDecl(decl);
                }
            }
            map = newGrammar.getComponents((short) 1);
            length = map.getLength();
            for (i = 0; i < length; i++) {
                XSAttributeDecl decl2 = (XSAttributeDecl) map.item(i);
                if (cachedGrammar.getGlobalAttributeDecl(decl2.getName()) == null) {
                    cachedGrammar.addGlobalAttributeDecl(decl2);
                }
            }
            map = newGrammar.getComponents((short) 3);
            length = map.getLength();
            for (i = 0; i < length; i++) {
                XSTypeDefinition decl3 = (XSTypeDefinition) map.item(i);
                if (cachedGrammar.getGlobalTypeDecl(decl3.getName()) == null) {
                    cachedGrammar.addGlobalTypeDecl(decl3);
                }
            }
            map = newGrammar.getComponents((short) 5);
            length = map.getLength();
            for (i = 0; i < length; i++) {
                XSAttributeGroupDecl decl4 = (XSAttributeGroupDecl) map.item(i);
                if (cachedGrammar.getGlobalAttributeGroupDecl(decl4.getName()) == null) {
                    cachedGrammar.addGlobalAttributeGroupDecl(decl4);
                }
            }
            map = newGrammar.getComponents((short) 7);
            length = map.getLength();
            for (i = 0; i < length; i++) {
                XSGroupDecl decl5 = (XSGroupDecl) map.item(i);
                if (cachedGrammar.getGlobalGroupDecl(decl5.getName()) == null) {
                    cachedGrammar.addGlobalGroupDecl(decl5);
                }
            }
            map = newGrammar.getComponents((short) 11);
            length = map.getLength();
            for (i = 0; i < length; i++) {
                XSNotationDecl decl6 = (XSNotationDecl) map.item(i);
                if (cachedGrammar.getGlobalNotationDecl(decl6.getName()) == null) {
                    cachedGrammar.addGlobalNotationDecl(decl6);
                }
            }
            XSObjectList annotations = newGrammar.getAnnotations();
            length = annotations.getLength();
            for (i = 0; i < length; i++) {
                cachedGrammar.addAnnotation((XSAnnotationImpl) annotations.item(i));
            }
        }

        public boolean containsGrammar(XMLGrammarDescription desc) {
            return false;
        }

        public Grammar getGrammar(XMLGrammarDescription desc) {
            return null;
        }

        public Grammar retrieveGrammar(XMLGrammarDescription desc) {
            return null;
        }

        public Grammar[] retrieveInitialGrammarSet(String grammarType) {
            return new Grammar[0];
        }
    }

    public XSLoaderImpl() {
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
    }

    public DOMConfiguration getConfig() {
        return this;
    }

    public XSModel loadURIList(StringList uriList) {
        int length = uriList.getLength();
        try {
            this.fGrammarPool.clear();
            for (int i = 0; i < length; i++) {
                this.fSchemaLoader.loadGrammar(new XMLInputSource(null, uriList.item(i), null));
            }
            return this.fGrammarPool.toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    public XSModel loadInputList(LSInputList is) {
        int length = is.getLength();
        try {
            this.fGrammarPool.clear();
            for (int i = 0; i < length; i++) {
                this.fSchemaLoader.loadGrammar(this.fSchemaLoader.dom2xmlInputSource(is.item(i)));
            }
            return this.fGrammarPool.toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    public XSModel loadURI(String uri) {
        try {
            this.fGrammarPool.clear();
            return ((XSGrammar) this.fSchemaLoader.loadGrammar(new XMLInputSource(null, uri, null))).toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    public XSModel load(LSInput is) {
        try {
            this.fGrammarPool.clear();
            return ((XSGrammar) this.fSchemaLoader.loadGrammar(this.fSchemaLoader.dom2xmlInputSource(is))).toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    public void setParameter(String name, Object value) throws DOMException {
        this.fSchemaLoader.setParameter(name, value);
    }

    public Object getParameter(String name) throws DOMException {
        return this.fSchemaLoader.getParameter(name);
    }

    public boolean canSetParameter(String name, Object value) {
        return this.fSchemaLoader.canSetParameter(name, value);
    }

    public DOMStringList getParameterNames() {
        return this.fSchemaLoader.getParameterNames();
    }
}
