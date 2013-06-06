package util.checklib.tomcat.main;

import util.checklib.tomcat.output.OutputPrinter;
import util.checklib.tomcat.util.JarnameParsingHelper;
import util.checklib.tomcat.util.LibCheckerConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.checklib.tomcat.util.TextFormattingHelper.*;
import static util.checklib.tomcat.util.LibCheckerConstants.*;

public class DirectoryProcess {

    private String tomcatHome;

    private boolean verboseMode;

    private OutputPrinter printer;


    public DirectoryProcess(String tomcatHome, boolean verboseMode, OutputPrinter printer) {
        this.tomcatHome = tomcatHome;
        this.verboseMode = verboseMode;
        this.printer = printer;
    }

    public void searchWebappsLibDirectory() throws Exception {
        String webappsDirectoryPath = tomcatHome + SLASH + WEBAPPS_TOMCAT_BUILTIN_DIR;
        File webappsDir = new File(removeDoubleSlash(webappsDirectoryPath));
        for (String listedFilename : webappsDir.list()) {
            String listedFilePathname = removeDoubleSlash(webappsDirectoryPath + SLASH + listedFilename);
            File listedFile = new File(listedFilePathname);
            if (listedFile.isDirectory()) {

                File librayDir = new File(listedFilePathname + LibCheckerConstants.WEB_INF_LIB);
                if (librayDir.exists()) {
                    processJarList(librayDir, listedFile.getName());
                }
            }
        }
    }


    private void processJarList(File jarDirectory, String webappName) throws Exception {


        HashMap<String, List<String>> mappaDipendenze = new HashMap<String, List<String>>();
        HashMap<String, List<String>> mappaVersioni = new HashMap<String, List<String>>();

        int prefixLength = 0;
        int jarnameLength = 0;
        int versionLength = 0;

        for (String actual : jarDirectory.list()) {
            if (jarnameLength < actual.length()) jarnameLength = actual.length();
            String version = JarnameParsingHelper.versionExtractor(actual);
            if (versionLength < version.length()) versionLength = version.length();
            String prefix = null;
            try {
                prefix = actual.substring(0, actual.indexOf("-"));
            } catch (Exception e) {
                String errorPrefixCutting = "PREFIX CUTTING - Error with this string " + actual + " -- SKIP MANUALLY CHECK REQUESTED EXCEPTION: " + e.getMessage();
                System.out.println(errorPrefixCutting);
                printer.writeln(errorPrefixCutting);
                continue;
            }
            if (prefixLength < prefix.length()) prefixLength = prefix.length();

            if (mappaDipendenze.get(prefix) == null) {
                mappaDipendenze.put(prefix, new ArrayList<String>());
                mappaVersioni.put(prefix, new ArrayList<String>());
            }
            mappaDipendenze.get(prefix).add(actual);
            mappaVersioni.get(prefix).add(version);

        }
        printer.emptyLine();
        printer.emptyLine();
        printer.emptyLine();
        printer.writeln(createSeparator(prefixLength, jarnameLength, versionLength, "*"));
        printer.writeln(createWebappsTitle(prefixLength, jarnameLength, versionLength, webappName, "*"));
        printer.writeln(createSeparator(prefixLength, jarnameLength, versionLength, "*"));
        printer.writeln(createSeparator(prefixLength, jarnameLength, versionLength, "="));
        printer.writeln(createLine(prefixLength, jarnameLength, versionLength, PREFIX, JARLIST, VERSION, SUSPECT));
        printer.writeln(createSeparator(prefixLength, jarnameLength, versionLength, "="));
        for (String libraryPrefix : mappaDipendenze.keySet()) {
            List<String> jars = mappaDipendenze.get(libraryPrefix);
            boolean suspect = SuspectJarSetChecker.isSuspect(jars, mappaVersioni.get(libraryPrefix), libraryPrefix);
            if (suspect || verboseMode) {
                for (int i = 0; i < jars.size(); i++) {
                    if (i == 0) {

                        printer.writeln(createLine(prefixLength, jarnameLength, versionLength, libraryPrefix, jars.get(i), mappaVersioni.get(libraryPrefix).get(i), translateSuspectResult(suspect)));
                    } else {
                        printer.writeln(createLine(prefixLength, jarnameLength, versionLength, "", jars.get(i), mappaVersioni.get(libraryPrefix).get(i), ""));
                    }


                }
                printer.writeln(createSeparator(prefixLength, jarnameLength, versionLength, "="));
            }

        }

        printer.emptyLine();
        printer.emptyLine();

    }

}
