package com.company.Task3;

import java.util.Random;
import java.util.Scanner;

public class ShellSort_Async
{
    public static int[] array;
    static WorkflowThread[] threads;

    public ShellSort_Async(Scanner input)
    {
        System.out.print("Input the array size: ");
        array = new int[input.nextInt()];

        Random random = new Random();
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt() % 1000;
        }

        PrintArray();

        do
        {
            System.out.print("Input the amount of sorting threads: ");
            threads = new WorkflowThread[input.nextInt()];
        }
        while (threads.length > array.length);

        Execute(input);

        PrintArray();
    }

    static void Execute(Scanner input)
    {
        System.out.print("Input the sorting step: ");
        WorkflowThread.stepReg = input.nextInt();

        int pieceLength;
        if (array.length > threads.length)
        {
            pieceLength = array.length / threads.length;
        }
        else
        {
            pieceLength = 1;
        }

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new WorkflowThread();
            threads[i].arrayPiece = new int[pieceLength];

            int startIndex = pieceLength * i;

            for (int j = 0, k = startIndex; j < pieceLength; j++, k++)
            {
                threads[i].arrayPiece[j] = array[k];
            }

            threads[i].start();
        }

        for (WorkflowThread t : threads)
        {
            try
            {
                t.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threads.length; i++)
        {
            int startIndex = pieceLength * i;

            for (int j = 0, k = startIndex; j < pieceLength; j++, k++)
            {
                array[k] = threads[i].arrayPiece[j];
            }
        }

        PrintArray();

        WorkflowThread finalThread = new WorkflowThread();
        finalThread.arrayPiece = array;

        finalThread.start();
        try
        {
            finalThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        array = finalThread.arrayPiece;
    }

    private static void PrintArray()
    {
        System.out.println();
        for (int number : array)
        {
            System.out.print(number + " ");
        }
        System.out.println("\n");
    }
}