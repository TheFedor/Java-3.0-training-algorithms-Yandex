package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// 37.Числа - lvl7 (Дивизион A)

        Scanner sc = new Scanner(System.in);

        int startNumber = sc.nextInt();
        int finalNumber = sc.nextInt();
        sc.close();

        int[] way = new int[10000]; //тут будем записывать индекс, откуда пришли
        Arrays.fill(way, -1);
        way[startNumber] = -10;

        func(way, startNumber, finalNumber);

        //восстанавливаем ответ
        int count = finalNumber;
        ArrayList<Integer> answer = new ArrayList<>();
        while(count != -10)
        {
            answer.add(count);
            count = way[count];
        }

        for (int i = answer.size()-1; i >= 0; --i)
            System.out.println(answer.get(i));

    }

    public static void func(int[] way, int startNumber, int finalNumber)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startNumber);

        while (!queue.isEmpty())
        {
            int workNumber = queue.get(0);
            String workNumberString = Integer.toString(workNumber);
            queue.remove(0);

            //тут обрабатываем следующие числа

            //если первое число меньше 9
            if (!workNumberString.startsWith("9"))
            {
                if (way[workNumber+1000] == -1)
                {
                    way[workNumber+1000] = workNumber;
                    queue.add(workNumber+1000);
                }
            }
            //если последнее число больше 1
            if (workNumber % 10 > 1)
            {
                if (way[workNumber-1] == -1)
                {
                    way[workNumber-1] = workNumber;
                    queue.add(workNumber-1);
                }
            }
            //сдвиг вправо
            StringBuilder number = new StringBuilder();
            number.append(workNumberString.charAt(3)).append(workNumberString.charAt(0)).append(workNumberString.charAt(1)).append(workNumberString.charAt(2));
            if (way[Integer.valueOf(number.toString())] == -1)
            {
                way[Integer.valueOf(number.toString())] = workNumber;
                queue.add(Integer.valueOf(number.toString()));
            }

            //сдвиг влево
            number = new StringBuilder();
            number.append(workNumberString.charAt(1)).append(workNumberString.charAt(2)).append(workNumberString.charAt(3)).append(workNumberString.charAt(0));
            if (way[Integer.valueOf(number.toString())] == -1)
            {
                way[Integer.valueOf(number.toString())] = workNumber;
                queue.add(Integer.valueOf(number.toString()));
            }

            //если путь до искомого числа уже найден
            if (way[finalNumber] > -1)
                break;
        }
    }

}
