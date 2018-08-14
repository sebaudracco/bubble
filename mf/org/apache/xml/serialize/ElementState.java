package mf.org.apache.xml.serialize;

import java.util.Hashtable;

public class ElementState {
    public boolean afterComment;
    public boolean afterElement;
    public boolean doCData;
    public boolean empty;
    public boolean inCData;
    public String localName;
    public String namespaceURI;
    public Hashtable prefixes;
    public boolean preserveSpace;
    public String rawName;
    public boolean unescaped;
}
