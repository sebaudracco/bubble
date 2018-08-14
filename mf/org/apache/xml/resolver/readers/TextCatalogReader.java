package mf.org.apache.xml.resolver.readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;
import java.util.Vector;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogEntry;
import mf.org.apache.xml.resolver.CatalogException;

public class TextCatalogReader implements CatalogReader {
    protected boolean caseSensitive = false;
    protected InputStream catfile = null;
    protected int[] stack = new int[3];
    protected Stack tokenStack = new Stack();
    protected int top = -1;

    public void setCaseSensitive(boolean isCaseSensitive) {
        this.caseSensitive = isCaseSensitive;
    }

    public boolean getCaseSensitive() {
        return this.caseSensitive;
    }

    public void readCatalog(Catalog catalog, String fileUrl) throws MalformedURLException, IOException {
        URL catURL;
        try {
            catURL = new URL(fileUrl);
        } catch (MalformedURLException e) {
            catURL = new URL("file:///" + fileUrl);
        }
        try {
            readCatalog(catalog, catURL.openConnection().getInputStream());
        } catch (FileNotFoundException e2) {
            catalog.getCatalogManager().debug.message(1, "Failed to load catalog, file not found", catURL.toString());
        }
    }

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
                    int numArgs = CatalogEntry.getEntryArgCount(CatalogEntry.getEntryType(entryToken));
                    Vector args = new Vector();
                    if (unknownEntry != null) {
                        catalog.unknownEntry(unknownEntry);
                        unknownEntry = null;
                    }
                    int count = 0;
                    while (count < numArgs) {
                        try {
                            args.addElement(nextToken());
                            count++;
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
                    }
                    catalog.addEntry(new CatalogEntry(entryToken, args));
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

    protected void finalize() {
        if (this.catfile != null) {
            try {
                this.catfile.close();
            } catch (IOException e) {
            }
        }
        this.catfile = null;
    }

    protected String nextToken() throws IOException, CatalogException {
        String token = "";
        if (!this.tokenStack.empty()) {
            return (String) this.tokenStack.pop();
        }
        int nextch;
        do {
            int ch = this.catfile.read();
            while (ch <= 32) {
                ch = this.catfile.read();
                if (ch < 0) {
                    return null;
                }
            }
            nextch = this.catfile.read();
            if (nextch < 0) {
                return null;
            }
            if (ch == 45 && nextch == 45) {
                ch = 32;
                nextch = nextChar();
                while (true) {
                    if (!(ch == 45 && nextch == 45) && nextch > 0) {
                        ch = nextch;
                        nextch = nextChar();
                    }
                }
            } else {
                int[] iArr = this.stack;
                int i = this.top + 1;
                this.top = i;
                iArr[i] = nextch;
                iArr = this.stack;
                i = this.top + 1;
                this.top = i;
                iArr[i] = ch;
                ch = nextChar();
                if (ch == 34 || ch == 39) {
                    int quote = ch;
                    while (nextChar() != quote) {
                        token = token.concat(new String(new char[]{(char) nextChar()}));
                    }
                    return token;
                }
                while (ch > 32) {
                    nextch = nextChar();
                    if (ch == 45 && nextch == 45) {
                        iArr = this.stack;
                        i = this.top + 1;
                        this.top = i;
                        iArr[i] = ch;
                        iArr = this.stack;
                        i = this.top + 1;
                        this.top = i;
                        iArr[i] = nextch;
                        return token;
                    }
                    token = token.concat(new String(new char[]{(char) ch}));
                    ch = nextch;
                }
                return token;
            }
        } while (nextch >= 0);
        throw new CatalogException(8, "Unterminated comment in catalog file; EOF treated as end-of-comment.");
    }

    protected int nextChar() throws IOException {
        if (this.top < 0) {
            return this.catfile.read();
        }
        int[] iArr = this.stack;
        int i = this.top;
        this.top = i - 1;
        return iArr[i];
    }
}
