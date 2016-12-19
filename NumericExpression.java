package javafxapplication2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 * @author suyashsrijan
 */
public class NumericExpression extends Expression {
    
    public NumericExpression(String expression, IValidator validator) throws InstantiationException {
        super(expression, validator);
    }
    
    // Function to evaluate a numerical expression using Dijkstra's shunting-yard algorithm
    @Override
    public String evaluate() {
        TreeMap<String, Integer> precedence = new TreeMap<>();
        Stack<String> operators  = new Stack<>();
        Stack<Double> numbers = new Stack<>();
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(getExpression().split("")));
        
        precedence.put("+", 0);
        precedence.put("-", 0);
        precedence.put("*", 1);
        precedence.put("/", 1);
        
        while (!tokens.isEmpty()) {
            String token = tokens.get(0);
            tokens.remove(0);
            
            if (!precedence.containsKey(token)) {
                numbers.push(Double.parseDouble(token));
                continue;
            }
            
            while (true) {
                if (operators.isEmpty() || (precedence.get(token) > precedence.get(operators.peek()))) {
                    operators.push(token);
                    break;
                }
                
                String operator = operators.pop();
                double num2 = numbers.pop();
                double num1 = numbers.pop();
                numbers.push(evalNums(operator, num1, num2));
            }
        }
        
        while (!operators.isEmpty()) {
            String operator = operators.pop();
            double num2 = numbers.pop();
            double num1 = numbers.pop();
            numbers.push(evalNums(operator, num1, num2));
        }
        return Double.toString(numbers.pop());
    }
    
    // Evaluate two numbers, throw error on DBZ or invalid operator
    private static double evalNums(String operator, double num1, double num2) {
        if (operator.equals("+")) return num1 + num2;
        if (operator.equals("-")) return num1 - num2;
        if (operator.equals("/") && num2 == 0) throw new ArithmeticException("Divide by zero");
        if (operator.equals("/")) return num1 / num2;
        if (operator.equals("*")) return num1 * num2;
        throw new RuntimeException("Invalid operator, must be one of [+ - / *]");
    }
}
