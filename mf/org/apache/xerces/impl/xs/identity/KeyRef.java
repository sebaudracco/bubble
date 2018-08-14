package mf.org.apache.xerces.impl.xs.identity;

import mf.org.apache.xerces.xs.XSIDCDefinition;

public class KeyRef extends IdentityConstraint {
    protected final UniqueOrKey fKey;

    public KeyRef(String namespace, String identityConstraintName, String elemName, UniqueOrKey key) {
        super(namespace, identityConstraintName, elemName);
        this.fKey = key;
        this.type = (short) 2;
    }

    public UniqueOrKey getKey() {
        return this.fKey;
    }

    public XSIDCDefinition getRefKey() {
        return this.fKey;
    }
}
