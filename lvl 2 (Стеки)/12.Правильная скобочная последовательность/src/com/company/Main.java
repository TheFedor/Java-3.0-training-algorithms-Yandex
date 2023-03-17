package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
	// 12. Правильная скобочная последовательность

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        br.close();

        boolean answer = true;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); ++i)
        {
            char c = str.charAt(i);

            switch (c)
            {
                case (')'):
                    if (stack.isEmpty())
                    {
                        answer = false;
                        i = str.length();
                    }
                    else if (stack.peek() != '(')
                    {
                        answer = false;
                        i = str.length();
                    }
                    else
                    {
                        stack.pop();
                    }
                    break;
                case (']'):
                    if (stack.isEmpty())
                    {
                        answer = false;
                        i = str.length();
                    }
                    else if (stack.peek() != '[')
                    {
                        answer = false;
                        i = str.length();
                    }
                    else
                    {
                        stack.pop();
                    }
                    break;
                case ('}'):
                    if (stack.isEmpty())
                    {
                        answer = false;
                        i = str.length();
                    }
                    else if (stack.peek() != '{')
                    {
                        answer = false;
                        i = str.length();
                    }
                    else
                    {
                        stack.pop();
                    }
                    break;
                default:
                    stack.push(c);
                    break;
            }
        }

        if (stack.size() > 0)
        {
            answer = false;
        }

        if (answer)
        {
            System.out.println("yes");
        }
        else
        {
            System.out.println("no");
        }

    }
}
