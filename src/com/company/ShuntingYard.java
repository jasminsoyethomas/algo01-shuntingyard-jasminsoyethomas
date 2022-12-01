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
5.               While there's an operator on the top of the stack with greater OR EQUAL precedence:
6.                       Pop operators from the stack onto the output queue
7.               Push the current operator onto the stack
8.        If it's a left bracket push it onto the stack (parenthesis)
9.        If it's a right bracket
10.             While there's not a left bracket at the top of the stack:
                      Pop operators from the stack onto the output queue.
                Pop the left bracket from the stack and discard it
 While there are operators on the stack, pop them to the queue*/
        for (int i = 0; i < expression.length(); i++) {
            if (numberList.contains(expression.substring(i, i + 1))) {
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
        while (numbers.size() > 0) {
            System.out.print(numbers.remove());
        }

        return null;
    }

    public void addOperatorToStack(String operator) {
        //PEMDAS
        if (operators.size() > 0) {
            while (operators.size() > 0 && isSecondOperatorGreaterOrEqual(operator, operators.peek())) {
                if (operators.peek().equals(")"))
                    numbers.add(operators.pop());
            }
            operators.push(operator);
        } else {
            operators.push(operator);
        }

    }

    public void addNumberToQueue(String number) {
        numbers.add(number);
    }

    public void addNumberToStack(String number) {
        operators.push(number);
    }

    public boolean isSecondOperatorGreaterOrEqual(String firstOperator, String secondOperator) {
        if (secondOperator == null) {

        }
        if (firstOperator.equals("+") || firstOperator.equals("-")) {
            if (secondOperator.equals("*") || secondOperator.equals("/") || secondOperator.equals("+") || secondOperator.equals("-")) {
                return true;
            } else {
                return false;
            }
        } else if (firstOperator.equals("*") || firstOperator.equals("/")) {
            return true;
        } else if (firstOperator.equals("(")) {
            return false;
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

        for (int i = 0; i < expression.length(); i++) {
            System.out.println("Checking: " + expression.substring(i, i + 1));
            if (numberList.contains(expression.substring(i, i + 1))) {
                addNumberToStack(expression.substring(i, i + 1));
                expression = expression.substring(i + 1);
                i--;
            }
            //51+3+
            else if (operatorList.contains(expression.substring(i, i + 1))) {
                String x = operators.pop();
                String y = operators.pop();
                operators.push(expression.substring(i, i + 1));
                String operator = operators.pop();
                String newString = "(" + y + operator + x + ")";
                operators.push(newString);
                expression = expression.substring(i + 1);
                operators.push(expression);
                i--;
            }
            System.out.println(operators);
            System.out.println();
        }
        System.out.println(operators.size());
        return operators.pop();
    }

    public boolean isValidExpression(String expressionToCheck) {
        for (int i = 0; i < expressionToCheck.length(); i++) {
            //more than one pair of parenthesis?
            if ("(".contains(expressionToCheck.substring(i, i + 1))) {
                if (expressionToCheck.contains(")")) {

                } else {
                    return false;
                }
            }
            if (expressionToCheck.contains(operatorList) != true && expressionToCheck.contains(numberList) != true) {
                return false;
            }
            if (expressionToCheck.substring(i, i + 1).contains(operatorList)) {
                if (expressionToCheck.substring(i + 1, i + 2).contains(operatorList)) {
                    return false;
                } else {

                }
            }
        }
        //no letters
        //operatos toger 1++2
        //correct number of (), if u have ( then u have )
        return true;
    }

    public double evaluatePostFix(String expression) {
        //solve the equation
        int sum = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.substring(i, i + 1).contains(numberList)) {
                i = Integer.valueOf(i);
            }
            if (expression.substring(i, i + 1).contains(operatorList)) {
                if (expression.substring(i, i + 1).contains("+")) {
                    sum += (i+1);
                }
                if (expression.substring(i, i + 1).contains("-")) {
                    sum -= (i+1);
                }
                if (expression.substring(i, i + 1).contains("*")) {
                    sum *= (i+1);
                }
                if (expression.substring(i, i + 1).contains("/")) {
                    sum /= (i+1);
                }
            }
        }
        return sum;
    }
}
