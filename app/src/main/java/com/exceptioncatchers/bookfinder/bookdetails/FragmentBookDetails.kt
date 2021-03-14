package com.exceptioncatchers.bookfinder.bookdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.bookdetails.viewmodel.BookDetailsViewModel
import com.exceptioncatchers.bookfinder.bookdetails.viewmodel.BookDetailsViewModelFactory
import com.exceptioncatchers.bookfinder.databinding.FragmentBookDetailsBinding
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.exceptioncatchers.bookfinder.userlibrary.FragmentUserLibrary

class FragmentBookDetails : Fragment(R.layout.fragment_book_details) {
    private lateinit var binding: FragmentBookDetailsBinding
    private val bookDetailsViewModel: BookDetailsViewModel by viewModels {
        BookDetailsViewModelFactory()
    }
    private lateinit var bookId: String
    private val args: FragmentBookDetailsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookDetailsBinding.bind(view)
        bookId = args.bookId
        subscribeBookDetailsResponse(bookId)
    }

    private fun subscribeBookDetailsResponse(bookId: String) {
        bookDetailsViewModel.getBook(bookId)
        bookDetailsViewModel.getBookDetails()
            .observe(this.viewLifecycleOwner, {
                subscribeUserInfo(it)
            })
    }

    private fun subscribeUserInfo(book: BookDetails) {
        bookDetailsViewModel.getUser(book.userUid)
        bookDetailsViewModel.getUserInfo().observe(this.viewLifecycleOwner, { userResponse ->
            userResponse?.let {
                setupBookDetails(book, it)
            }
        })
    }

    private fun setupBookDetails(book: BookDetails, user: User) {
        with(binding) {
            bookTitle.text = book.bookTitle
            bookAutor.text = book.bookAuthor
            ratingBar.rating = book.bookRating
            cardViewButton.setOnClickListener {
                val action = FragmentBookDetailsDirections.actionFragmentBookDetailsToChatFragment(user)
                findNavController().navigate(action)
            }
            bookDescription.text = book.bookDescription
            //реализовать диалог с полным описанием по клику
            bookDescription.setOnClickListener { TODO() }
            //реализовать подгрузку юзернейма и листенер с переходом в профиль
            bookOwnerUsername.text = user.username
            bookOwnerUsername.setOnClickListener { goUserLibrary(user.uid) }
            quantityCount.text = book.sharingCount.toString()
        }
        this.context?.let {
            Glide.with(it)
                .asBitmap()
                .load(book.bookPoster)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.bookPoster)
        }
    }

    private fun goUserLibrary(userId: String) {
        val action = FragmentBookDetailsDirections.actionFragmentBookDetailsToFragmentUserLibrary(userId)
        findNavController().navigate(action)
    }
}
