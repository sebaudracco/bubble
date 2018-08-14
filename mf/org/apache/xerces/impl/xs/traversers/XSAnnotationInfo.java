package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.opti.ElementImpl;
import mf.org.w3c.dom.Element;

final class XSAnnotationInfo {
    String fAnnotation;
    int fCharOffset;
    int fColumn;
    int fLine;
    XSAnnotationInfo next;

    XSAnnotationInfo(String annotation, int line, int column, int charOffset) {
        this.fAnnotation = annotation;
        this.fLine = line;
        this.fColumn = column;
        this.fCharOffset = charOffset;
    }

    XSAnnotationInfo(String annotation, Element annotationDecl) {
        this.fAnnotation = annotation;
        if (annotationDecl instanceof ElementImpl) {
            ElementImpl annotationDeclImpl = (ElementImpl) annotationDecl;
            this.fLine = annotationDeclImpl.getLineNumber();
            this.fColumn = annotationDeclImpl.getColumnNumber();
            this.fCharOffset = annotationDeclImpl.getCharacterOffset();
            return;
        }
        this.fLine = -1;
        this.fColumn = -1;
        this.fCharOffset = -1;
    }
}
