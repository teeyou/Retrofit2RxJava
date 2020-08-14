package teeu.android.retrofit2rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import teeu.android.retrofit2rxjava.adapter.PostAdapter;
import teeu.android.retrofit2rxjava.model.Post;
import teeu.android.retrofit2rxjava.retrofit.Api;
import teeu.android.retrofit2rxjava.retrofit.BannerApi;
import teeu.android.retrofit2rxjava.retrofit.RetrofitClient;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private Api mApi;
    private BannerApi mBannerApi;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public static boolean isRead;

    @Override
    protected void onStart() {
        super.onStart();
        checkBanner();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new PostAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        Retrofit retrofit_post = RetrofitClient.getInstance(getApplicationContext()).getPostClient();
        mApi = retrofit_post.create(Api.class);

        Retrofit retrofit_banner = RetrofitClient.getInstance(getApplicationContext()).getBannerClient();
        mBannerApi = retrofit_banner.create(BannerApi.class);

        fetchPosts();
        fetchBanner();

    }

    private void checkBanner() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        isRead = pref.getBoolean("isRead", false); //default false
        Log.d("MYTAG", "isRead : " + isRead);
    }

    private void fetchBanner() {
        mCompositeDisposable.add(mBannerApi.getBannerData()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    mAdapter.setBanner(data);
                }));
    }

    private void fetchPosts() {
        mCompositeDisposable.add(mApi.getData("JakeWharton", "hugo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Collections.sort(data, new Comparator<Post>() {
                        @Override
                        public int compare(Post post, Post t1) {
                            return post.getNumber() - t1.getNumber();
                        }

                        @Override
                        public boolean equals(Object o) {
                            return false;
                        }
                    });

                    mAdapter.setPostList(data);
                }));
    }

    @Override
    protected void onPause() {
        Log.d("MYTAG", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MYTAG", "onStop");
        mCompositeDisposable.clear();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isRead", isRead);
        editor.apply();
        super.onStop();
    }
}