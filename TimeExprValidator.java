package javafxapplication2;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author suyashsrijan
 */
public class TimeExprValidator implements IValidator {
    // Regex to scan for expressions like 23TM50TM-23TM15TM
    private static final String TIME_REGEX_FULL = "^(([0-9]{2})[TM]{2}([0-9]{2})[TM]{2}){1}([-]{1})(([0-9]{2})[TM]{2}([0-9]{2})[TM]{2}){1}$";
    // Regex to scan for expressions like 23TM15TM+35
    private static final String TIME_REGEX_FULL_2 = "^([0-9]{2})[TM]{2}([0-9]{2})[TM]{2}([+]{1})([0-9]+)$";
    
    @Override
    public boolean isValidExpression(String expr) {
        final Pattern pattern1 = Pattern.compile(TIME_REGEX_FULL);
        final Pattern pattern2 = Pattern.compile(TIME_REGEX_FULL_2);
        final Matcher matcher1 = pattern1.matcher(expr);
        final Matcher matcher2 = pattern2.matcher(expr);
        return matcher1.matches() ^ matcher2.matches();
    }
    
    @Override
    public TreeMap<String, String> getRegexs() {
        TreeMap<String, String> map = new TreeMap();
        map.put("TIME_REGEX_FULL", TIME_REGEX_FULL);
        map.put("TIME_REGEX_FULL_2", TIME_REGEX_FULL_2);
        return map;
    }
}
