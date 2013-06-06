package util.checklib.tomcat.docs;

public class OutputFormatPrinter extends CommonPrinter{

    public static void explainOutputFormat() {
        applicationInfo();
        System.out.println("Output format looks like this: ");
        System.out.println("");
        System.out.println("For each webapp found this application prints a table ");
        System.out.println("");
        System.out.println("**********************************");
        System.out.println("*           WEBAPPNAME           *");
        System.out.println("**********************************");
        System.out.println("||PREFIX|JARLIST|VERSION|SUSPECT||");
        System.out.println();
        System.out.println("with 4 columns that are:");
        System.out.println("1 - PREFIX -- the macro groups of libraries ( i.e. commons) ");
        System.out.println("");
        System.out.println("2 - JARLIST -- all the jars found and grouped according to column PREFIX");
        System.out.println("  i.e. resteasy-jackson-provider-2.3.2.Final.jar,resteasy-jaxrs-2.3.2.Final.jar]");
        System.out.println();
        System.out.println("3 - VERSION -- the version of corresponding jar used for check");
        System.out.println();
        System.out.println("4 - SUSPECT -- Yes o No if it seems ther is a problem with libs group");
        System.out.println("    This field make sense in verbose mode ");
        System.out.println("    CAUTION OF THE FALSE POSITIVES ");

    }

}
