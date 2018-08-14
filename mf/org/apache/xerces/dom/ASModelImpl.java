package mf.org.apache.xerces.dom;

import java.util.Vector;
import mf.org.apache.xerces.dom3.as.ASAttributeDeclaration;
import mf.org.apache.xerces.dom3.as.ASContentModel;
import mf.org.apache.xerces.dom3.as.ASElementDeclaration;
import mf.org.apache.xerces.dom3.as.ASEntityDeclaration;
import mf.org.apache.xerces.dom3.as.ASModel;
import mf.org.apache.xerces.dom3.as.ASNamedObjectMap;
import mf.org.apache.xerces.dom3.as.ASNotationDeclaration;
import mf.org.apache.xerces.dom3.as.ASObject;
import mf.org.apache.xerces.dom3.as.ASObjectList;
import mf.org.apache.xerces.dom3.as.DOMASException;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.w3c.dom.DOMException;

public class ASModelImpl implements ASModel {
    protected Vector fASModels = new Vector();
    protected SchemaGrammar fGrammar = null;
    boolean fNamespaceAware = true;

    public ASModelImpl(boolean isNamespaceAware) {
        this.fNamespaceAware = isNamespaceAware;
    }

    public short getAsNodeType() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASModel getOwnerASModel() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setOwnerASModel(ASModel ownerASModel) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public String getNodeName() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setNodeName(String nodeName) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public String getPrefix() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setPrefix(String prefix) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public String getLocalName() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setLocalName(String localName) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public String getNamespaceURI() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setNamespaceURI(String namespaceURI) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASObject cloneASObject(boolean deep) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public boolean getIsNamespaceAware() {
        return this.fNamespaceAware;
    }

    public short getUsageLocation() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public String getAsLocation() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setAsLocation(String asLocation) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public String getAsHint() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setAsHint(String asHint) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public boolean getContainer() {
        return this.fGrammar != null;
    }

    public ASNamedObjectMap getElementDeclarations() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASNamedObjectMap getAttributeDeclarations() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASNamedObjectMap getNotationDeclarations() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASNamedObjectMap getEntityDeclarations() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASNamedObjectMap getContentModelDeclarations() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void addASModel(ASModel abstractSchema) {
        this.fASModels.addElement(abstractSchema);
    }

    public ASObjectList getASModels() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void removeAS(ASModel as) {
        this.fASModels.removeElement(as);
    }

    public boolean validate() {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void importASObject(ASObject asobject) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void insertASObject(ASObject asobject) {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASElementDeclaration createASElementDeclaration(String namespaceURI, String name) throws DOMException {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASAttributeDeclaration createASAttributeDeclaration(String namespaceURI, String name) throws DOMException {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASNotationDeclaration createASNotationDeclaration(String namespaceURI, String name, String systemId, String publicId) throws DOMException {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASEntityDeclaration createASEntityDeclaration(String name) throws DOMException {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public ASContentModel createASContentModel(int minOccurs, int maxOccurs, short operator) throws DOMASException {
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public SchemaGrammar getGrammar() {
        return this.fGrammar;
    }

    public void setGrammar(SchemaGrammar grammar) {
        this.fGrammar = grammar;
    }

    public Vector getInternalASModels() {
        return this.fASModels;
    }
}
