package mf.org.apache.xerces.dom;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import mf.org.apache.xerces.dom.events.EventImpl;
import mf.org.apache.xerces.dom.events.MouseEventImpl;
import mf.org.apache.xerces.dom.events.MutationEventImpl;
import mf.org.apache.xerces.dom.events.UIEventImpl;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.events.DocumentEvent;
import mf.org.w3c.dom.events.Event;
import mf.org.w3c.dom.events.EventException;
import mf.org.w3c.dom.events.EventListener;
import mf.org.w3c.dom.events.MutationEvent;
import mf.org.w3c.dom.ranges.DocumentRange;
import mf.org.w3c.dom.ranges.Range;
import mf.org.w3c.dom.traversal.DocumentTraversal;
import mf.org.w3c.dom.traversal.NodeFilter;
import mf.org.w3c.dom.traversal.NodeIterator;
import mf.org.w3c.dom.traversal.TreeWalker;

public class DocumentImpl extends CoreDocumentImpl implements DocumentTraversal, DocumentEvent, DocumentRange {
    static final long serialVersionUID = 515687835542616694L;
    protected Hashtable eventListeners;
    protected transient ReferenceQueue iteratorReferenceQueue;
    protected transient List iterators;
    protected boolean mutationEvents = false;
    protected transient ReferenceQueue rangeReferenceQueue;
    protected transient List ranges;
    EnclosingAttr savedEnclosingAttr;

    class EnclosingAttr implements Serializable {
        private static final long serialVersionUID = 5208387723391647216L;
        AttrImpl node;
        String oldvalue;

        EnclosingAttr() {
        }
    }

    class LEntry implements Serializable {
        private static final long serialVersionUID = -8426757059492421631L;
        EventListener listener;
        String type;
        boolean useCapture;

        LEntry(String type, EventListener listener, boolean useCapture) {
            this.type = type;
            this.listener = listener;
            this.useCapture = useCapture;
        }
    }

    public DocumentImpl(boolean grammarAccess) {
        super(grammarAccess);
    }

    public DocumentImpl(DocumentType doctype) {
        super(doctype);
    }

    public DocumentImpl(DocumentType doctype, boolean grammarAccess) {
        super(doctype, grammarAccess);
    }

    public Node cloneNode(boolean deep) {
        DocumentImpl newdoc = new DocumentImpl();
        callUserDataHandlers(this, newdoc, (short) 1);
        cloneNode(newdoc, deep);
        newdoc.mutationEvents = this.mutationEvents;
        return newdoc;
    }

