package com.company.Task3;

public class WorkflowThread extends Thread
{
    public static int stepReg;

    public int[] arrayPiece;

    public WorkflowThread()
    {
        arrayPiece = new int[] {};
    }

    public void run()
    {
        ShellSort();
    }

    void ShellSort()
    {
        //  Регулировка шага (стандартным методом Шелла, начиная с половины размера)
        for (stepReg = arrayPiece.length / 2; stepReg > 0; stepReg /= 2)
        {
            //  Перебор элементов, начиная с элемента, индекс которого равен шагу
            for (int i = stepReg; i < arrayPiece.length; i++)
            {
                //  Перестановка элементов по возрастанию внутри участка между j-тым и i-тым элементами, до элемента с индексом i
                for (int j = i - stepReg; j >= 0 && arrayPiece[j] < arrayPiece[j + stepReg]; j -= stepReg)
                {
                    int temp = arrayPiece[j];
                    arrayPiece[j] = arrayPiece[j + stepReg];
                    arrayPiece[j + stepReg] = temp;
                }
            }
        }
    }
}