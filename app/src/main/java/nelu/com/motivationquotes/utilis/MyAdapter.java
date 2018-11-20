package nelu.com.motivationquotes.utilis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nelu.com.motivationquotes.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> images;
    private Context context;

    public MyAdapter(List<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recyle_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String listItem = images.get(position);
        //can modify crop,fit, centtercrop Change with centerInside

        Picasso.with(context)
                .load(listItem)
                .fit()
                .placeholder(R.drawable.loading)
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }


}
