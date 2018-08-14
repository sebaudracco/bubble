package mf.org.apache.xerces.dom3.as;

import mf.org.w3c.dom.DOMException;

public interface DocumentAS {
    void addAS(ASModel aSModel);

    ASModel getActiveASModel();

    ASObjectList getBoundASModels();

    ASElementDeclaration getElementDeclaration() throws DOMException;

    ASModel getInternalAS();

    void removeAS(ASModel aSModel);

    void setActiveASModel(ASModel aSModel);

    void setBoundASModels(ASObjectList aSObjectList);

    void setInternalAS(ASModel aSModel);

    void validate() throws DOMASException;
}
