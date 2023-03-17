package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.TypeVariable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 24.Буратино - lvl4 (Дивизион А)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());

        int[][] TVtime = new int[32401][2];
        int[][] times = new int[N][2];

        String[] line;
        for (int i = 0; i < N; ++i)
        {
            line = reader.readLine().split(" ");
            int work = Integer.valueOf(line[1]);
            line = line[0].split(":");
            int time = Integer.valueOf(line[0]) * 60 * 60;
            time += Integer.valueOf(line[1]) * 60;
            time += Integer.valueOf(line[2]);
            times[i][0] = time;
            times[i][1] = work;
        }
        reader.close();

        int thisTime = times[0][1];
        if (N > 1)
        {
            for (int j = 32400; j < times[1][0]; ++j)
            {
                TVtime[j-32400][0] = j;
                TVtime[j-32400][1] = thisTime;
            }
        }
        for (int i = 1; i < N-1; ++i)
        {
            thisTime = times[i][1];
            for (int j = times[i][0]; j < times[i+1][0]; ++j)
            {
                TVtime[j-32400][0] = j;
                TVtime[j-32400][1] = thisTime;
            }
        }
        thisTime = times[times.length-1][1];
        for (int j = times[times.length-1][0]; j <= 64800; ++j)
        {
            TVtime[j-32400][0] = j;
            TVtime[j-32400][1] = thisTime;
        }

        int[] ans = new int[32401];

        for (int i = 32400; i <= 46800; ++i)
        {
            if (i + 1 <= 46800)
            {
                ans[i-32399] = Math.max(ans[i-32400], ans[i-32399]);
            }
            if (i + TVtime[i-32400][1] <= 46800)
            {
                ans[(i-32400)+TVtime[i-32400][1]] = Math.max(ans[(i -32400)+ TVtime[i-32400][1]], ans[i-32400]+1);
            }
        }
        for (int i = 50400; i <= 64800; ++i)
        {
            if (i + 1 <= 64800)
            {
                ans[i-32399] = Math.max(ans[i-32400], ans[i-32399]);
            }
            if (i + TVtime[i-32400][1] <= 64800)
            {
                ans[(i-32400)+TVtime[i-32400][1]] = Math.max(ans[(i -32400)+ TVtime[i-32400][1]], ans[i-32400]+1);
            }
        }
        System.out.println((ans[46800-32400] + ans[64800-32400]));
    }
}
