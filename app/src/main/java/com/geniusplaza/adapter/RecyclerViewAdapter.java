package com.geniusplaza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geniusplaza.R;
import com.geniusplaza.model.User;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<User> item;
    private Context mContext ;

    public RecyclerViewAdapter(Context context, List<User> item ) {
        Log.i("autolog", "RecyclerViewAdapter");
        this.item = item;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("autolog", "onBindViewHolder");
        holder.first_name.setText(item.get(position).getFirstName());
        holder.last_name.setText(item.get(position).getLastName());
        setImageUrl(item.get(position).getImageUrl(), holder.user_image);
    }

    @Override
    public int getItemCount() {
        Log.i("autolog", "getItemCount");
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView first_name, last_name;
        public ImageView user_image;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i("autolog", "ViewHolder");

            first_name = (TextView) itemView.findViewById(R.id.tvFirstName);
            last_name = (TextView) itemView.findViewById(R.id.tvLastName);
            user_image = (ImageView)itemView.findViewById(R.id.imageViewUser);

        }
    }
    private void setImageUrl(String imageUrl,ImageView mImageView){

        try {
            Glide
                    .with(mContext)
                    .load(imageUrl)
                    .override(100,100) // Resize image
                    .placeholder(R.drawable.ic_launcher_background) // Place holder image
                    .error(R.drawable.ic_launcher_background) // On error image
                    .into(mImageView);

        } catch(IllegalArgumentException ex) {
            Log.wtf("Glide-tag", String.valueOf(mImageView.getTag()));
        }


    }
}