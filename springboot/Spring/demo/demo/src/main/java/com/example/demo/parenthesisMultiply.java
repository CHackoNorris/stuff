package com.example.demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class parenthesisMultiply {
    public void Multiply(String toCalculate) {
        mergeList merger = new mergeList();
        String op, tempFailSafeOp;
        Double tempSum, tempFailSafeNum;
        System.out.println(":::::Multiply:::::");
        String[] numbers = toCalculate.split("[\\+\\-\\*\\/]");
        toCalculate = toCalculate.replaceAll("[0123456789.]", "");
        String[] operators = toCalculate.split("(?!^)");
        List<String> numbersList = new LinkedList<String>(Arrays.asList(numbers));
        List<String> operatorsList = new LinkedList<String>(Arrays.asList(operators));
        try {
            op = operatorsList.get(0);
            switch (op) {
                case "*":
                    tempSum = Double.parseDouble(numbersList.get(0)) * Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " * " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.merger(numbersList.toString(), operatorsList.toString());
                    break;
                case "/":
                    tempSum = Double.parseDouble(numbersList.get(0)) / Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " / " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.merger(numbersList.toString(), operatorsList.toString());
                    break;
                case "+":
                case "-":
                    tempFailSafeOp = operatorsList.get(0);
                    tempFailSafeNum = Double.parseDouble(numbersList.get(0));
                    operatorsList.remove(0);
                    numbersList.remove(0);
                    operatorsList.add(tempFailSafeOp);
                    numbersList.add(String.valueOf(tempFailSafeNum));
                    merger.merger(numbersList.toString(), operatorsList.toString());
                    break;
                default:
                    System.out.println(op);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("calculation done!");
        }


    }
}