package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class mergeList {
    public void merger(String Numbers, String Operators) {
        calculateParenthesis calculateParenthesis = new calculateParenthesis();
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
        calculateParenthesis.pCalc(toCalculate);
    }
}
