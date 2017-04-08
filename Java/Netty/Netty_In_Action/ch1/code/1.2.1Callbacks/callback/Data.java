package callback;

/**
 * Created by xjsaber on 2017/4/8.
 *
 */
public class Data {

    private int n;
    private int m;

    public Data(int n, int m){
        this.n = n;
        this.m = m;
    }

    @Override
    public String toString(){
        int r = n / m;
        return String.format("%d / %d = %d", n, m, r);
    }
}
