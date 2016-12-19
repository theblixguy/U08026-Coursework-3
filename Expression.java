package javafxapplication2;

/**
 *
 * @author suyashsrijan
 */
public abstract class Expression {
    
    // String to hold the expression
    private final String expr;
    // String to hold the validator for the expression
    private final IValidator validator;
    
    public Expression(String expression, IValidator v) throws InstantiationException {
        if (v.isValidExpression(expression)) {
            this.expr = expression;
            this.validator = v;
        } else {
            throw new InstantiationException("Invalid expression passed");
        }
    }
    
    public String getExpression() {
        return this.expr;
    }
    
    public IValidator getValidator() {
        return this.validator;
    }
    
    public abstract String evaluate();
    
}
