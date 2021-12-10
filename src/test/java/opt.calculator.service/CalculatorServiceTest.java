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
    public void testExecuteOperationSumDivision() {
        double result = calculatorService.executeOperation(10,5,"/");
        Assert.assertEquals(2, result, 0.001);
    }
}
