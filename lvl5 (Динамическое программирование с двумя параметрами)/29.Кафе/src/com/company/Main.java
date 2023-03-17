package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// 29.Кафе - lvl5

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] costs = new int[N];

        int moreThan100 = 1;
        for (int i = 0; i < N; ++i)
        {
            costs[i] = sc.nextInt();
            if (costs[i] > 100)
                moreThan100++;
        }
        sc.close();

        int[][] dp = new int[moreThan100][N+1];

        for (int i = 0; i < moreThan100; ++i)
            dp[i][0] = 31000;

        dp[0][0] = 0;

        for (int j = 1; j <= N; ++j)
        {
            for (int i = 0; i < moreThan100; ++i)
            {
                int dayPrice = costs[j-1];
                if (dayPrice <= 100)
                {
                    if (dayPrice == 0)
                    {
                        dp[i][j] = dp[i][j-1];
                    }
                    else
                    {
                        if (i+1 == moreThan100)
                        {
                            dp[i][j] = dp[i][j-1] + dayPrice;
                        }
                        else
                        {
                            dp[i][j] = Math.min(dp[i+1][j-1], dp[i][j-1] + dayPrice);
                        }
                    }
                }
                else
                {
                    if (i-1 < 0)
                    {
                        //с ограничением сверху
                        dp[i][j] = dp[i+1][j-1];
                    }
                    else if (i+1 == moreThan100)
                    {
                        //с ограничением снизу
                        dp[i][j] = dp[i-1][j-1] + dayPrice;
                    }
                    else
                    {
                        //без ограничений
                        dp[i][j] = Math.min(dp[i-1][j-1] + dayPrice, dp[i+1][j-1]);
                    }
                }
            }
        }

        //выписываем минимальную плату, запоминая ее положение в таблице
        int lineMinPrice = 0;
        int columnMinPrice = N;
        int minPrice = 31000;
        for (int i = 0; i < moreThan100; ++i)
        {
            if (dp[i][N] < minPrice)
            {
                minPrice = dp[i][N];
                lineMinPrice = i;
            }
        }

        //выводим минимальную общую цену
        System.out.println(minPrice);
        //выписываем сколько купонов осталось
        System.out.print(lineMinPrice + " ");

        //ищем когда тратили купоны
        List<Integer> daysWhanFree = new ArrayList<>(moreThan100);

        while (columnMinPrice > 0)
        {
            if (costs[columnMinPrice-1] <= 100)
            {
                if (lineMinPrice + 1 < moreThan100)
                {
                    if (dp[lineMinPrice][columnMinPrice] == dp[lineMinPrice+1][columnMinPrice-1])
                    {
                        daysWhanFree.add(columnMinPrice);
                        lineMinPrice++;
                    }
                }
                columnMinPrice--;
            }
            else
            {
                if (lineMinPrice - 1 < 0)
                {
                    //упираемся в верх
                    daysWhanFree.add(columnMinPrice);
                    lineMinPrice++;
                }
                else if (lineMinPrice + 1 == moreThan100)
                    lineMinPrice--;
                else
                {
                    if (dp[lineMinPrice][columnMinPrice] == dp[lineMinPrice+1][columnMinPrice-1])
                    {
                        daysWhanFree.add(columnMinPrice);
                        lineMinPrice++;
                    }
                    else
                        lineMinPrice--;
                }
                columnMinPrice--;
            }
        }

        //выписываем сколько купонов потратили
        System.out.println(daysWhanFree.size());

        //выводим дни, когда использовал жетоны
        for (int i = daysWhanFree.size()-1; i >= 0; --i)
        {
            System.out.println(daysWhanFree.get(i));
        }

    }
}
