package com.exceptioncatchers.bookfinder.userlibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.databinding.BookHolderBinding

class UserLibraryAdapter(
    private val bookList: List<BookDetails>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<UserLibraryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): UserLibraryViewHolder {
        val binding = BookHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserLibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserLibraryViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(getItem(position))
        }
    }

    override fun getItemCount(): Int = bookList.size

    private fun getItem(position: Int): BookDetails = bookList[position]
}

class UserLibraryViewHolder(private val binding: BookHolderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book: BookDetails) {
        Glide.with(itemView.context)
            .asBitmap()
            .load(book.bookPoster)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.bookImg)
        binding.bookName.append(book.bookTitle)
        binding.bookWriter.append(book.bookAutor)
    }
}

interface ItemClickListener {
    fun onItemClicked(book: BookDetails)
}
