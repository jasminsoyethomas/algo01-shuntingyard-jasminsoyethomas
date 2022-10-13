package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYard {
    Stack<String> operators = new Stack<>();
    Queue<Integer> numbers = new LinkedList<Integer>();

   String operatorList = "+-*/";

    public String infixToPostFix(String expression) {
/*1 + 1 infix
    1 1 + postfix
    1.  While there are tokens to be read:
2.        Read a token
3.        If it's a number add it to queue
4.        If it's an operator
5.               While there's an operator on the top of the stack with greater precedence:
6.                       Pop operators from the stack onto the output queue
7.               Push the current operator onto the stack
8.        If it's a left bracket push it onto the stack (parenthesis)
9.        If it's a right bracket
10.            While there's not a left bracket
 */

        for(int i = 0; i<expression.length();i++){
             if(operatorList.contains(expression.substring(i,i+1))){
                 addOperatorToQueue(expression.substring(i,i+1));
             }
             if(expression.substring(i,i+1).equals("(")){
                 operators.push(expression.substring(i,i+1));
             }
        }

        return null;
    }

    public void addOperatorToQueue(String operator){
        //PEMDAS
        String operatorAtTopOfStack = operators.peek();
        while(isSecondOperatorGreater(operator, operatorAtTopOfStack)){
            Integer numFromStack = Integer.getInteger(operators.pop());
            numbers.add(numFromStack);
        }
        operators.push(operator);
    }

    public boolean isSecondOperatorGreater(String firstOperator, String secondOperator){
        // + - * /
        //false = secondOperator has lesser or equal precedence
        //true = secondOperator has greater or EQUAL precedence
        if(secondOperator == null){

        }
        if(firstOperator.equals("+")){
            if(secondOperator.equals("*")|| secondOperator.equals("/")){
                return true;
            }
            else{
                return false;
            }
        }
        if(firstOperator.equals("-")){
            if(secondOperator.equals("*")|| secondOperator.equals("/")){
                return true;
            }
            else{
                return false;
            }
        }
        if(firstOperator.equals("*")){
            if(secondOperator.equals("+")|| secondOperator.equals("-")){
                return false;
            }
            else{
                return true;
            }
        }
        if(firstOperator.equals("/")){
            if(secondOperator.equals("+")|| secondOperator.equals("-")){
                return false;
            }
            else{
                return true;
            }
        }
        return false;
    }

    public String postFixToInfix(String expression) {

        return null;
    }

    public boolean isValidExpression(String expressionToCheck) {

        return false;
    }

    public double evaluatePostFix(String expression) {
        return 0;
    }
}
