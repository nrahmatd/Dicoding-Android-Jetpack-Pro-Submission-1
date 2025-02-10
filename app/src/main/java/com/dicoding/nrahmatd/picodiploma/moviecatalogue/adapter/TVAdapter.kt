package com.dicoding.nrahmatd.picodiploma.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.nrahmatd.picodiploma.core.model.TVModel
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.R
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.databinding.ItemTvBinding

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class TVAdapter(private val tvList: ArrayList<TVModel>) :
    RecyclerView.Adapter<TVAdapter.ViewHolder>() {

    var onTvItemClickListener: ((TVModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)

    override fun getItemCount(): Int = tvList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = tvList[position]

        Glide.with(viewHolder.itemView.context)
            .load(item.imgTV)
            .apply(RequestOptions().override(350, 550))
            .into(viewHolder.imgShow)

        viewHolder.txtTitle.text = item.titleTV
        viewHolder.txtDate.text = item.dateTV
        viewHolder.txtDesc.text = item.descTV

        viewHolder.itemView.setOnClickListener {
            onTvItemClickListener?.invoke(tvList[viewHolder.bindingAdapterPosition])
        }
    }

    fun populateTVData(refresh: Boolean, dataModel: ArrayList<TVModel>) {
        tvList.clear()
        if (refresh)
            dataModel.shuffle()
        tvList.addAll(dataModel)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgShow: ImageView = itemView.findViewById(R.id.img_show)
        var txtTitle: TextView = itemView.findViewById(R.id.txt_title)
        var txtDate: TextView = itemView.findViewById(R.id.txt_date)
        var txtDesc: TextView = itemView.findViewById(R.id.txt_desc)
    }
}
