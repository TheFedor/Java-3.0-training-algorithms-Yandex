package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
	// 14.Сортировка вагонов lite - lvl2

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());
        int[] array = new int[N];
        String str = br.readLine();
        String[] strArray = str.split(" ");

        for (int i = 0; i < N; ++i)
        {
            array[i] = Integer.valueOf(strArray[i]);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(array[0]);

        int[] answer = new int[N];
        int c = 0;

        for (int i = 1; i < N; ++i)
        {
            while (!stack.isEmpty() && stack.peek() < array[i])
            {
                answer[c] = stack.pop();
                c++;
            }
            stack.push(array[i]);
        }

        while (!stack.isEmpty())
        {
            answer[c] = stack.pop();
            c++;
        }

        boolean result = true;

        for (int i = 0; i < N-1; ++i)
        {
            if (answer[i] > answer[i+1])
            {
                result = false;
                break;
            }
        }

        if (result)
        {
            System.out.println("YES");
        }
        else
        {
            System.out.println("NO");
        }
    }
}
