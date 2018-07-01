using System;
using System.Collections.Generic;
using System.Text;

namespace code
{

    class Expression_bodied
    {
        private string FirstName = "S";
        private string LastName = "B";
        public override string ToString() => $"{FirstName}, {LastName}";
        public string FullName => $"{FirstName}, {LastName}";

        public void Test()
        {
            Expression_bodied p = new Expression_bodied();
            Console.WriteLine(p.ToString());
            Console.WriteLine(p.FullName);
            Console.ReadKey();
        }

    }
}
