package com.xjsaber.learn.spring.springboot.mybatis;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xjsaber
 */
// 声明JdbcType为整形
@MappedJdbcTypes(JdbcType.INTEGER)
// 声明
@MappedTypes(value = SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
