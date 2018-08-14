package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.CharacterData;
import mf.org.w3c.dom.Comment;

public class CommentImpl extends CharacterDataImpl implements CharacterData, Comment {
    static final long serialVersionUID = -2685736833408134044L;

    public CommentImpl(CoreDocumentImpl ownerDoc, String data) {
        super(ownerDoc, data);
    }

    public short getNodeType() {
        return (short) 8;
    }

    public String getNodeName() {
        return "#comment";
    }
}
