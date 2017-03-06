package test;

/**
 * Created by xjsaber on 2017/3/6.
 */
public interface Iterator<E> {
    E next();
    boolean hasNext();
    void remove();
}
