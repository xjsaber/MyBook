package com.xjsaber.java.concurrency.ch4;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xjsaber
 * 将线程安全委托给ConcurrentHashMap
 */
@ThreadSafe
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points){
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
//        return unmodifiableMap;
        // 如果需要一个不发生变化的车辆视图，那么getLocations可以返回对locations这个Map对象的一个浅拷贝（Shallow Copy）
        // 由于Map的内容是不可变，因此只需复制Map的结构，而不用复制它的内容。
        return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y){
        if (locations.replace(id, new Point(x, y)) == null){
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }
}
