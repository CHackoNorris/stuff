package com.example.demo;

public class MathExpressionChecker {
    /**
     * Checks if mathExpression parenthesis are placed correctly
     * @param mathExpression math expression that only contains correct characters
     * @return returns false (Parentheses "(" and ")" must be equally)true statement
     */
    public boolean correctParenPlacement(String mathExpression) {
        int j = 0;
        for (int i = 0; i < mathExpression.length(); i++) {
            if (mathExpression.charAt(i) == '(') {
                j++;
            } else if (mathExpression.charAt(i) == ')') {
                j--;
            }
            if (j < 0) {
                break;
            }
        }
        return j == 0;
    }

    public Boolean containsOnlyValidChars(String mathExpression) {
        return mathExpression.matches(".*[^0-9.\\+\\-\\/\\*\\(\\)].*");
    }

    public void lastCheck(String finalCheck) {
        if (finalCheck.contains("*") || finalCheck.contains("/")) {
            //multiplies String
            Calculator multiply = new Calculator();
            multiply.Multiplication(finalCheck);
        } else if (finalCheck.contains("+") || finalCheck.contains("-")) {
            //additions String
            Calculator add = new Calculator();
            add.Addition(finalCheck);
        } else {
            //ALL calculations completed (supposed to)

            System.out.println("\u001B[32m" + "*************************\n" + "* done!                 *\n" + "* " + "\033[1m" + finalCheck + "\033[0m" + "\u001B[32m" + "\n*************************" + "\u001B[0m");
            //CalculatorController.finalCheck(finalCheck);
        }
    }

    public void pCheck(String parenthesisExpression, String constantExpression) {
        //get to the deepest bracket with lastIndex and (first)Index
        int lBarrier = 0, rBarrier = 0;
        String checkParenthesisExpression = parenthesisExpression;

        lBarrier = checkParenthesisExpression.lastIndexOf("(") + 1;
        rBarrier = checkParenthesisExpression.indexOf(")");

        checkParenthesisExpression = checkParenthesisExpression.substring(lBarrier, rBarrier);

        System.out.println(":::::checkParenthesis:::::");
        System.out.println(checkParenthesisExpression);

        MathExpressionChecker calculateParenthesis = new MathExpressionChecker();
        calculateParenthesis.pCalc(checkParenthesisExpression, constantExpression);
    }

    public void checkForParentheses(String expression) {
        String parenthesisExpression;
        if (expression.startsWith("(")) {
            expression = "1*" + expression;
        }
        String constantExpression = expression;
        System.out.println(":::::Expression:::::\n" + expression);
        //check if calculation has Parenthesis
        if (expression.contains("(") && expression.contains(")")) {
            System.out.println("Parentheses spotted");
            parenthesisExpression = expression;
            MathExpressionChecker checkParenthesis = new MathExpressionChecker();
            checkParenthesis.pCheck(parenthesisExpression, constantExpression);
            //invalid Parenthesis will abort process
        } else if (expression.contains("(")) {
            System.err.println("One Parenthesis is invalid");
        } else if (expression.contains(")")) {
            System.err.println("One Parenthesis is invalid");
        } else if (!expression.contains("(") && !expression.contains(")")) {
            System.out.println("No Parentheses/Parentheses already calculated");
            MathExpressionChecker check = new MathExpressionChecker();
            check.lastCheck(expression);
        }
    }

    public void pCalc(String checkParenthesisExpression, String constantExpression) {
        //Check if Multiplication or Addition
        String calculateParenthesisExpression = checkParenthesisExpression;
        System.out.println(":::::whichCalculation:::::");

        if (calculateParenthesisExpression.contains("*") || calculateParenthesisExpression.contains("/")) {
            System.out.println("Multiply");
            Calculator parenthesisMultiply = new Calculator();
            parenthesisMultiply.Multiply(calculateParenthesisExpression, constantExpression);
        } else if (calculateParenthesisExpression.contains("+") || calculateParenthesisExpression.contains("-")) {
            System.out.println("Add");
            Calculator parenthesisAdd = new Calculator();
            parenthesisAdd.Add(calculateParenthesisExpression, constantExpression);
        } else {
            //remove bracket and replace in expression
            Merger mergeBrackets = new Merger();
            mergeBrackets.remover(checkParenthesisExpression, constantExpression);
        }
    }
}
