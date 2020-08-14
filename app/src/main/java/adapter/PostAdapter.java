package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import teeu.android.retrofit2rxjava.R;
import teeu.android.retrofit2rxjava.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    Context mContext;
    List<Post> mPostList;

    public PostAdapter(Context context, List<Post> postList) {
        mContext = context;
        mPostList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.number.setText(String.valueOf(mPostList.get(position).getNumber()));
        holder.title.setText(mPostList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }
}
