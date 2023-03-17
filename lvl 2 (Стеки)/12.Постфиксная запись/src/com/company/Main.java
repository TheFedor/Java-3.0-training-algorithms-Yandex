package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        br.close();

        String[] strArray = str.split(" ");

        Stack<Integer> stack = new Stack<>();

        int first;
        int second;

        for (int i = 0; i < strArray.length; ++i)
        {
            switch (strArray[i])
            {
                case ("+"):
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first + second);
                    break;
                case ("-"):
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first - second);
                    break;
                case ("*"):
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first * second);
                    break;
                default:
                    stack.push(Integer.valueOf(strArray[i]));
                    break;
            }
        }

        int answer = stack.pop();
        System.out.println(answer);

    }
}
