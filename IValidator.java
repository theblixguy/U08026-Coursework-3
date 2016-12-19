package javafxapplication2;

import java.util.TreeMap;

/**
 *
 * @author suyashsrijan
 */
public interface IValidator {
    
    boolean isValidExpression(String expr);
    TreeMap<String, String> getRegexs();
    
}
