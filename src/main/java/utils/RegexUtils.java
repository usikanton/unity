package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static final String SORTING_DIRECTION_PATTERN = "direction=(asc|desc)";

    public static String find(String text, String pattern) {
        String result = "";
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        while (matcher.find())
            result = matcher.group();
        return result;
    }

    public static String find(String text, String pattern, int groupId) {
        String result = "";
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        while (matcher.find())
            result = matcher.group(groupId);
        return result;
    }
}
