package teeu.android.retrofit2rxjava.retrofit;

import java.util.List;


import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import teeu.android.retrofit2rxjava.model.Banner;
import teeu.android.retrofit2rxjava.model.Post;

public interface Api {
    public static final String BASE_URL = "https://api.github.com";

    @GET("/repos/{user_name}/{repo_name}/issues")
    Flowable<List<Post>> getData(@Path("user_name") String user, @Path("repo_name") String repo);

}
