package mf.org.apache.xerces.impl.validation;

public final class ConfigurableValidationState extends ValidationState {
    private boolean fIdIdrefChecking = true;
    private boolean fUnparsedEntityChecking = true;

    public void setIdIdrefChecking(boolean setting) {
        this.fIdIdrefChecking = setting;
    }

    public void setUnparsedEntityChecking(boolean setting) {
        this.fUnparsedEntityChecking = setting;
    }

    public String checkIDRefID() {
        return this.fIdIdrefChecking ? super.checkIDRefID() : null;
    }

    public boolean isIdDeclared(String name) {
        return this.fIdIdrefChecking ? super.isIdDeclared(name) : false;
    }

    public boolean isEntityDeclared(String name) {
        return this.fUnparsedEntityChecking ? super.isEntityDeclared(name) : true;
    }

    public boolean isEntityUnparsed(String name) {
        return this.fUnparsedEntityChecking ? super.isEntityUnparsed(name) : true;
    }

    public void addId(String name) {
        if (this.fIdIdrefChecking) {
            super.addId(name);
        }
    }

    public void addIdRef(String name) {
        if (this.fIdIdrefChecking) {
            super.addIdRef(name);
        }
    }
}
