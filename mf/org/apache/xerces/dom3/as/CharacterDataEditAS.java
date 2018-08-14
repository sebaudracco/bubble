package mf.org.apache.xerces.dom3.as;

public interface CharacterDataEditAS extends NodeEditAS {
    boolean canAppendData(String str);

    boolean canDeleteData(int i, int i2);

    boolean canInsertData(int i, String str);

    boolean canReplaceData(int i, int i2, String str);

    boolean canSetData(int i, int i2);

    boolean getIsWhitespaceOnly();
}
