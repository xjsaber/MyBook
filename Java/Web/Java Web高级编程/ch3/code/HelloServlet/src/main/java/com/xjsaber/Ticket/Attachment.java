package com.xjsaber;

/**
 * Created by xjSaber on 2017/1/8.
 */
public class Attachment {
    private String name;

    private byte[] contents;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public byte[] getContents()
    {
        return contents;
    }

    public void setContents(byte[] contents)
    {
        this.contents = contents;
    }
}
