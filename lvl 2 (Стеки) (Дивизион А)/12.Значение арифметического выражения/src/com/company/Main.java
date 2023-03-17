package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
	// 12. Значение арифметического выражения - lvl2 (Дивизион А)

        //Оценивать строку на правильность будем следующим образом. Выражение будет неправильным, если:
        //  отсутствует операторы между операндами
        //  Присутствуют неутвержденные символы
        //  Неправильная скобочная последовательность
        //  Идут два оператора подряд (ели второй минус, бракуем в том случае, если после него идет не число)
        //  Оператор стоит в конце строки или перед закрывающей скобкой
        //  Оператор стоит в начале строки или после открывающей скобки (если это минус, бракуем в том случае, если после него идет не число)
        //  Число начинается с одного или более нулей
        //  Выражение состоит только из скобок
        //  Перед открывающей скобкой или после закрывающей скобки не стоит оператор (исключение - если скобка стоит в начале или конце строки)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String arExpression = br.readLine();

        br.close();

        //переменная, отвечающая за правильность выражения
        boolean b = true;

        //проверяем, между всеми числами есть ли пробел
        arExpression = arExpression.strip();

        if (arExpression.length() == 0)
        {
            b = false;
        }
        else
        {
            for (int i = 1; i < arExpression.length()-1; ++i)
            {
                if (arExpression.codePointAt(i) == 32)
                {
                    if (arExpression.codePointAt(i-1) > 47 && arExpression.codePointAt(i-1) < 58)
                    {
                        i++;
                        while (arExpression.codePointAt(i) == 32)
                        {
                            i++;
                        }
                        if (arExpression.codePointAt(i) > 47 && arExpression.codePointAt(i) < 58)
                        {
                            b = false;
                            break;
                        }
                    }
                    i++;
                    while (arExpression.codePointAt(i) == 32)
                        i++;

                }
            }
        }


        //удаляем все пробелы из строки
        arExpression = arExpression.replaceAll("\\s","");

        //List для последующей проверки скобочной последовательности
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < arExpression.length(); ++i)
        {
            if (i == 0 && b == false)
                break;
            int codePoint = arExpression.codePointAt(i);

            //если символ неутвержден
            if (codePoint < 40 || codePoint > 57)
            {
                b = false;
                break;
            }
            else if (codePoint == 44 || codePoint == 46 || codePoint == 47) //если символ неутвержден
            {
                b = false;
                break;
            }
            else // если символ утвержден
            {
                //если это скобка
                if (codePoint == 40 || codePoint == 41)
                {
                    //записываем в List для последующей проверки скобочной последовательности
                    sequence.add(codePoint);

                    //проверяем на "последнее условие правильности выражения"
                    if (i != 0 && i != arExpression.length() - 1)
                    {
                        //если перед открывающей скобкой нет оператора
                        if (codePoint == 40)
                        {
                            int localCodePoint = arExpression.codePointAt(i-1);
                            if (localCodePoint != 42 && localCodePoint != 43 && localCodePoint != 45 && localCodePoint != 40)
                            {
                                b = false;
                                break;
                            }
                        }
                        else //если после закрывающей скобки нет оператора
                        {
                            int localCodePoint = arExpression.codePointAt(i+1);
                            if (localCodePoint != 42 && localCodePoint != 43 && localCodePoint != 45 && localCodePoint != 41)
                            {
                                b = false;
                                break;
                            }
                        }
                    }
                }

                //если символ - число нуль
                if (codePoint == 48)
                {
                    if (i != arExpression.length() - 1 )
                    {
                        if (i == 0)
                        {
                            if (arExpression.codePointAt(i+1) > 47 && arExpression.codePointAt(i+1) < 58)
                            {
                                b = false;
                                break;
                            }
                        }
                        else if (arExpression.codePointAt(i-1) == 42 || arExpression.codePointAt(i-1) == 43 || arExpression.codePointAt(i-1) == 45 || arExpression.codePointAt(i-1) == 40)
                        {
                            if (arExpression.codePointAt(i+1) > 47 && arExpression.codePointAt(i+1) < 58)
                            {
                                b = false;
                                break;
                            }
                        }
                    }
                }

                //если символ - оператор
                if (codePoint == 42 || codePoint == 43 || codePoint == 45)
                {
                    //оператор стоит в конце выражения или перед закрывающей скобкой
                    if (i == arExpression.length()-1)
                    {
                        b = false;
                        break;
                    }
                    else if (arExpression.codePointAt(i+1) == 41)
                    {
                        b = false;
                        break;
                    }

                    //оператор стоит в начале строки или после открывающей скобки (если это минус, бракуем в том случае, если после него идет не число)
                    if (i == 0)
                    {
                        //проверяем на минус
                        if (codePoint == 45)
                        {
                            if (arExpression.codePointAt(i+1) < 48 || arExpression.codePointAt(i+1) > 57)
                            {
                                b = false;
                                break;
                            }
                        }
                        else
                        {
                            b = false;
                            break;
                        }
                    }
                    else if (arExpression.codePointAt(i-1) == 40)
                    {
                        //проверяем на минус
                        if (codePoint == 45)
                        {
                            if (arExpression.codePointAt(i+1) < 48 || arExpression.codePointAt(i+1) > 57)
                            {
                                b = false;
                                break;
                            }
                        }
                        else
                        {
                            b = false;
                            break;
                        }
                    }

                    //идут два оператора подряд (если второй минус, бракуем в том случае, если после него идет не число)
                    if (arExpression.codePointAt(i+1) == 42 || arExpression.codePointAt(i+1) == 43)
                    {
                        b = false;
                        break;
                    }
                    else if (arExpression.codePointAt(i+1) == 45)
                    {
                        if (i+1 == arExpression.length()-1)
                        {
                            b = false;
                            break;
                        }
                        else if (arExpression.codePointAt(i+2) < 48 || arExpression.codePointAt(i+2) > 57)
                        {
                            b = false;
                            break;
                        }
                    }

                }

            }
        }

        //если выражение состоит только из скобок
        if (sequence.size() == arExpression.length())
            b = false;

        //если выражение верно, продолжаем (иначе ошибка)
        if (b)
        {
            //проверяем скобочную последовательность простым счетчиком (sequence)
            int counter = 0;
            for (int i = 0; i < sequence.size(); ++i)
            {
                if (sequence.get(i) == 40)
                    counter++;
                else
                    counter--;
                if (counter < 0)
                {
                    b = false;
                    break;
                }
            }
            if (counter != 0)
                b = false;

            //если скобочная последовательность верна, продолжаем (иначе ошибка)
            if (b)
            {
                //добавляем пробелы
                StringBuilder arExpressionWithAllWhitespaces = new StringBuilder();
                for (int i = 0; i < arExpression.length(); ++i)
                {
                    int localCodePoint = arExpression.codePointAt(i);
                    //если цифра
                    if (localCodePoint > 47 && localCodePoint < 58)
                    {
                        arExpressionWithAllWhitespaces.append(arExpression.charAt(i));
                    }
                    else if (localCodePoint != 45) // если (,),+ или *
                    {
                        if (i != 0)
                        {
                            arExpressionWithAllWhitespaces.append(" ");
                            arExpressionWithAllWhitespaces.append(arExpression.charAt(i));
                        }
                        else
                        {
                            arExpressionWithAllWhitespaces.append(arExpression.charAt(i));
                        }
                        if (i != arExpression.length() - 1)
                        {
                            if (arExpression.codePointAt(i+1) > 47 && arExpression.codePointAt(i+1) < 58)
                            {
                                arExpressionWithAllWhitespaces.append(" ");
                            }
                            else if (arExpression.codePointAt(i+1) == 45 && localCodePoint != 41)
                            {
                                arExpressionWithAllWhitespaces.append(" ");
                            }
                        }
                    }
                    else // если -
                    {
                        if (i == 0)
                        {
                            arExpressionWithAllWhitespaces.append(arExpression.charAt(i));
                        }
                        else if (arExpressionWithAllWhitespaces.charAt(arExpressionWithAllWhitespaces.length() - 1) == ' ')
                        {
                            arExpressionWithAllWhitespaces.append(arExpression.charAt(i));
                        }
                        else
                        {
                            arExpressionWithAllWhitespaces.append(" ");
                            arExpressionWithAllWhitespaces.append(arExpression.charAt(i));
                            arExpressionWithAllWhitespaces.append(" ");
                        }

                    }
                }
                for (int i = 0; i < arExpressionWithAllWhitespaces.length()-1; ++i)
                {
                    if (arExpressionWithAllWhitespaces.charAt(i) == arExpressionWithAllWhitespaces.charAt(i+1))
                        if (arExpressionWithAllWhitespaces.charAt(i) == ' ')
                            arExpressionWithAllWhitespaces.deleteCharAt(i);
                }

                //Переводим выражение в постфиксную запись, сохраняя последовательность в массиве строк
                String[] beforePostfix = arExpressionWithAllWhitespaces.toString().split(" ");
                List<String> afterPostfix = new ArrayList<>();
                Stack<String> stack = new Stack<>();

                for (int i = 0; i < beforePostfix.length; ++i)
                {
                    switch (beforePostfix[i])
                    {
                        case ("+"):
                            while (!stack.isEmpty() && stack.peek().compareTo("(") != 0)
                            {
                                afterPostfix.add(stack.pop());
                            }
                            stack.push(beforePostfix[i]);
                            break;
                        case ("-"):
                            while (!stack.isEmpty() && stack.peek().compareTo("(") != 0)
                            {
                                afterPostfix.add(stack.pop());
                            }
                            stack.push(beforePostfix[i]);
                            break;
                        case ("*"):
                            while (!stack.isEmpty() && stack.peek().compareTo("*") == 0)
                            {
                                afterPostfix.add(stack.pop());
                            }
                            stack.push(beforePostfix[i]);
                            break;
                        case ("("):
                            stack.push(beforePostfix[i]);
                            break;
                        case (")"):
                            while(stack.peek().compareTo("(") != 0)
                            {
                                afterPostfix.add(stack.pop());
                            }
                            stack.pop();
                            break;
                        default:
                            afterPostfix.add(beforePostfix[i]);
                            break;
                    }


                }

                while(!stack.isEmpty())
                {
                    afterPostfix.add(stack.pop());
                }


                //вычисляем значение выражения
                Stack<Integer> result = new Stack<>();
                int first;
                int second;

                for (int i = 0; i < afterPostfix.size(); ++i)
                {
                    String oneEntry = afterPostfix.get(i);
                    switch (oneEntry)
                    {
                        case ("+"):
                            second = result.pop();
                            first = result.pop();
                            result.push(first + second);
                            break;
                        case ("-"):
                            second = result.pop();
                            first = result.pop();
                            result.push(first - second);
                            break;
                        case ("*"):
                            second = result.pop();
                            first = result.pop();
                            result.push(first * second);
                            break;
                        default:
                            result.push(Integer.valueOf(oneEntry));
                            break;
                    }

                }

                int answer = result.pop();
                System.out.println(answer);


            }
            else
                System.out.println("WRONG");
        }
        else
            System.out.println("WRONG");

    }
}
