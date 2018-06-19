using System;
using System.Collections.Generic;

namespace code
{
    class CovarianceAndContravariance
    {
        static object GetObject() { return null; }
        static void SetObject(object obj) { }

        static string GetString() { return ""; }
        static void SetString(string str) { }

        public void Test() {

            IEnumerable<string> strings = new List<string>();
            IEnumerable<object> objects = strings;

            Action<object> actObject = SetObject;
            Action<string> actString = actObject;

            Func<object> del = GetString;

            Action<string> del2 = SetObject;
        }
    }
}
