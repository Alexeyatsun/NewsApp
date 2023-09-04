import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.gonews.R
import com.example.gonews.databinding.NewsItemBinding
import com.example.gonews.model.news.Article
import com.example.gonews.presentation.OnItemClickListener


class UnsplashNewsAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Article, UnsplashNewsAdapter.NewsViewHolder>(NEWS_COMPARATOR) {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NewsItemBinding.bind(view)
        fun bind(news: Article?) {
            binding.apply {
                Glide.with(itemView)
                    .load(news?.urlToImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.image_not_found)
                    .into(ivNewsFoto)
                tvTextNews.text = news?.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(getItem(position))
        }
    }

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title && oldItem.url == newItem.url
            }
        }
    }
}