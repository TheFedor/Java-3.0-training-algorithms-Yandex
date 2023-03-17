package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
	// 11.Конвейер - lvl2 (Дивизион А)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());
        String str;
        String[] strArray;
        float[] line;
        Stack<Float> stack = new Stack<>();
        List<Float> result = new ArrayList<>();

        for (int i = 0; i < N; ++i)
        {
            str = br.readLine();
            strArray = str.split(" ");
            line = new float[Integer.valueOf(strArray[0])];
            result.clear();
            for (int j = 0; j < line.length; ++j)
            {
                line[j] = Float.valueOf(strArray[j+1]);
            }

            //проверяем
            stack.push(line[0]);

            for (int j = 1; j < line.length; ++j)
            {
                while (!stack.isEmpty() && stack.peek() < line[j])
                {
                    result.add(stack.pop());
                }
                stack.push(line[j]);
            }
            while(!stack.isEmpty())
            {
                result.add(stack.pop());
            }

            boolean b = true;
            for (int j = 0; j < result.size()-1; ++j)
            {
                if (result.get(j) > result.get(j+1))
                {
                    b = false;
                    break;
                }
            }

            if (b)
                System.out.println(1);
            else
                System.out.println(0);

        }
        br.close();

    }
}
