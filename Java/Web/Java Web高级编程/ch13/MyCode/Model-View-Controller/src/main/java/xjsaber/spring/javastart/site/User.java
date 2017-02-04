package xjsaber.spring.javastart.site;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xjsaber on 2017/2/1.
 */
@XmlRootElement
public class User {
    private long userId;
    private String username;
    private String name;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
