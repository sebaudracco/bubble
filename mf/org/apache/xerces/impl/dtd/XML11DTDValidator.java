package mf.org.apache.xerces.impl.dtd;

import mf.org.apache.xerces.xni.parser.XMLComponentManager;

public class XML11DTDValidator extends XMLDTDValidator {
    protected static final String DTD_VALIDATOR_PROPERTY = "http://apache.org/xml/properties/internal/validator/dtd";

    public void reset(XMLComponentManager manager) {
        XMLDTDValidator curr = (XMLDTDValidator) manager.getProperty(DTD_VALIDATOR_PROPERTY);
        if (!(curr == null || curr == this)) {
            this.fGrammarBucket = curr.getGrammarBucket();
        }
        super.reset(manager);
    }

    protected void init() {
        if (this.fValidation || this.fDynamicValidation) {
            super.init();
            try {
                this.fValID = this.fDatatypeValidatorFactory.getBuiltInDV("XML11ID");
                this.fValIDRef = this.fDatatypeValidatorFactory.getBuiltInDV("XML11IDREF");
                this.fValIDRefs = this.fDatatypeValidatorFactory.getBuiltInDV("XML11IDREFS");
                this.fValNMTOKEN = this.fDatatypeValidatorFactory.getBuiltInDV("XML11NMTOKEN");
                this.fValNMTOKENS = this.fDatatypeValidatorFactory.getBuiltInDV("XML11NMTOKENS");
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
