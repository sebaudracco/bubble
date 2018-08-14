package mf.org.apache.xerces.impl;

import java.io.EOFException;
import java.io.IOException;
import mf.org.apache.xerces.impl.dtd.XMLDTDDescription;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDTDScanner;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDocumentScannerImpl extends XMLDocumentFragmentScannerImpl {
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    private static final Boolean[] FEATURE_DEFAULTS = new Boolean[]{Boolean.TRUE, Boolean.FALSE};
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    private static final Object[] PROPERTY_DEFAULTS = new Object[3];
    private static final String[] RECOGNIZED_FEATURES = new String[]{LOAD_EXTERNAL_DTD, DISALLOW_DOCTYPE_DECL_FEATURE};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{DTD_SCANNER, VALIDATION_MANAGER, NAMESPACE_CONTEXT};
    protected static final int SCANNER_STATE_DTD_EXTERNAL = 18;
    protected static final int SCANNER_STATE_DTD_EXTERNAL_DECLS = 19;
    protected static final int SCANNER_STATE_DTD_INTERNAL_DECLS = 17;
    protected static final int SCANNER_STATE_PROLOG = 5;
    protected static final int SCANNER_STATE_TRAILING_MISC = 12;
    protected static final int SCANNER_STATE_XML_DECL = 0;
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private final XMLDTDDescription fDTDDescription = new XMLDTDDescription(null, null, null, null, null);
    protected final Dispatcher fDTDDispatcher = new DTDDispatcher();
    protected XMLDTDScanner fDTDScanner;
    protected boolean fDisallowDoctype = false;
    protected String fDoctypeName;
    protected String fDoctypePublicId;
    protected String fDoctypeSystemId;
    private XMLInputSource fExternalSubsetSource = null;
    protected boolean fLoadExternalDTD = true;
    protected NamespaceContext fNamespaceContext = new NamespaceSupport();
    protected final Dispatcher fPrologDispatcher = new PrologDispatcher();
    protected boolean fScanningDTD;
    protected boolean fSeenDoctypeDecl;
    private final XMLString fString = new XMLString();
    private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    private final String[] fStrings = new String[3];
    protected final Dispatcher fTrailingMiscDispatcher = new TrailingMiscDispatcher();
    protected ValidationManager fValidationManager;
    protected final Dispatcher fXMLDeclDispatcher = new XMLDeclDispatcher();

    protected class ContentDispatcher extends FragmentContentDispatcher {
        protected ContentDispatcher() {
            super();
        }

        protected boolean scanForDoctypeHook() throws IOException, XNIException {
            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipString("DOCTYPE")) {
                return false;
            }
            XMLDocumentScannerImpl.this.setScannerState(4);
            return true;
        }

        protected boolean elementDepthIsZeroHook() throws IOException, XNIException {
            XMLDocumentScannerImpl.this.setScannerState(12);
            XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fTrailingMiscDispatcher);
            return true;
        }

        protected boolean scanRootElementHook() throws IOException, XNIException {
            if (XMLDocumentScannerImpl.this.fExternalSubsetResolver != null && !XMLDocumentScannerImpl.this.fSeenDoctypeDecl && !XMLDocumentScannerImpl.this.fDisallowDoctype && (XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD)) {
                XMLDocumentScannerImpl.this.scanStartElementName();
                resolveExternalSubsetAndRead();
                if (XMLDocumentScannerImpl.this.scanStartElementAfterName()) {
                    XMLDocumentScannerImpl.this.setScannerState(12);
                    XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fTrailingMiscDispatcher);
                    return true;
                }
            } else if (XMLDocumentScannerImpl.this.scanStartElement()) {
                XMLDocumentScannerImpl.this.setScannerState(12);
                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fTrailingMiscDispatcher);
                return true;
            }
            return false;
        }

        protected void endOfFileHook(EOFException e) throws IOException, XNIException {
            XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
        }

        protected void resolveExternalSubsetAndRead() throws IOException, XNIException {
            XMLDocumentScannerImpl.this.fDTDDescription.setValues(null, null, XMLDocumentScannerImpl.this.fEntityManager.getCurrentResourceIdentifier().getExpandedSystemId(), null);
            XMLDocumentScannerImpl.this.fDTDDescription.setRootName(XMLDocumentScannerImpl.this.fElementQName.rawname);
            XMLInputSource src = XMLDocumentScannerImpl.this.fExternalSubsetResolver.getExternalSubset(XMLDocumentScannerImpl.this.fDTDDescription);
            if (src != null) {
                XMLDocumentScannerImpl.this.fDoctypeName = XMLDocumentScannerImpl.this.fElementQName.rawname;
                XMLDocumentScannerImpl.this.fDoctypePublicId = src.getPublicId();
                XMLDocumentScannerImpl.this.fDoctypeSystemId = src.getSystemId();
                if (XMLDocumentScannerImpl.this.fDocumentHandler != null) {
                    XMLDocumentScannerImpl.this.fDocumentHandler.doctypeDecl(XMLDocumentScannerImpl.this.fDoctypeName, XMLDocumentScannerImpl.this.fDoctypePublicId, XMLDocumentScannerImpl.this.fDoctypeSystemId, null);
                }
                try {
                    if (XMLDocumentScannerImpl.this.fValidationManager == null || !XMLDocumentScannerImpl.this.fValidationManager.isCachedDTD()) {
                        XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(src);
                        do {
                        } while (XMLDocumentScannerImpl.this.fDTDScanner.scanDTDExternalSubset(true));
                    } else {
                        XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(null);
                    }
                    XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                } catch (Throwable th) {
                    XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                }
            }
        }
    }

    protected final class DTDDispatcher implements Dispatcher {
        protected DTDDispatcher() {
        }

        public boolean dispatch(boolean complete) throws IOException, XNIException {
            XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(null);
            while (true) {
                boolean again = false;
                try {
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 17:
                            boolean readExternalSubset;
                            boolean z;
                            if ((XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD) && (XMLDocumentScannerImpl.this.fValidationManager == null || !XMLDocumentScannerImpl.this.fValidationManager.isCachedDTD())) {
                                readExternalSubset = true;
                            } else {
                                readExternalSubset = false;
                            }
                            XMLDTDScanner xMLDTDScanner = XMLDocumentScannerImpl.this.fDTDScanner;
                            boolean z2 = XMLDocumentScannerImpl.this.fStandalone;
                            if (XMLDocumentScannerImpl.this.fHasExternalDTD && readExternalSubset) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (!xMLDTDScanner.scanDTDInternalSubset(true, z2, z)) {
                                if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(93)) {
                                    XMLDocumentScannerImpl.this.reportFatalError("EXPECTED_SQUARE_BRACKET_TO_CLOSE_INTERNAL_SUBSET", null);
                                }
                                XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                                if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(62)) {
                                    XMLDocumentScannerImpl.this.reportFatalError("DoctypedeclUnterminated", new Object[]{XMLDocumentScannerImpl.this.fDoctypeName});
                                }
                                XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
                                xMLDocumentScannerImpl.fMarkupDepth--;
                                XMLDocumentScannerImpl xMLDocumentScannerImpl2;
                                if (XMLDocumentScannerImpl.this.fDoctypeSystemId != null) {
                                    xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                                    if (XMLDocumentScannerImpl.this.fStandalone) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    xMLDocumentScannerImpl2.fIsEntityDeclaredVC = z;
                                    if (readExternalSubset) {
                                        XMLDocumentScannerImpl.this.setScannerState(18);
                                        break;
                                    }
                                } else if (XMLDocumentScannerImpl.this.fExternalSubsetSource != null) {
                                    xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                                    if (XMLDocumentScannerImpl.this.fStandalone) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    xMLDocumentScannerImpl2.fIsEntityDeclaredVC = z;
                                    if (readExternalSubset) {
                                        XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(XMLDocumentScannerImpl.this.fExternalSubsetSource);
                                        XMLDocumentScannerImpl.this.fExternalSubsetSource = null;
                                        XMLDocumentScannerImpl.this.setScannerState(19);
                                        break;
                                    }
                                } else {
                                    xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                                    z = XMLDocumentScannerImpl.this.fEntityManager.hasPEReferences() && !XMLDocumentScannerImpl.this.fStandalone;
                                    xMLDocumentScannerImpl2.fIsEntityDeclaredVC = z;
                                }
                                XMLDocumentScannerImpl.this.setScannerState(5);
                                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fPrologDispatcher);
                                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                return true;
                            }
                            break;
                        case 18:
                            XMLDocumentScannerImpl.this.fDTDDescription.setValues(XMLDocumentScannerImpl.this.fDoctypePublicId, XMLDocumentScannerImpl.this.fDoctypeSystemId, null, null);
                            XMLDocumentScannerImpl.this.fDTDDescription.setRootName(XMLDocumentScannerImpl.this.fDoctypeName);
                            XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(XMLDocumentScannerImpl.this.fEntityManager.resolveEntity(XMLDocumentScannerImpl.this.fDTDDescription));
                            XMLDocumentScannerImpl.this.setScannerState(19);
                            again = true;
                            break;
                        case 19:
                            if (!XMLDocumentScannerImpl.this.fDTDScanner.scanDTDExternalSubset(true)) {
                                XMLDocumentScannerImpl.this.setScannerState(5);
                                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fPrologDispatcher);
                                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                return true;
                            }
                            break;
                        default:
                            throw new XNIException("DTDDispatcher#dispatch: scanner state=" + XMLDocumentScannerImpl.this.fScannerState + " (" + XMLDocumentScannerImpl.this.getScannerStateName(XMLDocumentScannerImpl.this.fScannerState) + ')');
                    }
                    if (!complete && !again) {
                        return true;
                    }
                } catch (Exception e) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, e);
                    return false;
                } catch (Exception e2) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short) 2, e2);
                    return false;
                } catch (EOFException e3) {
                    XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                    return false;
                } finally {
                    XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                }
            }
        }
    }

    protected final class PrologDispatcher implements Dispatcher {
        protected PrologDispatcher() {
        }

        public boolean dispatch(boolean complete) throws IOException, XNIException {
            while (true) {
                boolean again = false;
                try {
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 1:
                            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
                            xMLDocumentScannerImpl.fMarkupDepth++;
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                if (!XMLDocumentScannerImpl.this.isValidNameStartChar(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                    if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                        if (!XMLDocumentScannerImpl.this.isValidNameStartHighSurrogate(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                            XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInProlog", null);
                                            break;
                                        }
                                        XMLDocumentScannerImpl.this.setScannerState(6);
                                        XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fContentDispatcher);
                                        return true;
                                    }
                                    XMLDocumentScannerImpl.this.setScannerState(3);
                                    again = true;
                                    break;
                                }
                                XMLDocumentScannerImpl.this.setScannerState(6);
                                XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fContentDispatcher);
                                return true;
                            } else if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                if (!XMLDocumentScannerImpl.this.fEntityScanner.skipString("DOCTYPE")) {
                                    XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInProlog", null);
                                    break;
                                }
                                XMLDocumentScannerImpl.this.setScannerState(4);
                                again = true;
                                break;
                            } else {
                                if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(45)) {
                                    XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                                }
                                XMLDocumentScannerImpl.this.setScannerState(2);
                                again = true;
                                break;
                            }
                        case 2:
                            XMLDocumentScannerImpl.this.scanComment();
                            XMLDocumentScannerImpl.this.setScannerState(5);
                            break;
                        case 3:
                            XMLDocumentScannerImpl.this.scanPI();
                            XMLDocumentScannerImpl.this.setScannerState(5);
                            break;
                        case 4:
                            if (XMLDocumentScannerImpl.this.fDisallowDoctype) {
                                XMLDocumentScannerImpl.this.reportFatalError("DoctypeNotAllowed", null);
                            }
                            if (XMLDocumentScannerImpl.this.fSeenDoctypeDecl) {
                                XMLDocumentScannerImpl.this.reportFatalError("AlreadySeenDoctype", null);
                            }
                            XMLDocumentScannerImpl.this.fSeenDoctypeDecl = true;
                            if (!XMLDocumentScannerImpl.this.scanDoctypeDecl()) {
                                if (XMLDocumentScannerImpl.this.fDoctypeSystemId != null) {
                                    boolean z;
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                                    if (XMLDocumentScannerImpl.this.fStandalone) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    xMLDocumentScannerImpl2.fIsEntityDeclaredVC = z;
                                    if ((XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD) && (XMLDocumentScannerImpl.this.fValidationManager == null || !XMLDocumentScannerImpl.this.fValidationManager.isCachedDTD())) {
                                        XMLDocumentScannerImpl.this.setScannerState(18);
                                        XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fDTDDispatcher);
                                        return true;
                                    }
                                } else if (XMLDocumentScannerImpl.this.fExternalSubsetSource != null) {
                                    XMLDocumentScannerImpl.this.fIsEntityDeclaredVC = !XMLDocumentScannerImpl.this.fStandalone;
                                    if ((XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD) && (XMLDocumentScannerImpl.this.fValidationManager == null || !XMLDocumentScannerImpl.this.fValidationManager.isCachedDTD())) {
                                        XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(XMLDocumentScannerImpl.this.fExternalSubsetSource);
                                        XMLDocumentScannerImpl.this.fExternalSubsetSource = null;
                                        XMLDocumentScannerImpl.this.setScannerState(19);
                                        XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fDTDDispatcher);
                                        return true;
                                    }
                                }
                                XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(null);
                                XMLDocumentScannerImpl.this.setScannerState(5);
                                break;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(17);
                            XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fDTDDispatcher);
                            return true;
                        case 5:
                            XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(38)) {
                                    XMLDocumentScannerImpl.this.setScannerState(7);
                                    again = true;
                                    break;
                                }
                                XMLDocumentScannerImpl.this.setScannerState(8);
                                again = true;
                                break;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(1);
                            again = true;
                            break;
                        case 7:
                            XMLDocumentScannerImpl.this.reportFatalError("ContentIllegalInProlog", null);
                            XMLDocumentScannerImpl.this.fEntityScanner.scanChar();
                            break;
                        case 8:
                            break;
                    }
                    XMLDocumentScannerImpl.this.reportFatalError("ReferenceIllegalInProlog", null);
                    if (!complete && !again) {
                        if (!complete) {
                            return true;
                        }
                        if (XMLDocumentScannerImpl.this.fEntityScanner.scanChar() != 60) {
                            XMLDocumentScannerImpl.this.reportFatalError("RootElementRequired", null);
                        }
                        XMLDocumentScannerImpl.this.setScannerState(6);
                        XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fContentDispatcher);
                        return true;
                    }
                } catch (Exception e) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, e);
                    return false;
                } catch (Exception e2) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short) 2, e2);
                    return false;
                } catch (EOFException e3) {
                    XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                    return false;
                }
            }
        }
    }

    protected final class TrailingMiscDispatcher implements Dispatcher {
        protected TrailingMiscDispatcher() {
        }

        public boolean dispatch(boolean complete) throws IOException, XNIException {
            while (true) {
                boolean again = false;
                try {
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 1:
                            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
                            xMLDocumentScannerImpl.fMarkupDepth++;
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                    if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(47)) {
                                        if (!XMLDocumentScannerImpl.this.isValidNameStartChar(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                            if (!XMLDocumentScannerImpl.this.isValidNameStartHighSurrogate(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                                break;
                                            }
                                            XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                            XMLDocumentScannerImpl.this.scanStartElement();
                                            XMLDocumentScannerImpl.this.setScannerState(7);
                                            break;
                                        }
                                        XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                        XMLDocumentScannerImpl.this.scanStartElement();
                                        XMLDocumentScannerImpl.this.setScannerState(7);
                                        break;
                                    }
                                    XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                    again = true;
                                    break;
                                }
                                XMLDocumentScannerImpl.this.setScannerState(2);
                                again = true;
                                break;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(3);
                            again = true;
                            break;
                        case 2:
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipString("--")) {
                                XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                            }
                            XMLDocumentScannerImpl.this.scanComment();
                            XMLDocumentScannerImpl.this.setScannerState(12);
                            break;
                        case 3:
                            XMLDocumentScannerImpl.this.scanPI();
                            XMLDocumentScannerImpl.this.setScannerState(12);
                            break;
                        case 7:
                            if (XMLDocumentScannerImpl.this.fEntityScanner.peekChar() != -1) {
                                XMLDocumentScannerImpl.this.reportFatalError("ContentIllegalInTrailingMisc", null);
                                XMLDocumentScannerImpl.this.fEntityScanner.scanChar();
                                XMLDocumentScannerImpl.this.setScannerState(12);
                                break;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(14);
                            return false;
                        case 8:
                            XMLDocumentScannerImpl.this.reportFatalError("ReferenceIllegalInTrailingMisc", null);
                            XMLDocumentScannerImpl.this.setScannerState(12);
                            break;
                        case 12:
                            XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                XMLDocumentScannerImpl.this.setScannerState(7);
                                again = true;
                                break;
                            }
                            XMLDocumentScannerImpl.this.setScannerState(1);
                            again = true;
                            break;
                        case 14:
                            return false;
                    }
                    if (!complete && !again) {
                        return true;
                    }
                } catch (Exception e) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, e);
                    return false;
                } catch (Exception e2) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short) 2, e2);
                    return false;
                } catch (EOFException e3) {
                    if (XMLDocumentScannerImpl.this.fMarkupDepth != 0) {
                        XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                        return false;
                    }
                    XMLDocumentScannerImpl.this.setScannerState(14);
                    return false;
                }
            }
        }
    }

    protected final class XMLDeclDispatcher implements Dispatcher {
        protected XMLDeclDispatcher() {
        }

        public boolean dispatch(boolean complete) throws IOException, XNIException {
            XMLDocumentScannerImpl.this.setScannerState(5);
            XMLDocumentScannerImpl.this.setDispatcher(XMLDocumentScannerImpl.this.fPrologDispatcher);
            try {
                if (XMLDocumentScannerImpl.this.fEntityScanner.skipString("<?xml")) {
                    XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
                    xMLDocumentScannerImpl.fMarkupDepth++;
                    if (XMLChar.isName(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                        XMLDocumentScannerImpl.this.fStringBuffer.clear();
                        XMLDocumentScannerImpl.this.fStringBuffer.append("xml");
                        if (XMLDocumentScannerImpl.this.fNamespaces) {
                            while (XMLChar.isNCName(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                XMLDocumentScannerImpl.this.fStringBuffer.append((char) XMLDocumentScannerImpl.this.fEntityScanner.scanChar());
                            }
                        } else {
                            while (XMLChar.isName(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                XMLDocumentScannerImpl.this.fStringBuffer.append((char) XMLDocumentScannerImpl.this.fEntityScanner.scanChar());
                            }
                        }
                        XMLDocumentScannerImpl.this.scanPIData(XMLDocumentScannerImpl.this.fSymbolTable.addSymbol(XMLDocumentScannerImpl.this.fStringBuffer.ch, XMLDocumentScannerImpl.this.fStringBuffer.offset, XMLDocumentScannerImpl.this.fStringBuffer.length), XMLDocumentScannerImpl.this.fString);
                    } else {
                        XMLDocumentScannerImpl.this.scanXMLDeclOrTextDecl(false);
                    }
                }
                XMLDocumentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                return true;
            } catch (Exception e) {
                XMLDocumentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, e);
                return false;
            } catch (Exception e2) {
                XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short) 2, e2);
                return false;
            } catch (EOFException e3) {
                XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                return false;
            }
        }
    }

    public void setInputSource(XMLInputSource inputSource) throws IOException {
        this.fEntityManager.setEntityHandler(this);
        this.fEntityManager.startDocumentEntity(inputSource);
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        this.fDoctypeName = null;
        this.fDoctypePublicId = null;
        this.fDoctypeSystemId = null;
        this.fSeenDoctypeDecl = false;
        this.fScanningDTD = false;
        this.fExternalSubsetSource = null;
        if (this.fParserSettings) {
            try {
                this.fLoadExternalDTD = componentManager.getFeature(LOAD_EXTERNAL_DTD);
            } catch (XMLConfigurationException e) {
                this.fLoadExternalDTD = true;
            }
            try {
                this.fDisallowDoctype = componentManager.getFeature(DISALLOW_DOCTYPE_DECL_FEATURE);
            } catch (XMLConfigurationException e2) {
                this.fDisallowDoctype = false;
            }
            this.fDTDScanner = (XMLDTDScanner) componentManager.getProperty(DTD_SCANNER);
            try {
                this.fValidationManager = (ValidationManager) componentManager.getProperty(VALIDATION_MANAGER);
            } catch (XMLConfigurationException e3) {
                this.fValidationManager = null;
            }
            try {
                this.fNamespaceContext = (NamespaceContext) componentManager.getProperty(NAMESPACE_CONTEXT);
            } catch (XMLConfigurationException e4) {
            }
            if (this.fNamespaceContext == null) {
                this.fNamespaceContext = new NamespaceSupport();
            }
            this.fNamespaceContext.reset();
            setScannerState(0);
            setDispatcher(this.fXMLDeclDispatcher);
            return;
        }
        this.fNamespaceContext.reset();
        setScannerState(0);
        setDispatcher(this.fXMLDeclDispatcher);
    }

    public String[] getRecognizedFeatures() {
        int length;
        String[] featureIds = super.getRecognizedFeatures();
        if (featureIds != null) {
            length = featureIds.length;
        } else {
            length = 0;
        }
        String[] combinedFeatureIds = new String[(RECOGNIZED_FEATURES.length + length)];
        if (featureIds != null) {
            System.arraycopy(featureIds, 0, combinedFeatureIds, 0, featureIds.length);
        }
        System.arraycopy(RECOGNIZED_FEATURES, 0, combinedFeatureIds, length, RECOGNIZED_FEATURES.length);
        return combinedFeatureIds;
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        super.setFeature(featureId, state);
        if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX)) {
            int suffixLength = featureId.length() - Constants.XERCES_FEATURE_PREFIX.length();
            if (suffixLength == Constants.LOAD_EXTERNAL_DTD_FEATURE.length() && featureId.endsWith(Constants.LOAD_EXTERNAL_DTD_FEATURE)) {
                this.fLoadExternalDTD = state;
            } else if (suffixLength == Constants.DISALLOW_DOCTYPE_DECL_FEATURE.length() && featureId.endsWith(Constants.DISALLOW_DOCTYPE_DECL_FEATURE)) {
                this.fDisallowDoctype = state;
            }
        }
    }

    public String[] getRecognizedProperties() {
        int length;
        String[] propertyIds = super.getRecognizedProperties();
        if (propertyIds != null) {
            length = propertyIds.length;
        } else {
            length = 0;
        }
        String[] combinedPropertyIds = new String[(RECOGNIZED_PROPERTIES.length + length)];
        if (propertyIds != null) {
            System.arraycopy(propertyIds, 0, combinedPropertyIds, 0, propertyIds.length);
        }
        System.arraycopy(RECOGNIZED_PROPERTIES, 0, combinedPropertyIds, length, RECOGNIZED_PROPERTIES.length);
        return combinedPropertyIds;
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        super.setProperty(propertyId, value);
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
            if (suffixLength == Constants.DTD_SCANNER_PROPERTY.length() && propertyId.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
                this.fDTDScanner = (XMLDTDScanner) value;
            }
            if (suffixLength == Constants.NAMESPACE_CONTEXT_PROPERTY.length() && propertyId.endsWith(Constants.NAMESPACE_CONTEXT_PROPERTY) && value != null) {
                this.fNamespaceContext = (NamespaceContext) value;
            }
        }
    }

    public Boolean getFeatureDefault(String featureId) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(featureId)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return super.getFeatureDefault(featureId);
    }

    public Object getPropertyDefault(String propertyId) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return super.getPropertyDefault(propertyId);
    }

    public void startEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        super.startEntity(name, identifier, encoding, augs);
        if (!name.equals("[xml]") && this.fEntityScanner.isExternal()) {
            setScannerState(16);
        }
        if (this.fDocumentHandler != null && name.equals("[xml]")) {
            this.fDocumentHandler.startDocument(this.fEntityScanner, encoding, this.fNamespaceContext, null);
        }
    }

    public void endEntity(String name, Augmentations augs) throws XNIException {
        super.endEntity(name, augs);
        if (this.fDocumentHandler != null && name.equals("[xml]")) {
            this.fDocumentHandler.endDocument(null);
        }
    }

    protected Dispatcher createContentDispatcher() {
        return new ContentDispatcher();
    }

    protected boolean scanDoctypeDecl() throws IOException, XNIException {
        boolean z;
        if (!this.fEntityScanner.skipSpaces()) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL", null);
        }
        this.fDoctypeName = this.fEntityScanner.scanName();
        if (this.fDoctypeName == null) {
            reportFatalError("MSG_ROOT_ELEMENT_TYPE_REQUIRED", null);
        }
        if (this.fEntityScanner.skipSpaces()) {
            scanExternalID(this.fStrings, false);
            this.fDoctypeSystemId = this.fStrings[0];
            this.fDoctypePublicId = this.fStrings[1];
            this.fEntityScanner.skipSpaces();
        }
        if (this.fDoctypeSystemId != null) {
            z = true;
        } else {
            z = false;
        }
        this.fHasExternalDTD = z;
        if (!(this.fHasExternalDTD || this.fExternalSubsetResolver == null)) {
            this.fDTDDescription.setValues(null, null, this.fEntityManager.getCurrentResourceIdentifier().getExpandedSystemId(), null);
            this.fDTDDescription.setRootName(this.fDoctypeName);
            this.fExternalSubsetSource = this.fExternalSubsetResolver.getExternalSubset(this.fDTDDescription);
            if (this.fExternalSubsetSource != null) {
                z = true;
            } else {
                z = false;
            }
            this.fHasExternalDTD = z;
        }
        if (this.fDocumentHandler != null) {
            if (this.fExternalSubsetSource == null) {
                this.fDocumentHandler.doctypeDecl(this.fDoctypeName, this.fDoctypePublicId, this.fDoctypeSystemId, null);
            } else {
                this.fDocumentHandler.doctypeDecl(this.fDoctypeName, this.fExternalSubsetSource.getPublicId(), this.fExternalSubsetSource.getSystemId(), null);
            }
        }
        boolean internalSubset = true;
        if (!this.fEntityScanner.skipChar(91)) {
            internalSubset = false;
            this.fEntityScanner.skipSpaces();
            if (!this.fEntityScanner.skipChar(62)) {
                reportFatalError("DoctypedeclUnterminated", new Object[]{this.fDoctypeName});
            }
            this.fMarkupDepth--;
        }
        return internalSubset;
    }

    protected String getScannerStateName(int state) {
        switch (state) {
            case 0:
                return "SCANNER_STATE_XML_DECL";
            case 5:
                return "SCANNER_STATE_PROLOG";
            case 12:
                return "SCANNER_STATE_TRAILING_MISC";
            case 17:
                return "SCANNER_STATE_DTD_INTERNAL_DECLS";
            case 18:
                return "SCANNER_STATE_DTD_EXTERNAL";
            case 19:
                return "SCANNER_STATE_DTD_EXTERNAL_DECLS";
            default:
                return super.getScannerStateName(state);
        }
    }
}
