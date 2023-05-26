package com.example.demo;

public class Expression {
    public void getter(String expression) {
        String parenthesisExpression;
        System.out.println(":::::Expression:::::\n" + expression);
        if (expression.contains("(") && expression.contains(")")) {
            System.out.println("Brackets spotted");
            parenthesisExpression = expression;
            checkParenthesis checkParenthesis = new checkParenthesis();
            checkParenthesis.pCheck (parenthesisExpression);
        } else if (expression.contains("(")) {
            System.err.println("One Bracket is invalid");
        } else if (expression.contains(")")) {
            System.err.println("One Bracket is invalid");
        } else if (!expression.contains("(") && !expression.contains(")")) {
            System.out.println("No Brackets/Brackets already calculated");
        }
    }
}