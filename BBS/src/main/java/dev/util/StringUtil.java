package dev.util;

public class StringUtil {
	public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String GT = "&gt;";
    public static final String LT = "&lt";
    public static final String LF = "\n";
    public static final String CR = "\r";

    public static boolean isNullOrEmpty(final String str) {
    	return str == null ? true : str.isEmpty();
    }
    
    public static String stripToEmpty(final String str) {
        return str == null ? EMPTY : str.strip();
    }

    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }
    
    public static String replaceToMarkup(String str) {
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll("/", "&#47;");
		str = str.replaceAll("\n", "<br>");
		return str;
	}
}