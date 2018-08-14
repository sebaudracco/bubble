package mf.org.apache.xml.resolver.apps;

import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogManager;
import mf.org.apache.xml.resolver.helpers.Debug;
import mf.org.apache.xml.resolver.helpers.FileURL;
import mf.org.apache.xml.resolver.tools.CatalogResolver;

public class resolver {
    private static Debug debug = CatalogManager.getStaticManager().debug;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Catalog resolver;
        int count;
        String result;
        Vector catalogFiles = new Vector();
        int resType = 0;
        String resTypeStr = null;
        String name = null;
        String publicId = null;
        String systemId = null;
        String uri = null;
        boolean absoluteSystem = false;
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-c")) {
                i++;
                catalogFiles.add(args[i]);
            } else if (args[i].equals("-p")) {
                i++;
                publicId = args[i];
            } else if (args[i].equals("-s")) {
                i++;
                systemId = args[i];
            } else if (args[i].equals("-n")) {
                i++;
                name = args[i];
            } else if (args[i].equals("-u")) {
                i++;
                uri = args[i];
            } else if (args[i].equals("-a")) {
                absoluteSystem = true;
            } else if (args[i].equals("-d")) {
                i++;
                try {
                    int debuglevel = Integer.parseInt(args[i]);
                    if (debuglevel > 0) {
                        debug.setDebug(debuglevel);
                    }
                } catch (Exception e) {
                }
            } else {
                resTypeStr = args[i];
            }
            i++;
        }
        if (resTypeStr == null) {
            usage();
        }
        if (resTypeStr.equalsIgnoreCase("doctype")) {
            resType = Catalog.DOCTYPE;
            if (publicId == null && systemId == null) {
                System.out.println("DOCTYPE requires public or system identifier.");
                usage();
            }
        } else if (resTypeStr.equalsIgnoreCase("document")) {
            resType = Catalog.DOCUMENT;
        } else if (resTypeStr.equalsIgnoreCase("entity")) {
            resType = Catalog.ENTITY;
            if (publicId == null && systemId == null && name == null) {
                System.out.println("ENTITY requires name or public or system identifier.");
                usage();
            }
        } else if (resTypeStr.equalsIgnoreCase("notation")) {
            resType = Catalog.NOTATION;
            if (publicId == null && systemId == null && name == null) {
                System.out.println("NOTATION requires name or public or system identifier.");
                usage();
            }
        } else if (resTypeStr.equalsIgnoreCase(HeaderConstants.PUBLIC)) {
            resType = Catalog.PUBLIC;
            if (publicId == null) {
                System.out.println("PUBLIC requires public identifier.");
                usage();
            }
        } else if (resTypeStr.equalsIgnoreCase("system")) {
            resType = Catalog.SYSTEM;
            if (systemId == null) {
                System.out.println("SYSTEM requires system identifier.");
                usage();
            }
        } else if (resTypeStr.equalsIgnoreCase("uri")) {
            resType = Catalog.URI;
            if (uri == null) {
                System.out.println("URI requires a uri.");
                usage();
            }
        } else {
            System.out.println(new StringBuilder(String.valueOf(resTypeStr)).append(" is not a recognized keyword.").toString());
            usage();
        }
        if (absoluteSystem) {
            URL base;
            try {
                base = FileURL.makeURL("basename");
            } catch (MalformedURLException e2) {
                debug.message(1, "Malformed URL on cwd", System.getProperty("user.dir").replace('\\', '/'));
                base = null;
            }
            URL url;
            try {
                url = new URL(base, systemId);
                try {
                    systemId = url.toString();
                } catch (MalformedURLException e3) {
                    URL url2 = url;
                    try {
                        url = new URL("file:///" + systemId);
                    } catch (MalformedURLException e4) {
                        debug.message(1, "Malformed URL on system id", systemId);
                    }
                    resolver = new CatalogResolver().getCatalog();
                    for (count = 0; count < catalogFiles.size(); count++) {
                        resolver.parseCatalog((String) catalogFiles.elementAt(count));
                    }
                    result = null;
                    if (resType != Catalog.DOCTYPE) {
                        System.out.println("Resolve DOCTYPE (name, publicid, systemid):");
                        if (name != null) {
                            System.out.println("       name: " + name);
                        }
                        if (publicId != null) {
                            System.out.println("  public id: " + publicId);
                        }
                        if (systemId != null) {
                            System.out.println("  system id: " + systemId);
                        }
                        if (uri != null) {
                            System.out.println("        uri: " + uri);
                        }
                        result = resolver.resolveDoctype(name, publicId, systemId);
                    } else if (resType != Catalog.DOCUMENT) {
                        System.out.println("Resolve DOCUMENT ():");
                        result = resolver.resolveDocument();
                    } else if (resType != Catalog.ENTITY) {
                        System.out.println("Resolve ENTITY (name, publicid, systemid):");
                        if (name != null) {
                            System.out.println("       name: " + name);
                        }
                        if (publicId != null) {
                            System.out.println("  public id: " + publicId);
                        }
                        if (systemId != null) {
                            System.out.println("  system id: " + systemId);
                        }
                        result = resolver.resolveEntity(name, publicId, systemId);
                    } else if (resType != Catalog.NOTATION) {
                        System.out.println("Resolve NOTATION (name, publicid, systemid):");
                        if (name != null) {
                            System.out.println("       name: " + name);
                        }
                        if (publicId != null) {
                            System.out.println("  public id: " + publicId);
                        }
                        if (systemId != null) {
                            System.out.println("  system id: " + systemId);
                        }
                        result = resolver.resolveNotation(name, publicId, systemId);
                    } else if (resType != Catalog.PUBLIC) {
                        System.out.println("Resolve PUBLIC (publicid, systemid):");
                        if (publicId != null) {
                            System.out.println("  public id: " + publicId);
                        }
                        if (systemId != null) {
                            System.out.println("  system id: " + systemId);
                        }
                        result = resolver.resolvePublic(publicId, systemId);
                    } else if (resType != Catalog.SYSTEM) {
                        System.out.println("Resolve SYSTEM (systemid):");
                        if (systemId != null) {
                            System.out.println("  system id: " + systemId);
                        }
                        result = resolver.resolveSystem(systemId);
                    } else if (resType == Catalog.URI) {
                        System.out.println("resType is wrong!? This can't happen!");
                        usage();
                    } else {
                        System.out.println("Resolve URI (uri):");
                        if (uri != null) {
                            System.out.println("        uri: " + uri);
                        }
                        result = resolver.resolveURI(uri);
                    }
                    System.out.println("Result: " + result);
                }
            } catch (MalformedURLException e5) {
                url = new URL("file:///" + systemId);
                resolver = new CatalogResolver().getCatalog();
                for (count = 0; count < catalogFiles.size(); count++) {
                    resolver.parseCatalog((String) catalogFiles.elementAt(count));
                }
                result = null;
                if (resType != Catalog.DOCTYPE) {
                    System.out.println("Resolve DOCTYPE (name, publicid, systemid):");
                    if (name != null) {
                        System.out.println("       name: " + name);
                    }
                    if (publicId != null) {
                        System.out.println("  public id: " + publicId);
                    }
                    if (systemId != null) {
                        System.out.println("  system id: " + systemId);
                    }
                    if (uri != null) {
                        System.out.println("        uri: " + uri);
                    }
                    result = resolver.resolveDoctype(name, publicId, systemId);
                } else if (resType != Catalog.DOCUMENT) {
                    System.out.println("Resolve DOCUMENT ():");
                    result = resolver.resolveDocument();
                } else if (resType != Catalog.ENTITY) {
                    System.out.println("Resolve ENTITY (name, publicid, systemid):");
                    if (name != null) {
                        System.out.println("       name: " + name);
                    }
                    if (publicId != null) {
                        System.out.println("  public id: " + publicId);
                    }
                    if (systemId != null) {
                        System.out.println("  system id: " + systemId);
                    }
                    result = resolver.resolveEntity(name, publicId, systemId);
                } else if (resType != Catalog.NOTATION) {
                    System.out.println("Resolve NOTATION (name, publicid, systemid):");
                    if (name != null) {
                        System.out.println("       name: " + name);
                    }
                    if (publicId != null) {
                        System.out.println("  public id: " + publicId);
                    }
                    if (systemId != null) {
                        System.out.println("  system id: " + systemId);
                    }
                    result = resolver.resolveNotation(name, publicId, systemId);
                } else if (resType != Catalog.PUBLIC) {
                    System.out.println("Resolve PUBLIC (publicid, systemid):");
                    if (publicId != null) {
                        System.out.println("  public id: " + publicId);
                    }
                    if (systemId != null) {
                        System.out.println("  system id: " + systemId);
                    }
                    result = resolver.resolvePublic(publicId, systemId);
                } else if (resType != Catalog.SYSTEM) {
                    System.out.println("Resolve SYSTEM (systemid):");
                    if (systemId != null) {
                        System.out.println("  system id: " + systemId);
                    }
                    result = resolver.resolveSystem(systemId);
                } else if (resType == Catalog.URI) {
                    System.out.println("Resolve URI (uri):");
                    if (uri != null) {
                        System.out.println("        uri: " + uri);
                    }
                    result = resolver.resolveURI(uri);
                } else {
                    System.out.println("resType is wrong!? This can't happen!");
                    usage();
                }
                System.out.println("Result: " + result);
            }
        }
        resolver = new CatalogResolver().getCatalog();
        for (count = 0; count < catalogFiles.size(); count++) {
            resolver.parseCatalog((String) catalogFiles.elementAt(count));
        }
        result = null;
        if (resType != Catalog.DOCTYPE) {
            System.out.println("Resolve DOCTYPE (name, publicid, systemid):");
            if (name != null) {
                System.out.println("       name: " + name);
            }
            if (publicId != null) {
                System.out.println("  public id: " + publicId);
            }
            if (systemId != null) {
                System.out.println("  system id: " + systemId);
            }
            if (uri != null) {
                System.out.println("        uri: " + uri);
            }
            result = resolver.resolveDoctype(name, publicId, systemId);
        } else if (resType != Catalog.DOCUMENT) {
            System.out.println("Resolve DOCUMENT ():");
            result = resolver.resolveDocument();
        } else if (resType != Catalog.ENTITY) {
            System.out.println("Resolve ENTITY (name, publicid, systemid):");
            if (name != null) {
                System.out.println("       name: " + name);
            }
            if (publicId != null) {
                System.out.println("  public id: " + publicId);
            }
            if (systemId != null) {
                System.out.println("  system id: " + systemId);
            }
            result = resolver.resolveEntity(name, publicId, systemId);
        } else if (resType != Catalog.NOTATION) {
            System.out.println("Resolve NOTATION (name, publicid, systemid):");
            if (name != null) {
                System.out.println("       name: " + name);
            }
            if (publicId != null) {
                System.out.println("  public id: " + publicId);
            }
            if (systemId != null) {
                System.out.println("  system id: " + systemId);
            }
            result = resolver.resolveNotation(name, publicId, systemId);
        } else if (resType != Catalog.PUBLIC) {
            System.out.println("Resolve PUBLIC (publicid, systemid):");
            if (publicId != null) {
                System.out.println("  public id: " + publicId);
            }
            if (systemId != null) {
                System.out.println("  system id: " + systemId);
            }
            result = resolver.resolvePublic(publicId, systemId);
        } else if (resType != Catalog.SYSTEM) {
            System.out.println("Resolve SYSTEM (systemid):");
            if (systemId != null) {
                System.out.println("  system id: " + systemId);
            }
            result = resolver.resolveSystem(systemId);
        } else if (resType == Catalog.URI) {
            System.out.println("Resolve URI (uri):");
            if (uri != null) {
                System.out.println("        uri: " + uri);
            }
            result = resolver.resolveURI(uri);
        } else {
            System.out.println("resType is wrong!? This can't happen!");
            usage();
        }
        System.out.println("Result: " + result);
    }

    public static void usage() {
        System.out.println("Usage: resolver [options] keyword");
        System.out.println("");
        System.out.println("Where:");
        System.out.println("");
        System.out.println("-c catalogfile  Loads a particular catalog file.");
        System.out.println("-n name         Sets the name.");
        System.out.println("-p publicId     Sets the public identifier.");
        System.out.println("-s systemId     Sets the system identifier.");
        System.out.println("-a              Makes the system URI absolute before resolution");
        System.out.println("-u uri          Sets the URI.");
        System.out.println("-d integer      Set the debug level.");
        System.out.println("keyword         Identifies the type of resolution to perform:");
        System.out.println("                doctype, document, entity, notation, public, system,");
        System.out.println("                or uri.");
        System.exit(1);
    }
}
