package mf.org.apache.xml.resolver;

import java.util.Hashtable;
import java.util.Vector;

public class CatalogEntry {
    protected static Vector entryArgs = new Vector();
    protected static Hashtable entryTypes = new Hashtable();
    protected static int nextEntry = 0;
    protected Vector args = null;
    protected int entryType = 0;

    public static int addEntryType(String name, int numArgs) {
        entryTypes.put(name, new Integer(nextEntry));
        entryArgs.add(nextEntry, new Integer(numArgs));
        nextEntry++;
        return nextEntry - 1;
    }

    public static int getEntryType(String name) throws CatalogException {
        if (entryTypes.containsKey(name)) {
            Integer iType = (Integer) entryTypes.get(name);
            if (iType != null) {
                return iType.intValue();
            }
            throw new CatalogException(3);
        }
        throw new CatalogException(3);
    }

    public static int getEntryArgCount(String name) throws CatalogException {
        return getEntryArgCount(getEntryType(name));
    }

    public static int getEntryArgCount(int type) throws CatalogException {
        try {
            return ((Integer) entryArgs.get(type)).intValue();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CatalogException(3);
        }
    }

    public CatalogEntry(String name, Vector args) throws CatalogException {
        Integer iType = (Integer) entryTypes.get(name);
        if (iType == null) {
            throw new CatalogException(3);
        }
        int type = iType.intValue();
        try {
            if (((Integer) entryArgs.get(type)).intValue() != args.size()) {
                throw new CatalogException(2);
            }
            this.entryType = type;
            this.args = args;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CatalogException(3);
        }
    }

    public CatalogEntry(int type, Vector args) throws CatalogException {
        try {
            if (((Integer) entryArgs.get(type)).intValue() != args.size()) {
                throw new CatalogException(2);
            }
            this.entryType = type;
            this.args = args;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CatalogException(3);
        }
    }

    public int getEntryType() {
        return this.entryType;
    }

    public String getEntryArg(int argNum) {
        try {
            return (String) this.args.get(argNum);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void setEntryArg(int argNum, String newspec) throws ArrayIndexOutOfBoundsException {
        this.args.set(argNum, newspec);
    }
}
