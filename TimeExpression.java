package javafxapplication2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author suyashsrijan
 */
public class TimeExpression extends Expression {
        
    public TimeExpression(String expression, IValidator validator) throws InstantiationException {
        super(expression, validator);
    }
    
    @Override
    public String evaluate() {
        final Pattern pattern1 = Pattern.compile(getValidator().getRegexs().get("TIME_REGEX_FULL"));
        final Pattern pattern2 = Pattern.compile(getValidator().getRegexs().get("TIME_REGEX_FULL_2"));
        final Matcher matcher1 = pattern1.matcher(getExpression());
        final Matcher matcher2 = pattern2.matcher(getExpression());
        String result = "";
        
        if (matcher1.matches()) {
            int hour1, hour2, min1, min2;
            LocalTime time1 = null, time2 = null;
            
            hour1 = Integer.parseInt(matcher1.group(2));
            hour2 = Integer.parseInt(matcher1.group(6));
            min1 = Integer.parseInt(matcher1.group(3));
            min2 = Integer.parseInt(matcher1.group(7));
            
            if (Utils.isBetween(hour1, 0, 23) && Utils.isBetween(min1, 0, 60)) {
                time1 = LocalTime.of(hour1, min1);
            }
            
            if (Utils.isBetween(hour2, 0, 23) && Utils.isBetween(min2, 0, 60)) {
                time2 = LocalTime.of(hour2, min2);
            }
            
            if (time1 != null && time2 != null) {
                result = Long.toString(Math.abs(Duration.between(time1, time2).toMinutes()));
            }
            
        }
        else if (matcher2.matches()) {
            int hour1, min1, minsToAdd;
            LocalTime time3 = null;
            
            hour1 = Integer.parseInt(matcher2.group(1));
            min1 = Integer.parseInt(matcher2.group(2));
            minsToAdd = Integer.parseInt(matcher2.group(4));
            
            if (Utils.isBetween(hour1, 0, 23) && Utils.isBetween(min1, 0, 60)) {
                time3 = LocalTime.of(hour1, min1);
                time3 = time3.plusMinutes(Long.valueOf(minsToAdd));
            }
            
            if (time3 != null) {
                result = Integer.toString(time3.getHour()) + ":" +
                        Integer.toString(time3.getMinute());
            }
            
        }
        
        return result;
    }
}
