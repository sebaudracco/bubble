package mf.org.apache.xerces.xs;

public interface ElementPSVI extends ItemPSVI {
    XSElementDeclaration getElementDeclaration();

    boolean getNil();

    XSNotationDeclaration getNotation();

    XSModel getSchemaInformation();
}
