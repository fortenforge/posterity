package posterity.com.posterity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolderPoster> {

    private List<String> posterTitles;
    private List<String> posterDates;

    public MainRecyclerAdapter(List<String> posterTitles, List<String> posterDates) {
        this.posterTitles = posterTitles;
        this.posterDates = posterDates;
    }

    @Override
    public ViewHolderPoster onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_item, parent, false);
        return new ViewHolderPoster(v);

    }

    @Override
    public void onBindViewHolder(ViewHolderPoster holder, int position) {
        holder.posterTitle.setText(posterTitles.get(position));
        holder.posterDate.setText(posterDates.get(position));
    }

    @Override
    public int getItemCount() {
        return posterTitles.size();
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

                }
            });
            posterThumbnail = (ImageView) itemView.findViewById(R.id.poster_thumbnail);
            posterTitle = (TextView) itemView.findViewById(R.id.poster_title);
            posterDate = (TextView) itemView.findViewById(R.id.poster_date);
        }
    }

}
