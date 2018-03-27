using System;
using System.Threading.Tasks;

namespace delay
{
    public class Test
    {

        private static async Task<T> DelayResult<T>(T result, TimeSpan delay)
        {
            await Task.Delay(delay);
            return result;
        }
    }
}
