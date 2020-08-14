package teeu.android.retrofit2rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import adapter.PostAdapter;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import teeu.android.retrofit2rxjava.model.Post;
import teeu.android.retrofit2rxjava.retrofit.Api;
import teeu.android.retrofit2rxjava.retrofit.RetrofitClient;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Api mApi;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        mApi = retrofit.create(Api.class);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        mCompositeDisposable.add(mApi.getData("JakeWharton", "hugo")
                .subscribeOn(Schedulers.io()) //io thread에서 데이터 받아와서 main thread에 통지
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> displayData(data)));
    }

    private void displayData(List<Post> posts) {
        PostAdapter adapter = new PostAdapter(this, posts);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
        super.onStop();
    }
}