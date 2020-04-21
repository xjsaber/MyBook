using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            MySort mySort = new MySort();
            //int[] array = new int[2000];
            //Init(array);
            int[] array = new int[] { 3, 5, 8, 11, 9, 7, 5, 2 };
            mySort.BubbleSort(array, 2000);
            foreach (int item in array)
            {
                Console.WriteLine(item);
            }
        }

        static void Init(int[] arr)
        {
            Random random = new Random();
            for (int i = 0; i < 2000; i++)
            {
                arr[i] = random.Next(0, 9);
            }
        }
    }
}
