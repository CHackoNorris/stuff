package com.example.demo;

public class checkParenthesis {
    public void pCheck(String parenthesisExpression) {
        int lBarrier = 0, rBarrier = 0;
        String checkParenthesisExpression = parenthesisExpression;

        lBarrier = checkParenthesisExpression.lastIndexOf("(") + 1;
        rBarrier = checkParenthesisExpression.indexOf(")");

        checkParenthesisExpression = checkParenthesisExpression.substring(lBarrier, rBarrier);

        System.out.println(":::::checkParenthesis:::::");
        System.out.println(checkParenthesisExpression);

        calculateParenthesis calculateParenthesis = new calculateParenthesis();
        calculateParenthesis.pCalc(checkParenthesisExpression);
    }
}