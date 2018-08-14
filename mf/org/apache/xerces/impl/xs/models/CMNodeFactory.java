package mf.org.apache.xerces.impl.xs.models;

import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.dtd.models.CMNode;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;

public class CMNodeFactory {
    private static final boolean DEBUG = false;
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final int MULTIPLICITY = 1;
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private XMLErrorReporter fErrorReporter;
    private SecurityManager fSecurityManager = null;
    private int maxNodeLimit;
    private int nodeCount = 0;

    public void reset(XMLComponentManager componentManager) {
        this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        try {
            this.fSecurityManager = (SecurityManager) componentManager.getProperty(SECURITY_MANAGER);
            reset();
        } catch (XMLConfigurationException e) {
            this.fSecurityManager = null;
        }
    }

    public void reset() {
        if (this.fSecurityManager != null) {
            this.maxNodeLimit = this.fSecurityManager.getMaxOccurNodeLimit() * 1;
        }
    }

    public CMNode getCMLeafNode(int type, Object leaf, int id, int position) {
        nodeCountCheck();
        return new XSCMLeaf(type, leaf, id, position);
    }

    public CMNode getCMRepeatingLeafNode(int type, Object leaf, int minOccurs, int maxOccurs, int id, int position) {
        nodeCountCheck();
        return new XSCMRepeatingLeaf(type, leaf, minOccurs, maxOccurs, id, position);
    }

    public CMNode getCMUniOpNode(int type, CMNode childNode) {
        nodeCountCheck();
        return new XSCMUniOp(type, childNode);
    }

    public CMNode getCMBinOpNode(int type, CMNode leftNode, CMNode rightNode) {
        nodeCountCheck();
        return new XSCMBinOp(type, leftNode, rightNode);
    }

    public void nodeCountCheck() {
        if (this.fSecurityManager != null) {
            int i = this.nodeCount;
            this.nodeCount = i + 1;
            if (i > this.maxNodeLimit) {
                this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "maxOccurLimit", new Object[]{new Integer(this.maxNodeLimit)}, (short) 2);
                this.nodeCount = 0;
            }
        }
    }

    public void resetNodeCount() {
        this.nodeCount = 0;
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
            if (suffixLength == Constants.SECURITY_MANAGER_PROPERTY.length() && propertyId.endsWith(Constants.SECURITY_MANAGER_PROPERTY)) {
                this.fSecurityManager = (SecurityManager) value;
                this.maxNodeLimit = this.fSecurityManager != null ? this.fSecurityManager.getMaxOccurNodeLimit() * 1 : 0;
            } else if (suffixLength == Constants.ERROR_REPORTER_PROPERTY.length() && propertyId.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) value;
            }
        }
    }
}
