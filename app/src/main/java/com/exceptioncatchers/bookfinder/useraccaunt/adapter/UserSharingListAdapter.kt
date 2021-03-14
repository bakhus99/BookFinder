package com.exceptioncatchers.bookfinder.useraccaunt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.databinding.NowReadingHolderBinding
import com.exceptioncatchers.bookfinder.userlibrary.adapter.ItemClickListener

class UserSharingListAdapter(
    private val bookList: List<BookDetails>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<UserSharingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSharingListViewHolder {
        val binding = NowReadingHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserSharingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserSharingListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(getItem(position))
        }
    }

    override fun getItemCount(): Int = bookList.size

    private fun getItem(position: Int): BookDetails = bookList[position]
}

class UserSharingListViewHolder(private val binding: NowReadingHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookDetails) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(book.bookPoster)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.accBookImg)
            binding.accBookTitle.text = book.bookTitle
            binding.accBookAuthor.text = book.bookAuthor
        }
}

interface ItemClickListener {
    fun onItemClicked(book: BookDetails)
}