package test;

/**
 * Created by xjsaber on 2017/3/6.
 *
 */
public interface Queue<E> {
    void add(E element);
    E remove();
    int size();
}
