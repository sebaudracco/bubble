package mf.org.apache.xerces.util;

public final class SecurityManager {
    private static final int DEFAULT_ENTITY_EXPANSION_LIMIT = 100000;
    private static final int DEFAULT_MAX_OCCUR_NODE_LIMIT = 3000;
    private int entityExpansionLimit = 100000;
    private int maxOccurLimit = 3000;

    public void setEntityExpansionLimit(int limit) {
        this.entityExpansionLimit = limit;
    }

    public int getEntityExpansionLimit() {
        return this.entityExpansionLimit;
    }

    public void setMaxOccurNodeLimit(int limit) {
        this.maxOccurLimit = limit;
    }

    public int getMaxOccurNodeLimit() {
        return this.maxOccurLimit;
    }
}
