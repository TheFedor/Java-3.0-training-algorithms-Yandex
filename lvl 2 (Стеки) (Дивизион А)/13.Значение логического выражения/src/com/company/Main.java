package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
	// 13.Значение логического выражения - lvl2 (Дивизион А)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String start = br.readLine();

        br.close();

        //переводим выражение в постфиксную запись
        List<Character> postfix = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < start.length(); ++i)
        {
            char c = start.charAt(i);
            switch (c)
            {
                case ('!'):
                    while (!stack.isEmpty() && stack.peek() == '!')
                        postfix.add(stack.pop());
                    stack.push(c);
                    break;
                case ('&'):
                    while (!stack.isEmpty() && (stack.peek() == '!' || stack.peek() == '&'))
                        postfix.add(stack.pop());
                    stack.push(c);
                    break;
                case ('|'):
                    while (!stack.isEmpty() && stack.peek() != '(')
                        postfix.add(stack.pop());
                    stack.push(c);
                    break;
                case ('^'):
                    while (!stack.isEmpty() && stack.peek() != '(')
                        postfix.add(stack.pop());
                    stack.push(c);
                    break;
                case ('('):
                    stack.push(c);
                    break;
                case (')'):
                    while (stack.peek() != '(')
                        postfix.add(stack.pop());
                    stack.pop();
                    break;
                default:
                    postfix.add(c);
                    break;
            }
        }

        while (!stack.isEmpty())
            postfix.add(stack.pop());

        //решаем выражение
        char oneChar;
        char twoChar;
        for (int i = 0; i < postfix.size(); ++i)
        {
            char c = postfix.get(i);
            switch (c)
            {
                case ('!'):
                    if (stack.peek() == '0')
                    {
                        stack.pop();
                        stack.push('1');
                    }
                    else
                    {
                        stack.pop();
                        stack.push('0');
                    }
                    break;
                case ('&'):
                    oneChar = stack.pop();
                    twoChar = stack.pop();
                    if (oneChar == '1' && twoChar == '1')
                        stack.push('1');
                    else
                        stack.push('0');
                    break;
                case ('|'):
                    oneChar = stack.pop();
                    twoChar = stack.pop();
                    if (oneChar == '1' || twoChar == '1')
                        stack.push('1');
                    else
                        stack.push('0');
                    break;
                case ('^'):
                    oneChar = stack.pop();
                    twoChar = stack.pop();
                    if (oneChar == twoChar)
                        stack.push('0');
                    else
                        stack.push('1');
                    break;
                default:
                    stack.push(c);
                    break;
            }
        }

        System.out.println(stack.pop().toString());

    }
}
