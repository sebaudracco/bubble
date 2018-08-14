package mf.org.apache.xerces.jaxp;

import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;

final class SchemaValidatorConfiguration implements XMLComponentManager {
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private final XMLGrammarPool fGrammarPool;
    private final XMLComponentManager fParentComponentManager;
    private final boolean fUseGrammarPoolOnly;
    private final ValidationManager fValidationManager;

    public SchemaValidatorConfiguration(XMLComponentManager parentManager, XSGrammarPoolContainer grammarContainer, ValidationManager validationManager) {
        this.fParentComponentManager = parentManager;
        this.fGrammarPool = grammarContainer.getGrammarPool();
        this.fUseGrammarPoolOnly = grammarContainer.isFullyComposed();
        this.fValidationManager = validationManager;
        try {
            XMLErrorReporter errorReporter = (XMLErrorReporter) this.fParentComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            if (errorReporter != null) {
                errorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
            }
        } catch (XMLConfigurationException e) {
        }
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (PARSER_SETTINGS.equals(featureId)) {
            return this.fParentComponentManager.getFeature(featureId);
        }
        if (VALIDATION.equals(featureId) || SCHEMA_VALIDATION.equals(featureId)) {
            return true;
        }
        if (USE_GRAMMAR_POOL_ONLY.equals(featureId)) {
            return this.fUseGrammarPoolOnly;
        }
        return this.fParentComponentManager.getFeature(featureId);
    }

    public Object getProperty(String propertyId) throws XMLConfigurationException {
        if ("http://apache.org/xml/properties/internal/grammar-pool".equals(propertyId)) {
            return this.fGrammarPool;
        }
        if (VALIDATION_MANAGER.equals(propertyId)) {
            return this.fValidationManager;
        }
        return this.fParentComponentManager.getProperty(propertyId);
    }
}
