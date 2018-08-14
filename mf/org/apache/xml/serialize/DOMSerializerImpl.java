package mf.org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import mf.org.apache.xerces.dom.CoreDocumentImpl;
import mf.org.apache.xerces.dom.DOMErrorImpl;
import mf.org.apache.xerces.dom.DOMLocatorImpl;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.dom.DOMNormalizer;
import mf.org.apache.xerces.dom.DOMStringListImpl;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMErrorHandler;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMStringList;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentFragment;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.ls.LSException;
import mf.org.w3c.dom.ls.LSOutput;
import mf.org.w3c.dom.ls.LSSerializer;
import mf.org.w3c.dom.ls.LSSerializerFilter;

public class DOMSerializerImpl implements LSSerializer, DOMConfiguration {
    protected static final short CDATA = (short) 8;
    protected static final short COMMENTS = (short) 32;
    protected static final short DISCARDDEFAULT = (short) 64;
    protected static final short DOM_ELEMENT_CONTENT_WHITESPACE = (short) 1024;
    protected static final short ENTITIES = (short) 4;
    protected static final short INFOSET = (short) 128;
    protected static final short NAMESPACES = (short) 1;
    protected static final short NSDECL = (short) 512;
    protected static final short PRETTY_PRINT = (short) 2048;
    protected static final short SPLITCDATA = (short) 16;
    protected static final short WELLFORMED = (short) 2;
    protected static final short XMLDECL = (short) 256;
    private final DOMErrorImpl fError;
    private DOMErrorHandler fErrorHandler;
    private final DOMLocatorImpl fLocator;
    private DOMStringList fRecognizedParameters;
    protected short features;
    private XMLSerializer serializer;
    private XML11Serializer xml11Serializer;

    static class DocumentMethods {
        private static Method fgDocumentGetInputEncodingMethod;
        private static Method fgDocumentGetXmlEncodingMethod;
        private static Method fgDocumentGetXmlVersionMethod;
        private static boolean fgDocumentMethodsAvailable;

        static {
            fgDocumentGetXmlVersionMethod = null;
            fgDocumentGetInputEncodingMethod = null;
            fgDocumentGetXmlEncodingMethod = null;
            fgDocumentMethodsAvailable = false;
            try {
                fgDocumentGetXmlVersionMethod = Document.class.getMethod("getXmlVersion", new Class[0]);
                fgDocumentGetInputEncodingMethod = Document.class.getMethod("getInputEncoding", new Class[0]);
                fgDocumentGetXmlEncodingMethod = Document.class.getMethod("getXmlEncoding", new Class[0]);
                fgDocumentMethodsAvailable = true;
            } catch (Exception e) {
                fgDocumentGetXmlVersionMethod = null;
                fgDocumentGetInputEncodingMethod = null;
                fgDocumentGetXmlEncodingMethod = null;
                fgDocumentMethodsAvailable = false;
            }
        }

        private DocumentMethods() {
        }
    }

    public DOMSerializerImpl() {
        this.features = (short) 0;
        this.fErrorHandler = null;
        this.fError = new DOMErrorImpl();
        this.fLocator = new DOMLocatorImpl();
        this.features = (short) (this.features | 1);
        this.features = (short) (this.features | 4);
        this.features = (short) (this.features | 32);
        this.features = (short) (this.features | 8);
        this.features = (short) (this.features | 16);
        this.features = (short) (this.features | 2);
        this.features = (short) (this.features | 512);
        this.features = (short) (this.features | 1024);
        this.features = (short) (this.features | 64);
        this.features = (short) (this.features | 256);
        this.serializer = new XMLSerializer();
        initSerializer(this.serializer);
    }

    public DOMConfiguration getDomConfig() {
        return this;
    }

