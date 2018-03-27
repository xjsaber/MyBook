using System;
using System.Collections.Generic;
using System.Text;

namespace ch1
{
    interface IObservable<out T>
    {
        IDisposable Subscribe(IObserver<T> observer);
    }
}
