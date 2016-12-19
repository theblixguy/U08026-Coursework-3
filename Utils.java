package javafxapplication2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author suyashsrijan
 */
public class Utils {
    // Check whether the number is within a given range (inclusive)
    public static boolean isBetween(int num, int start, int end) {
        return (num >= start && num <= end);
    }
    
    // Get current time (formatted)
    public static String getCurrentTime() {
        return LocalTime.now().getHour() + "TM" + LocalTime.now().getMinute() + "TM";
    }
    
    // Get current date (formatted)
    public static String getCurrentDate() {
        return LocalDate.now().getYear() + "DT" + LocalDate.now().getMonthValue() + "DT" + LocalDate.now().getDayOfMonth() + "DT";
    }
    
    // Get day of week from LocalDate
    public static DayOfWeek getDayOfWeekForDate(int year, int month, int day, boolean curDate) {
        if (curDate) {
            return LocalDate.now().getDayOfWeek();
        } else {
            return LocalDate.of(year, month, day).getDayOfWeek();
        }
    }
    
    // Return appropriate Expression class corresponding to the expression (if validated)
    public static Expression createExpression(String expression) throws InstantiationException {
        ArrayList<IValidator> validators = new ArrayList<>();
        validators.add(new DateExprValidator());
        validators.add(new TimeExprValidator());
        validators.add(new NumericExprValidator());
        
        for (int i = 0; i < validators.size(); i++) {
            if (validators.get(i).isValidExpression(expression)) {
                if (validators.get(i) instanceof DateExprValidator) {
                    return new DateExpression(expression, validators.get(i));
                }
                else if (validators.get(i) instanceof TimeExprValidator) {
                    return new TimeExpression(expression, validators.get(i));
                }
                else if (validators.get(i) instanceof NumericExprValidator) {
                    return new NumericExpression(expression, validators.get(i));
                }
            }
        }
        
        return null;
    }
}
