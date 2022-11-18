package com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.nrahmatd.R
import com.dicoding.nrahmatd.databinding.ItemMovieBinding
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.model.MovieModel

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class MovieAdapter(private val movieList: ArrayList<MovieModel>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onMovieItemClickListener: ((MovieModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = movieList[position]

        Glide.with(viewHolder.itemView.context)
            .load(item.imgMovie)
            .apply(RequestOptions().override(350, 550))
            .into(viewHolder.imgShow)

        viewHolder.txtTitle.text = item.titleMovie
        viewHolder.txtDate.text = item.dateMovie
        viewHolder.txtDesc.text = item.descMovie

        viewHolder.itemView.setOnClickListener {
            onMovieItemClickListener?.invoke(movieList[viewHolder.bindingAdapterPosition])
        }
    }

    fun populateMovieData(refresh: Boolean, dataModel: ArrayList<MovieModel>) {
        movieList.clear()
        if (refresh)
            dataModel.shuffle()
        movieList.addAll(dataModel)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgShow: ImageView = itemView.findViewById(R.id.img_show)
        var txtTitle: TextView = itemView.findViewById(R.id.txt_title)
        var txtDate: TextView = itemView.findViewById(R.id.txt_date)
        var txtDesc: TextView = itemView.findViewById(R.id.txt_desc)
    }
}
