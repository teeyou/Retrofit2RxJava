package teeu.android.retrofit2rxjava.retrofit;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import teeu.android.retrofit2rxjava.model.Banner;

public interface BannerApi {
    public static final String BASE_URL = "https://search-mock.herokuapp.com";

    @GET("/banner")
    Flowable<Banner> getBannerData();
}
