package mf.org.apache.xml.resolver.readers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Vector;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogEntry;
import mf.org.apache.xml.resolver.CatalogException;

public class TR9401CatalogReader extends TextCatalogReader {
    public void readCatalog(Catalog catalog, InputStream is) throws MalformedURLException, IOException {
        CatalogException cex2;
        this.catfile = is;
        if (this.catfile != null) {
            Vector unknownEntry = null;
            while (true) {
                String token = nextToken();
                if (token == null) {
                    break;
                }
                try {
                    String entryToken;
                    if (this.caseSensitive) {
                        entryToken = token;
                    } else {
                        entryToken = token.toUpperCase();
                    }
                    if (entryToken.equals("DELEGATE")) {
                        entryToken = "DELEGATE_PUBLIC";
                    }
                    try {
                        int numArgs = CatalogEntry.getEntryArgCount(CatalogEntry.getEntryType(entryToken));
                        Vector args = new Vector();
                        if (unknownEntry != null) {
                            catalog.unknownEntry(unknownEntry);
                            unknownEntry = null;
                        }
                        for (int count = 0; count < numArgs; count++) {
                            args.addElement(nextToken());
                        }
                        catalog.addEntry(new CatalogEntry(entryToken, args));
                    } catch (CatalogException cex) {
                        Vector unknownEntry2 = unknownEntry;
                        try {
                            if (cex.getExceptionType() == 3) {
                                if (unknownEntry2 == null) {
                                    unknownEntry = new Vector();
                                } else {
                                    unknownEntry = unknownEntry2;
                                }
                                unknownEntry.addElement(token);
                            } else if (cex.getExceptionType() == 2) {
                                catalog.getCatalogManager().debug.message(1, "Invalid catalog entry", token);
                                unknownEntry = null;
                            } else if (cex.getExceptionType() == 8) {
                                catalog.getCatalogManager().debug.message(1, cex.getMessage());
                                unknownEntry = unknownEntry2;
                            } else {
                                unknownEntry = unknownEntry2;
                            }
                        } catch (CatalogException e) {
                            cex2 = e;
                            unknownEntry = unknownEntry2;
                        }
                    }
                } catch (CatalogException e2) {
                    cex2 = e2;
                }
            }
            if (unknownEntry != null) {
                catalog.unknownEntry(unknownEntry);
            }
            this.catfile.close();
            this.catfile = null;
            return;
        }
        return;
        if (cex2.getExceptionType() == 8) {
            catalog.getCatalogManager().debug.message(1, cex2.getMessage());
        }
    }
}
