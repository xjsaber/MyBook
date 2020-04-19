using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    class ArrayQueue<T>
    {
        private T[] items;
        private int n = 0;
        private int head = 0;
        private int tail = 0;

        public ArrayQueue(int capacity)
        {
            items = new T[capacity];
            n = capacity;
        }

        public bool Enqueue(T item)
        {
            if (tail == n) return false;
            items[tail] = item;
            ++tail;
            return true;
        }

        public T Dequeue()
        {
            if (head == tail) return default;
            T t = items[head];
            ++head;
            return t;
        }
    }
}
