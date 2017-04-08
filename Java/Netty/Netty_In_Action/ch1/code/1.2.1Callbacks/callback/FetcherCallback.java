package callback;

/**
 * Created by xjsaber on 2017/4/8.
 *
 */
public interface FetcherCallback {

    void onData(Data data) throws Exception;

    void onError(Throwable cause);
}
