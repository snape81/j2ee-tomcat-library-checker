package util.checklib.tomcat.docs;

public class UsagePrinter extends CommonPrinter {

    public static void usage() {
        applicationInfo();
        System.out.println("Usage: java -jar libChecker.jar <tomcat_home> (--file filepath) (--verbose)");
        System.out.println();
        System.out.println("MANDATORY PARAM");
        System.out.println("<tomcat_home>  the tomcat installation dir absolute path");
        System.out.println("i.e. /home/foo/tomcat that must contains webapps directory");
        System.out.println();
        System.out.println("OPTIONAL PARAM");
        System.out.println("(--file filepath) specify this parameter value absolute path if you want generate a file repo instead console output");
        System.out.println("i.e. --file /home/foo/myLibReport.txt");
        System.out.println();
        System.out.println("(--verbose) specify this parameter if you want to see all the library retrieved, not only problematic");
        System.out.println();
        System.out.println("OUTPUT_FORMAT");
        System.out.println("Type java -jar libChecker.jar output_doc for output info");

    }

    public static void tomcatHomeError(String thome){
        System.out.println(" ERROR: TOMCAT_HOME: " + thome + " does not exists!!!!! ");

    }


}
