package com.xjsaber.flink.scala.typeinfo;

import org.apache.flink.api.common.typeinfo.TypeInfo;

/**
 * @author xjsaber
 */
@TypeInfo(CustomTypeInfoFactory.class)
public class CustomTuple {
    public String field0;
    public int field1;
}
