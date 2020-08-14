package teeu.android.retrofit2rxjava.retrofit;

import android.content.Context;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private static Retrofit post;
    private static Retrofit banner;

    private RetrofitClient(){}

    public static RetrofitClient getInstance(Context context) {
        if(instance == null)
            instance = new RetrofitClient();
        return instance;
    }

    public Retrofit getPostClient() {
        if(post == null) {
            post = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return post;
    }

    public Retrofit getBannerClient() {
        if(banner == null) {
            banner = new Retrofit.Builder()
                    .baseUrl(BannerApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return banner;
    }
}
