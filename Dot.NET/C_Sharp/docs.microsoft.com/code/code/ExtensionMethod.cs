using System;
using System.Collections.Generic;
using System.Text;

namespace ExtensionMethodsExample
{
    public static class Extension
    {
        public static int WordCount(this string str)
        {
            string[] userString = str.Split(new char[] { ' ', '.', '?' });

            int wordCount = userString.Length;
            return wordCount;
        }

        public static int TotalCharWithoutSpace(this string str)
        {
            int totalCharWithoutSpace = 0;
            string[] userString = str.Split(' ');
            foreach (string stringValue in userString)
            {
                totalCharWithoutSpace += stringValue.Length;
            }
            return totalCharWithoutSpace;
        }

    }
}
