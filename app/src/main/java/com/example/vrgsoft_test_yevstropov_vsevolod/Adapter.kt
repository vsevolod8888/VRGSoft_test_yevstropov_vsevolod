package com.example.vrgsoft_test_yevstropov_vsevolod
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsoft_test_yevstropov_vsevolod.databinding.HolderBinding
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain
import com.google.android.material.button.MaterialButton

class Adapter(
    var routeListener: RouteArchiveListener
) :
    ListAdapter<RedditDomain, Adapter.Holder>(ArchiveDiffCallback()) {
    var selectedItemPosition: Int = -1                                            // для чего это???

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.from(parent)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.itemView.findViewById<ImageView>(R.id.image).setOnClickListener {
            routeListener.onClickK(item)
            selectItemPosition(position)
        }
        holder.bind(item)
    }
    fun selectItemPosition(itemPos: Int) {
        selectedItemPosition = itemPos
        notifyDataSetChanged()
    }

    class Holder(val binding: HolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: RedditDomain
        ) {
            binding.redditDomain = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): Holder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HolderBinding.inflate(layoutInflater, parent, false)
                return Holder(binding)
            }
        }
    }
}

class ArchiveDiffCallback : DiffUtil.ItemCallback<RedditDomain>() {
    override fun areItemsTheSame(
        oldItem: RedditDomain,
        newItem: RedditDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: RedditDomain,
        newItem: RedditDomain
    ): Boolean {
        return oldItem == newItem
    }
}

interface RouteArchiveListener {        // ???????????
    fun onClickK(itemDetail: RedditDomain)
}