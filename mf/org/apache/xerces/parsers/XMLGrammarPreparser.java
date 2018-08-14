package mf.org.apache.xerces.parsers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarLoader;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLGrammarPreparser {
    private static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final Hashtable KNOWN_LOADERS = new Hashtable();
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool"};
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected XMLEntityResolver fEntityResolver;
    protected final XMLErrorReporter fErrorReporter;
    protected XMLGrammarPool fGrammarPool;
    private final Hashtable fLoaders;
    protected Locale fLocale;
    private int fModCount;
    protected final SymbolTable fSymbolTable;

    static class XMLGrammarLoaderContainer {
        public final XMLGrammarLoader loader;
        public int modCount = 0;

        public XMLGrammarLoaderContainer(XMLGrammarLoader loader) {
            this.loader = loader;
        }
    }

    static {
        KNOWN_LOADERS.put("http://www.w3.org/2001/XMLSchema", "mf.org.apache.xerces.impl.xs.XMLSchemaLoader");
        KNOWN_LOADERS.put("http://www.w3.org/TR/REC-xml", "mf.org.apache.xerces.impl.dtd.XMLDTDLoader");
    }

    public XMLGrammarPreparser() {
        this(new SymbolTable());
    }

    public XMLGrammarPreparser(SymbolTable symbolTable) {
        this.fModCount = 1;
        this.fSymbolTable = symbolTable;
        this.fLoaders = new Hashtable();
        this.fErrorReporter = new XMLErrorReporter();
        setLocale(Locale.getDefault());
        this.fEntityResolver = new XMLEntityManager();
    }

    public boolean registerPreparser(String grammarType, XMLGrammarLoader loader) {
        if (loader != null) {
            this.fLoaders.put(grammarType, new XMLGrammarLoaderContainer(loader));
            return true;
        } else if (!KNOWN_LOADERS.containsKey(grammarType)) {
            return false;
        } else {
            try {
                this.fLoaders.put(grammarType, new XMLGrammarLoaderContainer((XMLGrammarLoader) ObjectFactory.newInstance((String) KNOWN_LOADERS.get(grammarType), ObjectFactory.findClassLoader(), true)));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public Grammar preparseGrammar(String type, XMLInputSource is) throws XNIException, IOException {
        if (!this.fLoaders.containsKey(type)) {
            return null;
        }
        XMLGrammarLoaderContainer xglc = (XMLGrammarLoaderContainer) this.fLoaders.get(type);
        XMLGrammarLoader gl = xglc.loader;
        if (xglc.modCount != this.fModCount) {
            gl.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
            gl.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
            gl.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
            if (this.fGrammarPool != null) {
                try {
                    gl.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
                } catch (Exception e) {
                }
            }
            xglc.modCount = this.fModCount;
        }
        return gl.loadGrammar(is);
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    public void setErrorHandler(XMLErrorHandler errorHandler) {
        this.fErrorReporter.setProperty(ERROR_HANDLER, errorHandler);
    }

    public XMLErrorHandler getErrorHandler() {
        return this.fErrorReporter.getErrorHandler();
    }

    public void setEntityResolver(XMLEntityResolver entityResolver) {
        if (this.fEntityResolver != entityResolver) {
            int i = this.fModCount + 1;
            this.fModCount = i;
            if (i < 0) {
                clearModCounts();
            }
            this.fEntityResolver = entityResolver;
        }
    }

    public XMLEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }

    public void setGrammarPool(XMLGrammarPool grammarPool) {
        if (this.fGrammarPool != grammarPool) {
            int i = this.fModCount + 1;
            this.fModCount = i;
            if (i < 0) {
                clearModCounts();
            }
            this.fGrammarPool = grammarPool;
        }
    }

    public XMLGrammarPool getGrammarPool() {
        return this.fGrammarPool;
    }

    public XMLGrammarLoader getLoader(String type) {
        XMLGrammarLoaderContainer xglc = (XMLGrammarLoaderContainer) this.fLoaders.get(type);
        return xglc != null ? xglc.loader : null;
    }

    public void setFeature(String featureId, boolean value) {
        Enumeration loaders = this.fLoaders.elements();
        while (loaders.hasMoreElements()) {
            try {
                ((XMLGrammarLoaderContainer) loaders.nextElement()).loader.setFeature(featureId, value);
            } catch (Exception e) {
            }
        }
        if (featureId.equals(CONTINUE_AFTER_FATAL_ERROR)) {
            this.fErrorReporter.setFeature(CONTINUE_AFTER_FATAL_ERROR, value);
        }
    }

    public void setProperty(String propId, Object value) {
        Enumeration loaders = this.fLoaders.elements();
        while (loaders.hasMoreElements()) {
            try {
                ((XMLGrammarLoaderContainer) loaders.nextElement()).loader.setProperty(propId, value);
            } catch (Exception e) {
            }
        }
    }

    public boolean getFeature(String type, String featureId) {
        return ((XMLGrammarLoaderContainer) this.fLoaders.get(type)).loader.getFeature(featureId);
    }

    public Object getProperty(String type, String propertyId) {
        return ((XMLGrammarLoaderContainer) this.fLoaders.get(type)).loader.getProperty(propertyId);
    }

    private void clearModCounts() {
        Enumeration loaders = this.fLoaders.elements();
        while (loaders.hasMoreElements()) {
            ((XMLGrammarLoaderContainer) loaders.nextElement()).modCount = 0;
        }
        this.fModCount = 1;
    }
}
