package opt.calculator;

import opt.calculator.service.CalculatorService;

public class StartCalculator {
    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorService();
        calculatorService.calculate("5.23+5+10+(5+10*5*(5-5.98*10/2+(5+5)*5)+(5-5)/-2.2)");
    }
}
