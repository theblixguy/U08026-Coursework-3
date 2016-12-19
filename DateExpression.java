package javafxapplication2;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author suyashsrijan
 */
public class DateExpression extends Expression {
    
    public DateExpression(String expression, IValidator validator) throws InstantiationException {
        super(expression, validator);
    }
    
    // Evaluate a Date expression
    @Override
    public String evaluate() {
        final Pattern pattern1 = Pattern.compile(getValidator().getRegexs().get("DATE_REGEX_FULL"));
        final Pattern pattern2 = Pattern.compile(getValidator().getRegexs().get("DATE_REGEX_FULL_2"));
        final Matcher matcher1 = pattern1.matcher(getExpression());
        final Matcher matcher2 = pattern2.matcher(getExpression());
        String result = "";
        
        if (matcher1.matches()) {
            int year1, year2, month1, month2, day1, day2;
            LocalDate date1 = null, date2 = null;
            
            year1 = Integer.parseInt(matcher1.group(2));
            year2 = Integer.parseInt(matcher1.group(7));
            month1 = Integer.parseInt(matcher1.group(3));
            month2 = Integer.parseInt(matcher1.group(8));
            day1 = Integer.parseInt(matcher1.group(4));
            day2 = Integer.parseInt(matcher1.group(9));
            
            if (Utils.isBetween(year1, 1753, 2099) && Utils.isBetween(month1, 1, 12) && Utils.isBetween(day1, 1, 30)) {
                date1 = LocalDate.of(year1, month1, day1);
            }
            
            if (Utils.isBetween(year2, 1753, 2099) && Utils.isBetween(month2, 1, 12) && Utils.isBetween(day2, 1, 30)) {
                date2 = LocalDate.of(year2, month2, day2);
            }
            
            if (date1 != null && date2 != null) {
                result = Integer.toString(Math.abs(Period.between(date2, date1).getDays()));
            }
        }
        else if (matcher2.matches()) {
            int year1, month1, day1, daysToAdd;
            LocalDate date3 = null;
            year1 = Integer.parseInt(matcher2.group(2));
            month1 = Integer.parseInt(matcher2.group(3));
            day1 = Integer.parseInt(matcher2.group(4));
            daysToAdd = Integer.parseInt(matcher2.group(6));
            
            if (Utils.isBetween(year1, 1753, 2099) && Utils.isBetween(month1, 1, 12) && Utils.isBetween(day1, 1, 30)) {
                date3 = LocalDate.of(year1, month1, day1);
                date3 = date3.plusDays(Long.valueOf(daysToAdd));
            }
            
            if (date3 != null) {
                result = Integer.toString(date3.getYear()) + " - " +
                        date3.getMonthValue() + " - " +
                        date3.getDayOfMonth();
            }
        }
        return result;
    }
}
