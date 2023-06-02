package com.example.demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Calculator {
    private Merger merger = new Merger();
    public void Multiply(String toCalculate, String constantExpression) {
        //yepp another time pretty self-explanatory

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
                    merger.merger(numbersList.toString(), operatorsList.toString(), constantExpression);
                    break;
                case "/":
                    tempSum = Double.parseDouble(numbersList.get(0)) / Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " / " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.merger(numbersList.toString(), operatorsList.toString(), constantExpression);
                    break;
                case "+":
                case "-":
                    tempFailSafeOp = operatorsList.get(0);
                    tempFailSafeNum = Double.parseDouble(numbersList.get(0));
                    operatorsList.remove(0);
                    numbersList.remove(0);
                    operatorsList.add(tempFailSafeOp);
                    numbersList.add(String.valueOf(tempFailSafeNum));
                    merger.merger(numbersList.toString(), operatorsList.toString(), constantExpression);
                    break;
                default:
                    System.out.println(op);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("calculation done!");
        }


    }

    public void Add(String toCalculate, String constantExpression) {
        //self-explanatory as well
        System.out.println(":::::Add:::::");

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
                    merger.merger(numbersList.toString(), operatorsList.toString(), constantExpression);
                    break;
                case "-":
                    tempSum = Double.parseDouble(numbersList.get(0)) - Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " - " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.merger(numbersList.toString(), operatorsList.toString(), constantExpression);
                    break;
                default:
                    System.out.println(toCalculate);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("calculation done!");
        }
    }

    public void Multiplication(String lastCalculation) {
        //self-explanatory

        String op, tempFailSafeOp;
        Double tempSum, tempFailSafeNum;
        System.out.println(":::::Multiply:::::");
        String[] numbers = lastCalculation.split("[\\+\\-\\*\\/]");
        lastCalculation = lastCalculation.replaceAll("[0123456789.]", "");
        String[] operators = lastCalculation.split("(?!^)");
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
                    merger.lastMerger(numbersList.toString(), operatorsList.toString());
                    break;
                case "/":
                    tempSum = Double.parseDouble(numbersList.get(0)) / Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " / " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.lastMerger(numbersList.toString(), operatorsList.toString());
                    break;
                case "+":
                case "-":
                    tempFailSafeOp = operatorsList.get(0);
                    tempFailSafeNum = Double.parseDouble(numbersList.get(0));
                    operatorsList.remove(0);
                    numbersList.remove(0);
                    operatorsList.add(tempFailSafeOp);
                    numbersList.add(String.valueOf(tempFailSafeNum));
                    merger.lastMerger(numbersList.toString(), operatorsList.toString());
                    break;
                default:
                    System.out.println(op);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("calculation done!");
        }
    }

    public void Addition(String lastCalculation) {
        System.out.println(":::::Add:::::");

        String op;
        Double tempSum;
        //Separates String into numbers and operators
        String[] numbers = lastCalculation.split("[\\+\\-\\*\\/]");
        lastCalculation = lastCalculation.replaceAll("[0123456789.]", "");
        String[] operators = lastCalculation.split("(?!^)");
        List<String> numbersList = new LinkedList<String>(Arrays.asList(numbers));
        List<String> operatorsList = new LinkedList<String>(Arrays.asList(operators));
        //try to prevent severe system failure
        try {
            op = operatorsList.get(0);
            switch (op) {
                case "+":
                    //does addition
                    tempSum = Double.parseDouble(numbersList.get(0)) + Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " + " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.lastMerger(numbersList.toString(), operatorsList.toString());
                    break;
                case "-":
                    //does subtraction
                    tempSum = Double.parseDouble(numbersList.get(0)) - Double.parseDouble(numbersList.get(1));
                    System.out.println(numbersList.get(0) + " - " + numbersList.get(1) + " = " + tempSum);
                    numbersList.set(0, String.valueOf(tempSum));
                    numbersList.remove(1);
                    operatorsList.remove(0);
                    merger.lastMerger(numbersList.toString(), operatorsList.toString());
                    break;
                default:
                    //also just in case
                    System.out.println(lastCalculation);
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: add.java Line 47");
        }
    }
}
