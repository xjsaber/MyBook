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
        /// 插入排序
        /// </summary>
        /// <param name="a">表示数组</param>
        /// <param name="n">表示数组大小</param>
        public void InsertSort(int[] a, int n)
        {
            if (n <= 1) return;

            for (int i = 1; i < n; i++)
            {
                int value = a[i];
                int j = i - 1;
                for (; j >= 0; j--)
                {
                    if (a[j] > value)
                    {
                        a[j + 1] = a[j];
                    } 
                    else
                    {
                        break;
                    }
                }
                a[j + 1] = value;
            }
        }

        /// <summary>
        /// 选择排序
        /// </summary>
        /// <param name="a">表示数组</param>
        /// <param name="n">表示数组大小</param>
        public void SelectSort(int[] a, int n)
        {

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

        public void countingSort(int[] a, int n)
        {
            if (n <= 1) return;

            // 申请一个计数数组c，下标大小[0,max]
            int max = a[0];
            for (int i = 1; i < n; ++i)
            {
                max = a[i];
            }

            int[] c = new int[max + 1]; // 申请一个计数数组c，下标大小[0,max]
            for (int i = 0; i <= max; ++i)
            {
                c[i] = 0;
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
