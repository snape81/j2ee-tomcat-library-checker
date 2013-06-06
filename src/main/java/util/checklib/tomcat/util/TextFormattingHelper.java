package util.checklib.tomcat.util;

import static util.checklib.tomcat.util.LibCheckerConstants.*;

public class TextFormattingHelper {

    public static String removeDoubleSlash(String listedFilePathname) {
         return listedFilePathname.replaceAll(DOUBLE_SLASH_REGEX, SLASH);
     }

    public static String createWebappsTitle(int prefixLength, int jarnameLength, int versionLength, String webappName, String fieldSeparator) {
          int total = 7 + prefixLength + jarnameLength + SUSPECT.length() + versionLength;
          StringBuilder sb = new StringBuilder();
          sb.append(fieldSeparator);
          int padding = total - webappName.length() - 2;
          int paddingLeft = padding / 2;
          int paddingRight = padding - paddingLeft;
          appendPadding(paddingLeft, sb);
          sb.append(webappName.toUpperCase());
          appendPadding(paddingRight,sb);
          sb.append(fieldSeparator);
          return sb.toString();
      }


      public static String createSeparator(int prefix, int jarname, int version, String type) {
          int total = 7 + prefix + jarname + SUSPECT.length() + version;
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i <= total; i++) {
              sb.append(type);
          }
          return sb.toString();
      }

      public static String createLine(int prefix, int jarname, int versionLength, String prefixName, String jarName, String version, String suspect) {
          StringBuilder sb = new StringBuilder();
          sb.append("||");
          sb.append(prefixName);
          appendPadding(prefix, prefixName.length(), sb);
          sb.append("|");
          sb.append(jarName);
          appendPadding(jarname, jarName.length(), sb);
          sb.append("|");
          sb.append(version);
          appendPadding(versionLength, version.length(), sb);
          sb.append("|");
          sb.append(suspect);
          appendPadding(SUSPECT.length(), suspect.length(), sb);
          sb.append("||");
          return sb.toString();
      }

    private static void appendPadding(int length, StringBuilder sb) {
          appendPadding(length, 0, sb);
      }

      private static void appendPadding(int maxLength, int actualLength, StringBuilder sb) {
          int diff = maxLength - actualLength;
          if (diff > 0) {
              for (int i = 0; i < diff; i++) {
                  sb.append(" ");
              }
          }

      }

      public static String translateSuspectResult(boolean suspect) {
          if (suspect) return YES;
          return NO;
      }

}
