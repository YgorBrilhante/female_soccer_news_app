package adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brilhante.appfutebolfeminino.databinding.NewsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final NewsAdapter.favoriteListener favoriteListener;

    public NewsAdapter(List<News> news, NewsAdapter.favoriteListener favoriteListener){
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.ivTitle.setText(news.getTitle());
        holder.binding.ivDescription.setText(news.getDescription());
        Picasso.get().load(news.getImage()).into(holder.binding.ivThumbnail);
        holder.binding.btOpen.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(i);
        });
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, news.getLink());
            i.putExtra(Intent.EXTRA_TEXT, news.getLink());
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Share via"));
        });
        holder.binding.ivFavorite.setOnClickListener(view -> {
          news.favorite = !news.favorite;
          this.favoriteListener.onFavorite(news);
          notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface favoriteListener {
        void onFavorite(News news);
    }
}
