package com.company.Task2;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class WriteThread extends Thread
{
    Semaphore semaphore;
    static Vector<Integer> numbers;

    public WriteThread(Semaphore semaphore, Vector<Integer> numbers)
    {
        this.semaphore = semaphore;
        WriteThread.numbers = numbers;
    }

    public void run()
    {
        Write();

        try
        {
            sleep(100);
        }
        catch(InterruptedException e)
        {
            e.getMessage();
        }
    }

    void Write()
    {
        Random random = new Random();

        for (int i = 0; i < 100; i++)
        {
            try
            {
                semaphore.acquire();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            try
            {
                int temp = random.nextInt(100) + 1;
                numbers.add(i, temp);

                System.out.printf("Write: %d to position %d\n", temp, i);
            } catch(IndexOutOfBoundsException e)
            {
                System.out.println("Vector is already out of elements!");
            }

            semaphore.release();
        }
    }
}