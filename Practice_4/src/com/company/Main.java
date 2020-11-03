package com.company;

import com.company.Task2.WriteThread;
import com.company.Task2.ReadThread;

import com.company.Task3.ShellSort_Async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        ArrayList<Thread> waitList = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        while (true)
        {
            CallUI();

            switch (input.nextLine())
            {
                case "1":
                    Task1_ChangedThread thread1 = new Task1_ChangedThread();
                    Task1_ChangedThread thread2 = new Task1_ChangedThread();
                    Task1_ChangedThread thread3 = new Task1_ChangedThread();

                    waitList.add(thread1);
                    waitList.add(thread2);
                    waitList.add(thread3);

                    thread1.start();
                    thread2.start();
                    thread3.start();

                    Task1_ChangedThread.count = 0;
                    break;
                case "2":
                    Vector<Integer> numbers = new Vector<>(100);
                    Semaphore semaphore = new Semaphore(1);

                    WriteThread wt1 = new WriteThread(semaphore, numbers);
                    WriteThread wt2 = new WriteThread(semaphore, numbers);
                    ReadThread rt = new ReadThread(semaphore, numbers);

                    waitList.add(wt1);
                    waitList.add(wt2);
                    waitList.add(rt);

                    wt1.start();
                    wt2.start();
                    rt.start();
                    break;
                case "3":
                    new ShellSort_Async(input);
                    break;
                case "exit":
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input received! Try again");
                    break;
            }

            for (Thread t : waitList)
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
            waitList.clear();

            WaitKey();
        }
    }

    static void CallUI()
    {
        System.out.println("\nChoose the task to execute:\n");
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%d. Task %d\n", i + 1, i + 1);
        }
        System.out.print("\t\t\texit - close the program\n> ");
    }

    static void WaitKey() throws IOException
    {
        System.out.println("\nPress Enter to continue...");
        System.in.read();
        System.out.println();
    }
}