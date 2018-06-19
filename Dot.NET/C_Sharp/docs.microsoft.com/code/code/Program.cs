using ExtensionMethodsExample;
using System;

namespace code
{
    class Program
    {
        static void Main(string[] args)
        {
            // t1 int
            // t2 int 
            // tResult int
            //public delegate TResult Func<T1, T2, TResult>(T1 arg1, T2 arg2);
            //Func<int, int, int> function = (a, b) => a + b;

            //int c = function(3, 5);

            //Console.WriteLine(c);
            //Console.ReadKey();

            //string temp = "123";
            //temp.WordCount();

            //const int One = 0b0001;
            //const int Two = 0b0010;
            //const int Four = 0b0100;
            //const int Eight = 0b1000;

            //string str = "test";
            //object obj = str;

            //Console.ReadKey();
            Program p = new Program();
            Console.WriteLine(p.ToString());
            Console.WriteLine(p.FullName);
            Console.ReadKey();

        }

        

        static int Give()
        {
            string temp = "12";
            temp.WordCount();
            return 10;
        }
    }
}
