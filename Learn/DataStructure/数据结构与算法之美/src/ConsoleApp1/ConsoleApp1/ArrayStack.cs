using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    class ArrayStack<T>
    {
        private T[] items;
        private int count;
        private int n;

        public ArrayStack(int n)
        {
            this.items = new T[n];
            this.n = n;
            this.count = 0;
        }

        public bool Push(T item)
        {
            if (count == n) return false;
            items[count] = item;
            ++count;
            return true;
        }

        public T Pop()
        {
            if (count == 0) return default;
            T item = items[count-1];
            --count;
            return item;
        }
    }
}
