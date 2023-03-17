package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 16.Очередь с защитой от ошибок - lvl3

        List<String> queue = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] lineArray;

        int head = 0;
        int tail = 0;
        boolean b = true;

        while (b)
        {
            line = br.readLine();
            lineArray = line.split(" ");

            switch (lineArray[0])
            {
                case ("push"):
                    queue.add(lineArray[1]);
                    tail++;
                    System.out.println("ok");
                    break;
                case ("pop"):
                    if (head - tail == 0)
                        System.out.println("error");
                    else
                    {
                        System.out.println(queue.get(head));
                        head++;
                    }
                    break;
                case ("front"):
                    if (tail - head == 0)
                        System.out.println("error");
                    else
                        System.out.println(queue.get(head));
                    break;
                case ("size"):
                    System.out.println(tail - head);
                    break;
                case ("clear"):
                    head = 0;
                    tail = 0;
                    queue.clear();
                    System.out.println("ok");
                    break;
                case ("exit"):
                    System.out.println("bye");
                    br.close();
                    b = false;
                    break;
            }
        }

    }
}
