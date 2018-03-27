using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace MAsyncEx
{
    class TestEx
    {
        static async Task ThrowExceptionAsync()
        {
            await Task.Delay(TimeSpan.FromSeconds(1));
            throw new InvalidOperationException("Test");
        }

        static async Task TestAsync()
        {
            try
            {
                await ThrowExceptionAsync();
            }
            catch (InvalidOperationException) { }
        }
    }
}
