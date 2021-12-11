package opt.calculator.service;


import org.junit.Assert;
import org.junit.Test;


public class CalculatorServiceTest {
    CalculatorService calculatorService = new CalculatorService();
    @Test
    public void testExecuteOperationSum() {
        double result = calculatorService.executeOperation(10,5,"+");
        Assert.assertEquals(15, result, 0.001);
    }

    @Test
    public void testExecuteOperationDecrease() {
        double result = calculatorService.executeOperation(10,5,"-");
        Assert.assertEquals(5, result, 0.001);
    }

    @Test
    public void testExecuteOperationMultiplication() {
        double result = calculatorService.executeOperation(10,5,"*");
        Assert.assertEquals(50, result, 0.001);
    }

    @Test
    public void testExecuteOperationDivision() {
        double result = calculatorService.executeOperation(10,5,"/");
        Assert.assertEquals(2, result, 0.001);
    }

    @Test
    public void testFindParenthesis(){
        String parenthesis = calculatorService.findParenthesis("12+5*(1+2)");
        Assert.assertEquals("(1+2)", parenthesis);
    }

    @Test
    public void testFindParenthesisNotFound(){
        String parenthesis = calculatorService.findParenthesis("12+5*1+2");
        Assert.assertEquals("Not found", parenthesis);
    }
}
