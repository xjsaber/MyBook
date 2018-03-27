using System;
using System.Threading.Tasks;

namespace TaskResult
{
    interface IMAsyncInterface
    {
        Task<int> GetValueAsync();
    }

    class MySynchronousImplementation : IMAsyncInterface
    {
        public Task<int> GetValueAsync()
        {
            return Task.FromResult(13);
        }
    }
}
