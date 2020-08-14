package teeu.android.retrofit2rxjava.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import teeu.android.retrofit2rxjava.R;

public class PostViewHolder extends RecyclerView.ViewHolder{
    TextView number, title;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.text_number);
        title = itemView.findViewById(R.id.text_title);
    }
}
