package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 18.Дек с защитой от ошибок - lvl3

        String[] deque = new String[110];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        String[] lineArray;
        boolean b = true;
        int head = 0;
        int tail = 109;

        while (b)
        {
            line = br.readLine();
            lineArray = line.split(" ");

            switch (lineArray[0])
            {
                case ("push_front"):
                    deque[head] = lineArray[1];
                    head = (head+1) % 110;
                    System.out.println("ok");
                    break;
                case ("push_back"):
                    deque[tail] = lineArray[1];
                    if (tail == 0)
                        tail = 109;
                    else
                        tail = tail - 1;
                    System.out.println("ok");
                    break;
                case ("pop_front"):
                    if (((tail+1)%110) == head)
                        System.out.println("error");
                    else
                    {
                        if (head == 0)
                            head = 109;
                        else
                            head = head - 1;
                        System.out.println(deque[head]);
                    }
                    break;
                case ("pop_back"):
                    if (((tail+1)%110) == head)
                        System.out.println("error");
                    else
                    {
                        tail = (tail+1)%110;
                        System.out.println(deque[tail]);
                    }
                    break;
                case ("front"):
                    if (((tail+1)%110) == head)
                        System.out.println("error");
                    else
                    {
                        int head1 = head;
                        if (head == 0)
                            head = 109;
                        else
                            head--;
                        System.out.println(deque[head]);
                        head = head1;
                    }
                    break;
                case ("back"):
                    if (((tail+1)%110) == head)
                        System.out.println("error");
                    else
                    {
                        int tail1 = tail;
                        tail = (tail+1)%110;
                        System.out.println(deque[tail]);
                        tail = tail1;
                    }
                    break;
                case ("size"):
                    if (tail > head)
                        System.out.println(110 - tail + head - 1);
                    else
                        System.out.println(head - tail - 1);
                    break;
                case ("clear"):
                    System.out.println("ok");
                    head = 0;
                    tail = 109;
                    break;
                case ("exit"):
                    br.close();
                    b = false;
                    System.out.println("bye");
                    break;
            }

        }

    }
}
