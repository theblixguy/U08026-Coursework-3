package javafxapplication2;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author suyashsrijan
 */
public class NumericExprValidator implements IValidator {
    
    // Regex to scan for numerical expressions like 2+2*5/8
    private static final String NUM_REGEX_FULL = "^([-+]?[0-9]*\\.?[0-9]+[\\/\\+\\-\\*])+([-+]?[0-9]*\\.?[0-9]+)$";
    
    // Check whether the expression is a valid numerical expression as defined in the regex
    @Override
    public boolean isValidExpression(String expr) {
        final Pattern pattern = Pattern.compile(NUM_REGEX_FULL);
        final Matcher matcher = pattern.matcher(expr);
        return matcher.matches();
    }
    
    @Override
    public TreeMap<String, String> getRegexs() {
        TreeMap<String, String> map = new TreeMap();
        map.put("NUM_REGEX_FULL", NUM_REGEX_FULL);
        return map;
    }
}