    public DOMImplementation getImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }

    public NodeIterator createNodeIterator(Node root, short whatToShow, NodeFilter filter) {
        return createNodeIterator(root, whatToShow, filter, true);
    }

    public NodeIterator createNodeIterator(Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) {
        if (root == null) {
            throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        }
        NodeIterator iterator = new NodeIteratorImpl(this, root, whatToShow, filter, entityReferenceExpansion);
        if (this.iterators == null) {
            this.iterators = new LinkedList();
            this.iteratorReferenceQueue = new ReferenceQueue();
        }
        removeStaleIteratorReferences();
        this.iterators.add(new WeakReference(iterator, this.iteratorReferenceQueue));
        return iterator;
    }

    public TreeWalker createTreeWalker(Node root, short whatToShow, NodeFilter filter) {
        return createTreeWalker(root, whatToShow, filter, true);
    }

    public TreeWalker createTreeWalker(Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) {
        if (root != null) {
            return new TreeWalkerImpl(root, whatToShow, filter, entityReferenceExpansion);
        }
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    void removeNodeIterator(NodeIterator nodeIterator) {
        if (nodeIterator != null && this.iterators != null) {
            removeStaleIteratorReferences();
            Iterator i = this.iterators.iterator();
            while (i.hasNext()) {
                NodeIterator iterator = ((Reference) i.next()).get();
                if (iterator == nodeIterator) {
                    i.remove();
                    return;
                } else if (iterator == null) {
                    i.remove();
                }
            }
        }
    }

    private void removeStaleIteratorReferences() {
        removeStaleReferences(this.iteratorReferenceQueue, this.iterators);
    }

    private void removeStaleReferences(ReferenceQueue queue, List list) {
        Reference ref = queue.poll();
        int count = 0;
        while (ref != null) {
            count++;
            ref = queue.poll();
        }
        if (count > 0) {
            Iterator i = list.iterator();
            while (i.hasNext()) {
                if (((Reference) i.next()).get() == null) {
                    i.remove();
                    count--;
                    if (count <= 0) {
                        return;
                    }
                }
            }
        }
    }

    public Range createRange() {
        if (this.ranges == null) {
            this.ranges = new LinkedList();
            this.rangeReferenceQueue = new ReferenceQueue();
        }
        Range range = new RangeImpl(this);
        removeStaleRangeReferences();
        this.ranges.add(new WeakReference(range, this.rangeReferenceQueue));
        return range;
    }

    void removeRange(Range range) {
        if (range != null && this.ranges != null) {
            removeStaleRangeReferences();
            Iterator i = this.ranges.iterator();
            while (i.hasNext()) {
                Range otherRange = ((Reference) i.next()).get();
                if (otherRange == range) {
                    i.remove();
                    return;
                } else if (otherRange == null) {
                    i.remove();
                }
            }
        }
    }

    void replacedText(CharacterDataImpl node) {
        if (this.ranges != null) {
            notifyRangesReplacedText(node);
        }
    }

    private void notifyRangesReplacedText(CharacterDataImpl node) {
        removeStaleRangeReferences();
        Iterator i = this.ranges.iterator();
        while (i.hasNext()) {
            RangeImpl range = (RangeImpl) ((Reference) i.next()).get();
            if (range != null) {
                range.receiveReplacedText(node);
            } else {
                i.remove();
            }
        }
    }

    void deletedText(CharacterDataImpl node, int offset, int count) {
        if (this.ranges != null) {
            notifyRangesDeletedText(node, offset, count);
        }
    }

    private void notifyRangesDeletedText(CharacterDataImpl node, int offset, int count) {
        removeStaleRangeReferences();
        Iterator i = this.ranges.iterator();
        while (i.hasNext()) {
            RangeImpl range = (RangeImpl) ((Reference) i.next()).get();
            if (range != null) {
                range.receiveDeletedText(node, offset, count);
            } else {
                i.remove();
            }
        }
    }

    void insertedText(CharacterDataImpl node, int offset, int count) {
        if (this.ranges != null) {
            notifyRangesInsertedText(node, offset, count);
        }
    }

    private void notifyRangesInsertedText(CharacterDataImpl node, int offset, int count) {
        removeStaleRangeReferences();
        Iterator i = this.ranges.iterator();
        while (i.hasNext()) {
            RangeImpl range = (RangeImpl) ((Reference) i.next()).get();
            if (range != null) {
                range.receiveInsertedText(node, offset, count);
            } else {
                i.remove();
            }
        }
    }

    void splitData(Node node, Node newNode, int offset) {
        if (this.ranges != null) {
            notifyRangesSplitData(node, newNode, offset);
        }
    }

    private void notifyRangesSplitData(Node node, Node newNode, int offset) {
        removeStaleRangeReferences();
        Iterator i = this.ranges.iterator();
        while (i.hasNext()) {
            RangeImpl range = (RangeImpl) ((Reference) i.next()).get();
            if (range != null) {
                range.receiveSplitData(node, newNode, offset);
            } else {
                i.remove();
            }
        }
    }

    private void removeStaleRangeReferences() {
        removeStaleReferences(this.rangeReferenceQueue, this.ranges);
    }

    public Event createEvent(String type) throws DOMException {
        if (type.equalsIgnoreCase("Events") || "Event".equals(type)) {
            return new EventImpl();
        }
        if (type.equalsIgnoreCase("MutationEvents") || "MutationEvent".equals(type)) {
            return new MutationEventImpl();
        }
        if (type.equalsIgnoreCase("UIEvents") || "UIEvent".equals(type)) {
            return new UIEventImpl();
        }
        if (type.equalsIgnoreCase("MouseEvents") || "MouseEvent".equals(type)) {
            return new MouseEventImpl();
        }
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    void setMutationEvents(boolean set) {
        this.mutationEvents = set;
    }

    boolean getMutationEvents() {
        return this.mutationEvents;
    }

    protected void setEventListeners(NodeImpl n, Vector listeners) {
        if (this.eventListeners == null) {
            this.eventListeners = new Hashtable();
        }
        if (listeners == null) {
            this.eventListeners.remove(n);
            if (this.eventListeners.isEmpty()) {
                this.mutationEvents = false;
                return;
            }
            return;
        }
        this.eventListeners.put(n, listeners);
        this.mutationEvents = true;
    }

    protected Vector getEventListeners(NodeImpl n) {
        if (this.eventListeners == null) {
            return null;
        }
        return (Vector) this.eventListeners.get(n);
    }

    protected void addEventListener(NodeImpl node, String type, EventListener listener, boolean useCapture) {
        if (type != null && type.length() != 0 && listener != null) {
            removeEventListener(node, type, listener, useCapture);
            Vector nodeListeners = getEventListeners(node);
            if (nodeListeners == null) {
                nodeListeners = new Vector();
                setEventListeners(node, nodeListeners);
            }
            nodeListeners.addElement(new LEntry(type, listener, useCapture));
            LCount lc = LCount.lookup(type);
            if (useCapture) {
                lc.captures++;
                lc.total++;
                return;
            }
            lc.bubbles++;
            lc.total++;
        }
    }

    protected void removeEventListener(NodeImpl node, String type, EventListener listener, boolean useCapture) {
        if (type != null && type.length() != 0 && listener != null) {
            Vector nodeListeners = getEventListeners(node);
            if (nodeListeners != null) {
                for (int i = nodeListeners.size() - 1; i >= 0; i--) {
                    LEntry le = (LEntry) nodeListeners.elementAt(i);
                    if (le.useCapture == useCapture && le.listener == listener && le.type.equals(type)) {
                        nodeListeners.removeElementAt(i);
                        if (nodeListeners.size() == 0) {
                            setEventListeners(node, null);
                        }
                        LCount lc = LCount.lookup(type);
                        if (useCapture) {
                            lc.captures--;
                            lc.total--;
                            return;
                        }
                        lc.bubbles--;
                        lc.total--;
                        return;
                    }
                }
            }
        }
    }

    protected void copyEventListeners(NodeImpl src, NodeImpl tgt) {
        Vector nodeListeners = getEventListeners(src);
        if (nodeListeners != null) {
            setEventListeners(tgt, (Vector) nodeListeners.clone());
        }
    }

    protected boolean dispatchEvent(NodeImpl node, Event event) {
        if (event == null) {
            return false;
        }
        EventImpl evt = (EventImpl) event;
        if (!evt.initialized || evt.type == null || evt.type.length() == 0) {
            throw new EventException((short) 0, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "UNSPECIFIED_EVENT_TYPE_ERR", null));
        }
        LCount lc = LCount.lookup(evt.getType());
        if (lc.total == 0) {
            return evt.preventDefault;
        }
        int j;
        Vector nodeListeners;
        Vector nl;
        int nlsize;
        int i;
        LEntry le;
        evt.target = node;
        evt.stopPropagation = false;
        evt.preventDefault = false;
        ArrayList pv = new ArrayList(10);
        for (Node n = node.getParentNode(); n != null; n = n.getParentNode()) {
            pv.add(n);
            Node p = n;
        }
        if (lc.captures > 0) {
            evt.eventPhase = (short) 1;
            for (j = pv.size() - 1; j >= 0 && !evt.stopPropagation; j--) {
                NodeImpl nn = (NodeImpl) pv.get(j);
                evt.currentTarget = nn;
                nodeListeners = getEventListeners(nn);
                if (nodeListeners != null) {
                    nl = (Vector) nodeListeners.clone();
                    nlsize = nl.size();
                    for (i = 0; i < nlsize; i++) {
                        le = (LEntry) nl.elementAt(i);
                        if (le.useCapture && le.type.equals(evt.type) && nodeListeners.contains(le)) {
                            try {
                                le.listener.handleEvent(evt);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }
        if (lc.bubbles > 0) {
            evt.eventPhase = (short) 2;
            evt.currentTarget = node;
            nodeListeners = getEventListeners(node);
            if (!(evt.stopPropagation || nodeListeners == null)) {
                nl = (Vector) nodeListeners.clone();
                nlsize = nl.size();
                for (i = 0; i < nlsize; i++) {
                    le = (LEntry) nl.elementAt(i);
                    if (!le.useCapture && le.type.equals(evt.type) && nodeListeners.contains(le)) {
                        try {
                            le.listener.handleEvent(evt);
                        } catch (Exception e2) {
                        }
                    }
                }
            }
            if (evt.bubbles) {
                evt.eventPhase = (short) 3;
                int pvsize = pv.size();
                for (j = 0; j < pvsize && !evt.stopPropagation; j++) {
                    nn = (NodeImpl) pv.get(j);
                    evt.currentTarget = nn;
                    nodeListeners = getEventListeners(nn);
                    if (nodeListeners != null) {
                        nl = (Vector) nodeListeners.clone();
                        nlsize = nl.size();
                        for (i = 0; i < nlsize; i++) {
                            le = (LEntry) nl.elementAt(i);
                            if (!le.useCapture && le.type.equals(evt.type) && nodeListeners.contains(le)) {
                                try {
                                    le.listener.handleEvent(evt);
                                } catch (Exception e3) {
                                }
                            }
                        }
                    }
                }
            }
        }
        if (lc.defaults > 0 && evt.cancelable) {
            boolean z = evt.preventDefault;
        }
        return evt.preventDefault;
    }

    protected void dispatchEventToSubtree(Node n, Event e) {
        ((NodeImpl) n).dispatchEvent(e);
        if (n.getNodeType() == (short) 1) {
            NamedNodeMap a = n.getAttributes();
            for (int i = a.getLength() - 1; i >= 0; i--) {
                dispatchingEventToSubtree(a.item(i), e);
            }
        }
        dispatchingEventToSubtree(n.getFirstChild(), e);
    }

    protected void dispatchingEventToSubtree(Node n, Event e) {
        if (n != null) {
            ((NodeImpl) n).dispatchEvent(e);
            if (n.getNodeType() == (short) 1) {
                NamedNodeMap a = n.getAttributes();
                for (int i = a.getLength() - 1; i >= 0; i--) {
                    dispatchingEventToSubtree(a.item(i), e);
                }
            }
            dispatchingEventToSubtree(n.getFirstChild(), e);
            dispatchingEventToSubtree(n.getNextSibling(), e);
        }
    }

    protected void dispatchAggregateEvents(NodeImpl node, EnclosingAttr ea) {
        if (ea != null) {
            dispatchAggregateEvents(node, ea.node, ea.oldvalue, (short) 1);
        } else {
            dispatchAggregateEvents(node, null, null, (short) 0);
        }
    }

    protected void dispatchAggregateEvents(NodeImpl node, AttrImpl enclosingAttr, String oldvalue, short change) {
        NodeImpl owner = null;
        if (enclosingAttr != null) {
            owner = (NodeImpl) enclosingAttr.getOwnerElement();
            if (LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED).total > 0 && owner != null) {
                MutationEventImpl me = new MutationEventImpl();
                me.initMutationEvent(MutationEventImpl.DOM_ATTR_MODIFIED, true, false, enclosingAttr, oldvalue, enclosingAttr.getNodeValue(), enclosingAttr.getNodeName(), change);
                owner.dispatchEvent(me);
            }
        }
        if (LCount.lookup(MutationEventImpl.DOM_SUBTREE_MODIFIED).total > 0) {
            MutationEvent me2 = new MutationEventImpl();
            me2.initMutationEvent(MutationEventImpl.DOM_SUBTREE_MODIFIED, true, false, null, null, null, null, (short) 0);
            if (enclosingAttr != null) {
                dispatchEvent(enclosingAttr, me2);
                if (owner != null) {
                    dispatchEvent(owner, me2);
                    return;
                }
                return;
            }
            dispatchEvent(node, me2);
        }
    }

    protected void saveEnclosingAttr(NodeImpl node) {
        this.savedEnclosingAttr = null;
        if (LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED).total > 0) {
            NodeImpl eventAncestor = node;
            while (eventAncestor != null) {
                int type = eventAncestor.getNodeType();
                if (type == 2) {
                    EnclosingAttr retval = new EnclosingAttr();
                    retval.node = (AttrImpl) eventAncestor;
                    retval.oldvalue = retval.node.getNodeValue();
                    this.savedEnclosingAttr = retval;
                    return;
                } else if (type == 5) {
                    eventAncestor = eventAncestor.parentNode();
                } else if (type == 3) {
                    eventAncestor = eventAncestor.parentNode();
                } else {
                    return;
                }
            }
        }
    }

    void modifyingCharacterData(NodeImpl node, boolean replace) {
        if (this.mutationEvents && !replace) {
            saveEnclosingAttr(node);
        }
    }

    void modifiedCharacterData(NodeImpl node, String oldvalue, String value, boolean replace) {
        if (this.mutationEvents) {
            mutationEventsModifiedCharacterData(node, oldvalue, value, replace);
        }
    }

    private void mutationEventsModifiedCharacterData(NodeImpl node, String oldvalue, String value, boolean replace) {
        if (!replace) {
            if (LCount.lookup(MutationEventImpl.DOM_CHARACTER_DATA_MODIFIED).total > 0) {
                MutationEvent me = new MutationEventImpl();
                me.initMutationEvent(MutationEventImpl.DOM_CHARACTER_DATA_MODIFIED, true, false, null, oldvalue, value, null, (short) 0);
                dispatchEvent(node, me);
            }
            dispatchAggregateEvents(node, this.savedEnclosingAttr);
        }
    }

    void replacedCharacterData(NodeImpl node, String oldvalue, String value) {
        modifiedCharacterData(node, oldvalue, value, false);
    }

    void insertingNode(NodeImpl node, boolean replace) {
        if (this.mutationEvents && !replace) {
            saveEnclosingAttr(node);
        }
    }

    void insertedNode(NodeImpl node, NodeImpl newInternal, boolean replace) {
        if (this.mutationEvents) {
            mutationEventsInsertedNode(node, newInternal, replace);
        }
        if (this.ranges != null) {
            notifyRangesInsertedNode(newInternal);
        }
    }

    private void mutationEventsInsertedNode(NodeImpl node, NodeImpl newInternal, boolean replace) {
        if (LCount.lookup(MutationEventImpl.DOM_NODE_INSERTED).total > 0) {
            MutationEventImpl me = new MutationEventImpl();
            me.initMutationEvent(MutationEventImpl.DOM_NODE_INSERTED, true, false, node, null, null, null, (short) 0);
            dispatchEvent(newInternal, me);
        }
        if (LCount.lookup(MutationEventImpl.DOM_NODE_INSERTED_INTO_DOCUMENT).total > 0) {
            NodeImpl eventAncestor = node;
            if (this.savedEnclosingAttr != null) {
                eventAncestor = (NodeImpl) this.savedEnclosingAttr.node.getOwnerElement();
            }
            if (eventAncestor != null) {
                NodeImpl p = eventAncestor;
                while (p != null) {
                    eventAncestor = p;
                    if (p.getNodeType() == (short) 2) {
                        p = (NodeImpl) ((AttrImpl) p).getOwnerElement();
                    } else {
                        p = p.parentNode();
                    }
                }
                if (eventAncestor.getNodeType() == (short) 9) {
                    me = new MutationEventImpl();
                    me.initMutationEvent(MutationEventImpl.DOM_NODE_INSERTED_INTO_DOCUMENT, false, false, null, null, null, null, (short) 0);
                    dispatchEventToSubtree(newInternal, me);
                }
            }
        }
        if (!replace) {
            dispatchAggregateEvents(node, this.savedEnclosingAttr);
        }
    }

    private void notifyRangesInsertedNode(NodeImpl newInternal) {
        removeStaleRangeReferences();
        Iterator i = this.ranges.iterator();
        while (i.hasNext()) {
            RangeImpl range = (RangeImpl) ((Reference) i.next()).get();
            if (range != null) {
                range.insertedNodeFromDOM(newInternal);
            } else {
                i.remove();
            }
        }
    }

    void removingNode(NodeImpl node, NodeImpl oldChild, boolean replace) {
        if (this.iterators != null) {
            notifyIteratorsRemovingNode(oldChild);
        }
        if (this.ranges != null) {
            notifyRangesRemovingNode(oldChild);
        }
        if (this.mutationEvents) {
            mutationEventsRemovingNode(node, oldChild, replace);
        }
    }

    private void notifyIteratorsRemovingNode(NodeImpl oldChild) {
        removeStaleIteratorReferences();
        Iterator i = this.iterators.iterator();
        while (i.hasNext()) {
            NodeIteratorImpl iterator = (NodeIteratorImpl) ((Reference) i.next()).get();
            if (iterator != null) {
                iterator.removeNode(oldChild);
            } else {
                i.remove();
            }
        }
    }

    private void notifyRangesRemovingNode(NodeImpl oldChild) {
        removeStaleRangeReferences();
        Iterator i = this.ranges.iterator();
        while (i.hasNext()) {
            RangeImpl range = (RangeImpl) ((Reference) i.next()).get();
            if (range != null) {
                range.removeNode(oldChild);
            } else {
                i.remove();
            }
        }
    }

    private void mutationEventsRemovingNode(NodeImpl node, NodeImpl oldChild, boolean replace) {
        if (!replace) {
            saveEnclosingAttr(node);
        }
        if (LCount.lookup(MutationEventImpl.DOM_NODE_REMOVED).total > 0) {
            MutationEventImpl me = new MutationEventImpl();
            me.initMutationEvent(MutationEventImpl.DOM_NODE_REMOVED, true, false, node, null, null, null, (short) 0);
            dispatchEvent(oldChild, me);
        }
        if (LCount.lookup(MutationEventImpl.DOM_NODE_REMOVED_FROM_DOCUMENT).total > 0) {
            NodeImpl eventAncestor = this;
            if (this.savedEnclosingAttr != null) {
                eventAncestor = (NodeImpl) this.savedEnclosingAttr.node.getOwnerElement();
            }
            if (eventAncestor != null) {
                for (NodeImpl p = eventAncestor.parentNode(); p != null; p = p.parentNode()) {
                    eventAncestor = p;
                }
                if (eventAncestor.getNodeType() == (short) 9) {
                    me = new MutationEventImpl();
                    me.initMutationEvent(MutationEventImpl.DOM_NODE_REMOVED_FROM_DOCUMENT, false, false, null, null, null, null, (short) 0);
                    dispatchEventToSubtree(oldChild, me);
                }
            }
        }
    }

    void removedNode(NodeImpl node, boolean replace) {
        if (this.mutationEvents && !replace) {
            dispatchAggregateEvents(node, this.savedEnclosingAttr);
        }
    }

    void replacingNode(NodeImpl node) {
        if (this.mutationEvents) {
            saveEnclosingAttr(node);
        }
    }

    void replacingData(NodeImpl node) {
        if (this.mutationEvents) {
            saveEnclosingAttr(node);
        }
    }

    void replacedNode(NodeImpl node) {
        if (this.mutationEvents) {
            dispatchAggregateEvents(node, this.savedEnclosingAttr);
        }
    }

    void modifiedAttrValue(AttrImpl attr, String oldvalue) {
        if (this.mutationEvents) {
            dispatchAggregateEvents(attr, attr, oldvalue, (short) 1);
        }
    }

    void setAttrNode(AttrImpl attr, AttrImpl previous) {
        if (!this.mutationEvents) {
            return;
        }
        if (previous == null) {
            dispatchAggregateEvents(attr.ownerNode, attr, null, (short) 2);
        } else {
            dispatchAggregateEvents(attr.ownerNode, attr, previous.getNodeValue(), (short) 1);
        }
    }

    void removedAttrNode(AttrImpl attr, NodeImpl oldOwner, String name) {
        if (this.mutationEvents) {
            mutationEventsRemovedAttrNode(attr, oldOwner, name);
        }
    }

    private void mutationEventsRemovedAttrNode(AttrImpl attr, NodeImpl oldOwner, String name) {
        if (LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED).total > 0) {
            MutationEventImpl me = new MutationEventImpl();
            me.initMutationEvent(MutationEventImpl.DOM_ATTR_MODIFIED, true, false, attr, attr.getNodeValue(), null, name, (short) 3);
            dispatchEvent(oldOwner, me);
        }
        dispatchAggregateEvents(oldOwner, null, null, (short) 0);
    }

    void renamedAttrNode(Attr oldAt, Attr newAt) {
    }

    void renamedElement(Element oldEl, Element newEl) {
    }
}
