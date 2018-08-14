package mf.org.apache.xerces.impl.validation;

public interface EntityState {
    boolean isEntityDeclared(String str);

    boolean isEntityUnparsed(String str);
}
