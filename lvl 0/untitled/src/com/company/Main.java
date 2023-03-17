package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        int[] array = new int[123];

        while (true) {
            line = br.readLine();

            if (line == null)
            {
                break;
            }

            for (int i = 0; i < line.length(); ++i)
            {
                if (line.codePointAt(i) != 32)
                {
                    array[line.codePointAt(i)] = array[line.codePointAt(i)] + 1;
                }
            }

        }

        br.close();

        List<MyClass> list = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < array.length; ++i)
        {
            if (array[i] > 0)
            {
                list.add(new MyClass((char)i, array[i]));
                if (array[i] > max)
                {
                    max = array[i];
                }
            }
        }

        while (max > 0)
        {
            for (int i = 0; i < list.size(); ++i)
            {
                if (!(list.get(i).getHowMany() == max))
                {
                    System.out.print(" ");
                }
                else
                {
                    System.out.print("#");
                    list.set(i, new MyClass(list.get(i).getN(), list.get(i).getHowMany()-1));
                }
            }
            System.out.println();
            max--;
        }
        for (int i = 0; i < list.size(); ++i)
        {
            System.out.print((char) list.get(i).getN());
        }



    }

    public static class MyClass
    {
        int n;
        int howMany;

        public MyClass(int n, int howMany) {
            this.n = n;
            this.howMany = howMany;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getHowMany() {
            return howMany;
        }

        public void setHowMany(int howMany) {
            this.howMany = howMany;
        }
    }

}
