package mf.org.apache.xerces.jaxp.validation;

import java.util.HashMap;
import mf.javax.xml.validation.Schema;
import mf.javax.xml.validation.Validator;
import mf.javax.xml.validation.ValidatorHandler;

abstract class AbstractXMLSchema extends Schema implements XSGrammarPoolContainer {
    private final HashMap fFeatures = new HashMap();

    public final Validator newValidator() {
        return new ValidatorImpl(this);
    }

    public final ValidatorHandler newValidatorHandler() {
        return new ValidatorHandlerImpl((XSGrammarPoolContainer) this);
    }

    public final Boolean getFeature(String featureId) {
        return (Boolean) this.fFeatures.get(featureId);
    }

    final void setFeature(String featureId, boolean state) {
        this.fFeatures.put(featureId, state ? Boolean.TRUE : Boolean.FALSE);
    }
}
