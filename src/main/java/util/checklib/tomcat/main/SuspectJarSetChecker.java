package util.checklib.tomcat.main;

import util.checklib.tomcat.util.JarnameParsingHelper;
import util.checklib.tomcat.util.LibCheckerConstants;

import java.util.List;

public class SuspectJarSetChecker {

    public static boolean isSuspect(List<String> listaDiJar, List<String> versions, String prefix) throws Exception {
        if (prefix.equals(LibCheckerConstants.COMMONS_PREFIX)) {
            return nameCheck(listaDiJar);
        } else {


            return nameCheck(listaDiJar) || versionCheck(versions);
        }

    }


    private static boolean nameCheck(List<String> listaDiJar) throws Exception {
        for (String toCheck : listaDiJar) {
            for (String actual : listaDiJar) {
                if (!toCheck.equals(actual)) {
                    boolean contains = actual.contains(toCheck.substring(0, JarnameParsingHelper.firstNumericCharIndex(toCheck)));
                    if (contains) return true;
                }
            }
        }
        return false;
    }

    private static boolean versionCheck(List<String> versions) throws Exception {
        for (String toCheck : versions) {
            for (String actual : versions) {
                if (!toCheck.equals(actual)) {
                    boolean contains = actual.contains(toCheck.substring(0, JarnameParsingHelper.firstNumericCharIndex(toCheck)));
                    if (contains) return true;
                }
            }
        }
        return false;
    }
}
