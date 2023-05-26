package com.example.demo;

public class calculateParenthesis {
    public void pCalc(String checkParenthesisExpression) {
        String calculateParenthesisExpression = checkParenthesisExpression;
        System.out.println(":::::whichCalculation:::::");

        if (calculateParenthesisExpression.contains("*") || calculateParenthesisExpression.contains("/")) {
            System.out.println("Multiply");
            parenthesisMultiply parenthesisMultiply = new parenthesisMultiply();
            parenthesisMultiply.Multiply(calculateParenthesisExpression);
        } else if (calculateParenthesisExpression.contains("+") || calculateParenthesisExpression.contains("-")) {
            System.out.println("Add");
            parenthesisAdd parenthesisAdd = new parenthesisAdd();
            parenthesisAdd.Add(calculateParenthesisExpression);
        } else {
            //remove bracket and replace in expression
        }
    }
}