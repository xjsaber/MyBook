using ExtensionMethodsExample;
using System;
using System.Threading.Tasks;

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
            string result = MakeRequest().Result;
            Console.WriteLine(result);
            Console.ReadKey();
        }


        public static async Task<string> MakeRequest()
        {
            var client = new System.Net.Http.HttpClient();
            var steamTask = client.GetStringAsync("https://localhost:10000");
            try
            {
                var responseText = await steamTask;
                return responseText;
            } catch(System.Net.Http.HttpRequestException e) when (e.Message.Contains("301"))
            {
                return "Site Moved";
            }
        }
        

        static int Give()
        {
            string temp = "12";
            temp.WordCount();
            return 10;
        }
    }
}
