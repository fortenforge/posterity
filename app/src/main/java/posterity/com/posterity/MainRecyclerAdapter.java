package posterity.com.posterity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolderPoster> {

    private List<List<String>> posterData;

    public MainRecyclerAdapter(List<List<String>> posterData) {
        this.posterData = posterData;
        Log.d("Posterity", "MainRecyclerAdapter initialized");
    }

    @Override
    public ViewHolderPoster onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_item, parent, false);
        return new ViewHolderPoster(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderPoster holder, int position) {
        holder.posterTitle.setText(posterData.get(position).get(1));
        holder.posterDate.setText(posterData.get(position).get(2));
    }

    @Override
    public int getItemCount() {
        return posterData.size();
    }

    protected class ViewHolderPoster extends RecyclerView.ViewHolder {
        public ImageView posterThumbnail;
        public TextView posterTitle;
        public TextView posterDate;

        public ViewHolderPoster(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, PosterActivity.class);
                    intent.putExtra("eventnum", getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            posterThumbnail = (ImageView) itemView.findViewById(R.id.poster_thumbnail);
            posterTitle = (TextView) itemView.findViewById(R.id.poster_title);
            posterDate = (TextView) itemView.findViewById(R.id.poster_date);
        }
    }

}
