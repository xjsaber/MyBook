package callback;

/**
 * Created by xjsaber on 2017/4/8.
 *
 */
public class MyFetcher implements Fetcher{

    private final Data data;

    public MyFetcher(Data data){
        this.data = data;
    }

    public void fetchData(FetcherCallback callback){
        try {
            callback.onData(data);
        }
        catch (Exception ex){
            callback.onError(ex);
        }
    }
}
