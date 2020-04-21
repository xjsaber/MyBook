using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    class MySort
    {
        /// <summary>
        /// 冒泡排序
        /// </summary>
        /// <param name="a">array</param>
        /// <param name="n">length</param>
        public void BubbleSort(int[] a, int n)
        {
            if (n <= 1) return;

            bool flag = false;
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n - i - 1; j++)
                {
                    if (a[j] > a[j + 1])
                    {
                        MySwitch(ref a[j], ref a[j + 1]);
                        flag = true;
                    }
                }
                if (!flag) break;
            }
        }

        /// <summary>
        /// 快速排序
        /// </summary>
        /// <param name="a">array</param>
        /// <param name="n">length</param>
        public void QuickSort(int[] a, int n)
        {
            if (n <= 1) return;

            for (int i = 0; i < n; i++)
            {
                int tmp = a[i];
                for (int j = n - 1; j > 1; j++)
                {
                    if (a[j] > tmp)
                    {
                        MySwitch(ref tmp, ref a[j]);
                    }
                }
            }
            
            bool flag = false;
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n - i - 1; j++)
                {
                    if (a[j] > a[j + 1])
                    {
                        MySwitch(ref a[j], ref a[j + 1]);
                        flag = true;
                    }
                }
                if (!flag) break;
            }
        }

        private void MySwitch(ref int a, ref int b)
        {
            int temp = a;
            a = b;
            b = temp;
        }
    }
}
