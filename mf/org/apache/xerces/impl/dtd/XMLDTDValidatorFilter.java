package mf.org.apache.xerces.impl.dtd;

import mf.org.apache.xerces.xni.parser.XMLDocumentFilter;

public interface XMLDTDValidatorFilter extends XMLDocumentFilter {
    boolean hasGrammar();

    boolean validate();
}
