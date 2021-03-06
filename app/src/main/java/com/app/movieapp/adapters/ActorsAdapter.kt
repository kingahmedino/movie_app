package com.app.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.databinding.ActorListItemBinding
import com.app.movieapp.models.Actor
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ActorsAdapter(
    private val mContext: Context,
    private val mActorsList: List<Actor>
): RecyclerView.Adapter<ActorsAdapter.BindingHolder>() {
    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = ActorListItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return BindingHolder(binding.root)
    }

    override fun getItemCount(): Int = mActorsList.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val actor = mActorsList[position]

        if (position == 0)
            holder.mBinding!!.isFirstItem = true
        else if (position == mActorsList.size - 1)
            holder.mBinding!!.isLastItem = true

        holder.mBinding?.actor = actor
        Glide.with(mContext)
            .load(IMAGE_BASE_URL + actor.profile_path)
            .placeholder(actor.placeHolderImg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.mBinding?.actorImageView)

        holder.mBinding?.executePendingBindings()
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mBinding = DataBindingUtil.bind<ActorListItemBinding>(itemView)
    }
}