package opt.calculator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorService {

    public double calculate(String mathematicalExpression) {

        String parenthesis;
        String operation;
        while (true) {
            parenthesis = findParenthesis(mathematicalExpression);
            if (parenthesis.equals("Not found")) break;
            operation = findeOperations(parenthesis);
            mathematicalExpression = mathematicalExpression.replace(parenthesis, operation)
                    .replace("+-", "-");
        }
        mathematicalExpression = findeOperations(mathematicalExpression);

        return Double.parseDouble(mathematicalExpression);
    }


    public String findParenthesis(String mathematicalExpression) {
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
        String result = "";
        String operationFound = "";
        Matcher matcher = find.matcher(mathematicalExpression);
        while (matcher.find()) {
            operationFound = matcher.group();
            result = divisionMathematicalExpression(operationFound);
            mathematicalExpression = mathematicalExpression.replace(operationFound, result)
                    .replace("+-", "-");
            matcher = find.matcher(mathematicalExpression);
        }

        return mathematicalExpression;
    }

    private String divisionMathematicalExpression(String mathematicalExpression) {
        double firstNum;
        double secondNum;
        String operationSign = "";

        Pattern findBracket = Pattern.compile("[\\-]?\\d+(\\.\\d+)?");
        Matcher matcher = findBracket.matcher(mathematicalExpression);

        if (matcher.find()) {
            firstNum = Double.parseDouble(matcher.group(0));
        } else return "Not found number";
        if (matcher.find()) {
            secondNum = Double.parseDouble(matcher.group(0));
        } else return "Not found number";
        findBracket = Pattern.compile("[\\+|\\-]");
        matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find()) {
            operationSign = matcher.group().replace("-", "+");
        }
        findBracket = Pattern.compile("\\*");
        matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find()) {
            operationSign = matcher.group();
        }

        findBracket = Pattern.compile("/");
        matcher = findBracket.matcher(mathematicalExpression);
        if (matcher.find()) {
            operationSign = matcher.group();
        }

        return String.valueOf(executeOperation(firstNum, secondNum, operationSign));

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
