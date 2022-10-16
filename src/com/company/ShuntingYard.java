package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYard {
    Stack<String> operators = new Stack<>();
    Queue<Object> numbers = new LinkedList<>();

   String operatorList = "+-*/";
   String numberList = "0123456789";

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
10.             While there's not a left bracket at the top of the stack:
                      Pop operators from the stack onto the output queue.
                Pop the left bracket from the stack and discard it
 While there are operators on the stack, pop them to the queue*/
        for(int i = 0; i<expression.length();i++) {
            if (numberList.contains(expression.substring(i, i + 1))) {
                System.out.println("Found a number at position " + i);
                addNumberToQueue(expression.substring(i, i + 1));
            }
                if (operatorList.contains(expression.substring(i, i + 1))) {
                    addOperatorToStack(expression.substring(i, i + 1));
                }
                if (expression.substring(i, i + 1).equals("(")) {
                    operators.push(expression.substring(i, i + 1));
                }
                if (expression.substring(i, i + 1).equals(")")) {
                    while (!operators.peek().equals("(")) {
                        numbers.add(operators.pop());
                    }
                    operators.pop();
                }


            }
        while (operators.size() != 0) {
            String operatorFromStack = operators.pop();
            numbers.add(operatorFromStack);
        }

        System.out.println(numbers.size());

            while (numbers.size() > 0) {
                System.out.print(numbers.remove());
            }

        return null;
    }

    public void addOperatorToStack(String operator){
        //PEMDAS
        if(operators.size() > 0) {
            while(operators.size()>0 && isSecondOperatorGreater(operator, operators.peek())){
            //    System.out.println(operators.size() + "size");
                numbers.add(operators.pop());
            }
            operators.push(operator);
        }
        else{
            System.out.println("Adding lonely operator");
            operators.push(operator);
        }

    }

    public void addNumberToQueue(String number){
            System.out.println("Adding number to queue");
            numbers.add(number);
    }

    public boolean isSecondOperatorGreater(String firstOperator, String secondOperator){
        // + - * /
        //false = secondOperator has lesser or equal precedence
        //true = secondOperator has greater or EQUAL precedence
        if(secondOperator == null){

        }
        if(firstOperator.equals("+")||firstOperator.equals("-")){
            if(secondOperator.equals("*")|| secondOperator.equals("/")||secondOperator.equals("(")
                    ||secondOperator.equals(")")){
                return true;
            }
            else{
                return false;
            }
        }
        if(firstOperator.equals("*")||firstOperator.equals("/")){
            if(secondOperator.equals("+")|| secondOperator.equals("-")|secondOperator.equals("(")
                    ||secondOperator.equals(")")){
                return false;
            }
            else{
                return true;
            }
        }
        return false;
    }

    public String postFixToInfix(String expression) {
/*
Read the symbol from the input .based on the input symbol go to step 2 or 3.
If symbol is operand then push it into stack.
If symbol is operator then pop top 2 values from the stack.
this 2 popped value is our operand .
create a new string and put the operator between this operand in string.
push this string into stack.
At the end only one value remain in stack which is our infix expression.
 */
        return null;
    }

    public boolean isValidExpression(String expressionToCheck) {

        return false;
    }

    public double evaluatePostFix(String expression) {
        return 0;
    }
}
