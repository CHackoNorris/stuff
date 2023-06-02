package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Merger {
    private MathExpressionChecker check = new MathExpressionChecker();
    public void lastMerger(String numbers, String operators) {

        int i1 = 0, i2 = 0;
        //hard to tell, but it combines to Lists into one String
        System.out.println(":::::Merger:::::");
        numbers = numbers.replaceAll("\\[", "").replaceAll("\\]", "");
        operators = operators.replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(numbers);
        System.out.println(operators);
        String[] numberS = numbers.split(",");
        String[] operatorS = operators.split(",");
        List<String> numbersList = new LinkedList<String>(Arrays.asList(numberS));
        List<String> operatorsList = new LinkedList<String>(Arrays.asList(operatorS));
        System.out.println(numbersList.toString());
        System.out.println(operatorsList.toString());

        ArrayList<String> toCalculateM = new ArrayList<String>();

        while(i1 < numbersList.size() || i2 < operatorsList.size()) {
            if(i1 < numbersList.size())
                toCalculateM.add(numbersList.get(i1++));
            if(i2 < operatorsList.size())
                toCalculateM.add(operatorsList.get(i2++));
        }
        System.out.println(toCalculateM.toString());
        String toCalculate = toCalculateM.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s+", "").replaceAll(",", "");
        System.out.println(toCalculate);
        check.lastCheck(toCalculate);
    }

    public void merger(String Numbers, String Operators, String constantExpression) {
        //get two Lists and merge them to one. And put it to String

        String numbers = Numbers, operators = Operators;
        int i1 = 0, i2 = 0;
        System.out.println(":::::Merger:::::");
        numbers = numbers.replaceAll("\\[", "").replaceAll("\\]", "");
        operators = operators.replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(numbers);
        System.out.println(operators);
        String[] numberS = numbers.split(",");
        String[] operatorS = operators.split(",");
        List<String> numbersList = new LinkedList<String>(Arrays.asList(numberS));
        List<String> operatorsList = new LinkedList<String>(Arrays.asList(operatorS));
        System.out.println(numbersList.toString());
        System.out.println(operatorsList.toString());

        ArrayList<String> toCalculateM = new ArrayList<String>();

        while(i1 < numbersList.size() || i2 < operatorsList.size()) {
            if(i1 < numbersList.size())
                toCalculateM.add(numbersList.get(i1++));
            if(i2 < operatorsList.size())
                toCalculateM.add(operatorsList.get(i2++));
        }
        System.out.println(toCalculateM.toString());
        String toCalculate = toCalculateM.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s+", "").replaceAll(",", "");
        System.out.println(toCalculate);
        check.pCalc(toCalculate, constantExpression);
    }

    public void remover(String toMerge, String originalExpression) {

        //searches for deepest bracket
        int lBarrier = 0, rBarrier = 0, failSafeCheck = 0;
        String failSafeChar;

        lBarrier = originalExpression.lastIndexOf("(");
        failSafeCheck = lBarrier - 1;
        rBarrier = originalExpression.indexOf(")") + 1;
        failSafeChar = String.valueOf(originalExpression.charAt(failSafeCheck));

        if (failSafeChar.equals("+") || failSafeChar.equals("-") || failSafeChar.equals("*") || failSafeChar.equals("/")) {
            originalExpression = originalExpression.substring(0, lBarrier) + toMerge + originalExpression.substring(rBarrier, originalExpression.length());
            System.out.println(originalExpression);
        } else {
            originalExpression = originalExpression.substring(0, lBarrier) + "*" + toMerge + originalExpression.substring(rBarrier, originalExpression.length());
            System.out.println(originalExpression);
        }
        check.checkForParentheses(originalExpression);
    }
}
