package com.example.calc;

public class Calculations {

    public int calculate(int num, int secondNum, String action){
        switch (action){
            case "+":
                num += secondNum;
                break;
            case "-":
                num -= secondNum;
                break;
            case "*":
                num *= secondNum;
                break;
            case "/":
                num /= secondNum;
                break;
        }
        return num;
    }
}
