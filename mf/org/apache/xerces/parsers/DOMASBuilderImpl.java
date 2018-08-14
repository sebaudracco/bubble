package mf.org.apache.xerces.parsers;

import java.util.Vector;
import mf.org.apache.xerces.dom.ASModelImpl;
import mf.org.apache.xerces.dom3.as.ASModel;
import mf.org.apache.xerces.dom3.as.DOMASBuilder;
import mf.org.apache.xerces.dom3.as.DOMASException;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.XSGrammarBucket;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLGrammarPoolImpl;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.w3c.dom.ls.LSInput;

public class DOMASBuilderImpl extends DOMParserImpl implements DOMASBuilder {
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected ASModelImpl fAbstractSchema;
    protected XSGrammarBucket fGrammarBucket;

    public DOMASBuilderImpl() {
        super(new XMLGrammarCachingConfiguration());
    }

    public DOMASBuilderImpl(XMLGrammarCachingConfiguration config) {
        super((XMLParserConfiguration) config);
    }

    public DOMASBuilderImpl(SymbolTable symbolTable) {
        super(new XMLGrammarCachingConfiguration(symbolTable));
    }

    public DOMASBuilderImpl(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        super(new XMLGrammarCachingConfiguration(symbolTable, grammarPool));
    }

    public ASModel getAbstractSchema() {
        return this.fAbstractSchema;
    }

    public void setAbstractSchema(ASModel abstractSchema) {
        this.fAbstractSchema = (ASModelImpl) abstractSchema;
        XMLGrammarPool grammarPool = (XMLGrammarPool) this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        if (grammarPool == null) {
            grammarPool = new XMLGrammarPoolImpl();
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
        }
        if (this.fAbstractSchema != null) {
            initGrammarPool(this.fAbstractSchema, grammarPool);
        }
    }

    public ASModel parseASURI(String uri) throws DOMASException, Exception {
        return parseASInputSource(new XMLInputSource(null, uri, null));
    }

    public ASModel parseASInputSource(LSInput is) throws DOMASException, Exception {
        try {
            return parseASInputSource(dom2xmlInputSource(is));
        } catch (XNIException e) {
            throw e.getException();
        }
    }

    ASModel parseASInputSource(XMLInputSource is) throws Exception {
        if (this.fGrammarBucket == null) {
            this.fGrammarBucket = new XSGrammarBucket();
        }
        initGrammarBucket();
        XMLGrammarCachingConfiguration gramConfig = this.fConfiguration;
        gramConfig.lockGrammarPool();
        SchemaGrammar grammar = gramConfig.parseXMLSchema(is);
        gramConfig.unlockGrammarPool();
        if (grammar == null) {
            return null;
        }
        ASModelImpl newAsModel = new ASModelImpl();
        this.fGrammarBucket.putGrammar(grammar, true);
        addGrammars(newAsModel, this.fGrammarBucket);
        return newAsModel;
    }

    private void initGrammarBucket() {
        this.fGrammarBucket.reset();
        if (this.fAbstractSchema != null) {
            initGrammarBucketRecurse(this.fAbstractSchema);
        }
    }

    private void initGrammarBucketRecurse(ASModelImpl currModel) {
        if (currModel.getGrammar() != null) {
            this.fGrammarBucket.putGrammar(currModel.getGrammar());
        }
        for (int i = 0; i < currModel.getInternalASModels().size(); i++) {
            initGrammarBucketRecurse((ASModelImpl) currModel.getInternalASModels().elementAt(i));
        }
    }

    private void addGrammars(ASModelImpl model, XSGrammarBucket grammarBucket) {
        SchemaGrammar[] grammarList = grammarBucket.getGrammars();
        for (SchemaGrammar grammar : grammarList) {
            ASModelImpl newModel = new ASModelImpl();
            newModel.setGrammar(grammar);
            model.addASModel(newModel);
        }
    }

    private void initGrammarPool(ASModelImpl currModel, XMLGrammarPool grammarPool) {
        Grammar[] grammars = new Grammar[1];
        SchemaGrammar grammar = currModel.getGrammar();
        grammars[0] = grammar;
        if (grammar != null) {
            grammarPool.cacheGrammars(grammars[0].getGrammarDescription().getGrammarType(), grammars);
        }
        Vector modelStore = currModel.getInternalASModels();
        for (int i = 0; i < modelStore.size(); i++) {
            initGrammarPool((ASModelImpl) modelStore.elementAt(i), grammarPool);
        }
    }
}
