package com.example.examplemcpserver;

import org.springframework.ai.tool.annotation.Tool;

public class MathTool {

    @Tool(name = "add", description = "Adds two numbers")
    public int add(int a, int b) {
        return a + b;
    }

    @Tool(name = "subtract", description = "Subtracts two numbers")
    public int subtract(int a, int b) {
        return a - b;
    }

    @Tool(name = "multiply", description = "Multiplies two numbers")
    public int multiply(int a, int b) {
        return a * b;
    }

    @Tool(name = "divide", description = "Divides two numbers")
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return (double) a / b;
    }


}
