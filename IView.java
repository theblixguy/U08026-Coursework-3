package javafxapplication2;

import java.time.DayOfWeek;

/**
 *
 * @author suyashsrijan
 */
public interface IView {

    void clearScreen();
    void setupGUI();
    void writeErrorToOutput();
    void writeToOutput(String str);
    void updateOutputWithBuffer(String buf);
    void updateCurrentDay(DayOfWeek day);
}

