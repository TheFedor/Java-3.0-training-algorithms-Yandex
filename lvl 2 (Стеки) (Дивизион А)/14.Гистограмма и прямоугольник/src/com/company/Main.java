package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
	// 14.Гистограмма и прямоугольник - lvl2 (Дивизион А)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        br.close();


        String[] strArray = str.split(" ");
        int N = Integer.valueOf(strArray[0]);
        int[] array = new int[N];
        for (int i = 0; i < N; ++i)
            array[i] = Integer.valueOf(strArray[i+1]);

        Stack<int[]> stack = new Stack<>();

        long maxAria = 0;
        for (int i = 0; i < N; ++i)
        {
            if (stack.isEmpty() || array[i] > stack.peek()[1])
                stack.push(new int[] {i, array[i]});
            else {
                int ind = -1;
                while (!stack.isEmpty() && array[i] <= stack.peek()[1]) {
                    ind = stack.peek()[0];
                    int h = stack.pop()[1];
                    long area = h * (long)(i - ind);
                    if (area > maxAria)
                        maxAria = area;
                }
                stack.push(new int[] {ind, array[i]});
            }
        }
        long maxArea2 = stack.peek()[1] * (long)(N - stack.peek()[0]);
        if (stack.size() > 1)
        {
            stack.pop();
            int last = stack.peek()[1];
            int ind = -1;
            while (!stack.isEmpty())
            {
                if (last <= stack.peek()[1])
                {
                    ind = stack.peek()[0];
                    int h = stack.pop()[1];
                    long area = h * (long)(N - ind);
                    if (area > maxArea2)
                        maxArea2 = area;
                }
                else
                {
                    last = stack.peek()[1];
                }
            }
        }


        if (maxArea2 > maxAria)
            System.out.println(maxArea2);
        else
            System.out.println(maxAria);


    }
}
