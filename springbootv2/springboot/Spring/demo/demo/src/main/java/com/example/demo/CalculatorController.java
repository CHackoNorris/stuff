package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CalculatorController {

    @GetMapping("/runtest")
    public static String runtest(String expression) {
        return "<HTML>" +
                "<head>" +
                "<script type=\"text/javascript\">" +
                "function load(){" +
                "window.open(\"http://localhost:8080/calculate?expression=5(8-2(9%2F3)%2B25)*3\");" +
                "window.open(\"http://localhost:8080/calculate?expression=8%2F2(2%2B2)\");" +
                "}" +
                "</script>" +
                "</head>" +
                "<body onload=\"load()\">" +
                "</body>" +
                "</HTML>";
    }

    @GetMapping("/calculate")
    //gets expression from Front End as link
    public static String calculate(@RequestParam String expression) {
        long startTime = System.nanoTime();
        String result = "0";
        MathExpressionChecker checker = new MathExpressionChecker();
        if (checker.containsOnlyValidChars(expression)) {
            System.err.println("Invalid using Letters is forbidden");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Letters are not allowed");
        }
        if (!checker.correctParenPlacement(expression)) {
            System.err.println("Incorrect Parenthesis Placement");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Parentheses Placement");
        }
        checker.checkForParentheses(expression);

        long endTime = System.nanoTime();
        System.out.println("done in: " + "\033[1m" + (endTime - startTime) / 1000000 + "ms" + "\033[0m");
        return result;
    }
}