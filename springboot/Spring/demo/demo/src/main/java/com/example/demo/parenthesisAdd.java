package com.example.demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class parenthesisAdd {
    public void Add(String toCalculate) {
        System.out.println(":::::Add:::::");
        mergeList merger = new mergeList();
        String op;
        Double tempSum;
        String[] numbers = toCalculate.split("[\\+\\-\\*\\/]");
        toCalculate = toCalculate.replaceAll("[0123456789.]", "");
        String[] operators = toCalculate.split("(?!^)");
        List<String> numbersList = new LinkedList<String>(Arrays.asList(numbers));
        List<String> operatorsList = new LinkedList<String>(Arrays.asList(operators));
        try {
            op = operatorsList.get(0);
            switch (op) {
                case "+":
                    tempSum = Double.parseDouble(numbersList.get(0)) + Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " + " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.merger(numbersList.toString(), operatorsList.toString());
                    break;
                case "-":
                    tempSum = Double.parseDouble(numbersList.get(0)) - Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " - " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.merger(numbersList.toString(), operatorsList.toString());
                    break;
                default:
                    System.out.println(toCalculate);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("calculation done!");
        }
    }
}
