package test;

import java.util.Iterator;

/**
 * Created by xjsaber on 2017/3/6.
 *
 */
public interface Collection<E> {

    /**
     * 向集合添加元素确实改变了集合就返回true，如果没有发生变化则返回false
     * @param element
     * @return
     */
    boolean add(E element);

    /**
     * 用于返回一个实现了Iterator接口的对象。可以使用这个迭代器对象依次访问集合中的元素。
     * @return
     */
    Iterator<E> iterator();
}
