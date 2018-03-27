using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace WhenAll
{
    class Program
    {
        static async Task<string> DownloadAllAsync(IEnumerable<string> urls) 
        {
            var httpClient = new HttpClient();

            var downloads = urls.Select(url => httpClient.GetStringAsync(url));
            // 下面， 所有的URL下载同步开始
            Task<string>[] downloadTasks = downloads.ToArray();
            // 用异步方式等待所有下载完成
            string[] htmlPages = await Task.WhenAll(downloadTasks);

            return string.Concat(htmlPages);

        }

        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
        }
    }
}
