package opt.calculator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorService {

    public double calculate(String mathematicalExpression) {

        String parenthesis = "";
        String operation = "";
        while (true) {
            parenthesis = findParenthesis(mathematicalExpression);
            if (parenthesis.equals("Not found")) break;
            operation = findeOperations(parenthesis);
            mathematicalExpression = replaceString(mathematicalExpression, parenthesis, operation);
            mathematicalExpression = replaceString(mathematicalExpression, "+-", "-");
        }
        mathematicalExpression = findeOperations(mathematicalExpression);

        return Double.parseDouble(mathematicalExpression);
    }


    private String findParenthesis(String mathematicalExpression) {
        Pattern findBracket = Pattern.compile("\\([\\-]?\\d+(\\.\\d+)?([+\\-\\*\\/][\\-]?\\d+(\\.\\d+)?)+\\)");
        Matcher matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find())
            return matcher.group();
        else
            return "Not found";
    }


    private String findeOperations(String mathematicalExpression) {

        Pattern find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?\\/[\\-]?\\d+(\\.\\d+)?");
        mathematicalExpression = executeOperations(find, mathematicalExpression);

        find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?+\\*[\\-]?\\d+(\\.\\d+)?");
        mathematicalExpression = executeOperations(find, mathematicalExpression);

        find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?\\-\\d+(\\.\\d+)?");
        mathematicalExpression = executeOperations(find, mathematicalExpression);

        find = Pattern.compile("[\\-]?\\d+(\\.\\d+)?\\+\\d+(\\.\\d+)?");
        mathematicalExpression = executeOperations(find, mathematicalExpression);

        mathematicalExpression = mathematicalExpression.replace("(", "");
        mathematicalExpression = mathematicalExpression.replace(")", "");

        return mathematicalExpression;
    }

    private String executeOperations(Pattern find, String mathematicalExpression) {
        String operationNew = "";
        String operationFound = "";
        Matcher matcher = find.matcher(mathematicalExpression);
        while (matcher.find()) {
            operationFound = matcher.group();
            operationNew = divisionMathematicalExpression(operationFound);
            mathematicalExpression = replaceString(mathematicalExpression, operationFound, operationNew);
            mathematicalExpression = replaceString(mathematicalExpression, "+-", "-");

            matcher = find.matcher(mathematicalExpression);
        }

        return mathematicalExpression;
    }

    private String divisionMathematicalExpression(String mathematicalExpression) {
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

    double executeOperation(double firstNum, double secondNum, String operation) {
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
