package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xjsaber on 2016/7/3.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class Quote {

    private String type;
    private Value value;

    public Quote(){

    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Value getValue(){
        return value;
    }

    public void setValue(Value value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value + '}';
    }
}
