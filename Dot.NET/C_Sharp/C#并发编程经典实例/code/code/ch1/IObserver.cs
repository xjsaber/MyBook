using System;
using System.Collections.Generic;
using System.Text;

namespace ch1
{
    interface IObserver<in T>
    {
        void OnNext(T item);
        void OnCompleted();
        void OnError(Exception error);
    }
}
