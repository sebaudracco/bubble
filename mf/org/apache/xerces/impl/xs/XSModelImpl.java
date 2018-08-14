package mf.org.apache.xerces.impl.xs;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Vector;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.impl.xs.util.XSNamedMap4Types;
import mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSAttributeGroupDefinition;
import mf.org.apache.xerces.xs.XSElementDeclaration;
import mf.org.apache.xerces.xs.XSIDCDefinition;
import mf.org.apache.xerces.xs.XSModel;
import mf.org.apache.xerces.xs.XSModelGroupDefinition;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSNamespaceItemList;
import mf.org.apache.xerces.xs.XSNotationDeclaration;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public final class XSModelImpl extends AbstractList implements XSModel, XSNamespaceItemList {
    private static final boolean[] GLOBAL_COMP;
    private static final short MAX_COMP_IDX = (short) 16;
    private XSObjectList fAnnotations;
    private final XSNamedMap[] fGlobalComponents;
    private final int fGrammarCount;
    private final SchemaGrammar[] fGrammarList;
    private final SymbolHash fGrammarMap;
    private final boolean fHasIDC;
    private final XSNamedMap[][] fNSComponents;
    private final String[] fNamespaces;
    private final StringList fNamespacesList;
    private final SymbolHash fSubGroupMap;

    private final class XSNamespaceItemListIterator implements ListIterator {
        private int index;

        public XSNamespaceItemListIterator(int index) {
            this.index = index;
        }

        public boolean hasNext() {
            return this.index < XSModelImpl.this.fGrammarCount;
        }

        public Object next() {
            if (this.index < XSModelImpl.this.fGrammarCount) {
                SchemaGrammar[] access$1 = XSModelImpl.this.fGrammarList;
                int i = this.index;
                this.index = i + 1;
                return access$1[i];
            }
            throw new NoSuchElementException();
        }

        public boolean hasPrevious() {
            return this.index > 0;
        }

        public Object previous() {
            if (this.index > 0) {
                SchemaGrammar[] access$1 = XSModelImpl.this.fGrammarList;
                int i = this.index - 1;
                this.index = i;
                return access$1[i];
            }
            throw new NoSuchElementException();
        }

        public int nextIndex() {
            return this.index;
        }

        public int previousIndex() {
            return this.index - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(Object o) {
            throw new UnsupportedOperationException();
        }

        public void add(Object o) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        boolean[] zArr = new boolean[17];
        zArr[1] = true;
        zArr[2] = true;
        zArr[3] = true;
        zArr[5] = true;
        zArr[6] = true;
        zArr[10] = true;
        zArr[11] = true;
        zArr[15] = true;
        zArr[16] = true;
        GLOBAL_COMP = zArr;
    }

    public XSModelImpl(SchemaGrammar[] grammars) {
        this(grammars, (short) 1);
    }

    public XSModelImpl(SchemaGrammar[] grammars, short s4sVersion) {
        int i;
        this.fAnnotations = null;
        int length = grammars.length;
        int initialSize = Math.max(length + 1, 5);
        String[] namespaces = new String[initialSize];
        SchemaGrammar[] grammarList = new SchemaGrammar[initialSize];
        boolean hasS4S = false;
        for (i = 0; i < length; i++) {
            SchemaGrammar sg = grammars[i];
            String tns = sg.getTargetNamespace();
            namespaces[i] = tns;
            grammarList[i] = sg;
            if (tns == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
                hasS4S = true;
            }
        }
        if (!hasS4S) {
            namespaces[length] = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            int len = length + 1;
            grammarList[length] = SchemaGrammar.getS4SGrammar(s4sVersion);
            length = len;
        }
        for (i = 0; i < length; i++) {
            Vector gs = grammarList[i].getImportedGrammars();
            int j = gs == null ? -1 : gs.size() - 1;
            while (j >= 0) {
                SchemaGrammar sg2 = (SchemaGrammar) gs.elementAt(j);
                int k = 0;
                while (k < length && sg2 != grammarList[k]) {
                    k++;
                }
                if (k == length) {
                    if (length == grammarList.length) {
                        String[] newSA = new String[(length * 2)];
                        System.arraycopy(namespaces, 0, newSA, 0, length);
                        namespaces = newSA;
                        SchemaGrammar[] newGA = new SchemaGrammar[(length * 2)];
                        System.arraycopy(grammarList, 0, newGA, 0, length);
                        grammarList = newGA;
                    }
                    namespaces[length] = sg2.getTargetNamespace();
                    grammarList[length] = sg2;
                    length++;
                }
                j--;
            }
        }
        this.fNamespaces = namespaces;
        this.fGrammarList = grammarList;
        boolean hasIDC = false;
        this.fGrammarMap = new SymbolHash(length * 2);
        for (i = 0; i < length; i++) {
            this.fGrammarMap.put(null2EmptyString(this.fNamespaces[i]), this.fGrammarList[i]);
            if (this.fGrammarList[i].hasIDConstraints()) {
                hasIDC = true;
            }
        }
        this.fHasIDC = hasIDC;
        this.fGrammarCount = length;
        this.fGlobalComponents = new XSNamedMap[17];
        this.fNSComponents = (XSNamedMap[][]) Array.newInstance(XSNamedMap.class, new int[]{length, 17});
        this.fNamespacesList = new StringListImpl(this.fNamespaces, this.fGrammarCount);
        this.fSubGroupMap = buildSubGroups();
    }

    private SymbolHash buildSubGroups_Org() {
        int i;
        SubstitutionGroupHandler sgHandler = new SubstitutionGroupHandler(null);
        for (i = 0; i < this.fGrammarCount; i++) {
            sgHandler.addSubstitutionGroup(this.fGrammarList[i].getSubstitutionGroups());
        }
        XSNamedMap elements = getComponents((short) 2);
        int len = elements.getLength();
        SymbolHash subGroupMap = new SymbolHash(len * 2);
        for (i = 0; i < len; i++) {
            XSElementDecl head = (XSElementDecl) elements.item(i);
            XSElementDeclaration[] subGroup = sgHandler.getSubstitutionGroup(head);
            subGroupMap.put(head, subGroup.length > 0 ? new XSObjectListImpl(subGroup, subGroup.length) : XSObjectListImpl.EMPTY_LIST);
        }
        return subGroupMap;
    }

    private SymbolHash buildSubGroups() {
        int i;
        SubstitutionGroupHandler sgHandler = new SubstitutionGroupHandler(null);
        for (i = 0; i < this.fGrammarCount; i++) {
            sgHandler.addSubstitutionGroup(this.fGrammarList[i].getSubstitutionGroups());
        }
        XSObjectListImpl elements = getGlobalElements();
        int len = elements.getLength();
        SymbolHash subGroupMap = new SymbolHash(len * 2);
        for (i = 0; i < len; i++) {
            XSElementDecl head = (XSElementDecl) elements.item(i);
            XSElementDeclaration[] subGroup = sgHandler.getSubstitutionGroup(head);
            subGroupMap.put(head, subGroup.length > 0 ? new XSObjectListImpl(subGroup, subGroup.length) : XSObjectListImpl.EMPTY_LIST);
        }
        return subGroupMap;
    }

    private XSObjectListImpl getGlobalElements() {
        int i;
        SymbolHash[] tables = new SymbolHash[this.fGrammarCount];
        int length = 0;
        for (i = 0; i < this.fGrammarCount; i++) {
            tables[i] = this.fGrammarList[i].fAllGlobalElemDecls;
            length += tables[i].getLength();
        }
        if (length == 0) {
            return XSObjectListImpl.EMPTY_LIST;
        }
        XSObject[] components = new XSObject[length];
        int start = 0;
        for (i = 0; i < this.fGrammarCount; i++) {
            tables[i].getValues(components, start);
            start += tables[i].getLength();
        }
        return new XSObjectListImpl(components, length);
    }

    public StringList getNamespaces() {
        return this.fNamespacesList;
    }

    public XSNamespaceItemList getNamespaceItems() {
        return this;
    }

    public synchronized XSNamedMap getComponents(short objectType) {
        XSNamedMap xSNamedMap;
        if (objectType > (short) 0 && objectType <= (short) 16) {
            if (GLOBAL_COMP[objectType]) {
                SymbolHash[] tables = new SymbolHash[this.fGrammarCount];
                if (this.fGlobalComponents[objectType] == null) {
                    for (int i = 0; i < this.fGrammarCount; i++) {
                        switch (objectType) {
                            case (short) 1:
                                tables[i] = this.fGrammarList[i].fGlobalAttrDecls;
                                break;
                            case (short) 2:
                                tables[i] = this.fGrammarList[i].fGlobalElemDecls;
                                break;
                            case (short) 3:
                            case (short) 15:
                            case (short) 16:
                                tables[i] = this.fGrammarList[i].fGlobalTypeDecls;
                                break;
                            case (short) 5:
                                tables[i] = this.fGrammarList[i].fGlobalAttrGrpDecls;
                                break;
                            case (short) 6:
                                tables[i] = this.fGrammarList[i].fGlobalGroupDecls;
                                break;
                            case (short) 10:
                                tables[i] = this.fGrammarList[i].fGlobalIDConstraintDecls;
                                break;
                            case (short) 11:
                                tables[i] = this.fGrammarList[i].fGlobalNotationDecls;
                                break;
                            default:
                                break;
                        }
                    }
                    if (objectType == (short) 15 || objectType == (short) 16) {
                        this.fGlobalComponents[objectType] = new XSNamedMap4Types(this.fNamespaces, tables, this.fGrammarCount, objectType);
                    } else {
                        this.fGlobalComponents[objectType] = new XSNamedMapImpl(this.fNamespaces, tables, this.fGrammarCount);
                    }
                }
                xSNamedMap = this.fGlobalComponents[objectType];
            }
        }
        xSNamedMap = XSNamedMapImpl.EMPTY_MAP;
        return xSNamedMap;
    }

    public synchronized XSNamedMap getComponentsByNamespace(short objectType, String namespace) {
        XSNamedMap xSNamedMap;
        if (objectType > (short) 0 && objectType <= (short) 16) {
            if (GLOBAL_COMP[objectType]) {
                int i = 0;
                if (namespace == null) {
                    while (i < this.fGrammarCount) {
                        if (this.fNamespaces[i] == null) {
                            break;
                        }
                        i++;
                    }
                } else {
                    while (i < this.fGrammarCount && !namespace.equals(this.fNamespaces[i])) {
                        i++;
                    }
                }
                if (i == this.fGrammarCount) {
                    xSNamedMap = XSNamedMapImpl.EMPTY_MAP;
                } else {
                    if (this.fNSComponents[i][objectType] == null) {
                        SymbolHash table = null;
                        switch (objectType) {
                            case (short) 1:
                                table = this.fGrammarList[i].fGlobalAttrDecls;
                                break;
                            case (short) 2:
                                table = this.fGrammarList[i].fGlobalElemDecls;
                                break;
                            case (short) 3:
                            case (short) 15:
                            case (short) 16:
                                table = this.fGrammarList[i].fGlobalTypeDecls;
                                break;
                            case (short) 5:
                                table = this.fGrammarList[i].fGlobalAttrGrpDecls;
                                break;
                            case (short) 6:
                                table = this.fGrammarList[i].fGlobalGroupDecls;
                                break;
                            case (short) 10:
                                table = this.fGrammarList[i].fGlobalIDConstraintDecls;
                                break;
                            case (short) 11:
                                table = this.fGrammarList[i].fGlobalNotationDecls;
                                break;
                        }
                        if (objectType == (short) 15 || objectType == (short) 16) {
                            this.fNSComponents[i][objectType] = new XSNamedMap4Types(namespace, table, objectType);
                        } else {
                            this.fNSComponents[i][objectType] = new XSNamedMapImpl(namespace, table);
                        }
                    }
                    xSNamedMap = this.fNSComponents[i][objectType];
                }
            }
        }
        xSNamedMap = XSNamedMapImpl.EMPTY_MAP;
        return xSNamedMap;
    }

    public XSTypeDefinition getTypeDefinition(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSTypeDefinition) sg.fGlobalTypeDecls.get(name);
    }

    public XSTypeDefinition getTypeDefinition(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalTypeDecl(name, loc);
    }

    public XSAttributeDeclaration getAttributeDeclaration(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSAttributeDeclaration) sg.fGlobalAttrDecls.get(name);
    }

    public XSAttributeDeclaration getAttributeDeclaration(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalAttributeDecl(name, loc);
    }

    public XSElementDeclaration getElementDeclaration(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSElementDeclaration) sg.fGlobalElemDecls.get(name);
    }

    public XSElementDeclaration getElementDeclaration(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalElementDecl(name, loc);
    }

    public XSAttributeGroupDefinition getAttributeGroup(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSAttributeGroupDefinition) sg.fGlobalAttrGrpDecls.get(name);
    }

    public XSAttributeGroupDefinition getAttributeGroup(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalAttributeGroupDecl(name, loc);
    }

    public XSModelGroupDefinition getModelGroupDefinition(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSModelGroupDefinition) sg.fGlobalGroupDecls.get(name);
    }

    public XSModelGroupDefinition getModelGroupDefinition(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalGroupDecl(name, loc);
    }

    public XSIDCDefinition getIDCDefinition(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSIDCDefinition) sg.fGlobalIDConstraintDecls.get(name);
    }

    public XSIDCDefinition getIDCDefinition(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getIDConstraintDecl(name, loc);
    }

    public XSNotationDeclaration getNotationDeclaration(String name, String namespace) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSNotationDeclaration) sg.fGlobalNotationDecls.get(name);
    }

    public XSNotationDeclaration getNotationDeclaration(String name, String namespace, String loc) {
        SchemaGrammar sg = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return sg.getGlobalNotationDecl(name, loc);
    }

    public synchronized XSObjectList getAnnotations() {
        XSObjectList xSObjectList;
        if (this.fAnnotations != null) {
            xSObjectList = this.fAnnotations;
        } else {
            int i;
            int totalAnnotations = 0;
            for (i = 0; i < this.fGrammarCount; i++) {
                totalAnnotations += this.fGrammarList[i].fNumAnnotations;
            }
            if (totalAnnotations == 0) {
                this.fAnnotations = XSObjectListImpl.EMPTY_LIST;
                xSObjectList = this.fAnnotations;
            } else {
                XSAnnotationImpl[] annotations = new XSAnnotationImpl[totalAnnotations];
                int currPos = 0;
                for (i = 0; i < this.fGrammarCount; i++) {
                    SchemaGrammar currGrammar = this.fGrammarList[i];
                    if (currGrammar.fNumAnnotations > 0) {
                        System.arraycopy(currGrammar.fAnnotations, 0, annotations, currPos, currGrammar.fNumAnnotations);
                        currPos += currGrammar.fNumAnnotations;
                    }
                }
                this.fAnnotations = new XSObjectListImpl(annotations, annotations.length);
                xSObjectList = this.fAnnotations;
            }
        }
        return xSObjectList;
    }

    private static final String null2EmptyString(String str) {
        return str == null ? XMLSymbols.EMPTY_STRING : str;
    }

    public boolean hasIDConstraints() {
        return this.fHasIDC;
    }

    public XSObjectList getSubstitutionGroup(XSElementDeclaration head) {
        return (XSObjectList) this.fSubGroupMap.get(head);
    }

    public int getLength() {
        return this.fGrammarCount;
    }

    public XSNamespaceItem item(int index) {
        if (index < 0 || index >= this.fGrammarCount) {
            return null;
        }
        return this.fGrammarList[index];
    }

    public Object get(int index) {
        if (index >= 0 && index < this.fGrammarCount) {
            return this.fGrammarList[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

    public Iterator iterator() {
        return listIterator0(0);
    }

    public ListIterator listIterator() {
        return listIterator0(0);
    }

    public ListIterator listIterator(int index) {
        if (index >= 0 && index < this.fGrammarCount) {
            return listIterator0(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    private ListIterator listIterator0(int index) {
        return new XSNamespaceItemListIterator(index);
    }

    public Object[] toArray() {
        Object[] a = new Object[this.fGrammarCount];
        toArray0(a);
        return a;
    }

    public Object[] toArray(Object[] a) {
        if (a.length < this.fGrammarCount) {
            a = (Object[]) Array.newInstance(a.getClass().getComponentType(), this.fGrammarCount);
        }
        toArray0(a);
        if (a.length > this.fGrammarCount) {
            a[this.fGrammarCount] = null;
        }
        return a;
    }

    private void toArray0(Object[] a) {
        if (this.fGrammarCount > 0) {
            System.arraycopy(this.fGrammarList, 0, a, 0, this.fGrammarCount);
        }
    }
}
