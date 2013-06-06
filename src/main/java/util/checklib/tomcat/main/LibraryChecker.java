package util.checklib.tomcat.main;

import util.checklib.tomcat.output.OutputPrinter;

import java.io.File;

import static util.checklib.tomcat.docs.OutputFormatPrinter.explainOutputFormat;
import static util.checklib.tomcat.docs.UsagePrinter.tomcatHomeError;
import static util.checklib.tomcat.docs.UsagePrinter.usage;
import static util.checklib.tomcat.util.LibCheckerConstants.*;

public class LibraryChecker {

    public static void main(String[] args) throws Exception {
        boolean verbose = false;
        String reportFileName = null;

        if (args.length < 1) {
            usage();
            return;
        }

        if (args[0] != null && !args[0].equals("")) {

            if (!(new File(args[0])).exists()) {

                tomcatHomeError(args[0]);

                return;
            }
            System.out.println("Tomcat home detected: " + args[0]);
        } else {
            usage();
            return;
        }


        if (args[0].equals(OUTPUT_DOC)) {
            explainOutputFormat();
            return;
        }


        if (args.length > 1) {
            if (args.length < 5) {
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i];
                    if (arg != null && !arg.equals("")) {
                        if (arg.equals(VERBOSE_PARAM_NAME)) {
                            System.out.println("verbose mode detected! ");
                            verbose = true;
                        }
                        if (arg.equals(FILE_PARAM_NAME)) {
                            System.out.println("file writing mode detected! ");
                            int reportFileIndex = i + 1;
                            System.out.println("INDEX " + reportFileIndex);
                            if (reportFileIndex < args.length ) {
                                if (args[reportFileIndex].contains("/")) {
                                    reportFileName = args[reportFileIndex];
                                } else {
                                    usage();
                                    return;
                                }
                            } else {
                                usage();
                                return;
                            }
                        }

                    }
                }
            } else {
                usage();
                return;
            }
        }


        OutputPrinter printer;
        if (reportFileName != null) {
            printer = new OutputPrinter(reportFileName);
        } else {
            printer = new OutputPrinter();
        }


        DirectoryProcess processor = new DirectoryProcess(args[0], verbose, printer);
        System.out.println(" Start processing .... ");
        processor.searchWebappsLibDirectory();
        if (reportFileName!= null) {
            printer.flushToFile();
        }
        System.out.println(".... Stop processing");


    }


}
