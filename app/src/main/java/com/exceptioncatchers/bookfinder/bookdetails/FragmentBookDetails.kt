package com.exceptioncatchers.bookfinder.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.bookdetails.viewmodel.BookDetailsViewModel
import com.exceptioncatchers.bookfinder.bookdetails.viewmodel.BookDetailsViewModelFactory
import com.exceptioncatchers.bookfinder.databinding.FragmentBookDetailsBinding
import kotlinx.android.synthetic.main.fragment_book_details.*

class FragmentBookDetails : Fragment(R.layout.fragment_book_details) {
    private val binding: FragmentBookDetailsBinding by lazy {
        FragmentBookDetailsBinding.inflate(layoutInflater)
    }
    private val bookDetailsViewModel: BookDetailsViewModel by viewModels {
        BookDetailsViewModelFactory()
    }
    private lateinit var bookId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookId = requireNotNull(requireArguments().getString(BOOK_KEY))
        subscribeBookDetailsResponse(bookId)
    }

    private fun subscribeBookDetailsResponse(bookId: String) {
        bookDetailsViewModel.getBook(bookId)
        bookDetailsViewModel.getBookDetails()
            .observe(this.viewLifecycleOwner, { setupBookDetails(it) })
    }

    private fun setupBookDetails(book: BookDetails) {
        binding.bookTitle.append(book.bookTitle)
        binding.bookAutor.append(book.bookAutor)
        this.context?.let {
            Glide.with(it)
                .asBitmap()
                .load(book.bookPoster)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.bookPoster)
        }
        binding.ratingBar.rating = book.bookRating
        binding.cardViewButton.setOnClickListener { TODO() }
        binding.bookDescription.append(book.bookDescription)
        //реализовать диалог с полным описанием по клику
        binding.bookDescription.setOnClickListener { TODO() }
        //реализовать подгрузку юзернейма и листенер с переходом в профиль
        binding.bookOwnerUsername.append(book.bookOwner.username)
        binding.bookOwnerUsername.setOnClickListener { TODO() }
        binding.quantityCount.append(book.sharingCount.toString())
    }

    companion object {
        private const val BOOK_KEY = "book"
        fun newInstance(bookId: String): Fragment = FragmentBookDetails().apply {
            arguments = bundleOf(BOOK_KEY to bookId)
        }
    }
}