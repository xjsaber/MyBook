using System;
using System.Diagnostics;
using System.Threading.Tasks;

namespace ReturnAsync
{
    class Program
    {
        static async Task<int> DelayAndReturnAsync(int val)
        {
            await Task.Delay(TimeSpan.FromSeconds(val));
            return val;
        }

        /// <summary>
        /// in 2, 3, 1
        /// out 1, 2, 3
        /// </summary>
        /// <returns></returns>
        static async Task ProcessTasksAsync()
        {
            Task<int> taskA = DelayAndReturnAsync(2);
            Task<int> taskB = DelayAndReturnAsync(1);
            Task<int> taskC = DelayAndReturnAsync(3);

            var tasks = new[]
            {
                taskA, taskB, taskC
            };

            // 按顺序await每个任务
            foreach (var task in tasks)
            {
                var result = await task;
                Trace.WriteLine(result);
            }
        }

        static void Main(string[] args)
        {
            //ProcessTasksAsync();
            Console.ReadKey();
        }
    }
}
