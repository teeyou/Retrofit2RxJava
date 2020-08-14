package teeu.android.retrofit2rxjava.retrofit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;

    private RetrofitClient(){}

    public static Retrofit getInstance() {
        if(instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

        return instance;
    }
}
