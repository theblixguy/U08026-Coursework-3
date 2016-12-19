package javafxapplication2;

import java.util.ArrayList;
/**
 *
 * @author suyashsrijan
 */
public class CalculatorController implements ICalculator {
    
    private final IView view;
    // Numers of characters allowed on the output view
    private static final int MAX_CHARS = 20;
    
    public CalculatorController(IView view) {
        this.view = view;
    }
    
    // String buffer to store expression and result
    String buffer = new String();
    // Store history of button presses
    ArrayList<String> history = new ArrayList<>();
    
    // Handle button press
    @Override
    public void handleButtonPress(String buttonName) {
        switch (buttonName) {
            case "=":
                try {
                    Expression exp = Utils.createExpression(buffer);
                    view.clearScreen();
                    String result = exp.evaluate();
                    if (result.equals("")) {
                        view.writeErrorToOutput();
                    } else {
                        view.writeToOutput(result);
                    }
                } catch (InstantiationException | ArithmeticException e) {
                    view.writeErrorToOutput();
                }
                flushBuffer();
                break;
            case "AC":
                flushBuffer();
                history.clear();
                view.clearScreen();
                break;
            case "C":
                if (!history.isEmpty()) {
                    buffer = buffer.substring(0, (buffer.length() - history.get(history.size() - 1).length()));
                    history.remove(history.size() - 1);
                    view.clearScreen();
                    System.out.println("Buffer: " + buffer);
                } else {
                    flushBuffer();
                    view.clearScreen();
                }
                break;
            case "TIME":
                buffer += Utils.getCurrentTime();
                view.updateOutputWithBuffer(buffer);
                break;
            case "MD":
                buffer += Utils.getCurrentDate();
                view.updateOutputWithBuffer(buffer);
                view.updateCurrentDay(Utils.getDayOfWeekForDate(0, 0, 0, true));
                break;
            case "ST":
                view.writeErrorToOutput();
                break;
            default:
                if (buffer.length() + buttonName.length() <= MAX_CHARS) {
                    buffer += buttonName;
                    view.writeToOutput(buttonName);
                    history.add(buttonName);
                }
                break;
        }
        System.out.println("Button pressed: " + buttonName);
    }
    
    // Clear the buffer
    @Override
    public void flushBuffer() {
        buffer = "";
    }
    
}
