package javafxapplication2;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author suyashsrijan
 */
public class DateExprValidator implements IValidator {
    
    // Regex to scan for expressions like 2014DT12DT10DT-2014DT12DT5DT
    public static final String DATE_REGEX_FULL = "^(([0-9]{4})[DT]{2}([0-9]{2})[DT]{2}([0-9]{2})[DT]{2}){1}([-]{1})(([0-9]{4})[DT]{2}([0-9]{2})[DT]{2}([0-9]{2})[DT]{2}){1}$";
    // Regex to scan for expressions like 2014DT12DT5DT+5
    public static final String DATE_REGEX_FULL_2 = "^(([0-9]{4})[DT]{2}([0-9]{2})[DT]{2}([0-9]{2})[DT]{2}){1}([+]{1})([0-9]+)$";
    
    // Check whether the expression is a valid Date expression as defined in the regex
        @Override
        public boolean isValidExpression(String expr) {
            final Pattern pattern1 = Pattern.compile(DATE_REGEX_FULL);
            final Pattern pattern2 = Pattern.compile(DATE_REGEX_FULL_2);
            final Matcher matcher1 = pattern1.matcher(expr);
            final Matcher matcher2 = pattern2.matcher(expr);
            return matcher1.matches() ^ matcher2.matches();
        }
        
        @Override
        public TreeMap<String, String> getRegexs() {
            TreeMap<String, String> map = new TreeMap();
            map.put("DATE_REGEX_FULL", DATE_REGEX_FULL);
            map.put("DATE_REGEX_FULL_2", DATE_REGEX_FULL_2);
            return map;
        }
}
