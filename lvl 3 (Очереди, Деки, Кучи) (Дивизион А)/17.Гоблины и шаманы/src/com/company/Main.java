package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 17.Гоблины и шаманы - vlv3 (Дивизион А)

        //long time = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());

        List<String> queue = new ArrayList<>();
        StringBuilder answers = new StringBuilder("");

        String line;
        String[] lineArray;

        for (int i = 0; i < N; ++i)
        {
            line = br.readLine();
            lineArray = line.split(" ");

            switch (lineArray[0])
            {
                case ("+"):
                    queue.add(lineArray[1]);
                    break;
                case ("*"):
                    int indexToStay = (queue.size()) / 2;
                    if ((queue.size()) % 2 != 0)
                        indexToStay++;
                    queue.add(indexToStay, lineArray[1]);
                    break;
                case ("-"):
                    answers.append(queue.remove(0)).append("\n");
                    break;
            }
        }

        br.close();

        System.out.println(answers);

        //System.out.println("time: " + (System.currentTimeMillis() - time));

    }
}
