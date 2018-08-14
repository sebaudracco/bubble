package mf.org.apache.xml.resolver.apps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogManager;
import mf.org.apache.xml.resolver.helpers.Debug;
import mf.org.apache.xml.resolver.tools.ResolvingParser;
import org.xml.sax.SAXException;

public class xparse {
    private static Debug debug = CatalogManager.getStaticManager().debug;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String xmlfile = null;
        int maxErrs = 10;
        boolean nsAware = true;
        boolean validating = true;
        boolean z = 0 > 2;
        Vector catalogFiles = new Vector();
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-c")) {
                i++;
                catalogFiles.add(args[i]);
            } else if (args[i].equals("-w")) {
                validating = false;
            } else if (args[i].equals("-v")) {
                validating = true;
            } else if (args[i].equals("-n")) {
                nsAware = false;
            } else if (args[i].equals("-N")) {
                nsAware = true;
            } else if (args[i].equals("-d")) {
                i++;
                try {
                    int debuglevel = Integer.parseInt(args[i]);
                    if (debuglevel >= 0) {
                        debug.setDebug(debuglevel);
                        z = debuglevel > 2;
                    }
                } catch (Exception e) {
                }
            } else if (args[i].equals("-E")) {
                i++;
                try {
                    int errs = Integer.parseInt(args[i]);
                    if (errs >= 0) {
                        maxErrs = errs;
                    }
                } catch (Exception e2) {
                }
            } else {
                xmlfile = args[i];
            }
            i++;
        }
        if (xmlfile == null) {
            System.out.println("Usage: org.apache.xml.resolver.apps.xparse [opts] xmlfile");
            System.exit(1);
        }
        ResolvingParser.validating = validating;
        ResolvingParser.namespaceAware = nsAware;
        ResolvingParser reader = new ResolvingParser();
        Catalog catalog = reader.getCatalog();
        for (int count = 0; count < catalogFiles.size(); count++) {
            catalog.parseCatalog((String) catalogFiles.elementAt(count));
        }
        XParseError xParseError = new XParseError(true, z);
        xParseError.setMaxMessages(maxErrs);
        reader.setErrorHandler(xParseError);
        String parseType = validating ? "validating" : Constants.DOM_WELLFORMED;
        String nsType = nsAware ? "namespace-aware" : "namespace-ignorant";
        if (maxErrs > 0) {
            System.out.println("Attempting " + parseType + ", " + nsType + " parse");
        }
        Date startTime = new Date();
        try {
            reader.parse(xmlfile);
        } catch (SAXException sx) {
            System.out.println("SAX Exception: " + sx);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        long millisec = new Date().getTime() - startTime.getTime();
        long secs = 0;
        long mins = 0;
        long hours = 0;
        if (millisec > 1000) {
            secs = millisec / 1000;
            millisec %= 1000;
        }
        if (secs > 60) {
            mins = secs / 60;
            secs %= 60;
        }
        if (mins > 60) {
            hours = mins / 60;
            mins %= 60;
        }
        if (maxErrs > 0) {
            System.out.print("Parse ");
            if (xParseError.getFatalCount() > 0) {
                System.out.print("failed ");
            } else {
                System.out.print("succeeded ");
                System.out.print("(");
                if (hours > 0) {
                    System.out.print(new StringBuilder(String.valueOf(hours)).append(":").toString());
                }
                if (hours > 0 || mins > 0) {
                    System.out.print(new StringBuilder(String.valueOf(mins)).append(":").toString());
                }
                System.out.print(new StringBuilder(String.valueOf(secs)).append(".").append(millisec).toString());
                System.out.print(") ");
            }
            System.out.print("with ");
            int errCount = xParseError.getErrorCount();
            int warnCount = xParseError.getWarningCount();
            if (errCount > 0) {
                System.out.print(new StringBuilder(String.valueOf(errCount)).append(" error").toString());
                System.out.print(errCount > 1 ? "s" : "");
                System.out.print(" and ");
            } else {
                System.out.print("no errors and ");
            }
            if (warnCount > 0) {
                System.out.print(new StringBuilder(String.valueOf(warnCount)).append(" warning").toString());
                System.out.print(warnCount > 1 ? "s" : "");
                System.out.print(".");
            } else {
                System.out.print("no warnings.");
            }
            System.out.println("");
        }
        if (xParseError.getErrorCount() > 0) {
            System.exit(1);
        }
    }
}
