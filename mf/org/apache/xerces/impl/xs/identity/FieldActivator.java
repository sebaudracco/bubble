package mf.org.apache.xerces.impl.xs.identity;

public interface FieldActivator {
    XPathMatcher activateField(Field field, int i);

    void endValueScopeFor(IdentityConstraint identityConstraint, int i);

    void startValueScopeFor(IdentityConstraint identityConstraint, int i);
}
