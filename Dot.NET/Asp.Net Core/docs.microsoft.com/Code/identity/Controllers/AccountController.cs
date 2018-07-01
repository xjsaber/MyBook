using Microsoft.AspNetCore.Mvc;

namespace identity.Controllers
{
    public class AccountController : Controller
    {
        // GET
        public IActionResult Index()
        {
            return
            View();
        }
        
        public IActionResult Login()
        {
            ViewData["Message"] = "Your application description page.";

            return View();
        }
    }
}