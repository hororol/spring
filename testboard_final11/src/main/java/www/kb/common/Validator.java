package www.kb.common;

public class Validator {
    public static String changeToString(String str) {
        if (str != null) {
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("'", "&#039;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("<br>", "\r\n;");
        }
        return str;
    }

    public static String changeToHtml(String str) {
        if (str != null) {
            str = str.replaceAll("&amp;", "&");
            str = str.replaceAll("&lt;", "<");
            str = str.replaceAll("&gt;", ">");
            str = str.replaceAll("&#039;", "'");
            str = str.replaceAll("&quot;", "\"");
            str = str.replaceAll("\r\n;", "<br>");
        }

        return str;
    }
}









