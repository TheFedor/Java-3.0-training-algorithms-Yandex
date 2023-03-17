package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 15.Поврежденный XML - lvl2 (Дивизион А)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder line = new StringBuilder(br.readLine());

        br.close();

        //переменная, отвечающая за правильность теговой последовательности
        boolean b = false;

        //будем проверять отдельно правильность скобчной последовательности только для "<",">"
        int sch = 0; //переменная-счетчик для проверки скобочной ('<', '>') последовательности
        int start = 0;
        int end = 0;
        for (int i = 0; i < line.length(); ++i)
        {
            if (line.charAt(i) == '<')
            {
                sch++;
                if (sch > 1)
                {
                    if (i == line.length() - 1)
                    {
                        line.setCharAt(line.length()-1, '>');
                        b = true;
                        break;
                    }
                    char charBeforeIndex = line.charAt(i-1);
                    //проверка a1. Если пройдет - это ответ, если нет - меняем строку и работаем с телами тегов
                    line.setCharAt(i-1, '>');
                    sch = 0;
                    boolean localB = true;
                    for (int j = 0; j < i; ++j)
                    {
                        if (line.charAt(j) == '<') //<grnn><q><ew></e<></q><ojeoh></ojeoh></grnn>
                        {
                            sch++;
                            if (line.codePointAt(j+1) > 122 || line.codePointAt(j+1) < 97)
                            {
                                if (j+2 == line.length()-1)
                                {
                                    localB = false;
                                    break;
                                }
                                else
                                {
                                    if (line.codePointAt(j+1) == 47)
                                    {
                                        if (line.codePointAt(j+2) > 122 || line.codePointAt(j+2) < 97)
                                        {
                                            localB = false;
                                            break;
                                        }
                                    }
                                    else
                                    {
                                        localB = false;
                                        break;
                                    }
                                }
                            }
                        }
                        else if (line.charAt(j) == '>')
                            sch--;
                        if (sch > 1 || sch < 0)
                        {
                            localB = false;
                            break;
                        }
                    }
                    if (sch != 0)
                        localB = false;

                    if (localB)
                    {
                        sch = 0;
                        for (int j = i; j < line.length(); ++j)
                        {
                            if (line.charAt(j) == '<')
                            {
                                sch++;
                                //проверяем, есть ли символы внутри тега (если это не последний символ строки)
                                if (!(j == line.length()-1))
                                {
                                    if (line.codePointAt(j+1) <  97 || line.codePointAt(j+1) > 122)
                                    {
                                        if (line.codePointAt(j+1) == 47)
                                        {
                                            if (!(j+1 == line.length()-1))
                                            {
                                                if (line.codePointAt(j+2) <  97 || line.codePointAt(j+1) > 122)
                                                {
                                                    sch = 2;
                                                    break;
                                                }
                                            }
                                            else
                                            {
                                                sch = 2;
                                                break;
                                            }
                                        }
                                        else
                                        {
                                            sch = 2;
                                            break;
                                        }
                                    }

                                }

                            }
                            else if (line.charAt(j) == '>')
                                sch--;
                            if (sch > 1 || sch < 0)
                                break;
                        }
                        if (sch == 0)
                        {
                            b = true;
                        }
                        else
                            line.setCharAt(i-1,charBeforeIndex);
                    }
                    else
                    {
                        line.setCharAt(i-1, charBeforeIndex);
                        b = false;
                        if (!b)
                        {
                            line.setCharAt(i, '>');
                            //проверка на правильность скобочной последовательности
                            boolean locB = true;
                            int locSch = 0;
                            for (int j = 0; j < line.length(); ++j)
                            {
                                if (line.charAt(j) == '<')
                                {
                                    locSch++;
                                    if (j == line.length()-1)
                                    {
                                        locB = false;
                                        break;
                                    }
                                    else
                                    {
                                        if (line.codePointAt(j+1) > 122 || line.codePointAt(j+1) < 97)
                                        {
                                            if (line.codePointAt(j+1) == 47)
                                            {
                                                if (j+1 == line.length()-1)
                                                {
                                                    locB = false;
                                                    break;
                                                }
                                                else
                                                {
                                                    if (line.codePointAt(j+2) > 122 || line.codePointAt(j+2) < 97)
                                                    {
                                                        locB = false;
                                                        break;
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                locB = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                                else if (line.charAt(j) == '>')
                                {
                                    locSch--;
                                }
                                if (locSch > 1 || locSch < 0)
                                {
                                    locB = false;
                                    break;
                                }
                            }
                            if (locB)
                                b = true;
                            else
                                line.setCharAt(i, '<');
                        }
                        break;
                    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    break;
                }
                start = i;
            }
            else if (line.charAt(i) == '>')
            {
                sch--;
                if (sch < 0)
                {

                    if (end == 0)
                    {
                        line.setCharAt(end, '<');
                        b = true;
                        break;
                    }
                    end = i;
                    line.setCharAt(end, '<');
                    sch = 0;
                    for (int j = i; j < line.length(); ++j)
                    {
                        if (line.charAt(j) == '<')
                        {
                            sch++;
                            //проверяем, есть ли символы внутри тега (если это не последний символ строки)
                            if (!(j == line.length()-1))
                            {
                                if (line.codePointAt(j+1) <  97 || line.codePointAt(j+1) > 122)
                                {
                                    if (line.codePointAt(j+1) == 47)
                                    {
                                        if (!(j+1 == line.length()-1))
                                        {
                                            if (line.codePointAt(j+2) <  97 || line.codePointAt(j+1) > 122)
                                            {
                                                sch = 2;
                                                break;
                                            }
                                        }
                                        else
                                        {
                                            sch = 2;
                                            break;
                                        }
                                    }
                                    else
                                    {
                                        sch = 2;
                                        break;
                                    }
                                }

                            }

                        }
                        else if (line.charAt(j) == '>')
                            sch--;
                        if (sch > 1 || sch < 0)
                            break;
                    }
                    if (sch == 0)
                        b = true;
                    else
                        line.setCharAt(end, '>');

                    break;
                }
                end = i;
            }
        }
        //////////////////////////////////////////////
        String toTest;
        //////////////////////////////////////////////
        if (b)
        {
            System.out.println(line);
            toTest = line.toString();
        }
        else //если прошла скобочная последовательность ('<', '>'), но строка все-равно не верная, проверяем "скобочную последовательность" тел тегов
        {
            String[] tagsBogy = line.toString().split("><");
            tagsBogy[0] = tagsBogy[0].substring(1, tagsBogy[0].length());
            tagsBogy[tagsBogy.length-1] = tagsBogy[tagsBogy.length-1].substring(0, tagsBogy[tagsBogy.length-1].length()-1);
            List<String[]> stack = new ArrayList<>();
            int c = 0;
            for (int i = 0; i < tagsBogy.length; ++i)
            {
                if (stack.isEmpty() || !("/" + stack.get(c-1)[0]).equals(tagsBogy[i]))
                {
                    stack.add(new String[] {tagsBogy[i], Integer.toString(i)});
                    c++;
                }
                else
                {
                    stack.remove(c-1);
                    c--;
                }
            }
            //находим два нужных для нас тела тегов, одно из которых нужно будет исправить
            String firstBody = stack.get((stack.size()/2)-1)[0];
            int indexFirstBody = Integer.valueOf(stack.get((stack.size()/2)-1)[1]);
            String secondBody = stack.get(stack.size()/2)[0];
            int indexSecondBody = Integer.valueOf(stack.get(stack.size()/2)[1]);
            if (secondBody.charAt(0) != '/')
                secondBody = "/" + firstBody;
            else
            {
                for (int i = 0; i < firstBody.length(); ++i)
                {
                    if (firstBody.charAt(i) != secondBody.charAt(i+1))
                    {
                        if (firstBody.codePointAt(i) > 96 && firstBody.codePointAt(i) < 123)
                            secondBody = "/" + firstBody;
                        else
                            firstBody = secondBody.substring(1, secondBody.length());
                        break;
                    }
                }
            }

            //восстанавливаем теговую строку
            tagsBogy[indexFirstBody] = firstBody;
            tagsBogy[indexSecondBody] = secondBody;

            StringBuffer result = new StringBuffer("<" + String.join("><",tagsBogy) + ">");
            System.out.println(result);
            toTest = result.toString();

        }



    }
}
