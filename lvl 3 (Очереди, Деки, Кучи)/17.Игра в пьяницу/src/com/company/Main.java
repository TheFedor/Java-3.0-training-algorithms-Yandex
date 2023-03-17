package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
	// 17.Игра в пьяницу - lvl3

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] player1 = new int[11];
        int[] player2 = new int[11];

        String line = br.readLine();
        String[] lineArray = line.split(" ");
        for (int i = 0; i < 5; ++i)
            player1[i] = Integer.valueOf(lineArray[i]);
        line = br.readLine();
        lineArray = line.split(" ");
        for (int i = 0; i < 5; ++i)
            player2[i] = Integer.valueOf(lineArray[i]);

        int count = 0;
        String winner = "";
        int tailPlayer1 = 5;
        int tailPlayer2 = 5;

        do
        {
            count++;
            if (player1[0] > player2[0])
            {
                if (player2[0] == 0 && player1[0] == 9)
                {
                    player2[tailPlayer2] = player1[0];
                    player2[tailPlayer2+1] = player2[0];
                    tailPlayer2++;
                    for (int i = 0; i < tailPlayer2; ++i)
                        player2[i] = player2[i+1];
                    tailPlayer1--;
                    if (tailPlayer1 == 0)
                    {
                        winner = "second ";
                        break;
                    }
                    for (int i = 0; i < tailPlayer1; ++i)
                        player1[i] = player1[i+1];
                }
                else
                {
                    player1[tailPlayer1] = player1[0];
                    player1[tailPlayer1+1] = player2[0];
                    tailPlayer1++;
                    for (int i = 0; i < tailPlayer1; ++i)
                        player1[i] = player1[i+1];
                    tailPlayer2--;
                    if (tailPlayer2 == 0)
                    {
                        winner = "first ";
                        break;
                    }
                    for (int i = 0; i < tailPlayer2; ++i)
                        player2[i] = player2[i+1];
                }

            }
            else
            {
                if (player2[0] == 9 && player1[0] == 0)
                {
                    player1[tailPlayer1] = player1[0];
                    player1[tailPlayer1+1] = player2[0];
                    tailPlayer1++;
                    for (int i = 0; i < tailPlayer1; ++i)
                        player1[i] = player1[i+1];
                    tailPlayer2--;
                    if (tailPlayer2 == 0)
                    {
                        winner = "first ";
                        break;
                    }
                    for (int i = 0; i < tailPlayer2; ++i)
                        player2[i] = player2[i+1];
                }
                else
                {
                    player2[tailPlayer2] = player1[0];
                    player2[tailPlayer2+1] = player2[0];
                    tailPlayer2++;
                    for (int i = 0; i < tailPlayer2; ++i)
                        player2[i] = player2[i+1];
                    tailPlayer1--;
                    if (tailPlayer1 == 0)
                    {
                        winner = "second ";
                        break;
                    }
                    for (int i = 0; i < tailPlayer1; ++i)
                        player1[i] = player1[i+1];
                }
            }


        }
        while (count < 1000000);

        if (count == 1000000)
            System.out.println("botva");
        else
            System.out.println(winner + count);



    }
}
