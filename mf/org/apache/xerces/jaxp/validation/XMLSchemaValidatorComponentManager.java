package mf.org.apache.xerces.jaxp.validation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.util.DOMEntityResolverWrapper;
import mf.org.apache.xerces.util.ErrorHandlerWrapper;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.ParserConfigurationSettings;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;

final class XMLSchemaValidatorComponentManager extends ParserConfigurationSettings implements XMLComponentManager {
    private static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    private static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
    private static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
    private static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
    private static final String LOCALE = "http://apache.org/xml/properties/locale";
    private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    private static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    private static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    private static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    private static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private final HashMap fComponents = new HashMap();
    private boolean fConfigUpdated = true;
    private final XMLEntityManager fEntityManager = new XMLEntityManager();
    private ErrorHandler fErrorHandler = null;
    private final XMLErrorReporter fErrorReporter;
    private final HashMap fInitFeatures = new HashMap();
    private final HashMap fInitProperties = new HashMap();
    private final SecurityManager fInitSecurityManager;
    private Locale fLocale = null;
    private final NamespaceContext fNamespaceContext;
    private LSResourceResolver fResourceResolver = null;
    private final XMLSchemaValidator fSchemaValidator;
    private boolean fUseGrammarPoolOnly;
    private final ValidationManager fValidationManager;

    public XMLSchemaValidatorComponentManager(XSGrammarPoolContainer grammarContainer) {
        this.fComponents.put(ENTITY_MANAGER, this.fEntityManager);
        this.fErrorReporter = new XMLErrorReporter();
        this.fComponents.put("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.fNamespaceContext = new NamespaceSupport();
        this.fComponents.put(NAMESPACE_CONTEXT, this.fNamespaceContext);
        this.fSchemaValidator = new XMLSchemaValidator();
        this.fComponents.put(SCHEMA_VALIDATOR, this.fSchemaValidator);
        this.fValidationManager = new ValidationManager();
        this.fComponents.put(VALIDATION_MANAGER, this.fValidationManager);
        this.fComponents.put("http://apache.org/xml/properties/internal/entity-resolver", null);
        this.fComponents.put(ERROR_HANDLER, null);
        this.fComponents.put(SECURITY_MANAGER, null);
        this.fComponents.put("http://apache.org/xml/properties/internal/symbol-table", new SymbolTable());
        this.fComponents.put("http://apache.org/xml/properties/internal/grammar-pool", grammarContainer.getGrammarPool());
        this.fUseGrammarPoolOnly = grammarContainer.isFullyComposed();
        this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
        addRecognizedFeatures(new String[]{DISALLOW_DOCTYPE_DECL_FEATURE, NORMALIZE_DATA, SCHEMA_ELEMENT_DEFAULT, SCHEMA_AUGMENT_PSVI});
        this.fFeatures.put(DISALLOW_DOCTYPE_DECL_FEATURE, Boolean.FALSE);
        this.fFeatures.put(NORMALIZE_DATA, Boolean.FALSE);
        this.fFeatures.put(SCHEMA_ELEMENT_DEFAULT, Boolean.FALSE);
        this.fFeatures.put(SCHEMA_AUGMENT_PSVI, Boolean.TRUE);
        addRecognizedParamsAndSetDefaults(this.fEntityManager, grammarContainer);
        addRecognizedParamsAndSetDefaults(this.fErrorReporter, grammarContainer);
        addRecognizedParamsAndSetDefaults(this.fSchemaValidator, grammarContainer);
        if (Boolean.TRUE.equals(grammarContainer.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING))) {
            this.fInitSecurityManager = new SecurityManager();
        } else {
            this.fInitSecurityManager = null;
        }
        this.fComponents.put(SECURITY_MANAGER, this.fInitSecurityManager);
        this.fFeatures.put(IGNORE_XSI_TYPE, Boolean.FALSE);
        this.fFeatures.put(ID_IDREF_CHECKING, Boolean.TRUE);
        this.fFeatures.put(IDENTITY_CONSTRAINT_CHECKING, Boolean.TRUE);
        this.fFeatures.put(UNPARSED_ENTITY_CHECKING, Boolean.TRUE);
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if ("http://apache.org/xml/features/internal/parser-settings".equals(featureId)) {
            return this.fConfigUpdated;
        }
        if (VALIDATION.equals(featureId) || SCHEMA_VALIDATION.equals(featureId)) {
            return true;
        }
        if (USE_GRAMMAR_POOL_ONLY.equals(featureId)) {
            return this.fUseGrammarPoolOnly;
        }
        if (!XMLConstants.FEATURE_SECURE_PROCESSING.equals(featureId)) {
            return super.getFeature(featureId);
        }
        if (getProperty(SECURITY_MANAGER) == null) {
            return false;
        }
        return true;
    }

    public void setFeature(String featureId, boolean value) throws XMLConfigurationException {
        if ("http://apache.org/xml/features/internal/parser-settings".equals(featureId)) {
            throw new XMLConfigurationException((short) 1, featureId);
        } else if (!value && (VALIDATION.equals(featureId) || SCHEMA_VALIDATION.equals(featureId))) {
            throw new XMLConfigurationException((short) 1, featureId);
        } else if (USE_GRAMMAR_POOL_ONLY.equals(featureId) && value != this.fUseGrammarPoolOnly) {
            throw new XMLConfigurationException((short) 1, featureId);
        } else if (XMLConstants.FEATURE_SECURE_PROCESSING.equals(featureId)) {
            Object securityManager;
            String str = SECURITY_MANAGER;
            if (value) {
                securityManager = new SecurityManager();
            } else {
                securityManager = null;
            }
            setProperty(str, securityManager);
        } else {
            this.fConfigUpdated = true;
            this.fEntityManager.setFeature(featureId, value);
            this.fErrorReporter.setFeature(featureId, value);
            this.fSchemaValidator.setFeature(featureId, value);
            if (!this.fInitFeatures.containsKey(featureId)) {
                this.fInitFeatures.put(featureId, super.getFeature(featureId) ? Boolean.TRUE : Boolean.FALSE);
            }
            super.setFeature(featureId, value);
        }
    }

