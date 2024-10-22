package com.grasepta.storyapp.main.home.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.grasepta.storyapp.base.BaseRecycleViewAdapter
import com.grasepta.storyapp.base.helper.formatToReadableDate
import com.grasepta.storyapp.base.helper.loadImageFrom
import com.grasepta.storyapp.core.data.response.ListStory
import com.grasepta.storyapp.databinding.ItemStoryBinding

class StoryAdapter(fragment: Fragment): BaseRecycleViewAdapter<ListStory, StoryAdapter.ViewHolder>(fragment) {

    var onTapItem: ((ListStory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflateViewBinding(parent))
    }

    override fun getBindViewHolder(
        holder: ViewHolder,
        position: Int,
        data: ListStory
    ) {
        holder.binding(data)
    }

    inner class ViewHolder(private val bind: ItemStoryBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun binding(data: ListStory){
            bind.tvItemName.text = data.name
            bind.tvItemDescription.text = data.description
            bind.tvItemDate.text = data.createdAt?.formatToReadableDate()
            bind.ivItemPhoto.loadImageFrom(data.photoUrl.orEmpty())

            bind.root.setOnClickListener {
                onTapItem?.invoke(data)
            }
       }
    }
}
