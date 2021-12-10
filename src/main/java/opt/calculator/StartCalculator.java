package opt.calculator;

import opt.calculator.service.CalculatorService;

import java.util.Scanner;

public class StartCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CalculatorService calculatorService = new CalculatorService();
        System.out.println("Input mathematical expression");
        String mathematicalExpression = in.nextLine();
        System.out.println("Result is: "+calculatorService.calculate(mathematicalExpression));
    }
}
