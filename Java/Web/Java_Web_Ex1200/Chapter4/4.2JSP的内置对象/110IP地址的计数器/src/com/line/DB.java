package com.line;
import java.sql.*;
/**
 * Created by xjsaber on 3/8/2016.
 */
public class DB {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String classname="com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private String url="jdbc:microsoft:sqlserver://localhost;DatabaseName=dbo.TTempTable";
    public DB(){}
    /**
     *
     */
    public Connection getConnection(){
        try{
            Class.forName(classname);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            connection = DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace(System.err);
            connection = null;
        }
        return connection;
    }
    /**
     *
     */
    public Statement getStatement(){
        try{
            connection = getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
        return statement;
    }

    public ResultSet search(String sql){
        getStatement();
        try{
            resultSet = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public int doSql(String sql){
        int i = -1;
        getStatement();
        try{
            i = statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    public void closed(){
        try{
            connection.close();
        }catch (Exception ex){

        }
    }
}
