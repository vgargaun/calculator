package opt.calculator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorService {

    private String numberRegex = "[\\-]?\\d+(\\.\\d+)";


    public double calculate(String mathematicalExpression) {

        boolean test = true;
        String parenthesis = "";
        String operation = "";
        String tesst = "";
        while (test) {
            parenthesis = findParenthesis(mathematicalExpression);
            if (parenthesis.equals("Not found")) break;
            operation = executeOperations(parenthesis);
            mathematicalExpression = replaceString(mathematicalExpression, parenthesis, operation);
            mathematicalExpression = replaceString(mathematicalExpression, "+-", "-");
            System.out.println(mathematicalExpression);
        }
        mathematicalExpression = executeOperations(mathematicalExpression);
        System.out.println(mathematicalExpression);

        return 0.2;
    }


    private String findParenthesis(String mathematicalExpression) {
        Pattern findBracket = Pattern.compile("\\([\\-]?\\d+(\\.\\d+)?([+\\-\\*\\/][\\-]?\\d+(\\.\\d+)?)+\\)");
        Matcher matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find())
            return matcher.group();
        else
            return "Not found";
    }


    private String executeOperations(String mathematicalExpression) {
        String operationNew = "";
        String operationFound = "";


        Pattern find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?\\/[\\-]?\\d+(\\.\\d+)?");
        Matcher matcher = find.matcher(mathematicalExpression);

        while (matcher.find()) {
            operationFound = matcher.group();
            operationNew = findOperation(operationFound);
            mathematicalExpression = replaceString(mathematicalExpression, operationFound, operationNew);
            mathematicalExpression = replaceString(mathematicalExpression, "+-", "-");
            matcher = find.matcher(mathematicalExpression);
        }
        find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?+\\*[\\-]?\\d+(\\.\\d+)?");
        matcher = find.matcher(mathematicalExpression);
        while (matcher.find()) {
            operationFound = matcher.group();
            operationNew = findOperation(operationFound);
            mathematicalExpression = replaceString(mathematicalExpression, operationFound, operationNew);
            mathematicalExpression = replaceString(mathematicalExpression, "+-", "-");
            matcher = find.matcher(mathematicalExpression);
        }


        find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?\\-\\d+(\\.\\d+)?");
        matcher = find.matcher(mathematicalExpression);
        while (matcher.find()) {
            operationFound = matcher.group();
            operationNew = findOperation(operationFound);
            mathematicalExpression = replaceString(mathematicalExpression, operationFound, operationNew);
            mathematicalExpression = replaceString(mathematicalExpression, "+-", "-");
            matcher = find.matcher(mathematicalExpression);
        }

        find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?\\+\\d+(\\.\\d+)?");
        matcher = find.matcher(mathematicalExpression);
        while (matcher.find()) {
            operationFound = matcher.group();
            operationNew = findOperation(operationFound);
            mathematicalExpression = replaceString(mathematicalExpression, operationFound, operationNew);
            mathematicalExpression = replaceString(mathematicalExpression, "+-", "-");
            matcher = find.matcher(mathematicalExpression);
        }

        return mathematicalExpression;
    }

    private String findOperation(String mathematicalExpression) {
        double firstNum = 0;
        double secondNum = 0;
        String operation = "";

        Pattern findBracket = Pattern.compile("[\\-]?\\d+(\\.\\d+)?");
        Matcher matcher = findBracket.matcher(mathematicalExpression);

        if (matcher.find()) {
            firstNum = Double.parseDouble(matcher.group(0));
        }
        if (matcher.find()) {
            secondNum = Double.parseDouble(matcher.group(0));
        } else return "Not found operation";
        findBracket = Pattern.compile("[\\+|\\-]");
        matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find()) {
            operation = matcher.group();
            operation = operation.replace("-", "+");
        }
        findBracket = Pattern.compile("\\*");
        matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find()) {
            operation = matcher.group();
        }

        findBracket = Pattern.compile("/");
        matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find()) {
            operation = matcher.group();
        }



        return String.valueOf(executeOperation(firstNum, secondNum, operation));

    }

    private String replaceString(String mathematicalExpression, String oldExpresion, String newException) {
        return mathematicalExpression.replace(oldExpresion, newException);


    }

    private double executeOperation(double firstNum, double secondNum, String operation) {
        switch (operation) {
            case "+":
                return firstNum + secondNum;
            case "-":
                return firstNum - secondNum;
            case "*":
                return firstNum * secondNum;
            case "/":
                return firstNum / secondNum;
            default:
                throw new RuntimeException("Incorrect operation");
        }

    }
}
