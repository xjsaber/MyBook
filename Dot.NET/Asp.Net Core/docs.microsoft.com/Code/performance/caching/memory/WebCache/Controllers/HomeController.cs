using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Caching.Memory;
using Microsoft.Extensions.Primitives;
using WebCache.Models;

namespace WebCache.Controllers
{
   
    public class HomeController : Controller
    {
        
        private IMemoryCache _cache;
        
        public HomeController(IMemoryCache memoryCache)
        {
            _cache = memoryCache;
        }
        
        public IActionResult CacheTryGetValueSet()
        {
            DateTime cacheEntry;

            // Look for cache key.
            if (!_cache.TryGetValue(CacheKeys.Entry, out cacheEntry))
            {
                // Key not in cache, so get data.
                cacheEntry = DateTime.Now;

                // Set cache options.
                var cacheEntryOptions = new MemoryCacheEntryOptions()
                    // Keep in cache for this time, reset time if accessed.
                    .SetSlidingExpiration(TimeSpan.FromSeconds(3));

                // Save data in cache.
                _cache.Set(CacheKeys.Entry, cacheEntry, cacheEntryOptions);
            }

            return View("Cache", cacheEntry);
        }
        
        public IActionResult CreateDependentEntries()
        {
            var cts = new CancellationTokenSource();
            _cache.Set(CacheKeys.DependentCTS, cts);

            using (var entry = _cache.CreateEntry(CacheKeys.Parent))
            {
                // expire this entry if the dependant entry expires.
                entry.Value = DateTime.Now;
                entry.RegisterPostEvictionCallback(DependentEvictionCallback, this);

                _cache.Set(CacheKeys.Child,
                    DateTime.Now,
                    new CancellationChangeToken(cts.Token));
            }

            return RedirectToAction("GetDependentEntries");        
        }
        
        public IActionResult GetDependentEntries()
        {
            return View("Dependent", new DependentViewModel
            {
                ParentCachedTime = _cache.Get<DateTime?>(CacheKeys.Parent),
                ChildCachedTime = _cache.Get<DateTime?>(CacheKeys.Child),
                Message = _cache.Get<string>(CacheKeys.DependentMessage)
            });
        }
        
        public IActionResult RemoveChildEntry()
        {
            _cache.Get<CancellationTokenSource>(CacheKeys.DependentCTS).Cancel();
            return RedirectToAction("GetDependentEntries");
        }

        private static void DependentEvictionCallback(object key, object value,
            EvictionReason reason, object state)
        {
            var message = $"Parent entry was evicted. Reason: {reason}.";
            ((HomeController)state)._cache.Set(CacheKeys.DependentMessage, message);
        }
        
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult About()
        {
            ViewData["Message"] = "Your application description page.";

            return View();
        }

        public IActionResult Contact()
        {
            ViewData["Message"] = "Your contact page.";

            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
