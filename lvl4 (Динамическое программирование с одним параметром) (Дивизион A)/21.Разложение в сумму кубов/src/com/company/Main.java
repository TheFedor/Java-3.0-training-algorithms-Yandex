package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// 21.Разложение в сумму кубов - lvl 4 (Дивизион А)

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.close();

        int maxNumber = (int)Math.cbrt(N);

        int[] array = new int[maxNumber];
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();

        int answer = 1;
        boolean b = true;

        for (int i = 1; i <= maxNumber; ++i)
        {
            array[i-1] = (int)Math.pow(i, 3);
            stack.push(array[i-1]);
            if (array[i-1] == N)
            {
                b = false;
            }
        }

        while (b)
        {
            answer++;
            int max = 0;
            int min = N;

            while (!stack.isEmpty() && b)
            {
                int number = stack.pop();
                for (int i = 0; i < maxNumber; ++i)
                {
                    if (number + array[i] < N)
                    {
                        set.add(number+array[i]);
                        if ((number+array[i]) > max)
                            max = number + array[i];
                        if ((number + array[i]) < min)
                            min = number + array[i];
                    }
                    else if (number+array[i] == N)
                    {
                        b = false;
                        break;
                    }
                    else
                        break;

                }
            }
            int middle = (min + max)/2;
            if (b)
            {
                for (Integer n : set)
                    if (n >= middle)
                        stack.push(n);
                set.clear();
            }
        }

        System.out.println(answer);

    }

}
