package com.xjsaber.java.netty.guide.ch6;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author xjsaber
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private int userId;

    public UserInfo buildUserName(String userName){
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserId(int userId){
        this.userId = userId;
        return this;
    }

    /**
     * @return the userName
     */
    public final String getUserName(){
        return userName;
    }

    /**
     * @param userName userName
     *                 the userName to set
     */
    public final void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * @return the userId
     */
    public final int getUserId() {
        return userId;
    }

    /**
     * @param userId
     *      the userId to set
     */
    public final void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userId);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

}
