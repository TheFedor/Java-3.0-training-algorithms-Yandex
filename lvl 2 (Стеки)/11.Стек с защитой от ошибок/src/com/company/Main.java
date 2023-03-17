package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 11. Стек с защитой от ошибок

        List<String> list = new ArrayList<>();
        int c = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        String[] strArray;
        boolean b = true;
        while(b)
        {
            str = br.readLine();
            strArray = str.split(" ");

            switch (strArray[0])
            {
                case ("push"):
                    list.add(strArray[1]);
                    c++;
                    System.out.println("ok");
                    break;
                case ("pop"):
                    if (list.size() > 0)
                    {
                        String result = list.get(c-1);
                        list.remove(c-1);
                        c--;
                        System.out.println(result);
                    }
                    else
                    {
                        System.out.println("error");
                    }
                    break;
                case ("back"):
                    if (list.size() > 0)
                    {
                        System.out.println(list.get(c-1));
                    }
                    else
                    {
                        System.out.println("error");
                    }
                    break;
                case ("size"):
                    System.out.println(c);
                    break;
                case ("clear"):
                    list.clear();
                    System.out.println("ok");
                    c = 0;
                    break;
                case ("exit"):
                    br.close();
                    System.out.println("bye");
                    b = false;
                    break;
            }
        }

    }
}
