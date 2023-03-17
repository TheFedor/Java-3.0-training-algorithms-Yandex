package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
	// 15.Великое Лайнландское переселение - lvl2

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());

        String str = br.readLine();
        br.close();
        String[] strArray = str.split(" ");
        int[] array = new int[N];
        for (int i = 0; i < N; ++i)
        {
            array[i] = Integer.valueOf(strArray[i]);
        }

        Stack<int[]> stack = new Stack();
        int[] answer = new int[N];

        for (int i = 0; i < N; ++i)
        {
            while (!stack.isEmpty() && stack.peek()[0] > array[i])
            {
                answer[stack.peek()[1]] = i;
                stack.pop();
            }
            stack.push(new int[] {array[i], i});
        }

        while (!stack.isEmpty())
        {
            answer[stack.peek()[1]] = -1;
            stack.pop();
        }

        for (int i = 0; i < N; ++i)
        {
            System.out.print(answer[i] + " ");
        }
    }
}
