package com.company.Task2;

import java.util.Vector;
import java.util.concurrent.Semaphore;

public class ReadThread extends Thread
{
    Semaphore semaphore;
    static Vector<Integer> numbers;

    public ReadThread(Semaphore semaphore, Vector<Integer> numbers)
    {
        this.semaphore = semaphore;
        ReadThread.numbers = numbers;
    }

    public void run()
    {
        try
        {
            sleep(250);
        }
        catch(InterruptedException e)
        {
            e.getMessage();
        }

        Read();

        try
        {
            sleep(250);
        }
        catch(InterruptedException e)
        {
            e.getMessage();
        }
    }

    void Read()
    {
        for (int i = 0; i < 100; i++)
        {
            try
            {
                semaphore.acquire();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.printf("Read: %d from position %d\n", numbers.get(i), i);

            semaphore.release();
        }
    }
}