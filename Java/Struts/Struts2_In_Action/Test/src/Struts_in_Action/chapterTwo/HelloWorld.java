package Struts_in_Action.chapterTwo;

/**
 * Created by xjsaber on 3/13/2016.
 */
public class HelloWorld {

    private static final String GREETING = "Hello";

    public String execute(){
        setCustomGreeting(GREETING + getName());
        return "SUCCESS";   //选择结果的控制字符串
    }

    private String name;    //保存数据的JavaBean数据
    private String customGreeting;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCustomGreeting(){
        return customGreeting;
    }

    public void setCustomGreeting(String customGreeting){
        this.customGreeting = customGreeting;
    }
}