    public Object getProperty(String propertyId) throws XMLConfigurationException {
        if ("http://apache.org/xml/properties/locale".equals(propertyId)) {
            return getLocale();
        }
        Object component = this.fComponents.get(propertyId);
        if (component != null) {
            return component;
        }
        if (this.fComponents.containsKey(propertyId)) {
            return null;
        }
        return super.getProperty(propertyId);
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (ENTITY_MANAGER.equals(propertyId) || "http://apache.org/xml/properties/internal/error-reporter".equals(propertyId) || NAMESPACE_CONTEXT.equals(propertyId) || SCHEMA_VALIDATOR.equals(propertyId) || "http://apache.org/xml/properties/internal/symbol-table".equals(propertyId) || VALIDATION_MANAGER.equals(propertyId) || "http://apache.org/xml/properties/internal/grammar-pool".equals(propertyId)) {
            throw new XMLConfigurationException((short) 1, propertyId);
        }
        this.fConfigUpdated = true;
        this.fEntityManager.setProperty(propertyId, value);
        this.fErrorReporter.setProperty(propertyId, value);
        this.fSchemaValidator.setProperty(propertyId, value);
        if ("http://apache.org/xml/properties/internal/entity-resolver".equals(propertyId) || ERROR_HANDLER.equals(propertyId) || SECURITY_MANAGER.equals(propertyId)) {
            this.fComponents.put(propertyId, value);
        } else if ("http://apache.org/xml/properties/locale".equals(propertyId)) {
            setLocale((Locale) value);
            this.fComponents.put(propertyId, value);
        } else {
            if (!this.fInitProperties.containsKey(propertyId)) {
                this.fInitProperties.put(propertyId, super.getProperty(propertyId));
            }
            super.setProperty(propertyId, value);
        }
    }

    public void addRecognizedParamsAndSetDefaults(XMLComponent component, XSGrammarPoolContainer grammarContainer) {
        String[] recognizedFeatures = component.getRecognizedFeatures();
        addRecognizedFeatures(recognizedFeatures);
        String[] recognizedProperties = component.getRecognizedProperties();
        addRecognizedProperties(recognizedProperties);
        setFeatureDefaults(component, recognizedFeatures, grammarContainer);
        setPropertyDefaults(component, recognizedProperties);
    }

    public void reset() throws XNIException {
        this.fNamespaceContext.reset();
        this.fValidationManager.reset();
        this.fEntityManager.reset(this);
        this.fErrorReporter.reset(this);
        this.fSchemaValidator.reset(this);
        this.fConfigUpdated = false;
    }

    void setErrorHandler(ErrorHandler errorHandler) {
        Object errorHandlerWrapper;
        this.fErrorHandler = errorHandler;
        String str = ERROR_HANDLER;
        if (errorHandler != null) {
            errorHandlerWrapper = new ErrorHandlerWrapper(errorHandler);
        } else {
            errorHandlerWrapper = new ErrorHandlerWrapper(DraconianErrorHandler.getInstance());
        }
        setProperty(str, errorHandlerWrapper);
    }

    ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    void setResourceResolver(LSResourceResolver resourceResolver) {
        this.fResourceResolver = resourceResolver;
        setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper(resourceResolver));
    }

    LSResourceResolver getResourceResolver() {
        return this.fResourceResolver;
    }

    void setLocale(Locale locale) {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    Locale getLocale() {
        return this.fLocale;
    }

    void restoreInitialState() {
        this.fConfigUpdated = true;
        this.fComponents.put("http://apache.org/xml/properties/internal/entity-resolver", null);
        this.fComponents.put(ERROR_HANDLER, null);
        this.fComponents.put(SECURITY_MANAGER, this.fInitSecurityManager);
        setLocale(null);
        this.fComponents.put("http://apache.org/xml/properties/locale", null);
        if (!this.fInitFeatures.isEmpty()) {
            for (Entry entry : this.fInitFeatures.entrySet()) {
                super.setFeature((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
            }
            this.fInitFeatures.clear();
        }
        if (!this.fInitProperties.isEmpty()) {
            for (Entry entry2 : this.fInitProperties.entrySet()) {
                super.setProperty((String) entry2.getKey(), entry2.getValue());
            }
            this.fInitProperties.clear();
        }
    }

    private void setFeatureDefaults(XMLComponent component, String[] recognizedFeatures, XSGrammarPoolContainer grammarContainer) {
        if (recognizedFeatures != null) {
            for (String featureId : recognizedFeatures) {
                Boolean state = grammarContainer.getFeature(featureId);
                if (state == null) {
                    state = component.getFeatureDefault(featureId);
                }
                if (!(state == null || this.fFeatures.containsKey(featureId))) {
                    this.fFeatures.put(featureId, state);
                    this.fConfigUpdated = true;
                }
            }
        }
    }

    private void setPropertyDefaults(XMLComponent component, String[] recognizedProperties) {
        if (recognizedProperties != null) {
            for (String propertyId : recognizedProperties) {
                Object value = component.getPropertyDefault(propertyId);
                if (!(value == null || this.fProperties.containsKey(propertyId))) {
                    this.fProperties.put(propertyId, value);
                    this.fConfigUpdated = true;
                }
            }
        }
    }
}
