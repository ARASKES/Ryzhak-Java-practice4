package com.company;

public class Task1_ChangedThread extends Thread
{
    public static int count = 0;

    int threadID;

    public Task1_ChangedThread()
    {
        threadID = ++count;
    }

    public void run()
    {
        System.out.printf("Thread %d executes!\n", threadID);

        try
        {
            sleep(10000);
        }
        catch(InterruptedException e)
        {
            e.getMessage();
        }
    }
}