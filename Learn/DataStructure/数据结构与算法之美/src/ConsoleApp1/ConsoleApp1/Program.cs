using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            MySort mySort = new MySort();
            int[] array = { 3, 5, 4, 1, 2, 6 };
            mySort.BubbleSort(array, 6);
            foreach (int item in array)
            {
                Console.WriteLine(item);
            }
        }
    }
}
