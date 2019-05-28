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
 * #@MappedJdbcTypes 声明JdbcType为整形
 * #@MappedTypes 声明JavaType为SexEnum
 * @author xjsaber
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {

    }

    private final int SEX_MALE = 1;
    private final int SEX_FEMALE = 2;

    /**
     * 通过列名
     * @param rs 结果集
     * @param col 列名
     * @return SexEnum
     * @throws SQLException sql异常
     */
    @Override
    public SexEnum getNullableResult(ResultSet rs, String col) throws SQLException {
        int sex = rs.getInt(col);
        if (sex != SEX_MALE && sex != SEX_FEMALE){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        if (sex != SEX_MALE && sex != SEX_FEMALE) {
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * 通过存储过程读取性别
     * @param callableStatement 存储过程
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        if (sex != SEX_MALE && sex != SEX_FEMALE){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }
}
