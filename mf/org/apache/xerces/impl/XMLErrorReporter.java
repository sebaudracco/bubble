package mf.org.apache.xerces.impl;

import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.util.DefaultErrorHandler;
import mf.org.apache.xerces.util.ErrorHandlerProxy;
import mf.org.apache.xerces.util.MessageFormatter;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;

public class XMLErrorReporter implements XMLComponent {
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final Boolean[] FEATURE_DEFAULTS = new Boolean[1];
    private static final Object[] PROPERTY_DEFAULTS = new Object[1];
    private static final String[] RECOGNIZED_FEATURES = new String[]{CONTINUE_AFTER_FATAL_ERROR};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{ERROR_HANDLER};
    public static final short SEVERITY_ERROR = (short) 1;
    public static final short SEVERITY_FATAL_ERROR = (short) 2;
    public static final short SEVERITY_WARNING = (short) 0;
    protected boolean fContinueAfterFatalError;
    protected XMLErrorHandler fDefaultErrorHandler;
    protected XMLErrorHandler fErrorHandler;
    protected Locale fLocale;
    protected XMLLocator fLocator;
    protected Hashtable fMessageFormatters = new Hashtable();
    private ErrorHandler fSaxProxy = null;

    class C46281 extends ErrorHandlerProxy {
        C46281() {
        }

        protected XMLErrorHandler getErrorHandler() {
            return XMLErrorReporter.this.fErrorHandler;
        }
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    public void setDocumentLocator(XMLLocator locator) {
        this.fLocator = locator;
    }

    public void putMessageFormatter(String domain, MessageFormatter messageFormatter) {
        this.fMessageFormatters.put(domain, messageFormatter);
    }

    public MessageFormatter getMessageFormatter(String domain) {
        return (MessageFormatter) this.fMessageFormatters.get(domain);
    }

    public MessageFormatter removeMessageFormatter(String domain) {
        return (MessageFormatter) this.fMessageFormatters.remove(domain);
    }

    public String reportError(String domain, String key, Object[] arguments, short severity) throws XNIException {
        return reportError(this.fLocator, domain, key, arguments, severity);
    }

    public String reportError(String domain, String key, Object[] arguments, short severity, Exception exception) throws XNIException {
        return reportError(this.fLocator, domain, key, arguments, severity, exception);
    }

    public String reportError(XMLLocator location, String domain, String key, Object[] arguments, short severity) throws XNIException {
        return reportError(location, domain, key, arguments, severity, null);
    }

    public String reportError(XMLLocator location, String domain, String key, Object[] arguments, short severity, Exception exception) throws XNIException {
        String message;
        XMLParseException parseException;
        MessageFormatter messageFormatter = getMessageFormatter(domain);
        if (messageFormatter != null) {
            message = messageFormatter.formatMessage(this.fLocale, key, arguments);
        } else {
            StringBuffer str = new StringBuffer();
            str.append(domain);
            str.append('#');
            str.append(key);
            int argCount = arguments != null ? arguments.length : 0;
            if (argCount > 0) {
                str.append('?');
                for (int i = 0; i < argCount; i++) {
                    str.append(arguments[i]);
                    if (i < argCount - 1) {
                        str.append('&');
                    }
                }
            }
            message = str.toString();
        }
        if (exception != null) {
            parseException = new XMLParseException(location, message, exception);
        } else {
            parseException = new XMLParseException(location, message);
        }
        XMLErrorHandler errorHandler = this.fErrorHandler;
        if (errorHandler == null) {
            if (this.fDefaultErrorHandler == null) {
                this.fDefaultErrorHandler = new DefaultErrorHandler();
            }
            errorHandler = this.fDefaultErrorHandler;
        }
        switch (severity) {
            case (short) 0:
                errorHandler.warning(domain, key, parseException);
                break;
            case (short) 1:
                errorHandler.error(domain, key, parseException);
                break;
            case (short) 2:
                errorHandler.fatalError(domain, key, parseException);
                if (!this.fContinueAfterFatalError) {
                    throw parseException;
                }
                break;
        }
        return message;
    }

    public void reset(XMLComponentManager componentManager) throws XNIException {
        try {
            this.fContinueAfterFatalError = componentManager.getFeature(CONTINUE_AFTER_FATAL_ERROR);
        } catch (XNIException e) {
            this.fContinueAfterFatalError = false;
        }
        this.fErrorHandler = (XMLErrorHandler) componentManager.getProperty(ERROR_HANDLER);
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX) && featureId.length() - Constants.XERCES_FEATURE_PREFIX.length() == Constants.CONTINUE_AFTER_FATAL_ERROR_FEATURE.length() && featureId.endsWith(Constants.CONTINUE_AFTER_FATAL_ERROR_FEATURE)) {
            this.fContinueAfterFatalError = state;
        }
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX) && featureId.length() - Constants.XERCES_FEATURE_PREFIX.length() == Constants.CONTINUE_AFTER_FATAL_ERROR_FEATURE.length() && featureId.endsWith(Constants.CONTINUE_AFTER_FATAL_ERROR_FEATURE)) {
            return this.fContinueAfterFatalError;
        }
        return false;
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX) && propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length() == Constants.ERROR_HANDLER_PROPERTY.length() && propertyId.endsWith(Constants.ERROR_HANDLER_PROPERTY)) {
            this.fErrorHandler = (XMLErrorHandler) value;
        }
    }

    public Boolean getFeatureDefault(String featureId) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(featureId)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }

    public XMLErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    public ErrorHandler getSAXErrorHandler() {
        if (this.fSaxProxy == null) {
            this.fSaxProxy = new C46281();
        }
        return this.fSaxProxy;
    }
}
