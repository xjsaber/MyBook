package com.xjsaber.flink.scala.typeinfo;

import org.apache.flink.api.common.typeinfo.TypeInfoFactory;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author xjsaber
 */
public class CustomTypeInfoFactory extends TypeInfoFactory<CustomTuple> {
    @Override
    public TypeInformation<CustomTuple> createTypeInfo(Type type, Map<String, TypeInformation<?>> map) {
//        return new CustomTypeInfoFactory(map.get("String"), map.get("int"));
        return null;
    }
}