    public void setParameter(String name, Object value) throws DOMException {
        if (value instanceof Boolean) {
            boolean state = ((Boolean) value).booleanValue();
            if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
                if (state) {
                    this.features = (short) (this.features & -5);
                    this.features = (short) (this.features & -9);
                    this.features = (short) (this.features | 1);
                    this.features = (short) (this.features | 512);
                    this.features = (short) (this.features | 2);
                    this.features = (short) (this.features | 32);
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_XMLDECL)) {
                this.features = (short) (state ? this.features | 256 : this.features & -257);
            } else if (name.equalsIgnoreCase("namespaces")) {
                if (state) {
                    r2 = this.features | 1;
                } else {
                    r2 = this.features & -2;
                }
                this.features = (short) r2;
                this.serializer.fNamespaces = state;
            } else if (name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA)) {
                if (state) {
                    r2 = this.features | 16;
                } else {
                    r2 = this.features & -17;
                }
                this.features = (short) r2;
            } else if (name.equalsIgnoreCase(Constants.DOM_DISCARD_DEFAULT_CONTENT)) {
                if (state) {
                    r2 = this.features | 64;
                } else {
                    r2 = this.features & -65;
                }
                this.features = (short) r2;
            } else if (name.equalsIgnoreCase(Constants.DOM_WELLFORMED)) {
                if (state) {
                    r2 = this.features | 2;
                } else {
                    r2 = this.features & -3;
                }
                this.features = (short) r2;
            } else if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
                if (state) {
                    r2 = this.features | 4;
                } else {
                    r2 = this.features & -5;
                }
                this.features = (short) r2;
            } else if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
                if (state) {
                    r2 = this.features | 8;
                } else {
                    r2 = this.features & -9;
                }
                this.features = (short) r2;
            } else if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
                if (state) {
                    r2 = this.features | 32;
                } else {
                    r2 = this.features & -33;
                }
                this.features = (short) r2;
            } else if (name.equalsIgnoreCase(Constants.DOM_FORMAT_PRETTY_PRINT)) {
                if (state) {
                    r2 = this.features | 2048;
                } else {
                    r2 = this.features & -2049;
                }
                this.features = (short) r2;
            } else if (name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_VALIDATE) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS)) {
                if (state) {
                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                if (state) {
                    r2 = this.features | 512;
                } else {
                    r2 = this.features & -513;
                }
                this.features = (short) r2;
                this.serializer.fNamespacePrefixes = state;
            } else if (!name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE) && !name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS)) {
                throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{name}));
            } else if (!state) {
                throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
            if (value == null || (value instanceof DOMErrorHandler)) {
                this.fErrorHandler = (DOMErrorHandler) value;
                return;
            }
            throw new DOMException((short) 17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "TYPE_MISMATCH_ERR", new Object[]{name}));
        } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER) || name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION) || (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE) && value != null)) {
            throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
        } else {
            throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{name}));
        }
    }

    public boolean canSetParameter(String name, Object state) {
        boolean z = false;
        if (state == null) {
            return true;
        }
        if (state instanceof Boolean) {
            boolean value = ((Boolean) state).booleanValue();
            if (name.equalsIgnoreCase("namespaces") || name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA) || name.equalsIgnoreCase(Constants.DOM_DISCARD_DEFAULT_CONTENT) || name.equalsIgnoreCase(Constants.DOM_XMLDECL) || name.equalsIgnoreCase(Constants.DOM_WELLFORMED) || name.equalsIgnoreCase(Constants.DOM_INFOSET) || name.equalsIgnoreCase(Constants.DOM_ENTITIES) || name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS) || name.equalsIgnoreCase(Constants.DOM_COMMENTS) || name.equalsIgnoreCase(Constants.DOM_FORMAT_PRETTY_PRINT) || name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                return true;
            }
            if (name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_VALIDATE) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS)) {
                if (!value) {
                    z = true;
                }
                return z;
            } else if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE) || name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS)) {
                return value;
            }
        } else if ((name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER) && state == null) || (state instanceof DOMErrorHandler)) {
            return true;
        }
        return false;
    }

    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList parameters = new ArrayList();
            parameters.add("namespaces");
            parameters.add(Constants.DOM_SPLIT_CDATA);
            parameters.add(Constants.DOM_DISCARD_DEFAULT_CONTENT);
            parameters.add(Constants.DOM_XMLDECL);
            parameters.add(Constants.DOM_CANONICAL_FORM);
            parameters.add(Constants.DOM_VALIDATE_IF_SCHEMA);
            parameters.add(Constants.DOM_VALIDATE);
            parameters.add(Constants.DOM_CHECK_CHAR_NORMALIZATION);
            parameters.add(Constants.DOM_DATATYPE_NORMALIZATION);
            parameters.add(Constants.DOM_FORMAT_PRETTY_PRINT);
            parameters.add(Constants.DOM_NORMALIZE_CHARACTERS);
            parameters.add(Constants.DOM_WELLFORMED);
            parameters.add(Constants.DOM_INFOSET);
            parameters.add(Constants.DOM_NAMESPACE_DECLARATIONS);
            parameters.add(Constants.DOM_ELEMENT_CONTENT_WHITESPACE);
            parameters.add(Constants.DOM_ENTITIES);
            parameters.add(Constants.DOM_CDATA_SECTIONS);
            parameters.add(Constants.DOM_COMMENTS);
            parameters.add(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS);
            parameters.add(Constants.DOM_ERROR_HANDLER);
            this.fRecognizedParameters = new DOMStringListImpl(parameters);
        }
        return this.fRecognizedParameters;
    }

    public Object getParameter(String name) throws DOMException {
        if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
            if ((this.features & 32) != 0) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase("namespaces")) {
            return (this.features & 1) != 0 ? Boolean.TRUE : Boolean.FALSE;
        } else {
            if (name.equalsIgnoreCase(Constants.DOM_XMLDECL)) {
                return (this.features & 256) != 0 ? Boolean.TRUE : Boolean.FALSE;
            } else {
                if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
                    return (this.features & 8) != 0 ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
                        return (this.features & 4) != 0 ? Boolean.TRUE : Boolean.FALSE;
                    } else {
                        if (name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA)) {
                            return (this.features & 16) != 0 ? Boolean.TRUE : Boolean.FALSE;
                        } else {
                            if (name.equalsIgnoreCase(Constants.DOM_WELLFORMED)) {
                                return (this.features & 2) != 0 ? Boolean.TRUE : Boolean.FALSE;
                            } else {
                                if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                                    return (this.features & 512) != 0 ? Boolean.TRUE : Boolean.FALSE;
                                } else {
                                    if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE) || name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS)) {
                                        return Boolean.TRUE;
                                    }
                                    if (name.equalsIgnoreCase(Constants.DOM_DISCARD_DEFAULT_CONTENT)) {
                                        return (this.features & 64) != 0 ? Boolean.TRUE : Boolean.FALSE;
                                    } else {
                                        if (name.equalsIgnoreCase(Constants.DOM_FORMAT_PRETTY_PRINT)) {
                                            return (this.features & 2048) != 0 ? Boolean.TRUE : Boolean.FALSE;
                                        } else {
                                            if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
                                                if ((this.features & 4) != 0 || (this.features & 8) != 0 || (this.features & 1) == 0 || (this.features & 512) == 0 || (this.features & 2) == 0 || (this.features & 32) == 0) {
                                                    return Boolean.FALSE;
                                                }
                                                return Boolean.TRUE;
                                            } else if (name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_VALIDATE) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION)) {
                                                return Boolean.FALSE;
                                            } else {
                                                if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
                                                    return this.fErrorHandler;
                                                }
                                                if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER) || name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION) || name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
                                                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
                                                }
                                                throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{name}));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String writeToString(Node wnode) throws DOMException, LSException {
        XMLSerializer ser;
        String ver = _getXmlVersion(wnode);
        if (ver == null || !ver.equals("1.1")) {
            ser = this.serializer;
        } else {
            if (this.xml11Serializer == null) {
                this.xml11Serializer = new XML11Serializer();
                initSerializer(this.xml11Serializer);
            }
            copySettings(this.serializer, this.xml11Serializer);
            ser = this.xml11Serializer;
        }
        StringWriter destination = new StringWriter();
        try {
            prepareForSerialization(ser, wnode);
            ser._format.setEncoding("UTF-16");
            ser.setOutputCharStream(destination);
            if (wnode.getNodeType() == (short) 9) {
                ser.serialize((Document) wnode);
            } else if (wnode.getNodeType() == (short) 11) {
                ser.serialize((DocumentFragment) wnode);
            } else if (wnode.getNodeType() == (short) 1) {
                ser.serialize((Element) wnode);
            } else {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "unable-to-serialize-node", null);
                if (ser.fDOMErrorHandler != null) {
                    DOMErrorImpl error = new DOMErrorImpl();
                    error.fType = "unable-to-serialize-node";
                    error.fMessage = msg;
                    error.fSeverity = (short) 3;
                    ser.fDOMErrorHandler.handleError(error);
                }
                throw new LSException((short) 82, msg);
            }
            ser.clearDocumentState();
            return destination.toString();
        } catch (LSException lse) {
            throw lse;
        } catch (RuntimeException e) {
            if (e == DOMNormalizer.abort) {
                ser.clearDocumentState();
                return null;
            }
            throw ((LSException) DOMUtil.createLSException((short) 82, e).fillInStackTrace());
        } catch (IOException ioe) {
            throw new DOMException((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "STRING_TOO_LONG", new Object[]{ioe.getMessage()}));
        } catch (Throwable th) {
            ser.clearDocumentState();
        }
    }

    public void setNewLine(String newLine) {
        this.serializer._format.setLineSeparator(newLine);
    }

    public String getNewLine() {
        return this.serializer._format.getLineSeparator();
    }

    public LSSerializerFilter getFilter() {
        return this.serializer.fDOMFilter;
    }

    public void setFilter(LSSerializerFilter filter) {
        this.serializer.fDOMFilter = filter;
    }

    private void initSerializer(XMLSerializer ser) {
        ser.fNSBinder = new NamespaceSupport();
        ser.fLocalNSBinder = new NamespaceSupport();
        ser.fSymbolTable = new SymbolTable();
    }

    private void copySettings(XMLSerializer src, XMLSerializer dest) {
        dest.fDOMErrorHandler = this.fErrorHandler;
        dest._format.setEncoding(src._format.getEncoding());
        dest._format.setLineSeparator(src._format.getLineSeparator());
        dest.fDOMFilter = src.fDOMFilter;
    }

    public boolean write(Node node, LSOutput destination) throws LSException {
        DOMErrorImpl error;
        if (node == null) {
            return false;
        }
        XMLSerializer ser;
        String ver = _getXmlVersion(node);
        if (ver == null || !ver.equals("1.1")) {
            ser = this.serializer;
        } else {
            if (this.xml11Serializer == null) {
                this.xml11Serializer = new XML11Serializer();
                initSerializer(this.xml11Serializer);
            }
            copySettings(this.serializer, this.xml11Serializer);
            ser = this.xml11Serializer;
        }
        String encoding = destination.getEncoding();
        if (encoding == null) {
            encoding = _getInputEncoding(node);
            if (encoding == null) {
                encoding = _getXmlEncoding(node);
                if (encoding == null) {
                    encoding = "UTF-8";
                }
            }
        }
        try {
            prepareForSerialization(ser, node);
            ser._format.setEncoding(encoding);
            OutputStream outputStream = destination.getByteStream();
            Writer writer = destination.getCharacterStream();
            String uri = destination.getSystemId();
            if (writer != null) {
                ser.setOutputCharStream(writer);
            } else if (outputStream != null) {
                ser.setOutputByteStream(outputStream);
            } else if (uri == null) {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "no-output-specified", null);
                if (ser.fDOMErrorHandler != null) {
                    error = new DOMErrorImpl();
                    error.fType = "no-output-specified";
                    error.fMessage = msg;
                    error.fSeverity = (short) 3;
                    ser.fDOMErrorHandler.handleError(error);
                }
                throw new LSException((short) 82, msg);
            } else {
                ser.setOutputByteStream(XMLEntityManager.createOutputStream(uri));
            }
            if (node.getNodeType() == (short) 9) {
                ser.serialize((Document) node);
            } else if (node.getNodeType() == (short) 11) {
                ser.serialize((DocumentFragment) node);
            } else if (node.getNodeType() == (short) 1) {
                ser.serialize((Element) node);
            } else {
                ser.clearDocumentState();
                return false;
            }
            ser.clearDocumentState();
            return true;
        } catch (UnsupportedEncodingException ue) {
            if (ser.fDOMErrorHandler != null) {
                error = new DOMErrorImpl();
                error.fException = ue;
                error.fType = "unsupported-encoding";
                error.fMessage = ue.getMessage();
                error.fSeverity = (short) 3;
                ser.fDOMErrorHandler.handleError(error);
            }
            throw new LSException((short) 82, DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "unsupported-encoding", null));
        } catch (LSException lse) {
            throw lse;
        } catch (RuntimeException e) {
            if (e == DOMNormalizer.abort) {
                ser.clearDocumentState();
                return false;
            }
            throw ((LSException) DOMUtil.createLSException((short) 82, e).fillInStackTrace());
        } catch (RuntimeException e2) {
            if (ser.fDOMErrorHandler != null) {
                error = new DOMErrorImpl();
                error.fException = e2;
                error.fMessage = e2.getMessage();
                error.fSeverity = (short) 2;
                ser.fDOMErrorHandler.handleError(error);
            }
            throw ((LSException) DOMUtil.createLSException((short) 82, e2).fillInStackTrace());
        } catch (Throwable th) {
            ser.clearDocumentState();
        }
    }

    public boolean writeToURI(Node node, String URI) throws LSException {
        XMLSerializer ser;
        if (node == null) {
            return false;
        }
        String ver = _getXmlVersion(node);
        if (ver == null || !ver.equals("1.1")) {
            ser = this.serializer;
        } else {
            if (this.xml11Serializer == null) {
                this.xml11Serializer = new XML11Serializer();
                initSerializer(this.xml11Serializer);
            }
            copySettings(this.serializer, this.xml11Serializer);
            ser = this.xml11Serializer;
        }
        String encoding = _getInputEncoding(node);
        if (encoding == null) {
            encoding = _getXmlEncoding(node);
            if (encoding == null) {
                encoding = "UTF-8";
            }
        }
        try {
            prepareForSerialization(ser, node);
            ser._format.setEncoding(encoding);
            ser.setOutputByteStream(XMLEntityManager.createOutputStream(URI));
            if (node.getNodeType() == (short) 9) {
                ser.serialize((Document) node);
            } else if (node.getNodeType() == (short) 11) {
                ser.serialize((DocumentFragment) node);
            } else if (node.getNodeType() == (short) 1) {
                ser.serialize((Element) node);
            } else {
                ser.clearDocumentState();
                return false;
            }
            ser.clearDocumentState();
            return true;
        } catch (LSException lse) {
            throw lse;
        } catch (RuntimeException e) {
            if (e == DOMNormalizer.abort) {
                ser.clearDocumentState();
                return false;
            }
            throw ((LSException) DOMUtil.createLSException((short) 82, e).fillInStackTrace());
        } catch (Exception e2) {
            if (ser.fDOMErrorHandler != null) {
                DOMErrorImpl error = new DOMErrorImpl();
                error.fException = e2;
                error.fMessage = e2.getMessage();
                error.fSeverity = (short) 2;
                ser.fDOMErrorHandler.handleError(error);
            }
            throw ((LSException) DOMUtil.createLSException((short) 82, e2).fillInStackTrace());
        } catch (Throwable th) {
            ser.clearDocumentState();
        }
    }

    private void prepareForSerialization(XMLSerializer ser, Node node) {
        boolean z;
        boolean z2 = true;
        ser.reset();
        ser.features = this.features;
        ser.fDOMErrorHandler = this.fErrorHandler;
        ser.fNamespaces = (this.features & 1) != 0;
        if ((this.features & 512) != 0) {
            z = true;
        } else {
            z = false;
        }
        ser.fNamespacePrefixes = z;
        OutputFormat outputFormat = ser._format;
        if ((this.features & 2048) != 0) {
            z = true;
        } else {
            z = false;
        }
        outputFormat.setIndenting(z);
        outputFormat = ser._format;
        if ((this.features & 32) == 0) {
            z = true;
        } else {
            z = false;
        }
        outputFormat.setOmitComments(z);
        OutputFormat outputFormat2 = ser._format;
        if ((this.features & 256) != 0) {
            z2 = false;
        }
        outputFormat2.setOmitXMLDeclaration(z2);
        if ((this.features & 2) != 0) {
            Document document;
            Node root = node;
            boolean verifyNames = true;
            if (node.getNodeType() == (short) 9) {
                document = (Document) node;
            } else {
                document = node.getOwnerDocument();
            }
            try {
                Method versionChanged = document.getClass().getMethod("isXMLVersionChanged()", new Class[0]);
                if (versionChanged != null) {
                    verifyNames = ((Boolean) versionChanged.invoke(document, null)).booleanValue();
                }
            } catch (Exception e) {
            }
            if (node.getFirstChild() != null) {
                while (node != null) {
                    verify(node, verifyNames, false);
                    Node next = node.getFirstChild();
                    while (next == null) {
                        next = node.getNextSibling();
                        if (next == null) {
                            node = node.getParentNode();
                            if (root == node) {
                                next = null;
                                break;
                            }
                            next = node.getNextSibling();
                        }
                    }
                    node = next;
                }
                return;
            }
            verify(node, verifyNames, false);
        }
    }

    private void verify(Node node, boolean verifyNames, boolean xml11Version) {
        int type = node.getNodeType();
        this.fLocator.fRelatedNode = node;
        boolean wellformed;
        switch (type) {
            case 1:
                if (verifyNames) {
                    if ((this.features & 1) != 0) {
                        wellformed = CoreDocumentImpl.isValidQName(node.getPrefix(), node.getLocalName(), xml11Version);
                    } else {
                        wellformed = CoreDocumentImpl.isXMLName(node.getNodeName(), xml11Version);
                    }
                    if (!(wellformed || this.fErrorHandler == null)) {
                        DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[]{"Element", node.getNodeName()}), (short) 3, "wf-invalid-character-in-node-name");
                    }
                }
                NamedNodeMap attributes = node.hasAttributes() ? node.getAttributes() : null;
                if (attributes != null) {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        Attr attr = (Attr) attributes.item(i);
                        this.fLocator.fRelatedNode = attr;
                        DOMNormalizer.isAttrValueWF(this.fErrorHandler, this.fError, this.fLocator, attributes, attr, attr.getValue(), xml11Version);
                        if (verifyNames && !CoreDocumentImpl.isXMLName(attr.getNodeName(), xml11Version)) {
                            DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[]{"Attr", node.getNodeName()}), (short) 3, "wf-invalid-character-in-node-name");
                        }
                    }
                    break;
                }
                break;
            case 3:
                DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, node.getNodeValue(), xml11Version);
                break;
            case 4:
                DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, node.getNodeValue(), xml11Version);
                break;
            case 5:
                if (verifyNames && (this.features & 4) != 0) {
                    CoreDocumentImpl.isXMLName(node.getNodeName(), xml11Version);
                    break;
                }
            case 7:
                ProcessingInstruction pinode = (ProcessingInstruction) node;
                String target = pinode.getTarget();
                if (verifyNames) {
                    if (xml11Version) {
                        wellformed = XML11Char.isXML11ValidName(target);
                    } else {
                        wellformed = XMLChar.isValidName(target);
                    }
                    if (!wellformed) {
                        DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[]{"Element", node.getNodeName()}), (short) 3, "wf-invalid-character-in-node-name");
                    }
                }
                DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, pinode.getData(), xml11Version);
                break;
            case 8:
                if ((this.features & 32) != 0) {
                    DOMNormalizer.isCommentWF(this.fErrorHandler, this.fError, this.fLocator, ((Comment) node).getData(), xml11Version);
                    break;
                }
                break;
        }
        this.fLocator.fRelatedNode = null;
    }

    private String _getXmlVersion(Node node) {
        Document doc = node.getNodeType() == (short) 9 ? (Document) node : node.getOwnerDocument();
        if (doc != null && DocumentMethods.fgDocumentMethodsAvailable) {
            try {
                return (String) DocumentMethods.fgDocumentGetXmlVersionMethod.invoke(doc, null);
            } catch (VirtualMachineError vme) {
                throw vme;
            } catch (ThreadDeath td) {
                throw td;
            } catch (Throwable th) {
            }
        }
        return null;
    }

    private String _getInputEncoding(Node node) {
        Document doc = node.getNodeType() == (short) 9 ? (Document) node : node.getOwnerDocument();
        if (doc != null && DocumentMethods.fgDocumentMethodsAvailable) {
            try {
                return (String) DocumentMethods.fgDocumentGetInputEncodingMethod.invoke(doc, null);
            } catch (VirtualMachineError vme) {
                throw vme;
            } catch (ThreadDeath td) {
                throw td;
            } catch (Throwable th) {
            }
        }
        return null;
    }

    private String _getXmlEncoding(Node node) {
        Document doc = node.getNodeType() == (short) 9 ? (Document) node : node.getOwnerDocument();
        if (doc != null && DocumentMethods.fgDocumentMethodsAvailable) {
            try {
                return (String) DocumentMethods.fgDocumentGetXmlEncodingMethod.invoke(doc, null);
            } catch (VirtualMachineError vme) {
                throw vme;
            } catch (ThreadDeath td) {
                throw td;
            } catch (Throwable th) {
            }
        }
        return null;
    }
}
