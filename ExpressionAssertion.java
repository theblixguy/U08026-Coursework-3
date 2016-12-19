package javafxapplication2;

/**
 *
 * @author suyashsrijan
 */

@FunctionalInterface interface ExprRunnable { void run() throws Exception; }

class ExpressionAssertion{
    public static void assertDoesNotThrow(ExprRunnable action){
        try{
            action.run();
        }
        catch(Exception ex){
            throw new AssertionError("Exception caught while creating expression", ex);
        }
    }
}
