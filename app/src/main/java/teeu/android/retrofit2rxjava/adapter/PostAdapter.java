package teeu.android.retrofit2rxjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import teeu.android.retrofit2rxjava.MainActivity;
import teeu.android.retrofit2rxjava.R;
import teeu.android.retrofit2rxjava.model.Banner;
import teeu.android.retrofit2rxjava.model.Post;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<Post> mPostList;
    Banner mBanner;

    public PostAdapter(Context context) {
        mContext = context;
        mPostList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        Post post = mPostList.get(position);
        if (post.getBanner() == null) return 0;
        else return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_first, parent, false);
            return new PostViewHolder(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_second, parent, false);
            return new BannerViewHolder(view);
        } else
            throw new RuntimeException("viewType has to be 0 or 1");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            initLayoutFirst((PostViewHolder) holder, position);
        } else if (holder.getItemViewType() == 1) {
            initLayoutSecond((BannerViewHolder) holder, position);
        }
    }

    private void initLayoutFirst(PostViewHolder holder, int position) {
        holder.title.setText(mPostList.get(position).getTitle());
        holder.number.setText(String.valueOf(mPostList.get(position).getNumber()));

        holder.itemView.setOnClickListener(v -> {
            MainActivity.isRead = false;
            notifyDataSetChanged();
        });
    }

    private void initLayoutSecond(BannerViewHolder holder, int position) {
        if (MainActivity.isRead) {
            Glide.with(holder.itemView.getContext()).load("").into(holder.banner);
        } else {
            Glide.with(holder.itemView.getContext()).load(mBanner.getUrl()).into(holder.banner);
            holder.itemView.setOnClickListener(v -> {
                MainActivity.isRead = true;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mBanner.getLink()));
                mContext.startActivity(intent);
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public void setPostList(List<Post> list) {
        mPostList = list;
        if (mBanner != null) {
            int idx = mBanner.getPosition();
            if (mPostList.get(idx).getBanner() == null) {
                Post post = new Post(-1, null, mBanner);
                mPostList.add(idx, post);    //banner를 먼저 받아오고 posts를 받아온 경우
            }
        }
        notifyDataSetChanged();
    }

    public void setBanner(Banner banner) {
        mBanner = banner;
        int idx = banner.getPosition();
        Post post = new Post(-1, null, mBanner);

        if (mPostList.size() > idx)
            mPostList.add(idx, post); //posts를 먼저 받아오고 banner를 받아 온 경우 실행

        notifyDataSetChanged();
    }
}
