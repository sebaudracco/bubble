package mf.org.apache.xerces.xinclude;

import mf.org.apache.xerces.xni.NamespaceContext;

public class XIncludeNamespaceSupport extends MultipleScopeNamespaceSupport {
    private boolean[] fValidContext;

    public XIncludeNamespaceSupport() {
        this.fValidContext = new boolean[8];
    }

    public XIncludeNamespaceSupport(NamespaceContext context) {
        super(context);
        this.fValidContext = new boolean[8];
    }

    public void pushContext() {
        super.pushContext();
        if (this.fCurrentContext + 1 == this.fValidContext.length) {
            boolean[] contextarray = new boolean[(this.fValidContext.length * 2)];
            System.arraycopy(this.fValidContext, 0, contextarray, 0, this.fValidContext.length);
            this.fValidContext = contextarray;
        }
        this.fValidContext[this.fCurrentContext] = true;
    }

    public void setContextInvalid() {
        this.fValidContext[this.fCurrentContext] = false;
    }

    public String getURIFromIncludeParent(String prefix) {
        int lastValidContext = this.fCurrentContext - 1;
        while (lastValidContext > 0 && !this.fValidContext[lastValidContext]) {
            lastValidContext--;
        }
        return getURI(prefix, lastValidContext);
    }
}
