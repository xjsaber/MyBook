using System;
using System.Threading.Tasks;

namespace delay
{
    interface IMAsyncInterface
    {
        Task<int> GetValueAsync();
    }



    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
        }
    }
}
