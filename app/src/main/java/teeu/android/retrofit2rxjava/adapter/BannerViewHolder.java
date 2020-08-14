package teeu.android.retrofit2rxjava.adapter;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import teeu.android.retrofit2rxjava.R;

public class BannerViewHolder extends RecyclerView.ViewHolder{
    ImageView banner;
    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.img_banner);
    }
}
