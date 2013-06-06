package util.checklib.tomcat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JarnameParsingHelper {

    public static String versionExtractor(String toCheck) throws Exception {
        Pattern p = Pattern.compile("\\d\\.\\d.*\\.jar");
        Matcher m = p.matcher(toCheck);
        if (m.find()) {
            return m.group().replaceAll("\\.jar", LibCheckerConstants.EMPTY_STRING_REPLACEMENT);
        } else {
            return toCheck.replaceAll("\\.jar", "");
        }

    }

    public static int firstNumericCharIndex(String toCheck) throws Exception {
          Pattern p = Pattern.compile("\\d\\.");  // insert your pattern here
          Matcher m = p.matcher(toCheck);
          if (m.find()) {
              return m.start();
          }
          throw new Exception("No numeric char in jar name");
      }
}
